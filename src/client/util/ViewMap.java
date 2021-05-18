package client.util;

import java.util.HashMap;
import java.util.Map;

/**
 * Singleton
 */
public class ViewMap
{
  private static Map<String, String> viewMap;
  private static ViewMap instance;

    private ViewMap(){
    viewMap = new HashMap<>();
    viewMap.put("Movie List", "movieList/movieList");
    viewMap.put("Showing List", "showingList/showingList");
    viewMap.put("Seat", "seatView/seat");
    viewMap.put("Login", "loginView/login");
    viewMap.put("Create User", "createUserView/createUser");
    viewMap.put("Edit Movie", "adminView/editView/editMovie");
    viewMap.put("Edit Showing", "adminView/editView/editShowing");
    viewMap.put("Add Showing", "adminView/editView/addShowing");
    viewMap.put("Admin", "adminView/admin");
    viewMap.put("Edit Booking", "adminView/editView/editBooking");
  }
  public static ViewMap getInstance(){
      if (instance == null){
        instance = new ViewMap();
      }
      return instance;
  }
  public static String getPath(String title){

    return viewMap.get(title);
  }
}
