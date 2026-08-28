import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

/**
 * Write a description of class Materia here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Materia extends Actor
{
    // atributos declarados
    private String nombre;
    private int danio;
    private int vida;
    private int velocidad;
    private int tiempoAttack = 30;
    private int contadorAttack = 0;
    private int contadorDañoRecibido = 0;
    //constructor
    public Materia(String nombre, int danio, int vida, int velocidad){
        this.nombre = nombre;
        this.danio = danio;
        this.vida = vida;
        this.velocidad = velocidad;
    }

    public void act() {
        perseguirJugador();
        contadorAttack++;
    }
    
    public String getNombre(){
        return this.nombre;
    }
    
    public void attackAlumno(){
        Alumno alumno = new Alumno();
        if(this.isTouching(Alumno.class) && contadorAttack == tiempoAttack) {
            contadorDañoRecibido += 20;
            Alumno.recibirDano(decremento);
        }
        contadorAttack = 0;
    }
    
    public void perseguirJugador() {
        // Obtenemos la lista de alumnos en el mundo
        List<Alumno> alumnos = getWorld().getObjects(Alumno.class);

        // Validamos que exista al menos un alumno en el escenario
        if (!alumnos.isEmpty()) {
            Alumno alumno = alumnos.get(0);
            turnTowards(alumno.getX(), alumno.getY()); // Gira hacia el alumno
            move(this.velocidad);                      // Avanza en esa dirección
        }
    }
}
