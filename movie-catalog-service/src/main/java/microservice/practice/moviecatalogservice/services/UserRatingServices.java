package microservice.practice.moviecatalogservice.services;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import microservice.practice.moviecatalogservice.model.MovieRating;
import microservice.practice.moviecatalogservice.model.UserRatings;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Service
public class UserRatingServices {
    private final RestTemplate restTemplate;

    public UserRatingServices(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @HystrixCommand(fallbackMethod = "getUserRatingFallback")
    public UserRatings getUserRating(@PathVariable("userId") String userId) {
        return restTemplate.getForObject("http://MOVIE-RATING-SERVICE/movie-ratings/user/" + userId, UserRatings.class);
    }

    private UserRatings getUserRatingFallback(@PathVariable("userId") String userId) {
        UserRatings userRatings = new UserRatings();
        userRatings.setUserId(userId);
        userRatings.setRatings(Arrays.asList(
                new MovieRating("0", 0)
        ));
        return userRatings;
    }
}
