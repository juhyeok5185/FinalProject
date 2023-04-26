package hotel.management.v1.manager.mall.MVCController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import hotel.management.v1.manager.mall.Dto.ManagerMallListDto;
import hotel.management.v1.manager.mall.Service.ManagerMallListService;

@Controller
public class ManagerMallListMVCController {
	@Autowired
	private ManagerMallListService service;

		@GetMapping("/hotel/manager/managerMallList")
		public void mallList() {
		}
	
		
		public ModelAndView contactmallList() {
			List<ManagerMallListDto.MallListSearch> list = service.contactmallList();
			return new ModelAndView("/hotel/manager/managerMallList").addObject("list",list);
		}
	
}
