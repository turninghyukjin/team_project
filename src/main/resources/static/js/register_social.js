
    function checkNickname() {

        var nicknameInput = $('#nicknameInput').val(); //id값이 "nicknameInput"인 입력란의 값을 저장

        $.ajax({
            url:'/register/social/nicknameDuplicate', //Controller에서 요청 받을 주소
            type:'post', //POST 방식으로 전달
            data:{nickname:nicknameInput},
            success:function(result){ //컨트롤러에서 넘어온 result값을 받는다
                if(result == 'N'){ // result이 N이면 -> 사용 가능한 아이디
                    resultMsg = '사용 가능한 아이디입니다.';
                    $("#resultMsg").text(resultMsg).css("color", "blue");
                } else { // result이 Y이면 -> 사용중인 아이디
                    resultMsg = '사용중인 아이디입니다.';
                    $("#resultMsg").text(resultMsg).css("color","red");
                    $('#nicknameInput').val('');
                }
            },
            error:function(error){
                console.log("AJAX request 에 대한 에러");
            }
        });
    };






