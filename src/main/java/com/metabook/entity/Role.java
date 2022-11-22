package com.metabook.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String code;

    @OneToMany(mappedBy = "role")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<User> userList;
}
