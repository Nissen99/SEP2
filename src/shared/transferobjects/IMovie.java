package shared.transferobjects;

import java.io.Serializable;

public interface IMovie extends Serializable
{
  String getMovieTitle();
  int getMovieId();
}
