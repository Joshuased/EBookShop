let editMode = false;

function toggleEditMode() {
  editMode = !editMode;
  loadBooks();
}

function isValidImageUrl(url) {
  return new Promise((resolve) => {
    const img = new Image();
    img.onload = () => resolve(true);
    img.onerror = () => resolve(false);
    img.src = url;
  });
}

async function addImage(id) {
  const addImageUrl = prompt("Enter imageUrl:");
  if (!addImageUrl) return;

  const isValid = await isValidImageUrl(addImageUrl);
  if (!isValid) {
    alert("That URL doesn't point to a valid image.");
    return;
  }

  fetch(`/books/${id}`)
    .then(res => res.json())
    .then(book => {
      book.imageUrl = addImageUrl;

      return fetch(`/books/${id}`, {
        method: 'PUT',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(book)
      });
    })
    .then(() => {
      loadBooks();
    })
    .catch(err => {
      alert("Failed to update image: " + err.message);
    });
}

function updateBook(id) {
  const newPrice = prompt("Enter new price:");
  const newQty = prompt("Enter new quantity:");
  const newImageUrl = prompt("Enter new imageUrl:");

  fetch(`/books/${id}?price=${newPrice}&qty=${newQty}&imageUrl=${newImageUrl}`, {
    method: 'PUT'
  }).then(() => {
    alert("Book updated!");
    loadBooks();
  });
}

function deleteBook(id) {
  fetch(`/books/${id}`, {
    method: 'DELETE'
  }).then(() => loadBooks());
}

function loadBooks() {
  fetch('/books')
    .then(res => res.json())
    .then(data => {
      if (!Array.isArray(data)) {
        throw new Error("Expected an array but got: " + JSON.stringify(data));
      }
      const list = document.getElementById('bookList');
      list.innerHTML = '';
      data.forEach(b => {
        list.innerHTML += `
          <div class="book-card">
            ${b.imageUrl ? `
              <img src="${b.imageUrl}"/>`
            : `<div class="placeholder-cover"><button onclick="addImage(${b.id})">Add Image</button></div>`}
            <h3 style="font-size: 16px;">${b.title}</h3>
            <p><strong>Author:</strong> ${b.author}</p>
            <p><strong>Price:</strong> $${b.price}</p>
            <p><strong>Quantity:</strong> ${b.qty}</p>
            <p>ID: ${b.id}</p>
            ${editMode ? `<button onclick="updateBook(${b.id})">Edit</button>` : ""}
            ${editMode ? `<button onclick="deleteBook(${b.id})" style="background-color: red;">Delete</button>` : ""}
          </div>
        `;
      });
    });
}

async function addBook() {
  const addId = prompt("Enter book ID:");
  const addTitle = prompt("Enter book title:");
  const addAuthor = prompt("Enter book Author:");
  const addPrice = prompt("Enter book price:");
  const addQ = prompt("Enter book quantity:");
  const addUrl = prompt("Enter book cover url")
  
  const book = {
    id: parseInt(addId),
    title: addTitle.trim(),
    author: addAuthor.trim(),
    price: parseFloat(addPrice),
    qty: parseInt(addQ),
    imageUrl: addUrl.trim()
  };

  const isVal = await isValidImageUrl(addUrl);
  if (!isVal) {
    alert("That URL doesn't point to a valid image.")
    book.imageUrl = "";
  }

  if (isNaN(book.id) || !book.title || !book.author || isNaN(book.price) || isNaN(book.qty)) {
    alert("Please fill in all fields with the correct data types.");
    return;
  }

  fetch('/books', {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify(book)
  })
    .then(response => {
      if (!response.ok) {
        throw new Error("Failed to add book. Check backend validation");
      }
      return response.json();
    })
    .then(() => {
      alert("Book added!");
      loadBooks();
    })
    .catch(err => {
      alert("Error: " + err.message);
    });
}

function loadSorted(type) {
  fetch(`/books/sort/${type}`)
    .then(res => res.json())
    .then(data => {
      if (!Array.isArray(data)) {
        throw new Error("Expected an array but got: " + JSON.stringify(data));
      }
      const list = document.getElementById('bookList');
      list.innerHTML = '';
      data.forEach(b => {
        list.innerHTML += `
          <div class="book-card">
            ${b.imageUrl ? `
              <img src="${b.imageUrl}" style="width: 100%; height: 120px; object-fit: cover; border-radius: 5px; margin-bottom: 8px;"
                   onerror="this.outerHTML='<div class=\\'placeholder-cover\\'><button onclick=\\'addImage(${b.id})\\'>Add Image</button></div>'"/>`
            : `<div class="placeholder-cover"><button onclick="addImage(${b.id})">Add Image</button></div>`}
            <h3>${b.title}</h3>
            <p><strong>Author:</strong> ${b.author}</p>
            <p><strong>Price:</strong> $${b.price}</p>
            <p><strong>Quantity:</strong> ${b.qty}</p>
            <p>ID: ${b.id}</p>
            ${editMode ? `<button onclick="updateBook(${b.id})">Edit</button>` : ""}
            ${editMode ? `<button onclick="deleteBook(${b.id})" style="background-color: red;">Delete</button>` : ""}
          </div>
        `;
      });
    });
}
