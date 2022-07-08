package bo.custom;

import bo.SuperBO;
import dto.StudentDTO;
import entity.Student;

import java.util.List;

public interface StudentBO extends SuperBO {
    public boolean add(StudentDTO studentDTO) throws Exception;

    public boolean delete(String id) throws Exception;

    public boolean update(StudentDTO studentDTO) throws Exception;

    public List<StudentDTO> searchAll() throws Exception;

    Student searchId(String stuId);

    boolean isExists(String sId);


}
