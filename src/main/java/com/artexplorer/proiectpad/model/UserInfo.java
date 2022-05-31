package com.artexplorer.proiectpad.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="user_info")
public class UserInfo {

    @Id
    private Long id;

    private String username;

    @Email
    private String email;

    @Enumerated(EnumType.STRING)
    private enUserRole userRole;
}