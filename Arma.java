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
        destruirAlLlegarAlBorde();
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
    private void destruirAlLlegarAlBorde() {
        // isAtEdge() detecta cuando toca cualquier extremo del mapa
        World mundo = getWorld();
        int limiteDerecho = mundo.getWidth() - (getImage().getWidth() / 2);
        int limiteInferior = mundo.getHeight() - (getImage().getHeight() / 2);

        if (getX() >= limiteDerecho) {
            getWorld().removeObject(this);
        }
        if (getY() >= limiteInferior) {
            getWorld().removeObject(this);
        }
    }
}
