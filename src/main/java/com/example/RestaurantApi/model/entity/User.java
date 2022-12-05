package com.example.RestaurantApi.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Data
@Builder
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

    @Column
    private LocalDateTime createDate;

    @Column
    private LocalDateTime updateDate;
}
