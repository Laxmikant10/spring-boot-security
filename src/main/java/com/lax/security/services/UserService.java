package com.lax.security.services;

import com.lax.security.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    List<User> list = new ArrayList<>();

    public UserService(){
        /*list.add(new User("abc","abc","abc@gmail.com","NORMAL"));
        list.add(new User("xyz","xyz","xyz@gmail.com","ADMIN"));*/
    }

    //get all users
    public List<User> getAllUser(){
        return this.list;
    }

    //get Single user
    public User getSingleUser(String username){
        return this.list.stream().filter(user -> user.getUserName().equals(username)).findAny().get();
    }

    //Add new user
    public User addUser(User user){
        this.list.add(user);
        return user;
    }
}
