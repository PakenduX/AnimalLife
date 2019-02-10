package views;

import controllers.Jeu;
import models.Animal;
import models.Carnivore;

import javax.swing.*;
import java.io.File;
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
        int k = 9;
        int t = 14;
        int x;
        x=(int)(Math.random()*3.1);
        System.out.println("le x est "+x);
        while(true) {


            if(a.getDeplacementVersDroite()){
                System.out.println("droite");
                if (i == 4)
                    i = 1;
                else
                    i++;
                System.out.println("droite");

                animal.setIcon(new ImageIcon(this.getClass().getResource("/images/pas"+i+"_tigre.png")));
            }
            else {
                if (j == 8)
                    j = 5;
                else
                    j++;
                System.out.println("gauche");

                animal.setIcon(new ImageIcon(this.getClass().getResource("/images/pas"+j+"_tigre.png")));

            }




            if(a.estVivant()&& x<=1) {
                if(a.getDeplacementVersHaut()){
                    System.out.println("haut");
                    if(t==16)
                        t=13;
                    else
                        t++;
                    System.out.println("le t vaut "+t);
                    animal.setIcon(new ImageIcon(this.getClass().getResource("/images/pas"+t+"_tigre.png")));

                }
                else{
                    System.out.println("bas");
                    if(k==12)
                        k=9;
                    else
                        k++;
                    System.out.println("le k vaut "+k);
                    animal.setIcon(new ImageIcon(this.getClass().getResource("/images/pas"+k+"_tigre.png")));
                }
                a.seDeplacerHigh(panneau.getWidth(), panneau.getHeight());
                animal.setLocation(a.getPosition().getX(), a.getPosition().getY());
                System.out.println("La position dans le traitment carni est "+ animal.getX() +        " " + animal.getY());
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

