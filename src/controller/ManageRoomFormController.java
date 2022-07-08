package controller;

import bo.BOFactory;
import bo.custom.RoomBO;
import dto.RoomDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

public class ManageRoomFormController {
    public TextField txtId;
    public TextField txtDuration;
    public TextField txtType;
    public TextField txtFee;

    public TableColumn<RoomDTO,String> colId;
    public TableColumn<RoomDTO,String> colType;
    public TableColumn<RoomDTO,String> colDuration;
    public TableColumn<RoomDTO,String> colFee;
    public TableView<RoomDTO> tblRoom;
    public TextField txtId1;
    public TextField txtDuration1;
    public TextField txtType1;
    public TextField txtFee1;



    ObservableList<RoomDTO> obList= FXCollections.observableArrayList();

    private final RoomBO roomBO = BOFactory.getInstance().getBO(BOFactory.BOType.PROGRAM);

    public void initialize(){
       // showValidation();
        try {
            viewAllPrograms();
        } catch (Exception e) {
            e.printStackTrace();
        }

        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colType.setCellValueFactory(new PropertyValueFactory<>("name"));
        colDuration.setCellValueFactory(new PropertyValueFactory<>("duration"));
        colFee.setCellValueFactory(new PropertyValueFactory<>("fee"));

        tblRoom.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue)-> {
                    setProgramData(newValue);
                }
        );
       // listenFieldChange(validationList);

    }

    private void viewAllPrograms() throws Exception {
        obList.clear();
        List<RoomDTO> programs= roomBO.searchAll();
        obList.addAll(programs);
        tblRoom.setItems(obList);
    }

    private void setProgramData(RoomDTO i) {
        if(i!=null) {
            txtId.setText(i.getId());
            txtType.setText(i.getName());
            txtDuration.setText(i.getDuration());
            txtFee.setText(String.valueOf(i.getFee()));

            txtId1.setText(i.getId());
            txtType1.setText(i.getName());
            txtDuration1.setText(i.getDuration());
            txtFee1.setText(String.valueOf(i.getFee()));
        }
    }

    public void addRoomOnAction(ActionEvent actionEvent) throws Exception {
       // if (ValidationUtil.isAllValidated(validationList)){
            RoomDTO item1 = new RoomDTO(txtId.getText(),txtType.getText(),txtDuration.getText(),Double.parseDouble(txtFee.getText()));
            if(roomBO.add(item1)) {
                new Alert(Alert.AlertType.CONFIRMATION, "Saved..").show();
                viewAllPrograms();
                clearFields();
            }else {
                new Alert(Alert.AlertType.WARNING, "Try Again..").show();
            }
       // }else{
          //  new Alert(Alert.AlertType.WARNING, "Fields are not filled properly..").show();
       // }
    }



    public void deleteProgramOnAction(ActionEvent actionEvent) throws Exception {
     //   if (ValidationUtil.isAllValidated(validationList)){
            if (roomBO.delete(txtId1.getText())){
                new Alert(Alert.AlertType.CONFIRMATION, "Deleted").show();
                viewAllPrograms();
                clearFields();
            }else{
                new Alert(Alert.AlertType.WARNING, "Try Again").show();
            }
       // }else{
          //  new Alert(Alert.AlertType.WARNING, "Fields are not filled properly..").show();
       // }
    }

    public void updateRoomOnAction(ActionEvent actionEvent) throws Exception {
       // if (ValidationUtil.isAllValidated(validationList)){
            RoomDTO program1 = new RoomDTO(txtId1.getText(),txtType1.getText(),txtDuration1.getText(),Double.parseDouble(txtFee1.getText()));
            if (roomBO.update(program1)) {
                new Alert(Alert.AlertType.CONFIRMATION, "Updated..").show();
                viewAllPrograms();
                clearFields();
            }else {
                new Alert(Alert.AlertType.WARNING, "Try Again").show();
            }
        //}else{
          //  new Alert(Alert.AlertType.WARNING, "Fields are not filled properly..").show();
        //}
    }

    public void searchIdOnAction(ActionEvent actionEvent) {
       /* String id = txtId1.getText();
        ProgramDTO item1= programBO.searchId(id);
        if (item1==null) {
            new Alert(Alert.AlertType.WARNING, "Empty Result Set").show();
        } else {
            setData(item1);
        }*/
    }

    private void clearFields() {
        txtId.clear();
        txtType.clear();
        txtDuration.clear();
        txtFee.clear();
        txtId1.clear();
        txtType1.clear();
        txtDuration1.clear();
        txtFee1.clear();
    }

}
