package com.zxw.jwxt.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zxw.common.enums.ExceptionEnums;
import com.zxw.common.exception.JwException;
import com.zxw.common.pojo.RS;
import com.zxw.common.utils.FileUtils;
import com.zxw.jwxt.domain.StudentRole;
import com.zxw.jwxt.domain.TStudent;
import com.zxw.jwxt.domain.TUser;
import com.zxw.jwxt.domain.UserRealm;
import com.zxw.jwxt.dto.CourseDTO;
import com.zxw.jwxt.dto.ScheduleDTO;
import com.zxw.jwxt.mapper.TStudentMapper;
import com.zxw.jwxt.vo.QueryStudentVO;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author zxw
 * @since 2023-11-07
 */
@Service
//@Transactional(rollbackFor = Exception.class)
public class StudentService extends BaseService {

    @Autowired
    private TStudentMapper studentMapper;

    @Autowired
    private CourseService courseService;

    @Autowired
    private StudentRoleService studentRoleService;

    public TStudent findByUsername(String username) {
        TStudent tStudent = studentMapper.selectById(username);
        return tStudent;
    }

    /**
     * 查询班级中的所有学生信息
     */
    public IPage findStudentByclass(QueryStudentVO queryStudentVO) {
        Page page = getPage(queryStudentVO);
        IPage<QueryStudentVO> iPage = studentMapper.findStudentByClassesId(page, queryStudentVO.getClassesId());
        return iPage;
    }

    /**
     * 查询所有学生
     */
    public IPage pageQuery(QueryStudentVO queryStudentVO) {
        Page page = getPage(queryStudentVO);
        QueryWrapper wrapper = getWrapper(queryStudentVO, null);
        IPage iPage = studentMapper.selectPage(page, wrapper);
        return iPage;
    }

    /**
     * 添加缺勤学生名单
     */
    public RS addStudentAbenst(QueryStudentVO queryStudentVO) {
        List<TStudent> list = studentMapper.selectBatchIds(queryStudentVO.getStudentIds());
        if (list == null) {
            return RS.error("无法查询到相应数据");
        }
        List<Object> collect = list.stream().map(e -> {
            e.setAbsent(e.getAbsent() + 1);
            studentMapper.updateById(e);
            return e;
        }).collect(Collectors.toList());
        return RS.ok(collect);
    }

