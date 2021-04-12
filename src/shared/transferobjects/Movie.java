package shared.transferobjects;

public class Movie
{
  private String movieTitle;

  public Movie(String movieTitle)
  {
   this.movieTitle = movieTitle;
  }

  public String getMovieTitle()
  {
    return movieTitle;
  }

  public String toString(){
    return movieTitle;
  }
}
