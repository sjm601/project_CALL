package ezen.call.domain.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import ezen.call.domain.member.dto.Member;
import ezen.call.domain.member.dto.MemberCombined;
import ezen.call.domain.member.dto.MemberHistory;

/**
 * RDB를 통해 회원목록 저장 및 관리(검색, 수정, 삭제) 구현체
 * @author 이한솔
 */
public class JdbcMemberDao implements MemberDao {
    
	/**
     * 신규 회원 등록
     * @param Connection 커넥션
     * @param Member 회원
     * @return Boolean 성공여부
     */
    public boolean create(Connection connection, Member member) {
        boolean success = false;
        StringBuilder sb = new StringBuilder();
        sb.append(" INSERT INTO member (")
          .append("     member_number,")
          .append("     email,")
          .append("     password,")
          .append("     member_name,")
          .append("     phone_number,")
          .append("     birthday,")
          .append("     gender)")
          .append(" VALUES (member_number_seq.NEXTVAL, ?, ?, ?, ?, ?, ?)");

        PreparedStatement pstmt = null;
        try {
            pstmt = connection.prepareStatement(sb.toString());
            pstmt.setString(1, member.getEmail());
            pstmt.setString(2, member.getPassword());
            pstmt.setString(3, member.getMemberName());
            pstmt.setString(4, member.getPhoneNum());
            pstmt.setString(5, member.getBirthday());
            pstmt.setString(6, member.getGender());
            pstmt.executeUpdate();
            success = true;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            try {
                if (pstmt != null) pstmt.close();
            } catch (Exception e) {}
        }
        return success;
    }

