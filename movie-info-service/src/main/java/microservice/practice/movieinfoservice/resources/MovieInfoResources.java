package microservice.practice.movieinfoservice.resources;

import microservice.practice.movieinfoservice.model.MovieInfo;
import microservice.practice.movieinfoservice.model.MovieSummary;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping(value = "/movie-info")
public class MovieInfoResources {

    private final RestTemplate restTemplate;

    @Value("${api.key}")
    private String apiKey;

    public MovieInfoResources(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping(value = "/{movieId}")
    public MovieInfo getMovie(@PathVariable("movieId") String movieId){
        MovieSummary movieSummary = restTemplate.getForObject("https://api.themoviedb.org/3/movie/"+movieId+"?api_key="+apiKey,MovieSummary.class);
        return new MovieInfo(movieId,movieSummary.getTitle(),movieSummary.getOverview());
    }
}
