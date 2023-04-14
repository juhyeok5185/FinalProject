package hotel.management.v1.manager.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import hotel.management.v1.manager.dto.ManagerDto;

@Mapper
public interface ManagerDao {

    public List<ManagerDto.bookList> test();
    
}
