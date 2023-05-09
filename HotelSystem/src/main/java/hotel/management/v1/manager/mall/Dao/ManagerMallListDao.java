package hotel.management.v1.manager.mall.Dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import hotel.management.v1.manager.mall.Dto.ManagerMallListDto;

@Mapper
public interface ManagerMallListDao {
	
	public List<ManagerMallListDto.MallListSearch> mallsearch (ManagerMallListDto.FindMallList dto);
	
	public List<ManagerMallListDto.MallListSearch> contactmallList ();

	public ManagerMallListDto.MallListSearch findByNo(Integer orderNo);

	public Integer orderdetaildelete(Integer orderNo);
	
	public Integer delete(Integer orderNo);	

	public ManagerMallListDto.orderDetail orderDetail(Integer orderNo);

}
