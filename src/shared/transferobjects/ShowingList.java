package shared.transferobjects;

import java.util.ArrayList;

public class ShowingList
{
  private ArrayList<Showing> showingList = new ArrayList<>();

  public void addShowing(Showing showing)
  {
    showingList.add(showing);
  }

  public ArrayList<Showing> getShowingList()
  {
    return showingList;
  }
}
