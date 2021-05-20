package shared.transferobjects;

/**
 * Simple Klasse der holder informationer omkring en bruger
 */
public class User implements IUser
{
  private String userName;
  private int userID;
  private String email;
  private String password;

  public User(int userID, String userName, String email, String password)
  {
    this.userName = userName;
    this.userID = userID;
    this.email = email;
    this.password = password;
  }

  public String getUserName()
  {
    return userName;
  }


  public int getUserID()
  {
    return userID;
  }

  public String getEmail()
  {
    return email;
  }

  public String getPassword()
  {
    return password;
  }

  public boolean equals(Object obj){
    if (!(obj instanceof User)){
      return false;
    }
    IUser is = (User) obj;
     return (is.getUserID() == userID) && is.getUserName().equals(userName) &&
        is.getEmail().equals(email) && is.getPassword().equals(password);
  }

}
