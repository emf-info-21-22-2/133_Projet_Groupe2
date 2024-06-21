document.addEventListener("DOMContentLoaded", function() {
    // Charger les questions lorsque la page est prête
    chargerQuestions(function(data) {
        console.log("Données reçues : ", data);
        const questionsList = document.getElementById("questionsList");

        // Parcourir les questions et leurs réponses
        for (let questionText in data) {
            // Créer un élément pour la question
            const questionElement = document.createElement("div");
            questionElement.classList.add("enoncer");
            questionElement.textContent = questionText; // Le texte de la question
            questionsList.appendChild(questionElement);
        }
    }, function(xhr, status, error) {
        console.error("Échec du chargement des questions :", status, error);
        const questionsList = document.getElementById("questionsList");
        questionsList.textContent = "Échec du chargement des questions.";
    });
});
