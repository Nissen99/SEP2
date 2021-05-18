package shared.transferobjects;

import java.io.Serializable;
import java.sql.Timestamp;

public interface IShowing extends Serializable
{
  public int getId();
  public IHall getHall();
  public IMovie getMovie();
  public String toString();
  public String getTime();
  public String getWeekDay();
  public Timestamp getTimestamp();
  public String getDate();
}
