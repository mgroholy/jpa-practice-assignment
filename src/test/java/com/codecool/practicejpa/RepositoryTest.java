package com.codecool.practicejpa;

import com.codecool.practicejpa.entity.Episode;
import com.codecool.practicejpa.repository.EpisodeRepository;
import com.codecool.practicejpa.repository.SeasonRepository;
import com.codecool.practicejpa.repository.SeriesRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
public class RepositoryTest {

    @Autowired
    private EpisodeRepository episodeRepository;

    @Autowired
    private SeasonRepository seasonRepository;

    @Autowired
    private SeriesRepository seriesRepository;

    @Test
    public void episodeRepositorySavesThenReturnsSameEpisode(){
        Episode episode = Episode.builder()
                .title("Episode title")
                .releaseDate(LocalDate.of(2020,1,1))
                .runtime(60)
                .build();
        episodeRepository.save(episode);
        assertEquals(episode, episodeRepository.findAll().get(0));
    }


}
