package com.example.Edu.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Edu.backend.model.User;
import com.example.Edu.backend.repository.UserRepository;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;

    public void save(User user) {
        userRepository.save(user);
    }

    public List <User> getAll (){
        return userRepository.findAll();
    }

    public User getById(Integer id){
        return userRepository.findById(id).get();
    }

    public void delete(Integer id){
        userRepository.deleteById(id);
    }

}
