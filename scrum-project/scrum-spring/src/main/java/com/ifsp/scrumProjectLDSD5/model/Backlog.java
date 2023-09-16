package com.ifsp.scrumProjectLDSD5.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name="BACKLOG")
public class Backlog {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "assignmentDate", nullable = false)
    private LocalDateTime assignmentDate;

    @Column(name = "endDate", nullable = false)
    private LocalDateTime endDate;

}