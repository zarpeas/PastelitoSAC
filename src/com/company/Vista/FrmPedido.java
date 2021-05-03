package com.company.Vista;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.text.Document;
import java.awt.event.*;

public class FrmPedido {
    //public static JTextField txtCliente;
    private JPanel PanlPedido;
    private JButton txtBuscar;
    private JComboBox cmbProducto;
    private JTextField txtPrecio;
    private JTextField txtCantidad;
    private JTextField txtTotal;
    private JButton registrarButton;
    private JButton limpiarButton;
    private JButton button3;
    private JTable TbPedido;
    private JCheckBox ChDelivery;
    public  JTextField txtCliente;

    DefaultTableModel tbmodel = (DefaultTableModel) TbPedido.getModel();
    TableRowSorter<DefaultTableModel> tr;

    public JPanel getRootPanel() {
        return PanlPedido;
    }

    double [] Precio = {0,30.5, 18, 21.5,4,10.5};
    int indicePrecio;
    String cantidadProducto;
    double precioUnitario;

    protected class DocumentListenerCantidad implements DocumentListener{
        public void insertUpdate(DocumentEvent e) {
            indicePrecio = cmbProducto.getSelectedIndex();
            precioUnitario = Precio[indicePrecio];
            cantidadProducto = txtCantidad.getText(); // get cantidad
            int cantidadInt = Integer.parseInt(cantidadProducto); // int cantidad

            //Calcular total
            double total = cantidadInt*precioUnitario;
            txtTotal.setText(String.valueOf(total));
        }

        public void removeUpdate(DocumentEvent e) {
            indicePrecio = cmbProducto.getSelectedIndex();
            precioUnitario = Precio[indicePrecio];
            cantidadProducto = txtCantidad.getText(); // get cantidad
            int cantidadInt;

            if(txtCantidad.getText().isEmpty()){
                    txtTotal.setText(" ");
                    cantidadInt = 0;
            } else {
                cantidadInt = Integer.parseInt(cantidadProducto); // int cantidad
            }

            double total = cantidadInt*precioUnitario;

            txtTotal.setText(String.valueOf(total));
        }

        public void changedUpdate(DocumentEvent e) {
            displayEditInfo(e);
        }
        private void displayEditInfo(DocumentEvent e) {
            Document document = e.getDocument();
            int changeLength = e.getLength();
        }
    }

    public FrmPedido(){

        listar();

        // Agregar DocumentListener a campo de cantidad
        txtCantidad.getDocument().addDocumentListener(new DocumentListenerCantidad());

        cmbProducto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                indicePrecio = cmbProducto.getSelectedIndex(); //get indice combobox
                txtPrecio.setText(String.valueOf(Precio[indicePrecio])); // set precio en lbl
                precioUnitario = Precio[indicePrecio];      // get precio
                cantidadProducto = txtCantidad.getText(); // get cantidad
            }
        });

        txtCantidad.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e){
                indicePrecio = cmbProducto.getSelectedIndex();
                precioUnitario = Precio[indicePrecio];

                //Validar si la cantidad ingresada es un número válido
                char keyChar = e.getKeyChar();
                int keyCode = e.getKeyCode();

                txtCantidad.setEditable(!Character.isLetter(keyChar) && keyCode != 32);
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
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
                row[4] = txtTotal.getText();
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
