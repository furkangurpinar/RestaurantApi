package com.example.RestaurantApi.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Entity
@Table(name="users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;

    @Column
    private String userName;

    @Column
    private String userPhoneNumber;

    @Column
    private String userMail;

    @Column
    private String userPassword;
}