    /**
     * 模板化代码
     * 导入学生信息表格
     */
    public RS importXlsStudent(MultipartFile myFile, QueryStudentVO queryStudentVO) {
        // 创建
        XSSFWorkbook workbook = null;
        try {
            workbook = new XSSFWorkbook(myFile.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 左下角单元
        XSSFSheet sheet = workbook.getSheetAt(0);
        for (Row row : sheet) {
            int rowNum = row.getRowNum();
            if (rowNum == 0) {
                continue;
            }
            XSSFCell cell = (XSSFCell) row.getCell(0);
            cell.setCellType(XSSFCell.CELL_TYPE_STRING);
            String sid = cell.getStringCellValue();
            String sname = row.getCell(1).getStringCellValue();
            /**
             * 转换
             */
            XSSFCell cell2 = (XSSFCell) row.getCell(2);
            cell2.setCellType(XSSFCell.CELL_TYPE_STRING);
            String password = cell2.getStringCellValue();
            String sex = row.getCell(3).getStringCellValue();
            String scity = row.getCell(4).getStringCellValue();
            row.getCell(5).setCellType(Cell.CELL_TYPE_STRING);
            row.getCell(6).setCellType(Cell.CELL_TYPE_STRING);
            String phone = row.getCell(5).getStringCellValue();
            String idcard = row.getCell(6).getStringCellValue();
            String address = row.getCell(7).getStringCellValue();
            String political = row.getCell(8).getStringCellValue();
            String beginTime = row.getCell(9).getStringCellValue();
            String roleId = "b762e0f84ec911e8bf5d34de1af4e65a";
            String qx = "学生";
            TStudent student = new TStudent();
            student.setUsername(sname);
            student.setScity(scity);
            student.setAbsent(0);
            student.setClassesId(queryStudentVO.getClassesId());
            student.setPassword(password);
            student.setQx(qx);
            student.setId(sid);
            student.setSex(sex);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            try {
                student.setBeginTime(sdf.parse(beginTime));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            student.setPhone(phone);
            student.setPoliticalStatus(political);
            student.setAddress(address);
            student.setIdcard(idcard);
            student.setGradeId(queryStudentVO.getGradeId());
            studentMapper.insert(student);
            StudentRole studentRole = new StudentRole();
            studentRole.setRoleId(roleId);
            studentRole.setStudentId(sid);
            RS srInsert = studentRoleService.insert(studentRole);
            if (srInsert.get("status").equals("0")) {
                return RS.error("导入失败");
            }
        }
        return RS.ok();
    }

    /**
     * 模板化代码
     */
    public void exportXlsStudent(HttpServletResponse response, HttpServletRequest request, QueryStudentVO queryStudentVO) throws IOException {
        Page page = getPage(queryStudentVO);
        Page<QueryStudentVO> list = studentMapper.findAll(page, queryStudentVO.getClassesId());
        // 在内存中创建一个Excel文件，通过输出流写到客户端提供下载
        if (list.getRecords().isEmpty()) {
            throw new JwException(ExceptionEnums.NO_DATA);
        }
        XSSFWorkbook workbook = new XSSFWorkbook();
        // 创建一个sheet页
        XSSFSheet sheet = workbook.createSheet(list.getRecords().get(0).getClassname() + "学生信息");
        XSSFCellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setWrapText(true);
        cellStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER); // 居中
        cellStyle.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
        // 第一行表头
        XSSFRow headRow = sheet.createRow(0);
        headRow.createCell(0).setCellValue("学号");
        headRow.createCell(1).setCellValue("姓名");
        headRow.createCell(2).setCellValue("密码");
        headRow.createCell(3).setCellValue("性别");
        headRow.createCell(4).setCellValue("户籍");
        headRow.createCell(5).setCellValue("班级");
        headRow.createCell(6).setCellValue("年级");
        headRow.createCell(7).setCellValue("入学时间");
        headRow.createCell(8).setCellValue("手机号码");
        headRow.createCell(9).setCellValue("身份证号");
        headRow.createCell(10).setCellValue("家庭住址");
        headRow.createCell(11).setCellValue("政治面貌");
        String classname = list.getRecords().get(0).getClassname();
        for (int i = 0; i < list.getRecords().size(); i++) {
            QueryStudentVO vo = list.getRecords().get(i);
            XSSFRow dataRow = sheet.createRow(sheet.getLastRowNum() + 1);
            dataRow.createCell(0).setCellValue(vo.getSid());
            dataRow.createCell(1).setCellValue(vo.getSname());
            dataRow.createCell(2).setCellValue(vo.getPassword());
            dataRow.createCell(3).setCellValue(vo.getSex());
            dataRow.createCell(4).setCellValue(vo.getScity());
            dataRow.createCell(5).setCellValue(classname);
            dataRow.createCell(6).setCellValue(vo.getGradeId());
            dataRow.createCell(7).setCellValue(new SimpleDateFormat("yyyy-MM-dd").format(vo.getBeginTime()));
            dataRow.createCell(8).setCellValue(vo.getPhone());
            dataRow.createCell(9).setCellValue(vo.getIdcard());
            dataRow.createCell(10).setCellValue(vo.getAddress());
            dataRow.createCell(11).setCellValue(vo.getPoliticalStatus());
        }
        String filename = list.getRecords().get(0).getClassname() + "学生信息.xlsx";
        String agent = request.getHeader("User-Agent");
        filename = FileUtils.encodeDownloadFilename(filename, agent);
        // 一个流两个头
        ServletOutputStream out = response.getOutputStream();
        String contentType = request.getSession().getServletContext().getMimeType(filename);
        response.setContentType(contentType);
        response.setHeader("content-disposition", "attchment;filename=" + filename);
        workbook.write(out);
    }

    public RS updateAbsent(String sid) {
        TStudent tStudent = studentMapper.selectById(sid);
        tStudent.setAbsent(tStudent.getAbsent() + 1);
        return studentMapper.updateById(tStudent) == 1 ? RS.ok() : RS.error("操作失败");
    }

    public TStudent findInfo(String userId) {
        TStudent tStudent = studentMapper.selectById(userId);
        return tStudent;
    }

    public Object[][] findSchedule(QueryStudentVO queryCourseVO, String userId) {
        Object[][] arr = new Object[5][7];
        List<CourseDTO> list = courseService.findScheduleByStudent(userId, queryCourseVO.getTeamId());
        list.forEach(e -> {
            ScheduleDTO scheduleDTO = new ScheduleDTO(e.getCourseName(), e.getWname(), e.getTeacherName(), e.getClassroom(), null);
            switch (e.getSse()) {
                case "1-2节":
                    parseSchedule(arr, e, scheduleDTO, 0);
                    break;
                case "3-4节":
                    parseSchedule(arr, e, scheduleDTO, 1);
                    break;
                case "5-6节":
                    parseSchedule(arr, e, scheduleDTO, 2);
                    break;
                case "7-8节":
                    parseSchedule(arr, e, scheduleDTO, 3);
                    break;
                case "9-10节":
                    parseSchedule(arr, e, scheduleDTO, 4);
                    break;
            }
        });
        return arr;
    }

    private void parseSchedule(Object[][] arr, CourseDTO e, ScheduleDTO scheduleDTO, int i) {
        switch (e.getSw()) {
            case "周一":
                List<ScheduleDTO> o1 = (List<ScheduleDTO>) arr[i][0];
                if (o1 == null) {
                    ArrayList<Object> list = new ArrayList<>();
                    list.add(scheduleDTO);
                    arr[i][0] = list;
                } else {
                    o1.add(scheduleDTO);
                }
                break;
            case "周二":
                List<ScheduleDTO> o2 = (List<ScheduleDTO>) arr[i][1];
                if (o2 == null) {
                    ArrayList<Object> list = new ArrayList<>();
                    list.add(scheduleDTO);
                    arr[i][1] = list;
                } else {
                    o2.add(scheduleDTO);
                }
                break;
            case "周三":
                List<ScheduleDTO> o3 = (List<ScheduleDTO>) arr[i][2];
                if (o3 == null) {
                    ArrayList<Object> list = new ArrayList<>();
                    list.add(scheduleDTO);
                    arr[i][2] = list;
                } else {
                    o3.add(scheduleDTO);
                }
                break;
            case "周四":
                List<ScheduleDTO> o4 = (List<ScheduleDTO>) arr[i][3];
                if (o4 == null) {
                    ArrayList<Object> list = new ArrayList<>();
                    list.add(scheduleDTO);
                    arr[i][3] = list;
                } else {
                    o4.add(scheduleDTO);
                }
                break;
            case "周五":
                List<ScheduleDTO> o5 = (List<ScheduleDTO>) arr[i][4];
                if (o5 == null) {
                    ArrayList<Object> list = new ArrayList<>();
                    list.add(scheduleDTO);
                    arr[i][4] = list;
                } else {
                    o5.add(scheduleDTO);
                }
                break;
            case "周六":
                List<ScheduleDTO> o6 = (List<ScheduleDTO>) arr[i][5];
                if (o6 == null) {
                    ArrayList<Object> list = new ArrayList<>();
                    list.add(scheduleDTO);
                    arr[i][5] = list;
                } else {
                    o6.add(scheduleDTO);
                }
                break;
            case "周日":
                List<ScheduleDTO> o7 = (List<ScheduleDTO>) arr[i][6];
                if (o7 == null) {
                    ArrayList<Object> list = new ArrayList<>();
                    list.add(scheduleDTO);
                    arr[i][6] = list;
                } else {
                    o7.add(scheduleDTO);
                }
                break;
        }
    }

    public List<Integer> countPeople(UserRealm realm) {
        TUser user = (TUser) realm;
        LocalDate date = LocalDate.now();
        int year = date.getYear();
        List<Integer> list = studentMapper.countPeople(String.valueOf(year - 4), String.valueOf(year - 1));
        return list;
    }

    public List<TStudent> findByCollege(String collegeId) {
        QueryWrapper wrapper = new QueryWrapper<>();
        wrapper.eq("college_id", collegeId);
        List<TStudent> list = studentMapper.selectList(wrapper);
        return list;
    }
}
