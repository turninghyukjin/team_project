$(document).ready(function(){
    var errorMessage = [[${errorMessage}]];
    if(errorMessage != null){
        alert(errorMessage);
    }
});





function buttonClick_SocialRegister2() {
   console.log('Button clicked!');
   window.location.href = '/register/login';
};

//window.onload = function() {
//    document.addEventListener('DOMContentLoaded', function() {
//        // '회원 가입 완료' 버튼 클릭 이벤트 핸들러 등록
//        var button = document.querySelector('.button_registerSocialForm');
//        if (button) {
//            button.addEventListener('click', buttonClick_SocialRegister2);
//        }
//    });
//};

function checkNickname() {
  var nicknameInput = document.getElementById("nicknameInput"); // 닉네임을 입력하는 입력란의 ID
  var nickname = nicknameInput.value; // 입력된 닉네임 가져오기

  // Ajax 요청
  fetch("/register/social/" + nickname + "/duplicate")
    .then(function (response) {
      return response.text();
    })
    .then(function (message) {
      // 메시지 처리
      if (message === "이미 사용 중인 닉네임입니다.") {
        // 중복된 닉네임 처리
        // 중복된 닉네임 메시지를 표시하는 등의 로직을 추가해주세요.
        console.log("중복된 닉네임입니다.");
      } else {
        // 사용 가능한 닉네임 처리
        // 사용 가능한 닉네임 메시지를 표시하는 등의 로직을 추가해주세요.
        console.log("사용 가능한 닉네임입니다.");
      }
    })
    .catch(function (error) {
      // 오류 처리
      console.log("오류 발생:", error);
    });
};

//function checkEmail(){
//        var email = $('#email').val(); //email값이 "email"인 입력란의 값을 저장
//        $.ajax({
//            url:'/emailDuplicateCheck', //Controller에서 요청 받을 주소
//            type:'post', //POST 방식으로 전달
//            data:{id:email},
//            success:function(resultNumber){ //컨트롤러에서 넘어온 cnt값을 받는다
//                if(cnt == 0){ //cnt가 1이 아니면(=0일 경우) -> 사용 가능한 아이디
//                    $('.id_ok').css("display","inline-block");
//                    $('.id_already').css("display", "none");
//                } else { // cnt가 1일 경우 -> 이미 존재하는 아이디
//                    $('.id_already').css("display","inline-block");
//                    $('.id_ok').css("display", "none");
//                    alert("아이디를 다시 입력해주세요");
//                    $('#id').val('');
//                }
//            },
//            error:function(){
//                alert("에러입니다");
//            }
//        });
//        };