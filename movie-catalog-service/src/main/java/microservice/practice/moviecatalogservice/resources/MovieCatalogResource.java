package microservice.practice.moviecatalogservice.resources;

import microservice.practice.moviecatalogservice.model.MovieCatalog;
import microservice.practice.moviecatalogservice.model.MovieInfo;
import microservice.practice.moviecatalogservice.model.MovieRating;
import microservice.practice.moviecatalogservice.model.UserRatings;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/movie-catalog")
public class MovieCatalogResource {

    private final RestTemplate restTemplate;
    private final WebClient.Builder builder;

    public MovieCatalogResource(RestTemplate restTemplate, WebClient.Builder builder) {
        this.restTemplate = restTemplate;
        this.builder = builder;
    }


    @GetMapping(value = "/{userId}")
    public List<MovieCatalog> getCatalog(@PathVariable("userId") String userId) {

        UserRatings userRatings = restTemplate.getForObject("http://MOVIE-RATING-SERVICE/movie-ratings/user/"+userId,UserRatings.class);
        return userRatings.getUserMovieRating().stream().map(ratings -> {
                    MovieInfo movieInfo = restTemplate.getForObject("http://MOVIE-INFO-SERVICE/movie-info/"+ratings.getMovieId(), MovieInfo.class);
//                   MovieInfo movieInfo = builder.build()
//                            .get()
//                            .uri("http://localhost:8082/movie-info/"+ratings.getMovieId())
//                            .retrieve()
//                            .bodyToMono(MovieInfo.class)
//                            .block();
                    return new MovieCatalog(movieInfo.getName(), "test", ratings.getRating());
                }

        ).collect(Collectors.toList());

    }
}