package shared.transferobjects;

import java.io.Serializable;
import java.util.ArrayList;

public interface IHall extends Serializable
{

  int getMaxSeatsInRow();

  int getMaxRows();

  String getHallNo();

  ArrayList<ISeat> getSeats();

  ISeat addSeat(ISeat seat);

}
