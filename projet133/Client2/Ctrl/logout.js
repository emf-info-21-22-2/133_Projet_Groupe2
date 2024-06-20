document.addEventListener("DOMContentLoaded", function() {
    const logoutButton = document.getElementById("logoutButton");
    if (logoutButton) {
        logoutButton.addEventListener("click", function() {
            // Appel à la fonction de déconnexion depuis servicesHttp.js
            deconnecterUtilisateur(function(response) {
                console.log("Déconnexion réussie :", response);
                // Redirection vers la page de connexion après déconnexion
                window.location.href = "index.html";
            }, function(xhr, status, error) {
                console.error("Erreur lors de la déconnexion :", status, error);
            });
        });
    } else {
        console.error("Le bouton de déconnexion n'a pas été trouvé dans le DOM.");
    }
});
