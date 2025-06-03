/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import model.Machine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FichierMachine {
    private static final List<Machine> listeMachines = new ArrayList<>();

    public static boolean ajouterMachine(Machine m) {
        for (Machine existante : listeMachines) {
            if (existante.getRefMachine().equalsIgnoreCase(m.getRefMachine())) {
                return false;
            }
        }
        listeMachines.add(m);
        return true;
    }

    public static List<Machine> getListeMachines() {
        return Collections.unmodifiableList(listeMachines);
    }

    public static void clearListeMachines() {
        listeMachines.clear();
    }
}
