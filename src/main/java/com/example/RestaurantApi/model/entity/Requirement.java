package com.example.RestaurantApi.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Requirement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int requirementId;

    @Column
    private String address;

    @Column
    private String phoneNumber;

    @Column
    private String restaurantName;

    @Column
    private LocalDateTime createDate;

    @Column
    private LocalDateTime updateDate;

}
