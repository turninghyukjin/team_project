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

$(document).ready(function(){
        /*웹페이지 열었을 때*/
        $(".checkbox").show();
        $(".checkboxIcon").hide();

        /*img1을 클릭했을 때 img2를 보여줌*/
        $("#img1").click(function(){
            $(".checkbox").hide();
            $(".checkboxIcon").show();
        });

        /*img2를 클릭했을 때 img1을 보여줌*/
        $("#img2").click(function(){
            $("#img1").show();
            $("#img2").hide();
        });
    });





