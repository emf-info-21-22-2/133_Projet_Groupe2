document.addEventListener("DOMContentLoaded", function () {
    const createQuestionForm = document.getElementById("createQuestionForm");
    const creationMessage = document.getElementById("creation-message");

    createQuestionForm.addEventListener("submit", function (event) {
        event.preventDefault();

        const questionText = document.getElementById("questionText").value;

        // Appel à la fonction pour créer une question depuis servicesHttp.js
        ajouterQuestion(questionText, function (response) {
            console.log("Question créée avec succès :", response);
            creationMessage.textContent = "Question créée avec succès.";
            createQuestionForm.reset(); // Réinitialiser le formulaire après ajout
        }, function (xhr, status, error) {
            console.error("Échec de la création de la question :", xhr, status, error);
            creationMessage.textContent = "Échec de la création de la question. " +
                                           "Statut : " + status + ", Erreur : " + error;
        });
    });
});
