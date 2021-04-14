package shared;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class Showing
{
  private Timestamp timestamp;
  private Movie movie;
  private int showingId;
  private Hall hall;

  public Showing(int showingId, Movie movie, Timestamp timestamp, Hall hall)
  {
    this.movie = movie;
    this.timestamp = timestamp;
    this.showingId = showingId;
    this.hall = hall;
  }

  public int getId()
  {
    return showingId;

  }

  public Hall getHall()
  {
    return hall;
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

  public Timestamp getTimestamp()
  {
    return timestamp;
  }

  public String getDate(){
    String date = (new SimpleDateFormat("dd-MM-yyyy")).format(timestamp.getTime());
    return date;
  }

}
