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



  $(document).ready(function() {
        $(".checkbox-wrapper").click(function() {
            var checkbox = $(this).find("input[type='checkbox']");
            checkbox.prop("checked", !checkbox.prop("checked"));
            $(this).toggleClass("checked");
        });
    });