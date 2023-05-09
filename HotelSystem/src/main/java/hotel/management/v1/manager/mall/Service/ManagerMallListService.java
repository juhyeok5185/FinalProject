package hotel.management.v1.manager.mall.Service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hotel.management.v1.exception.NotFoundMallListException;
import hotel.management.v1.manager.mall.Dao.ManagerMallListDao;
import hotel.management.v1.manager.mall.Dto.ManagerMallListDto;

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
		   if(list.size()==0) {
			   throw new NotFoundMallListException("검색 결과가 없습니다.");
		   }
		   return list;
		}

	public Integer orderdetaildelete(Integer orderNo) {
		return dao.orderdetaildelete(orderNo);
	}
	
	public Integer delete(Integer orderNo) {
		return dao.delete(orderNo);
		}

	public ManagerMallListDto.orderDetail orderDetail(Integer orderNo) {
		return dao.orderDetail(orderNo);
	}

	}
	