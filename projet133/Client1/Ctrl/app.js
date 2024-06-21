document.addEventListener("DOMContentLoaded", function() {
    // Charger les questions lorsque la page est prête
    chargerQuestions(function(data) {
        console.log("Données reçues : ", data);
        const questionsList = document.getElementById("questionsList");
        
        for (let questionText in data) {
            const questionElement = document.createElement("div");
            questionElement.classList.add("question");
            questionElement.textContent = questionText;
            questionsList.appendChild(questionElement);

            const answersList = document.createElement("ul");
            answersList.classList.add("answers");
            data[questionText].forEach(function(answer) {
                const answerItem = document.createElement("li");
                answerItem.classList.add("answer");
                answerItem.textContent = answer;
                answersList.appendChild(answerItem);
            });

            questionsList.appendChild(answersList);
        }
    }, function(xhr, status, error) {
        console.error("Échec du chargement des questions :", status, error);
        const questionsList = document.getElementById("questionsList");
        questionsList.textContent = "Échec du chargement des questions.";
    })
});