package shared.transferobjects;

import java.io.Serializable;

public interface IMovie extends Serializable
{
  public String getMovieTitle();
  public int getMovieId();
}
