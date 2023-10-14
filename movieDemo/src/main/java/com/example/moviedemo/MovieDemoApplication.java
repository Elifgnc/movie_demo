package com.example.moviedemo;

import com.example.moviedemo.business.abstracts.MovieService;
import com.example.moviedemo.entity.Movie;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jdk.jfr.Enabled;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@SpringBootApplication
@EnableWebMvc
public class MovieDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(MovieDemoApplication.class, args);
    }

    @Bean
    CommandLineRunner runner(MovieService movieService){
        // proje ilk çalıştığında burayı çalıştırır
        return args -> {
            ObjectMapper mapper = new ObjectMapper();

            TypeReference<List<Movie>> typeReference = new TypeReference<List<Movie>>(){};
            InputStream inputStream = TypeReference.class.getResourceAsStream("/json/movies.json");
            try {
                // json ı movie ye dönüştürerek DB e kaydeder

                List<Movie> movies = mapper.readValue(inputStream,typeReference);
                movieService.save(movies);

                // list olarak tutar list save kısmı çalışır

                System.out.println("Movies Saved!");
            } catch (IOException e){
                System.out.println("Unable to save movies: " + e.getMessage());
            }
        };
    }
}
