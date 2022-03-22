"use strict"

let pageSize = 5;
let currentPage = 1;
let userRepos;

function clearResults() {
    while (listofposts.firstChild) {
        listofposts.firstChild.remove();
    }
}

function loadPosts(){
    const result = document.querySelectorAll('li');
    userRepos = Array.from(result) || [];
}

function loadPage() {
    clearResults();
    nPages.textContent = Math.ceil(userRepos.length / pageSize);
    const currentObject = userRepos.slice((currentPage - 1) * pageSize, currentPage * pageSize);
    insertPosts(currentObject);
    pageIndicator.textContent = currentPage;
}

function insertPosts(objects) {
    objects.forEach(x => listofposts.appendChild(x));
}

function nextPage() {
    currentPage += 1;
    const nPages = Math.ceil(userRepos.length / pageSize);
    if (currentPage > nPages) {
        currentPage = 1;
    }
    loadPage();
}

function prevPage() {
    currentPage -= 1;
    const nPages = Math.ceil(userRepos.length / pageSize);
    if (currentPage < 1) {
        currentPage = nPages;
    }
    loadPage();
}

prev.addEventListener('click', prevPage);
next.addEventListener('click', nextPage);

loadPosts();
loadPage();
