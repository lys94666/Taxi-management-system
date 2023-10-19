package com.example.Controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.Service.UserService;
import com.example.common.R;
import com.example.entity.User;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@CrossOrigin
@RestController
@ResponseBody
@RequestMapping("/login")
@Slf4j
public class loginController {

    @Autowired
    private UserService userService;

    @PostMapping
    public R loginCheck(HttpServletRequest request, User user) {
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(User::getUsername, DigestUtils.md5DigestAsHex(user.getUsername().getBytes()));
        User one = userService.getOne(lambdaQueryWrapper);
        if (one == null) {
            return R.error("用户不存在");
        } else {
            if (user.getPassword().equals(one.getPassword())) {
                log.info("{}", one.getUsername());
                log.info("-----------");
                System.out.println(request.getSession().getId());
                log.info("-----------");
                request.getSession().setAttribute("user", one.getUsername());
                log.info("{设置成功}");
                return R.success("登陆成功！");
            } else {
                return R.error("用户名或密码错误！");
            }
        }
    }

    @PostMapping("/save")
    public R setUser(@RequestBody User user) {
        // username 转为md5 加密 防止sql注入 然后进行保存
        String password = user.getPassword();
        String ID = UUID.randomUUID().toString();
        user.setUsername(DigestUtils.md5DigestAsHex((user.getUsername()).getBytes()));
        userService.save(user);
        return R.success("保存成功");
    }


}
