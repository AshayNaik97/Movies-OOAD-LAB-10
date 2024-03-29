package com.ll.exam.Movies.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import com.ll.exam.Movies.Repository.MovieRepository;
import com.ll.exam.Movies.Model.Movie;

@Service
public class MovieService {

    private final MovieRepository movieRepository;

    @Autowired
    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    public Movie addMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    public void rateMovie(Long id, double rating) {
        Optional<Movie> optionalMovie = movieRepository.findById(id);
        if (optionalMovie.isPresent()) {
            Movie movie = optionalMovie.get();
            double currentRating = movie.getAverageRating();
           double totalRatings = movie.getTotalRatings();
            double newAverageRating = ((currentRating * totalRatings) + rating) / (totalRatings + 1);
           movie.setAverageRating(newAverageRating);
           movie.setTotalRatings(totalRatings + 1);
            movieRepository.save(movie);
        }
    }

    public Movie getMovieById(long id) {
        Optional<Movie> optionalMovie = movieRepository.findById(id);
        return optionalMovie.orElse(null);
    }

    public void deleteMovie(long id) {
        movieRepository.deleteById(id);
    }
}
