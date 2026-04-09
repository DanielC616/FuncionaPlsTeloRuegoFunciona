/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LABDANIALEISA;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;


/**
 *
 * @author Revan
 */
public class LevelManager {
    private Game game;
    //contrario a lo aparente, maxrows controla Y, y max columns el x
    //esto es porque la cantidad de rows es valor máximo de Y
    int mapNum [][]= new int [Settings.MAX_ROWS][Settings.MAX_COLUMNS];
    public void loadMap(){
        try{//aquí decidi de usat try catch por el potencial de romper el juego si el mapa carga mal
           InputStream is=getClass().getResourceAsStream("/maps/map01.txt");//loadea el recurso
           BufferedReader br = new BufferedReader(new InputStreamReader(is));//para leer su contenido
           int mapcol=0;
           int maprow=0;
           String line;
           while (mapcol<Settings.MAX_COLUMNS && maprow<Settings.MAX_ROWS){//mientras permanezca dentro de los limites
               line= br.readLine();//lee y guarda una linea de texto
               for(mapcol=0;mapcol<=line.length();mapcol++){
                   //separa y guarda los valores de line y lo pasa a un arreglo
                   String numbers[]=line.split(",");
                   //pasar a int y guarda un valor
                   int num = Integer.parseInt(numbers[mapcol]);
                   //se pone mapcol para repetir y guardar cada valor
                   //guardarlo en la matriz 2D que es el mapa
                   mapNum[mapcol][maprow]=num;
                    
               }
               if(mapcol==line.length()){
                   mapcol=0;//reinicio el mapcol que es el x
                   maprow++;//pasa a la siguiente fila
               }
           }
           while((line=br.readLine())!=null){//mientras exista una linea que leer
               line=br.readLine();
               String numbers[]=line.split(",");//guardarlo en un vector y separar los digitos
               int num= Integer.parseInt(numbers[mapcol]);
               mapNum[mapcol][maprow]=num; 
               mapcol++;
               maprow++;
           }
           br.close();
        }catch(Exception e){
            
        }
    }
    
}
