package com.ll.exam.Movies.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ll.exam.Movies.Model.Movie;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
    // You can define custom queries here if needed
}

