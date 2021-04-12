package shared.transferobjects;

import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
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

  public String getTime(){
    String time = (new SimpleDateFormat("HH:mm")).format(timestamp.getTime());
    return time;

  }

  public String getWeekDay(){

    String day = (new SimpleDateFormat("EEEE")).format(timestamp.getTime());
    return day;
  }

  public String getDate(){
    String date = (new SimpleDateFormat("dd-MM-yyyy")).format(timestamp.getTime());
    return date;
  }

}
