document.addEventListener("DOMContentLoaded", function() {
    const registerForm = document.getElementById("registrationForm");
    const loginForm = document.getElementById("loginForm");

    if (registerForm) {
        registerForm.addEventListener("submit", function(event) {
            event.preventDefault();
            const data = {
                nome: document.getElementById("nome").value,
                sobrenome: document.getElementById("sobrenome").value,
                email: document.getElementById("email").value,
                password: document.getElementById("senha").value
            };

            fetch("http://localhost:8080/api/auth/register", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify(data)
            })
                .then(response => response.text())
                .then(data => alert(data))
                .catch(error => console.error("Erro:", error));
        });
    }

    if (loginForm) {
        loginForm.addEventListener("submit", function(event) {
            event.preventDefault();
            const data = {
                email: document.getElementById("email").value,
                password: document.getElementById("senha").value
            };

            fetch("http://localhost:8080/api/auth/login", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify(data)
            })
                .then(response => {
                    if (response.ok) {
                        alert("Login realizado com sucesso.");
                    } else {
                        alert("Credenciais inválidas.");
                    }
                    return response.text();
                })
                .catch(error => console.error("Erro:", error));
        });
    }
});
