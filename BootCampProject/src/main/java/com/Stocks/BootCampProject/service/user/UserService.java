package com.Stocks.BootCampProject.service.user;

import com.Stocks.BootCampProject.entity.User;
import com.Stocks.BootCampProject.error.UserNotFoundException;

public interface UserService {
   public User saveUser(User user);

    public User fetchUserById(int userId) throws UserNotFoundException;
}
