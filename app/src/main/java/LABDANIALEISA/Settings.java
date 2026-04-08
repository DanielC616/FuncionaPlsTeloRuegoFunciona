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
    public static final int HEIGHT = 900;
    public static final int FPS = 60;
    public static final int SCALE = 100;
    
    public static Point2D.Float PLAYER_POS = new Point2D.Float(1,5);//lo quiero en decimales para el spawn point sea en mitad de un cuadrado
    public static double PLAYER_ANGLE = 0;
    public static double PLAYER_SPEED = 0.004;
    public static double PLAYER_MOUSE_SENSITIVITY = 0.002;
    
    
}
