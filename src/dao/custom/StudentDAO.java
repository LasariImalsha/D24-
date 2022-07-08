package dao.custom;

import dao.SuperDAO;
import dto.StudentDTO;
import entity.Student;

import java.util.ArrayList;
import java.util.List;

public interface StudentDAO extends SuperDAO<Student, String> {

    boolean isExist(String id);


}
