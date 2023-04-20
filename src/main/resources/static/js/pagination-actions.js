const url = window.location.href;
const params = url.split('?')[1];

if (!params.includes('page')) {
    const link = document.getElementById(`link-0`);
    link.style.backgroundColor = '#ddd';
} else {
    const digit1 = parseInt(params.split('page')[1].charAt(1));
    let digit2 = -1;
    if (digit1 >= 1 && checkDigit(params.split('page')[1].charAt(2))) {
        digit2 = parseInt(params.split('page')[1].charAt(2));
    }

    if (digit2 > -1) {
        const page = `${digit1.toString()}${digit2.toString()}`;
        console.log(page);
        const pageNumber = parseInt(page);
        const link = document.getElementById(`link-${pageNumber.toString()}`);
        link.style.backgroundColor = '#ddd';
    } else {
        const link = document.getElementById(`link-${digit1.toString()}`);
        link.style.backgroundColor = '#ddd';
    }
}

function checkDigit(x) {
    return x >= '0' && x <= '9';
}

