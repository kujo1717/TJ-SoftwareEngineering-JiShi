package com.example.backend.controller;


import com.example.backend.Tools.JwtUtil;
import com.example.backend.common.Result;
import com.example.backend.dto.UserDTO;
import com.example.backend.entity.User;
import com.example.backend.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.swing.text.html.HTML;
import java.io.File;
import java.io.IOException;
import java.util.Base64;
import java.util.UUID;

/**
 * @Author hym
 * @Date 2022/10/29
 * @Description demo
 */
@Api(tags = {"User"})
@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    JavaMailSenderImpl mailSender;
    JwtUtil jwtUtil;
    @ApiOperation("根据id返回指定用户")
    @GetMapping("")
    public Result<User> findUser(){
        //System.out.println(jwtUtil.getUserId(request.getHeader("token")));
        Long userid = Long.valueOf(jwtUtil.getUserId(request.getHeader("token")));
        return userService.findUser(userid);
    }
    @GetMapping("logout")
    public Result<String> logout(){
        return Result.success("success");
    }
    @PostMapping("login")
    public Result<String> login(@RequestBody User user){
        //System.out.println("111");
        return userService.confirmUser(user.getEmail(),user.getPassword());
    }
    @GetMapping("email")
    public Result<String> email(@RequestParam("user")String user, @RequestParam("code")String code){

        Base64.Decoder decoder = Base64.getDecoder();
        String str=new String(decoder.decode(code));
        String[] strings = str.split("/");
        if (Long.valueOf(strings[2])-System.currentTimeMillis()>60*60*24){
            return Result.fail(10001,"验证超时了!");
        }
        return userService.registerUser(user,strings[1],strings[0]);

    }
    @PostMapping("register")
    public Result<String> register(@RequestBody User user) throws MessagingException {


        return userService.email(user);
        //return userService.registerUser(user.getEmail(),user.getPassword(),user.getName());
    }
    @PutMapping("")
    @ResponseBody
    public Result<User> updateUser( UserDTO user){
        Long userid = Long.valueOf(jwtUtil.getUserId(request.getHeader("token")));
        String imgUrl;
        System.out.println(user);
        ApplicationHome applicationHome = new ApplicationHome(this.getClass());
        String path = applicationHome.getDir().getParentFile()
                .getParentFile().getAbsolutePath() + "\\src\\main\\resources\\static";
        if (user.getAvatarFile()!=null) {

            var file = user.getAvatarFile();
            String fileName = UUID.randomUUID() + "." + file.getContentType()
                    .substring(file.getContentType().lastIndexOf("/") + 1);
            try {
                // 获取保存路径

                File files = new File(path+'/'+userid, fileName);
                File parentFile = files.getParentFile();
                if (!parentFile.exists()) {
                    parentFile.mkdir();
                }
                file.transferTo(files);
            } catch (IOException e) {
                e.printStackTrace();
            }
            imgUrl="http://localhost:8081/api/static/"+userid+"/"+fileName;
        }
        else {
            imgUrl="http://localhost:8081/api/static/th.jpg";
        }
        //System.out.println(imgUrl);
        return userService.putUser(userid,user.getName(),user.getIntroduce(),user.getAge(),imgUrl);
    }
}
