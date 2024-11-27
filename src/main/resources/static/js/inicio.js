const anuncios = document.querySelectorAll('.col-10.col-md-3.col-lg-3.m-1.p-1');
anuncios.forEach(div => {div.addEventListener('click', function (){
    const link = this.querySelector('.d-none.ver-Anuncio');
        if(link){
            link.click();
        }
});
});
