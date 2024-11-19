document.getElementById("nuevaFoto").addEventListener("click", function () {
    document.getElementById("nuevoArchivo").click();
} );

document.getElementById("nuevoArchivo").addEventListener("change", function () {
    document.getElementById("formulario-add-foto").submit();
});
