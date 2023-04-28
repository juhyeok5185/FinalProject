package hotel.management.v1.manager.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
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

    public void checkOut(String tel, String roomNo) {
        Integer intRoomNo = Integer.parseInt(roomNo);
        dao.changeRoomStatusEmpty(intRoomNo);
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
        Integer intRoomNo = Integer.parseInt(roomNo);
        dao.setRoom(intRoomNo , bookNo);
        dao.changeBookStatus(bookNo);
        dao.changeRoomStatusCheckIn(intRoomNo);
    }

    public ManagerDto.userDetail memberDetail(String name,String tel) {
        return dao.memberDetail(name,tel);
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

    //검색 조건들을 dto로 받는 메소드
    public List<ManagerDto.findBookList> bookSearch(ManagerDto.bookSearchCondition dto) {
        //dto로 받은 값들의 날짜 데이터를 db의 형식과 맞춰준다.
        SimpleDateFormat originalFormat = new SimpleDateFormat("MM/dd/yyyy");
        SimpleDateFormat targetFormat = new SimpleDateFormat("yy/MM/dd");
        try {
            // fromDate값을 받았으면 그값의 format을 변경해주고 dto에 값을 추가해준다.
            if(dto.getFromDate() != ""){
                Date fromDateof = originalFormat.parse(dto.getFromDate());
                String fromDate = targetFormat.format(fromDateof);
                dto.setFromDate(fromDate);
            }
            // toDate값을 받았으면 그값의 format을 변경해주고 dto에 값을 추가해준다.
            if(dto.getToDate() != ""){
                Date toDateof = originalFormat.parse(dto.getToDate());
                String toDate = targetFormat.format(toDateof);
                dto.setToDate(toDate);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        // set된 dto의 조건들을 dao에 연결해준다.
        return dao.bookSearch(dto);
    }

}
