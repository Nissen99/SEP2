package client.util;

public enum VIEWPATH
{
  VIEWPATH("Admin", "edit Movie");

  public final String admin;
  public final String editMovie;


  VIEWPATH(String admin, String sad)
  {
    this.admin = admin;
    this.editMovie = sad;
  }
}
