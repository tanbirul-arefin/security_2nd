package com.real.security_second.service;

import com.real.security_second.Repository.userrepository;
import com.real.security_second.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class userservice {
    private final userrepository userrepository;

    public void registeruser( String firstname, String lastname,String username, String password)
    throws Exception{

        User existinguser = userrepository.findByUsername(username);
        if(existinguser!=null){
            throw new Exception("Username already exists");
        }

        User user = new User();
        user.setFirstname(firstname);
        user.setLastname(lastname);
        user.setUsername(username);
        user.setPassword(password);
        user.setRole("USER");


        userrepository.save(user);

    }
}
