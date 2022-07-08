package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;

public class DashboardFormController {
    public AnchorPane mainContext;
    public AnchorPane addContext;

    public void registerOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/RegisterForm.fxml");
        Parent load = FXMLLoader.load(resource);
        addContext.getChildren().clear();
        addContext.getChildren().add(load);
    }

    public void manageRoomOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/manageRoomForm.fxml");
        Parent load = FXMLLoader.load(resource);
        addContext.getChildren().clear();
        addContext.getChildren().add(load);
    }

    public void manageStudentOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/manageStudentForm.fxml");
        Parent load = FXMLLoader.load(resource);
        addContext.getChildren().clear();
        addContext.getChildren().add(load);
    }

    public void logOutOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/LoginForm.fxml");
        Parent load = FXMLLoader.load(resource);
        mainContext.getChildren().clear();
        mainContext.getChildren().add(load);
    }

    public void viewDetailsOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/viewDetailsForm.fxml");
        Parent load = FXMLLoader.load(resource);
        addContext.getChildren().clear();
        addContext.getChildren().add(load);

    }

}
