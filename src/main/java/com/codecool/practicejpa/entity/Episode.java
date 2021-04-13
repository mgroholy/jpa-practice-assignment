package com.codecool.practicejpa.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Episode {

    @Id
    @GeneratedValue
    private Long id;

    private String title;
    private int runtime;
    private LocalDate releaseDate;

    @EqualsAndHashCode.Exclude
    @ManyToOne
    private Season season;
}
