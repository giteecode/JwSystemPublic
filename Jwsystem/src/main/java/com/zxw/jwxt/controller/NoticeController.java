package com.zxw.jwxt.controller;


import com.zxw.jwxt.domain.Notice;
import com.zxw.jwxt.service.INoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author zxw
 * @since 2020-01-31
 */
@RestController
@RequestMapping("/api/notice")
public class NoticeController extends BaseController {
    @Autowired
    private INoticeService noticeService;

    @PostMapping
    public ResponseEntity save(@RequestBody Notice notice) {
        notice.setCreateTime(new Date());
        notice.setPublisher(getUserId());
        notice.setStatus(1);
        boolean b = noticeService.save(notice);
        return ResponseEntity.ok(b);
    }

    @PutMapping
    public ResponseEntity edit(@RequestBody Notice notice) {
        notice.setUpdateTime(new Date());
        boolean b = noticeService.updateById(notice);
        return ResponseEntity.ok(b);
    }

    @DeleteMapping
    public ResponseEntity delete(String id) {
        boolean b = noticeService.removeById(id);
        return ResponseEntity.ok(b);
    }

}
