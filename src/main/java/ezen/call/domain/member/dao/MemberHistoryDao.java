package ezen.call.domain.member.dao;

import java.sql.Connection;
import java.util.List;

import ezen.call.domain.member.dto.MemberHistory;


/**
 * 데이터베이스 멤버히스토리 관리 명세(2023-07-24)
 * @author 강소영
 */
public interface MemberHistoryDao {
    
	// 회원히스토리 등록
    public boolean create(Connection connection, MemberHistory memberHistory);
	// 회원번호로 회원히스토리 검색
    public MemberHistory findByNumber(Connection connection, int memberNum);
	// 전체 회원히스토리 검색
    public List<MemberHistory> findByAll(Connection connection);
	// 회원 번호로 회원 탈퇴 및 상태정보(회원상태, 탈퇴날짜) 수정
    public boolean updateWithdraw(Connection connection, int memberNum);
}