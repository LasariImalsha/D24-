package bo.custom.impl;

import bo.custom.RoomBO;
import dao.DAOFactory;
import dao.custom.impl.RoomDAOImpl;
import dto.RoomDTO;
import entity.Room;

import java.util.ArrayList;
import java.util.List;

public class RoomBOImpl implements RoomBO {

    private final RoomDAOImpl programDAO = DAOFactory.getInstance().getDAO(DAOFactory.DAOType.PROGRAM);

    @Override
    public boolean add(RoomDTO roomDTO) throws Exception {
        return programDAO.add(new Room(
                roomDTO.getId(),
                roomDTO.getName(),
                roomDTO.getDuration(),
                roomDTO.getFee()
        ));
    }

    @Override
    public boolean delete(String id) throws Exception {
        return programDAO.delete(id);
    }

    @Override
    public boolean update(RoomDTO roomDTO) throws Exception {
        return programDAO.update(new Room(
                roomDTO.getId(),
                roomDTO.getName(),
                roomDTO.getDuration(),
                roomDTO.getFee()
        ));
    }

    @Override
    public List<RoomDTO> searchAll() throws Exception {
        List<Room> all = programDAO.searchAll();
        ArrayList<RoomDTO> roomDTOS = new ArrayList<>();

        for (Room room : all) {
            roomDTOS.add(new RoomDTO(
                    room.getId(),
                    room.getName(),
                    room.getDuration(),
                    room.getFee()
            ));
        }
        return roomDTOS;
    }

    @Override
    public RoomDTO search(String id) {
        try {
            Room item=programDAO.search(id);
            return new RoomDTO(item.getId(),item.getName(),item.getDuration(),item.getFee());
        } catch (Exception throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
}
