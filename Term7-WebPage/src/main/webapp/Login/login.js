document.addEventListener("DOMContentLoaded", function(){
    const loginForm = document.getElementById("login-form");
    const loginMessage = document.getElementById("login-message");

    loginForm.addEventListener("submit", function(event){
        event.preventDefault();

        //example users
        const users = [
            {username: "marvinmonroe", password: "password"},
            {username: "seymourskinner", password:"password"}
        ];

        const username = loginForm.username.value;
        const password = loginForm.password.value;

        const user = users.find((user) => user.username === username && user.password === password);

        if (user) {
            loginMessage.textContent = "Login successful";
            //redirect to view all bookings
            window.location.href = "/bookings/view-all.html";
        }
        else {
            loginMessage.textContent = "Login failed";
        }
    });
});