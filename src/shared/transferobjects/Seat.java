package shared.transferobjects;

import java.io.Serializable;

public class Seat implements Serializable
{
  private String seatNo;

  public String getSeatNo()
  {
    return seatNo;
  }

  public void setSeatNo(String seatNo)
  {
    this.seatNo = seatNo;
  }
}
