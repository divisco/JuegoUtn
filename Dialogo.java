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
    
    public void cambiarDialogo() {
        if (Greenfoot.isKeyDown("f") && !teclaFpresionada) {
            Greenfoot.playSound("click.mp3");
            teclaFpresionada = true;            
            if (indice < dialogo.length - 1){
                indice++;
                removerTexto();
                iniciarTexto();
            } else {
                // 1. Notifica al alumno para que reciba las armas
                Alumno alumno = (Alumno) getWorld().getObjects(Alumno.class).get(0);
                Contador contador = (Contador) getWorld().getObjects(Contador.class).get(0);
                Aula aula = (Aula) getWorld();
                int valorVida = (int) Math.round((10.0 * Math.pow(1.3, (aula.getCurrentEscenario() - 1))));
                int valorVelocidad = (int) Math.round((15.0 * Math.pow(1.3, (aula.getCurrentEscenario() - 1))));
                if(contador.getPuntos()!=0){
                    if(nameProfe.equalsIgnoreCase("Mario")){
                        if(contador.getPuntos()>=valorVida){
                            contador.decrementarPuntos(valorVida);
                            getWorld().showText("Recibes vida!", 300, 250);
                            Greenfoot.delay(50);
                            getWorld().showText("", 300, 250);
                            alumno.aumentarVida();
                            ((Aula) getWorld()).siguienteNivel();
                        } else {
                            getWorld().showText("No cuentas con "+ valorVida +" horas de estudio", 300, 250);
                            Greenfoot.delay(30);
                            getWorld().showText("", 300, 250);
                        }
                    }
                    if(nameProfe.equalsIgnoreCase("Sole")){
                        if (contador.getPuntos()>=valorVelocidad){
                            contador.decrementarPuntos(valorVelocidad);
                            getWorld().showText("Recibes velocidad!", 300, 250);
                            Greenfoot.delay(50);
                            getWorld().showText("", 300, 250);
                            alumno.aumentarVelocidad();
                            ((Aula) getWorld()).siguienteNivel();
                        } else {
                            getWorld().showText("No cuentas con las " +  valorVelocidad + " horas de estudio", 300, 250);
                            Greenfoot.delay(30);
                            getWorld().showText("", 300, 250);
                        }
                    }
                } else {
                    ((Aula) getWorld()).siguienteNivel();
                }
                eliminarse();
            }
        }

        if (!Greenfoot.isKeyDown("f")) {
            teclaFpresionada = false;
        }
    }
}
