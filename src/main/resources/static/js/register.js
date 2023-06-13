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
                        console.log("본인인증 성공");
                    } else {
                        console.log("본인인증 실패");
                    }
                }
            );
        }