package hotel.management.v1.manager.MVCController;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import hotel.management.v1.manager.dto.ManagerDto;
import hotel.management.v1.manager.service.ManagerService;

@Controller
@RequestMapping("/hotel")
public class ManagerMVCController {
    @Autowired
    ManagerService service;
    
    @GetMapping("/manager/bookList")
    public ModelAndView booklist(){
        List<ManagerDto.findBookList> bookList = service.findBookList();
        return new ModelAndView("hotel/manager/bookList").addObject("bookList", bookList);
    }

    @GetMapping("/manager/memberSearch")
    public void memberSearch(){}
    
    @GetMapping("/manager/memberDetail")
    public void memberDetail(String name){
        // service.findUserByName(name);
    }
}
