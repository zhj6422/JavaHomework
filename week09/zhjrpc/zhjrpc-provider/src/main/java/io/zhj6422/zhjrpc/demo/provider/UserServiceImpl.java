package io.zhj6422.zhjrpc.demo.provider;

import io.zhj6422.zhjrpc.demo.api.User;
import io.zhj6422.zhjrpc.demo.api.UserService;


public class UserServiceImpl implements UserService {

  @Override
  public User findById(int id) {
    return new User(id, "User" + System.currentTimeMillis());
  }
}

