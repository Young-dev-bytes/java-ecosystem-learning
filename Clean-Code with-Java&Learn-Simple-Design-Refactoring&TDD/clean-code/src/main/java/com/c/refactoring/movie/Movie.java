package com.c.refactoring.movie;

import com.c.refactoring.StringUtils;

import java.util.Arrays;
import java.util.List;

public class Movie {

    public static final List<String> VALID_B_RATINGS_LIST = Arrays.asList("B1", "B2", "B3", "B4");
    String rating;

    public Movie(String rating) {
        super();
        this.rating = rating;
    }

    public String getRating() {
        return rating;
    }

    /*Axx or By
    Where x represents any digit between 0 and 9, and y represents
    any digit between 1 and 4*/
    /*public boolean isValidRating() {

        if (!this.getRating().isEmpty()) {

            String firstStr = this.getRating().substring(0, 1);
            String secondStr = this.getRating().substring(1, 2);

            if (firstStr.equalsIgnoreCase("B")
                    && this.getRating().length() == 2) {
                if (StringUtils.isNumeric(secondStr)
                        && Integer.parseInt(secondStr) > 0
                        && Integer.parseInt(secondStr) < 5)
                    return true;

            } else if (firstStr.equalsIgnoreCase("A")
                    && this.getRating().length() == 3
                    && StringUtils.isNumeric(this.getRating().substring(1, 3)))
                return true;

        }
        return false;
    }*/

    public boolean isValidRating() {

        if (rating == null)
            return false;

        if (isValidARating()) {
            return true;
        }

        if (isValidBRating()) {
            return true;
        }
        return false;
    }

    private boolean isValidBRating() {
        return VALID_B_RATINGS_LIST.contains(rating);
    }

    private boolean isValidARating() {
        return rating.substring(0, 1).equalsIgnoreCase("A")
                && rating.length() == 3
                && StringUtils.isNumeric(rating.substring(1, 3));
    }

    public void setRating(String rating) {
        this.rating = rating;
    }
}
