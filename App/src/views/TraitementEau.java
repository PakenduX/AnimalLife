package views;

import models.Eau;


import javax.swing.*;

public class TraitementEau implements Runnable {
    private JLabel eau;
    private Panneau_Jungle panneau;

    public TraitementEau(JLabel eau, Panneau_Jungle panneau){
        this.eau = eau;
        this.panneau = panneau;
    }

    public void run() {

        while(true) {
            if(Eau.isEau_is_suffisant()) {
                eau.setIcon(new ImageIcon("/home/pkss/AnimalLife/App/images/eau11.png"));
                eau.setLocation(Eau.getPosition().getX(), Eau.getPosition().getY());
            }else
                eau.setLocation(-100,-100);

            panneau.repaint();

        }
    }


}
