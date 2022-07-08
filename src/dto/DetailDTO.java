package dto;

import entity.Room;
import entity.Student;


import java.util.Date;

public class DetailDTO {
    private String detailId;
    private Date date;
    private Room room;
    private Student student;

    public DetailDTO() {
    }

    public DetailDTO(String detailId, Date date, Room room, Student student) {
        this.setDetailId(detailId);
        this.setDate(date);
        this.setRoom(room);
        this.setStudent(student);
    }

    public String getDetailId() {
        return detailId;
    }

    public void setDetailId(String detailId) {
        this.detailId = detailId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
