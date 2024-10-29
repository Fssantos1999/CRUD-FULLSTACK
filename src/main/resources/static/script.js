document.addEventListener("DOMContentLoaded", function() {
    const registerForm = document.getElementById("registerForm");
    const loginForm = document.getElementById("loginForm");

    if (registerForm) {
        registerForm.addEventListener("submit", function(event) {
            event.preventDefault();
            const data = {
                username: document.getElementById("username").value,
                email: document.getElementById("email").value,
                password: document.getElementById("password").value
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
                username: document.getElementById("loginUsername").value,
                password: document.getElementById("loginPassword").value
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
