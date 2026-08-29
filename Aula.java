import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

public class Aula extends World
{
    //Todos estos atributos los podemos llamar con un this. de ser necesario, ya que son globales
    //interfaz y jugador
    private Vida vida = new Vida(); // barra de  vida del jugador
    private Alumno alumno = new Alumno(5,100,5, vida);
    private Contador contador = new Contador(); // contador de puntos
    private int posicion = 0;
    private Tiempo tiempo = new Tiempo(0);
    
    //controlamos de escenarios y spawn de enemigos
    private int currentEscenario = 0;
    private int segundosIniciales = 25;
    private int contadorSpawn = 0;
    private int frecuenciaSpawn = 60; //crea un enemigo cada 1s aprox
    private boolean nivelActivo = false;
    
    //Constructor
    public Aula()
    {    
        super(600, 400, 1);
        addObject(contador, 530, 20);
        addObject(vida, 60, 20);
        addObject(tiempo, 300, 20);
        setCapas();
        nivel0();
    }
    
    public void act()
    {
        if (this.nivelActivo) {
            //control de spawn
            this.contadorSpawn ++;
            if (this.contadorSpawn >= this.frecuenciaSpawn * Math.pow(1.05, this.currentEscenario)) { //aca hacemos inicial*porcentaje elevado al nivel
                spawnEnemigos();
                spawnEnemigos();
                this.contadorSpawn = 0;
            }
            
            //control de fin de nivel por el tiempo
            if (this.tiempo.estaTerminado()) {
                this.nivelActivo = false;
                if (this.currentEscenario <8){
                    tienda();            
                }else{
                    nivel0();
                }
            }
        }
    }
    
    // PRESENTACION JUEGO
    
    // Tienda accesible para comprar
    
    public void tienda(){
        //tienda normal
        if (this.currentEscenario <=8){
            limpiarEscenario();
            alumno.setLocation(300, 200);
            showText("Tienda", 300, 250);
            Greenfoot.delay(150);
            showText("", 300, 250);
            Profesor mario = new Profesor(getDialogoMarioTienda(), "Mario", "marioSkin.png");
            mario.resize();
            addObject(mario, 200, 100);
            Profesor sole = new Profesor(getDialogoSoleTienda(), "Sole", "soleSkin.png");
            sole.resize();
            addObject(sole, 380, 100);
            Decoration planta1 = (Planta) new Planta();
            addObject(planta1, 100, 300);
            Decoration planta2 = (Planta) new Planta();
            addObject(planta2, 500, 300);
        }      
    }
    // Nivel de inicio con mario
    public void nivel0(){
        //incializamos al alumno
        limpiarEscenario();
        alumno.resize(45,65);
        addObject(alumno, 300, 300);
        
        if (this.currentEscenario < 8){
            Profesor mario = new Profesor(getDialogoMarioInicio(), "Mario", "marioSkin.png");
            mario.resize();
            addObject(mario, 300, 100);
        } else {
            alumno.setLocation(300, 200);
            Profesor mario = new Profesor(getDialogoMarioFinal(), "Mario", "marioSkin.png");
            mario.resize();
            addObject(mario, 300, 100);
        }
        
        //Decoracion del aula
        Banco banco1=new Banco();
        Banco banco2=new Banco();
        Banco banco3=new Banco();
        Banco banco4=new Banco();
        addObject(banco1, 200, 200);
        addObject(banco2, 400, 200);
        addObject(banco3, 200, 300);
        addObject(banco4, 400, 300);
    }
    
    //Enemigos segun nivel actual
    private Materia elegirEnemigo(){
        switch (this.currentEscenario) {
            case 1: return new EnemigoI2();
            case 2: return new EnemigoIS();
            case 3: return new EnemigoSO();
            case 4: return new EnemigoSSL();
            case 5: return new EnemigoF2();
            case 6: return new EnemigoAM2();
            case 7: return new EnemigoASI();
            case 8: return new EnemigoPP();
            default: return null;
        }
    }
    
    //Spawneador de enemigos
    public void spawnEnemigos(){
        Materia enemigo = elegirEnemigo();
        if (enemigo == null) return; // si no hay enemigo para el nivel actual, corta la ejecucion
        
        //para definir las posiciones del spaw en el mundo
        int x = 0;
        int y = 0;
        int borde = Greenfoot.getRandomNumber(4);
        // 0 arriba , 1 abajo, 2 izquierda, 3 derecha
        
        switch (borde) {
            case 0: //arriba
                x = Greenfoot.getRandomNumber(getWidth());
                y = 0;
                break;
            case 1: //abajo
                x = Greenfoot.getRandomNumber(getWidth());
                y = getHeight();
                break;
            case 2: //izquierda
                x = 0;
                y = Greenfoot.getRandomNumber(getHeight());
                break;
            case 3: //derecha
                x = getWidth();
                y = Greenfoot.getRandomNumber(getHeight());
                break;
        }
        
        //con esto añadimos enemigos en el mundo
        enemigo.resize();
        addObject(enemigo, x, y);
    }
    
    // Iniciamos todos lo niveles con este metodo
    public void iniciarNivel(int nivel){
        limpiarEscenario();
        this.currentEscenario = nivel;
        this.contadorSpawn = 0;
        this.tiempo.contarTiempo(this.segundosIniciales + (5 * this.currentEscenario));
        this.nivelActivo = true;
    }
    
