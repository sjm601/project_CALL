package ezen.call.domain.member.dto;

/**
 * JavaBean 규약에 따라 만든 재사용 가능한 컴포넌트
 * @author 이한솔
 */
public class Member {

    private int memberNum;
    private String email;
    private String password;
    private String memberName;
    private String phoneNum;
    private String birthday;
    private String gender;
    
    public Member() {}

    public Member(int memberNum, String email, String password, String memberName, String phoneNum, String birthday, String gender) {
        this.memberNum = memberNum;
        this.email = email;
        this.password = password;
        this.memberName = memberName;
        this.phoneNum = phoneNum;
        this.birthday = birthday;
        this.gender = gender;
    }
    
    public Member(String email, String password, String memberName, String phoneNum, String birthday, String gender) {
        this.email = email;
        this.password = password;
        this.memberName = memberName;
        this.phoneNum = phoneNum;
        this.birthday = birthday;
        this.gender = gender;
    }

    public Member(String email, String phoneNum) {
        this.email = email;
        this.phoneNum = phoneNum;
    }

    public int getMemberNum() {
        return memberNum;
    }

    public void setMemberNum(int memberNum) {
        this.memberNum = memberNum;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthDay(String birthday) {
        this.birthday = birthday;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "Member [memberNum=" + memberNum + ", email=" + email + ", password=" + password + ", memberName="
                + memberName + ", phoneNum=" + phoneNum + ", birthday=" + birthday + ", gender=" + gender + "]";
    }
}