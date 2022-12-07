package com.example.backend.service.impl;


import com.example.backend.Tools.JwtUtil;
import com.example.backend.common.Result;
import com.example.backend.dto.UserDTO;
import com.example.backend.entity.User;
import com.example.backend.mapper.UserMapper;
import com.example.backend.service.UserService;
import org.apache.ibatis.jdbc.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Base64;
import java.util.List;
import java.util.Map;

/**
 * @Author hym
 * @Date 2022/10/29
 * @Description demo
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    JavaMailSenderImpl mailSender;

    @Override
    public List<User> findAllUser() {
        return userMapper.selectAllUser();
    }

    public User findUser(Long id){
        User user = userMapper.selectById(id);//利用mybatis-plus的单表查询，自己不用写SQL语句
        //没有找到用户，返回错误码
        if(user == null){
            return null;
        }

        //找到用户，返回正确信息
        return user;
    }
    public Result<String> confirmUser(String email,String password){
        User user=userMapper.selectByEmail(email);
        if (user==null){
            return Result.fail(10001,"用户不存在");
        }
        else if (!user.getPassword().equals(password)){
            return Result.fail(10001,"密码错误");
        }
        String userId=user.getId().toString();
        String token = JwtUtil.sign(userId);
        return Result.success(token);
    }
    public Result<String> registerUser(String email,String password,String name){
        User user=userMapper.selectByEmail(email);
        if (user!=null){
            return Result.fail(10001,"用户已验证");
        }
        if(userMapper.insertUser(name,email,password)>0){
            user=userMapper.selectByEmail(email);
            String userId=user.getId().toString();
            String token = JwtUtil.sign(userId);
            return Result.success("成功验证！");
        }

        return Result.fail(10001,"验证失败");
    }
    public Result<User> putUser(Long id,String name,String introduce,int age,String imgUrl){
        int i=userMapper.updateUser(id,name,age,introduce,imgUrl);
        if (i>0){
            return Result.success(userMapper.selectById(id));
        }
        else{
            return Result.fail(10001,"更新失败！");
        }
    }
    public Result<String> email(User user){
        if (userMapper.selectByEmail(user.getEmail())!=null){
            return Result.fail(10001,"已经注册了!");
        }
        System.out.println(user);
        Base64.Encoder encoder = Base64.getEncoder();
        String str=user.getName()+"/"+user.getPassword()+"/"+System.currentTimeMillis();
        String code=encoder.encodeToString(str.getBytes());
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper;
        try {
            helper = new MimeMessageHelper(mimeMessage, true);

            helper.setSubject("济时:请验证您的邮箱");

            helper.setText("尊敬的" + user.getName() + "您好！\n" + "请点击下方链接验证您的邮箱:\n" + "        http://localhost:8081/api/user/email?user=" + user.getEmail() + "&code=" + code);
            helper.setTo(user.getEmail());
            helper.setFrom("417695971@qq.com");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
        mailSender.send(mimeMessage);
        return Result.success("成功发送邮箱");
    }

}
