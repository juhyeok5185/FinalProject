package hotel.management.v1.manager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/hotel")
public class managerController {
    
    @GetMapping("/manager/bookList")
    public void booklist(){
        
    }
}
