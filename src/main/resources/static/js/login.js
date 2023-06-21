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


     document.addEventListener('DOMContentLoaded', function() {
        var passwordToggleIconHide = document.querySelector('.password-toggle-icon-hide');
        var passwordToggleIconShow = document.querySelector('.password-toggle-icon-show');
        var passwordInput = document.querySelector('.email_loginForm_password input[type="password"]');

        passwordToggleIconHide.addEventListener('click', function() {
          passwordInput.setAttribute('type', 'text');
          passwordToggleIconHide.style.display = 'none';
          passwordToggleIconShow.style.display = 'inline-block';
        });

        passwordToggleIconShow.addEventListener('click', function() {
          passwordInput.setAttribute('type', 'password');
          passwordToggleIconHide.style.display = 'inline-block';
          passwordToggleIconShow.style.display = 'none';
        });
      });