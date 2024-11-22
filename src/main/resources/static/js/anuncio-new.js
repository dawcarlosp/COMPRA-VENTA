document.addEventListener("DOMContentLoaded", () => {
    function triggerFileInput(div) {
        const fileInput = div.querySelector(".file-input");
        fileInput.click();
    }

    function previewImage(input) {
        const div = input.parentElement;
        const file = input.files[0];

        if (file) {
            const reader = new FileReader();
            reader.onload = (e) => {
                const img = document.createElement("img");
                img.src = e.target.result;
                div.innerHTML = ""; // Limpia el contenido
                div.appendChild(img); // Agrega la imagen
                div.appendChild(input); // Agrega de nuevo el input
            };
            reader.readAsDataURL(file);
        }
    }

    document.querySelectorAll(".fotoProductoInsert").forEach((div) => {
        div.addEventListener("click", () => triggerFileInput(div));
        const input = div.querySelector(".file-input");
        input.addEventListener("change", () => previewImage(input));
    });
});
