package shared.transferobjects;

import java.io.Serializable;

public interface IBooking extends Serializable
{
  int getBookingId();

  IShowing getShowing();

  IUser getUser();

  String toString();

  boolean equals(Object obj);
}
