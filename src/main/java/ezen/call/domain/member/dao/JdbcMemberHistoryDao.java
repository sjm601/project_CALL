package ezen.call.domain.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import ezen.call.domain.member.dto.MemberHistory;


/**
 * DB를 통한 회원히스토리 저장 및 관리(검색, 수정) 구현체(2023-07-24)
 * @author 강소영
 */
public class JdbcMemberHistoryDao implements MemberHistoryDao {

	/**
	 * 회원히스토리 등록
	 * @param Connection 커넥션
	 * @param MemberHistory 히스토리
	 * @return Boolean 등록 성공여부
	 */
    @Override
    public boolean create(Connection connection, MemberHistory memberHistory) {
        boolean success = false;
        StringBuilder sb = new StringBuilder();
        sb.append(" INSERT INTO member_history (")
          .append("     member_number")
          .append("    ) VALUES (")
          .append("    member_number_seq.CURRVAL)");

        PreparedStatement pstmt = null;
        try {
            pstmt = connection.prepareStatement(sb.toString());
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
	 * 회원번호로 회원히스토리 검색
	 * @param Connection 커넥션
	 * @param Int 회원번호
	 * @return MemberHistory 회원 히스토리
	 */
    @Override
    public MemberHistory findByNumber(Connection connection, int memberNum) {
        MemberHistory memberHistory = null;
        StringBuilder sb = new StringBuilder();
        sb.append(" SELECT member_number,")
                .append("     TO_CHAR(member_register_date, 'yyyy-MM-DD') member_register_date,")
                .append("     administrator,")
                .append("     status,")
                .append("     TO_CHAR(member_withdraw_date, 'yyyy-MM-DD') member_withdraw_date")
                .append("        FROM member_history")
                .append("        WHERE member_number = ?");

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = connection.prepareStatement(sb.toString());
            pstmt.setInt(1, memberNum);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                int uMemberNum = rs.getInt("member_number");
                String memberRegisterDate = rs.getString("member_register_date");
                String administrator = rs.getString("administrator");
                String status = rs.getString("status");
                String memberWithdrawDate = rs.getString("member_withdraw_date");
                memberHistory = new MemberHistory();
                memberHistory.setMemberNum(uMemberNum);
                memberHistory.setMemberRegisterDate(memberRegisterDate);
                memberHistory.setAdministrator(administrator);
                memberHistory.setStatus(status);
                memberHistory.setMemberWithdrawDate(memberWithdrawDate);
            }

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
            } catch (Exception e) {}
        }
        return memberHistory;
    }

    /**
	 * 전체 회원히스토리 검색
	 * @param Connection 커넥션
	 * @return List<MemberHistory> 회원 히스토리 리스트
	 */
    @Override
    public List<MemberHistory> findByAll(Connection connection) {
        List<MemberHistory> list = null;
        StringBuilder sb = new StringBuilder();
        sb.append(" SELECT mh.member_number,")
          .append("     TO_CHAR(mh.member_register_date, 'yyyy-MM-DD') member_register_date,")
            .append("     mh.administrator,")
          .append("     mh.status,")
          .append("     TO_CHAR(mh.member_withdraw_date, 'yyyy-MM-DD') member_withdraw_date")
          .append("  FROM member_history mh")
          .append("    JOIN member m ON m.member_number = mh.member_number");

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = connection.prepareStatement(sb.toString());
            rs = pstmt.executeQuery();
            list = new ArrayList<MemberHistory>();
            while (rs.next()) {
                int uMemberNum = rs.getInt("member_number");
                String memberRegisterDate = rs.getString("member_register_date");
                String administrator = rs.getString("administrator");
                String status = rs.getString("status");
                String memberWithdrawDate = rs.getString("member_withdraw_date");
                MemberHistory memberHistory = new MemberHistory();
                memberHistory = new MemberHistory();
                memberHistory.setMemberNum(uMemberNum);
                memberHistory.setMemberRegisterDate(memberRegisterDate);
                memberHistory.setAdministrator(administrator);
                memberHistory.setStatus(status);
                memberHistory.setMemberWithdrawDate(memberWithdrawDate);
                list.add(memberHistory);
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
	 * 회원 번호로 회원 탈퇴 및 상태정보(회원상태, 탈퇴날짜) 수정
	 * @param Connection 커넥션
	 * @param Int 회원번호
	 * @return Boolean 탈퇴 및 상태정보 수정 성공여부
	 */
    @Override
    public boolean updateWithdraw(Connection connection, int memberNum) {
        boolean success = false;
        StringBuilder sb = new StringBuilder();
        sb.append(" UPDATE member_history")
          .append(" SET status = 'F',")
          .append("        member_withdraw_date = sysdate")
          .append(" WHERE member_number = ?");

        PreparedStatement pstmt = null;
        try {
            pstmt = connection.prepareStatement(sb.toString());
            pstmt.setInt(1, memberNum);
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

}