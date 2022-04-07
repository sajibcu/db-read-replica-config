package com.tutorial.readreplica.we.rest;

import com.tutorial.readreplica.model.User;
import com.tutorial.readreplica.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Random;

@RestController
public class UserResource {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<User> getUsers( ) {
        return userService.getAllUser();
    }

    @PostMapping( value = "/user", produces = MediaType.APPLICATION_JSON_VALUE )
    public User addNewUser( ) {
        User user = new User();
        Random random = new Random();
        user.setName( "User " + random.nextInt( 1000 ) );
        user.setAge( random.nextInt( 100 ) );
        user.setEmail( "email"+random.nextInt( 1000 )+"@gmail.com" );
        return userService.save( user );
    }

}
