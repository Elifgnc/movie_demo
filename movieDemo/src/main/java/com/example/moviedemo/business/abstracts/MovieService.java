package com.example.moviedemo.business.abstracts;

import com.example.moviedemo.dataAccess.MovieRepository;
import com.example.moviedemo.entity.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MovieService {


    static Object getType() {
        return getType();
    }

    static Object getDirector() {
        return getDirector();
    }

    static Object getimdbRatingMin() {
        return getimdbRatingMin();
    }

    static Object getimdbRatingMax() {
        return getimdbRatingMax();
    }

    static Object getMovie() {
        return getMovie();
    }

    public Iterable<Movie> getAllMovies();
    public Movie save(Movie movie);
    public void save(List<Movie> movies);
    public void deleteMovie(Long id);
    public Movie updateMovie(Long id, Movie movie);
    public Iterable<Movie> search(String keyword);

   // Iterable<Movie> filter(String keyword);
    //Iterable<Movie> filter1(String Type);
  //Iterable<Movie> filter2(String imdbRating);

  //  Iterable<Movie> filter3(String type, String director);

    //Iterable<Movie> filter4(float imdbRatingMin, float imdbRattingMax);

    Iterable<Movie> filter5(String type, String director,float imdbRatingMin, float imdbRattingMax);

    // Get movie by keyword
    public List<Movie> findByKeyword(String keyword);


    List<Movie> findByKeyword(float imdbRatingMin, float imdbRatingMax);


    List<Movie> findByKeyword1(String keyword);

    List<Movie> findByKeyword2(String keyword);
}


    //public Movie getMovieById(Long id);
