package model.tm;

import java.util.Date;

public class DetailTM {
    private String id;
    private Date date;
    private String program;
    private String student;

    public DetailTM() {
    }

    public DetailTM(String id, Date date, String program, String student) {
        this.setId(id);
        this.setDate(date);
        this.setProgram(program);
        this.setStudent(student);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    public String getStudent() {
        return student;
    }

    public void setStudent(String student) {
        this.student = student;
    }
}
