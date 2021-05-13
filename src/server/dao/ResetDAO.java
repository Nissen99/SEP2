package server.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ResetDAO extends BaseDAO
{
  private String sqlstatement = "DROP SCHEMA IF EXISTS bioDatabase CASCADE;\n"
      + "CREATE SCHEMA bioDatabase;\n" + "SET SCHEMA 'biodatabase';\n" + "\n"
      + "\n" + "\n" + "CREATE DOMAIN dateEU AS DATE\n"
      + "    CHECK ( VALUE > TO_DATE('01-Jan-1850', 'dd-Mon-yyyy'));\n"
      + "CREATE DOMAIN email_ AS VARCHAR(60)\n"
      + "    CHECK ( VALUE LIKE '%@%');\n" + "\n"
      + "--CREATE DOMAIN password as VARCHAR\n"
      + "--CHECK (VALUE LIKE CHAR_LENGTH(VALUE) >= 3);\n" + "\n" + "\n" + "\n"
      + "CREATE TABLE IF NOT EXISTS User_\n" + "(\n"
      + "    userId SERIAL PRIMARY KEY,\n" + "    userName  VARCHAR(25),\n"
      + "    email email_,\n" + "    password varchar(25)\n" + ");\n" + "\n"
      + "\n" + "CREATE TABLE IF NOT EXISTS Movie\n" + "(\n"
      + "    movieId     SERIAL PRIMARY KEY,\n" + "    title       VARCHAR,\n"
      + "    releaseDate dateEU CHECK ( DATE_PART('year', releaseDate) BETWEEN 1900 AND (DATE_PART('year', NOW()) + 5))\n"
      + ");\n" + "\n" + "CREATE TABLE IF NOT EXISTS Hall\n" + "(\n"
      + "    --Her bruges der ikke Serial til PK, da det skal skrives ind gennem java\n"
      + "    hallNo       VARCHAR(1) PRIMARY KEY,\n"
      + "    maxSeatInRow INTEGER,\n" + "    maxRows      INTEGER\n" + ");\n"
      + "\n" + "\n" + "CREATE TABLE IF NOT EXISTS Showing\n" + "(\n"
      + "    showingId SERIAL PRIMARY KEY,\n"
      + "    date      dateEU CHECK ( DATE_PART('year', date) BETWEEN 1900 AND (DATE_PART('year', NOW()) + 1)),\n"
      + "    time      TIMESTAMP,\n" + "    movieId   INTEGER,\n"
      + "    hallNo    VARCHAR(1),\n" + "\n"
      + "    FOREIGN KEY (movieId) REFERENCES Movie (movieId) ON DELETE CASCADE,\n"
      + "    FOREIGN KEY (hallNo) REFERENCES Hall (hallNo)\n" + ");\n" + "\n"
      + "\n" + "CREATE TABLE IF NOT EXISTS Seat\n" + "(\n"
      + "    seatNo     VARCHAR(10) PRIMARY KEY,\n"
      + "    hallNo     VARCHAR(1),\n" + "\n"
      + "    FOREIGN KEY (hallNo) REFERENCES Hall (hallNo)\n" + ");\n" + "\n"
      + "CREATE TABLE IF NOT EXISTS Booking\n" + "(\n"
      + "    bookingId SERIAL PRIMARY KEY,\n" + "    showingId INTEGER,\n"
      + "    userId    INTEGER,\n" + "\n"
      + "    FOREIGN KEY (showingId) REFERENCES Showing (showingId) ON DELETE CASCADE,\n"
      + "    FOREIGN KEY (userId) REFERENCES user_ (userId)\n" + ");\n" + "\n"
      + "CREATE TABLE IF NOT EXISTS BookingSpec\n" + "(\n"
      + "    bookingId SERIAL,\n" + "    seatNo    VARCHAR(10),\n" + "\n"
      + "    PRIMARY KEY (bookingId, seatNo),\n"
      + "    FOREIGN KEY (bookingId) REFERENCES Booking (bookingId) ON DELETE CASCADE,\n"
      + "    FOREIGN KEY (seatNo) REFERENCES Seat (seatNo)\n" + ");";
  public void reset() {
    try(Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(sqlstatement);
      statement.execute();
    }
    catch (SQLException throwables)
    {
      throwables.printStackTrace();
    }
  }
}
