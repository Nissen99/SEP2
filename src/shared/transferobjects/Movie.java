package shared.transferobjects;

public class Movie implements IMovie
{
  private String movieTitle;
  private int movieId;

  public Movie(int movieId, String movieTitle)
  {
    this.movieTitle = movieTitle;
    this.movieId = movieId;
  }

  public Movie(String movieTitle)
{
  this.movieTitle = movieTitle;
}

  public String getMovieTitle()
  {
    return movieTitle;
  }

  public int getMovieId()
  {
    return movieId;
  }

  public boolean equals(Object obj){
    if (!(obj instanceof Movie)){
      return false;
    }
    Movie is = (Movie) obj;
    return is.getMovieId() == movieId && is.getMovieTitle().equals(movieTitle);
  }
}
