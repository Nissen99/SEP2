package shared.transferobjects;

import java.io.Serializable;
import java.util.ArrayList;

public interface IHall extends Serializable
{

  public int getMaxSeatsInRow();

  public int getMaxRows();

  public String getHallNo();

  public ArrayList<ISeat> getSeats();

  public ISeat addSeat(ISeat seat);

}
