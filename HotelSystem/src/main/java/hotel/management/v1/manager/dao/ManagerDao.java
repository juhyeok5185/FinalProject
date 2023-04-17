package hotel.management.v1.manager.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import hotel.management.v1.manager.dto.ManagerDto;

@Mapper
public interface ManagerDao {

    public List<ManagerDto.findBookList> findBookList();

    //주말동안한거
    public List<ManagerDto.findUserList> findUserList(String name);

    public Integer bookCancel(String tel);

    public String findTelByName(String name);
    
}
