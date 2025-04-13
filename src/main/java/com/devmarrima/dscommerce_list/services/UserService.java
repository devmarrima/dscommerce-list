package com.devmarrima.dscommerce_list.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.devmarrima.dscommerce_list.entities.Role;
import com.devmarrima.dscommerce_list.entities.User;
import com.devmarrima.dscommerce_list.projections.UserDetailsProjection;
import com.devmarrima.dscommerce_list.repositories.UserRepository;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<UserDetailsProjection> projections = userRepository.findByEmail(username);
        if (projections.size() == 0) {
            throw new UsernameNotFoundException("User not found");
        }
        User user = new User();
        user.setEmail(username);
        user.setPassword(projections.get(0).getPassword());
        for (UserDetailsProjection list : projections) {
            user.addRole(new Role(list.getRoleId(), list.getAuthority()));

        }
        return user;
    }
}
