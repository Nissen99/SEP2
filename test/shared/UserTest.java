package shared;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import shared.transferobjects.Movie;
import shared.transferobjects.User;

import static org.junit.jupiter.api.Assertions.*;

class UserTest
{
  User user;
  @BeforeEach
  public void setUp() {
    user = new User(1, "Henning","henningsmai@mail.com","pass123");
  }


  @Test
  void equalsTjekkerAltErEns(){
    User is = new User(1, "Henning","henningsmai@mail.com","pass123");

    assertEquals(user, is);
  }

  @Test
  void equalsTjekkerIdForskelligt(){
    User is = new User(3, "Henning","henningsmai@mail.com","pass123");

    assertNotEquals(user, is);
  }

  @Test
  void equalsTjekkerUsernameForskelligt(){
    User is = new User(1, "IkkeHenning","henningsmai@mail.com","pass123");

    assertNotEquals(user, is);
  }

  @Test
  void equalsTjekkerEmailForskelligt()
  {
    User is = new User(1, "Henning","Gitte@Hotmail.com","pass123");

    assertNotEquals(user, is);
  }

  @Test
  void equalsTjekkerPasswordForskelligt(){
    User is = new User(1, "Henning","henningsmai@mail.com","Sikkertpassword123");

    assertNotEquals(user, is);
  }

  @Test
  public void constructorSkalopretteUser() {
    assertNotNull(user);
  }

  @Test
  public void bliverNavnSatIConstructor() {
    assertEquals("Henning", user.getUserName());
  }
  @Test
  public void bliverEmailSatiContructor() {
    assertEquals("henningsmai@mail.com", user.getEmail());
  }

  @Test
  public void bliverPasswordSatiContructor() {
    assertEquals("pass123", user.getPassword());
  }

  @Test
  public void bliverIdSatiContructor() {
    assertEquals(1, user.getUserID());
  }


}