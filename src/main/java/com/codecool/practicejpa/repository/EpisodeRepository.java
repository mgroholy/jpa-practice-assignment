package com.codecool.practicejpa.repository;

import com.codecool.practicejpa.entity.Episode;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EpisodeRepository extends JpaRepository<Episode, Long> {
}
