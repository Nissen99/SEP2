package shared;

public class Seat
{
  private boolean isOccupied;
  private String seatNo;

  public Seat(){
    this.isOccupied = false;
  }
  public boolean IsOccupied()
  {
    return isOccupied;
  }

  public String getSeatNo()
  {
    return seatNo;
  }

  public void setIsOccupiedState(boolean isOccupied)
  {
    this.isOccupied = isOccupied;
  }

  public void setSeatNo(String seatNo)
  {
    this.seatNo = seatNo;
  }
}
