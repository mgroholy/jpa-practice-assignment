package com.codecool.practicejpa;

import com.codecool.practicejpa.entity.Episode;
import com.codecool.practicejpa.entity.Genre;
import com.codecool.practicejpa.entity.Season;
import com.codecool.practicejpa.entity.Series;
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
    public void episodeRepositorySave_simpleEpisode_findAllReturnsSameEpisode(){
        Episode episode = Episode.builder()
                .title("Episode title")
                .releaseDate(LocalDate.of(2020,1,1))
                .runtime(60)
                .build();
        episodeRepository.save(episode);
        assertEquals(episode, episodeRepository.findAll().get(0));
    }

    @Test
    public void seasonRepositorySave_simpleSeason_findAllReturnsSameSeason(){
        Season season = Season.builder()
                .releaseDate(LocalDate.of(2020,1,1))
                .build();
        seasonRepository.save(season);
        assertEquals(season, seasonRepository.findAll().get(0));
    }

    @Test
    public void seasonRepositorySave_seasonWithEpisode_savesSeasonAndEpisode(){
        Episode episode = Episode.builder()
                .title("Episode title")
                .releaseDate(LocalDate.of(2020,1,1))
                .runtime(60)
                .build();
        Season season = Season.builder()
                .episode(episode)
                .releaseDate(LocalDate.of(2020,1,1))
                .build();

        seasonRepository.save(season);
        assertEquals(season, seasonRepository.findAll().get(0));
        assertEquals(episode, episodeRepository.findAll().get(0));

    }

    @Test
    public void seriesRepositorySave_simpleSeries_findAllReturnsSeries(){
        Series series = Series.builder()
                .genre(Genre.ACTION)
                .title("Series title")
                .build();
        seriesRepository.save(series);
        assertEquals(series, seriesRepository.findAll().get(0));
    }

    @Test
    public void seriesRepositorySave_seriesWithSeasonWithEpisode_savesAll(){
        Episode episode = Episode.builder()
                .title("Episode title")
                .releaseDate(LocalDate.of(2020,1,1))
                .runtime(60)
                .build();
        Season season = Season.builder()
                .episode(episode)
                .releaseDate(LocalDate.of(2020,1,1))
                .build();
        Series series = Series.builder()
                .genre(Genre.ACTION)
                .title("Series title")
                .season(season)
                .build();
        episode.setSeason(season);
        season.setSeries(series);
        seriesRepository.save(series);
        assertEquals(season, seasonRepository.findAll().get(0));
        assertEquals(episode, episodeRepository.findAll().get(0));
        assertEquals(series, seriesRepository.findAll().get(0));

    }

    @Test
    public void episodeRepositoryFindEpisodesByDateBetween_episodeBetweenDates_returnsEpisode(){
        LocalDate from = LocalDate.of(2020,1,1);
        LocalDate to = LocalDate.of(2020,2,1);
        Episode episode = Episode.builder()
                .runtime(30)
                .title("Episode title")
                .releaseDate(LocalDate.of(2020,1,15))
                .build();
        episodeRepository.save(episode);
        assertEquals(episode, episodeRepository.findEpisodesByDateBetween(from, to).get(0));
    }

    @Test
    public void episodeRepositoryFindEpisodesByDateBetween_episodeNotBetweenDates_returnsEpisode(){
        LocalDate from = LocalDate.of(2020,1,1);
        LocalDate to = LocalDate.of(2020,2,1);
        Episode episode = Episode.builder()
                .runtime(30)
                .title("Episode title")
                .releaseDate(LocalDate.of(2021,1,15))
                .build();
        episodeRepository.save(episode);
        assertEquals(0, episodeRepository.findEpisodesByDateBetween(from,to).size());
    }

    @Test
    public void episodeRepositoryFindEpisodesByDateBetween_multipleEpisodeBetweenDates_returnsEpisode(){
        LocalDate from = LocalDate.of(2020,1,1);
        LocalDate to = LocalDate.of(2020,2,1);
        Episode episode1 = Episode.builder()
                .runtime(30)
                .title("Episode title")
                .releaseDate(LocalDate.of(2020,1,15))
                .build();
        Episode episode2 = Episode.builder()
                .runtime(30)
                .title("Episode title")
                .releaseDate(LocalDate.of(2020,1,16))
                .build();

        episodeRepository.save(episode1);
        episodeRepository.save(episode2);
        assertEquals(2, episodeRepository.findEpisodesByDateBetween(from, to).size());
        assertTrue(episodeRepository.findEpisodesByDateBetween(from,to).contains(episode1));
        assertTrue(episodeRepository.findEpisodesByDateBetween(from,to).contains(episode2));


    }


}
