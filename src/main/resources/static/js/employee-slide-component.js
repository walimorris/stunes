const toggle = document.getElementById('toggle');
addEventListener('click', (e) => {
    document.querySelector('#slideSet').classList.toggle('paused');
    console.log('clicked')
});
