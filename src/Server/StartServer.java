package Server;

import shared.transferobjects.Movie;
import shared.transferobjects.MovieList;
import shared.transferobjects.Showing;
import shared.transferobjects.ShowingList;

import java.sql.Time;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

public class StartServer
{
  public static void main(String[] args)
  {

    //startserver TODO
    MovieList movieList = new MovieList();
    Movie movie = new Movie("SpiderMan", movieList);
    Movie movie1 = new Movie("Batman", movieList);
    Date date = new Date();
    ShowingList showingList = new ShowingList();
    Showing showing = new Showing(date, movie, showingList);
    System.out.println(movieList.getMovieListSize());
    System.out.println(showingList.getShowingListSize());

  }
}
