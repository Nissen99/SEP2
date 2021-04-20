package shared;

public class Booking
{
  private int bookingId;
  private Showing showing;
  private User user;
  private Seat seat;

  public Booking( int bookingId, Showing showing, User user, String seatNo)
  {
    this.showing = showing;
    this.user = user;
    this.bookingId = bookingId;
    this.seat = new Seat();
    seat.setSeatNo(seatNo);
  }

  public int getBookingId()
  {
    return bookingId;
  }

  public Showing getShowing()
  {
    return showing;
  }

  public String toString(){
    return showing.getWeekDay()+ " "  + showing.getMovie() + " " + user.getName() + " " + seat.getSeatNo();
  }
}
