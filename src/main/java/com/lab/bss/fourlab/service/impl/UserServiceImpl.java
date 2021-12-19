package com.lab.bss.fourlab.service.impl;

import com.lab.bss.fourlab.model.User;
import com.lab.bss.fourlab.service.UserService;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private List<User> userList = new ArrayList<>();

    @Override
    public void save(@NonNull User user) {
        userList.add(user);
    }

    @Override
    public User getByName(String name) {
        return userList.stream()
                .filter(x -> x.getName().equals(name))
                .findAny().orElse(null);
    }
}
