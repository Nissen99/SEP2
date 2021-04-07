package shared.transferobjects;

import java.sql.Time;
import java.util.Date;

public class Showing
{
  private Date date;
  private Time time;
  private Movie movie;
  private ShowingList showingList;

  public Showing(Date date, Movie movie, ShowingList showingList)
  {
    this.date = date;
    this.movie = movie;
    this.showingList = showingList;
    showingList.addShowing(this);
  }
}
