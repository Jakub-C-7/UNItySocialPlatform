"use strict"

$(document).ready(function () {
    setInterval(reloadMessages, 10000);

    function reloadMessages() {
        $.ajax({
            url: window.location.href,
            type: 'GET',
            success: function (data) {
                // document.querySelector("main").innerHTML = data;
                document.location.reload();
            }
        });
    }
});

document.addEventListener("DOMContentLoaded", function(event) {
    let chatId = window.location.href;
    let chatscrollpos = sessionStorage.getItem('chatscrollpos' + chatId);
    if (chatscrollpos) $('#chatWrapper').scrollTop(chatscrollpos);
});

window.onbeforeunload = function(e) {
    let chatId = window.location.href;
    sessionStorage.setItem('chatscrollpos' + chatId, $('#chatWrapper').scrollTop());
};
