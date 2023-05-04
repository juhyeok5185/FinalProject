package hotel.management.v1.manager.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

import org.apache.commons.lang3.StringUtils;
import org.mybatis.spring.MyBatisSystemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hotel.management.v1.exception.NotFoundBookListException;
import hotel.management.v1.exception.NotFoundUserListException;
import hotel.management.v1.manager.dao.ManagerDao;
import hotel.management.v1.manager.dto.ManagerDto;
import hotel.management.v1.util.ManagerUtil;
import hotel.management.v1.util.managerUtil;


@Service
public class ManagerService {
   @Autowired
   ManagerDao dao;

    public List<ManagerDto.findBookList> findBookList(){
        try {
            List<ManagerDto.findBookList> list = dao.findBookList().get();
            return list;
        } catch (NoSuchElementException e) {
            return null;
        }
        
    }

    public List<ManagerDto.findUserList> userSearch(String name) {
        List<ManagerDto.findUserList> list = dao.findUserList(name);
        if(list.size() == 0){
            throw new NotFoundUserListException("고객을 찾을수 없습니다.");
        }
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
        try {
            ManagerDto.userDetail userDetail = dao.memberDetail(name,tel).get();
            return userDetail;
        } catch (NoSuchElementException e) {
            return null;
        } catch (MyBatisSystemException m){
            return null;
        }
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
        ManagerUtil managerUtil = new ManagerUtil();
        dto = managerUtil.DateFormat(dto);
        
        //set된 dto의 조건들을 dao에 연결해준다.
        List<ManagerDto.findBookList> list = dao.bookSearch(dto);
        if(list.size() == 0){
            throw new NotFoundBookListException("검색결과가 없습니다.");
        }
        return list;  
    }
}
