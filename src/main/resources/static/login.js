function login(){
    const username = document.getElementById("username").value.trim();
    const password = document.getElementById("password").value.trim();


    console.log("Values gotten.");

    fetch("/users/login", {
        method: 'POST',
        headers: {'Content-Type': 'application/json' },
        body: JSON.stringify({ username, password })
    })
    .then(async response => {        
        const data = await response.json();

        if (!response.ok) {
            throw new Error(data.message || "Login failed");
        }

        return data;
    })
    .then(data => {
        alert(data.message);
        window.location.href = 'main.html';
    })
    .catch(err => {
        console.log("Error text response:", err);
        alert("Error: " + err.message);
        console.error("Detailed error:", err);
    });
}