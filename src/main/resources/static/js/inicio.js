const anuncios = document.querySelectorAll('.w-50.d-flex.flex-column.align-items-center.border.border-info.rounded-5.mt-2');
anuncios.forEach(div => {div.addEventListener('click', function (){
    const link = this.querySelector('.d-none.ver-Anuncio');
        if(link){
            link.click();
        }
});
});