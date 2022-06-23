package gui;

import domain.Joc;
import domain.Jucator;
import domain.Runda;
import dto.RezultatDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import service.Observer;
import service.Service;
import service.TalentShowException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GameController implements Observer {
        private int index = 0;
        private Joc  joc,copie_joc;
        private Service service;
        private Jucator jucator;
        private int punctaj;
        ObservableList<Runda> model = FXCollections.observableArrayList();
        ObservableList<Joc> model_final = FXCollections.observableArrayList();

    @FXML
    Button button;

     @FXML
     TableView<Runda> tableView;

    @FXML
    TableView<Joc> tableView2;


     @FXML
    TextField textFieldLinie;




    @FXML
    TableColumn<Runda,String> tableColumn1;


    @FXML
    TableColumn<Runda,Boolean> tableColumn2;

    @FXML
    TableColumn<Runda,Integer> tableColumn3;

    @FXML
    TableColumn<Joc,String>tableColumn11;

    @FXML
    TableColumn<Joc,String>tableColumn21;

    @FXML
    TableColumn<Joc,String>tableColumn31;

    public void setCopie_joc(Joc copie_joc) {
        this.copie_joc = copie_joc;
    }

    @FXML
public void initialize(){
    tableColumn1.setCellValueFactory(new PropertyValueFactory<Runda, String>("cuvant_introdus"));
    tableColumn2.setCellValueFactory(new PropertyValueFactory<Runda, Boolean>("castigat"));
    tableColumn3.setCellValueFactory(new PropertyValueFactory<Runda, Integer>("punctaj"));
    tableView.setItems(model);
    punctaj = 0;
}




    public GameController() {

    }

    public void initModel(){

        tableView.setItems(model);

        }


    public void initFinalModel(){
        tableColumn11.setCellValueFactory(new PropertyValueFactory<Joc, String>("cuvant1"));
        tableColumn21.setCellValueFactory(new PropertyValueFactory<Joc, String>("cuvant2"));
        tableColumn31.setCellValueFactory(new PropertyValueFactory<Joc, String>("cuvant3"));
        model_final.add(copie_joc);
        tableView2.setItems(model_final);

    }


    @FXML
    public void rundaNoua() {

        if (index < 3) {
            try {
                Runda runda = new Runda(textFieldLinie.getText());

                Runda new_runda = service.addRezultat(runda, joc);

                runda.setCastigat(new_runda.isCastigat());
                //runda.setCuvant_introdus(new_runda.getCuvant_introdus());
                runda.setPunctaj(new_runda.getPunctaj());
                punctaj += runda.getPunctaj();
                model.add(runda);



                if (runda.isCastigat() == true) {

                    if (runda.getCuvant_introdus().equals(joc.getCuvant1()))
                        joc.setCuvant1("");
                    if (runda.getCuvant_introdus().equals(joc.getCuvant2()))
                        joc.setCuvant1("");
                    if (runda.getCuvant_introdus().equals(joc.getCuvant3()))
                        joc.setCuvant1("");

                }
                index += 1;

                initModel();
            } catch (TalentShowException e) {
                MessageAlert.showMessage(null, Alert.AlertType.INFORMATION, "Adaugare esuata!", e.getMessage());

            }
        }
        if (index == 3) {
            button.setDisable(true);
            Runda sfarsit = new Runda();
            sfarsit.setPunctaj(punctaj);
            sfarsit.setCuvant_introdus("");
            sfarsit.setCastigat(false);
            model.add(sfarsit);
            initModel();
            initFinalModel();
        }
    }



    @FXML
    public void logOut(){
        try {

           // service.logout(crtAngajat, this);
            Stage stage = (Stage)button.getScene().getWindow();
            // create a new stage for the popup dialog.

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/views/logInView.fxml"));
            Parent root = loader.load();

            LogInController logInController = loader.getController();
            logInController.setService(service);

            FXMLLoader cloader = new FXMLLoader();
            cloader.setLocation(getClass().getResource("/views/mainView.fxml"));
            Parent croot=cloader.load();


            GameController chatCtrl =
                    cloader.<GameController>getController();
            chatCtrl.setService(service);

            logInController.setMainController(chatCtrl);
            logInController.setParent(croot);

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Log in:");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            //dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(root);
            dialogStage.setScene(scene);

            dialogStage.show();

            stage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        catch (TalentShowException e){
            MessageAlert.showMessage(null, Alert.AlertType.INFORMATION, "Iesire esuata!", e.getMessage());

        }
    }


    public void setJoc(Joc joc) {
        this.joc = joc;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public void setJucator(Jucator jucator) {
        this.jucator = jucator;
    }
}
