package shared.transferobjects;

import java.io.Serializable;

public interface IBooking extends Serializable
{
  public int getBookingId();

  public IShowing getShowing();

  public IUser getUser();

  public String toString();
}
