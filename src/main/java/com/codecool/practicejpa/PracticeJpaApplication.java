package com.codecool.practicejpa;

import com.codecool.practicejpa.entity.Episode;
import com.codecool.practicejpa.entity.Genre;
import com.codecool.practicejpa.entity.Season;
import com.codecool.practicejpa.entity.Series;
import com.codecool.practicejpa.repository.SeriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

@SpringBootApplication
public class PracticeJpaApplication {


    private SeriesRepository seriesRepository;

    @Autowired
    public PracticeJpaApplication(SeriesRepository seriesRepository) {
        this.seriesRepository = seriesRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(PracticeJpaApplication.class, args);
    }

    @Bean
    public CommandLineRunner init(){
        return args -> {
            Episode episode1 = Episode.builder()
                    .releaseDate(LocalDate.of(2007,6,17))
                    .title("Sally")
                    .runtime(30)
                    .build();
            Episode episode2 = Episode.builder()
                    .releaseDate(LocalDate.of(2007,6,24))
                    .title("Bret Gives Up the Dream")
                    .runtime(30)
                    .build();
            Episode episode3 = Episode.builder()
                    .releaseDate(LocalDate.of(2007,7,1))
                    .title("Mugged")
                    .runtime(30)
                    .build();
            Episode episode4 = Episode.builder()
                    .releaseDate(LocalDate.of(2007,7,8))
                    .title("Yoko")
                    .runtime(30)
                    .build();
            Episode episode5 = Episode.builder()
                    .releaseDate(LocalDate.of(2007,7,15))
                    .title("Sally Returns")
                    .runtime(30)
                    .build();
            Episode episode6 = Episode.builder()
                    .releaseDate(LocalDate.of(2007,7,22))
                    .title("Bowie")
                    .runtime(30)
                    .build();
            Episode episode7 = Episode.builder()
                    .releaseDate(LocalDate.of(2007,7,29))
                    .title("Drive By")
                    .runtime(30)
                    .build();
            Episode episode8 = Episode.builder()
                    .releaseDate(LocalDate.of(2007,8,5))
                    .title("Girlfriends")
                    .runtime(30)
                    .build();
            Episode episode9 = Episode.builder()
                    .releaseDate(LocalDate.of(2007,8,12))
                    .title("What Goes on Tour")
                    .runtime(30)
                    .build();
            Episode episode10 = Episode.builder()
                    .releaseDate(LocalDate.of(2007,8,19))
                    .title("New Fans")
                    .runtime(30)
                    .build();
            Episode episode11 = Episode.builder()
                    .releaseDate(LocalDate.of(2007,8,26))
                    .title("The Actor")
                    .runtime(30)
                    .build();
            Episode episode12 = Episode.builder()
                    .releaseDate(LocalDate.of(2007,9,2))
                    .title("The Third Conchord")
                    .runtime(30)
                    .build();
            Episode episode13 = Episode.builder()
                    .releaseDate(LocalDate.of(2009,1,18))
                    .title("A Good Opportunity")
                    .runtime(30)
                    .build();
            Episode episode14 = Episode.builder()
                    .releaseDate(LocalDate.of(2009,1,25))
                    .title("The New Cup")
                    .runtime(30)
                    .build();
            Episode episode15 = Episode.builder()
                    .releaseDate(LocalDate.of(2009,2,1))
                    .title("The Tough Brets")
                    .runtime(30)
                    .build();
            Episode episode16 = Episode.builder()
                    .releaseDate(LocalDate.of(2009,2,8))
                    .title("Murray Takes It to the Next Level")
                    .runtime(30)
                    .build();
            Episode episode17 = Episode.builder()
                    .releaseDate(LocalDate.of(2009,2,15))
                    .title("Unnatural Love")
                    .runtime(30)
                    .build();
            Episode episode18 = Episode.builder()
                    .releaseDate(LocalDate.of(2009,2,22))
                    .title("Love Is a Weapon of Choice")
                    .runtime(30)
                    .build();
            Episode episode19 = Episode.builder()
                    .releaseDate(LocalDate.of(2009,3,1))
                    .title("Prime Minister")
                    .runtime(30)
                    .build();
            Episode episode20 = Episode.builder()
                    .releaseDate(LocalDate.of(2009,3,8))
                    .title("New Zealand Town")
                    .runtime(30)
                    .build();
            Episode episode21 = Episode.builder()
                    .releaseDate(LocalDate.of(2009,3,15))
                    .title("Wingmen")
                    .runtime(30)
                    .build();
            Episode episode22 = Episode.builder()
                    .releaseDate(LocalDate.of(2009,3,22))
                    .title("Evicted")
                    .runtime(30)
                    .build();
            Episode[] seasonOneEpisodes = {episode1, episode2,episode3,episode4,episode5,episode6,episode7,episode8,episode9,episode10,episode11,episode12};
            Episode[] seasonTwoEpisodes = {episode13, episode14, episode15, episode16, episode17, episode18,episode19,episode20,episode21,episode22};


            Season season1 = Season.builder()
                    .releaseDate(LocalDate.of(2007,6,17))
                    .episodes(Arrays.stream(seasonOneEpisodes).collect(Collectors.toSet()))
                    .numberOfEpisodes(seasonOneEpisodes.length)
                    .build();

            Arrays.stream(seasonOneEpisodes).forEach(episode -> episode.setSeason(season1));

            Season season2 = Season.builder()
                    .releaseDate(LocalDate.of(2009,1,18))
                    .episodes(Arrays.stream(seasonTwoEpisodes).collect(Collectors.toSet()))
                    .numberOfEpisodes(seasonTwoEpisodes.length)
                    .build();
            Arrays.stream(seasonTwoEpisodes).forEach(episode -> episode.setSeason(season2));

            Series flightOfTheConchords = Series.builder()
                    .title("Flight of the Conchords")
                    .genre(Genre.COMEDY)
                    .actor("Jemaine Clement")
                    .actor("Bret McKenzie")
                    .actor("Rhys Darby")
                    .actor("Kristen Schaal")
                    .season(season1)
                    .season(season2)
                    .build();
            season1.setSeries(flightOfTheConchords);
            season2.setSeries(flightOfTheConchords);

            seriesRepository.save(flightOfTheConchords);
        };
    }

}
