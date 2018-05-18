/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author David Bubeník (bubenda1)
 */
public class Main extends Application {

    public static EntityManager em = null;

    @Override
    public void start(Stage stage) throws Exception {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PU");

        try {
            em = emf.createEntityManager();
            FXMLLoader Loader = new FXMLLoader(getClass().getClassLoader().getResource("Main.fxml"));

            try {
                Loader.load();
            } catch (IOException ex) {
                Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
            }

            MainController mainController = Loader.getController();
            mainController.updatePersons();
            mainController.updateBanks();
            Parent p = Loader.getRoot();
            stage.setScene(new Scene(p));
            stage.setTitle("Database of banks and persons");
            stage.show();
        } catch (Exception e) {
            System.out.println("ERROR WHILE CONNECTING TO DATABASE!");
            FXMLLoader Loader = new FXMLLoader(getClass().getClassLoader().getResource("Error.fxml"));

            try {
                Loader.load();
            } catch (IOException ex) {
                Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
            }

            ErrorController errorController = Loader.getController();
            errorController.setData(
                    "Database connection error",
                    e.toString()
            );
            stage.setScene(new Scene((AnchorPane) Loader.getRoot()));
            stage.setTitle("Database of banks and persons");
            stage.show();
        }

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
