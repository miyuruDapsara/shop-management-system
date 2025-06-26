package controller;

import bo.BoFactory;
import bo.Botype;
import bo.custom.ApplicationUserBo;
import dto.ApplicationUserDto;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class SignupFormController {

    public AnchorPane container;
    public TextField txtFullName;
    public TextField txtEmail;
    public TextField txtpassword;

    private ApplicationUserBo bo = BoFactory.getInstance().getBo(Botype.APPLICATION_USER);

    public void registerOnAction(ActionEvent actionEvent) {
      try {
          boolean isSaved =bo.signup(
                  new ApplicationUserDto(txtFullName.getText(),txtEmail.getText(),txtpassword.getText()
                  )
          );
          if (isSaved){
              new Alert(Alert.AlertType.INFORMATION,"User saved successfully").show();
              setUi("LoginForm");
          }else {
              new Alert(Alert.AlertType.ERROR,"something went wrong").show();
          }
      } catch (Exception e) {
          System.out.println(e.getMessage());
          new Alert(Alert.AlertType.ERROR,"something went wrong").show();
      }
    }

    public void loginOnAction(ActionEvent actionEvent) throws IOException {
        setUi("LoginForm");
    }

    private  void setUi(String path) throws IOException {
        Stage stage =(Stage) container.getScene().getWindow();
        stage.setScene(
                new Scene(FXMLLoader.load(getClass().getResource("../view/"+path+".fxml")))
        );
    }
}
