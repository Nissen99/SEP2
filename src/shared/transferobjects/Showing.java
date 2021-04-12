package shared.transferobjects;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class Showing
{
  private Timestamp timestamp;
  private Movie movie;



  public Showing(Movie movie, Timestamp timestamp)
  {
    this.movie = movie;
    this.timestamp = timestamp;

  }


  public Movie getMovie()
  {
    return movie;
  }


  //Bliver brugt ikke slet
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
