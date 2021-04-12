package shared.transferobjects;

public class Booking
{
  private int bookingId;
  private Showing showing;
  private User user;

  public Booking(Showing showing, User user, int bookingId)
  {
    this.showing = showing;
    this.user = user;
    this.bookingId = bookingId;
  }

  public Showing getShowing()
  {
    return showing;
  }

  public String toString(){
    return showing.getWeekDay()+ " "  + showing.getMovie() + " " + user.getName();
  }
}
