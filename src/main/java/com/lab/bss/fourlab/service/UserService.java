package com.lab.bss.fourlab.service;

import com.lab.bss.fourlab.model.User;
import org.springframework.lang.Nullable;

public interface UserService {

    void save(User user);

    @Nullable
    User getByName(String name);

}
