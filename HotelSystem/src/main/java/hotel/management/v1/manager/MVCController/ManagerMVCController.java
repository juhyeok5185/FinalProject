package hotel.management.v1.manager.MVCController;

import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import hotel.management.v1.manager.dto.ManagerDto;

@Controller
@RequestMapping("/hotel")
public class ManagerMVCController {
    
    @GetMapping("/manager/bookList")
    public void booklist(){}

    @GetMapping("/manager/memberSearch")
    public void membersearch(){}
}
