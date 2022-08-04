package microservice.practice.movieinfoservice.resources;

import microservice.practice.movieinfoservice.model.MovieInfo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping(value = "/movie-info")
public class MovieInfoResources {

    @GetMapping(value = "/{movieId}")
    public MovieInfo getMovie(@PathVariable("movieId") String movieId){
        return new MovieInfo(movieId,"ABC");

    }
}
