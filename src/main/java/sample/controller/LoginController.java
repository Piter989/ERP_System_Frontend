package sample.controller;


import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sample.dto.OperatorCredentialsDto;
import sample.factory.PopupFactory;
import sample.handler.EmployeeLoadedHandler;
import sample.rest.Authenticatior;
import sample.rest.AuthenticatiorImplo;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    private static final String APP_FXML = "/fxml/app.fxml";
    private static final String APP_TITLE = "ERP System";
    private PopupFactory popupFactory;
    private Authenticatior authenticatior;


    @FXML
    private Button exitButton;

    @FXML
    private Button loginButton;

    @FXML
    private AnchorPane loginAnchorPane;

    @FXML
    private TextField loginTextField;

    @FXML
    private PasswordField passwordTextField;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initializeExitButton();
        initializeLoginButton();


    }


    public  LoginController() {
        popupFactory = new PopupFactory();
        authenticatior = new AuthenticatiorImplo();
    }

    private void initializeLoginButton() {
        loginButton.setOnAction((x) -> {

        performAuthentication();


        });
    }

    private void performAuthentication() {
        Stage waitingPopup = popupFactory.createWaitingPopup("Connecting to the server...");
        waitingPopup.show();
        String login = loginTextField.getText();
        String password = passwordTextField.getText();
        OperatorCredentialsDto dto = new OperatorCredentialsDto();
        dto.setLogin(login);
        dto.setPassword(password);
        authenticatior.authenticator(dto,(authenticationResult) -> {
            Platform.runLater(()->{
                waitingPopup.close();

                if(authenticationResult.isAuthenticated()){
                    openAppCloseLoginStage();


                }else {
                    showIncorrectCredentialsMessage();
                }
            });
        });
    }

    private void showIncorrectCredentialsMessage() {
        //TODO
        System.out.println("Incorrect credentials");
    }

    private void openAppCloseLoginStage() {
        Stage appStage = new Stage();
        Parent appRoot = null;

        try {
           appRoot = FXMLLoader.load(getClass().getResource(APP_FXML));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(appRoot,1024,768);
        appStage.initStyle(StageStyle.UNDECORATED);
        appStage.setTitle(APP_TITLE);
        appStage.setScene(scene);
        appStage.show();
        getStage().close();
    }



    private void initializeExitButton() {
        exitButton.setOnAction((x) -> {
        getStage().close();
        });
    }

    private Stage getStage (){

        return (Stage) loginAnchorPane.getScene().getWindow();
    }
}




