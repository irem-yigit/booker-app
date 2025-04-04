const API_URL = 'http://localhost:8080/api/books';

function fetchBooks() {
    fetch(API_URL)
        .then(response => response.json())
        .then(books => {
            const bookList = document.getElementById('book-list');
            bookList.innerHTML = '';
            books.forEach(book => {
                const div = document.createElement('div');
                div.className = 'book-item';
                div.textContent = book.title;
                bookList.appendChild(div);
            });
        })
        .catch(error => console.error('Error fetching books:', error));
}

function addBook() {
    const title = document.getElementById('book-title').value;
    if (!title) return;
    fetch(API_URL, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ title })
    })
    .then(() => {
        document.getElementById('book-title').value = '';
        fetchBooks();
    })
    .catch(error => console.error('Error adding book:', error));
}

fetchBooks();