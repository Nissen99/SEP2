package Client.view.cinemaBookingMain;

import client.factories.ViewHandler;
import client.view.chatsmart.ChatSmartViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import shared.ChatUser;
import shared.Message;

import javax.swing.*;
import java.io.IOException;

public class ChatSmartViewController
{
  @FXML public TableColumn<ChatUser, String> tableColumnStatus;
  @FXML public TableColumn<ChatUser, String> tableColumnNavn;
  @FXML private TableView<ChatUser> friendListArea;

  @FXML private TextField textBoxArea;

  @FXML private Button logOutButton;
  @FXML private Button sendButton;
  @FXML private ListView<Message> chatBoxArea;
  @FXML private Label userNameLabel;

  private ChatSmartViewModel viewModel;
  private ViewHandler viewHandler;

  public void init(ChatSmartViewModel viewModel, ViewHandler viewHandler)
  {
    this.viewHandler = viewHandler;
    this.viewModel = viewModel;

    chatBoxArea.setItems(viewModel.getMessages());
    chatBoxArea.scrollTo(chatBoxArea.getItems().size());

    System.out.println("Før ");
    friendListArea.setItems(viewModel.getChatUsers());
    friendListArea.scrollTo(friendListArea.getItems().size());

    //This is for the tableView ( Y and X axe)
    tableColumnNavn.setCellValueFactory(new PropertyValueFactory<>("userName"));
    tableColumnStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
    System.out.println("Efter");

    textBoxArea.textProperty().bindBidirectional(viewModel.txtMsgProperty());
    userNameLabel.textProperty().setValue(viewModel.getChatuserUserName());

  }

  public void OnSendButton(ActionEvent actionEvent)
  {
    viewModel.sendMessage();
    // the two methods belows just updates the list when pressed on OnSendButton
    chatBoxArea.scrollTo(chatBoxArea.getItems().size());
    friendListArea.scrollTo(friendListArea.getItems().size());

  }

  public void OnLogOutButton(ActionEvent actionEvent) throws IOException
  {
    if (JOptionPane
        .showConfirmDialog(null, "Are you sure, you want to logout?", "WARNING",
            JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
    {
      viewHandler.openView("Login");
    }
    else
    {
      // do nothing
    }
  }

  public void OnTextBoxArea(ListView.EditEvent editEvent)
  {
    //DO nothing
  }

  public void OnSettingButton(ActionEvent mouseEvent) throws IOException
  {
    if (JOptionPane
        .showConfirmDialog(null, "Do you want to open settings?",
            "Settings", JOptionPane.YES_NO_OPTION)
        == JOptionPane.YES_OPTION)
    {
      System.out.println("Open the settings?");
      viewHandler.openView("Settings");
    }
    else
    {
      // do nothing
    }
  }
}
