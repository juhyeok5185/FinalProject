package hotel.management.v1.manager.mall.RESTController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import hotel.management.v1.manager.mall.Dto.ManagerMallDto;
import hotel.management.v1.manager.mall.Service.ManagerMallService;

// 관리자 : 김동욱
@Controller
public class ManagerMallRESTController {
	@Autowired
	private ManagerMallService managermallservice;
	
//	@PostMapping("/manager/managerMallList")
//    public ResponseEntity<List<ManagerMallDto.findManagerMallList>> listSearch(ManagerMallDto.findManagerMallList dto) {
//        List<ManagerMallDto.findManagerMallList> list = managermallservice.listSearch(dto);
//        return ResponseEntity.ok(list);
//    }
}
