var mostPopularTrack;

$(document).ready(function () {
    // event listeners on employee slide component
    $('.most-popular-slide-show').on('mouseenter', function () {showNavigationButtons()});
    $('.most-popular-slide-show').on('mouseleave', function (){hideNavigationButtons()});

    collectMostPopularTracks();
    console.log(mostPopularTrack);
    populateTop10Tracks();
});

/**
 * Top ten most popular tracks are built and rendered on screen.
 * Renders four tracks at a single time and disables the others
 * until chosen for display.
 */
function populateTop10Tracks() {
    const slideshow = $('.most-popular-slide-show');
    let box;

    for (let i = 0; i < mostPopularTrack.length; i++) {
        if (i > 3) {
            box =
                `<div class="box" style="display: none">
                     <img src="/images/album-placeholder.jpeg" 
                          alt="track" 
                          th:src="@{/images/album-placeholder.jpeg}">
                     <h1>${mostPopularTrack[i].name}</h1>
                     <p>${mostPopularTrack[i].composer}</p>
                     <a href="#">Learn More ></a>
                 </div>`;
        } else {
            box =
                `<div class="box">
                     <img src="/images/album-placeholder.jpeg" 
                          alt="track" 
                          th:src="@{/images/album-placeholder.jpeg}">
                     <h1>${mostPopularTrack[i].name}</h1>
                     <p>${mostPopularTrack[i].composer}</p>
                     <a href="#">Learn More ></a>
                 </div>`;
        }
        slideshow.append(box);
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

/**
 * Calls internal spring boot api that collects top ten most popular tracks
 * from the stunes database.
 *
 */
function collectMostPopularTracks() {
    $.ajax({
        async: false,
        type: "GET",
        url: "/tracks/mostpopular",
        dataType: "json",
        success: function (result, status, xhr) {
            mostPopularTrack = result;
        },
        error: function (xhr, status, error) {
            console.log(`result=${status}, error=${error}`);
        }
    });
}
