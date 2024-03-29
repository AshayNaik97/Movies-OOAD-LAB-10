package com.ll.exam.Movies.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.ll.exam.Movies.Service.MovieService;
import java.util.Map;

import com.ll.exam.Movies.Model.Movie;
import java.util.List;

@RestController
@RequestMapping("/movies")
@CrossOrigin(origins = "file:///C:/Users/ashuk/OneDrive/Documents/ooad/Movies/src/main/java/com/ll/exam/Movies/View/index.html")
public class MovieController {

    private final MovieService movieService;

    // @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping
    public List<Movie> getAllMovies() {
        return movieService.getAllMovies();
    }

    @PostMapping
    public Movie addMovie(@RequestBody Movie movie) {
        return movieService.addMovie(movie);
    }

    @PutMapping("/{id}/rate")
    public ResponseEntity<String> rateMovie(@PathVariable Long id, @RequestBody Map<String, Double> requestBody) {
        if (!requestBody.containsKey("rating")) {
            return ResponseEntity.badRequest().body("Rating not provided");
        }

        Double rating = requestBody.get("rating");

        movieService.rateMovie(id, rating);

        return ResponseEntity.ok().body("Rating updated successfully");
    }

    @GetMapping("/{id}")
    public Movie getMovieById(@PathVariable Long id) {
        return movieService.getMovieById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteMovie(@PathVariable Long id) {
        movieService.deleteMovie(id);
    }
}
