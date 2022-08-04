package microservice.practice.moviecatalogservice.model;

import java.util.List;

public class UserRatings {
    public List<MovieRating> getUserMovieRating() {
        return userMovieRating;
    }

    public void setUserMovieRating(List<MovieRating> userMovieRating) {
        this.userMovieRating = userMovieRating;
    }

    public UserRatings() {
    }

    private List<MovieRating> userMovieRating;
}
