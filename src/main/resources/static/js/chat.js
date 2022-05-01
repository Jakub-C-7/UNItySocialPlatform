"use strict"

const form = document.getElementById('createMessageWrapper');

function clearChatBox() {
    window.onbeforeunload = function(e) {
        let chatId = window.location.href;
        sessionStorage.setItem('chatscrollpos' + chatId, $('#chatWrapper').scrollTop());
        sessionStorage.removeItem("chatmessage" + window.location.href);
    };
}

if (form.addEventListener){
    form.addEventListener("submit", clearChatBox, false);
}

$(document).ready(function () {
    setInterval(reloadMessages, 10000);

    function reloadMessages() {
        $.ajax({
            url: window.location.href,
            type: 'GET',
            success: function () {
                document.location.reload();
            }
        });
    }
});

document.addEventListener("DOMContentLoaded", function(event) {
    let chatId = window.location.href;
    let chatscrollpos = sessionStorage.getItem('chatscrollpos' + chatId);
    if (chatscrollpos) $('#chatWrapper').scrollTop(chatscrollpos);

    let chatmessage = sessionStorage.getItem('chatmessage' + chatId);
    if (chatmessage) $('#messageContent').val(chatmessage);
});

window.onbeforeunload = function(e) {
    let chatId = window.location.href;
    sessionStorage.setItem('chatscrollpos' + chatId, $('#chatWrapper').scrollTop());
    sessionStorage.setItem('chatmessage' + chatId, $('#messageContent').val());
};
