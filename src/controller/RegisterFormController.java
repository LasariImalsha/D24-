package controller;

import bo.BOFactory;
import bo.custom.DetailBO;
import bo.custom.RoomBO;
import bo.custom.StudentBO;
import com.jfoenix.controls.JFXComboBox;
import dto.DetailDTO;
import dto.RoomDTO;
import dto.StudentDTO;
import entity.Room;
import entity.Student;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

public class RegisterFormController {
    public AnchorPane addContext;
    public TextField txtNIC;
    public TextField txtStuName;
    public TextField txtDOB;
    public TextField txtEmail;
    public TextField txtPhoneNo;
    public TextField txtAddress;
    public TextField txtProName;
    public TextField txtDuration;
    public TextField txtFee;
    public TextField txtStuId;

    public Label lblDate;
    public Label lblTime;

    public JFXComboBox<String> cmbId;

   // public TableView<DetailDTO> tblRegistration;

  /*  public TableColumn<DetailDTO,String> colStuId;
    public TableColumn<DetailDTO,String> colProId;
    public TableColumn<DetailDTO,String> colProgram;
    public TableColumn<DetailDTO,String> colDate;

   */

    private final DetailBO detailBO= BOFactory.getInstance().getBO(BOFactory.BOType.DETAIL);
    private final RoomBO roomBO = BOFactory.getInstance().getBO(BOFactory.BOType.PROGRAM);
    private final StudentBO studentBO= BOFactory.getInstance().getBO(BOFactory.BOType.STUDENT);
    public Label lblDetailId;


    ObservableList<DetailDTO> obList= FXCollections.observableArrayList();
    private Object Program;



    //public Label lblDId;


