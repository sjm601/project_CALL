
  const form = document.querySelector('.needs-validation');
  const inputs = form.querySelectorAll('input');
  const creditRadios = document.querySelectorAll('input[name="payment"]');
  const creditInvalidFeedback = document.querySelector('.payment-info .invalid-feedback');

  // 입력 필드 유효성 검사 함수
  function validateInput(input) {
    return input.value.trim() !== '';
  }

  // 결재방법 선택 체크 박스 유효성 검사 함수
  function validateCredit() {
    const checkedRadio = Array.from(creditRadios).some(radio => radio.checked);
    checkedRadio ? creditInvalidFeedback.style.display = 'none' : creditInvalidFeedback.style.display = 'block';
  }

  // 폼 제출 시 유효성 검사 실행
  form.addEventListener('submit', function (e) {
    e.preventDefault();
    let formIsValid = true;

    inputs.forEach(input => {
      if (!validateInput(input)) {
        input.classList.add('is-invalid');
        formIsValid = false;
      } else {
        input.classList.remove('is-invalid');
      }
    });

    validateCredit();

    if (formIsValid) {
      form.classList.add('was-validated');
      form.submit();
    }
  });

  // 입력 필드 값이 변경될 때 유효성 검사 실행
  inputs.forEach(input => {
    input.addEventListener('input', function () {
      if (!validateInput(input)) {
        input.classList.add('is-invalid');
      } else {
        input.classList.remove('is-invalid');
      }
    });
  });

  // 결재 방법이 변경될 때 유효성 검사 실행
  creditRadios.forEach(radio => radio.addEventListener('change', validateCredit));