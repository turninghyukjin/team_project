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
function toggleCheckbox(element) {
  if (element && element.querySelector) {
    var checkbox = element.querySelector('.checkbox');
    var checkboxIcon = element.querySelector('.checkboxIcon');

    if (checkbox && checkboxIcon) {
      checkbox.classList.toggle('active');
      checkbox.style.display = checkbox.classList.contains('active') ? 'none' : 'block';
      checkboxIcon.style.display = checkbox.classList.contains('active') ? 'block' : 'none';

      if (element.classList.contains('signUp_agree_checkboxForm_wrapper')) {
        toggleAllCheckboxes(checkbox.classList.contains('active'));
      } else {
        toggleAllCheckboxes();
      }
    }
  }
}

function toggleAllCheckboxes(allChecked) {
  var allCheckbox = document.querySelector('.signUp_agree_checkboxForm_wrapper .checkbox');
  var allCheckboxIcon = document.querySelector('.signUp_agree_checkboxForm_wrapper .checkboxIcon');
  var individualCheckboxes = document.querySelectorAll('.checkbox:not(.all)');

  if (allChecked !== undefined) {
    // 모두 동의 체크박스의 상태를 변경
    allCheckbox.classList.toggle('active');
    allCheckbox.style.display = allCheckbox.classList.contains('active') ? 'none' : 'block';
    allCheckboxIcon.style.display = allCheckbox.classList.contains('active') ? 'block' : 'none';
  }

  if (allCheckbox.classList.contains('active')) {
    // 개별 체크박스들을 모두 선택한 상태로 변경
    for (var i = 0; i < individualCheckboxes.length; i++) {
      var checkbox = individualCheckboxes[i];
      var checkboxIcon = checkbox.nextElementSibling;

      checkbox.classList.add('active');
      checkbox.style.display = 'none';
      checkboxIcon.style.display = 'block';
    }
  } else {
    // 개별 체크박스들을 선택하지 않은 상태로 변경
    for (var i = 0; i < individualCheckboxes.length; i++) {
      var checkbox = individualCheckboxes[i];
      var checkboxIcon = checkbox.nextElementSibling;

      checkbox.classList.remove('active');
      checkbox.style.display = 'block';
      checkboxIcon.style.display = 'none';
    }
  }
}

// 개별 체크박스 클릭 시 toggleCheckbox 함수 호출
var individualCheckboxes = document.querySelectorAll('.checkbox:not(.all)');
for (var i = 0; i < individualCheckboxes.length; i++) {
  var checkboxWrapper = individualCheckboxes[i].parentNode;
  checkboxWrapper.addEventListener('click', function() {
    toggleCheckbox(this);
  });
}




var adultCertButton = document.getElementById('adultCertButton');
var requiredCheckboxes = document.querySelectorAll('.checkbox.required');

// 버튼 초기 비활성화
adultCertButton.disabled = true;

// 개별 체크박스 클릭 시 toggleCheckbox 함수 호출
var individualCheckboxes = document.querySelectorAll('.checkbox:not(.all)');
for (var i = 0; i < individualCheckboxes.length; i++) {
  var checkboxWrapper = individualCheckboxes[i].parentNode;
  checkboxWrapper.addEventListener('click', function() {
    toggleCheckbox(this);
    updateButtonState();
  });
}

// 모두 동의 체크박스 클릭 시 toggleAllCheckboxes 함수 호출
var allCheckboxWrapper = document.querySelector('.signUp_agree_checkboxForm_wrapper');
allCheckboxWrapper.addEventListener('click', function() {
  toggleAllCheckboxes();
});

// 필수 체크박스 개수 체크 및 버튼 상태 업데이트 함수
function updateButtonState() {
  var checkedRequiredCheckboxes = document.querySelectorAll('.checkbox.required.active');
  adultCertButton.disabled = checkedRequiredCheckboxes.length < 2;
}

// 페이지 로드 시 버튼 상태 업데이트
window.addEventListener('load', function() {
  updateButtonState();
});

var adultCertButton = document.getElementById('adultCertButton');

// 버튼 비활성화
adultCertButton.disabled = true;

// ...

// 페이지 로드 시 버튼 상태 업데이트
window.addEventListener('load', function() {
  updateButtonState();
});