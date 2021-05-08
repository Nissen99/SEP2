package shared.transferobjects;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

import java.io.Serializable;

public class Booking implements Serializable
{
  private int bookingId;
  private Showing showing;
  private User user;
  //private ObjectProperty<User> userPropterty = new SimpleObjectProperty();


  public Booking( int bookingId, Showing showing, User user)
  {
    this.showing = showing;
    this.user = user;
    //userPropterty.set(user);
    this.bookingId = bookingId;
  }

  public int getBookingId()
  {
    return bookingId;
  }

  public Showing getShowing()
  {
    return showing;
  }

  public User getUser(){return user;}

  public String toString(){
    return showing.getWeekDay()+ " "  + showing.getMovie() + " " + user.getUserName();
  }
}
