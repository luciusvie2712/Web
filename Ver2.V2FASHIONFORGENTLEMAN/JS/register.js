var email = document.querySelector("#email_user_inp"); 
var pass = document.querySelector("#pass");
var cfpass = document.querySelector("#cfpass");
var btndn = document.querySelector("#button");
btndn.addEventListener("click", e => {
    e.preventDefault();
    if(email.value == "" || pass.value == "" || cfpass.value == ""){
        alert("Nhung thanh phan bat buoc khong duoc bo trong!");
    }
    else{
        if(pass.value !== cfpass.value){
            alert("Mat khau khong khop")
        }
        else{
            var user = {
                email : email.value,
                password : pass.value
            };
            var json = JSON.stringify(user);
            localStorage.setItem(email.value, json);
            alert("Dang ky thanh cong!")
            window.location.href = "../HTML/login.html";
        }
    }
})       