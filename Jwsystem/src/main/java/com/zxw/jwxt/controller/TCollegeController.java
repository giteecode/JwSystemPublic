package com.zxw.jwxt.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zxw.common.enums.ExceptionEnums;
import com.zxw.common.exception.JwException;
import com.zxw.common.pojo.RS;
import com.zxw.common.pojo.TableResponse;
import com.zxw.jwxt.domain.TCollege;
import com.zxw.jwxt.service.CollegeService;
import com.zxw.jwxt.vo.BaseQueryParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author zxw
 * @since 2023-11-07
 */
@RestController
@RequestMapping("/api/college")
public class TCollegeController extends BaseController {

    @Autowired
    private CollegeService collegeService;

    /**
     * 分页查询
     *
     * @return
     * @throws IOException
     */
    @GetMapping("/pageQuery")
    public TableResponse pageQuery(BaseQueryParam baseQueryParam) {
        IPage iPage = collegeService.pageQuery(baseQueryParam);
        TableResponse result = TableResponse.of(iPage);
        return result;
    }

    /**
     * 增加学院
     *
     * @return
     */
    @PostMapping("/saveOrUpdateCollege")
    public RS addCollege(@RequestBody TCollege model) {
        if (model == null) {
            throw new JwException(ExceptionEnums.NO_DATA);
        }
        return collegeService.save(model);
    }

    /**
     * 作废学院
     *
     * @return
     */
    @DeleteMapping("/deleteCollege")
    public RS delete(@RequestParam("id") String id) {
        RS result = collegeService.delete(id);
        return result;
    }

    /**
     * 修改学院信息
     *
     * @return
     */
    @PostMapping("editCollege")
    public RS editCollege(@RequestBody TCollege model) {
        if (model == null) {
            throw new JwException(ExceptionEnums.NO_DATA);
        }
        return collegeService.edit(model);
    }

    /**
     * 查询列表
     */
    @GetMapping("listajax")
    public List<TCollege> listajax() {
        List<TCollege> list = collegeService.findListNostatus();
        return list;
    }

}
