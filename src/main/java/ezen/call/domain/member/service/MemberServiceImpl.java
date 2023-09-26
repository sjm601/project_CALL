package ezen.call.domain.member.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import ezen.call.domain.member.dao.MemberDao;
import ezen.call.domain.member.dao.MemberHistoryDao;
import ezen.call.domain.member.dto.Member;
import ezen.call.domain.member.dto.MemberCombined;
import ezen.call.domain.member.dto.MemberHistory;
import ezen.call.web.common.page.PageParams;

/**
 * 회원 관련 비즈니스 로직 처리 및 트랜잭션 관리 구현체
 * @author 이한솔
 */
public class MemberServiceImpl implements MemberService{
    
    private DataSource dataSource;
    private MemberDao memberDao;
    private MemberHistoryDao memberHistoryDao;
    
    public MemberServiceImpl(DataSource dataSource, MemberDao memberDao, MemberHistoryDao memberHistoryDao) {
        this.dataSource = dataSource;
        this.memberDao = memberDao;
        this.memberHistoryDao = memberHistoryDao;
    }
    
    /**
	 * 회원 등록
	 * @param Member 회원
	 */
    @Override
    public void registerMember(Member member, MemberHistory memberHistory) {
        Connection connection = null;
        try {
            // 트랜잭션 시작
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);
            // memberDao 사용
            // 등록
            memberDao.create(connection, member);
            memberHistoryDao.create(connection, memberHistory);
            // 정상 처리 시 커밋
            connection.commit();
        } catch (Exception e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {}
            throw new RuntimeException(e.getMessage());
        } finally {
            try {
                if (connection != null) connection.close();
            } catch (SQLException e1) {}
        }
    }
    
    /**
	 * 회원 인증
	 * @param String 이메일
	 * @param String 비밀번호
	 * @return Member 회원
	 */
    @Override
    public Member isMember(String email, String password) {
        Member member = null;
        Connection connection = null;
        try {
            // select는 트랜잭션 X
            connection = dataSource.getConnection();
            member = memberDao.findByUser(connection, email, password);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            try {
                if(connection != null) connection.close();
            } catch (SQLException e1) {}
        }
        return member;
    }
    
    /**
     * 회원 전체 목록
     * @return List<Member> 회원 리스트
     */
    @Override
    public List<Member> getMembers() {
        List<Member> members = null;
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            members = memberDao.findByAll(connection);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            try {
                if (connection != null) connection.close();
            } catch (SQLException e1) {}
        }
        return members;
    }

    /**
     * 회원 전체 히스토리 목록
     * @return List<MemberHistory> 회원 히스토리 리스트
     */
    @Override
    public List<MemberHistory> getMemberHistorys() {
        List<MemberHistory> memberHistorys = null;
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            memberHistorys = memberHistoryDao.findByAll(connection);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            try {
                if (connection != null) connection.close();
            } catch (SQLException e1) {}
        }
        return memberHistorys;
    }
    
    /**
     * 회원번호로 회원 상세정보 조회
     * @param Int 회원번호
     * @return Member 회원
     */
    @Override
    public Member readMemberByNum(int memberNum) {
        Member member = null;
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            member = memberDao.findByNumber(connection, memberNum);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            try {
                if (connection != null) connection.close();
            } catch (SQLException e1) {}
        }
        return member;
    }
    
    /**
     * 회원번호로 회원히스토리 조회
     * @param Int 회원번호
     * @return MemberHistory 회원 히스토리
     */
    @Override
    public MemberHistory readMemberHistoryByNum(int memberNum) {
        MemberHistory memberHistory = null;
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            memberHistory = memberHistoryDao.findByNumber(connection, memberNum);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            try {
                if (connection != null) connection.close();
            } catch (SQLException e1) {}
        }
        return memberHistory;
    }
    
    /**
     * 회원번호로 탈퇴회원 정보 업데이트
     * @param Int 회원번호
     */
    @Override
    public void updateMemberDisabled(int memberNum) {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            //회원번호를 찾아서 업데이트
            memberHistoryDao.updateWithdraw(connection, memberNum);
            //정상 처리시 커밋
            connection.commit();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            try {
            	if(connection != null) connection.close();
            } catch (SQLException e1) {}
        }
    }
    
    /**
     * 전체 회원 조회 (히스토리 포함)
     * @return List<MemberCombined> 회원 리스트 (히스토리 포함)
     */
    @Override
    public List<MemberCombined> getMembersCombined() {
        List<MemberCombined> memberList = null;
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            memberList = memberDao.findByAllCombined(connection); 
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            try {
                if (connection != null) connection.close();
            } catch (SQLException e1) {}
        }
        return memberList;
    }
    
    /**
     * 회원번호로 회원검색 (히스토리 포함)
     * @param 회원번호
     * @return 회원 (히스토리 포함)
     */
    @Override
    public MemberCombined readMemberByNumCombined(int memberNum) {
        MemberCombined memberCombined = null;
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            memberCombined = memberDao.findByNumberCombined(connection, memberNum); 
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            try {
                if (connection != null) connection.close();
            } catch (SQLException e1) {}
        }
        return memberCombined;
    }
    
    /**
     * 전체 회원 수
     * @return Int 회원 수
     */
    @Override
    public int getMemberCount() {
        int count = 0;
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            count = memberDao.getCountAll(connection);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            try {
                if (connection != null) connection.close();
            } catch (SQLException e1) {}
        }
        return count;
    }
    
    /**
     * 전체 회원 조회 (히스토리 포함 + 페이징처리)
     * @param PageParams 페이지
     * @return List<MemberCombined> 회원 리스트 (히스토리 포함 + 페이징처리)
     */
    @Override
    public List<MemberCombined> getMembersPaginationCombined(PageParams params) {
        List<MemberCombined> list = null;
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            list = memberDao.membersPaginationCombined(connection, params.getRequestPage(), params.getElementSize());
            
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            try {
                if (connection != null) connection.close();
            } catch (SQLException e1) {}
        }
        return list;
    }

    /**
     * 이메일 중복 조회
     * @param String 이메일
     * @return Boolean 중복 여부
     */
    @Override
    public boolean confirmEmail(String email) {
        boolean success = false;
        Connection connection = null;
        try {
            // select는 트랜잭션 X
            connection = dataSource.getConnection();
            success = memberDao.existEmail(connection, email);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            try {if(connection != null) connection.close();} catch (SQLException e1) {}
        }
        return success;
    }

    /**
     * 폰번 중복 확인
     * @param String 폰번호
     * @return Boolean 중복 여부
     */
    @Override
    public boolean confirmPhoneNum(String phoneNum) {
        boolean success = false;
        Connection connection = null;
        try {
            // select는 트랜잭션 X
            connection = dataSource.getConnection();
            success = memberDao.existPhoneNum(connection, phoneNum);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            try {if(connection != null) connection.close();} catch (SQLException e1) {}
        }
        return success;
    }

    /**
     * 비밀번호 중복 확인
     * @param String 비밀번호
     * @return Boolean 중복 여부
     */
    @Override
    public boolean confirmPassword(String password) {
        boolean success = false;
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            success = memberDao.existPassword(connection, password);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            try {if(connection != null) connection.close();} catch (SQLException e1) {}
        }
        return success;
    }

    /**
     * 이메일 비밀번호 체크
     * @param String 이메일
     * @param String 비밀번호
     * @return Boolean 확인 여부
     */
    @Override
    public boolean checkEmPw(String email, String password) {
        boolean success = false;
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            success = memberDao.CheckEmPw(connection, email, password);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            try {
            	if(connection != null) connection.close();
            } catch (SQLException e1) {}
        }
        return success;
    }
    
    // 테스트 메인
    public static void main(String[] args) {
//  	ServiceFactory serviceFactory = ServiceFactory.getInstance();
//      MemberService memberService = serviceFactory.getMemberService();
        
//      PageParams params = new PageParams();
//      List<MemberCombined> membersInfo = memberService.getMembersPaginationCombined(params);
//      System.out.println(membersInfo);
//      for (MemberCombined memberCombined : membersInfo) {
//          System.out.println(memberCombined);
//      }
        
//      MemberCombined memberCombined = memberService.readMemberByNumCombined(5);
//      System.out.println(memberCombined);
        
//      Member member = new Member(35,"aaaa@naver.com", "1234", "김무개", "01011118888", "2023-03-08" ,"F");
//      MemberHistory memberHistory = new MemberHistory();
//      System.out.println(member);
//      System.out.println(memberHistory);
//      memberService.registerMember(member, memberHistory);

//      Member loginMember = memberService.isMember("lee@gmail.com", "1234");
//      System.out.println(loginMember);

//      List<Member> members = memberService.getMembers();
//      for (Member member : members) {
//        System.out.println(member);
//      }

//      List<MemberHistory> memberHistories = memberService.getMemberHistorys();
//      for (MemberHistory memberHistory : memberHistories) {
//        System.out.println(memberHistory);
//      }
        
//      Member member = memberService.readMemberByNum(7);
//      System.out.println(member);
//      MemberHistory memberHistory = memberService.readMemberHistoryByNum(7);
//      System.out.println(memberHistory);
        
//      memberService.updateMemberDisabled(1);
    }
}