package hotel.management.v1.manager.MVCController;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import hotel.management.v1.manager.dto.ManagerDto;
import hotel.management.v1.manager.service.ManagerService;

@Controller
@RequestMapping("/hotel")
public class ManagerMVCController {
   
    
    @GetMapping("/manager/bookList")
    public ModelAndView booklist(){
        System.out.println("---------------");
        System.out.println("---------------");
        System.out.println("---------------");
		return new ModelAndView("manager/bookList");
    }

    @GetMapping("/manager/memberSearch")
    public void membersearch(){}
}
