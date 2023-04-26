package hotel.management.v1.manager.mall.Service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hotel.management.v1.board.dto.BoardDto;
import hotel.management.v1.manager.mall.Dao.ManagerMallListDao;
import hotel.management.v1.manager.mall.Dto.ManagerMallListDto;
import hotel.management.v1.manager.mall.Dto.ManagerMallListDto.FindMallList;
import hotel.management.v1.manager.mall.Dto.ManagerMallListDto.MallListSearch;

@Service
public class ManagerMallListService {
	@Autowired
	private ManagerMallListDao malllistdao;
	
	private final static Integer PAGESIZE = 10;
	private final static Integer BLOCKSIZE = 5;
	
	public List<ManagerMallListDto.MallListSearch> mallsearch (ManagerMallListDto.FindMallList dto) {
		   List<ManagerMallListDto.MallListSearch> list = malllistdao.mallsearch(dto);
		   return list;
		}

	public List<ManagerMallListDto.MallListSearch> contactmallList() {
		   List<ManagerMallListDto.MallListSearch> list = malllistdao.contactmallList();
		   return list;
		}
	
	public ManagerMallListDto.Pagination pagination(Integer pageno) {
		Integer mallListCnt = malllistdao.count();
		Integer mallListPageCnt = (mallListCnt - 1) / PAGESIZE + 1;
		
		pageno = Math.abs(pageno);
		if(pageno>mallListPageCnt)
			pageno = mallListPageCnt;
		Integer startRownum = (pageno-1) * PAGESIZE;
		Integer endRownum = startRownum + PAGESIZE + 1; 
		List<ManagerMallListDto.FindMallList> mallList = malllistdao.pagination(startRownum, endRownum);
		Integer prev = (pageno - 1) / BLOCKSIZE * BLOCKSIZE;
		Integer start = prev + 1;
		Integer end = prev + BLOCKSIZE;
		Integer next = end + 1;
		if (end >= mallListPageCnt) {
			end = mallListPageCnt;
			next = 0;
		}
		
		prev = prev == 0 ? start : prev;
		next = next == 0 ? end : next;
		return new ManagerMallListDto.Pagination(prev, start, end, next, mallList);
	}
	
}