// Función para previsualizar imágenes y añadir una nueva caja si es necesario
function previewImage(input) {
    if (input.files && input.files[0]) {
        const reader = new FileReader();
        reader.onload = function (e) {
            const parent = input.closest('.fotoProductoInsert');

            // Crear una etiqueta <img> y asignarle la imagen cargada
            const img = document.createElement('img');
            img.src = e.target.result;
            parent.innerHTML = '';  // Limpiar el contenido previo (si lo hay)
            parent.appendChild(img);
            parent.classList.add('has-image');

            // Verificar y agregar una nueva caja si todas las actuales están llenas
            const fotoContainer = document.getElementById('fotoContainer');
            const allBoxes = fotoContainer.querySelectorAll('.fotoProductoInsert');
            const filledBoxes = fotoContainer.querySelectorAll('.has-image');

            if (filledBoxes.length === allBoxes.length) {
                addNewBox(fotoContainer);
            }
        };
        reader.readAsDataURL(input.files[0]);
    }
}

// Función para añadir una nueva caja
function addNewBox(container) {
    const newBox = document.createElement('div');
    newBox.className = 'fotoProductoInsert';
    newBox.onclick = () => triggerFileInput(newBox);

    const newInput = document.createElement('input');
    newInput.type = 'file';
    newInput.className = 'file-input';
    newInput.accept = 'image/jpeg, image/png, image/gif, image/webp';
    newInput.onchange = () => previewImage(newInput);

    newBox.appendChild(newInput);
    container.appendChild(newBox);
}

// Función para activar el input de archivo al hacer clic en una caja
function triggerFileInput(element) {
    const input = element.querySelector('.file-input');
    input.click();
}
