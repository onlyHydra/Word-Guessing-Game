package gui;

import domain.Joc;
import domain.Jucator;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import service.Service;
import service.TalentShowException;

public class LogInController {
    private Service service;
    private Jucator jucator;

    GameController mainCtrl;

    Parent mainParent;

    @FXML
    private TextField textFieldUsername;
    @FXML
    private TextField textFieldParola;
    @FXML
    Button button;

    @FXML
    private void logIn(ActionEvent actionEvent) {

        String username = textFieldUsername.getText().toString();
        String passwd = textFieldParola.getText().toString();
        jucator = service.findOneJucatorByCredentials(username);// to be changed
        if(jucator==null){
            MessageAlert.showMessage(null, Alert.AlertType.INFORMATION, "Logare esuata!", "Acest jurat nu exista");

        }
        else {
            try{
                service.login(jucator, mainCtrl);
                // Util.writeLog("User succesfully logged in "+crtUser.getId());
                Stage stage=new Stage();
                stage.setTitle("Welcome back  " +jucator.getAlias()+"!");
                stage.setScene(new Scene(mainParent));
                stage.setOnCloseRequest(new EventHandler<WindowEvent>(){
                    @Override
                    public void handle(WindowEvent event) {
                        mainCtrl.logOut();
                        System.exit(0);
                    }
                });
                stage.show();
                Joc joc = service.getJoc();
                mainCtrl.setService(service);
                mainCtrl.setJoc(joc);
                mainCtrl.setJucator(jucator);
                mainCtrl.setCopie_joc(joc);
                mainCtrl.initModel();

                ((Node)(actionEvent.getSource())).getScene().getWindow().hide();

            }   catch (TalentShowException e) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Talent Show");
                alert.setHeaderText("Authentication failure");
                alert.setContentText("Wrong username or password");
                alert.showAndWait();
            }

        }

    }

    public void setService(Service service) {
        this.service=service;
    }

    public void setMainController(GameController chatController) {
        this.mainCtrl = chatController;
    }

    public void setParent(Parent p){
        mainParent=p;
    }


    @FXML
    private void initialize() {
    }

}
