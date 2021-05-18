package shared.transferobjects;

import java.io.Serializable;
import java.sql.Timestamp;

public interface IShowing extends Serializable
{
  int getId();
  IHall getHall();
  IMovie getMovie();
  String toString();
  String getTime();
  String getWeekDay();
  Timestamp getTimestamp();
  String getDate();
}
