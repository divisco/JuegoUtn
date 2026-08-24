import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
public class Aula extends World
{
    private Vida vida = new Vida();
    private Alumno alumno = new Alumno(5,100,5, vida);
    private Contador contador = new Contador();
    private Inventario inventario = new Inventario();
    private int posicion = 0;
    public void nivel1(){
        addObject(alumno, 300, 300);
        String[] dialogos = { //corresponde al dialogo del nivel 1, por eso queda aca
            "¡Hola estudiante!",
            "Bienvenido al segundo año de \nla carrera de ingenieria en sistemas.",
            "Tendras que aprobar las 8 \nmaterias de este año",
            "La clave es que consigas horas \nde estudio atacando a las materias",
            "Sole y yo te ayudaremos, unicamente \nsi estudias lo suficiente",
            "Recuerda, estamos de tu lado \npero tambien necesitamos de tu voluntad",
            "Up to you!"
        };
        Profesor mario = new Profesor(dialogos);
        addObject(mario, 300, 100);
        
        setPaintOrder(Inventario.class, Contador.class, Alumno.class, Profesor.class);
    }
    public void nivel2(){
        addObject(alumno, 300, 300);
    }
    public void nivel3(){
        addObject(alumno, 300, 300);
    }
    public void nivel4(){
        addObject(alumno, 300, 300);
    }
    public void nivel5(){
        addObject(alumno, 300, 300);
    }
    public void nivel6(){
        addObject(alumno, 300, 300);
    }
    public void nivel7(){
        addObject(alumno, 300, 300);
    }
    public void nivel8(){
        addObject(alumno, 300, 300);
    }
    public Aula()
    {    
        super(600, 400, 1);
        addObject(contador, 530, 20);
        addObject(vida, 60, 20);
        addObject(inventario, 175, 25);
        nivel1();
    }
}
