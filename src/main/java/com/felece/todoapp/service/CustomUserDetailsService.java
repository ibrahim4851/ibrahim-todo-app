package com.felece.todoapp.service;

import com.felece.todoapp.CustomUserDetails;
import com.felece.todoapp.dto.UserDto;
import com.felece.todoapp.entity.User;
import com.felece.todoapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    public List<User> findAll(){
        return userRepository.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByUsername(username);
        if(user ==null) {
            throw new UsernameNotFoundException("User Not Found");
        }
        return new CustomUserDetails(user);
    }

    public User addUser(UserDto dto){
        User user = new User();
        user.setUsername(dto.getUserName());
        user.setPassword(dto.getPassword());
        user.setRole(dto.getRole());
        user = userRepository.save(user);
        return user;
    }

    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }

    public User updateUser(Long id){
        User user = userRepository.findById(id).get();
        user.setRole("ADMIN");
        return userRepository.save(user);
    }

}