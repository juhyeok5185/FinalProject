package hotel.management.v1.manager.RestController;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hotel.management.v1.manager.dto.ManagerDto;

@RestController
@RequestMapping("/hotel")
public class ManagerRestController {

    @PostMapping("/manager/bookSearch")
    public ResponseEntity<String> booklist(ManagerDto.bookList dto) {
        System.out.println(dto);
        return ResponseEntity.ok("dd");
    }
    
}
