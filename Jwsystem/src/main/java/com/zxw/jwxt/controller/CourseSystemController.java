package com.zxw.jwxt.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zxw.common.pojo.TableResponse;
import com.zxw.jwxt.service.CourseSystemService;
import com.zxw.jwxt.vo.BaseQueryParam;
import com.zxw.jwxt.vo.QueryCourseSystemVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @PACKAGE_NAME: com.zxw.jwxt.controller
 * @author: zxw
 * @DATE: 2020/1/6 18:10:11
 **/
@RestController
@RequestMapping("/api/courseSystem")
public class CourseSystemController {
    @Autowired
    private CourseSystemService courseSystemService;

    /**
     * 查询所有
     *
     * @return
     */
    @GetMapping("/listajax")
    public ResponseEntity courseSystemListajax(BaseQueryParam baseQueryParam) {
        List list = courseSystemService.listajax(baseQueryParam);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/findCourseBySystemId")
    public ResponseEntity findCourseBySystemId(QueryCourseSystemVO courseSystemVO) {
        IPage iPage = courseSystemService.findCourseBySystemId(courseSystemVO);
        TableResponse of = TableResponse.of(iPage);
        return ResponseEntity.ok(of);
    }
}
