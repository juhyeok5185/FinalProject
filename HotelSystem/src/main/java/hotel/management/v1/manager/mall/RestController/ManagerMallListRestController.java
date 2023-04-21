package hotel.management.v1.manager.mall.RestController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import hotel.management.v1.manager.mall.Dto.ManagerMallListDto;
import hotel.management.v1.manager.mall.Service.ManagerMallListService;

@RestController
public class ManagerMallListRestController {
	@Autowired
	private ManagerMallListService mallservice;

	@PostMapping("/hotel/manager/mallList")
	public ResponseEntity<List<ManagerMallListDto.MallListSearch>> mallList(ManagerMallListDto.FindMallList dto){
		List<ManagerMallListDto.MallListSearch> list = mallservice.mallsearch(dto);
		System.out.println(dto);
		return ResponseEntity.ok(list);
	}
}