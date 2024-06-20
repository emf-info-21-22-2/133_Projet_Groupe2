class LoginCtrl{
	
    constructor() {
        this.init();
    }

    creerEntraineur() {
        var username = document.getElementById("TXTusername").value;
        var password = document.getElementById("TXTpassword").value;

        loginConnecter(username, password, this.loginSuccess.bind(this), this.loginError.bind(this));
    }

    loginSuccess(data) {
        alert("User créé avec succès!", data);
        //window.location.href = "app.html";
        
    }

    loginError(data) {
        alert("User incorrecte ou deja créer !", data);
    }


    init() {
        var btnLogin = document.getElementById("buttonLogin");
        btnLogin.addEventListener('click', () => this.creerUser());

    }
}
$(document).ready(function () {
    var loginCTRL = new LoginCtrl();
  });
