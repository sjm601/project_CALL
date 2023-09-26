// 쿠키 값을 가져오는 함수
function getCookie(name) {
    var value = "; " + document.cookie;
    var parts = value.split("; " + name + "=");
    if (parts.length === 2)
        return parts.pop().split(";").shift();
}

// 로그인 시 호출되는 함수
function loginSubmit() {
    const email = document.getElementById("userEmail").value;
    const saveEmailCheckbox = document.getElementById("saveEmail");
    // 로그인 성공 후, 아이디 저장 체크박스가 선택되어 있다면 쿠키를 설정합니다.
    if (saveEmailCheckbox.checked) {
        setCookie("saveEmail", email, 2592000); // 30일 동안 쿠키 유지
    } else {
        // 체크박스가 선택되어 있지 않으면 기존의 쿠키를 삭제합니다.
        setCookie("saveEmail", email, 0);
    }
    return false; // 페이지 전환 방지
}

// 쿠키 값을 가져와서 input 태그에 설정하는 함수
function setEmailInputValue() {
    const savedEmail = getCookie("saveEmail");
    if (savedEmail) {
        document.getElementById("userEmail").value = savedEmail;
    }
}

function checkBoxCheck() {
    const savedEmail = getCookie("saveEmail");
    if (savedEmail) {
        const saveEmailCheckbox = document.getElementById("saveEmail");
        saveEmailCheckbox.checked = true;
    }
}

document.addEventListener("DOMContentLoaded", function() {
    checkBoxCheck();
    setEmailInputValue();
});