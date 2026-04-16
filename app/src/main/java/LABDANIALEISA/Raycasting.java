/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LABDANIALEISA;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

/**
 *
 * @author Revan
 */
public class Raycasting {
    private Game game;
    private double rayAngle;
    public Raycasting(Game game){
        this.game=game;
    }
    //poner Graphics2D dentro de los parametros para dibujar los rayos
    public void rayCast(Graphics2D g2d){
        //agarrar la posicion del jugador
        float ox=game.pepito.getPos().x;
        float oy=game.pepito.getPos().y;
        
        //mx y my son mapPosition
        int mx=game.pepito.getMapPos().x;
        int my=game.pepito.getMapPos().y;
        
        float x_vert,y_vert,x_hor,y_hor,rdx,rdy,depthV,depthH,deltadepth,rayDist,projHeight;//dx y dy es el salto en x e y respectivamente
        
        
        //se quita la mitad del fov, para que el loop dibuje el rayo desde la mitad antes, hasta la mitad despues
        //es decir, para asegurar que en el centro sea el angulo del jugador(o vector dirección)(hacia donde mira)
        this.rayAngle=game.pepito.getAngle()-Settings.HALF_FOV+0.000001;//se suma una cantidad pequeña para evitar división por 0
        float aTan= 1/(float)Math.tan(rayAngle);
        for(int ray=1;ray<=Settings.NUM_RAYS;ray++){
            double sen_a=Math.sin(rayAngle);
            double cos_a=Math.cos(rayAngle);
            
            //ahora queremos hallar el resultado de dos rayos basicamente, donde uno se detiene en los cortes horizontales y el otro con las verticales 
            //queremos sacar la dirección del rayo como un vector dirección que extendiremos hasta hallar un muro
            
            if (sen_a>0){//si el seno es positivo, está mirando hacia abajo (el sistema cartesiano de las interfaces gráficás están giradas)
                y_hor= my+1;
                rdy=1;//en la posición de mapa, cuando avanzamos una casilla en x, nada más avanzamos 1 y el y toca calcularlo
            } else{
                //si seno es negativo, está mirando hacia arriba, y queremos checkear el muro un poquito a la arriba del lugar en el mapa actual
                y_hor= (float)my-0.001f;//los decimales cuentan como double por default
                rdy=-1;//en este caso es negativo por estar hacia arriba
            }
           
            depthH=(y_hor-oy)/(float)sen_a;//por cierto, cuando ves float es porque necesito estandarizarlo a un tipo de dato
            x_hor=ox+depthH*(float)cos_a;
            
            //para todos los demás menos el inicial
            deltadepth=rdy/(float)sen_a;
            rdx=deltadepth *(float)cos_a;
            
            
            //queremos seguir el ciclo hasta que toque un muro o llegue a su maximo depth dado
            for(int i=1;i<=Settings.MAX_DEPTH;i++){
                if(game.pepito.checkWall(x_hor,y_hor)==false){//reusamos el checkwall para checkear si hay un muro
                    //si es true, es porque no hay muro(porque esta originalmente fue para checkeo de colisión)
                    //true es que se puede avanzar(no muro)
                    //break es una forma de enseguida salir de un loop
                    break;
                }
                //si no hay muro, siga avanzando
                x_hor+=rdx;
                y_hor+=rdy;
                depthH+=deltadepth;
            }
            
            
            
            
            
            
            
            
            
            //verticales xvert y yvert son los cortes del rayo cuando checkea colisión usando los limites verticales
            //en otras palabras, vamos a checkear colisión usando las partes de los muros en el eje y
            if (cos_a>0){//si el coseno es positivo, está mirando a la derecha
                x_vert= mx+1;
                rdx=1;//en la posición de mapa, cuando avanzamos una casilla en x, nada más avanzamos 1 y el y toca calcularlo
            } else{
                //si coseno es negativo, está mirando hacia atrás, y queremos checkear el muro un poquito a la izquierda del lugar en el mapa actual
                x_vert= (float)mx-0.001f;//los decimales cuentan como double por default
                //hacerlo directamente en mx podría confundir al sistema entre checkear el actual o el anterior
                rdx=-1;//en este caso es negativo por estar a la izquierda
            }
            //ahora, el corta con y de los cortes verticales
            depthV=(x_vert-ox)/(float)cos_a;//por cierto, cuando ves float es porque necesito estandarizarlo a un tipo de dato
            y_vert=oy+depthV*(float)sen_a;
            
            //para todos los demás menos el inicial
            deltadepth=rdx/(float)cos_a;
            rdy=deltadepth *(float)sen_a;//seno*hipotenusa=cateto opuesto(el incremento en y)
            
            
            //queremos seguir el ciclo hasta que toque un muro o llegue a su maximo depth dado
            for(int i=1;i<=Settings.MAX_DEPTH;i++){
                if(game.pepito.checkWall(x_vert,y_vert)==false){//reusamos el checkwall para checkear si hay un muro
                    //si es true, es porque no hay muro(porque esta originalmente fue para checkeo de colisión)
                    //true es que se puede avanzar(no muro)
                    //break es una forma de enseguida salir de un loop
                    break;
                }
                
                //si no hay muro, siga avanzando
                x_vert+=rdx;
                y_vert+=rdy;
                depthV+=deltadepth;
            }
            
            //ahora que tenemos las dos intersecciones,queremos hallar la de menor distancia para que sea la distancia del rayo
            if(depthV<depthH){
                rayDist=depthV;
            }else{
                rayDist=depthH;
            }
            rayDist*=Math.cos(game.pepito.getAngle()-rayAngle);
            
         //visualizar el rayo para debugging
       // g2d.setColor(Color.green);
        //g2d.draw(new Line2D.Float(ox*Settings.SCALE,oy*Settings.SCALE,Settings.SCALE*ox+
          //      Settings.SCALE*rayDist*(float)cos_a,Settings.SCALE*oy+Settings.SCALE*rayDist*(float)sen_a));
        
          
          //dibujar el resultado del raycaster en proyección 3d
        projHeight=Settings.SCREEN_DIST*Settings.WALL_HEIGHT/(rayDist+0.00001f);//otra vez, evitar división por 0
        double intensity=255/(1+Math.pow(rayDist, 5)*0.00002);
        int c = (int) Math.max(0, Math.min(255, intensity));
        Color co = new Color(c,c,c);
        g2d.setColor(co);
        //se le resta un projheight/2 para crear un offset que centra la camara
        //si no, la camara queda un poco más arriba de lo que queremos
        g2d.fill(new Rectangle2D.Float(ray*Settings.SCREEN_SCALE, Settings.HALF_HEIGHT-projHeight/2, Settings.SCREEN_SCALE, projHeight));
            
            this.rayAngle+=Settings.DELTA_ANGLE;//cada vez, se mueve un poco el angulo por delta angulo (espacio entre rayos)
        }
        
    }
    
    public void update(){
        //rayCast();
    }

}
