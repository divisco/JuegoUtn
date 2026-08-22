import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
public class Aula extends World
{
    private Alumno alumno = new Alumno(2,100,5);
    private Contador contador = new Contador();
    public void nivel1(){
        addObject(alumno, 300, 300);
        String[] dialogos = {"¡Hola estudiante!","Bienvenido al segundo año de la carrera de ingenieria en sistemas",};
        Profesor profe=new Profesor("Hola estudiante");
        addObject(profe, 300, 100);
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
        addObject(contador, 50, 20);
        nivel1();
    }
}
