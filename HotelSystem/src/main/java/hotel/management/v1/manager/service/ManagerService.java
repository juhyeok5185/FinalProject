package hotel.management.v1.manager.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hotel.management.v1.client.book.entity.book;
import hotel.management.v1.manager.dao.ManagerDao;
import hotel.management.v1.manager.dto.ManagerDto;
import hotel.management.v1.manager.dto.ManagerDto.bookSearchCondition;


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

    public void checkIn(String roomNo , String tel ) {
        tel = StringUtils.strip(tel);
        Integer bookNo = dao.findBookNoByTel(tel);
        System.out.println(bookNo);
        Integer intRoomNo = Integer.parseInt(roomNo);
        dao.setRoom(intRoomNo , bookNo);
        dao.changeBookStatus(bookNo);
    }

    public ManagerDto.userDetail memberDetail(String name) {
        return dao.memberDetail(name);
    }

    public void blackBtn(String name) {
        dao.blackBtn(name);
    }

    public void vipBtn(String name) {
        dao.vipBtn(name);
    }

    public void ableBtn(String name) {
        dao.ableBtn(name);
    }

    public List<ManagerDto.findBookList> bookSearch(ManagerDto.bookSearchCondition dto) {
        SimpleDateFormat originalFormat = new SimpleDateFormat("MM/dd/yyyy");
        SimpleDateFormat targetFormat = new SimpleDateFormat("yy/MM/dd");
        try {
            if(dto.getFromDate() != ""){
                Date fromDateof = originalFormat.parse(dto.getFromDate());
                String fromDate = targetFormat.format(fromDateof);
                dto.setFromDate(fromDate);
            }
            if(dto.getToDate() != ""){
                Date toDateof = originalFormat.parse(dto.getToDate());
                String toDate = targetFormat.format(toDateof);
                dto.setToDate(toDate);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        
        System.out.println(dto);
        System.out.println(dao.bookSearch(dto));
        return dao.bookSearch(dto);
    }

}
