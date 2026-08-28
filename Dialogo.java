import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Dialogo extends Actor
{
    private String[] dialogo;
    private Texto texto;
    private GreenfootImage imagenBase;
    private int indice = 0;
    private boolean teclaFpresionada = false;
    private String nameProfe;
    Aula escenario;

    public Dialogo(String[] dialogo, String nameProfe){
        this.dialogo = dialogo;
        this.nameProfe = nameProfe;
    }

    public void act(){
        cambiarDialogo();
    }

    public void iniciarTexto(){
        texto = new Texto(dialogo[indice]);
        getWorld().addObject(texto, getX(), getY());    
    }

    public void removerTexto(){
        if (texto != null && texto.getWorld() != null) {
            getWorld().removeObject(texto);
        }
    }

    public void eliminarse()
    {
        removerTexto();
        World mundo = getWorld();
        if (mundo != null) {
            mundo.removeObject(this);
        }
    }

    public void cambiarTamano(int ancho, int alto){
        GreenfootImage imagen = getImage();
        imagen.scale(ancho, alto);
        setImage(imagen);
        imagenBase = getImage();
    }
    
    public int[] calcularValores(){
        Aula aula = (Aula) getWorld();
        int valorVida = (int) Math.round((10.0 * Math.pow(1.2, (aula.getCurrentEscenario() - 1))));
        int valorVelocidad = (int) Math.round((15.0 * Math.pow(1.2, (aula.getCurrentEscenario() - 1))));
        int[] valores = {valorVida, valorVelocidad};
        return valores;
    }
   
    public void cambiarDialogo() {
        if (Greenfoot.isKeyDown("f") && !teclaFpresionada) {
            Greenfoot.playSound("click.mp3");
            teclaFpresionada = true;            
            
            // Avanza a la siguiente página de texto mientras queden frases
            if (indice < dialogo.length - 1){
                indice++;
                removerTexto();
                iniciarTexto();
            } else {
                // Llegó al final del texto del diálogo
                Alumno alumno = (Alumno) getWorld().getObjects(Alumno.class).get(0);
                Contador contador = (Contador) getWorld().getObjects(Contador.class).get(0);
                Aula aula = (Aula) getWorld();
                
                // Si estamos en el nivel 0, solo pasa al siguiente nivel
                if (aula.getCurrentEscenario() == 0) {
                    aula.siguienteNivel();
                } else {
                    int valorVida = aula.getCostoVida();
                    int valorVelocidad = aula.getCostoVelocidad();

                    if (nameProfe.equalsIgnoreCase("Mario")) {
                        if (contador.getPuntos() >= valorVida) {
                            contador.decrementarPuntos(valorVida);
                            getWorld().showText("Recibes vida!", 300, 250);
                            Greenfoot.delay(50);
                            getWorld().showText("", 300, 250);
                            alumno.aumentarVida();
                        } else {
                            getWorld().showText("No cuentas con " + valorVida + " horas de estudio.\n¡A rendir sin refuerzos!", 300, 250);
                            Greenfoot.delay(60);
                            getWorld().showText("", 300, 250);
                        }
                        aula.siguienteNivel();
                    } else if (nameProfe.equalsIgnoreCase("Sole")) {
                        if (contador.getPuntos() >= valorVelocidad) {
                            contador.decrementarPuntos(valorVelocidad);
                            getWorld().showText("Recibes velocidad!", 300, 250);
                            Greenfoot.delay(50);
                            getWorld().showText("", 300, 250);
                            alumno.aumentarVelocidad();
                        } else {
                            getWorld().showText("No cuentas con " + valorVelocidad + " horas de estudio.\n¡A rendir sin refuerzos!", 300, 250);
                            Greenfoot.delay(60);
                            getWorld().showText("", 300, 250);
                        }
                        aula.siguienteNivel();
                    }
                }
                eliminarse();
            }
        }

        if (!Greenfoot.isKeyDown("f")) {
            teclaFpresionada = false;
        }
    }
}
