package com.metabook.service.user;

import com.metabook.entity.User;
import com.metabook.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByEmail(username);
//        System.out.println(user.getRole().getCode());
        if (user == null) throw new UsernameNotFoundException("Username cannot found : " + username);
        return new CustomerUserDetails(user);
    }
}
