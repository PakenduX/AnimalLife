package views;

import controllers.Jeu;
import models.Animal;
import models.Carnivore;
import models.Herbivore;
import models.Omnivore;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * La classe qui permet de lancer la première fenetre de
 * l'application pour saisir les données du jeu.
 * @author Équipe Jungle
 *
 */
public class Options extends JFrame implements ActionListener {

    private static int nbCarn, qHerbN, qHerbE, nbOmn, nbHerbv;

    JTextField nbCarn_field, nbHerbv_field, nbOmn_field, qHerbN_field, qHerbE_field;
    private Panneau_option panneau_option;

    public Options(){
        this.setTitle("Choisir");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panneau_option=new Panneau_option();
        setBounds(0, 0, Toolkit.getDefaultToolkit().getScreenSize().width, Toolkit.getDefaultToolkit().getScreenSize().height);
        setResizable(false);
        panneau_option.setLayout(null);

        JLabel titre = new JLabel("Saisissez les options et " +
                "cliquez sur lancer pour lancer le jeu");

        //Les labels
        JLabel nbCarn_label = new JLabel("Nombre de carnivore :");
        JLabel nbHerbv_label = new JLabel("Nombre d'herbivore :");
        JLabel nbOmn_label = new JLabel("Nombre d'omnivore :");
        JLabel qHerbN_label = new JLabel("Qtité d'herbe normale :");
        JLabel qHerbE_label = new JLabel("Qtité d'herbe empoisonnée:");

        //Les champs de saisie
        nbCarn_field = new JTextField();
        nbHerbv_field = new JTextField();
        nbOmn_field = new JTextField();
        qHerbN_field = new JTextField();
        qHerbE_field = new JTextField();

        //Bouton lancer
        JButton lancer = new JButton("Lancer");

        //Positionnement
        Color c = Color.CYAN;
        Font f = new Font("Arial", Font.BOLD, 18);
        titre.setFont(new Font("Arial", Font.BOLD, 30));
        titre.setForeground(c);
        titre.setBounds(500, 250, 1700, 100);
        nbCarn_label.setFont(f);
        nbCarn_label.setForeground(c);
        nbCarn_label.setBounds(600, 400, 230, 30);
        nbCarn_field.setBounds(900, 400, 110, 30);
        nbHerbv_label.setFont(f);
        nbHerbv_label.setForeground(c);
        nbHerbv_label.setBounds(600, 450, 230, 30);
        nbHerbv_field.setBounds(900, 450, 110, 30);
        nbOmn_label.setFont(f);
        nbOmn_label.setForeground(c);
        nbOmn_label.setBounds(600, 500, 230, 30);
        nbOmn_field.setBounds(900, 500, 110, 30);
        qHerbN_label.setFont(f);
        qHerbN_label.setForeground(c);
        qHerbN_label.setBounds(600, 550, 230, 30);
        qHerbN_field.setBounds(900, 550, 110, 30);
        qHerbE_label.setFont(f);
        qHerbE_label.setForeground(c);
        qHerbE_label.setBounds(600, 600, 230, 30);
        qHerbE_field.setBounds(900, 600, 110, 30);

        lancer.setBounds(800, 750, 300, 30);

        panneau_option.add(titre);
        panneau_option.add(nbCarn_label);
        panneau_option.add(nbCarn_field);
        panneau_option.add(nbHerbv_label);
        panneau_option.add(nbHerbv_field);
        panneau_option.add(nbOmn_label);
        panneau_option.add(nbOmn_field);
        panneau_option.add(qHerbN_label);
        panneau_option.add(qHerbN_field);
        panneau_option.add(qHerbE_label);
        panneau_option.add(qHerbE_field);
        panneau_option.add(lancer);
        this.setContentPane(panneau_option);

        lancer.addActionListener(this);
        setVisible(true);

    }

    public static int getNbCarn() {
        return nbCarn;
    }

    public static int getQHerbN() {
        return qHerbN;
    }

    public static int getQHerbE() {
        return qHerbE;
    }

    public static int getNbOmn() {
        return nbOmn;
    }

    public static int getNbHerbv() {
        return nbHerbv;
    }

    public static void main(String[] args){

        new Options().setVisible(true);
    }


    public void actionPerformed(ActionEvent event) {
        try{
            nbCarn = Integer.parseInt(nbCarn_field.getText());
            nbHerbv = Integer.parseInt(nbHerbv_field.getText());
            qHerbN = Integer.parseInt(qHerbN_field.getText());
            qHerbE = Integer.parseInt(qHerbE_field.getText());
            nbOmn = Integer.parseInt(nbOmn_field.getText());
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            new Jeu().lancer();

        }catch (NumberFormatException e){
            JOptionPane.showMessageDialog(this, "Veuillez saisir une valeur entière svp", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }
}
