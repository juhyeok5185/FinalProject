package hotel.management.v1.manager.mall.Dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import hotel.management.v1.manager.mall.Dto.ManagerMallListDto;
import hotel.management.v1.manager.mall.Dto.ManagerMallListDto.MallListSearch;

@Mapper
public interface ManagerMallListDao {
	
	public List<ManagerMallListDto.MallListSearch> mallsearch (ManagerMallListDto.FindMallList dto);
	
	public List<ManagerMallListDto.MallListSearch> contactmallList ();
	
	public List<ManagerMallListDto.FindMallList> pagination (Integer startRownum, Integer endRownum);

	public Integer count();
}
