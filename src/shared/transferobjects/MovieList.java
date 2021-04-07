package shared.transferobjects;

import java.util.ArrayList;
import java.util.List;

public class MovieList
{
  private ArrayList<Movie> movieList;

  public MovieList()
  {
    movieList = new ArrayList<>();
  }

  public void addMovie(Movie movie)
  {
    movieList.add(movie);
  }

  public void removeMovie(int index) {
    movieList.remove(index);
  }

  public ArrayList<Movie> getMovieList()
  {
    return movieList;
  }

  public int getMovieListSize() {
    return movieList.size();
  }
}