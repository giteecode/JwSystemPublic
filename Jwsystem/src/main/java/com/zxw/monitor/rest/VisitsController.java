package com.zxw.monitor.rest;

import com.zxw.monitor.service.VisitsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zxw
 * @date 2019/12/18 20:23
 */
@RestController
@RequestMapping("/api/visits")
public class VisitsController {

    private final VisitsService visitsService;

    public VisitsController(VisitsService visitsService) {
        this.visitsService = visitsService;
    }

    @PostMapping
    public ResponseEntity create() {
        visitsService.count();
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity get() {
        return new ResponseEntity<>(visitsService.get(), HttpStatus.OK);
    }

    @GetMapping(value = "/chartData")
    public ResponseEntity getChartData() {
        return new ResponseEntity<>(visitsService.getChartData(), HttpStatus.OK);
    }
}
