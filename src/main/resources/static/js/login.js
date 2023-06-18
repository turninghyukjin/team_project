function buttonClick_emailRegister()  {
  window.location.href = '/register'
}

function toggleLoginForm() {
        var loginFormWrapper = document.querySelector('.email_loginForm_wrapper');

        if (loginFormWrapper.style.display === 'none') {
            loginFormWrapper.style.display = 'block';
        } else {
            loginFormWrapper.style.display = 'none';
        }
    }