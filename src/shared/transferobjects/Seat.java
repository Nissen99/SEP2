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

  public boolean equals(Object obj){
    if (!(obj instanceof Seat)){
      return false;
    }
    ISeat is = (Seat) obj;
    return is.getSeatNo().equals(seatNo);
  }
}
