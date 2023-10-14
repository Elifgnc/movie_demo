package com.example.moviedemo;

import com.example.moviedemo.business.concretes.MovieManager;
import com.example.moviedemo.dataAccess.MovieRepository;
import com.example.moviedemo.entity.Movie;
import jakarta.persistence.Id;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.annotation.Rollback;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MovieRepositoryTest {
    @Mock
    private MovieRepository movieRepository;
    @Mock
    private MovieManager movieManager;

    private Movie movie;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        this.movieManager = new MovieManager(this.movieRepository);
        movie = Movie.builder().title("Luke Cage").comingSoon(true).year("2016-").rated("TV-MA").released("30 Sep 2016").runtime("55 min").genre("Action, Crime, Drama")
                .director("N/A").writer("Cheo Hodari Coker").actors("Mahershala Ali, Mike Colter, Frankie Faison, Erik LaRay Harvey").plot("Given superstrength and durability by a sabotaged experiment, a wrongly accused man escapes prison to become a superhero for hire.")
                .language("English").country("USA").awards("N/A").poster("http://ia.media-imdb.com/images/M/MV5BMTcyMzc1MjI5MF5BMl5BanBnXkFtZTgwMzE4ODY2OTE@._V1_SX300.jpg")
                .metascore("N/A").imdbRating(7).imdbVotes("N/A").imdbID("tt3322314").type("series").totalSeasons("1").build();
    }
    @Test
    public void getMovies() {
        movieManager.getAllMovies();
        verify(movieRepository).findAll();
    }
    @Test
    public void saveTest() {
        movieManager.save(movie);
        verify(movieRepository).save(movie);
    }
    @Test
    public void searchTest() {
        movieManager.save(movie);
        String keyword = "Luke Cage";
        movieManager.search(keyword);
        verify(movieRepository).search(keyword);

        //verify(movieRepository).search(keyword); ifadesi, Mockito isimli bir Java kütüphanesi kullanılarak gerçekleştirilen bir test sınamasıdır.
        // Bu ifade, belirli bir metodun belirli bir parametre değeriyle
        // çağrılıp çağrılmadığını doğrulamak için kullanılır.
    }


    @Test
    public void filter5Test() {
        String type = "movie";
        String director = "Zack Snyder";
        float imdbRatingMin = 5;
        float imdbRatingMax = 8 ;
        movieRepository.save(movie);
        movieManager.filter5(type,director,imdbRatingMin,imdbRatingMax);
        verify(movieRepository).findMoviesByTypeContainsIgnoreCaseAndDirectorContainingIgnoreCaseAndImdbRatingBetween(type,director,imdbRatingMin,imdbRatingMax);
    }


    @Test
    public void updateMovie() {
        String title = "Breaking Bad";
        System.out.println(movie.getTitle());
        movie.setTitle(title);
        try {
            org.assertj.core.api.Assertions.assertThat(movie.getTitle()).isEqualTo(title);
            // assertThat: içindeki değerleri karşılaştırmayı sağlıyor.
            // org.assertj.core.api.Assertions assertThat'i aktif hale getirmek için kullanılır.
            System.out.println(movie.getTitle());

        } catch (Exception e) {
            System.out.println("No value!!!");
        }

    }
    /*
    @Test
    public void deleteMovie() {
        movieManager.save(movie);
        Long id = movie.getId();
        System.out.println(id);
        //movieManager.deleteMovie(id);
        boolean isIdExistLaterDelete = movieRepository.findById(id).isPresent();
        System.out.println(isIdExistLaterDelete);

        // Eğer film hala varsa (isPresent() true dönerse),
        // silme işlemi başarısız olmuş demektir.


        // içindeki değer false ise true döner ve testten geçer.
    }*/


    @Test
    @Rollback(false) // İşlemi gerçekleştir ve geri almadan devam et
    public void testDeleteMovie() {
        // Örnek bir film oluştur
        mock(when(movieRepository.save(any(Movie.class))).thenReturn(movie));
        movieManager.save(movie);

        Long movieId = movie.getId();

        // Silme işlemini gerçekleştir
        //movieRepository.deleteById(movieId);

        // Silindiğini doğrula
        //Movie deletedMovie = MovieRepository.findById(Movie.class, movieId);
        //org.assertj.core.api.Assertions.assertThat(deletedMovie).isNull(); // Silindiğinde null döner

        boolean isIdExistLaterDelete = movieRepository.findById(movieId).isPresent();
        System.out.println(isIdExistLaterDelete);
    }
}






