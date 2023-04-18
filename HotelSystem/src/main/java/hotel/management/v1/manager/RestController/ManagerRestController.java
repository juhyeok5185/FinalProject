package hotel.management.v1.manager.RestController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hotel.management.v1.manager.dto.ManagerDto;
import hotel.management.v1.manager.service.ManagerService;

@RestController
@RequestMapping("/hotel")
public class ManagerRestController {
    @Autowired
    ManagerService service;

    @PostMapping("/manager/bookSearch")
    public ResponseEntity<String> bookSearch(ManagerDto.bookSearchCondition dto) {
        return ResponseEntity.ok("dd");
    }

    @PostMapping("/manager/memberSearch")
    public ResponseEntity<List> userSearch(String name) {
        List<ManagerDto.findUserList> list = service.userSearch(name);
        return ResponseEntity.ok(list);
    }

    @PostMapping("/manager/bookCancel")
    public ResponseEntity<?> bookCancel(String bookTel) {
        Integer bookCancel = service.bookCancel(bookTel);
        return ResponseEntity.ok("");
    }

    @PostMapping("/manager/checkOut")
    public ResponseEntity<?> checkOut(String bookTel , String roomNo) {
        service.checkOut(bookTel , roomNo);
        return ResponseEntity.ok("");
    }

    @PostMapping("/manager/changeBook")
    public ResponseEntity<?> changeBook(boolean breakfast , boolean dinner, String bookTel) {
        service.changeBook(breakfast , dinner , bookTel);
        return ResponseEntity.ok("");
    }

    @PostMapping("/manager/checkBtn")
    public ResponseEntity<?> roomList(String roomGrade){ 
        List<ManagerDto.roomList> list = service.roomList(roomGrade);
        return ResponseEntity.ok(list);
    }

    @PostMapping("/manager/checkIn")
    public ResponseEntity<?> checkIn(String roomNo , String name){ 
        service.checkIn(roomNo , name);

        return ResponseEntity.ok("");
    }

}
