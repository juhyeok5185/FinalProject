package hotel.management.v1.manager.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hotel.management.v1.manager.dao.ManagerDao;
import hotel.management.v1.manager.dto.ManagerDto;


@Service
public class ManagerService {
   @Autowired
   ManagerDao dao;

    public List<ManagerDto.bookList> bookList(){
        List<ManagerDto.bookList> test = dao.test();
        System.out.println(test);
        return test;
    }
}
