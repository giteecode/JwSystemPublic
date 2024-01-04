package com.zxw.jwxt.controller;


import com.zxw.jwxt.domain.TYear;
import com.zxw.jwxt.service.ITYearService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author zxw
 * @since 2020-01-26
 */
@RestController
@RequestMapping("/api/year")
public class TYearController extends BaseController {
    @Autowired
    private ITYearService yearService;

    @GetMapping("/listajax")
    public ResponseEntity listajax() {
        List<TYear> list = yearService.list();
        return ResponseEntity.ok(list);
    }
}
