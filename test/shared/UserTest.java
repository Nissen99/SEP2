package shared;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest
{
  User user;
  @BeforeEach
  public void setUp() {
    user = new User(1, "Henning");
  }

  @Test
  public void constructorSkalopretteUser() {
    assertNotNull(user);
  }

  @Test
  public void bliverNavnSatIConstructor() {
    assertEquals("Henning", user.getName());
  }

  @Test
  public void bliverIdSatiContructor() {
    assertEquals(1, user.getUserID());
  }


}