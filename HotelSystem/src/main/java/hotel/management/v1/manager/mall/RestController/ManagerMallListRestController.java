package hotel.management.v1.manager.mall.RestController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import hotel.management.v1.manager.mall.Dto.ManagerMallListDto;
import hotel.management.v1.manager.mall.Service.ManagerMallListService;

@RestController
public class ManagerMallListRestController {
	@Autowired
	private ManagerMallListService service;
	

	@PostMapping("/hotel/manager/managerMallList")
	public ResponseEntity<List<ManagerMallListDto.MallListSearch>> mallList(ManagerMallListDto.FindMallList dto){
		List<ManagerMallListDto.MallListSearch> list = service.mallsearch(dto);
		return ResponseEntity.ok(list);
	}
	
	@PostMapping("/hotel/manager/delete")
	public ResponseEntity<?> delete(String orderNo){
		Integer orderno = Integer.parseInt(orderNo);
		service.orderdetaildelete(orderno);
		service.delete(orderno);
		return ResponseEntity.ok("");
	}
	
}