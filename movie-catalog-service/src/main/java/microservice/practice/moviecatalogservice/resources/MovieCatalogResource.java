package microservice.practice.moviecatalogservice.resources;

import microservice.practice.moviecatalogservice.model.MovieCatalog;
import microservice.practice.moviecatalogservice.model.UserRatings;
import microservice.practice.moviecatalogservice.services.MovieInfoServices;
import microservice.practice.moviecatalogservice.services.UserRatingServices;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/movie-catalog")
public class MovieCatalogResource {

    private final MovieInfoServices movieInfoServices;
    private final UserRatingServices userRatingServices;
    private final WebClient.Builder builder;

    public MovieCatalogResource(RestTemplate restTemplate, MovieInfoServices movieInfoServices, UserRatingServices userRatingServices, @Qualifier("webClientBean") WebClient.Builder builder) {
        this.movieInfoServices = movieInfoServices;
        this.userRatingServices = userRatingServices;
        this.builder = builder;
    }


    @GetMapping(value = "/{userId}")
    public List<MovieCatalog> getCatalog(@PathVariable("userId") String userId) {

        UserRatings userRatings = userRatingServices.getUserRating(userId);
        return userRatings.getRatings().stream().map(ratings -> movieInfoServices.getMovieCatalog(ratings)
        ).collect(Collectors.toList());

    }
}