package shared.transferobjects;

import java.io.Serializable;

public interface ISeat extends Serializable
{
  String getSeatNo();
  void setSeatNo(String seatNo);
  boolean equals(Object obj);
}
