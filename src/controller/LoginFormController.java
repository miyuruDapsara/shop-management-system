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

public class LoginFormController {
    public AnchorPane container;
    public TextField txtEmail;
    public TextField txtPassword;


    private ApplicationUserBo applicationUserBo = BoFactory.getInstance().getBo(Botype.APPLICATION_USER);
    public void accessDashboardOnAction(ActionEvent actionEvent) {
        try {
            ApplicationUserDto selectedUser = applicationUserBo.login(txtEmail.getText());
            if (selectedUser==null){
                new Alert(Alert.AlertType.WARNING, "User not found").show();
                return;
            }
            if (txtPassword.decryptPassword(
                    selectedUser.getPassword(),
                    txtPassword.getText()
            )){
                setUi("DashboardForm");
            }
            else {
                new Alert(Alert.AlertType.WARNING, "Incorrect Password").show();
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void createAnAccountOnAction(ActionEvent actionEvent) throws IOException {
       setUi("SignupForm");
    }

    private  void setUi(String path) throws IOException {
        Stage stage =(Stage) container.getScene().getWindow();
        stage.setScene(
                new Scene(FXMLLoader.load(getClass().getResource("../view/"+path+".fxml")))
        );
    }
}
