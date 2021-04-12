package shared.transferobjects;

public class Movie
{
  private String movieTitle;
  private int movieId;

  public Movie(int movieId, String movieTitle)
  {
    this.movieTitle = movieTitle;
    this.movieId = movieId;
  }

  public String getMovieTitle()
  {
    return movieTitle;
  }

  public String toString(){
    return movieTitle;
  }
}
