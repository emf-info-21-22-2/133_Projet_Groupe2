document.addEventListener("DOMContentLoaded", function() {
    const createUserForm = document.getElementById("createUserForm");
    const creationMessage = document.getElementById("creation-message");

    createUserForm.addEventListener("submit", function(event) {
        event.preventDefault();

        const newUsername = document.getElementById("newUsername").value;
        const newPassword = document.getElementById("newPassword").value;

        // Appel à la fonction d'ajout d'utilisateur depuis servicesHttp.js
        enregistrerUtilisateur(newUsername, newPassword, function(response) {
            console.log("Utilisateur ajouté avec succès :", response);
            creationMessage.textContent = "Utilisateur ajouté avec succès.";
            createUserForm.reset(); // Réinitialiser le formulaire après ajout
        }, function(xhr, status, error) {
            console.error("Erreur lors de l'ajout d'utilisateur :", status, error);
            creationMessage.textContent = "Erreur lors de l'ajout d'utilisateur.";
        });
    });

    const logoutButton = document.getElementById("logoutButton");
    logoutButton.addEventListener("click", function() {
        // Appel à la fonction de déconnexion depuis servicesHttp.js
        deconnecterUtilisateur(function(response) {
            console.log("Déconnexion réussie :", response);
            window.location.href = "login.html"; // Redirection vers la page de connexion après déconnexion
        }, function(xhr, status, error) {
            console.error("Erreur lors de la déconnexion :", status, error);
        });
    });
});
