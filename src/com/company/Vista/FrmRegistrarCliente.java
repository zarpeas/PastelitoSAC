package com.company.Vista;

import com.company.Clases.Cliente;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.event.*;
import java.util.ArrayList;

public class FrmRegistrarCliente {
    private JPanel panelPrincipal;
    private JTextField txtNombre;
    private JTextField txtCelular;
    private JTextField txtDireccion;
    private JTextField txtCorreo;
    private JButton btnEditar;
    private JButton btnGuardar;
    private JTable
            TbCliente;
    private JButton bntEliminar;
    private JTextField txtBuscar;
    private JButton btnBuscar;
    private JButton BtnLista;
    ArrayList<Cliente>
            lista = new ArrayList<Cliente>();
    DefaultTableModel tbmodel = (DefaultTableModel) TbCliente.getModel();
    TableRowSorter<DefaultTableModel> tr;

    String Nombre, Telefono, Correo , Direccion;

    public JPanel getRootPanel() {
        return panelPrincipal;
    }

    public FrmRegistrarCliente(){

        listar();


        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {



                //Arreglo
                final Object[] row = new Object[5];
                row[0] = txtNombre.getText();
                row[1] = txtCelular.getText();
                row[2] = txtCorreo.getText();
                row[3] = txtDireccion.getText();


               /* if (txtNombre.getText().isEmpty() || txtApellido.getText().isEmpty()|| txtDireccion.getText().isEmpty()|| txtCorreo.getText().isEmpty()|| txtFecha.getText().isEmpty()){
                    JOptionPane.showMessageDialog( null, "Los campos deden estar llenos");
                }
                else{
                    final Object[] row = new Object[5];
                    row[0] = txtNombre.getText();
                    row[1] = txtApellido.getText();
                    row[2] = txtDireccion.getText();
                    row[3] = txtCorreo.getText();
                    row[4] = txtFecha.getText();

                    //obj.setNombres((String) row[0]);

                    tbmodel.addRow(row);

                    Limpiar();
                    JOptionPane.showMessageDialog( null, "Se registro correctamente");

                }
                //Arreglo*/


                if (txtNombre.getText().length()==0 || txtCelular.getText().length()==0 || txtCorreo.getText().length()==0 || txtDireccion.getText().length()==0){
                    JOptionPane.showMessageDialog( null, "Debe llenar los datos solicitados");
                }
                else {
                    tbmodel.addRow(row);

                    JOptionPane.showMessageDialog( null, "Se registro correctamente");
                    Limpiar();
                }



                //fila[5]=txtCorreo.getSelectedItem().toString();

                //String dato[] = {txtNombre.getText(),txtApellido.getText(),txtDireccion.getText(),txtCorreo.getText(),txtFecha.getText()};

                //tbmodel.addRow(dato);

                //TbCliente.setModel(tbmodel);




                /*Cliente obj = new Cliente();

                Nombre = txtNombre.getText();
                Telefono = txtCelular.getText();
                Direccion= txtDireccion.getText();
                Correo= txtCorreo.getText();

                //new Personal[]{obj}
                //Cliente obj = new Cliente();
                        //{obj.setNombres(Nombre),obj.setApellidos(Apellido),obj.setDireccion(Direccion),obj.setCorreo(Correo),obj.setFechaNacimiento(Fecha)}

                obj.setNombres(Nombre);
                obj.setCorreo(Telefono);
                obj.setDireccion(Direccion);
                obj.setCorreo(Correo);

                if (obj.getNombres().isEmpty() || obj.getCorreo().isEmpty()|| obj.getDireccion().isEmpty()|| obj.getCorreo().isEmpty()) {
                    JOptionPane.showMessageDialog( null, "Los campos deden estar llenos");
                }
                else if (obj.equals(null)){
                    JOptionPane.showMessageDialog( null, "Error registrar");
                }else{
                    JOptionPane.showMessageDialog( null, "Se registro correctamente");
                    lista.add(obj);
                    listar();
                    Limpiar();
                }*/

                //listar();

            }
        });

        btnEditar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editar();

            }
        });

        //TbCliente.addMouseListener(new MouseEvent());

        DefaultTableModel model = (DefaultTableModel) TbCliente.getModel();

        TbCliente.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {

                int i = TbCliente.getSelectedRow();
                txtNombre.setText(TbCliente.getValueAt(i,0).toString());
//                txtApellido.setText(TbCliente.getValueAt(i,1).toString());
                txtCelular.setText(TbCliente.getValueAt(i,1).toString());
                txtCorreo.setText(TbCliente.getValueAt(i,2).toString());
                txtDireccion.setText(TbCliente.getValueAt(i,3).toString());
            }
        });

        bntEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int dto = TbCliente.getSelectedRow();
                if (dto>=0){
                    tbmodel.removeRow(dto);
                    JOptionPane.showMessageDialog( null, "Se elimino correctamente"+ dto);
                }else {
                    JOptionPane.showMessageDialog( null, "Error al eliminar");
                }
            }
        });



        txtBuscar.addKeyListener(new KeyAdapter() {

            @Override
            public void keyPressed(KeyEvent e) {
                //JOptionPane.showMessageDialog( null, "Demo");
                //tr.setRowFilter(RowFilter.regexFilter(txtBuscar.getText(),1));
                //tr= new TableRowSorter<>(tbmodel);
                //TbCliente.setRowSorter(tr);

            }
        });
        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Buscar();
            }
        });
        BtnLista.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pasarData();

            }
        });
    }

    public void Limpiar(){
        txtNombre.setText("");
//        txtApellido.setText("");
        txtDireccion.setText("");
        txtCorreo.setText("");
        txtCelular.setText("");

        txtNombre.requestFocus();
    }

    public void registrar(){

    }

    public void listar(){

        Object[] column = {"Nombre","Celular","Correo","Dirección"};
        Object[] row =new Object[0];
        tbmodel.setColumnIdentifiers(column);
        TbCliente.setModel(tbmodel);
        TbCliente.setAutoCreateRowSorter(true);
        tr = new  TableRowSorter<>(tbmodel);
        TbCliente.setRowSorter(tr);
        /*String matris [][] = new String[lista.size()][5];

        for (int i=0; i<lista.size(); i++){
            matris[i][0]=lista.get(i).getNombres();
            matris[i][1]=lista.get(i).getApellidos();
            matris[i][2]=lista.get(i).getFechaNacimiento();
            matris[i][3]=lista.get(i).getDireccion();
            matris[i][4]=lista.get(i).getCorreo();
        }

        TbCliente.setModel(new DefaultTableModel(
                matris,
                new String []{"Nombre","Apellido","Dirección","Correo","Fecha de Nacimiento"}
        ));*/
    }

    public void editar(){

        int dto = TbCliente.getSelectedRow();

        if ( dto >=0){

            TbCliente.setValueAt(txtNombre.getText(), dto,0);
//            TbCliente.setValueAt(txtApellido.getText(), dto,1);
            TbCliente.setValueAt(txtCelular.getText(), dto,1);
            TbCliente.setValueAt(txtCorreo.getText(), dto,2);
            TbCliente.setValueAt(txtDireccion.getText(), dto,3);

            JOptionPane.showMessageDialog( null, "Se Actulizo correctamente");

        }else {
            JOptionPane.showMessageDialog( null, "Error al Actulizar");
        }

    }
    public void Buscar(){

        try {
            tr.setRowFilter(RowFilter.regexFilter(txtBuscar.getText()));
        } catch (Exception e) {
            JOptionPane.showMessageDialog( null, "Error al Buscar");
        }

    }

    public void pasarData(){

        FrmListaCliente ui = new FrmListaCliente();
        JPanel jp = ui.getRootPanel();
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(jp);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        DefaultTableModel model=(DefaultTableModel)ui.TbListaCliente.getModel();

        int rows = TbCliente.getRowCount();
        for (int i=0; i<rows; i++) {
            final Object[] row = new Object[4];
            row[0]=TbCliente.getValueAt(i,0).toString();
            row[1]=TbCliente.getValueAt(i,1).toString();
            row[2]=TbCliente.getValueAt(i,2).toString();
            row[3]=TbCliente.getValueAt(i,3).toString();
            model.addRow(row);
        }
        ui.TbListaCliente.setModel(model);

    }


}
