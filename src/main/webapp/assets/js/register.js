const password = document.getElementById("password");
const confirmPassword = document.getElementById("confirmPassword");
const email = document.getElementById("email");

const error2 = document.getElementById("error2");
const error3 = document.getElementById("error3");

// Sự kiện pass
password.addEventListener("input", confirmPass);
confirmPassword.addEventListener("input", confirmPass);
email.addEventListener("input", checkEmail);

function checkEmail() {
  if (!email.value.includes("@gmail.com") && email.value != "") {
    error2.style.display = "block";
  } else {
    error2.style.display = "none";
  }
}


function confirmPass() {
  if (
    password.value != confirmPassword.value &&
    confirmPassword.value != "" &&
    password.value != ""
  ) {
    error3.style.display = "block";
  } else {
    error3.style.display = "none";
  }
}

const usernamecheck = UserDAO