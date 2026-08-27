import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Arma here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Arma extends Actor
{
    /**
     * Act - do whatever the Arma wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    private int velocidad = 3;
    private String direccionBorde;
    
    public Arma(String direccionBorde) {
        this.direccionBorde = direccionBorde;
    }
    public void act()
    {
        moverHaciaBorde();
        if (getWorld() == null) {
            return;
        }
        destruirPorColision();
    }
    private void moverHaciaBorde() {
        World mundo = getWorld();
        if (mundo == null) return;

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
