package com.project.model;

import com.project.model.enums.RoleCode;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_role")
    private Integer id;

    //TODO: unique true?
    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private RoleCode role;

    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL)
    private List<User> users;

}


