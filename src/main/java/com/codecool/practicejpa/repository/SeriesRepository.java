package com.codecool.practicejpa.repository;

import com.codecool.practicejpa.entity.Series;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeriesRepository extends JpaRepository<Series, Long> {
}
