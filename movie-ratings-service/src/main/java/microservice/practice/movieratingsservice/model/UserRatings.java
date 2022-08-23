package microservice.practice.movieratingsservice.model;

import java.util.Arrays;
import java.util.List;

public class UserRatings {
    private String userId;
    private List<MovieRating> ratings;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<MovieRating> getRatings() {
        return ratings;
    }

    public void setRatings(List<MovieRating> ratings) {
        this.ratings = ratings;
    }

    public void initData(String userId){
        this.setUserId(userId);
        this.setRatings(Arrays.asList(
                new MovieRating("100",3),
                new MovieRating("200",4)
        ));
    }
}
