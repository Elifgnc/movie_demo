package com.example.moviedemo.controller;

import com.example.moviedemo.business.abstracts.MovieService;
import com.example.moviedemo.dataAccess.MovieRepository;
import com.example.moviedemo.entity.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class MovieeController {

    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private MovieService movieService;

    @GetMapping("/movies/list2")
    public ModelAndView showMovie(ModelAndView model) {
        ModelAndView mav = new ModelAndView("list-movie");
        List<Movie> list = movieRepository.findAll();
        mav.addObject("movie", list);
        return mav;
    }

    @GetMapping("/addMovieForm")
    public ModelAndView addMovieForm() {
        ModelAndView mav = new ModelAndView("add-movie-form");
        Movie newMovie = new Movie();
        mav.addObject("movie", newMovie);
        return mav;
    }

    @PostMapping("/saveMovie")
    public String saveMovie(@ModelAttribute Movie movie) {
        movieRepository.save(movie);
        return "redirect:/movies/list2";
    }

    @GetMapping("/showUpdateForm")
    public ModelAndView showUpdateForm(@RequestParam Long movieId) {
        ModelAndView mav = new ModelAndView("add-movie-form");
        Movie movie = movieRepository.findById(movieId).get();
        mav.addObject("movie", movie);
        return mav;
    }

    @GetMapping("/deleteMovie")
    public String deleteEmployee(@RequestParam Long movieId) {
        movieRepository.deleteById(movieId);
        return "redirect:/movies/list2";
    }

    @GetMapping("/movie")
    public ModelAndView getMovie(Model model, String keyword, float imdbRatingMin, float imdbRatingMax) {
//        model.addAttribute("type", MovieService.getType());
//        model.addAttribute("director", MovieService.getDirector());
//        model.addAttribute("imdbRatingMin", MovieService.getimdbRatingMin());
//        model.addAttribute("imdbRatingMax", MovieService.getimdbRatingMax());

        if (keyword != null) {
            ModelAndView mav = new ModelAndView("list-movie");
            List<Movie> list = movieService.findByKeyword(imdbRatingMin, imdbRatingMax);
            List<Movie> list1 = movieService.findByKeyword1(keyword);
            List<Movie> list2 = movieService.findByKeyword2(keyword);
            mav.addObject("movie", list);
            mav.addObject("movie", list1);
            mav.addObject("movie", list2);
            return mav;
        } else {
            model.addAttribute("movie", MovieService.getMovie());
            ModelAndView mav = new ModelAndView("list-movie");
            List<Movie> list = null;
            List<Movie> list1 = null;
            List<Movie> list2 = null;
            mav.addObject("movie", list);
            mav.addObject("movie", list1);
            mav.addObject("movie", list2);
            return mav;
        }


    }

}
