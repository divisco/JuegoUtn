import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Tiempo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Tiempo extends Actor
{
    private int tiempoRestante;
    private int ciclos;
    
    public Tiempo(int segundosIniciales){
        this.tiempoRestante = segundosIniciales;
        this.ciclos = 0;
        actualizarImagen();
    }
    
    public void act()
    {
        contarTiempo(tiempoRestante);
    }
    
    public void actualizarImagen(){
        GreenfootImage img = new GreenfootImage(""+ tiempoRestante,40,Color.WHITE, new Color(0, 0, 0, 0));
        setImage(img);
    }
    
    public void contarTiempo(int segundosIniciales){
        
        //escala aprox: 60 ciclos = 1 segundo
        tiempoRestante =+ segundosIniciales;
        if (tiempoRestante > 0){
            ciclos ++;
            if (ciclos >= 60) {
                tiempoRestante --;
                ciclos = 0;
                actualizarImagen();
            }
        }
    }
    
    public boolean estaTerminado(){
        return this.tiempoRestante <= 0;
    }
    
    
}
