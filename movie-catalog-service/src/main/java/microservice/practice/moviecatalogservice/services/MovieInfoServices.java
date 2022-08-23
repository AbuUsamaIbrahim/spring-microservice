package microservice.practice.moviecatalogservice.services;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import microservice.practice.moviecatalogservice.model.MovieCatalog;
import microservice.practice.moviecatalogservice.model.MovieInfo;
import microservice.practice.moviecatalogservice.model.MovieRating;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MovieInfoServices {
    private final RestTemplate restTemplate;

    public MovieInfoServices(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @HystrixCommand(fallbackMethod = "getMovieCatalogFallback")
    public MovieCatalog getMovieCatalog(MovieRating ratings) {
        MovieInfo movieInfo = restTemplate.getForObject("http://MOVIE-INFO-SERVICE/movie-info/" + ratings.getMovieId(), MovieInfo.class);
//                   MovieInfo movieInfo = builder.build()
//                            .get()
//                            .uri("http://localhost:8082/movie-info/"+ratings.getMovieId())
//                            .retrieve()
//                            .bodyToMono(MovieInfo.class)
//                            .block();
        return new MovieCatalog(movieInfo.getName(), movieInfo.getDescription(), ratings.getRating());
    }

    private MovieCatalog getMovieCatalogFallback(MovieRating ratings) {
        return new MovieCatalog("Movie not found", "Not found", ratings.getRating());
    }
}
