package com.metabook;

import com.metabook.entity.User;
import com.metabook.repository.RoleRepository;
import com.metabook.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableJpaAuditing
@SpringBootApplication
public class DemoMetabookApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(DemoMetabookApplication.class, args);
    }

    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

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
    }
}
