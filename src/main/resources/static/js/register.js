const userCode = "imp47175133";
IMP.init(userCode);

window.onload = function() {
  const button = document.getElementById("adultCertButton");
  button.addEventListener("click", handleAdultCertification);
};

function handleAdultCertification() {
  IMP.certification(
    {
      pg: "html5_inicis.MIIiasTest",
      merchant_uid: "1570777386811537",
      company: "sul",
      m_redirect_url: "http://localhost/register/social",
      popup: true,
    },
    function(rsp) {
      if (rsp.success) {
        // jQuery로 HTTP 요청
        jQuery.ajax({
          url: "/register",
          method: "POST",
          headers: { "Content-Type": "application/json" },
          data: { imp_uid: rsp.imp_uid }
        });

        // 본인인증이 완료되었습니다.
//        alert("본인인증이 완료되었습니다.");

        // 다음 페이지로 이동
        window.location.href = "http://localhost/register/social";
      } else {
        alert("인증에 실패하였습니다. 에러 내용: " + rsp.error_msg);
      }
    }
  );
}


function toggleCheckbox(checkboxWrapper) {
  const checkbox = checkboxWrapper.querySelector('#checkbox');
  const checkboxIcon = checkboxWrapper.querySelector('#checkboxIcon');

  if (checkbox.checked) {
    checkboxIcon.style.display = 'block';
    // 여기에 버튼 활성화에 대한 로직을 추가하세요.
    // 예를 들어, 버튼의 disabled 속성을 false로 설정하여 활성화할 수 있습니다.
  } else {
    checkboxIcon.style.display = 'none';
    // 여기에 버튼 비활성화에 대한 로직을 추가하세요.
    // 예를 들어, 버튼의 disabled 속성을 true로 설정하여 비활성화할 수 있습니다.
  }
}


// 필수 동의 체크박스 요소들의 부모 래퍼 요소를 선택합니다.
const checkBoxWrapper = document.querySelectorAll('.checkbox-wrapper');

// 필수 동의 체크박스 요소들을 선택합니다.
const requiredCheckboxes = checkBoxWrapper.querySelectorAll('.checkbox-wrapper input[type="checkbox"]');

// 동의하기 버튼 요소를 선택합니다.
const agreeButton = document.getElementById('adultCertButton');

// 필수 동의 체크박스의 개수를 저장하기 위한 변수를 초기화합니다.
let requiredCheckedCount = 0;

// 필수 동의 체크박스의 변경 이벤트를 처리하는 함수입니다.
function handleRequiredCheckboxChange() {
  // 체크된 필수 동의 개수를 초기화합니다.
  requiredCheckedCount = 0;

  // 필수 동의 체크박스 요소들을 순회하면서 체크 상태를 확인합니다.
  requiredCheckboxes.forEach(checkbox => {
    if (checkbox.checked) {
      requiredCheckedCount++;
    }
  });

  // 필수 동의 체크박스 2개가 체크되었을 때만 버튼을 활성화합니다.
  if (requiredCheckedCount === 2) {
    agreeButton.disabled = false;
  } else {
    agreeButton.disabled = true;
  }
}

// 필수 동의 체크박스들에 이벤트 리스너를 등록합니다.
requiredCheckboxes.forEach(checkbox => {
  checkbox.addEventListener('change', handleRequiredCheckboxChange);
});

// 페이지 진입 시 동의하기 버튼을 비활성화합니다.
agreeButton.disabled = true;

function toggleCheckbox(checkboxWrapper) {
  const checkbox = checkboxWrapper.querySelector('#checkbox');
  const checkboxIcon = checkboxWrapper.querySelector('#checkboxIcon');

  if (checkbox.style.display === 'none') {
    checkbox.style.display = 'block';
    checkboxIcon.style.display = 'none';
    checkbox.checked = true;
  } else {
    checkbox.style.display = 'none';
    checkboxIcon.style.display = 'block';
    checkbox.checked = false;
  }

  // 체크 상태 변경 후 필수 동의 체크박스 변경 이벤트를 호출하여 동의하기 버튼을 업데이트합니다.
  handleRequiredCheckboxChange();
}
