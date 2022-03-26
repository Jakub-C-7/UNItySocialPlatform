"use strict"

let pageSize = 10;
let currentPage = 1;
let objects;

function clearResults() {
    while (listofgroupposts.firstChild) {
        listofgroupposts.firstChild.remove();
    }
}

function loadPosts(){
    const result = document.querySelectorAll('li');
    objects = Array.from(result) || [];
}

function loadPage() {
    clearResults();
    nPages.textContent = Math.ceil(objects.length / pageSize);
    const currentObject = objects.slice((currentPage - 1) * pageSize, currentPage * pageSize);
    insertPosts(currentObject);
    pageIndicator.textContent = currentPage;
}

function insertPosts(objects) {
    objects.forEach(x => listofgroupposts.appendChild(x));
}

function nextPage() {
    currentPage += 1;
    const nPages = Math.ceil(objects.length / pageSize);
    if (currentPage > nPages) {
        currentPage = 1;
    }
    loadPage();
}

function prevPage() {
    currentPage -= 1;
    const nPages = Math.ceil(objects.length / pageSize);
    if (currentPage < 1) {
        currentPage = nPages;
    }
    loadPage();
}

prev.addEventListener('click', prevPage);
next.addEventListener('click', nextPage);

loadPosts();
loadPage();