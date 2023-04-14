package hotel.management.v1.manager.service;

import org.springframework.stereotype.Service;


@Service
public class ManagerService {
   
    public void bookList(){
        System.out.println(LocalDate.now());
    }
}
