document.addEventListener("DOMContentLoaded", function () {
    const createUserForm = document.getElementById("createUserForm");
    const creationMessage = document.getElementById("creation-message");
    const createQuestionButton = document.getElementById("createQuestionButton");
    if (createQuestionButton) {
        createQuestionButton.addEventListener("click", function () {
           
                // Rediriger vers la page de connexion si le token de session n'existe pas
                window.location.href = "addQuestion.html";
           
        });
    }

    createUserForm.addEventListener("submit", function (event) {
        event.preventDefault();

        const newUsername = document.getElementById("newUsername").value;
        const newPassword = document.getElementById("newPassword").value;

        // Appel à la fonction d'ajout d'utilisateur depuis servicesHttp.js
        enregistrerUtilisateur(newUsername, newPassword, function (response) {
            console.log("Utilisateur ajouté avec succès :", response);
            creationMessage.textContent = "Utilisateur ajouté avec succès.";
            createUserForm.reset(); // Réinitialiser le formulaire après ajout
        }, function (xhr, status, error) {
            console.error("Erreur lors de l'ajout d'utilisateur :", status, error);
            creationMessage.textContent = "Erreur lors de l'ajout d'utilisateur.";
        });
    });

    
});
