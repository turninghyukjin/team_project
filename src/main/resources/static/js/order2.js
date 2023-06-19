$(document).ready(function() {
  // 버튼 비활성화
  $(".orderButtonStyle").prop("disabled", true);

  // 체크박스 클릭 이벤트 처리
  $(".custom_checkbox").click(function() {
    var isChecked = $(this).find("img").eq(1).is(":visible");
    if (isChecked) {
      $(this).find("img").eq(0).show();
      $(this).find("img").eq(1).hide();
    } else {
      $(this).find("img").eq(0).hide();
      $(this).find("img").eq(1).show();
    }

    // 체크박스 상태에 따라 결제 버튼 활성화/비활성화
    var allCheckboxes = $(".custom_checkbox");
    var isAnyCheckboxChecked = false;

    allCheckboxes.each(function() {
      if ($(this).find("img").eq(1).is(":visible")) {
        isAnyCheckboxChecked = true;
        return false; // 체크된 첫 번째 체크박스만 확인하고 반복문 종료
      }
    });

    if (isAnyCheckboxChecked) {
      // 체크박스가 하나라도 체크되어 있으면 결제 버튼 활성화
      $(".orderButtonStyle").prop("disabled", false);
    } else {
      // 모든 체크박스가 체크되지 않았으면 결제 버튼 비활성화
      $(".orderButtonStyle").prop("disabled", true);
    }
  });
});

$(document).ready(function() {
  $(".agree_content").click(function() {
    $(".agreeDetailContent_wrapper").toggle();
  });
});


   $(document).ready(function() {
     $("button.orderButtonStyle").click(function() {
       var IMP = window.IMP;
       IMP.init("imp47175133");

       var today = new Date();
       var hours = today.getHours(); // 시
       var minutes = today.getMinutes();  // 분
       var seconds = today.getSeconds();  // 초
       var milliseconds = today.getMilliseconds();
       var makeMerchantUid = hours + minutes + seconds + milliseconds;

       function requestPay() {
         IMP.request_pay({
           pg: 'kcp',
           pay_method: 'card',
           merchant_uid: "IMP" + makeMerchantUid,
           name: '막걸리',
           amount: 21000,
           buyer_email: 'kgm34872804@gmail.com',
           buyer_name: 'SUL',
           buyer_tel: '010-1234-5678',
           buyer_addr: '성남시 분당구',
           buyer_postcode: '123-456'
         }, function(rsp) { // callback
           if (rsp.success) {
             console.log(rsp);
           } else {
             console.log(rsp);
           }
         });
       }

       requestPay();
     });
   });

