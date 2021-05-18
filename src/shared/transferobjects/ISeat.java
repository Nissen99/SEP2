package shared.transferobjects;

import java.io.Serializable;

public interface ISeat extends Serializable
{
  public String getSeatNo();

  public void setSeatNo(String seatNo);
}
