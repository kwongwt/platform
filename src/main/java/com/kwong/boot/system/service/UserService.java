package com.kwong.boot.system.service;

import java.util.List;

import com.kwong.boot.system.model.User;

public interface UserService {

	/**
     *  通过账号获取用户
     */
    public User findByUsername(String username);

}
