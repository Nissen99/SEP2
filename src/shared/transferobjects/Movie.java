package shared.transferobjects;

import java.util.UUID;

public class Movie
{
  private String movieTitle;
  private String movieID;
  private MovieList movieList;

  public Movie(String movieTitle, MovieList movieList)
  {
    this.movieTitle = movieTitle;
    this.movieList = movieList;
    this.movieID = UUID.randomUUID().toString();
    movieList.addMovie(this);
  }


}
