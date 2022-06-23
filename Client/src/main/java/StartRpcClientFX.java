import gui.LogInController;
import gui.GameController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import rpcprotocol.ServicesRpcProxy;
import service.Service;

import java.io.IOException;
import java.util.Properties;

public class StartRpcClientFX extends Application {
    private Stage primaryStage;

    private static int defaultChatPort = 55575;
    private static String defaultServer ="localhost";


    public void start(Stage primaryStage) throws Exception {
        System.out.println("In start");
        Properties clientProps = new Properties();
        try {
            clientProps.load(StartRpcClientFX.class.getResourceAsStream("/client.properties"));
            System.out.println("Client properties set. ");
            clientProps.list(System.out);
        } catch (IOException e) {
            System.err.println("Cannot find client.properties " + e);
            return;
        }
        String serverIP = clientProps.getProperty("server.host", defaultServer);
        int serverPort = defaultChatPort;

        try {
            serverPort = Integer.parseInt(clientProps.getProperty("server.port"));
        } catch (NumberFormatException ex) {
            System.err.println("Wrong port number " + ex.getMessage());
            System.out.println("Using default port: " + defaultChatPort);
        }
        System.out.println("Using server IP " + serverIP);
        System.out.println("Using server port " + serverPort);

        Service server = new ServicesRpcProxy(serverIP, serverPort);

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/views/logInView.fxml"));
        Parent root = loader.load();


        LogInController ctrl =
                loader.<LogInController>getController();
        ctrl.setService(server);

        FXMLLoader gameloader = new FXMLLoader();
        gameloader.setLocation(getClass().getResource("/views/GameView.fxml"));
        Parent croot= gameloader.load();


        GameController chatCtrl =
                gameloader.<GameController>getController();
        chatCtrl.setService(server);

      ctrl.setMainController(chatCtrl);
      ctrl.setParent(croot);


        primaryStage.setTitle("Talent Show");
        primaryStage.setScene(new Scene(root, 700, 500));
        primaryStage.show();
    }
}
