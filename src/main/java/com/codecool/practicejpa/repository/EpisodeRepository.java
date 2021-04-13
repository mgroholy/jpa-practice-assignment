package com.codecool.practicejpa.repository;

import com.codecool.practicejpa.entity.Episode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface EpisodeRepository extends JpaRepository<Episode, Long> {

    @Query("SELECT e FROM Episode e WHERE e.releaseDate BETWEEN :from AND :to")
    List<Episode> findEpisodesByDateBetween(LocalDate from, LocalDate to);

}
