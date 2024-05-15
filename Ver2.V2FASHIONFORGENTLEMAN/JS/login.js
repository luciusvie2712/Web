const email = document.querySelector("#email_user_inp");
const pass = document.querySelector("#pass");
const loginBtn = document.querySelector("#button");
loginBtn.addEventListener("click", e => {
    e.preventDefault();
    if(email.value == "" || pass.value == ""){
        alert("Không được bỏ trống");
    }else{
        const data = JSON.parse(localStorage.getItem(email.value));
        console.log(data.email);
        console.log(data.password);
        if(data.email == email.value && data.password == pass.value){
            alert("Đăng nhập thành công");
            window.location.href = "../HTML/main.html";
        }
        else{
            alert("Vui lòng kiểm tra lại email hoặc mật khẩu");
        }
    }
})