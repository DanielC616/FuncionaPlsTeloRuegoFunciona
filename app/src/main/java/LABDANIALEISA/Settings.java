/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LABDANIALEISA;

import java.awt.Point;
import java.awt.geom.Point2D;

/**
 *
 * @author Revan
 */
//vamos a usar esta clase para establecer algunas constantes
public class Settings {
    // por qué public? Se usa para que pueda ser accedido por otras 
    // por qué static? esto es para que no necesite crear una instancia para usar el método
    // por qué final? esto es para que declarar que será constante y no cambiará
    public static final int WIDTH = 1600;
    public static final int HALF_WIDTH = WIDTH/2;
    public static final int HEIGHT = 900;
    public static final int HALF_HEIGHT = HEIGHT/2;
    public static final int FPS = 60;
    public static final int SCALE = 100;
    
    public static final int WALL_HEIGHT=1;
    
    public static Point2D.Float PLAYER_POS = new Point2D.Float(1,5);//lo quiero en decimales para el spawn point sea en mitad de un cuadrado
    public static double PLAYER_ANGLE = 0;
    public static double PLAYER_SPEED = 0.008;
    public static double PLAYER_MOUSE_SENSITIVITY = 0.002;
    
    //variables de mapa
    public static final int MAX_ROWS=70;//la altura máxima
    public static final int MAX_COLUMNS=70;//el ancho máximo
    
    
    //variables raycasting, hay otros métodos, y puede que este no sea el más optimo, pero es el más facil a entender conceptualmente
    //la razón que hay mitades, es porque se usará desde 0 hasta la mitad (para llegar al jugador)
    //y luego desde el jugador hasta la mitad del fov para el fov completo
    public static final float FOV_GRADOS=90;
    public static final float FOV = (FOV_GRADOS*(float)Math.PI)/180;//pasar fov a radianes como la computadora
    public static final float HALF_FOV=FOV/2;//queremos lanzar la mitad del fov de un lado, y la otra mitad del otro
    //usamos num_rays=width/2, para reducir el numero de rayos
    public static final int NUM_RAYS=WIDTH/2;//un numero alto de rayos, entre mayor rayos, mayor resolución
    
    public static final int HALF_NUM_RAYS=NUM_RAYS/2;
    // el angulo entre rayos(delta_angle) =angulo total de visión(90 grados) entre numero de rayos
    //esto distribuye los rayos de igual forma entre los 90 grados del fov
    public static float DELTA_ANGLE=FOV/NUM_RAYS;
    public static final int MAX_DEPTH=30;
    
    
    public static final float SCREEN_DIST= HALF_WIDTH/(float)Math.tan(HALF_FOV);
    
    //calcula que tan ancho debe ser cada rectangulo para llenar la pantalla con los rayos
    //es decir, es vez de crear 1600 rayos y luego que cada rayo cree un rectangulo de un pixel
    //se crean 800 con una anchura de dos y así mejorar el uso de recursos
    public static final int SCREEN_SCALE=WIDTH/NUM_RAYS;
    
    
}
