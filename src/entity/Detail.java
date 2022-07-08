package entity;

import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Detail implements SuperEntity{
    @Id
    private String detailId;
    @CreationTimestamp
    private Date date;

    @ManyToOne(cascade = CascadeType.ALL)
    private Room room;
    @ManyToOne(cascade = CascadeType.ALL)
    private Student student;

    public Detail() {
    }

    public Detail(String detailId, Date date, Room room, Student student) {
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

    @Override
    public String toString() {
        return "Detail{" +
                "detailId='" + detailId + '\'' +
                ", date=" + date +
                ", program=" + room +
                ", student=" + student +
                '}';
    }
}
