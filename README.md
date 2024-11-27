Para que funcione hace fata una base de datos: wallapop
Puerta trasera para cargar datos: /cargar

Desarrollo Web en Entorno Servidor
2o Curso CFGS Desarrollo de Aplicaciones Web
I.E.S. Juan Bosco
Alcázar de San Juan
1o PRÁCTICA. WEB DE COMPRA-VENTA WALLAPOP
-
-
-
-
-
-
Realiza una aplicación web similar a Wallapop utilizando Java Spring boot, Thymeleaf y
MySQL.
Página inicial: (“/”) Al entrar en la página se mostrará un listado con todos los
anuncios ordenados por la fecha de creación de forma descendente. De cada artículo
se mostrará una foto (la primera que se insertó), el nombre de artículo y el precio.
Además, en la parte baja de la página se mostrará el número total de artículos. Al
pinchar sobre un artículo se cargará la web /anuncios/ver/{id} . (1,5 puntos)
Menú (a la izquierda o debajo de la cabecera) con las opciones Anuncios (donde salen
todos los anuncios), mis anuncios (donde salen solo mis anuncios) y un apartado de
Inicio de sesión donde (0,5 puntos):
o Si no se ha iniciado sesión se mostrará un formulario de login con los “input”
email/password un botón de Login y un enlace al registro.
o Si ya se ha iniciado sesión, se sustituirá el formulario por el nombre del usuario
conectado y un enlace a cerrar sesión.
Registro de usuarios (1 punto):
o Datos: email, password, nombre, teléfono, población
o Validar email y que no esté no repetido (poner unique en la BD)
o Validar que el password tenga al menos 4 caracteres
o Resto de campos no obligatorios.
o Mostrar mensajes de error junto a los campos. Los mensajes se guardarán en
messages.properties
Implementar Login y logout con recordar usuario (1 punto):
o Prohibir el acceso a páginas privadas si no estás identificado (insertar, editar,
borrar y mis anuncios).
o Guardar una cookie durante 5 días para que nos identifiquemos de forma
automática.
Anuncios:
o Insertar anuncio: Precio, título, descripción (2 puntos)
▪ Validar campos obligatorios (título y precio) y mostrar mensajes al
lado de los input.
▪ Permitir insertar varias fotos en cada anuncio. Validar en el servidor
que sean imágenes sino indicarlo con un mensaje. Redimensionarlas a
1000px de ancho (y su correspondiente alto para mantener la relación
de aspecto) antes de guardarlas en el disco.
o Ver anuncio (1 punto) al pinchar sobre el anuncio se muestran todos sus datos
y los datos del usuario. Además se mostrarán todas las fotos con algún tipo de
carrusel.
o Borrar anuncios y sus fotos (1 punto)
▪ Borrar anuncio, sus fotos y los archivos del disco.
o Editar anuncios: Se podrá modificar el título, la descripción, borrar fotos e
insertar nuevas. (1,5 puntos)Desarrollo Web en Entorno Servidor
2o Curso CFGS Desarrollo de Aplicaciones Web
I.E.S. Juan Bosco
Alcázar de San Juan
o
-
-
Mis anuncios: Se mostrará un listado con los anuncios del usuario conectado
con enlaces en cada uno para borrar y editar. (0,5 puntos)
Diseño y funcionalidad general:
o Cuidar el aspecto estético y la usabilidad de la aplicación. Todas las vistas
deben seguir el mismo diseño. Se recomienda usar Bootstrap. No poner
validaciones en el lado del cliente para poder probar las del lado del servidor.
Se pueden usar “fragmentos” de thymeleaf.
o Comprobar que no salen excepciones de ningún tipo aunque metamos mal los
parámetros en la URL, por ejemplo /anuncios/borrar/100 cuando el 100 no
exista o /anuncios/borrar/aaa.
o Mostrar mensajes de error y de confirmación al insertar, borrar, si intentamos
borrar anuncios que no son nuestros, etc.
Seguridad: Asegurarse que cuando un usuario borra o edita un anuncio es el
propietario del mismo.
Opcionales (Si se hace en parejas hay que hacer obligatoriamente al menos uno de ellos):
-
-
-
-
-
Añade una entidad Categoría que estará relacionada N:M con Anuncio. Al insertar un
anuncio se seleccionará una o varias categorías para cada anuncio (mediante un select
multiple, un checkbox para cada categoría o un componente externo como
jQueryflexdatalist) . Luego en el listado se podrán filtrar los artículos por categoría.
Añadir un contador de visitas para cada anuncio. Al visualizar el anuncio se mostrará el
número de visitas que ha recibido. Cada vez que se cargue la página ver_anuncio de un
anuncio se añadirá una nueva Visita sólo si el usuario que la está visitando está
“logueado”. Cuando ese mismo usuario vuelva a visitar el anuncio no se contará de
nuevo.
Dentro de cada anuncio, y si estamos correctamente identificados, podremos enviar
mensajes al propietario del anuncio y él podrá contestarnos (no hace falta que se
recarguen en tiempo real, es suficiente con que aparezcan al recargar la página), al
estilo de Wallapop.
Paginación: Los anuncios se paginarán de forma que aparezcan de 5 en 5 y con
botones para navegar entre las páginas.
Añadir una opción para buscar anuncios. Introduciendo una o varias palabras, se
mostrarán los anuncios en los que el título o el texto contengan al menos una de las
palabras indicadas.
Resultados de aprendizaje evaluados con esta actividad: RA3, RA4 y RA5
Desarrollo Web en Entorno Servidor
2o Curso CFGS Desarrollo de Aplicaciones Web
I.E.S. Juan Bosco
Alcázar de San Juan
1o PRÁCTICA. WEB DE COMPRA-VENTA WALLAPOP
-
-
-
-
-
-
Realiza una aplicación web similar a Wallapop utilizando Java Spring boot, Thymeleaf y
MySQL.
Página inicial: (“/”) Al entrar en la página se mostrará un listado con todos los
anuncios ordenados por la fecha de creación de forma descendente. De cada artículo
se mostrará una foto (la primera que se insertó), el nombre de artículo y el precio.
Además, en la parte baja de la página se mostrará el número total de artículos. Al
pinchar sobre un artículo se cargará la web /anuncios/ver/{id} . (1,5 puntos)
Menú (a la izquierda o debajo de la cabecera) con las opciones Anuncios (donde salen
todos los anuncios), mis anuncios (donde salen solo mis anuncios) y un apartado de
Inicio de sesión donde (0,5 puntos):
o Si no se ha iniciado sesión se mostrará un formulario de login con los “input”
email/password un botón de Login y un enlace al registro.
o Si ya se ha iniciado sesión, se sustituirá el formulario por el nombre del usuario
conectado y un enlace a cerrar sesión.
Registro de usuarios (1 punto):
o Datos: email, password, nombre, teléfono, población
o Validar email y que no esté no repetido (poner unique en la BD)
o Validar que el password tenga al menos 4 caracteres
o Resto de campos no obligatorios.
o Mostrar mensajes de error junto a los campos. Los mensajes se guardarán en
messages.properties
Implementar Login y logout con recordar usuario (1 punto):
o Prohibir el acceso a páginas privadas si no estás identificado (insertar, editar,
borrar y mis anuncios).
o Guardar una cookie durante 5 días para que nos identifiquemos de forma
automática.
Anuncios:
o Insertar anuncio: Precio, título, descripción (2 puntos)
▪ Validar campos obligatorios (título y precio) y mostrar mensajes al
lado de los input.
▪ Permitir insertar varias fotos en cada anuncio. Validar en el servidor
que sean imágenes sino indicarlo con un mensaje. Redimensionarlas a
1000px de ancho (y su correspondiente alto para mantener la relación
de aspecto) antes de guardarlas en el disco.
o Ver anuncio (1 punto) al pinchar sobre el anuncio se muestran todos sus datos
y los datos del usuario. Además se mostrarán todas las fotos con algún tipo de
carrusel.
o Borrar anuncios y sus fotos (1 punto)
▪ Borrar anuncio, sus fotos y los archivos del disco.
o Editar anuncios: Se podrá modificar el título, la descripción, borrar fotos e
insertar nuevas. (1,5 puntos)Desarrollo Web en Entorno Servidor
2o Curso CFGS Desarrollo de Aplicaciones Web
I.E.S. Juan Bosco
Alcázar de San Juan
o
-
-
Mis anuncios: Se mostrará un listado con los anuncios del usuario conectado
con enlaces en cada uno para borrar y editar. (0,5 puntos)
Diseño y funcionalidad general:
o Cuidar el aspecto estético y la usabilidad de la aplicación. Todas las vistas
deben seguir el mismo diseño. Se recomienda usar Bootstrap. No poner
validaciones en el lado del cliente para poder probar las del lado del servidor.
Se pueden usar “fragmentos” de thymeleaf.
o Comprobar que no salen excepciones de ningún tipo aunque metamos mal los
parámetros en la URL, por ejemplo /anuncios/borrar/100 cuando el 100 no
exista o /anuncios/borrar/aaa.
o Mostrar mensajes de error y de confirmación al insertar, borrar, si intentamos
borrar anuncios que no son nuestros, etc.
Seguridad: Asegurarse que cuando un usuario borra o edita un anuncio es el
propietario del mismo.
Opcionales (Si se hace en parejas hay que hacer obligatoriamente al menos uno de ellos):
-
-
-
-
-
Añade una entidad Categoría que estará relacionada N:M con Anuncio. Al insertar un
anuncio se seleccionará una o varias categorías para cada anuncio (mediante un select
multiple, un checkbox para cada categoría o un componente externo como
jQueryflexdatalist) . Luego en el listado se podrán filtrar los artículos por categoría.
Añadir un contador de visitas para cada anuncio. Al visualizar el anuncio se mostrará el
número de visitas que ha recibido. Cada vez que se cargue la página ver_anuncio de un
anuncio se añadirá una nueva Visita sólo si el usuario que la está visitando está
“logueado”. Cuando ese mismo usuario vuelva a visitar el anuncio no se contará de
nuevo.
Dentro de cada anuncio, y si estamos correctamente identificados, podremos enviar
mensajes al propietario del anuncio y él podrá contestarnos (no hace falta que se
recarguen en tiempo real, es suficiente con que aparezcan al recargar la página), al
estilo de Wallapop.
Paginación: Los anuncios se paginarán de forma que aparezcan de 5 en 5 y con
botones para navegar entre las páginas.
Añadir una opción para buscar anuncios. Introduciendo una o varias palabras, se
mostrarán los anuncios en los que el título o el texto contengan al menos una de las
palabras indicadas.
Resultados de aprendizaje evaluados con esta actividad: RA3, RA4 y RA5
