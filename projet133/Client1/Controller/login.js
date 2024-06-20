document.getElementById('loginForm').addEventListener('submit', function(event) {
    event.preventDefault();
    
    const username = document.getElementById('username').value;
    const password = document.getElementById('password').value;
    const errorMessage = document.getElementById('error-message');

    if (username === "user" && password === "password") {
        alert("Login réussi!");
        errorMessage.textContent = "";
    } else {
        errorMessage.textContent = "Identifiant ou mot de passe incorrect.";
    }
});
