const anuncios = document.querySelectorAll('.col-10.col-md-5.col-lg-5.m-1');
anuncios.forEach(div => {div.addEventListener('click', function (){
    const link = this.querySelector('.d-none.ver-Anuncio');
        if(link){
            link.click();
        }
});
});