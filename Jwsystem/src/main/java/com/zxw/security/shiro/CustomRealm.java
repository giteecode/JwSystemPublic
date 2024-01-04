package com.zxw.security.shiro;

import com.zxw.jwxt.domain.AuthFunction;
import com.zxw.jwxt.domain.TStudent;
import com.zxw.jwxt.domain.TTeacher;
import com.zxw.jwxt.domain.TUser;
import com.zxw.jwxt.service.AuthFunctionService;
import com.zxw.jwxt.service.StudentService;
import com.zxw.jwxt.service.TeacherService;
import com.zxw.jwxt.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author zxw
 * @date 2019/11/7 21:27
 */
public class CustomRealm extends AuthorizingRealm {

    @Autowired
    private UserService userSerivce;
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private AuthFunctionService functionService;

    //告诉shiro如何根据获取到的用户信息中的密码和盐值来校验密码
//    {
//        //设置用于匹配密码的CredentialsMatcher
//        HashedCredentialsMatcher hashMatcher = new HashedCredentialsMatcher();
//        hashMatcher.setHashAlgorithmName(Sha256Hash.ALGORITHM_NAME);
//        hashMatcher.setStoredCredentialsHexEncoded(false);
//        hashMatcher.setHashIterations(1024);
//        this.setCredentialsMatcher(hashMatcher);
//    }

    /**
     * 授权认证
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        Object object = principalCollection.getPrimaryPrincipal();
        List<AuthFunction> list = null;
        if (object instanceof TUser) {
            TUser user = (TUser) object;
            list = functionService.findAll();
        } else if (object instanceof TTeacher) {
            TTeacher teacher = (TTeacher) object;
            if (teacher.getQx().equals("院长")) {
                info.addStringPermission("yz");
            }
            list = functionService.findListByTeacherid(teacher.getId());
        } else if (object instanceof TStudent) {
            TStudent student = (TStudent) object;
            list = functionService.findListByStudentid(student.getId());
        }
        for (AuthFunction function : list) {
            info.addStringPermission(function.getCode());
        }
        return info;
    }

    /**
     * 认证
     *
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        Session session = SecurityUtils.getSubject().getSession();
        String RadioButtonList1 = (String) session.getAttribute("RadioButtonList1");
        UsernamePasswordToken upToken = (UsernamePasswordToken) token;
        String username = upToken.getUsername();
        upToken.setHost(session.getHost());
        if (RadioButtonList1.equals("学生")) {
            TStudent student = studentService.findByUsername(username);
            String password = student.getPassword();
            SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(student, password,
                    this.getClass().getSimpleName());
            return info;
        } else if (RadioButtonList1.equals("教师")) {
            TTeacher teacher = teacherService.findByUsername(username);
            if (teacher != null) {
                String password = teacher.getPassword();
                SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(teacher, password,
                        this.getClass().getSimpleName());
                return info;
            }
        } else if (RadioButtonList1.equals("管理员")) {
            TUser user = userSerivce.findByUsername(username);
            if (user != null) {
                String password = user.getPassword();
                SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, password,
                        this.getClass().getSimpleName());
                return info;
            }
        } else if (RadioButtonList1.equals("教务人员")) {
            TUser user = userSerivce.findByUsername(username);
            if (user != null) {
                String password = user.getPassword();
                SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, password,
                        this.getClass().getSimpleName());
                return info;
            }
        }
        return null;
    }
}