    // Victorias y Derrotas
    // Avanzamos al siguiente nivel incluyendo mensajes
    public void siguienteNivel() {
        this.currentEscenario++;
        // Condicion de victoria
        if (currentEscenario <= 8) {
            Materia materia = elegirEnemigo();
            showText("NIVEL " + this.currentEscenario, 300, 250);
            Greenfoot.delay(100);
            
            // Verificación de seguridad para evitar NullPointerException
            if (materia != null) {
                showText(materia.getNombre().toUpperCase(), 300, 250);
            }
            
            Greenfoot.delay(100);
            showText("", 300, 250);
            iniciarNivel(this.currentEscenario);
        } else {
            // AL PASAR LOS 8 NIVELES FINALIZA EL JUEGO
            nivel0();
            Greenfoot.delay(100);
            showText("", 300, 100);
            int dialogos = 2;
            if (dialogos == 2){
                Aprobaste aprobaste = new Aprobaste();
                addObject(aprobaste, 300, 200);
                showText("NOS VEMOS EN TERCERO", 300, 275);
                Greenfoot.delay(150);
                Greenfoot.stop();
            }
            
        }
    }
    
    public void limpiarEscenario() {
        List<Actor> todosLosObjetos = getObjects(Actor.class);
        for (Actor objeto : todosLosObjetos) {
            if (!(objeto instanceof Alumno || objeto instanceof Vida || objeto instanceof Contador || objeto instanceof Tiempo)){
                removeObject(objeto);
            }
        }
    }
    
    //condicion de derrota, si la vida es 0, se activa este metodo y tiene que recursar el nivel
    // Condicion de derrota: si la vida es 0, se activa este metodo y repite el nivel actual
    public void recursarNivel() {
        this.nivelActivo = false; // Pausamos el spawn mientras se muestra el mensaje
        limpiarEscenario();
        
        this.alumno.morir();
        Greenfoot.delay(150);
        
        Desaprobaste desaprobaste = new Desaprobaste();
        addObject(desaprobaste, 300, 200);
        showText("TIENES QUE RECURSAR LA MATERIA", 300, 250);
        Greenfoot.delay(150);
        showText("", 300, 250);
        removeObject(desaprobaste);
        
        this.alumno.reaparecer();
        
        iniciarNivel(this.currentEscenario);
    }
    
    public void setCapas(){
        setPaintOrder(Aprobaste.class, Desaprobaste.class, Texto.class, Dialogo.class, Tiempo.class, Contador.class,Banco.class,Vida.class, Lapiz.class, Alumno.class, Goma.class, Profesor.class);
    }
    //Metodo utilizado por arma para indicar que la suma de horas de estudio
    public void sumarHsEstudio(){
        contador.sumarPuntos();
    }
    
    public int getCurrentEscenario(){
        return this.currentEscenario;
    }
    
    // Costo de Vida: valorInicial * (1.2 ^ (nivel - 1))
    public int getCostoVida() {
        return (int) Math.round(10.0 * Math.pow(1.3, this.currentEscenario - 1));
    }

    // Costo de Velocidad: valorInicial * (1.2 ^ (nivel - 1))
    public int getCostoVelocidad() {
        return (int) Math.round(15.0 * Math.pow(1.3, this.currentEscenario - 1));
    }
    
    public String[] getDialogoMarioInicio(){ //corresponde al dialogo del nivel 0, por eso queda aca, esto lo dice mario
        return new String[]{
            "¡Hola estudiante!",
            "Bienvenido al segundo año de \nla carrera de ingenieria en sistemas.",
            "Tendras que aprobar las 8 \nmaterias de este año",
            "La clave es que consigas horas \nde estudio atacando a las materias",
            "Sole y yo te ayudaremos, unicamente \nsi estudias lo suficiente",
            "Recuerda, estamos de tu lado \npero tambien necesitamos de tu voluntad",
            "Up to you!"
        };    
    }

    public String[] getDialogoMarioTienda() {
        return new String[]{
            "¡Hola de nuevo!",
            "¡Superaste "+elegirEnemigo().getNombre()+" con exito!",
            "Si estudiaste " + getCostoVida() + " horas o mas...",
            "Pudes usarlas para recuperar tu vida",
            "Up to you!",
            "Presiona la tecla F para \nrecuperar toda la vida"
        };
    }

    public String[] getDialogoSoleTienda() {
        return new String[]{
            "¡Hola estudiante!",
            "¡Superaste "+elegirEnemigo().getNombre()+" con exito!",
            "Si estudiaste " + getCostoVelocidad() + " horas o mas...",
            "Puedes usarla para aumentar tu velocidad",
            "Presiona la tecla F para \naumenar en 1 tu velocidad"
        };
    }
    
    public String[] getDialogoMarioFinal(){ // dialogo al final del juego.
        return new String[]{
            "¡Hola de nuevo!",
            "¡Superaste TODAS las materias con exito!",
            "¡Te felicito por esforzarte en cada momento!",
            "¡Sigue dando lo mejor cada dia!",
            "Up to you!"
        };    
        
    }
    
    public String[] getDialogoSoleFinal(){
        return new String[]{
            "¡Hola de nuevo!",
            "¡Bien por llegar hasta el final del año!",
            "¡Te felicito por esforzarte \nen cada momento!",
            "Recuerda siempre:\n \nSi lo puedes imaginar, \nlo puedes programar"
        };
    }

    // --- MÉTODOS DE CONEXIÓN CON CONTADOR ---

    public int getHorasEstudio() {
        return this.contador.getPuntos();
    }

    public void descontarHorasEstudio(int cantidad) {
        this.contador.decrementarPuntos(cantidad);
    }
}
