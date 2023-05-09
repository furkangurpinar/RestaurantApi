package com.example.RestaurantApi.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Province {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int provinceId;

    @Column
    private String provinceName;

    @Column
    private LocalDateTime createDate;

    @Column
    private LocalDateTime updateDate;

}
