package com.example.moviedemo.business.concretes;

import com.example.moviedemo.business.abstracts.MovieService;
import com.example.moviedemo.dataAccess.MovieRepository;
import com.example.moviedemo.entity.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieManager implements MovieService {

    private final MovieRepository movieRepository;

    @Autowired
    public MovieManager(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public Iterable<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    @Override
    public Movie save(Movie movie) {
      return  movieRepository.save(movie); // her eklenen yeni veri de save kullanılır
    }

    @Override
    public void save(List<Movie> movies) {
      movieRepository.saveAll(movies); // başlangıç için saveAll kulanılır
    }

    @Override
    public void deleteMovie(Long id) {
        movieRepository.deleteById(id); // ID üzerinden silme işlemi yapar.
    }

    @Override
    public Movie updateMovie(Long id, Movie movie) {
        Movie updateMovie = this.movieRepository.findById(id).orElseThrow();
        updateMovie.setType(movie.getType());
        updateMovie.setDirector(movie.getDirector());
        updateMovie.setWriter(movie.getWriter());
        updateMovie.setTitle(movie.getTitle());
        updateMovie.setAwards(movie.getAwards());
        updateMovie.setActors(movie.getActors());
        updateMovie.setComingSoon(movie.getComingSoon());
        updateMovie.setCountry(movie.getCountry());
        updateMovie.setGenre(movie.getGenre());
        updateMovie.setImdbID(movie.getImdbID());
        updateMovie.setImdbRating(movie.getImdbRating());
        updateMovie.setImdbVotes(movie.getImdbVotes());
        updateMovie.setRuntime(movie.getRuntime());
        updateMovie.setReleased(movie.getReleased());
        updateMovie.setRated(movie.getRated());
        updateMovie.setTotalSeasons(movie.getTotalSeasons());
        updateMovie.setYear(movie.getYear());
        updateMovie.setPoster(movie.getPoster());
        updateMovie.setPlot(movie.getPlot());
        updateMovie.setLanguage(movie.getLanguage());
        this.movieRepository.save(updateMovie);
        return updateMovie;
    }

    public Iterable<Movie> search(String keyword) {
        if(keyword!=null){
            return  this.movieRepository.search(keyword);
        }
        else{
            return movieRepository.findAll();
        }
    }

    //@Override
    //public Iterable<Movie> filter(String keyword) {
      //  return this.movieRepository.filter(keyword);
//}


    //@Override
    //public Iterable<Movie> filter3(String type, String director) {
      //  return movieRepository.findMoviesByTypeContainsIgnoreCaseAndDirectorContainsIgnoreCase(type,director) ;

    //}

    @Override
    public Iterable<Movie> filter5(String type, String Director, float imdbRatingMin, float imdbRatingMax) {
        return movieRepository.findMoviesByTypeContainsIgnoreCaseAndDirectorContainingIgnoreCaseAndImdbRatingBetween(type, Director, imdbRatingMin, imdbRatingMax);

    }

    @Override
    public List<Movie> findByKeyword(String keyword) {
        return null;
    }


    @Override
    public List<Movie> findByKeyword(float imdbRatingMin, float imdbRatingMax) {
        return movieRepository.findMoviesByImdbRatingBetween(imdbRatingMin,imdbRatingMax);
    }

    @Override
    public List<Movie> findByKeyword1(String keyword) {
        return movieRepository.findByTypeLikeIgnoreCase(keyword);
    }

    @Override
    public List<Movie> findByKeyword2(String keyword) {
        return movieRepository.findByDirectorLikeIgnoreCase(keyword);
    }


    /*@Override
      public Iterable<Movie> filter4(float imdbRatingMin, float imdbRatingMax) {
      return movieRepository.findMoviesByImdbRatingBetween(imdbRatingMin,imdbRatingMax);

    }*/

 }
