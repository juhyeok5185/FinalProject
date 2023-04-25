package hotel.management.v1.manager.mall.Service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hotel.management.v1.manager.mall.Dao.ManagerMallListDao;
import hotel.management.v1.manager.mall.Dto.ManagerMallListDto;

@Service
public class ManagerMallListService {
	@Autowired
	private ManagerMallListDao malllistdao;
											
	public List<ManagerMallListDto.MallListSearch> mallsearch (ManagerMallListDto.FindMallList dto) {
		   List<ManagerMallListDto.MallListSearch> list = malllistdao.mallsearch(dto);
		   return list;
	}
}