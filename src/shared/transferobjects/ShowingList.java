package shared.transferobjects;

import java.util.ArrayList;

public class ShowingList
{
  private ArrayList<Showing> showingList;

  public ShowingList()
  {
    showingList = new ArrayList<>();
  }

  public void addShowing(Showing showing)
  {
    showingList.add(showing);
  }

  public void removeShowing(int index) {
    showingList.remove(index);
  }

  public ArrayList<Showing> getShowingList()
  {
    return showingList;
  }

  public int getShowingListSize() {
    return showingList.size();
  }
}
