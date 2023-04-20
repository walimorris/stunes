var slide = 1;

$(document).ready(function () {
    $('#dot-1').css('background-color', '#743089');
    setInterval(slideshow, 4000);
});

function slideshow() {
    if (slide === 3) {
        slide = 1;
    } else {
        slide = slide + 1;
    }
    switch (slide) {
        case 1:
            $('#slide-1').css('visibility', 'visible');
            $('#slide-2').css('visibility', 'hidden');
            $('#slide-3').css('visibility', 'hidden');

            // update respective dot
            $('#dot-1').css('background-color', '#743089');
            $('#dot-2').css('background-color', '#ddd');
            $('#dot-3').css('background-color', '#ddd');
            break;
        case 2:
            $('#slide-1').css('visibility', 'hidden');
            $('#slide-2').css('visibility', 'visible');
            $('#slide-3').css('visibility', 'hidden');

            // update respective dot
            $('#dot-1').css('background-color', '#ddd');
            $('#dot-2').css('background-color', '#743089');
            $('#dot-3').css('background-color', '#ddd');
            break;
        default:
            $('#slide-1').css('visibility', 'hidden');
            $('#slide-2').css('visibility', 'hidden');
            $('#slide-3').css('visibility', 'visible');

            // update respective dot
            $('#dot-1').css('background-color', '#ddd');
            $('#dot-2').css('background-color', '#ddd');
            $('#dot-3').css('background-color', '#743089');
    }
}
