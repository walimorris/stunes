$(document).ready(function () {
    activatePaginationLink();
})

function activatePaginationLink() {
    const url = window.location.href;
    const params = url.split('?')[1];

    if (!params.includes('page')) {
        $('#link-0').css('background-color', '#ddd');
    } else {
        const digit1 = parseInt(params.split('page')[1].charAt(1));
        let digit2 = -1;
        if (digit1 >= 1 && checkDigit(params.split('page')[1].charAt(2))) {
            digit2 = parseInt(params.split('page')[1].charAt(2));
        }
        if (digit2 > -1) {
            const page = `${digit1.toString()}${digit2.toString()}`;
            const pageNumber = parseInt(page);
            $(`#link-${pageNumber.toString()}`).css('background-color', '#ddd');
        } else {
            $(`#link-${digit1.toString()}`).css('background-color', '#ddd');
        }
    }
}

function checkDigit(x) {
    return x >= '0' && x <= '9';
}

