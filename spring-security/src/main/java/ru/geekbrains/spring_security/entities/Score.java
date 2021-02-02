package ru.geekbrains.spring_security.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "scores")
public class Score {
    @Id
    @Column(name = "user_id")
    private Long id;

    @Column(name = "score")
    private Integer score;

    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id")
    private User user;

}
