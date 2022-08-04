package microservice.practice.movieratingsservice.resources;

import microservice.practice.movieratingsservice.model.MovieRating;
import microservice.practice.movieratingsservice.model.UserRatings;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.jws.soap.SOAPBinding;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping(value = "/movie-ratings")
public class MovieRatingsResources {

    @GetMapping(value = "/{movieId}")
    public MovieRating getRating(@PathVariable("movieId") String movieId){
        return new MovieRating(movieId,4);

    }
    @GetMapping(value = "/user/{userId}")
    public UserRatings getRatingsByUser(@PathVariable("userId") String userId){
        UserRatings userRatings = new UserRatings();
        List<MovieRating> movieRatings = Arrays.asList(
                new MovieRating("123", 3),
                new MovieRating("124", 4)
        );
         userRatings.setUserMovieRating(movieRatings);
         return userRatings;
    }
}
