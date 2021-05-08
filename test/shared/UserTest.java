package shared;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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
  public void constructorSkalopretteUser() {
    assertNotNull(user);
  }

  @Test
  public void bliverNavnSatIConstructor() {
    assertEquals("Henning", user.getUserName());
  }

  @Test
  public void bliverIdSatiContructor() {
    assertEquals(1, user.getUserID());
  }


}