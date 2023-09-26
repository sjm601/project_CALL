package ezen.call.domain.member.dto;

/**
 * Member, MemberHistory 바인드 클래스
 * @author 김종원
 */
public class MemberCombined {

    private Member member;
    private MemberHistory memberHistory;
    
    public MemberCombined () {}
    
    public MemberCombined (Member member, MemberHistory memberHistory) {
        this.member = member;
        this.memberHistory = memberHistory;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public MemberHistory getMemberHistory() {
        return memberHistory;
    }

    public void setMemberHistory(MemberHistory memberHistory) {
        this.memberHistory = memberHistory;
    }

    @Override
    public String toString() {
        return "MemberInfo [" + member + ", " + memberHistory + "]";
    }
}