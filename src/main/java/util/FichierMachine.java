/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import model.Machine;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class FichierMachine {

    private static final String CHEMIN = "machines.json";

    public static boolean ajouterMachine(Machine machine) {
        List<Machine> machines = lireMachines();
        for (Machine m : machines) {
            if (m.getRefMachine().equalsIgnoreCase(machine.getRefMachine())) {
                return false; // Machine déjà existante
            }
        }
        machines.add(machine);
        sauvegarderListe(machines);
        return true;
    }

    public static List<Machine> lireMachines() {
        try (Reader reader = new FileReader(CHEMIN)) {
            Type listType = new TypeToken<List<Machine>>() {}.getType();
            List<Machine> machines = new Gson().fromJson(reader, listType);
            return machines != null ? machines : new ArrayList<>();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    public static boolean supprimerMachine(String refMachine) {
        List<Machine> machines = lireMachines();
        boolean removed = machines.removeIf(m -> m.getRefMachine().equalsIgnoreCase(refMachine));
        if (removed) {
            sauvegarderListe(machines);
        }
        return removed;
    }

    public static boolean modifierMachine(String refMachine, String nouvelleDesignation, String nouveauType,
                                           float nouveauX, float nouveauY, float nouveauC) {
        List<Machine> machines = lireMachines();
        for (Machine m : machines) {
            if (m.getRefMachine().equalsIgnoreCase(refMachine)) {
                m.setdMachine(nouvelleDesignation);
                m.setType(nouveauType);
                m.setX(nouveauX);
                m.setY(nouveauY);
                m.setC(nouveauC);
                sauvegarderListe(machines);
                return true;
            }
        }
        return false;
    }

    private static void sauvegarderListe(List<Machine> machines) {
        try (FileWriter writer = new FileWriter(CHEMIN)) {
            new Gson().toJson(machines, writer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
