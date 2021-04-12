package shared.transferobjects;

public class Booking
{
  //private String bookingID;
  private Showing showing;
  private User user;

  public Booking(Showing showing, User user)
  {
    this.showing = showing;
    this.user = user;
    //Booking id? Skal det genereres her?
  }

  public Showing getShowing()
  {
    return showing;
  }
}
