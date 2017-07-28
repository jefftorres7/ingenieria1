/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

/**
 *
 * @author Jefferson
 */
public class MetodosGenerales {
    // metodo para validacion de cedulas

    public boolean ValidaCedula(String ced) {
        int i = 0, r = 0, sep = 0, sei = 0;
        if (ced.length()!= 9) {
            return false;
        }else{
            for (i = 0; i < ced.length(); i += 2)// funcion para scar tamañao de una string
            {
                r = Integer.parseInt(ced.charAt(i) + "");// doble castin
                r = r * 2;
                if (r > 9) {
                    r -= 9;
                }
                sep = sep + r;
            }
            for (i = 1; i < ced.length() - 1; i += 2)// funcion para scar tamañao de una matriz
            {
                r = Integer.parseInt(ced.charAt(i) + "");// doble castin y saber en que posicion del string charAt();
                sei = sei + r;
            }
            r = 10 - ((sep + sei) % 10);
            if (r == 10) {
                r = 0;
            }
        }
        return (r == Integer.parseInt(ced.charAt(9) + "")) ? true : false;
    }
}
