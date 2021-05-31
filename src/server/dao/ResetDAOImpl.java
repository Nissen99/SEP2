package server.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Denne klasse wiper databasen, dette sker ved at droppe den, og lave den igen fra bunden
 * Dette bliver brugt ved test
 */
public class ResetDAOImpl extends BaseDAO implements ResetDAO
{
  private String sqlstatement = "DROP SCHEMA IF EXISTS bioDatabase CASCADE;\n"
      + "CREATE SCHEMA bioDatabase;\n" + "SET SCHEMA 'biodatabase';\n" + "\n"
      + "\n" + "CREATE TABLE IF NOT EXISTS User_\n" + "(\n"
      + "    userId   SERIAL PRIMARY KEY,\n"
      + "    userName VARCHAR(25) UNIQUE NOT NULL ,\n" + "\n"
      + "    -- Email og password er deklareret som varchar's med deres default limit.\n"
      + "    -- Det kommer til at reserverer mere plads, men vi ofrer det for\n"
      + "    -- at kunne kaste vores egen exception.\n"
      + "    email    VARCHAR NOT NULL,\n" + "    password VARCHAR NOT NULL\n"
      + ");\n" + "\n" + "\n" + "CREATE TABLE IF NOT EXISTS Movie\n" + "(\n"
      + "    movieId SERIAL PRIMARY KEY,\n" + "    title   VARCHAR\n" + ");\n"
      + "\n" + "CREATE TABLE IF NOT EXISTS Hall\n" + "(\n"
      + "    --Her bruges der ikke Serial til PK, da det skal skrives ind gennem java\n"
      + "    hallNo       VARCHAR(1) PRIMARY KEY,\n"
      + "    maxSeatInRow INTEGER,\n" + "    maxRows      INTEGER\n" + ");\n"
      + "\n" + "\n" + "CREATE TABLE IF NOT EXISTS Showing\n" + "(\n"
      + "    showingId SERIAL PRIMARY KEY,\n" + "    time      TIMESTAMP,\n"
      + "    movieId   INTEGER,\n" + "    hallNo    VARCHAR(1),\n" + "\n"
      + "    FOREIGN KEY (movieId) REFERENCES Movie (movieId) ON DELETE CASCADE,\n"
      + "    FOREIGN KEY (hallNo) REFERENCES Hall (hallNo)\n" + ");\n" + "\n"
      + "\n" + "CREATE TABLE IF NOT EXISTS Seat\n" + "(\n"
      + "    seatNo VARCHAR(10) PRIMARY KEY,\n" + "    hallNo VARCHAR(1),\n"
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
      + "    FOREIGN KEY (seatNo) REFERENCES Seat (seatNo)\n" + ");\n" + "\n"
      + "\n" + "\n"
      + "-- Vi valgte at gå fra at lave domæner for at vi kunne lave tilpassede exception beskeder,\n"
      + "-- ved brug af triggers og funktioner.\n"
      + "-- Derudover valgte vi at opdele forskellige scenarier for at kunne kaste en bestem fejl\n"
      + "-- samt dens besked for at give brugeren relevante oplysninger om fejlene de har fået.\n"
      + "CREATE OR REPLACE FUNCTION emailError()\n" + "    RETURNS TRIGGER\n"
      + "    LANGUAGE plpgsql\n" + "AS\n" + "$$\n" + "BEGIN\n"
      + "    IF (NEW.email NOT LIKE '_%@%.%_') THEN\n"
      + "        RAISE EXCEPTION 'Email skal være en gyldig mail der indeholder @ og punktum';\n"
      + "    ELSEIF (LENGTH(new.email) NOT BETWEEN 6 AND 50) THEN\n"
      + "        RAISE EXCEPTION 'Email skal være mellem 6 og 50 tegn';\n"
      + "    END IF;\n" + "    RETURN new;\n" + "END;\n" + "$$;\n" + "\n"
      + "CREATE TRIGGER emailError\n" + "    BEFORE INSERT OR UPDATE\n"
      + "    ON User_\n" + "    FOR EACH ROW\n"
      + "EXECUTE PROCEDURE emailError();\n" + "\n" + "\n" + "\n"
      + "CREATE OR REPLACE FUNCTION passwordError()\n" + "    RETURNS TRIGGER\n"
      + "    LANGUAGE plpgsql\n" + "AS\n" + "$$\n" + "BEGIN\n"
      + "    IF (LENGTH(NEW.password) NOT BETWEEN 5 AND 50) THEN\n"
      + "        RAISE EXCEPTION 'Password skal være mellem 5 og 50 tegn langt';\n"
      + "    ELSEIF (NEW.password = LOWER(NEW.password) OR NEW.password = UPPER(new.password)) THEN\n"
      + "        RAISE EXCEPTION 'Password skal indeholde små bogstaver og store bogstaver';\n"
      + "    END IF;\n" + "    RETURN new;\n" + "END;\n" + "$$;\n" + "\n"
      + "CREATE TRIGGER passwordError\n" + "    BEFORE INSERT OR UPDATE\n"
      + "    ON User_\n" + "    FOR EACH ROW\n"
      + "EXECUTE PROCEDURE passwordError();\n";
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
