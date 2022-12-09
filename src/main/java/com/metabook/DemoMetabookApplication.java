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
        if (roleRepository.findByCode("ROLE_ADMIN")== null) {
            roleRepository.save(Role.builder().code("ROLE_ADMIN").build());
            roleRepository.save(Role.builder().code("ROLE_USER").build());
        }
        if (userRepository.findByEmail("admin@gmail.com") == null) {
            User user = User.builder()
                    .email("admin@gmail.com")
                    .password(passwordEncoder.encode("123"))
                    .role(roleRepository.findByCode("ROLE_ADMIN")).build();
            userRepository.save(user);
            System.out.println(user);
        }

    }


}
