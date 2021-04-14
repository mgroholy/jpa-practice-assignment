package com.codecool.practicejpa.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Season {

    @Id
    @GeneratedValue
    private Long id;
    private LocalDate releaseDate;

    @Transient
    private int numberOfEpisodes;

    @ManyToOne
    private Series series;

    @Singular
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "season", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private Set<Episode> episodes;

}
