# Documentación del Proyecto: Juego UTN (Greenfoot)

Esta documentación describe la estructura, clases, atributos y métodos del juego desarrollado en Greenfoot. El juego simula a un estudiante de segundo año de Ingeniería en Sistemas que debe "atacar" materias para ganar horas de estudio y aprobar. 

Este archivo está formateado en Markdown, por lo que puedes copiar y pegar todo este texto directamente en una celda de texto de Google Colab y se verá con formatos de títulos, listas y negritas automáticamente.

---

## 🌍 Mundos (Worlds)

### `Aula` (Hereda de `World`)
Es el mundo principal donde se desarrolla el juego. Maneja los diferentes niveles y la interfaz de usuario (HUD).

**Atributos:**
- `vida` (`Vida`): Instancia de la barra de vida del jugador para mostrar en pantalla.
- `alumno` (`Alumno`): Instancia del jugador principal, inicializado con velocidad 2, vida 100 y fuerza 5.
- `contador` (`Contador`): Instancia del contador de "Horas de estudio" en pantalla.
- `posicion` (`int`): Variable de control (actualmente no utilizada activamente).

**Métodos:**
- `Aula()`: Constructor. Inicializa el mundo (600x400), agrega el contador, la barra de vida y llama a `nivel1()`.
- `nivel1()` a `nivel8()`: Métodos destinados a cargar la configuración de cada nivel. Actualmente, `nivel1()` agrega al alumno y a un `Profesor` (Mario) con un diálogo introductorio.

---

## 🎭 Actores (Actors)

### `Alumno` (Hereda de `Actor`)
Es el personaje principal controlado por el jugador.

**Atributos:**
- `velocidad` (`int`): Cantidad de píxeles que se mueve por fotograma.
- `vida` (`int`): Puntos de salud actuales del alumno (Máximo 100).
- `barraVida` (`Vida`): Referencia a la barra de vida de la interfaz para actualizarla al recibir daño.
- `fuerza` (`int`): Capacidad de daño del alumno.

**Métodos:**
- `Alumno(...)`: Constructor que inicializa los atributos del jugador.
- `movimiento()`: Detecta las teclas `W`, `A`, `S`, `D` y actualiza la posición (`setLocation`) según la `velocidad`.
- `cambiarArma()`: Detecta la tecla `E` (lógica pendiente de implementar).
- `recibirDano(int decremento)`: Resta el decremento a la `vida`, reproduce un sonido (`desaprobado.mp3`) y actualiza el sprite de la `barraVida` progresivamente cuando la vida baja de 100, 80, 60, 40 y llega a 0.
- `act()`: Se ejecuta en cada fotograma, llamando a `movimiento()`.

---

### `Profesor` (Hereda de `Actor`)
Un NPC (Non-Playable Character) con el que el jugador puede interactuar para leer diálogos.

**Atributos:**
- `contenido` (`String[]`): Arreglo de frases que el profesor dirá.
- `dialogo` (`Dialogo`): Instancia de la caja de texto asociada a este profesor.

**Métodos:**
- `Profesor(String[] contenido)`: Constructor que recibe el arreglo de textos.
- `hablar()`: Verifica si está tocando al `Alumno` (`isTouching`). Si lo toca, instancia y muestra el `Dialogo`. Si deja de tocarlo, elimina el cuadro de diálogo de la pantalla.
- `act()`: Ejecuta continuamente la función `hablar()`.

---

### `Dialogo` (Hereda de `Actor`)
Gestiona la caja gráfica del diálogo y el avance de los textos.

**Atributos:**
- `dialogo` (`String[]`): Arreglo con las frases a mostrar.
- `texto` (`Texto`): Objeto que renderiza el string actual en pantalla.
- `imagenBase` (`GreenfootImage`): Guarda la imagen original de la caja.
- `indice` (`int`): Posición actual en el arreglo de diálogos (comienza en 0).
- `teclaFpresionada` (`boolean`): Bandera para evitar que el texto avance rapidísimo al mantener apretada la tecla.

**Métodos:**
- `Dialogo(String[] dialogo)`: Constructor.
- `iniciarTexto()`: Instancia el objeto `Texto` con la frase actual (`indice`) y lo añade al mundo.
- `removerTexto()`: Elimina el texto actual del mundo.
- `eliminarse()`: Elimina el texto y la caja de diálogo del mundo.
- `cambiarTamano(int ancho, int alto)`: Escala la imagen de fondo del diálogo.
- `cambiarDialogo()`: Si se presiona `F`, avanza el `indice`, actualiza el texto y reproduce `click.mp3`. Usa `teclaFpresionada` para registrar que la tecla fue soltada antes de avanzar de nuevo.
- `act()`: Llama a `cambiarDialogo()`.

---

### `Texto` (Hereda de `Actor`)
Un renderizador simple para generar imágenes a partir de cadenas de texto.

**Métodos:**
- `Texto(String contenido)`: Crea una imagen transparente con el texto en color negro y tamaño de fuente 12, y la establece como sprite del actor.

---

### `Contador` (Hereda de `Actor`)
HUD que muestra los puntos o "Horas de estudio".

**Atributos:**
- `puntos` (`int`): La cantidad de horas/puntos actuales (inicia en 0).

**Métodos:**
- `Contador()`: Constructor, llama a `generar()`.
- `generar()`: Crea y establece una imagen con el texto `"Hs estudio: " + puntos`.
- `sumarPuntos()`: Incrementa `puntos` en 1.

---

### `Vida` (Hereda de `Actor`)
HUD que muestra gráficamente la salud del jugador mediante barras de diferentes colores o estados.

**Métodos:**
- `Vida()`: Constructor, llama a `rescalar()`.
- `rescalar()`: Redimensiona la imagen actual de la barra de vida a 100x20 píxeles.
- `cambiarImagen(int numSprite)`: Cambia el archivo de imagen de la barra (ej: `vida2.png`, `vida3.png`) y la reescala nuevamente. Utilizado por el `Alumno` al recibir daño.

---

### `Materia` y `Tiempo` (Heredan de `Actor`)
Actualmente son clases vacías generadas por la plantilla de Greenfoot, listas para ser implementadas (por ejemplo, `Materia` funcionará como el enemigo del juego).

---

## 🛠️ Notas para el Desarrollador (Futuras tareas y fixes)
1. **Actualización del Contador:** En la clase `Contador`, el método `sumarPuntos()` incrementa la variable pero no actualiza la imagen. Quien siga el código debería agregar `generar();` dentro del método `sumarPuntos()` para que impacte visualmente.
2. **Sistema de Combate:** Implementar la lógica faltante en `Alumno.cambiarArma()` y las colisiones con la clase `Materia` (enemigos).
3. **Manejo de Niveles:** Desarrollar los métodos vacíos `nivel2()` a `nivel8()` en la clase `Aula`.
