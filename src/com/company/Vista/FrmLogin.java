package com.company.Vista;

import com.company.Clases.Usuario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrmLogin {
    private JPanel PanelLogin;
    private JLabel label;
    private JTextField txtUsuario;
    private JPasswordField txtPass;
    private JButton BtnIngresar;
    private JButton btnCancelar;
    private JPanel JFont;

    public JPanel getRootPanel() {

        return PanelLogin;
        
        
    }
    public FrmLogin(){

        //imgLogin ing = new imgLogin();

        this.setContentPane();


        BtnIngresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String Usuario, Pass;

                Usuario= txtUsuario.getText();
                Pass= txtPass.getText();

                Usuario obj = new Usuario();

                obj.setUsuario(Usuario);
                obj.setPassword(Pass);

                if (obj.getUsuario().isEmpty() || obj.getPassword().isEmpty()){
                    JOptionPane.showMessageDialog( null, "Los campos deben estar llenos");
                }
                else if(obj.getUsuario().equals("adm") && obj.getPassword().equals("123"))
                {
                    JOptionPane.showMessageDialog( null, "Bienvenidos");

                    FrmMenu ui = new FrmMenu();
                    JPanel jp = ui.getRootPanel();
                    JFrame frame = new JFrame();
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.setContentPane(jp);
                    frame.pack();
                    frame.setLocationRelativeTo(null);
                    frame.setVisible(true);

                }
                else {

                    JOptionPane.showMessageDialog( null, "Usuario y contraseña incorrecta");
                    FrmMenu ui = new FrmMenu();
                    JPanel jp = ui.getRootPanel();
                    JFrame frame = new JFrame();
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.setContentPane(jp);
                    frame.pack();
                    frame.setLocationRelativeTo(null);
                    frame.setVisible(false);
                }
            }
        });
    }

    private void setContentPane() {
    }



}
