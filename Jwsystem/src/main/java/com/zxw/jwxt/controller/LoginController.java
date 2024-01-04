package com.zxw.jwxt.controller;

import com.zxw.common.enums.ExceptionEnums;
import com.zxw.common.exception.BadRequestException;
import com.zxw.common.exception.JwException;
import com.zxw.common.pojo.RS;
import com.zxw.jwxt.domain.UserRealm;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.StringJoiner;
import java.util.UUID;

/**
 * @author zxw
 * @date 2019/11/8 21:24
 */
@RestController
@RequestMapping("/api")
public class LoginController extends BaseController {

    /**
     * 登录功能
     *
     * @param username         用户名
     * @param password         密码
     * @param checkcode        验证码
     * @param RadioButtonList1 身份
     * @param request
     * @return
     */
    @PostMapping("/login")
    @ResponseBody
    public ResponseEntity login(String username, String password, String checkcode, String RadioButtonList1, HttpServletRequest request) {
        if (checkcode != null && !"".equals(checkcode)) {
            HttpSession session = request.getSession();
            System.out.println(session);
            //String code = (String) session.getAttribute(new StringJoiner("_").add("code").add(session.getId()).toString());
            //if (checkcode.equals(code)) {
                Subject subject = SecurityUtils.getSubject();
                AuthenticationToken token = new UsernamePasswordToken(username, password);
                try {
                    subject.getSession().setAttribute("RadioButtonList1", RadioButtonList1);
                    System.out.println(token);
                    subject.login(token);
                } catch (AuthenticationException e) {
                    throw new BadRequestException("用户名或密码错误");
                }
                getRealm().setToken(UUID.randomUUID().toString());
                return ResponseEntity.ok(getRealm());
            }
            throw new BadRequestException("验证码错误");
        //}
       // throw new BadRequestException("验证码不能为空");
    }

    /**
     * 用户退出时，销毁Session
     *
     * @return
     */
    @DeleteMapping("/logout")
    public RS logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return RS.ok();
    }

    /**
     * 获取用户信息
     *
     * @return
     */
    @GetMapping("/info")
    public ResponseEntity info() {
        UserRealm realm = getRealm();
        return ResponseEntity.ok(realm);
    }

}
