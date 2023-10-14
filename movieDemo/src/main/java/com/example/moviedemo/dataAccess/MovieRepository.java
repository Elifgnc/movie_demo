package com.example.moviedemo.dataAccess;

import com.example.moviedemo.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

    static Movie findById(Class<Movie> movieClass, Long movieId) {
        return null;
    }

    // SEARCH İŞLEMİ: Title
    @Query("SELECT m from Movie m where m.title like %?1%")
    List<Movie> search(String keyword);


    //FİLTER İŞLEMİ: -imdbRating, Type, Director (dynamic)
    // @Query("select m from Movie m where m.imdbRating like %?1%" + " or m.type like %?1%" + " or m.director like %?1%")
    // List<Movie> filter(String keyword);
  /*  @Query("select m from Movie m where m.imdbRating like %?1%" + " or m.type like %?1%" + " or m.director like %?1%")
    List<Movie> filter1(String Type);

    @Query("select m from Movie m where m.imdbRating like %?1%" + " or m.type like %?1%" + " or m.director like %?1%")
    List<Movie> filter2(String imdbRating);*/

    List<Movie> findMoviesByTypeContainsIgnoreCaseAndDirectorContainingIgnoreCaseAndImdbRatingBetween(String type, String Director, float ImdbRatingMin, float ImdbRatingMax);

    //List<Movie> findMoviesByTypeContainsIgnoreCaseAndDirectorContainsIgnoreCase(String type, String director);
    //List<Movie> findMoviesByImdbRatingBetween(float imdbRatingMin, float imdbRatingMax);

    //Iterable<Movie> filter(String keyword);
    //Movie getById(long id);


    List<Movie> findMoviesByImdbRatingBetween(float imdbRatingMin,float imdbRatingMax);

    List<Movie> findByTypeLikeIgnoreCase(String type);

    List<Movie> findByDirectorLikeIgnoreCase(String director);
}




