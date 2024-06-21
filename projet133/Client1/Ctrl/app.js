$(document).ready(function() {
    // Appel de la fonction pour charger les questions
    chargerQuestions(function(questions) {
        // Success callback - gestion des questions récupérées
        afficherQuestions(questions);
    }, function(error) {
        // Error callback - gestion des erreurs
        console.error("Erreur lors du chargement des questions:", error);
        $("#question").text("Impossible de charger les questions.");
    });
});

/**
 * Fonction permettant d'afficher les questions dans le label.
 * @param {Array} questions Liste des questions récupérées.
 */
function afficherQuestions(questions) {
    // Supposons que 'questions' est un tableau d'objets contenant les questions.
    var questionLabel = $("#question");
    questionLabel.text("");  // Réinitialiser le contenu du label

    questions.forEach(function(question, index) {
        // Ajout de chaque question dans le label. Utilisez 'text' si vous voulez juste du texte
        questionLabel.append((index + 1) + ". " + question.texte + "<br>");
    });
}
