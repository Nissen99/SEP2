package shared.transferobjects;


import java.io.Serializable;


public class Booking implements Serializable
{
  private int bookingId;
  private Showing showing;
  private User user;


  public Booking(int bookingId, Showing showing, User user)
  {
    this.showing = showing;
    this.user = user;
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