    /**
     * 입력 받은 email, password와 일치하는 회원 조회
     * @param Connection 커넥션
     * @param String 이메일
     * @param String 비밀번호
     * @return Member 회원
     */
    public Member findByUser(Connection connection, String email, String password) {
        Member member = null;
        StringBuilder sb = new StringBuilder();
        sb.append(" SELECT m.member_number,")
          .append("    m.email,")
          .append("    m.member_name")
          .append(" FROM member m")
          .append("  JOIN member_history mh ON mh.member_number = m.member_number ")
          .append(" WHERE m.email = ? AND m.password = ? AND mh.status = 'T'");

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = connection.prepareStatement(sb.toString());
            pstmt.setString(1, email);
            pstmt.setString(2, password);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                int memberNum = rs.getInt("member_number");
                String userEmail = rs.getString("email");
                String userName = rs.getString("member_name");
                member = new Member();
                member.setMemberNum(memberNum);
                member.setEmail(userEmail);
                member.setMemberName(userName);
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
            } catch (Exception e) {}
        }
        return member;
    }
    
    /**
     * 전체 회원 조회
     * @param Connection 커넥션
     * @return List<Member> 회원 리스트
     */
    @Override 
    public List<Member> findByAll(Connection connection) {
        List<Member> list = null;
        StringBuilder sb = new StringBuilder();
        sb.append(" SELECT m.member_number,")
          .append("    m.member_name,")
          .append("    m.email,")
          .append("    m.phone_number,")
          .append("    m.birthday,")
          .append("    m.gender")
          .append(" FROM member m")
          .append("   JOIN member_history mh ON mh.member_number = m.member_number");

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = connection.prepareStatement(sb.toString());
            rs = pstmt.executeQuery();
            list = new ArrayList<Member>();
            while (rs.next()) {
                int userNum = rs.getInt("member_number");
                String userName = rs.getString("member_name");
                String userEmail = rs.getString("email");
                String userPhoneNum = rs.getString("phone_number");
                String userBirthday = rs.getString("birthday");
                String userGender = rs.getString("gender");

                Member member = new Member();
                member.setMemberNum(userNum);
                member.setMemberName(userName);
                member.setEmail(userEmail);
                member.setPhoneNum(userPhoneNum);
                member.setBirthDay(userBirthday);
                member.setGender(userGender);
                list.add(member);
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
            } catch (Exception e) {}
        }
        return list;
    }
    
    /**
     * 회원 번호로 회원 조회
     * @param Connection 커넥션
     * @param Int 회원번호
     * @return 검색된 회원
     */
    @Override
    public Member findByNumber(Connection connection, int memberNum) {
        Member member = null;
        StringBuilder sb = new StringBuilder();
        sb.append(" SELECT member_number,")
          .append("   email,")
          .append("   member_name,")
          .append("   gender,")
          .append("   phone_number")
          .append(" FROM member")
          .append(" WHERE member_number = ?");

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = connection.prepareStatement(sb.toString());
            pstmt.setInt(1, memberNum);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                member = new Member();
                int umemberNum = rs.getInt("member_number");
                String email = rs.getString("email");
                String memberName = rs.getString("member_name");
                String gender = rs.getString("gender");
                String phoneNum = rs.getString("phone_number");
                member.setMemberNum(umemberNum);
                member.setEmail(email);
                member.setMemberName(memberName);
                member.setGender(gender);
                member.setPhoneNum(phoneNum);
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
            } catch (Exception e) {}
        }
        return member;
    }
    
    /**
     * 전체 회원 조회 (히스토리 포함)
     * @param Connection 커넥션
     * @return List<MemberCombined> 회원리스트 (히스토리 포함)
     */
    @Override 
    public List<MemberCombined> findByAllCombined(Connection connection) {
        List<MemberCombined> list = null;
        StringBuilder sb = new StringBuilder();
        sb.append(" SELECT m.member_number,")
          .append("    m.email,")
          .append("    m.member_name,")
          .append("    m.phone_number,")
          .append("    m.birthday,")
          .append("    m.gender,")
          .append("    TO_CHAR(mh.member_register_date, 'yyyy-MM-DD') member_register_date,")
          .append("    mh.administrator,")
          .append("    mh.status,")
          .append("    TO_CHAR(mh.member_withdraw_date, 'yyyy-MM-DD') member_withdraw_date")
          .append(" FROM member m")
          .append("   JOIN member_history mh ON mh.member_number = m.member_number");

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = connection.prepareStatement(sb.toString());
            rs = pstmt.executeQuery();
            list = new ArrayList<MemberCombined>();
            while (rs.next()) {
                int memberNum = rs.getInt("member_number");
                String memberName = rs.getString("member_name");
                String email = rs.getString("email");
                String phoneNum = rs.getString("phone_number");
                String birthday = rs.getString("birthday");
                String gender = rs.getString("gender");
                
                String memberRegisterDate = rs.getString("member_register_date");
                String administrator = rs.getString("administrator");
                String status = rs.getString("status");
                String memberWithdrawDate = rs.getString("member_withdraw_date");
                
                MemberCombined memberCombined = new MemberCombined();
                Member member = new Member();
                MemberHistory memberHistory = new MemberHistory();
                
                member.setMemberNum(memberNum);
                member.setMemberName(memberName);
                member.setEmail(email);
                member.setPhoneNum(phoneNum);
                member.setBirthDay(birthday);
                member.setGender(gender);
                
                memberHistory.setMemberNum(memberNum);
                memberHistory.setMemberRegisterDate(memberRegisterDate);
                memberHistory.setAdministrator(administrator);
                memberHistory.setStatus(status);
                memberHistory.setMemberWithdrawDate(memberWithdrawDate);
                
                memberCombined.setMember(member);
                memberCombined.setMemberHistory(memberHistory);
                
                list.add(memberCombined);
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
            } catch (Exception e) {}
        }
        return list;
    }
    
    /**
     * 회원번호로 회원 조회 (히스토리 포함)
     * @param Connection 커넥션
     * @param Int 회원번호
     * @return MemberCombined 회원 (히스토리 포함)
     */
    @Override
    public MemberCombined findByNumberCombined(Connection connection, int memberNum) {
        MemberCombined memberCombined = null;
        StringBuilder sb = new StringBuilder();
        sb.append(" SELECT m.member_number,")
          .append("   m.email,")
          .append("   m.member_name,")
          .append("   m.phone_number,")
          .append("   m.birthday,")
          .append("   m.gender,")
          .append("   TO_CHAR(mh.member_register_date, 'yyyy-MM-DD') member_register_date,")
          .append("   mh.administrator,")
          .append("   mh.status,")
          .append("   TO_CHAR(mh.member_withdraw_date, 'yyyy-MM-DD') member_withdraw_date")
          .append(" FROM member m")
          .append("   JOIN member_history mh ON mh.member_number = m.member_number")
          .append(" WHERE m.member_number = ?");

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = connection.prepareStatement(sb.toString());
            pstmt.setInt(1, memberNum);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                memberCombined = new MemberCombined();
                Member member = new Member();
                MemberHistory memberHistory = new MemberHistory();
                
                int memberNumber = rs.getInt("member_number");
                String memberName = rs.getString("member_name");
                String email = rs.getString("email");
                String phoneNum = rs.getString("phone_number");
                String birthday = rs.getString("birthday");
                String gender = rs.getString("gender");
                
                String memberRegisterDate = rs.getString("member_register_date");
                String administrator = rs.getString("administrator");
                String status = rs.getString("status");
                String memberWithdrawDate = rs.getString("member_withdraw_date");
                
                member.setMemberNum(memberNumber);
                member.setMemberName(memberName);
                member.setEmail(email);
                member.setPhoneNum(phoneNum);
                member.setBirthDay(birthday);
                member.setGender(gender);
                
                memberHistory.setMemberNum(memberNumber);
                memberHistory.setMemberRegisterDate(memberRegisterDate);
                memberHistory.setAdministrator(administrator);
                memberHistory.setStatus(status);
                memberHistory.setMemberWithdrawDate(memberWithdrawDate);
                
                memberCombined.setMember(member);
                memberCombined.setMemberHistory(memberHistory);
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
            } catch (Exception e) {}
        }
        return memberCombined;
    }
    
    /**
     * 전체 회원 수 조회
     * @param Connection 커넥션
     * @return Int 회원 수
     */
    @Override
    public int getCountAll(Connection connection) {
        int count = 0;
        StringBuilder sb = new StringBuilder();
        sb.append(" SELECT COUNT(*) cnt")
          .append(" FROM member m")
          .append("   JOIN member_history mh ON mh.member_number = m.member_number");
        
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = connection.prepareStatement(sb.toString());
            rs = pstmt.executeQuery();
            if (rs.next()) {
                count = rs.getInt("cnt");
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
            } catch (Exception e) {}
        }
        return count;
    }
    
    /**
     * 전체 회원 정보 (히스토리 포함 + 페이징 처리)
     * @param Connection 커넥션
     * @param Int 요청 페이지 
     * @param Int 항목 수
     * @return List<MemberCombined> 회원리스트 (히스토리 포함 + 페이징 처리)
     */
    @Override
    public List<MemberCombined> membersPaginationCombined(Connection connection, int requestPage, int elementSize) {
        List<MemberCombined> list = null;
        StringBuilder sb = new StringBuilder();
        
       sb.append("     SELECT")
           .append("     page,")
           .append("     member_number,")
           .append("     email,")
           .append("     member_name,")
           .append("     phone_number,")
           .append("     birthday,")
           .append("     gender,")
           .append("     register_date,")
           .append("     administrator,")
           .append("     status,")
           .append("     withdraw_date")
           .append(" FROM")
           .append("     (")
           .append("         SELECT")
           .append("             ceil(ROWNUM / ?) page,")
           .append("             member_number,")
           .append("             email,")
           .append("             member_name,")
           .append("             phone_number,")
           .append("             birthday,")
           .append("             gender,")
           .append("             register_date,")
           .append("             administrator,")
           .append("             status,")
           .append("             withdraw_date")
           .append("         FROM")
           .append("             (")
           .append("                 SELECT")
           .append("                     m.member_number,")
           .append("                     m.email,")
           .append("                     m.member_name,")
           .append("                     m.phone_number,")
           .append("                     m.birthday,")
           .append("                     m.gender,")
           .append("                     TO_CHAR(mh.member_register_date, 'YYYY-MM-DD') register_date,")
           .append("                     mh.administrator,")
           .append("                     mh.status,")
           .append("                     TO_CHAR(mh.member_withdraw_date, 'YYYY-MM-DD') withdraw_date")
           .append("                 FROM")
           .append("                   member m")
           .append("                     JOIN member_history mh ON mh.member_number = m.member_number")
           .append("             )")
           .append("     )")
           .append(" WHERE")
           .append("     page = ?");
        
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = connection.prepareStatement(sb.toString());
            pstmt.setInt(1, elementSize);
            pstmt.setInt(2, requestPage);
            rs = pstmt.executeQuery();
            list = new ArrayList<MemberCombined>();
            while (rs.next()) {         
                int memberNum = rs.getInt("member_number");
                String email = rs.getString("email");
                String memberName = rs.getString("member_name");
                String phoneNum = rs.getString("phone_number");
                String birthday = rs.getString("birthday");
                String gender = rs.getString("gender");
                
                String memberRegisterDate = rs.getString("register_date");
                String administrator = rs.getString("administrator");
                String status = rs.getString("status");
                String memberWithdrawDate = rs.getString("withdraw_date");
                
                MemberCombined memberCombined = new MemberCombined();
                Member member = new Member();
                MemberHistory memberHistory = new MemberHistory();
                
                member.setMemberNum(memberNum);
                member.setMemberName(memberName);
                member.setEmail(email);
                member.setPhoneNum(phoneNum);
                member.setBirthDay(birthday);
                member.setGender(gender);
                
                memberHistory.setMemberNum(memberNum);
                memberHistory.setMemberRegisterDate(memberRegisterDate);
                memberHistory.setAdministrator(administrator);
                memberHistory.setStatus(status);
                memberHistory.setMemberWithdrawDate(memberWithdrawDate);
                
                memberCombined.setMember(member);
                memberCombined.setMemberHistory(memberHistory);
                
                list.add(memberCombined);
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
            } catch (Exception e) {}
        }
        return list;
    }

    /**
     * 중복 이메일 여부
     * @param Connection 커넥션
     * @param String 이메일
     * @return 중복 여부
     */
    public boolean existEmail(Connection connection, String email) {
        boolean success = false;
        int countEmail = 0;
        StringBuilder sb = new StringBuilder();
        sb.append(" SELECT count(email) cnt")
                .append("  FROM member")
                .append(" WHERE email = ?");

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = connection.prepareStatement(sb.toString());
            pstmt.setString(1, email);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                countEmail = rs.getInt("cnt");
                if(countEmail != 0) {
                    return success = true;
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
            } catch (Exception e) {}
        }
        return success;
    }

    /**
     * 중복 폰번호 여부
     * @param Connection 커넥션
     * @param String 폰번호
     * @return 중복 여부
     */
    public boolean existPhoneNum(Connection connection, String phoneNum) {
        boolean success = false;
        int countPhoneNum = 0;
        StringBuilder sb = new StringBuilder();
        sb.append(" SELECT count(phone_number) cnt")
                .append("  FROM member")
                .append(" WHERE phone_number = ?");

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = connection.prepareStatement(sb.toString());
            pstmt.setString(1, phoneNum);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                countPhoneNum = rs.getInt("cnt");
                if(countPhoneNum!=0) {
                    return success = true;
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
            } catch (Exception e) {}
        }
        return success;
    }

    /**
     * 중복 비밀번호 여부
     * @param Connection 커넥션
     * @param String 비밀번호
     * @return 중복 여부
     */
    public boolean existPassword(Connection connection, String password) {
        boolean success = false;
        int countPassword = 0;
        StringBuilder sb = new StringBuilder();
        sb.append(" SELECT count(password) cnt")
                .append("  FROM member")
                .append(" WHERE password = ?");

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = connection.prepareStatement(sb.toString());
            pstmt.setString(1, password);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                countPassword = rs.getInt("cnt");
                if(countPassword!=0) {
                    return success = true;
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
            } catch (Exception e) {}
        }
        return success;
    }
    
    /**
     * 동일 이메일 또는 비밀번호 확인
     * @param String 이메일
     * @param String 비밀번호
     * @return Boolean 중복여부 
     */
    @Override
    public boolean CheckEmPw(Connection connection, String email, String password) {
        boolean success = false;
        int countEmail = 0;
        int countPassword = 0;
        StringBuilder sb = new StringBuilder();
        sb.append(" SELECT count(email) ecnt , count(password) pcnt")
          .append("  FROM member")
          .append(" WHERE email = ? OR password = ?");

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = connection.prepareStatement(sb.toString());
            pstmt.setString(1, email);
            pstmt.setString(2, password);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                countEmail = rs.getInt("ecnt");
                countPassword = rs.getInt("pcnt");
                if(countEmail != 0 || countPassword !=0) {
                    return success = true;
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
            } catch (Exception e) {}
        }
        return success;
    }
}