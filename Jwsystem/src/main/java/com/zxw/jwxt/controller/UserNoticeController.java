package com.zxw.jwxt.controller;


import com.zxw.jwxt.domain.UserNotice;
import com.zxw.jwxt.dto.NoticeDTO;
import com.zxw.jwxt.service.INoticeService;
import com.zxw.jwxt.service.IUserNoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author zxw
 * @since 2020-01-31
 */
@RestController
@RequestMapping("/api/userNotice")
public class UserNoticeController extends BaseController {
    @Autowired
    private IUserNoticeService userNoticeService;
    @Autowired
    private INoticeService noticeService;

    @GetMapping("/findNoticeByJW")
    public ResponseEntity findNoticeByJW() {
        List<NoticeDTO> list = userNoticeService.findNoticeByJW(getRealm());
        return ResponseEntity.ok(list);
    }

    @GetMapping("/findNoticeByStudent")
    public ResponseEntity findNoticeByStudent() {
        List<NoticeDTO> list = userNoticeService.findNoticeByStudent(getRealm());
        return ResponseEntity.ok(list);
    }

    @GetMapping("/findNoticeByTeacher")
    public ResponseEntity findNoticeByTeacher() {
        List<NoticeDTO> list = userNoticeService.findNoticeByTeacher(getRealm());
        return ResponseEntity.ok(list);
    }

    @PostMapping
    public ResponseEntity save(@RequestBody UserNotice userNotice, String type) {
        boolean b = userNoticeService.save(userNotice, getRealm(), type);
        return ResponseEntity.ok(b);
    }

    @PutMapping
    public ResponseEntity edit(@RequestBody UserNotice userNotice) {
        boolean b = userNoticeService.updateById(userNotice);
        return ResponseEntity.ok(b);
    }

    @DeleteMapping
    public ResponseEntity delete(String id) {
        boolean b = userNoticeService.removeById(id);
        return ResponseEntity.ok(b);
    }
}
