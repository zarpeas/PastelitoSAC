package com.company.Vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.event.*;

public class FrmPedido {


    //public static JTextField txtCliente;
    private JPanel PanlPedido;
    private JButton txtBuscar;
    private JComboBox cmbProducto;
    private JTextField txtPrecio;
    private JTextField txtCantidad;
    private JTextField txtPrecioTotal;
    private JButton registrarButton;
    private JTable TbPedido;
    private JCheckBox ChDelivery;
    public  JTextField txtCliente;

    DefaultTableModel tbmodel = (DefaultTableModel) TbPedido.getModel();
    TableRowSorter<DefaultTableModel>
            tr;

    public JPanel getRootPanel() {
        return PanlPedido;
    }

    double [] Precio = {0,30.5, 18, 21.5,4,10.5};
    int IndPrecio;
    String cantidadprod; // get cantidad
    double preciounit;

    public FrmPedido(){

        listar();

        cmbProducto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //double [] Precio = {0,30.5, 18, 21.5,4,10.5};
                IndPrecio = cmbProducto.getSelectedIndex(); //get indice combobox
                txtPrecio.setText(String.valueOf(Precio[IndPrecio])); // set precio en lbl
                preciounit = Precio[IndPrecio];      // get precio
                cantidadprod = txtCantidad.getText(); // get cantidad


            }
        });


        txtCantidad.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {

                IndPrecio = cmbProducto.getSelectedIndex();
                preciounit = Precio[IndPrecio];
                cantidadprod = txtCantidad.getText();
                if (cantidadprod.isEmpty()){
                    txtPrecioTotal.setText(String.valueOf(0));
                    //JOptionPane.showMessageDialog( null, "Por favor, ingresar una cantidad de productos");
                    //txtCantidad.requestFocus();
                } else{
                    int cantidadInt;

                    cantidadInt = Integer.parseInt(cantidadprod); // int cantidad
                    double preciofinal = cantidadInt*preciounit;
                    txtPrecioTotal.setText(String.valueOf(preciofinal));
                }


            }
        });

        registrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                final Object[] row = new Object[6];
                row[0] = txtCliente.getText();
                row[1] = cmbProducto.getSelectedItem().toString();
                row[2] = txtPrecio.getText();
                row[3] = txtCantidad.getText();
                row[4] = txtPrecioTotal.getText();
                row[5] = ChDelivery.isSelected();

                System.out.println("New Pedido"+row);

                String mjs;

                if (ChDelivery.isSelected()==true){

                    mjs="Si";
                }else{
                    mjs="No";
                }

                tbmodel.addRow(row);

                listar();

            }
        });
        txtBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FrmListaCliente ui = new FrmListaCliente();
                JPanel jp = ui.getRootPanel();
                JFrame frame = new JFrame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setContentPane(jp);
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
        TbPedido.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }
        });
    }

    public void listar(){
        Object[] column = {"Cliente","Producto","Precio","Cantidad","PrecioTotal","Delivery"};
        Object[] row = new Object[0];
        tbmodel.setColumnIdentifiers(column);
        TbPedido.setModel(tbmodel);
        TbPedido.setAutoCreateRowSorter(true);
        tr = new  TableRowSorter<>(tbmodel);
        TbPedido.setRowSorter(tr);

    }


}
