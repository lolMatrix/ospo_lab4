package com.lab.bss.fourlab.controller;

import com.lab.bss.fourlab.model.User;
import com.lab.bss.fourlab.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api", produces = "application/json")
public class AuthController {

    private UserService userService;

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/reg", method = RequestMethod.POST)
    private String register(@RequestBody User user) {
        User realUser = userService.getByName(user.getName()); //получаем по имени пользователя
        if (realUser != null) {
            return "registred"; //говорим, что уже зарегистрирован
        }

        userService.save(user);//сохраняем и говорим что успешно
        return "success";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    private String login(@RequestBody User user) {
        User realUser = userService.getByName(user.getName());
        if (realUser == null) {
            return "error login"; // берем пользователя по имени и если не найден говорим что ошибка имени
        }

        // иначе сравниваем пароли, если подходит говорим что успешно иначе говорим что ошибка пароля
        if (realUser.getPassword().equals(user.getPassword())) {
            return "success auth for " + user.getName();
        }
        return "error password";
    }

}
