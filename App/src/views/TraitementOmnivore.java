package views;

import controllers.Jeu;
import models.Animal;
import models.Carnivore;
import models.Omnivore;

import javax.swing.*;
import java.io.IOException;

/**
 * Cette classe permet de lancer le traitement
 * pour un omnivore (deplacement, mort ).
 */

public class TraitementOmnivore implements Runnable {
    private Animal<?> a;
    private JLabel animal;
    private Panneau_Jungle panneau;

    public TraitementOmnivore(Omnivore a, JLabel animal, Panneau_Jungle panneau){
        this.animal = animal;
        this.panneau = panneau;
        this.a = a;
    }

    public void run() {
        int i = 2;
        int j = 10;
        while(true) {
            if(a.getDeplacementVersBas()){
                if (i == 4)
                    i = 1;
                else
                    i++;

                animal.setIcon(new ImageIcon("/home/pkss/AnimalLife/App/images/pas" + i + "_ours.png"));

            }else{
                if (j == 12)
                    j = 9;
                else
                    j++;

                animal.setIcon(new ImageIcon("/home/pkss/AnimalLife/App/images/pas" + j + "_ours.png"));

            }
            if(a.estVivant()) {
                a.seDeplacer(panneau.getWidth(), panneau.getHeight());
                animal.setLocation(a.getPosition().getX(), a.getPosition().getY());
                System.out.println("La position dans le traitment omni est "+ animal.getX() + " " + animal.getY());
                /*try {
                    Jeu.traiter_collisions(a);
                } catch (IOException e) {
                    e.printStackTrace();
                }*/
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

