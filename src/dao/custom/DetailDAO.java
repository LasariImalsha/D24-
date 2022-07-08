package dao.custom;

import dao.SuperDAO;
import entity.Detail;

import java.sql.SQLException;
import java.util.List;

public interface DetailDAO extends SuperDAO<Detail,String> {

    List<Detail> viewDetail(String id);

    String getDetailId();

}
