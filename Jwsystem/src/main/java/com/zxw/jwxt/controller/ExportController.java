package com.zxw.jwxt.controller;

import com.zxw.common.pojo.RS;
import com.zxw.common.utils.XssfUtils;
import com.zxw.jwxt.service.StudentService;
import com.zxw.jwxt.vo.QueryStudentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author zxw
 * @date 2019/12/18 20:22
 */
@RestController
@RequestMapping("/api/export")
public class ExportController extends BaseController {
    @Autowired
    private StudentService studentService;


    /**
     * 导出学生信息模板模板
     */
    @GetMapping("/exportXlsModel")
    public void ExportXlsModel(HttpServletResponse response, HttpServletRequest request) throws Exception {
        XssfUtils xfu = new XssfUtils();
        xfu.ExportXlsModel(request, response);
    }

    /**
     * 导出该班级的学生表格
     *
     * @param response
     * @param request
     * @param queryStudentVO
     * @throws Exception
     */
    @GetMapping("/exportXlsStudent")
    public void ExportXlsStudent(HttpServletResponse response, HttpServletRequest request, QueryStudentVO queryStudentVO)
            throws Exception {
        studentService.exportXlsStudent(response, request, queryStudentVO);
    }

    /**
     * 导入学生信息
     *
     * @param file
     * @param queryStudentVO
     * @return
     * @throws Exception
     */
    @PostMapping("/importXlsStudent")
    public RS ImportXlsStudent(@RequestParam MultipartFile file, QueryStudentVO queryStudentVO) {
        RS flag = studentService.importXlsStudent(file, queryStudentVO);
        return RS.ok();
    }


}
