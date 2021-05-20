package shared.transferobjects;

public class Booking implements IBooking
{
  private int bookingId;
  private IShowing showing;
  private IUser user;


  public Booking(int bookingId, IShowing showing, IUser user)
  {
    this.showing = showing;
    this.user = user;
    this.bookingId = bookingId;
  }

  public int getBookingId()
  {
    return bookingId;
  }

  public IShowing getShowing()
  {
    return showing;
  }

  public IUser getUser(){return user;}

  public String toString(){
    return showing.getWeekDay()+ " "  + showing.getMovie() + " " + user.getUserName();
  }

  public boolean equals(Object obj){
    if (!(obj instanceof Booking)){
      return false;
    }
    Booking is = (Booking) obj;
    return is.getBookingId() == bookingId && is.getShowing().equals(showing) && is.getUser().equals(user);
  }
}
