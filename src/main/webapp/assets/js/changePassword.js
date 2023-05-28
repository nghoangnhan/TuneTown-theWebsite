const newPassword = document.getElementById("newPassword");
const reNewPassword = document.getElementById("reNewPassword");

const error= document.getElementById("error");
// Sự kiện pass
newPassword.addEventListener("input", confirmPassword);
reNewPassword.addEventListener("input", confirmPassword);

function confirmPassword() {
    if (
        newPassword.value != reNewPassword.value &&
        reNewPassword.value != "" &&
        newPassword.value != ""
    ) {
        error.style.display = "block";
    } else {
        error.style.display = "none";
    }
}
