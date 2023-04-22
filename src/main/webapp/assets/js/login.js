const form = document.querySelector(".login_form");

// Get elements
const phonenumber = document.querySelector("input[type=text]");
const password = document.querySelector("input[type=password]");

const error = document.getElementById("error");

// Sự kiện trên username
phonenumber.addEventListener("input", (e) => {
  if (e.target.value.length < 9) {
    e.target.style.border = "1px solid ";
    error.style.display = "block";
    e.target.focus();
    return false;
  } else {
    error.style.display = "none";
    return true;
  }
});

// Sự kiện pass
password.addEventListener("input", (e) => {
  if (e.target.value.length < 1) {
    e.target.style.border = "1px solid ";
    error.style.display = "block";
    e.target.focus();
    return false;
  } else {
    error.style.display = "none";
  }
});

//Sự kiện trên form
form.addEventListener("submit", (e) => {
  e.preventDefault();
  if (password.value === "") {
    error.style.display = "block";
  }
  if (
    phonenumber.value.length >= 9 &&
    password.value.length >= 6 &&
    phonenumber.classList.contains("valid") == true
  ) {
    document.forms["form"].submit();
  }
});
