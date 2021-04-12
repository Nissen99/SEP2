package shared.transferobjects;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;

public class Showing
{
  private Timestamp timestamp;
  private Movie movie;

  public Showing(Movie movie, Timestamp timestamp)
  {
    this.movie = movie;
    this.timestamp = timestamp;
  }
}
