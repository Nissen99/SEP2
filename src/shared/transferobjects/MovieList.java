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

  public String toString(){
    String s = null;
    for (Movie movie : movieList)
    {
      s += movie.toString();
    }
    return s;
  }
}