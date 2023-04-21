var slide = 1;

$(document).ready(function () {
    // on ready, begin with slide 1
    $('#dot-1').css('background-color', '#743089');
    setInterval(slideshow, 4000);

    // event listeners on slide dots
    $('#dot-1').on('click', function () {updateSlide(1);}); // user clicks slide 1 dot
    $('#dot-2').on('click', function () {updateSlide(2);}); // user clicks slide 2 dot
    $('#dot-3').on('click', function () {updateSlide(3);}); // user clicks slide 3 dot

    // event listeners on next & prev buttons
    $('#prev-button').on('click', function () {updateSlide(slide - 1);}); // user clicks prev, subtract 1
    $('#next-button').on('click', function () {updateSlide(slide + 1);}); // user clicks next, add 1

    // event listeners on employee slide component
    $('#slideSet').on('mouseenter', function () {showNavigationButtons()});
    $('#slideSet').on('mouseleave', function (){hideNavigationButtons()});
});

/**
 * Used in conjunction with setInterval to create a slideshow animation
 * functionality on the employee-slide component. Updates a global var
 * 'slide' to iterate through elements.
 */
function slideshow() {
    if (slide >= 3) {
        slide = 1;
    } else {
        slide = slide + 1;
    }
    updateSlide(slide);
}

/**
 * Updates the slide element's and respective slide dot element's visibility
 * attribute based on the supplied slide.
 *
 * @param n slide number to navigate to
 */
function updateSlide(n) {
    slide = n;
    switch (n) {
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

/**
 * Hide navigation buttons by changing visibility property and fadeOut
 * animation property keyframes.
 */
function hideNavigationButtons() {
    $('#prev-button').css('visibility', 'hidden');
    $('#next-button').css('visibility', 'hidden');
    $('#prev-button').css('animation', '1s fadeOut');
    $('#next-button').css('animation', '1s fadeOut');

}

/**
 * Show navigation buttons by changing visibility property and fadeIn
 * animation property keyframes.
 */
function showNavigationButtons() {
    $('#prev-button').css('visibility', 'visible');
    $('#next-button').css('visibility', 'visible');
    $('#prev-button').css('animation', '1s fadeIn');
    $('#next-button').css('animation', '1s fadeIn');
}
