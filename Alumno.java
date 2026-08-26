import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
public class Alumno extends Actor
{
    private int velocidad;
    private int vida;
    private Vida barraVida;
    private int fuerza;
    private int tamanoScala = 75;
    private int margenContacto = 10;
    private boolean tieneArmas = false;
    private Object[] inventario = (Object[]) new Object[3];
     
    public Alumno() {
        this.velocidad = 5;
        this.vida = 100;
        this.fuerza = 10;
        setImage("alumnoFrente.png");
    }
    public Alumno(int velocidad, int vida, int fuerza, Vida barraVida){
        this.velocidad = velocidad;
        this.vida=vida;
        this.fuerza=fuerza;
        this.barraVida = barraVida;
    }
    public void cambiarArma(){
        if(Greenfoot.isKeyDown("e")){
            
        }
    }
   
    public void movimientoRight(){
        if(Greenfoot.isKeyDown("d")){
            intentarMover(getX() + velocidad, getY());
            setImage("alumnoLadoDer.png");
            resize();
            
        }
    }
    public void movimientoLeft(){
        if(Greenfoot.isKeyDown("a")){
            intentarMover(getX() - velocidad, getY());
            setImage("alumnoLadoIzq.png");
            resize();
        }
    }
    public void movimientoUp(){
        if(Greenfoot.isKeyDown("w")){
            intentarMover(getX(), getY() - velocidad);
            setImage("alumnoEspalda.png");
            resize();
        }
    }
    public void movimientoDown(){
        if(Greenfoot.isKeyDown("s")){
            intentarMover(getX(), getY() + velocidad);
            setImage("alumnoFrente.png");
            resize();
        }
    }
    
    public void resize(){
        GreenfootImage imagen = getImage();
        imagen.scale(tamanoScala,tamanoScala);
        setImage(imagen);
    }
    
    private boolean detectHitbox() {
        Profesor prof = (Profesor) getOneIntersectingObject(Profesor.class);
        Banco banco = (Banco) getOneIntersectingObject(Banco.class);
        if (prof != null) {
            int distX = Math.abs(getX() - prof.getX());
            int distY = Math.abs(getY() - prof.getY());
            
            int limiteX = (getImage().getWidth() / 2) + (prof.getImage().getWidth() / 2) - margenContacto - 30;
            int limiteY = (getImage().getHeight() / 2) + (prof.getImage().getHeight() / 2) - margenContacto;

            if (distX < limiteX && distY < limiteY) {
                return true;
            }
        }
        if (banco != null) {
            int distX = Math.abs(getX() - banco.getX());
            int distY = Math.abs(getY() - banco.getY());
            
            int limiteX = (getImage().getWidth() / 2) + (banco.getImage().getWidth() / 2) - margenContacto - 30;
            int limiteY = (getImage().getHeight() / 2) + (banco.getImage().getHeight() / 2) - margenContacto - 20;

            if (distX < limiteX && distY < limiteY) {
                return true;
            }
        }
        return false;
    }
    private void intentarMover(int nuevaX, int nuevaY) {
        int oldX = getX();
        int oldY = getY();

        int radioX = getImage().getWidth() / 2;
        int radioY = getImage().getHeight() / 2;

        int minX = radioX;
        int maxX = getWorld().getWidth() - radioX;
        int minY = radioY;
        int maxY = getWorld().getHeight() - radioY;

        if (nuevaX < minX) nuevaX = minX;
        if (nuevaX > maxX) nuevaX = maxX;
        if (nuevaY < minY) nuevaY = minY;
        if (nuevaY > maxY) nuevaY = maxY;

        setLocation(nuevaX, nuevaY);
        if (detectHitbox()) {
            setLocation(oldX, oldY);
        }
    }
    public void act()
    {
        movimientoRight();
        movimientoLeft();
        movimientoUp();
        movimientoDown();
    }
    public void recibirDano(int decremento){
        vida = vida-decremento;
        if (barraVida !=null){
            Greenfoot.playSound("desaprobado.mp3");
            if(vida<100){
                barraVida.cambiarImagen(2);
            }
            if(vida<80){
                barraVida.cambiarImagen(3);
            }
            if(vida<60){
                barraVida.cambiarImagen(4);
            }
            if(vida<40){
                barraVida.cambiarImagen(5);
            }
            if(vida<=0){
                barraVida.cambiarImagen(6);
            }
 
       }
    }
    
    public void recibirArmas() {
        this.tieneArmas = true; // O la lógica para habilitar el lápiz y la goma
        
        
    }
}

