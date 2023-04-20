package hotel.management.v1.manager.mall.MVCController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import hotel.management.v1.manager.mall.Dto.ManagerMallDto;
import hotel.management.v1.manager.mall.Service.ManagerMallService;

// 관리자 : 김동욱
@Controller
public class ManagerMallMVCController {
	@Autowired
	private ManagerMallService managermallservcie;
			
//	@GetMapping("manager/managerMallList")
//	 public ModelAndView managerMallList(){
//        List<ManagerMallDto.findManagerMallList> managerMallList = managermallservcie.findManagerMallList();
//        return new ModelAndView("hotel/manager/managerMallList").addObject("managerMallList", managerMallList);
//    }
}
