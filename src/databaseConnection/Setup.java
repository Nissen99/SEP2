package databaseConnection;

public class Setup
{
  public static void main(String[] args)
  {
    DataBaseModel dataBaseModel = new DataBaseModel();
    dataBaseModel.createSep2Schema();
    dataBaseModel.createUserTable();
  }


}
