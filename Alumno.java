import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
public class Alumno extends Actor
{
    private int velocidad;
    private int vida;
    private Vida barraVida;
    private int fuerza;
    private int tamanoScala = 100;
    private int margenContacto = 10;
    
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
            tamanoAlumno();
        }
    }
    public void movimientoLeft(){
        if(Greenfoot.isKeyDown("a")){
            intentarMover(getX() - velocidad, getY());
            setImage("alumnoLadoIzq.png");
            tamanoAlumno();
        }
    }
    public void movimientoUp(){
        if(Greenfoot.isKeyDown("w")){
            intentarMover(getX(), getY() - velocidad);
            setImage("alumnoEspalda.png");
            tamanoAlumno();
        }
    }
    public void movimientoDown(){
        if(Greenfoot.isKeyDown("s")){
            intentarMover(getX(), getY() + velocidad);
            setImage("alumnoFrente.png");
            tamanoAlumno();
        }
    }
    
    public void tamanoAlumno(){
        GreenfootImage imagen = getImage();
        imagen.scale(tamanoScala,tamanoScala);
        setImage(imagen);
    }
    
    private boolean debeChocarProfesor() {
        Profesor prof = (Profesor) getOneIntersectingObject(Profesor.class);
        if (prof != null) {
            int distX = Math.abs(getX() - prof.getX());
            int distY = Math.abs(getY() - prof.getY());
            
            int limiteX = (getImage().getWidth() / 2) + (prof.getImage().getWidth() / 2) - margenContacto - 30;
            int limiteY = (getImage().getHeight() / 2) + (prof.getImage().getHeight() / 2) - margenContacto;

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

        if (debeChocarProfesor()) {
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
