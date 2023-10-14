package com.example.moviedemo.entity;

import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@DynamicInsert
@DynamicUpdate
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    public String title;
    public Boolean comingSoon;
    @Column(name = "_year") // DB de bu şekilde tutar.
    public String year;
    public String rated;
    public String released;
    public String runtime;
    public String genre;
    public String director;
    public String writer;
    public String actors;
    public String plot;
    public String language;
    public String country;
    public String awards;
    public String poster;
    public String metascore;
    public float imdbRating;
    public String imdbVotes;
    public String imdbID;
    public String type;
    public String totalSeasons;
}
