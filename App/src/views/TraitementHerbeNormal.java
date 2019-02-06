package views;

import models.Animal;
import models.Carnivore;
import models.Herbe;

import javax.swing.*;

public class TraitementHerbeNormal implements Runnable {
    private Herbe a;
    private JLabel herbe;
    private Panneau_Jungle panneau;

    public TraitementHerbeNormal(Herbe a, JLabel herbe, Panneau_Jungle panneau){
        this.herbe = herbe;
        this.panneau = panneau;
        this.a = a;
    }


    public void run() {

        while(true) {
            if(a.isHerbe_est_vivant())
               herbe.setIcon(new ImageIcon("/home/pkss/AnimalLife/App/images/herbe_normal.png"));
            else
                herbe.setLocation(-100,-100);
            panneau.repaint();


        }
    }


}
