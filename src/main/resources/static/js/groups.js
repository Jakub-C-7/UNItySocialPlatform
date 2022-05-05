"use strict"

let pageSize = 5;
let currentPage = 1;
let objects;
let filteredObjects;

function clearResults() {
    while (listofgroups.firstChild) {
        listofgroups.firstChild.remove();
    }
}

function loadPosts(){
    const result = document.querySelectorAll('.aGroup');
    objects = Array.from(result) || [];
}

function loadPage() {
    clearResults();
    nPages.textContent = Math.max(1, Math.ceil(objects.length / pageSize));
    const currentObject = objects.slice((currentPage - 1) * pageSize, currentPage * pageSize);
    insertPosts(currentObject);
    pageIndicator.textContent = currentPage;
}

function insertPosts(objects) {
    objects.forEach(x => listofgroups.appendChild(x));
}

function nextPage() {
    if (filteredObjects != null){
        const nPages = Math.ceil(filteredObjects.length / pageSize);
        currentPage += 1;
        if (currentPage > nPages) {
            currentPage = 1;
        }
        loadFilteredPage();
    } else {
        const nPages = Math.ceil(objects.length / pageSize);
        currentPage += 1;
        if (currentPage > nPages) {
            currentPage = 1;
        }
        loadPage();
    }
}

function prevPage() {
    if (filteredObjects != null){
        const nPages = Math.ceil(filteredObjects.length / pageSize);
        currentPage -= 1;
        if (currentPage < 1) {
            currentPage = nPages;
        }
        loadFilteredPage();
    }
    else {
        const nPages = Math.ceil(objects.length / pageSize);
        currentPage -= 1;
        if (currentPage < 1) {
            currentPage = nPages;
        }
        loadPage();
    }
}

prev.addEventListener('click', prevPage);
next.addEventListener('click', nextPage);

loadPosts();
loadPage();

//Search --------------------
myQuery.addEventListener('input', ev => {
    filteredObjects = null

    for (const result of document.querySelectorAll('.hidden')) {
        result.classList.remove('hidden');
    }

    const filteredSections = objects.filter(section => {
        const name = section.childNodes[1].textContent.toUpperCase();
        return !name.includes(myQuery.value.toString().toUpperCase());
    });

    filteredObjects = objects.filter(section => {
        const name = section.childNodes[1].textContent.toUpperCase();
        return name.includes(myQuery.value.toString().toUpperCase());
    });

    for (const section of filteredSections) {
        section.classList.add('hidden');
    }

    loadFilteredPage();
});

function loadFilteredPage() {
    clearResults();
    nPages.textContent = Math.max(Math.ceil(filteredObjects.length / pageSize), 1);
    const currentObject = filteredObjects.slice((currentPage - 1) * pageSize, currentPage * pageSize);
    insertPosts(currentObject);
    pageIndicator.textContent = currentPage;
}