package com.zxw.jwxt.controller;


import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zxw.common.pojo.RS;
import com.zxw.common.pojo.TableResponse;
import com.zxw.jwxt.domain.TUser;
import com.zxw.jwxt.domain.UserRealm;
import com.zxw.jwxt.dto.UpdatePassDTO;
import com.zxw.jwxt.service.UserService;
import com.zxw.jwxt.vo.BaseQueryParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author zxw
 * @since 2023-11-07
 */
@RestController
@RequestMapping("/api/user")
public class TUserController extends BaseController {
    @Autowired
    private UserService userService;
    @Value("${filepath}")
    private String filepath;

    @GetMapping("/pageQuery")
    public TableResponse pageQuery(BaseQueryParam baseQueryParam) {
        IPage iPage = userService.pageQuery(baseQueryParam);
        TableResponse of = TableResponse.of(iPage);
        return of;
    }

    @PostMapping("/add")
    public RS saveOrUpdate(@RequestBody TUser user) {
        RS rs = userService.saveOrUpdate(user);
        return rs;
    }

    @PutMapping("/edit")
    public RS edit(@RequestBody TUser user){
        RS  rs = userService.edit(user);
        return rs;
    }

    @DeleteMapping("/delete")
    public RS delete(String id) {
        RS rs = userService.lock(id);
        return rs;
    }

    @PostMapping("/updatePass")
    public RS updatePass(@RequestBody UpdatePassDTO updatePassDTO){
        UserRealm realm = getRealm();
        TUser tUser = new TUser();
        TUser byUsername = userService.findByUsername(realm.getId());
        if(byUsername != null && byUsername.getPassword().equals(updatePassDTO.getOldPass())){
            tUser.setId(realm.getId());
            tUser.setPassword(updatePassDTO.getNewPass());
            RS rs = userService.edit(tUser);
            return rs;
        }
        return RS.ok();
    }

    /**
     * 文件上传接口
     */
    @CrossOrigin
    @PostMapping("/updateAvatar")
    public RS upload(@RequestPart(value = "file") MultipartFile file) throws IOException {  //MultipartFile前面可加@RequestPart("设置的KEY"),required = false非必须传参
        //接收文件的文件名
        String originalFilename = file.getOriginalFilename();
        //定义唯一标识.uuid
        String flag = IdUtil.fastSimpleUUID();
        //获取当前路径并且拼接文件名
//        System.out.println(System.getProperty("user.dir"));
        String rootFilePath = filepath + "/";
        String s = FileUtil.extName(file.getOriginalFilename());
        //利用hutool工具类直接些写入文件到本地
        //FileUtil.writeBytes(file.getBytes(), rootFilePath);
        File desc = new File(filepath + File.separator + flag + "." +s);

        if (!desc.exists())
        {
            if (!desc.getParentFile().exists())
            {
                desc.getParentFile().mkdirs();
            }
        }
        file.transferTo(desc);
        // 文件上传成功的操作，可以不加
        //sysFileService.doUploadSuccess(file,flag,filePath);
        String resUrl = "/images/" + flag + "." + s;
        UserRealm realm = getRealm();
        TUser tUser = new TUser();
        TUser byUsername = userService.findByUsername(realm.getId());
        if(byUsername != null ){
            tUser.setId(realm.getId());
            tUser.setAvatar(resUrl);
            userService.edit(tUser);
        }
        return RS.ok(resUrl);  // 返回结果 url
    }

}
