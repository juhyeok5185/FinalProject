package hotel.management.v1.manager.mall.Service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hotel.management.v1.manager.mall.Dao.ManagerMallListDao;
import hotel.management.v1.manager.mall.Dto.ManagerMallListDto;
import hotel.management.v1.manager.mall.Dto.ManagerMallListDto.orderDetail;

@Service
public class ManagerMallListService {
	@Autowired
	private ManagerMallListDao dao;
	
	public List<ManagerMallListDto.MallListSearch> mallsearch (ManagerMallListDto.FindMallList dto) {
		   List<ManagerMallListDto.MallListSearch> list = dao.mallsearch(dto);
		   return list;
		}

	public List<ManagerMallListDto.MallListSearch> contactmallList() {
		   List<ManagerMallListDto.MallListSearch> list = dao.contactmallList();
		   return list;
		}

	public Integer restdelete(Integer orderNo) {
		return dao.restdelete(orderNo);
		}

	public orderDetail orderDetail(String tel) {
		return null;
	}

	}
	