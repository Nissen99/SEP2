package shared.transferobjects;

import java.sql.Time;
import java.util.Date;

public class Showing
{
  private Date date;
  private Time time;
  private Movie movie;

  public Showing(Date date, Time time)
  {
    this.date = date;
    this.time = time;
  }
}
