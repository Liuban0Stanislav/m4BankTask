package com.lyuban.m4banktask.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Model {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //генерируемое значение
    private Integer id;
    @Column(name = "name", unique = true, nullable = false)          //столбец должен содержать уникальное значение, не может быть null
    private String name;
    @Column(name = "`value`")
    private int value;
}
