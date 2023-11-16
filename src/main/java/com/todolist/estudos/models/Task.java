package com.todolist.estudos.models;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table
@Getter
@Setter
public class Task {
    @Id
    @GeneratedValue
    @Column
    private Long id;
    @Column
    private String title;
    @Column
    private String description;
    @Column
    private boolean completed;

}

