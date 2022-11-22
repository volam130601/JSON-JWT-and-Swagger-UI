package com.metabook;

import com.metabook.entity.Role;
import com.metabook.entity.User;
import com.metabook.repository.RoleRepository;
import com.metabook.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class DemoMetabookApplication implements CommandLineRunner {

    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    public static void main(String[] args) {
        SpringApplication.run(DemoMetabookApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        if (userRepository.findAll() == null) {
            User user = new User();
            user.setEmail("admin@gmail.com");
            user.setPassword(passwordEncoder.encode("loda"));
            user.setRole(roleRepository.findById(1L).get());
            userRepository.save(user);
            System.out.println(user);
        }
        if (roleRepository.findAll() == null) {
            roleRepository.save(Role.builder().code("ROLE_ADMIN").build());
            roleRepository.save(Role.builder().code("ROLE_USER").build());
        }
    }
}
