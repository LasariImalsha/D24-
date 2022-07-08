package bo.custom;

import bo.SuperBO;
import dto.RoomDTO;

import java.util.List;

public interface RoomBO extends SuperBO {
    public boolean add(RoomDTO roomDTO) throws Exception;

    public boolean delete(String id) throws Exception;

    public boolean update(RoomDTO roomDTO) throws Exception;

    public List<RoomDTO> searchAll() throws Exception;

    public RoomDTO search(String newValue);

}
