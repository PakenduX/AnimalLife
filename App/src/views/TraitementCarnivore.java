package views;

import controllers.Jeu;
import models.Animal;
import models.Carnivore;

import javax.swing.*;
import java.io.IOException;

/**
 * Cette classe permet de lancer le traitement
 * pour un carnivore (deplacement, mort ).
 */

public class TraitementCarnivore implements Runnable {
    private Animal<?> a;
    private JLabel animal;
    private Panneau_Jungle panneau;

    public TraitementCarnivore(Carnivore a, JLabel animal, Panneau_Jungle panneau){
        this.animal = animal;
        this.panneau = panneau;
        this.a = a;
    }

    public void run() {
        int i = 2;
        int j = 6;
        while(true) {
            if(a.getDeplacementVersDroite()){
                if (i == 4)
                    i = 1;
                else
                    i++;

                animal.setIcon(new ImageIcon("/home/pkss/AnimalLife/App/images/pas" + i + "_tigre.png"));

            }else{
                if (j == 8)
                    j = 5;
                else
                    j++;

                animal.setIcon(new ImageIcon("/home/pkss/AnimalLife/App/images/pas" + j + "_tigre.png"));

            }
            if(a.estVivant()) {
                a.seDeplacer(panneau.getWidth(), panneau.getHeight());
                animal.setLocation(a.getPosition().getX(), a.getPosition().getY());
                System.out.println("La position dans le traitment carni est "+ animal.getX() + " " + animal.getY());
            }else
                animal.setLocation(-100, -100);

            panneau.repaint();

            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


}

