package com.codecool.practicejpa.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Series {

    @Id
    @GeneratedValue
    private Long id;
    private String title;

    @Transient
    private int numberOfSeasons;

    @Singular
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "series", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private Set<Season> seasons;

    @ElementCollection
    private Set<String> actors;

    @Enumerated(EnumType.STRING)
    private Genre genre;
}
