var BASE_URL = "http://localhost:8080/";

/**
 * Fonction permettant de demander la liste des questions au serveur.
 * @param {function} successCallback Fonction de callback lors du retour avec succès de l'appel.
 * @param {function} errorCallback Fonction de callback en cas d'erreur.
 */
function chargerQuestions(successCallback, errorCallback) {
  $.ajax({
    type: "GET",
    url: BASE_URL + "getQuestions",
    success: successCallback,
    error: errorCallback
  });
}

/**
 * Fonction pour se connecter.
 * @param {string} username Nom d'utilisateur.
 * @param {string} password Mot de passe.
 * @param {function} successCallback Fonction de callback en cas de succès.
 * @param {function} errorCallback Fonction de callback en cas d'erreur.
 */
function loginConnecter(username, password, successCallback, errorCallback) {
  $.ajax({
    type: "POST",
    url: BASE_URL + "login",
    data: {
      username: username,
      password: password
    },
    success: successCallback,
    error: errorCallback
  });
}

/**
 * Fonction pour enregistrer un nouvel utilisateur.
 * @param {string} username Nom d'utilisateur.
 * @param {string} password Mot de passe.
 * @param {function} successCallback Fonction de callback en cas de succès.
 * @param {function} errorCallback Fonction de callback en cas d'erreur.
 */
function enregistrerUtilisateur(username, password, successCallback, errorCallback) {
  $.ajax({
    type: "POST",
    url: BASE_URL + "addUser",
    data: {
      username: username,
      password: password
    },
    success: successCallback,
    error: errorCallback
  });
}

/**
 * Fonction pour se déconnecter.
 * @param {function} successCallback Fonction de callback en cas de succès.
 * @param {function} errorCallback Fonction de callback en cas d'erreur.
 */
function deconnecterUtilisateur(successCallback, errorCallback) {
  $.ajax({
    type: "POST",
    url: BASE_URL + "logout",
    success: successCallback,
    error: errorCallback
  });
}

/**
 * Fonction pour ajouter une question.
 * @param {string} enoncer L'énoncé de la question.
 * @param {function} successCallback Fonction de callback en cas de succès.
 * @param {function} errorCallback Fonction de callback en cas d'erreur.
 */
function ajouterQuestion(enoncer, successCallback, errorCallback) {
  $.ajax({
    type: "POST",
    url: BASE_URL + "addQuestion",
    data: {
      enoncer: enoncer
    },
    success: successCallback,
    error: errorCallback
  });
}

/**
 * Fonction pour supprimer une question.
 * @param {number} id ID de la question.
 * @param {function} successCallback Fonction de callback en cas de succès.
 * @param {function} errorCallback Fonction de callback en cas d'erreur.
 */
function supprimerQuestion(id, successCallback, errorCallback) {
  $.ajax({
    type: "POST",
    url: BASE_URL + "deleteQuestion",
    data: {
      id: id
    },
    success: successCallback,
    error: errorCallback
  });
}

/**
 * Fonction pour ajouter une réponse.
 * @param {string} newReponse Le texte de la réponse.
 * @param {boolean} correcte Indique si la réponse est correcte.
 * @param {number} question ID de la question associée.
 * @param {function} successCallback Fonction de callback en cas de succès.
 * @param {function} errorCallback Fonction de callback en cas d'erreur.
 */
function ajouterReponse(newReponse, correcte, question, successCallback, errorCallback) {
  $.ajax({
    type: "POST",
    url: BASE_URL + "addReponse",
    data: {
      newReponse: newReponse,
      correcte: correcte,
      question: question
    },
    success: successCallback,
    error: errorCallback
  });
}

/**
 * Fonction pour supprimer une réponse.
 * @param {number} id ID de la réponse.
 * @param {function} successCallback Fonction de callback en cas de succès.
 * @param {function} errorCallback Fonction de callback en cas d'erreur.
 */
function supprimerReponse(id, successCallback, errorCallback) {
  $.ajax({
    type: "POST",
    url: BASE_URL + "deleteReponse",
    data: {
      id: id
    },
    success: successCallback,
    error: errorCallback
  });
}

/**
 * Fonction pour vérifier une réponse.
 * @param {number} idReponse ID de la réponse.
 * @param {function} successCallback Fonction de callback en cas de succès.
 * @param {function} errorCallback Fonction de callback en cas d'erreur.
 */
function verifierReponse(idReponse, successCallback, errorCallback) {
  $.ajax({
    type: "POST",
    url: BASE_URL + "checkReponse",
    data: {
      idReponse: idReponse
    },
    success: successCallback,
    error: errorCallback
  });
}
