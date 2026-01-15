package com;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Table(name="table_1")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
}
