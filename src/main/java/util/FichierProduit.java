package util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import model.Produit;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FichierProduit {

    private static final String CHEMIN = "produits.json";

    public static boolean ajouterProduit(Produit produit) {
        List<Produit> produits = lireProduits();
        for (Produit p : produits) {
            if (p.getNom().equalsIgnoreCase(produit.getNom())) {
                return false; // Produit déjà existant
            }
        }
        produits.add(produit);
        sauvegarderListe(produits);
        return true;
    }

    public static List<Produit> lireProduits() {
        try (Reader reader = new FileReader(CHEMIN)) {
            Type listType = new TypeToken<List<Produit>>() {}.getType();
            List<Produit> produits = new Gson().fromJson(reader, listType);
            return produits != null ? produits : new ArrayList<>();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    public static boolean supprimerProduit(String nom) {
        List<Produit> produits = lireProduits();
        boolean removed = produits.removeIf(p -> p.getNom().equalsIgnoreCase(nom));
        if (removed) {
            sauvegarderListe(produits);
        }
        return removed;
    }

    public static boolean modifierProduit(String ancienNom, String nouveauNom, double nouveauPrix) {
        List<Produit> produits = lireProduits();
        for (Produit p : produits) {
            if (p.getNom().equalsIgnoreCase(ancienNom)) {
                p.setNom(nouveauNom);
                p.setPrix(nouveauPrix);
                sauvegarderListe(produits);
                return true;
            }
        }
        return false;
    }

    private static void sauvegarderListe(List<Produit> produits) {
        try (FileWriter writer = new FileWriter(CHEMIN)) {
            new Gson().toJson(produits, writer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
