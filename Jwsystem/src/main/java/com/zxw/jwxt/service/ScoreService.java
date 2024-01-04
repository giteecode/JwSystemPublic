package com.zxw.jwxt.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zxw.common.exception.BadRequestException;
import com.zxw.common.pojo.RS;
import com.zxw.jwxt.domain.TScore;
import com.zxw.jwxt.domain.TeacherCourse;
import com.zxw.jwxt.dto.CourseDTO;
import com.zxw.jwxt.dto.ScoreDTO;
import com.zxw.jwxt.mapper.TScoreMapper;
import com.zxw.jwxt.vo.QueryScoreVO;
import com.zxw.jwxt.vo.QueryStudentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

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
public class ScoreService extends BaseService {
    @Autowired
    private TScoreMapper scoreMapper;

    @Autowired
    private ScoreService scoreService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private ITeacherCourseService teacherCourseService;

    @Autowired
    private ClassesService classesService;

    public RS saveCourse(String classesId, String cid, String teacherId) {
        try {
            QueryStudentVO studentVO = new QueryStudentVO();
            studentVO.setClassesId(classesId);
            List<QueryStudentVO> list = studentService.findStudentByclass(studentVO).getRecords();
            for (QueryStudentVO queryStudentVO : list) {
                TScore score = new TScore();
                score.setTeacherId(teacherId);
                score.setCourseId(cid);
                score.setStudentId(queryStudentVO.getSid());
                scoreMapper.insert(score);
            }
            return RS.ok();
        } catch (Exception e) {
            throw new BadRequestException("课程分配失败");
        }
    }

    /**
     * @param id
     * @return
     */
    public List<TScore> queryByStudentId(String id) {
        List<TScore> list = scoreMapper.findStudentId(id);
        return list;
    }

    /**
     * 查找学生的所有课程信息
     *
     * @param sid
     * @return
     */
    public List<CourseDTO> findSelectCourseByStudentId(String sid) {
        List<CourseDTO> list = scoreMapper.findSelectCourseByStudentId(sid);
        return list;
    }

    public RS addScore(QueryScoreVO scoreVO) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("student_id", scoreVO.getSid());
        queryWrapper.eq("course_id", scoreVO.getCid());
        TScore tScore = scoreMapper.selectOne(queryWrapper);
        if(tScore == null){
            throw new BadRequestException("查不到该用户信息");
        }
        Double attendance = scoreVO.getAttendance() * 0.2;
        Double usually = scoreVO.getUsually() * 0.2;
        Double exam = scoreVO.getExam() * 0.6;
        tScore.setAttendance(attendance.intValue());
        tScore.setUsually(usually.intValue());
        tScore.setExam(exam.intValue());
        tScore.setScore(attendance.intValue() + usually.intValue() + exam.intValue());
        tScore.setPoint(new BigDecimal((attendance.intValue() + usually.intValue() + exam.intValue()) / 20));
        tScore.setStatus(1);
        int i = scoreMapper.update(tScore, queryWrapper);
        return i == 0 ? RS.error("操作失败") : RS.ok();
    }

    /**
     * 查询学生成绩
     *
     * @param scoreVO
     * @param sid
     * @return
     */
    public IPage findStudentScore(QueryScoreVO scoreVO, String sid) {
        IPage<ScoreDTO> list = scoreMapper.findStudentScore(this.getPage(scoreVO), sid);
        return list;
    }

    /**
     * 记录缺勤
     *
     * @param scoreVO
     * @return
     */
    public RS addAbsent(QueryScoreVO scoreVO) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("course_id", scoreVO.getCid());
        queryWrapper.eq("student_id", scoreVO.getSid());
        TScore tScore = scoreMapper.selectOne(queryWrapper);
        tScore.setAbsent(tScore.getAbsent() + 1);
        int i = scoreMapper.update(tScore, queryWrapper);
        return i == 1 ? RS.ok() : RS.error("添加缺勤失败");
    }

    public Boolean findIsSelect(String userId, String cid) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("course_id", cid);
        queryWrapper.eq("student_id", userId);
        TScore tScore = scoreMapper.selectOne(queryWrapper);
        return tScore == null ? false : true;
    }

    public RS save(QueryScoreVO scoreVO, String userId) {
        Boolean b = this.findIsSelect(userId, scoreVO.getCid());
        if (!b) {
            // 判断人数是否已满
            TeacherCourse teacherCourse = teacherCourseService.getOne(this.queryOne("id", scoreVO.getCid()));
            if (!teacherCourse.getPeople().equals(teacherCourse.getTotalPeople())) {
                // 选修
                TScore tScore = new TScore();
                tScore.setStudentId(userId);
                tScore.setTeacherId(scoreVO.getTid());
                tScore.setCourseId(teacherCourse.getId());
                int i = scoreMapper.insert(tScore);
                RS people = courseService.updatePeople(scoreVO.getCid());
                if (i == 1) {
                    return RS.ok();
                }
                throw new BadRequestException("选课失败");
            }
            throw new BadRequestException("课程人数已满");
        }
        throw new BadRequestException("已经选择了该门课程,不能重复下载");
    }

    public RS delete(String userId, String cid) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("course_id", cid);
        queryWrapper.eq("student_id", userId);
        int i = scoreMapper.delete(queryWrapper);
        return i == 1 ? RS.ok() : RS.error("操作失败");
    }

    public List<CourseDTO> findCourseByStudent(String sid) {
        List<CourseDTO> list = scoreMapper.findCourseByStudent(sid);
        return list;
    }
}
