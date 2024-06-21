document.addEventListener("DOMContentLoaded", function() {
    // Attacher un écouteur d'événements à la soumission du formulaire de connexion
    const loginForm = document.getElementById("loginForm");
    loginForm.addEventListener("submit", function(event) {
        event.preventDefault(); // Empêcher la soumission traditionnelle du formulaire

        // Récupérer les valeurs des champs
        const username = document.getElementById("TXTusername").value;
        const password = document.getElementById("TXTpassword").value;

        // Effacer tout message d'erreur précédent
        const errorMessage = document.getElementById("error-message");
        errorMessage.textContent = "";

        // Appeler la fonction de connexion depuis serviceshttp.js
        loginConnecter(username, password, function(data) {
            // Callback de succèss
            console.log("Connexion réussie :", data);
            window.location.href = "app.html"; 
            // Rediriger ou effectuer d'autres actions après une connexion réussie
            const questions = document.getElementById("question").value;
            
        }, function(xhr, status, error) {
            // Callback d'erreur
            console.error("Échec de la connexion :", status, error);
            errorMessage.textContent = "Échec de la connexion. Veuillez vérifier vos identifiants.";
        });
    });
});
