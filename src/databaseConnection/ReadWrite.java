package databaseConnection;

public class ReadWrite
{
  private int activeReaders, activeWriters, waitingWriters;
  private DataBaseModel dataBaseModel;

  public ReadWrite(DataBaseModel dataBaseModel)
  {
    activeReaders = activeWriters = waitingWriters = 0;
    this.dataBaseModel = dataBaseModel;
  }

  public synchronized void acquireRead()
  {
    while (activeWriters >= 1 || waitingWriters > 0)
    {
      try
      {
        wait();
      }
      catch (InterruptedException e)
      {
        e.printStackTrace();
      }
      activeReaders++;

    }
  }

}
