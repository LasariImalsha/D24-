package bo.custom;

import bo.SuperBO;
import dto.DetailDTO;
import model.tm.DetailTM;

import java.util.List;

public interface DetailBO extends SuperBO {
    public boolean add(DetailDTO detailDTO) throws Exception;

    public boolean delete(String id) throws Exception;

    public boolean update(DetailDTO detailDTO) throws Exception;

    public List<DetailTM> searchAll() throws Exception;

    List<DetailDTO> searchId(String id);

    String getDetailId();

}
