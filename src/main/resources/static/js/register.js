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

// 동의 체크박스 작업 시작


    // DOMContentLoaded 이벤트 핸들러
    document.addEventListener('DOMContentLoaded', function() {
        // #allCheck 요소를 클릭할 때의 이벤트 핸들러
        document.getElementById('allCheck').addEventListener('click', function () {
            // 모든 .checkbox 요소를 선택
            var checkboxes = document.querySelectorAll('.checkbox');

            // 각각의 .checkbox 요소에 대해 처리
            checkboxes.forEach(function (checkbox) {
                // .checkbox 클래스를 .checkboxIcon 클래스로 변경
                checkbox.classList.toggle('checkboxIcon');
                checkbox.classList.toggle('checkbox');

                // 해당 checkbox 요소 아래의 span 태그의 텍스트 변경
                var span = checkbox.nextElementSibling;
                span.textContent = checkbox.classList.contains('checkboxIcon') ? '동의합니다' : '모두 동의합니다';
            });
        });
    });





