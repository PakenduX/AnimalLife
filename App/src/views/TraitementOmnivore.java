package views;

import models.Animal;
import models.Omnivore;

import javax.swing.*;

/**
 * Cette classe permet de lancer le traitement
 * pour un omnivore (deplacement, mort ).
 */

public class TraitementOmnivore implements Runnable {
    private Animal<?> a;
    private JLabel animal;
    private Panneau_Jungle panneau;

    public TraitementOmnivore(Omnivore a, JLabel animal, Panneau_Jungle panneau) {
        this.animal = animal;
        this.panneau = panneau;
        this.a = a;
    }

    /**
     * ON change les images en fonction du sens de déplacement
     * de l'animal (droite, gauche, bas, haut).
     */
    public void run() {
        int k = 2;
        int t = 6;
        int j = 9;
        int i = 14;
        int x;
        x = (int)(Math.random()*3.1);
        while(true) {

            if(a.getDeplacementVersDroite()){
                if (i == 16)
                    i = 13;
                else
                    i++;

                animal.setIcon(new ImageIcon(this.getClass().getResource("/images/pas"+i+"_ours.png")));
            }
            else {
                if (j == 12)
                    j = 9;
                else
                    j++;

                animal.setIcon(new ImageIcon(this.getClass().getResource("/images/pas"+j+"_ours.png")));

            }

            if(a.estVivant() && x <= 1) {
                if(a.getDeplacementVersHaut()){
                    if(t==8)
                        t=5;
                    else
                        t++;
                    animal.setIcon(new ImageIcon(this.getClass().getResource("/images/pas"+t+"_ours.png")));

                }
                else{
                    if(k==4)
                        k=1;
                    else
                        k++;
                    animal.setIcon(new ImageIcon(this.getClass().getResource("/images/pas"+k+"_ours.png")));
                }
                a.seDeplacerHigh(panneau.getWidth(), panneau.getHeight());
                animal.setLocation(a.getPosition().getX(), a.getPosition().getY());
            } if(a.estVivant()&&x>1&&x<=2){
                a.seDeplacerLeft(panneau.getWidth(), panneau.getHeight());
                animal.setLocation(a.getPosition().getX(), a.getPosition().getY());
            }
            if(a.estVivant()&&x>2&&x<=3){
                a.seDeplacerRight(panneau.getWidth(), panneau.getHeight());
                animal.setLocation(a.getPosition().getX(), a.getPosition().getY());
            }

            if(!a.estVivant())
                animal.setLocation(-100, -100);

            panneau.repaint();

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


}


