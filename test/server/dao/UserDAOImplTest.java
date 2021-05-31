package server.dao;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import shared.exception.ServerException;
import shared.transferobjects.IUser;
import static org.junit.jupiter.api.Assertions.*;


class UserDAOImplTest
{
  /**
   * Der er lavet flere valide usernames, da de skal være unikke
   */
  private DAOTestSetup setup = new DAOTestSetup();
  private UserDAO userDAO = new UserDAOImpl();
  private final String validUserName1 = "UnikUserName";
  private final String validUserName2 = "TotalValidUserName";
  private final String validUserName3 = "ThisIsValid";
  private final String  validEmail = "KasperErSej@yaho.com";
  private final String validPassword = "SikkertPassword123";



  @BeforeEach void setup() throws ServerException
  {
    setup.setup();
  }

  @Test
  void testCreate() throws ServerException
  {
    assertEquals(2, userDAO.create(validUserName1, validEmail, validPassword).getUserID());
  }

  @Test
  void createUserWithUsernameNull()
  {
    assertThrows(ServerException.class, () -> userDAO.create(null, validEmail, validPassword));
  }

  @Test
  void createUserWithPasswordNull()
  {
    assertThrows(ServerException.class, () -> userDAO.create(validUserName1, validEmail, null));
  }

  @Test
  void createUserWithEmailNull()
  {
    assertThrows(ServerException.class, () -> userDAO.create(validUserName1, null, validPassword));
  }

  @Test
  void userNameMustBeUnique() throws ServerException
  {
    userDAO.create(validUserName1, validEmail, validPassword);
    assertThrows(ServerException.class, () -> userDAO.create(validUserName1, validEmail, validPassword));
  }



  @Test
  void emailContainsAtSignAtRightSpot(){
    assertThrows(ServerException.class, () -> userDAO.create("Username", "@Email.com", "passwordsD123"));
    assertDoesNotThrow(() -> userDAO.create("IkkeMitNavn", "Hejsa@gmail.com", "DuHackerikkeMig"));
  }

  @Test
  void emailBoundaryUnder6AndOn6Characters()
  {
    assertThrows(ServerException.class, () -> userDAO.create("UserName1", "g@1.d", "Passwordsersikre"));
    assertDoesNotThrow(() ->userDAO.create("UserName2", "Gm@l.c", "Password123"));
  }

  @Test
  void emailBoundaryOn50AndOver50Characters(){
    StringBuilder email = makeLongString(50);

    String notLongEmail = email.toString();

    assertDoesNotThrow(() -> userDAO.create(validUserName1, notLongEmail, validPassword));

    email.append("a");

    String longEmail = email.toString();

    assertThrows(ServerException.class, () -> userDAO.create(validUserName2,
        longEmail, validPassword));
  }



  @Test
  void passwordNeedsUpperAndLowerCaseLetters(){

    assertThrows(ServerException.class, () -> userDAO.create("username", "MinEmail@gmail.com", "passawords"));
    assertThrows(ServerException.class, () -> userDAO.create("DetErMinNavn", "jada@gmail.com", "PASSWORDUPSCAPS"));

  }

  @Test
  void passwordBoundaryUnder5AndOn5Characters(){

    assertThrows(ServerException.class, () -> userDAO.create(validUserName1, validEmail, "Hej1"));
    assertDoesNotThrow(() -> userDAO.create(validUserName2, validEmail, "Hej12"));
  }

  @Test
  void passwordBoundaryOn50AndOver50Characters()
  {
    StringBuilder password = makeLongString(49);
    password.append("A"); //et Password skal have upper og lowercase, så laver 49 og tilføljer "A"

    assertDoesNotThrow(() -> userDAO.create(validUserName1, validEmail, password.toString()));
    password.append("a");
    assertThrows(ServerException.class, () -> userDAO.create(validUserName2, validEmail, password.toString()));
  }

  @Test
  void loginGivesUsCorrectUser() throws ServerException
  {
    IUser user = setup.getUserList().get(0);

    assertEquals(user, userDAO.login(setup.getUserName(), setup.getPassword()));
  }

  @Test
  void loginWithWrongUsername(){
    assertThrows(ServerException.class, () -> userDAO.login("Yikes", setup.getPassword()));
  }

  @Test
  void loginWithWrongPassword(){
    assertThrows(ServerException.class, () -> userDAO.login(setup.getUserName(), "PasswordNumber3"));
  }

  @Test
  void loginUsernameIsNull(){

    assertThrows(ServerException.class, () -> userDAO.login(null, setup.getPassword()));
  }

  @Test
  void loginPasswordIsNull(){

    assertThrows(ServerException.class, () -> userDAO.login(setup.getUserName(), null));
  }


  private StringBuilder makeLongString(int length)
  {
    StringBuilder email = new StringBuilder();
    for (int i = 0; i < length-4; i++)
    {
      email.append("a");
      if (i == 35)
      {
        email.append("@");
      }
    }
    email.append(".dk");
    return email;
  }
}