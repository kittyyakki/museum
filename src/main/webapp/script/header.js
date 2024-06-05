document.addEventListener('DOMContentLoaded', () => {
    const link1 = document.querySelector('.header_gnb > a[href="museum.do?command=artworkList"]');
    const container = document.querySelector('.header_gnb_list_container');

    if (link1 && container) {
        link1.addEventListener('mouseover', () => {
            container.style.display = 'block';
        });

        link1.addEventListener('mouseout', () => {
            container.style.display = 'none';
        });

        container.addEventListener('mouseover', () => {
            container.style.display = 'block';
        });

        container.addEventListener('mouseout', () => {
            container.style.display = 'none';
        });
    } else {
        console.error('Required elements not found');
    }
});
