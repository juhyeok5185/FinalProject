package hotel.management.v1.manager.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hotel.management.v1.client.book.entity.book;
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

    public void checkOut(String tel, String roomNo) {
        Integer intRoomNo = Integer.parseInt(roomNo);
        dao.changeRoomStatus(intRoomNo);
        dao.checkOut(tel);
    }

    public void changeBook(boolean breakfast, boolean dinner, String tel) {
        ManagerDto.findBookNoCount bookNoCount = dao.findBookNoCountByTel(tel);
        if(breakfast == true){
            dao.updateBreakfast(bookNoCount.getBookNo() , tel);
        } else {
            dao.cancelBreakfast(bookNoCount.getBookNo());
        }
        Integer searchRes = dao.searchRes(bookNoCount.getBookNo());
        if (dinner == true){
            if(searchRes == null){
                dao.updateDinner(bookNoCount.getBookNo() , bookNoCount.getTotalCount());
            }
        } else {
            dao.cancelDinner(bookNoCount.getBookNo());
        }
    }

    public List<ManagerDto.roomList> roomList(String roomGrade) {
        List<ManagerDto.roomList> list = dao.roomList(roomGrade);
        return list;
    }

    public void checkIn(String roomNo , String name ) {
        Integer bookNo = dao.findBookNoByName(name);
        Integer intRoomNo = Integer.parseInt(roomNo);
        dao.setRoom(intRoomNo , bookNo);
        dao.changeBookStatus(bookNo);
    }

    public ManagerDto.userDetail memberDetail(String name) {
        return dao.memberDetail(name);
    }

}
