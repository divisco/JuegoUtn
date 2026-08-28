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
                if(contador.getPuntos()!=0){
                    if(nameProfe.equalsIgnoreCase("Mario")  && contador.getPuntos()>=10){
                        getWorld().showText("Recibes vida!", 300, 250);
                        Greenfoot.delay(50);
                        getWorld().showText("", 300, 250);
                        alumno.aumentarVida();
                        contador.decrementarPuntos(10);
                    }
                    else if(nameProfe.equalsIgnoreCase("Sole") && contador.getPuntos()>=20){
                        getWorld().showText("Recibes velocidad!", 300, 250);
                        Greenfoot.delay(50);
                        getWorld().showText("", 300, 250);
                        alumno.aumentarVelocidad();
                        contador.decrementarPuntos(20);
                    }
                    
                }
                ((Aula) getWorld()).siguienteNivel();
                eliminarse();
            }
        }

        if (!Greenfoot.isKeyDown("f")) {
            teclaFpresionada = false;
        }
    }
}
