package com.zxw.jwxt.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zxw.common.pojo.RS;
import com.zxw.common.pojo.TableResponse;
import com.zxw.jwxt.domain.TSpecialty;
import com.zxw.jwxt.service.SpecialtyService;
import com.zxw.jwxt.vo.QuerySpecialtyVO;
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
@RequestMapping("/api/specialty")
public class TSpecialtyController extends BaseController {
    @Autowired
    private SpecialtyService specialtyService;

    @DeleteMapping("/redoSpecialty")
    public RS redoSpecialty(@RequestParam("id") String id) {
        RS rs = specialtyService.redoSpecialty(id);
        return rs;
    }

    @GetMapping("/pageQuery")
    public TableResponse pageQuery(QuerySpecialtyVO baseQueryParam) throws IOException {
        IPage result = specialtyService.pageQuery(baseQueryParam, getRealm());
        TableResponse response = TableResponse.of(result);
        return response;
    }

    @PostMapping("/addSpecialty")
    public RS saveOrUpdateSpeciatly(@RequestBody TSpecialty specialty) {
        RS rs = specialtyService.saveOrUpdateSpeciatly(specialty);
        return rs;
    }

    @PostMapping("/updateSpecialty")
    public RS updateSpecialty(@RequestBody TSpecialty specialty) {
        RS rs = specialtyService.update(specialty);
        return rs;
    }

    @DeleteMapping("/delete")
    public RS delete(@RequestParam("id") String id) {
        RS rs = specialtyService.deleteBatch(id);
        return rs;
    }

    @GetMapping("/listajax")
    public List listajax(QuerySpecialtyVO querySpecialtyVO) {
        return specialtyService.listajax(querySpecialtyVO,getRealm());
    }
}
