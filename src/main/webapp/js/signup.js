const allInput = document.querySelector('input.all');
const inputList = document.querySelectorAll('input.check-item');
allInput.addEventListener('change', function (e) {
    e.preventDefault();
    for (let i = 0; i < inputList.length; i++) {
        inputList[i].checked = this.checked;
    };
});

//필수 사항 체크시 전체 동의
const autoCheck = document.querySelectorAll('input.check-item');
const autoCheckAll = document.querySelector('input.all');

for (let i = 0; i < autoCheck.length; i++) {
  autoCheck[i].addEventListener('change', function () {
    let allChecked = true;
    for (let j = 0; j < autoCheck.length; j++) {
      if (!autoCheck[j].checked) {
        allChecked = false;
        break;
      }
    }
    autoCheckAll.checked = allChecked;
  });
}

autoCheckAll.addEventListener('change', function () {
  for (let i = 0; i < autoCheck.length; i++) {
    autoCheck[i].checked = this.checked;
  }
});

  //비밀번호 확인
  const passwordInput = document.getElementById('passwd');
  const passwordConfirm = document.getElementById('repasswd');
  
  passwordConfirm.addEventListener('input', function () {
    const invalidFeedback = this.nextElementSibling;
    const errorMessage = '비밀번호가 맞지 않습니다...';
  
    if (this.value !== passwordInput.value) {
      this.parentElement.classList.add('was-validated');
      invalidFeedback.textContent = errorMessage;
      invalidFeedback.style.display = 'block';
    } else {
      this.parentElement.classList.remove('was-validated');
      invalidFeedback.style.display = 'none';
    }
  });
  
// 정규 표현식 패턴
const emailPattern = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
const passwordPattern = /^(?=.*[a-zA-Z])(?=.*[0-9]).{6,30}$/;
const namePattern = /^[가-힣]{2,10}$/;
const phoneNumberPattern = /^\d{3}\d{3,4}\d{4}$/;
const datePattern = /^(19[0-9][0-9]|20\d{2})(0[0-9]|1[0-2])(0[1-9]|[1-2][0-9]|3[0-1])$/;

const form = document.querySelector('.needs-validation');

function validateInput(inputElement, regexPattern) {
  const inputValue = inputElement.value;
  return regexPattern.test(inputValue);
}

const inputs = form.querySelectorAll('.form-control');
inputs.forEach((input) => {
  input.addEventListener('input', function () {
    const pattern = this.getAttribute('id') === 'name' ? namePattern
                  : this.getAttribute('id') === 'email' ? emailPattern
                  : this.getAttribute('id') === 'passwd' ? passwordPattern
                  : this.getAttribute('id') === 'phoneNum' ? phoneNumberPattern
                  : this.getAttribute('id') === 'birthday' ? datePattern
                  : null;

    if (pattern) {
      const invalidFeedback = input.nextElementSibling;
      const errorMessage = this.getAttribute('id') === 'name' ? '이름을 정확히 입력하여주세요...'
                         : this.getAttribute('id') === 'email' ? '유효하지 않은 이메일 형식입니다...'
                         : this.getAttribute('id') === 'passwd' ? '영문과 숫자를 포함한 6자 이상 30자 이하의 비밀번호를 입력하여주세요...'
                         : this.getAttribute('id') === 'phoneNum' ? '전화번호를 정확히 입력하여주세요...'
                         : this.getAttribute('id') === 'birthday' ? '생년월일을 정확히입력하여주세요...'
                         : null;

      if (!validateInput(input, pattern)) {
        input.parentElement.classList.add('was-validated');
        invalidFeedback.textContent = errorMessage;
        invalidFeedback.style.display = 'block';
      } else {
        input.parentElement.classList.remove('was-validated');
        invalidFeedback.style.display = 'none';
      }
    }
  });
});

//성별 선택 체크 박스 유효성 검사
const genderRadios = document.querySelectorAll('input[name="options"]');
const genderInvalidFeedback = document.querySelector('.input-group .invalid-feedback');

function validateGender() {
  const checkedRadio = Array.from(genderRadios).some((radio) => radio.checked);
  checkedRadio ? genderInvalidFeedback.style.display = 'none' : genderInvalidFeedback.style.display = 'block';
}

genderRadios.forEach(radio => radio.addEventListener('change', validateGender));

//유효성 검사 실행
form.addEventListener('submit', function (e) {
  if (!form.checkValidity()) {
    e.preventDefault();
    e.stopPropagation();
  }

  inputs.forEach((input) => {
    const pattern = input.getAttribute('id') === 'name' ? namePattern
                  : input.getAttribute('id') === 'email' ? emailPattern
                  : input.getAttribute('id') === 'passwd' ? passwordPattern
                  : input.getAttribute('id') === 'phoneNum' ? phoneNumberPattern
                  : input.getAttribute('id') === 'birthday' ? datePattern
                  : null;

    if (pattern) {
      const invalidFeedback = input.nextElementSibling;
      const errorMessage = input.getAttribute('id') === 'name' ? '이름을 입력하여주세요...'
                         : input.getAttribute('id') === 'email' ? '이메일을입력하여주세요...'
                         : input.getAttribute('id') === 'passwd' ? '비밀번호를 입력하여주세요...'
                         : input.getAttribute('id') === 'phoneNum' ? '전화번호를 입력하여주세요...'
                         : input.getAttribute('id') === 'birthday' ? '생년월일을 입력하여주세요...'
                         : null;

      if (!validateInput(input, pattern)) {
        input.parentElement.classList.add('was-validated');
        invalidFeedback.textContent = errorMessage;
        invalidFeedback.style.display = 'block';
      } else {
        input.parentElement.classList.remove('was-validated');
        invalidFeedback.style.display = 'none';
      }
    }
  });
  validateGender();

  //약관동의 박스 유효성 검사
  const checkboxGroups = form.querySelectorAll('.form-check');
  checkboxGroups.forEach(function (group) {
    const checkbox = group.querySelector('input');
    const checkboxInvalidFeedback = group.querySelector('.invalid-feedback');

    if (!checkbox.checkValidity()) {
      group.classList.add('was-validated');
      checkboxInvalidFeedback.style.display = 'block';
    } else {
      group.classList.remove('was-validated');
      checkboxInvalidFeedback.style.display = 'none';
    }
  });
});