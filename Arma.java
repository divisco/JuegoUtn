import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Arma here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Arma extends Actor
{
    //ATIBUTOS
    private int velocidad;
    private String direccionBorde;
    
    //CONSTUCTOR
    public Arma(String direccionBorde, int velocidad) {
        this.velocidad = velocidad;
        this.direccionBorde = direccionBorde;
        rotarDireccion();
    }
    
    public void act()
    {
        moverHaciaBorde(direccionBorde);
        if (getWorld() == null) {
            return;
        }
        if (this.isTouching(Materia.class)) {
            hacerDanoEnemigo();
        }
        if (getWorld() != null) {
            destruirPorColision();
        }
    }
    
    //METODOS
    public void hacerDanoEnemigo() {
        Materia materia = (Materia) getOneIntersectingObject(Materia.class);
        if (materia != null) {
            World mundo = getWorld();
            Aula aula = (Aula) getWorld();
            aula.sumarHsEstudio();
            mundo.removeObject(materia);
            Alumno alumno = (Alumno) aula.getObjects(Alumno.class).get(0);
            if(alumno.getArma()==1){
                aula.removeObject(this);
                Greenfoot.playSound("destruirEnemigo.mp3");
            }
        }
    }
    
    private void moverHaciaBorde(String direccionBorde) {
        World mundo = getWorld();
        if (mundo == null) return;
        if (direccionBorde == null){ direccionBorde = "UP"; }

        int radioX = getImage().getWidth() / 2;
        int radioY = getImage().getHeight() / 2;
        
        switch (direccionBorde.toUpperCase()) {
            case "DERECHO":
                int limiteDerecho = mundo.getWidth() - radioX;
                if (getX() < limiteDerecho) {
                    // Avanza hacia la derecha pero sin pasarse del borde
                    setLocation(Math.min(getX() + velocidad, limiteDerecho), getY());
                }
                break;

            case "IZQUIERDO":
                int limiteIzquierdo = radioX;
                if (getX() > limiteIzquierdo) {
                    // Avanza hacia la izquierda
                    setLocation(Math.max(getX() - velocidad, limiteIzquierdo), getY());
                }
                break;

            case "UP":
                int limiteSuperior = radioY;
                if (getY() > limiteSuperior) {
                    // Avanza hacia arriba
                    setLocation(getX(), Math.max(getY() - velocidad, limiteSuperior));
                }
                break;

            case "DOWN":
                int limiteInferior = mundo.getHeight() - radioY;
                if (getY() < limiteInferior) {
                    // Avanza hacia abajo
                    setLocation(getX(), Math.min(getY() + velocidad, limiteInferior));
                }
                break;
        }
    }
    
    //este metodo es para rotar visualmente las armas
    private void rotarDireccion(){
        if (this.direccionBorde == null) return;
        //las rotaciones tienen +35 por la inclinacion del .png
        switch (this.direccionBorde.toUpperCase()) {
            case "DERECHO":
                setRotation(44);
                break;
            case "DOWN":
                setRotation(134);
                break;
            case "IZQUIERDO":
                setRotation(224);
                break;
            case "UP":
                setRotation(314);
                break;
        }
        
    }
    
    // ESTO PERMITE CONTROLAR SI HACE COLICION CON OTRAS COSAS ANTES QUE CON EL BORDE DEL MUNDO
    private void destruirPorColision(){
        if (this.isTouching(Materia.class) || this.isTouching(Profesor.class) || this.isTouching(Decoration.class)){
            World mundo = getWorld();
            mundo.removeObject(this);
            return;
        } else {
            destruirAlLlegarAlBorde();
        }
    }
    
    private void destruirAlLlegarAlBorde() {
        World mundo = getWorld();
    
        // 1. Verificación de seguridad
        if (mundo == null) {
            return;
        }
    
        int limiteDerecho = mundo.getWidth() - (getImage().getWidth() / 2) - 3;
        int limiteInferior = mundo.getHeight() - (getImage().getHeight() / 2);
        int limiteIzquierdo = 20;
        int limiteSuperior = 20;
    
        // 2. Comprobación en eje X
        if (getX() >= limiteDerecho || getX() <= limiteIzquierdo) {
            mundo.removeObject(this);
            return; // Detiene la ejecución aquí
        }
    
        // 3. Comprobación en eje Y
        if (getY() >= limiteInferior || getY() <= limiteSuperior) {
            mundo.removeObject(this);
            return; // Detiene la ejecución aquí
        }
    }
}
