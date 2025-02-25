package com.socialmedia.service.implementation;

import com.socialmedia.modal.MyUserDetails;
import com.socialmedia.modal.Users;
import com.socialmedia.repository.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyOwnUserDetailService implements UserDetailsService {

    @Autowired
    private UsersRepo usersRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<Users> users = usersRepo.findUserByEmail(username);

        if (users.isEmpty()){
            System.out.println("User not Found");
            throw new UsernameNotFoundException("User Not Found");
        }

        return new MyUserDetails(users.get(0));
    }
}
