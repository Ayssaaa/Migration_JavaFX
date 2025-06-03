/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.List;
import util.FichierMachine;

/**
 *
 * @author Assiya.mdabhi
 */
public class Machine {
    public String refMachine, dMachine, type;
    public float x, y, c; 

    public Machine(String refMachine, String dMachine, String type, float x, float y, float c) {
        this.refMachine = refMachine;
        this.dMachine = dMachine;
        this.type = type;
        this.x = x;
        this.y = y;
        this.c = c;
    }

    public String getRefMachine() {
        return refMachine;
    }

    public void setRefMachine(String refMachine) {
        this.refMachine = refMachine;
    }

    public String getdMachine() {
        return dMachine;
    }

    public void setdMachine(String dMachine) {
        this.dMachine = dMachine;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getC() {
        return c;
    }

    public void setC(float c) {
        this.c = c;
    }
    
    @Override
    public String toString() {
        return "Machine{" +
                "référence='" + refMachine + '\'' +
                "désignation=" + dMachine + '\'' +
                "coût horaire="+c+ '\'' +
                "abscisse="+x+ '\'' +
                "ordonnée="+y+ '\'' +
                '}';
    }
}
    
  
   
