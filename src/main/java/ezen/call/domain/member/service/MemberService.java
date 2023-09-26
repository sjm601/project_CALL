package ezen.call.domain.member.service;

import java.util.List;

import ezen.call.domain.member.dto.Member;
import ezen.call.domain.member.dto.MemberCombined;
import ezen.call.domain.member.dto.MemberHistory;
import ezen.call.web.common.page.PageParams;

/**
 * 회원 관련 비즈니스 로직 처리 및 트랜잭션 관리를 위한 명세
 * @author 이한솔
 */
public interface MemberService {
    
    // 회원 등록
    public void registerMember(Member member, MemberHistory memberHistory);
    // 회원 인증
    public Member isMember(String email, String password);
    // 회원 전체 목록
    public List<Member> getMembers();
    // 회원 전체 히스토리 목록
    public List<MemberHistory> getMemberHistorys();
    // 회원번호로 회원 상세정보 조회
    public Member readMemberByNum(int memberNum);
    // 회원번호로 회원히스토리 조회
    public MemberHistory readMemberHistoryByNum(int memberNum);
    // 회원번호로 탈퇴회원 정보 업데이트
    public void updateMemberDisabled(int memberNum);
    // 전체 회원 조회 (히스토리 포함)
    public List<MemberCombined> getMembersCombined();
    // 전체 회원 조회 (히스토리 포함)
    public MemberCombined readMemberByNumCombined(int memberNum);
    // 전체 회원 수
    public int getMemberCount();
    // 전체 회원 조회 (히스토리 포함 + 페이징처리)
    public List<MemberCombined> getMembersPaginationCombined(PageParams params);
    // 이메일 중복 확인
    public boolean confirmEmail(String email);
    // 폰번호 중복 확인
    public boolean confirmPhoneNum(String phoneNum);
    // 비밀번호 확인
    public boolean confirmPassword(String password);
    // 이메일과 비밀번호 동시중복여부
    public boolean checkEmPw(String email, String password);
}