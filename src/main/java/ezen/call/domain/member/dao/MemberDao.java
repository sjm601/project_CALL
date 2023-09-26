package ezen.call.domain.member.dao;

import java.sql.Connection;
import java.util.List;

import ezen.call.domain.member.dto.Member;
import ezen.call.domain.member.dto.MemberCombined;

/**
 * 데이터 베이스 회원 관리 명세
 * @author 이한솔
 */
public interface MemberDao {

	// 신규 회원 등록
    public boolean create(Connection connection, Member member);
    // 입력 받은 email, password와 일치하는 회원 조회
    public Member findByUser(Connection connection, String email, String password);
    // 전체 회원 조회
    public List<Member> findByAll(Connection connection);
    // 회원 번호로 회원 조회
    public Member findByNumber(Connection connection, int memberNum);
    // 전체 회원 조회 (히스토리 포함)
    public List<MemberCombined> findByAllCombined(Connection connection);
    // 회원번호로 회원 조회 (히스토리 포함)
    public MemberCombined findByNumberCombined(Connection connection, int memberNum);
    // 전체 회원 수 조회
    public int getCountAll(Connection connection);
    // 전체 회원 정보 (히스토리 포함 + 페이징 처리)
    public List<MemberCombined> membersPaginationCombined(Connection connection, int requestPage, int elementSize);
    // 중복 이메일 여부
    public boolean existEmail(Connection connection, String email);
    // 중복 폰번호 여부
    public boolean existPhoneNum(Connection connection, String phoneNum);
    // 중복 비밀번호 여부
    public boolean existPassword(Connection connection, String password);
    // 동일 이메일 또는 비밀번호 확인
    public boolean CheckEmPw(Connection connection, String email, String password);
}