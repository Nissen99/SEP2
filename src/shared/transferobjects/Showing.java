package shared.transferobjects;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class Showing implements IShowing
{
  private Timestamp timestamp;
  private IMovie movie;
  private int showingId;
  private IHall hall;

  public Showing(int showingId, IMovie movie, Timestamp timestamp, IHall hall)
  {
    this.movie = movie;
    this.timestamp = timestamp;
    this.showingId = showingId;
    this.hall = hall;

  }

  /**
   * Her overloader vi construtoren da vi generer ID gennem database, men stadigvæk
   * gerne vil kunne oprette showings så de kan sendes gennem systemet
   */
  public Showing(IMovie movie, Timestamp timestamp, IHall hall)
{
  this.movie = movie;
  this.timestamp = timestamp;
  this.hall = hall;
}

  public int getId()
  {
    return showingId;

  }

  public IHall getHall()
  {
    return hall;
  }

  public IMovie getMovie()
  {
    return movie;
  }

  public String toString(){

    return "Movie title: " + movie.getMovieTitle() + ", hallNo: " + hall.getHallNo()
        + " time: " + getTime();
  }

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
