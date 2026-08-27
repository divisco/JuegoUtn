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
    
    
    //constructor
    public Materia(String nombre, int danio, int vida, int velocidad){
        this.nombre = nombre;
        this.danio = danio;
        this.vida = vida;
        this.velocidad = velocidad;
    }

    public void act() {
        perseguirJugador();
    }
    
    //metodos
 /**   public void perseguirJugador(Alumno alumno){
        //guardamos el calculo de la diferencia entre la x e y del alumno y la materia
        int calX = alumno.getX() - this.getX();
        int calY = alumno.getY() - this.getY();
        
        //calculamos la distancia total usando Pitagoras
        double distancia = Math.sqrt((posX * posX) + (posY * posY));
        
        //Mover solo si no est[an en el mismo punto
        if (distancia > 0){            
            int nuevaX = (int) Math.round(this.getX() + (calX / distancia) * this.velocidad);
            int nuevaY = (int) Math.round(this.getY() + (calY / distancia) * this.velocidad);
            
            this.setLocation(nuevaX, nuevaY);
        }
    } */
    
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
