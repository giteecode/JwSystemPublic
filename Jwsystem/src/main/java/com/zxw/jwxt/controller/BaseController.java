package com.zxw.jwxt.controller;

import com.zxw.jwxt.domain.UserRealm;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

/**
 * @author zxw
 * @date 2019/11/7 21:12
 */
public class BaseController {

    public String getUserId() {
        Subject subject = SecurityUtils.getSubject();
        UserRealm user = (UserRealm) subject.getPrincipal();
        return user.getId();
    }

    public String getUserQx() {
        String qx = (String) SecurityUtils.getSubject().getSession().getAttribute("qx");
        return qx;
    }

    public UserRealm getRealm() {
        UserRealm principal = (UserRealm) SecurityUtils.getSubject().getPrincipal();
        return principal;
    }

    public String loginType(){
        String RadioButtonList1 = (String) SecurityUtils.getSubject().getSession().getAttribute("RadioButtonList1");
        return RadioButtonList1;
    }

    public Session getSubjectSeesion(){
        return SecurityUtils.getSubject().getSession();
    }
}
