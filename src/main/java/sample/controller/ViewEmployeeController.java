package sample.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import sample.dto.EmployeeDto;
import sample.factory.PopupFactory;
import sample.handler.EmployeeLoadedHandler;
import sample.rest.EmployeeRestClient;

import java.net.URL;
import java.util.ResourceBundle;

public class ViewEmployeeController implements Initializable {

    private  final EmployeeRestClient employeeRestClient;


    @FXML
    private BorderPane viewEmployeeBorderPane;

    @FXML
    private Button okButton;

    @FXML
    private TextField firstNameTextField;

    @FXML
    private TextField lastNameTextField;

    @FXML
    private TextField salaryTextField;

    public ViewEmployeeController() {
        employeeRestClient = new EmployeeRestClient();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        firstNameTextField.setEditable(false);
        lastNameTextField.setEditable(false);
        salaryTextField.setEditable(false);
        initializeOkButton();


    }
    public void loadEmployeeData (Long idEmployee, EmployeeLoadedHandler handler){
        Thread thread = new Thread(()->{
           EmployeeDto dto = employeeRestClient.getEmployees(idEmployee);
            Platform.runLater(()->{
                firstNameTextField.setText(dto.getFirstName());
                lastNameTextField.setText(dto.getLastName());
                salaryTextField.setText(dto.getSalary());
                handler.handle();
            });
        });
        thread.start();

    }

    private void initializeOkButton() {
        okButton.setOnAction((x)->{
            getStage().close();
        });
    }

    private Stage getStage (){

        return (Stage) viewEmployeeBorderPane.getScene().getWindow();
    }
}
