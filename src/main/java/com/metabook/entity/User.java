package com.metabook.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "users")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String password;
    private Integer phoneNumber;
    private String firstName;
    private String lastName;
    private String fullName;
    private Date birthDay;
    private int gender;
    @ManyToOne
    @JoinColumn(name = "role_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Role role;

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public String getGender() {
        if (gender == 0) return "Male";
        else if (gender == 1) return "Female";
        return "Diff";
    }
}
