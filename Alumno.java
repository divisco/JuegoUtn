import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
public class Alumno extends Actor
{
    //atributos del alumno
    private int velocidad;
    private int vida;
    private Vida barraVida;
    private int fuerza;
    private int tamanoScala = 75;
    private int margenContacto = 10;
    
    //Armas y disparo de la goma
    private boolean tieneArmas = true;
    private int tiempoDisparo = 30;
    private int contadorDisparo = 0;
    private int tipoArmaEquipada = 0; // 0 es lapiz, 1 es goma
    private boolean teclaPresionada = false; // cortamos el bucle de mantener presionada E
    
    //CONSTRUCTOR
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
        setImage("alumnoFrente.png");
    }

    
    public void act()
    {
        //movimiento
        movimientoRight();
        movimientoLeft();
        movimientoUp();
        movimientoDown();
        //combate
        cambiarArma();
        atacarODisparar();
        contadorDisparo++;
    }
    
    //METODOS
    public void setImageAlumno(String image){
        setImage(image);
        resize();
    }
    
    public void cambiarArma(){
        //controlador para la tecla e
        if(Greenfoot.isKeyDown("e")){
            if(!this.teclaPresionada){ // se activa una sola vez al pulsar la tecla e
                this.tipoArmaEquipada = (this.tipoArmaEquipada == 0) ? 1:0; //
                this.teclaPresionada = true;
            }
        } else {
            this.teclaPresionada = false; // se libera al soltar la tecla
        }
    }
    
    private Arma crearProyectil(String direccion) {
        if (this.tipoArmaEquipada == 0) {
            return new Lapiz(direccion);
        } else {
            return new Goma(direccion);
        }
    }
    
    public void atacarODisparar() {
        if (this.contadorDisparo >= this.tiempoDisparo) {
            String dir = "";
            int spawnX = getX();
            int spawnY = getY();
            int offset = 40; // Distancia frente al alumno

            if (Greenfoot.isKeyDown("up")) {
                dir = "UP";
                spawnY -= offset;
            } else if (Greenfoot.isKeyDown("down")) {
                dir = "DOWN";
                spawnY += offset;
            } else if (Greenfoot.isKeyDown("left")) {
                dir = "IZQUIERDO";
                spawnX -= offset;
            } else if (Greenfoot.isKeyDown("right")) {
                dir = "DERECHO";
                spawnX += offset;
            }

            if (!dir.isEmpty()) {
                Arma arma = crearProyectil(dir);
                getWorld().addObject(arma, spawnX, spawnY);
                this.contadorDisparo = 0;
            }
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
    
    public void recibirDano(int cantidad){
       vida -= cantidad;

       if (vida < 0) {
           vida = 0;
       }
    
       // Actualiza el sprite de la barra de vida (ej: vida4.png, vida3.png...)
       if (barraVida != null) {
           if(vida>=100){ barraVida.cambiarImagen(10);}
           if(vida<100){ barraVida.cambiarImagen(10);}
           if(vida<90){ barraVida.cambiarImagen(9); }
           if(vida<80){ barraVida.cambiarImagen(8); }
           if(vida<70){ barraVida.cambiarImagen(7); }
           if(vida<60){ barraVida.cambiarImagen(6); }
           if(vida<50){ barraVida.cambiarImagen(5); }
           if(vida<40){ barraVida.cambiarImagen(4); }
           if(vida<30){ barraVida.cambiarImagen(3); }
           if(vida<20){ barraVida.cambiarImagen(2); }
           if(vida<10){ barraVida.cambiarImagen(1); }
           if(vida<=0){ barraVida.cambiarImagen(0); }
       } 
       
       if (barraVida !=null){
                Greenfoot.playSound("dañoVida.mp3");
        
        }
        
        if (vida == 0){
            Aula aula = (Aula) getWorld();
            aula.recursarNivel();
        }
    
    }
    //Obtener tipo de arma current
    public int getArma(){
        return tipoArmaEquipada;
    }
    //Metodos de incremento de vida y velocidad
    public void aumentarVida(){
        int diferencia = 100 - vida;
        vida+=diferencia;
        recibirDano(0);
    }
    
    public void aumentarVelocidad(){
        this.velocidad+=1;
    }
}

