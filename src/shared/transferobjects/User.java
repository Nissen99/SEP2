package shared.transferobjects;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;

public class User
{
  private String name;
  private int userID;
  private MovieList movieList;
  private ShowingList showingList;

  public User(String name, int userID)
  {
    this.name = name;
    this.userID = userID;
  }

  public void makeBooking(String movieTitle, Date date, Time time) {
    Booking booking = new Booking();
  }

  public ArrayList<Movie> getMovieList() {
    return movieList.getMovieList();
  }
}
