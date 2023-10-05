package com.ifsp.model;

import jakarta.persistence.*;
import com.ifsp.enumeration.Status;
import lombok.Getter;

import lombok.Setter;


@Entity
@Getter
@Setter
@Table(name="SPRINT")

public class Sprint {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "BIGINT")
    private Long id;

    @Column(name =  "TITLE")
    private String title;


    @Column(name = "STATUS")
    @Enumerated(EnumType.ORDINAL)
    private Status status;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "backlog_id")
    private Backlog backlog;

    @Column(name = "DESCRIPTION")
    private String description;


    public static Object builder(){
        return null;
    }


}
