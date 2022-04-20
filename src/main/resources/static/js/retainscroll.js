"use strict"

document.addEventListener("DOMContentLoaded", function(event) {
    let sliceat = window.location.href.lastIndexOf("?");
    let storageName = window.location.href.slice(0, sliceat);
    let scrollpos;

    if (sliceat === -1) scrollpos = sessionStorage.getItem(window.location.href);
    else scrollpos = sessionStorage.getItem(storageName);

    if (scrollpos) window.scrollTo(0, scrollpos);
});

window.onbeforeunload = function(e) {
    let sliceat = window.location.href.lastIndexOf("?");
    let storageName = window.location.href.slice(0, sliceat);
    if (sliceat === -1) sessionStorage.setItem(window.location.href, window.scrollY);
    else sessionStorage.setItem(storageName, window.scrollY);
};
