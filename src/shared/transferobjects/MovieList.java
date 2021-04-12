package shared.transferobjects;

import java.util.ArrayList;
import java.util.List;

public class MovieList
{
  private ArrayList<Movie> movieList = new ArrayList<>();

  public void addMovie(Movie movie)
  {
    movieList.add(movie);
  }

  public ArrayList<Movie> getMovieList()
  {
    return movieList;
  }
}