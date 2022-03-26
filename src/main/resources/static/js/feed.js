"use strict"

let pageSize = 5;
let currentPage = 1;
let objects;

function clearResults() {
    while (itemlist.firstChild) {
        itemlist.firstChild.remove();
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
    objects.forEach(x => itemlist.appendChild(x));
}

function nextPage() {
    const nPages = Math.ceil(objects.length / pageSize);
    currentPage += 1;
        if (currentPage > nPages) {
            currentPage = 1;
        }
    loadPage();
}

function prevPage() {
    const nPages = Math.ceil(objects.length / pageSize);
    currentPage -= 1;
        if (currentPage < 1) {
            currentPage = nPages;
        }
    loadPage();
}

prev.addEventListener('click', prevPage);
next.addEventListener('click', nextPage);

loadPosts();
loadPage();

//Search --------------------
myQuery.addEventListener('input', ev => {

    for (const result of document.querySelectorAll('.hidden')) {
        result.classList.remove('hidden');
    }

    const filteredSections = objects.filter(section => {
        const name = section.childNodes[3].textContent.toUpperCase();
        return !name.includes(myQuery.value.toString().toUpperCase());
    });

    for (const section of filteredSections) {
        section.classList.add('hidden');
    }

    const nLength = Math.ceil(objects.length - filteredSections.length);

    nPages.textContent = Math.max(Math.ceil(nLength / pageSize), 1);
});