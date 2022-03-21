package myProject;

import java.util.ArrayList;
import java.util.Random;

/**
 * This class is used to model a game board.
 * @autor Natalia Riaños Horta (2042568) rianos.natalia@correounivalle.edu.co
 * Miguel Ángel Ospina Hernández (2040634) miguel.ospina.hernandez@correounivalle.edu.co
 * @version v.1.0.0 date: 1/03/2022
 */
public class Tablero extends ArrayList <ArrayList<Integer>> {
    private int cantFilas=10, cantColumnas=10;
    /**
     * Constructor of tablero class
     */
    public Tablero() {
        limpiarTablero();
    }
    /**
     * muestra el mapa por consola
    * */
    public void mostrarPorConsola(){
        for(int i = 0; i< this.size(); i++){
           // System.out.println(this.get(i));
        }
    }
    /**
     * limpia el tablero, pone todo en cero
     * */
    public void limpiarTablero(){
        this.clear();
        for(int numFila=0; numFila<cantFilas; numFila++){
            ArrayList <Integer> nuevaFila = new ArrayList<>();
            for(int numColumna=0; numColumna<cantFilas; numColumna++){
                nuevaFila.add(0);
            }
            this.add(nuevaFila);
        }
    }
    /**
     * añade un elemento en una posicion dada
     * */
    public void add (int elemento, int x, int y){
            this.get(y).remove(x);
            this.get(y).add(x,elemento);
    }

    /**
     * decir si hay un barco
     * */
    public boolean hayUnBarco(Barco barco, int x, int y){
        int max = this.get(1).size()-barco.getTamaño();
        switch (barco.getOrientacion()){
            case 0: //horizontal
                if(x>max){
                    x=max; //hacer el ajuste
                }
                for(int i=0; i<barco.getTamaño();i++){
                    if(this.get(y).get(x+i)==2){
                        return true; //hay un barco
                    }
                }
                return false;
            case 1: //vertical
                if(y>max){
                    y=max; //hacer el ajuste
                }
                for(int i=0; i<barco.getTamaño();i++){
                    if(this.get(y+i).get(x)==2){
                        return true; //no se puede pintar porque hay un 2
                    }
                }
                return false;
        }
        System.out.println("no entró en los casos");
        return true;
    }
    /**
     * pinta/muestra un barco en el mapa
     */
    public void pintarBarco(Barco barcoApintar, int x, int y){
        if(!hayUnBarco(barcoApintar,x,y)){
            int max = this.get(1).size()-barcoApintar.getTamaño();
            int cant=barcoApintar.getTamaño();
            switch (barcoApintar.getOrientacion()){
                case 0:
                    if(x>=max){
                        for(int i=max; i<=9;i++){
                            this.add(1,i,y);
                        }
                    }else{
                        for(int i=0; i<cant;i++){
                            this.add(1,x+i,y);
                        }
                    }
                    break;
                case 1:
                    if(y>=max){
                        for(int i=max; i<=9;i++){
                            this.add(1,x,i);
                        }
                    }else{
                        for(int i=0; i<cant;i++){
                            this.add(1,x,y+i);
                        }
                    }
            }
        }else {
            System.out.println("no se puede pintó");
        }
    }
    /**
     * borra un barco del mapa
     */
    public void borrarBarco(Barco barcoApintar, int x, int y){
        if(!hayUnBarco(barcoApintar,x,y)){
            int max = this.get(1).size()-barcoApintar.getTamaño();
            int cant=barcoApintar.getTamaño();
            switch (barcoApintar.getOrientacion()){ //0 horizontal, 1 vertical
                case 0:
                    if(x>=max){
                        for(int i=max; i<=9;i++){
                            this.add(0,i,y);
                        }
                    }else{
                        for(int i=0; i<cant;i++){
                            this.add(0,x+i,y);
                        }
                    }
                    break;
                case 1:
                    if(y>=max){
                        for(int i=max; i<=9;i++){
                            this.add(0,x,i);
                        }
                    }else{
                        for(int i=0; i<cant;i++){
                            this.add(0,x,y+i);
                        }
                    }
            }

        }
    }

    /**
     * añade un barco al mapa
     */
    public void añadirBarco(Barco barcoApintar, int x, int y) {
        if (!hayUnBarco(barcoApintar, x, y)) {
            barcoApintar.posicionInicial(x,y);
            for (int i = 0; i < barcoApintar.size(); i++) {
                this.add(barcoApintar.get(i),(barcoApintar.getPosicionesX().get(i)),(barcoApintar.getPosicionesY().get(i)));
            }
        } else {
          //  System.out.println("NO SE PUEDE AÑADIR PORQUE HAY UN 2 ");
        }

    }

    /**
     * genera un tablero aleatorio en el mapa
     */
    public void generarTableroAleatorio(ArrayList <Barco> barcos){
        int cantBarcos=0;
        Random random =new Random();
        while(cantBarcos<barcos.size()){
            int x= random.nextInt(cantFilas);
            int y= random.nextInt(cantColumnas);
            int o= random.nextInt(2);
            if(!hayUnBarco(barcos.get(cantBarcos),x,y)){
                for(int i=0; i<=o; i++){
                    barcos.get(cantBarcos).cambiarOrientacion();
                }
                añadirBarco(barcos.get(cantBarcos),x,y);
                cantBarcos++;
            }
        }
        mostrarPorConsola();
    }
}
