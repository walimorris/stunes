var mostPopularTrack;

$(document).ready(function () {
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
    console.log("ajax has been called");
    console.log(mostPopularTrack);
    populateTop10Tracks();
});

function populateTop10Tracks() {
    const slideshow = $('.most-popular-slide-show');

    for (let i = 0; i < mostPopularTrack.length; i++) {
        const box = `
            <div class="box">
            <img src="../static/images/album-placeholder.jpeg" alt="track" th:src="@{/images/album-placeholder.jpeg}">
            <h1>${mostPopularTrack[i].name}</h1>
            <p>${mostPopularTrack[i].composer}</p>
            <a href="#">Learn More ></a>
        </div>
        `;
        slideshow.append(box);
    }
}


