package hotel.management.v1.manager.mall.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;import hotel.management.v1.board.dto.BoardDto;
import hotel.management.v1.manager.dto.ManagerDto;
import hotel.management.v1.manager.mall.Dao.ManagerMallDao;
import hotel.management.v1.manager.mall.Dto.ManagerMallDto;

@Service
public class ManagerMallService {
	@Autowired
	private ManagerMallDao malldao;
	
	
//	 public List<ManagerMallDto.findManagerMallList> findManagerMallList() {
//	        List<ManagerMallDto.findManagerMallList> list = malldao.findManagerMallList();
//	        return list;
//	    }
//		
//		public List<ManagerMallDto.findManagerMallList> listSearch(ManagerMallDto.findManagerMallList dto) {
//	        return malldao.searchManagerMall(dto);
//	}
}
