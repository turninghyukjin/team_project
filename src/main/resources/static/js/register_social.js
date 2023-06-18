
   function buttonClick_SocialRegister2() {
       console.log('Button clicked!');
       window.location.href = '/register/login';
   }
window.onload = function() {
    document.addEventListener('DOMContentLoaded', function() {
        // '회원 가입 완료' 버튼 클릭 이벤트 핸들러 등록
        var button = document.querySelector('.button_registerSocialForm');
        if (button) {
            button.addEventListener('click', buttonClick_SocialRegister2);
        }
    });
};
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
}