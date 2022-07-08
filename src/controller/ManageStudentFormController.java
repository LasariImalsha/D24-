package controller;

import bo.BOFactory;
import bo.custom.StudentBO;
import dto.StudentDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.util.List;

public class ManageStudentFormController {
    public AnchorPane addContext;

    public TextField txtDOB;

    public TextField txtAddress;
    public TextField txtPhoneNo;
    public TextField txtEmail;
    public TextField txtName;
    public TextField txtNIC;
    public TextField txtStuId;

    public TableColumn<StudentDTO,String> colId;
    public TableColumn<StudentDTO,String> colName;
    public TableColumn<StudentDTO,String> colDOB;
    public TableColumn<StudentDTO,String> colNIC;
    public TableColumn<StudentDTO,String> colAddress;
    public TableColumn<StudentDTO,String> colPhoneNo;
    public TableColumn<StudentDTO,String> colEmail;

    public TableView<StudentDTO> tblStudent;

   // public JFXComboBox<String> cmbStudentId;

    private final StudentBO studentBO= BOFactory.getInstance().getBO(BOFactory.BOType.STUDENT);

    ObservableList<StudentDTO> obList= FXCollections.observableArrayList();



    public void initialize() {

        //showValidation();
        try {
            viewAllStudents();
        } catch (Exception e) {
            e.printStackTrace();
        }

       /* try {
            loadStudentId();
        } catch (Exception throwables) {
            throwables.printStackTrace();
        }

        */

        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colDOB.setCellValueFactory(new PropertyValueFactory<>("dob"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colNIC.setCellValueFactory(new PropertyValueFactory<>("nic"));
        colPhoneNo.setCellValueFactory(new PropertyValueFactory<>("phoneNo"));

       /* cmbStudentId.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            setStudentData(newValue);
        });

        */
        tblStudent.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue)-> {
                    setStudentData(newValue);
                }
        );


        //listenFieldChange(validationList);
    }

    private void setStudentData(StudentDTO s1) {
        txtStuId.setText(s1.getId());
        txtName.setText(s1.getName());
        txtDOB.setText(s1.getDob());
        txtAddress.setText(s1.getAddress());
        txtEmail.setText(s1.getEmail());
        txtNIC.setText(s1.getNic());
        txtPhoneNo.setText(s1.getPhoneNo());


    }


/*
    private void loadStudentId() throws Exception {
        List<StudentDTO> student=studentBO.searchAll();
        for (StudentDTO dto : student) {
            cmbStudentId.getItems().add(dto.getsId());
        }
    }


     */


    public void updateOnAction(ActionEvent actionEvent) throws Exception {
       // if (ValidationUtil.isAllValidated(validationList)){
            StudentDTO item1 = new StudentDTO(txtStuId.getText(),txtName.getText(),txtDOB.getText(),txtNIC.getText(),txtAddress.getText(),txtPhoneNo.getText(),txtEmail.getText());
            if (studentBO.update(item1)) {
                new Alert(Alert.AlertType.CONFIRMATION, "Updated..").show();
                viewAllStudents();
                clearFields();
            }else {
                new Alert(Alert.AlertType.WARNING, "Try Again").show();
            }
       // }else{
         //   new Alert(Alert.AlertType.WARNING, "Fields are not filled properly..").show();
        //}
    }

    private void clearFields() {
        txtStuId.clear();
        txtEmail.clear();
        txtPhoneNo.clear();
        txtAddress.clear();
        txtNIC.clear();
        txtDOB.clear();
        txtName.clear();
    }

    private void viewAllStudents() throws Exception {
        obList.clear();
        List<StudentDTO> items=studentBO.searchAll();
        obList.addAll(items);
        tblStudent.setItems(obList);
    }

    public void deleteOnAction(ActionEvent actionEvent) throws Exception {
       // if (ValidationUtil.isAllValidated(validationList)){
            if (studentBO.delete(txtStuId.getText())){
                new Alert(Alert.AlertType.CONFIRMATION, "Deleted").show();
                viewAllStudents();
                clearFields();
            }else{
                new Alert(Alert.AlertType.WARNING, "Try Again").show();
            }
       // }else{
          //  new Alert(Alert.AlertType.WARNING, "Fields are not filled properly..").show();
       // }
    }
}
