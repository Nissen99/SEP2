package server.dao;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import shared.exception.ServerException;
import shared.transferobjects.IUser;
import static org.junit.jupiter.api.Assertions.*;


class UserDAOImplTest
{
  private DAOTestSetup setup = new DAOTestSetup();
  private UserDAO userDAO = new UserDAOImpl();

  @BeforeEach void setup() throws ServerException
  {
    setup.setup();
  }

  @Test
  void testCreate() throws ServerException
  {
    assertEquals(2, userDAO.create("Henning", "Sara@Hotmail.com", "JegStalkerIkkeSara").getUserID());
  }

  @Test
  void createUserWithUsernameNull()
  {
    assertThrows(ServerException.class, () -> userDAO.create(null, "denneMail@Gmail.com", "Sikkerhed"));
  }

  @Test
  void createUserWithPasswordNuln()
  {
    assertThrows(ServerException.class, () -> userDAO.create("Yikers", "denneMail@Gmail.com", null));
  }

  @Test
  void createUserWithEmailNull()
  {
    assertThrows(ServerException.class, () -> userDAO.create("SendMigSpam", null, "Sikkerhed"));
  }

  @Test
  void userNameMustBeUnique() throws ServerException
  {
    userDAO.create("Unik", "sendspamher@gmail.com", "Password123");
    assertThrows(ServerException.class, () -> userDAO.create("Unik", "Hans@Hotma.com", "ikkeHanseskode"));
  }



  @Test
  void emailContainsAtSign(){
    assertThrows(ServerException.class, () -> userDAO.create("Username", "@Email", "passwordsD123"));
    assertThrows(ServerException.class, () -> userDAO.create("godtUserName", "dethererenmail@", "passwordS123"));
    assertDoesNotThrow(() -> userDAO.create("IkkeMitNavn", "Hejsa@gmail.com", "DuHackerikkeMig"));
  }

  @Test
  void emailBoundaryUnder6AndOn6Characters()
  {
    assertThrows(ServerException.class, () -> userDAO.create("UserName", "g@123", "Passwordsersikre"));
    assertDoesNotThrow(() ->userDAO.create("UserName", "Gmai@l", "Password123"));
  }

  @Test
  void emailBoundaryOn50AndOver50Characters(){
    StringBuilder email = makeLongString(50);

    String notLongEmail = email.toString();

    assertDoesNotThrow(() -> userDAO.create("GoodName", notLongEmail, "Passwords"));

    email.append("a");

    String longEmail = email.toString();

    assertThrows(ServerException.class, () -> userDAO.create("UserName",
        longEmail, "Passwordsersikre"));
  }



  @Test
  void passwordNeedsUpperAndLowerCaseLetters(){

    assertThrows(ServerException.class, () -> userDAO.create("username", "MinEmail@gmail", "passawords"));
    assertThrows(ServerException.class, () -> userDAO.create("DetErMinNavn", "jada@gmail", "PASSWORDUPSCAPS"));

  }

  @Test
  void passwordBoundaryUnder5AndOn5Characters(){

    assertThrows(ServerException.class, () -> userDAO.create("Minuser", "minMail@gmail", "Hej1"));
    assertDoesNotThrow(() -> userDAO.create("NyBruger", "min@gmail", "Hej12"));
  }

  @Test
  void passwordBoundaryOn50AndOver50Characters()
  {
    StringBuilder password = makeLongString(49);
    password.append("A"); //et Password skal have upper og lowercase, så laver 49 og tilføljer "A"

    assertDoesNotThrow(() -> userDAO.create("mitnavn", "godmail@sda", password.toString()));
    password.append("a");
    assertThrows(ServerException.class, () -> userDAO.create("Jatak", "sendmigspam@gmail.com", password.toString()));
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
    for (int i = 0; i < length-1; i++)
    {
      email.append("a");
      if (i == 35)
      {
        email.append("@");
      }
    }
    return email;
  }
}