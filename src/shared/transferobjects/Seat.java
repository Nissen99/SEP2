package shared.transferobjects;


public class Seat implements ISeat
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
