package server.model;

import shared.exception.ServerException;
import shared.transferobjects.*;
import shared.util.PropertyChangeSubject;

import javax.security.auth.login.LoginException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

public interface ServerModel
{



  ArrayList<Movie> getMovieList() throws SQLException;

}
