import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
public class Aula extends World
{
    private Vida vida = new Vida();
    private Alumno alumno = new Alumno(5,100,5, vida);
    private Contador contador = new Contador();
    private int posicion = 0;
    private int segundosIniciales = 30;
    private Tiempo tiempo = new Tiempo(0);
    private int currentEscenario = 0;
    private String[] dialogoNivel1 = { //corresponde al dialogo del nivel 1, por eso queda aca, esto lo dice mario
            "¡Hola estudiante!",
            "Bienvenido al segundo año de \nla carrera de ingenieria en sistemas.",
            "Tendras que aprobar las 8 \nmaterias de este año",
            "La clave es que consigas horas \nde estudio atacando a las materias",
            "Sole y yo te ayudaremos, unicamente \nsi estudias lo suficiente",
            "Recuerda, estamos de tu lado \npero tambien necesitamos de tu voluntad",
            "Up to you!"
    };
    
    //Enemigos
    private EnemigoAM2 am2 = new EnemigoAM2();
    public void limpiarEscenario() {
        List<Actor> todosLosObjetos = getObjects(Actor.class);
        for (Actor objeto : todosLosObjetos) {
            if (!(objeto instanceof Alumno || objeto instanceof Vida || objeto instanceof Contador || objeto instanceof Tiempo || objeto instanceof Inventario)){
                removeObject(objeto);
            }
        }
    }
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
    // Enemigo AM2
    public void nivel1(){
        limpiarEscenario();
        tiempo.contarTiempo(segundosIniciales);
        addObject(am2, 0, 0);
    }
    public void nivel2(){
        limpiarEscenario();
        tiempo.contarTiempo(segundosIniciales * 2);
    }
    public void nivel3(){
        limpiarEscenario();
        tiempo.contarTiempo(segundosIniciales * 3);
    }
    public void nivel4(){
        limpiarEscenario();
        tiempo.contarTiempo(segundosIniciales * 4);
    }
    public void nivel5(){
        limpiarEscenario();
        tiempo.contarTiempo(segundosIniciales * 5);
    }
    public void nivel6(){
        limpiarEscenario();
        tiempo.contarTiempo(segundosIniciales * 6);
    }
    public void nivel7(){
        limpiarEscenario();
        tiempo.contarTiempo(segundosIniciales * 7);
    }
    public void nivel8(){
        limpiarEscenario();
        tiempo.contarTiempo(segundosIniciales * 8);
    }
    public void siguienteNivel(){
        currentEscenario+=1;
        if(currentEscenario==1){
            showText("Bienvenido al siguiente nivel...", 300, 100);
            nivel1();
            Greenfoot.delay(100);
            showText("", 300, 100);
        }
    }
    public void setCapas(){
        setPaintOrder(Texto.class,Dialogo.class, Inventario.class,Tiempo.class, Contador.class,Banco.class,Vida.class, Alumno.class, Profesor.class);
    }
    public Aula()
    {    
        super(600, 400, 1);
        addObject(contador, 530, 20);
        addObject(vida, 60, 20);
        addObject(tiempo, 300, 20);
        setCapas();
        nivel0();
    }
}
