package com.tutorial.readreplica.service;

import com.tutorial.readreplica.model.User;
import com.tutorial.readreplica.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Transactional( propagation = Propagation.REQUIRED )
    public User save( User user ){
        return userRepository.save( user );
    }

    @Transactional( propagation = Propagation.REQUIRED, readOnly = true )
    public List<User> getAllUser(){
        return userRepository.findAll();
    }
}
