package Client.view.cinemaBookingMain;

import client.factories.ViewHandler;
import client.model.ClientDataModel;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import shared.ChatUser;
import shared.ChatUserList;
import shared.Message;
import shared.MessageList;
import java.beans.PropertyChangeEvent;
import java.sql.Timestamp;


public class ChatSmartViewModel
{
  private ClientDataModel dataModel;

  private ObservableList<Message> messages;
  private ObservableList<ChatUser>chatUsers;

  private ViewHandler viewHandler;
  private StringProperty txtMsg = new SimpleStringProperty();

  public ChatSmartViewModel(ClientDataModel dataModel)
  {
    this.dataModel = dataModel;
    messages = FXCollections.observableArrayList();
    chatUsers = FXCollections.observableArrayList();
    dataModel.addPropertyChangeListener(this::onUpdated);
  }

  private void onUpdated(PropertyChangeEvent event)
  {
    this.getAllMessageList();
    this.getChatUserList();

  }

  public void getAllMessageList()
  {

    Platform.runLater(()->{

    MessageList messageList = dataModel.getAllMessageList();
    messages.removeAll(messages);
    for (int i = 0; i < messageList.messageSize(); i++)
    {
      messages.add(messageList.getMessage(i));
    }
     });

  }
  public void getChatUserList()
  {
    Platform.runLater(()-> {
      ChatUserList chatUserList = dataModel.getChatUserList();
      chatUsers.removeAll(chatUsers);
      for (int i = 0; i <chatUserList.chatUserSize() ; i++)
      {
        chatUsers.add(chatUserList.getChatUser(i));
      }

    });
      }

      public String getChatuserUserName()
      {
        return dataModel.getChatUser().getUserName();
      }

  public void sendMessage()
  {
    Timestamp timestamp = new Timestamp(System.currentTimeMillis());
    Message message = new Message(txtMsg.getValue(), timestamp,dataModel.getChatUser());
    dataModel.sendMessage(message);
    txtMsg.setValue("");
  }

  public ObservableList<ChatUser> getChatUsers()
  {
    return chatUsers;
  }

  public ObservableList<Message> getMessages()
  {
    return messages;
  }

  public StringProperty txtMsgProperty()
  {
    return txtMsg;
  }

}

