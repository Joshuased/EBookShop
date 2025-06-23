function register() {
    const username = document.getElementById("regUsr").value.trim();
    const password = document.getElementById("regPass").value.trim();

    if (!username || !password) {
        alert("Username and password are required!");
        return;
    }

    fetch('users', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ username, password })
    })
    .then(async response => {
        if (!response.ok) {
            const errorText = await response.text();
            throw new Error(errorText || "Registration failed");
        }
        return response.json(); // assuming Spring returns the created user object
    })
    .then(data => {
        alert("Registration successful!");
        window.location.href = 'login.html';
    })
    .catch(err => {
        alert("Error: " + err.message);
        console.error("Detailed error:", err);
    });
}