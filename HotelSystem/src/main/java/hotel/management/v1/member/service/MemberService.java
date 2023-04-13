package hotel.management.v1.member.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hotel.management.v1.member.dao.MemberDao;

@Service
public class MemberService {
	@Autowired
	private MemberDao dao;
	
}
