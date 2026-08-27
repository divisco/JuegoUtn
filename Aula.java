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
    private int segundosIniciales = 30;
    private int contadorSpawn = 0;
    private int frecuenciaSpawn = 120; //crea un enemigo cada 2s aprox
    private boolean nivelActivo = false;
    
    private String[] dialogoNivel1 = { //corresponde al dialogo del nivel 0, por eso queda aca, esto lo dice mario
            "¡Hola estudiante!",
            "Bienvenido al segundo año de \nla carrera de ingenieria en sistemas.",
            "Tendras que aprobar las 8 \nmaterias de este año",
            "La clave es que consigas horas \nde estudio atacando a las materias",
            "Sole y yo te ayudaremos, unicamente \nsi estudias lo suficiente",
            "Recuerda, estamos de tu lado \npero tambien necesitamos de tu voluntad",
            "Up to you!"
    };
    
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
            if (this.contadorSpawn >= this.frecuenciaSpawn) {
                spawnEnemigos();
                this.contadorSpawn = 0;
            }
            
            //control de fin de nivel por el tiempo
            if (this.tiempo.estaTerminado()) {
                this.nivelActivo = false;
                siguienteNivel();
            }
        }
    }
    
    // PRESENTACION JUEGO
    public void nivel0(){
        //incializamos al alumno
        alumno.resize();
        addObject(alumno, 300, 300);
        
        Profesor mario = new Profesor(dialogoNivel1);
        mario.resize();
        addObject(mario, 300, 100);
        
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
            case 1: return new EnemigoAM2();
            case 2: return new EnemigoIS();
            case 3: return new EnemigoPP();
            // case 4: return new EnemigoSSL();
            // case 5: return new EnemigoF2();
            // case 6: return new EnemigoSO();
            // case 7: return new EnemigoASI();
            // case 8: return new EnemigoI2();
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
                x = getHeight();
                y = Greenfoot.getRandomNumber(getHeight());
                break;
        }
        
        addObject(enemigo, x, y);
    }
    
    // Iniciamos todos lo niveles con este metodo
    public void iniciarNivel(int nivel){
        limpiarEscenario();
        this.currentEscenario = nivel;
        this.contadorSpawn = 0;
        this.tiempo.contarTiempo(this.segundosIniciales);
        this.nivelActivo = true;
    }
    
    // Avanzamos al siguiente nivel incluyendo mensajes
    public void siguienteNivel(){
        this.currentEscenario++;
        
        if(currentEscenario<=8){
            showText("BIENVENIDO A LA SIGUIENTE MATERIA", 300, 250);
            Greenfoot.delay(100);
            showText("NIVEL " + this.currentEscenario, 300, 250);
            Greenfoot.delay(100);
            showText("", 300, 250);
            iniciarNivel(this.currentEscenario);
        } else {
            // AL PASAR LOS 8 NIVELES FINALIZA EL JUEGO
            showText("¡FELICITACIONES! AÑO APROBADO", 300, 200);
            Greenfoot.delay(100);
            showText("", 300, 100);
            Greenfoot.stop();
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
    
    public void setCapas(){
        setPaintOrder(Texto.class,Dialogo.class, Tiempo.class, Contador.class,Banco.class,Vida.class, Alumno.class, Profesor.class);
    }
}
