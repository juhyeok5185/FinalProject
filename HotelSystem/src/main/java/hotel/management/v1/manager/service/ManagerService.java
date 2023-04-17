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

    public List<ManagerDto.findBookList> findBookList(){
        List<ManagerDto.findBookList> list = dao.findBookList();
        return list;
    }

    public List<ManagerDto.findUserList> userSearch(String name) {
        List<ManagerDto.findUserList> list = dao.findUserList(name);
        return list;
    }

    public Integer bookCancel(String tel) {
       
        return dao.bookCancel(tel);
    }

    public void checkOut(String tel) {
        dao.checkOut(tel);
    }

    public void changeBook(boolean breakfast, boolean dinner, String tel) {
        Integer bookNo = dao.findBookNoByTel(tel);
        if(breakfast == true){
            dao.updateBreakfast(bookNo);
        }
        else if (dinner == true){
            //if문 한번더
            dao.updateDinner(bookNo);
        }
    }

}
