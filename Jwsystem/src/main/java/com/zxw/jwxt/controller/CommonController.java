package com.zxw.jwxt.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zxw.common.pojo.TableResponse;
import com.zxw.jwxt.service.*;
import com.zxw.jwxt.vo.BaseQueryParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author zxw
 * @date 2019/12/18 20:23
 */
@RestController
@RequestMapping("/api")
public class CommonController extends BaseController {
    @Autowired
    private TeamService teamService;

    @Autowired
    private SectionService sectionService;

    @Autowired
    private GradeService gradeService;

    @Autowired
    private ExamwayService examwayService;

    @Autowired
    private WeekService weekService;

    @Autowired
    private NatureService natureService;

    @Autowired
    private CstatusService cstatusService;

    /**
     * 分页查询
     *
     * @return
     */
    @GetMapping("/cstatus/pageQuery")
    public TableResponse cstatusPageQuery(BaseQueryParam baseQueryParam) {
        IPage iPage = cstatusService.pageQuery(baseQueryParam);
        TableResponse result = TableResponse.of(iPage);
        return result;
    }

    /**
     * 分页查询
     *
     * @return
     */
    @GetMapping("/nature/pageQuery")
    public TableResponse naturePageQuery(BaseQueryParam baseQueryParam) {
        IPage iPage = natureService.pageQuery(baseQueryParam);
        TableResponse result = TableResponse.of(iPage);
        return result;
    }


    /**
     * 分页查询
     *
     * @return
     */
    @GetMapping("/week/pageQuery")
    public TableResponse weekPageQuery(BaseQueryParam baseQueryParam) {
        IPage iPage = weekService.pageQuery(baseQueryParam);
        TableResponse result = TableResponse.of(iPage);
        return result;
    }

    /**
     * 分页查询
     *
     * @return
     */
    @GetMapping("/examway/pageQuery")
    public TableResponse examwayPageQuery(BaseQueryParam baseQueryParam) {
        IPage iPage = examwayService.pageQuery(baseQueryParam);
        TableResponse result = TableResponse.of(iPage);
        return result;
    }

    /**
     * 分页查询
     *
     * @return
     */
    @GetMapping("/grade/pageQuery")
    public TableResponse gradePageQuery(BaseQueryParam baseQueryParam) {
        IPage iPage = gradeService.pageQuery(baseQueryParam);
        TableResponse result = TableResponse.of(iPage);
        return result;
    }

    /**
     * ajax列表
     */
    @GetMapping("/grade/listajax")
    public List gradeListajax(BaseQueryParam baseQueryParam) {
        return gradeService.listajax(baseQueryParam);
    }

    /**
     * 分页查询
     *
     * @return
     */
    @GetMapping("/section/pageQuery")
    public TableResponse sectionPageQuery(BaseQueryParam baseQueryParam) {
        IPage iPage = sectionService.pageQuery(baseQueryParam);
        TableResponse result = TableResponse.of(iPage);
        return result;
    }

    /**
     * 分页查询
     *
     * @return
     */
    @GetMapping("/team/pageQuery")
    public TableResponse teamPageQuery(BaseQueryParam baseQueryParam) {
        IPage iPage = teamService.pageQuery(baseQueryParam);
        TableResponse result = TableResponse.of(iPage);
        return result;
    }
}
