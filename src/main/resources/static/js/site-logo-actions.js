$(document).ready(function () {
    $('.logo-img').on('click', function () {
        document.location.href = '/'
    });

    $('.logo-img').on('mouseenter', function () {
        $('.logo-img').css('cursor', 'pointer');
    });
});
