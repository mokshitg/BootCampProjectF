package com.Stocks.BootCampProject.service.user;

import com.Stocks.BootCampProject.entity.User;
import com.Stocks.BootCampProject.error.UserNotFoundException;
import com.Stocks.BootCampProject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImp implements UserService{

    @Autowired
    private UserRepository userRepository;
    @Override
    public User saveUser(User user) {


        return userRepository.save(user);
    }

    @Override
    public User fetchUserById(int userId) throws UserNotFoundException {

        Optional<User> user = userRepository.findById(userId);
        if(!user.isPresent()){
            throw new UserNotFoundException("User Account Not Found");
        }

        return user.get();
    }
}
