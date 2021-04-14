package shared;

public class Seat
{
  private boolean isOccupied;
  private int seatNo;

  public Seat(){
    this.isOccupied = false;
  }
  public boolean getIsOccupied()
  {
    return isOccupied;
  }

  public int getCol()
  {
    return seatNo % 100;
  }

  public int getRow()
  {
    return seatNo/100;
  }

  public void setIsOccupiedState(boolean isOccupied)
  {
    this.isOccupied = isOccupied;
  }

  public void setSeatNo(int seatNo)
  {
    this.seatNo = seatNo;
  }
}
