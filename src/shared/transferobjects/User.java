package shared.transferobjects;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;

public class User
{
  private String name;
  private int userID;

  public User(String name, int userID)
  {
    this.name = name;
    this.userID = userID;
  }

  public String getName()
  {
    return name;
  }
}