    public void initialize(){
        // showValidation();
        // listenFieldChange(validationList);
        loadDetailId();

        loadDateAndTime();

        try {
            loadIds();
        } catch (Exception e) {
            e.printStackTrace();
        }

        //  viewJoinedProgram(txtStuId.getText());

       /* colStuId.setCellValueFactory(new PropertyValueFactory<>("stuId"));
        colProId.setCellValueFactory(new PropertyValueFactory<>("proId"));
        colProgram.setCellValueFactory(new PropertyValueFactory<>("program"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));

        */

        cmbId.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                setProgramData(newValue);
        });

    }

    private void loadDetailId() {
        String Id = detailBO.getDetailId();;
        String finalId = "R-001";

        if (Id != null) {

            String[] splitString = Id.split("-");
            int id = Integer.parseInt(splitString[1]);
            id++;

            if (id < 10) {
                finalId = "R-00" + id;
            } else if (id < 100) {
                finalId = "R-0" + id;
            } else {
                finalId = "R-" + id;
            }
            lblDetailId.setText(finalId);
        } else {
            lblDetailId.setText(finalId);
        }

    }

    private void setProgramData(String newValue) {
        RoomDTO p1 = roomBO.search(newValue);
        if (p1 == null) {
            new Alert(Alert.AlertType.WARNING, "Empty Result Set");
        } else {
            txtDuration.setText(p1.getDuration());
            txtFee.setText(String.valueOf(p1.getFee()));
            txtProName.setText(p1.getName());
        }
    }

    private void loadIds() throws Exception {
        List<RoomDTO> all = roomBO.searchAll();
        for (RoomDTO dto : all) {
            cmbId.getItems().add(dto.getId());
        }
    }

   /* private void viewJoinedProgram(String id) {
        obList.clear();
        List<DetailDTO> details=detailBO.searchId(id);
        obList.addAll(details);
        tblRegistration.setItems(obList);
    }

    */

    private void loadDateAndTime() {
        Date date = new Date();
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        lblDate.setText(f.format(date));

        Timeline time = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            LocalTime currentTime = LocalTime.now();
            lblTime.setText(currentTime.format(DateTimeFormatter.ofPattern("hh:mm:ss a"))
            );
        }),
                new KeyFrame(Duration.seconds(1))
        );
        time.setCycleCount(Animation.INDEFINITE);
        time.play();
    }

    public void registerStudentOnAction(ActionEvent actionEvent) throws Exception {
        try {

            if (!txtStuId.getText().isEmpty() && !txtStuName.getText().isEmpty() && !txtDOB.getText().isEmpty() && !txtNIC.getText().isEmpty() && !txtAddress.getText().isEmpty() && !txtPhoneNo.getText().isEmpty() && !txtEmail.getText().isEmpty() && !cmbId.getValue().isEmpty() && !txtProName.getText().isEmpty() && !txtDuration.getText().isEmpty() && !txtFee.getText().isEmpty()) {

                Student student = new Student(
                        txtStuId.getText(),
                        txtStuName.getText(),
                        txtDOB.getText(),
                        txtNIC.getText(),
                        txtAddress.getText(),
                        txtPhoneNo.getText(),
                        txtEmail.getText()
                );


                Room room = new Room(
                        cmbId.getValue(),
                        txtProName.getText(),
                        txtDuration.getText(),
                        Double.parseDouble(txtFee.getText())
                );


                Date date = java.sql.Date.valueOf(LocalDate.now());


                // if (ValidationUtil.isAllValidated(validationList)){



                StudentDTO stu1 = new StudentDTO(txtStuId.getText(), txtStuName.getText(), txtDOB.getText(), txtNIC.getText(), txtAddress.getText(), txtPhoneNo.getText(), txtEmail.getText());

                DetailDTO detail1 = new DetailDTO(lblDetailId.getText(), date, room, student);

                String sId = txtStuId.getText();

                if (studentBO.isExists(sId) != false) {
                    if (detailBO.add(detail1)) {
                        new Alert(Alert.AlertType.CONFIRMATION, "Saved..").show();
                        clearFields();
                        loadDetailId();
                    } else {
                        new Alert(Alert.AlertType.WARNING, "Try Again..").show();
                    }
                } else {
                    if (studentBO.add(stu1) && detailBO.add(detail1)) {
                        new Alert(Alert.AlertType.CONFIRMATION, "Saved..").show();
                        clearFields();
                        loadDetailId();
                    } else {
                        new Alert(Alert.AlertType.WARNING, "Try Again..").show();
                    }
                }
            }else{
                new Alert(Alert.AlertType.WARNING, "Fields are not filled properly..").show();
            }
       }catch (Exception e){
            new Alert(Alert.AlertType.WARNING, "Something went wrong..").show();
        }

        // }else{
        //  new Alert(Alert.AlertType.WARNING, "Fields are not filled properly..").show();
        // }
    }

    private void clearFields() {
        txtStuId.clear();
        txtAddress.clear();
        txtDOB.clear();
        txtDuration.clear();
        txtEmail.clear();
        txtFee.clear();
        txtNIC.clear();
        txtPhoneNo.clear();
        txtProName.clear();
        txtStuName.clear();
    }
/*
    private void viewPrograms() {
        obList.clear();
        List<DetailDTO> details=detailBO.searchId();
        obList.addAll(details);
        tblRegistration.setItems(obList);
    }

 */


    public void searchStudentOnAction(ActionEvent actionEvent) {
        String stuId = txtStuId.getText();
        Student item1= studentBO.searchId(stuId);
        if (item1==null) {
            new Alert(Alert.AlertType.WARNING, "Empty Result Set").show();
        } else {
            setData(item1);
        }

    }

    private void setData(Student p1) {
            txtStuName.setText(p1.getName());
            txtStuId.setText(p1.getId());
            txtDOB.setText(p1.getDob());
            txtPhoneNo.setText(p1.getPhoneNo());
            txtNIC.setText(p1.getNic());
            txtEmail.setText(p1.getEmail());
            txtAddress.setText(p1.getAddress());
    }
}
