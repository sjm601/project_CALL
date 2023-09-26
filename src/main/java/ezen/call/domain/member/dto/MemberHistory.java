package ezen.call.domain.member.dto;

/**
 * JavaBean 규약에 따라 만든 재사용 가능한 컴포넌트 (2023-07-24)
 * @author 강소영
 */
public class MemberHistory {
    
    private int memberNum;
    private String memberRegisterDate;
    private String administrator;
    private String status;
    private String memberWithdrawDate;
    
    public MemberHistory() {}

    public MemberHistory(int memberNum, String memberRegisterDate, String administrator, String status,
            String memberWithdrawDate) {
        this.memberNum = memberNum;
        this.memberRegisterDate = memberRegisterDate;
        this.administrator = administrator;
        this.status = status;
        this.memberWithdrawDate = memberWithdrawDate;
    }
    
    public MemberHistory(String memberRegisterDate, String administrator, String status,
            String memberWithdrawDate) {
        this.memberRegisterDate = memberRegisterDate;
        this.administrator = administrator;
        this.status = status;
        this.memberWithdrawDate = memberWithdrawDate;
    }

    public int getMemberNum() {
        return memberNum;
    }

    public void setMemberNum(int memberNum) {
        this.memberNum = memberNum;
    }

    public String getMemberRegisterDate() {
        return memberRegisterDate;
    }

    public void setMemberRegisterDate(String memberRegisterDate) {
        this.memberRegisterDate = memberRegisterDate;
    }

    public String getAdministrator() {
        return administrator;
    }

    public void setAdministrator(String administrator) {
        this.administrator = administrator;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMemberWithdrawDate() {
        return memberWithdrawDate;
    }

    public void setMemberWithdrawDate(String memberWithdrawDate) {
        this.memberWithdrawDate = memberWithdrawDate;
    }

    @Override
    public String toString() {
        return "MemberHistory [memberNum=" + memberNum + ", memberRegisterDate=" + memberRegisterDate
                + ", administrator=" + administrator + ", status=" + status + ", memberWithdrawDate="
                + memberWithdrawDate + "]";
    }
}