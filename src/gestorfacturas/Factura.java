/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestorfacturas;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Yosep
 */
public class Factura extends javax.swing.JFrame {

    public Connection con;
    Statement st;
    PreparedStatement ps;
    CallableStatement cs;
    ResultSet rs;
    ResultSet rst;
    DatabaseMetaData metadata;
    ResultSetMetaData rsmetadata;
    private DefaultTableModel facturas;
    public int codcliente;
    public int factura;
    public boolean editable;
    
    /**
     * Creates new form Factura
     */
    public Factura() {
        initComponents();
        this.facturas = (DefaultTableModel) facturasTB.getModel();
        Connection();
        vistaFacturas();
        
        
    }

    public void Connection(){
        
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
            con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","FACTURAS","FACTURAS");
            

        }catch(Exception se){
            se.printStackTrace();
        }  
        
    }
    
    public void vistaFacturas(){
        
        try {
            
            st = con.createStatement();
            rs = st.executeQuery("SELECT * FROM view_fact_clien");
            
            rsmetadata = rs.getMetaData();
            //obtenemos el numero de columnas
            int columnCount = rsmetadata.getColumnCount();
            
            //creamos un array donde introduciremos los numbres de las columnas
            String[] columnNames = new String[columnCount];
            
            
            for(int i = 0; i < columnCount; i++){
                //recorremos el resultsetmetadata para introducir en el array los nombres de las columnas
                columnNames[i] = rsmetadata.getColumnName(i+1);
                
            }
            
            for(int x = 0; x < columnNames.length; x++){
                //recorremos el array para añadir el nombre ya obtenido de las columnas a nuestra tabla
                facturas.addColumn(columnNames[x]);
                
            }
            
            
            while (rs.next()){
                
                Object [] fila = new Object[columnCount]; // Hay tres columnas en la tabla

                // Se rellena cada posición del array con el valor de las columnas de la tabla. 
                for (int z = 0; z < columnCount; z++)

                    fila[z] = rs.getObject(z+1); // El primer indice en rs es el 1, no el cero, por eso se suma 1.

                // Se añade al modelo la fila completa.
                facturas.addRow(fila); 
                
            }
            
            rs.close();
 
        } catch (SQLException ex) {
            Logger.getLogger(Factura.class.getName()).log(Level.SEVERE, null, ex);
        }
         
    }
    
    public void refrescarInfo(){
        
        facturas.setRowCount(0);
        
        try{
            
            st = con.createStatement();
            rs = st.executeQuery("SELECT * FROM view_fact_clien");
            rsmetadata = rs.getMetaData();
            //obtenemos el numero de columnas
            int columnCount = rsmetadata.getColumnCount();
            
            while (rs.next()){
                
                Object [] fila = new Object[columnCount]; // Hay tres columnas en la tabla

                // Se rellena cada posición del array con el valor de las columnas de la tabla. 
                for (int z = 0; z < columnCount; z++)

                    fila[z] = rs.getObject(z+1); // El primer indice en rs es el 1, no el cero, por eso se suma 1.

                // Se añade al modelo la fila completa.
                facturas.addRow(fila); 
                
            }
            
            rs.close();
            
        }catch (SQLException ex) {
            ex.printStackTrace();
        }
        
    }
    
    public void facturasClien(){
        
        clientesCB.removeAllItems();

        try { 
                                   
            st = con.createStatement();
            rs = st.executeQuery("SELECT DISTINCT (NOMBRE)  FROM view_fact_clien");
            
            while(rs.next()){
                
                clientesCB.addItem(rs.getString("Nombre"));
                
            }
            
            

            rs.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        facturasTB = new javax.swing.JTable();
        addBT = new javax.swing.JButton();
        deleteBT = new javax.swing.JButton();
        editBT = new javax.swing.JButton();
        filterBT = new javax.swing.JButton();
        notFilterBT = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        clientesCB = new javax.swing.JComboBox<>();
        nFacturaTF = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        facturasTB.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        facturasTB.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                facturasTBMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(facturasTB);

        addBT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/add_2_opt.png"))); // NOI18N
        addBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addBTActionPerformed(evt);
            }
        });

        deleteBT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/minus_opt.png"))); // NOI18N
        deleteBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteBTActionPerformed(evt);
            }
        });

        editBT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edit_opt.png"))); // NOI18N
        editBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editBTActionPerformed(evt);
            }
        });

        filterBT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/filter_opt.png"))); // NOI18N
        filterBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                filterBTActionPerformed(evt);
            }
        });

        notFilterBT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/filter_delete_opt.png"))); // NOI18N
        notFilterBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                notFilterBTActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Consolas", 1, 18)); // NOI18N
        jLabel1.setText("Nº Factura");

        jLabel2.setFont(new java.awt.Font("Consolas", 1, 18)); // NOI18N
        jLabel2.setText("Cliente");

        clientesCB.setFont(new java.awt.Font("Consolas", 1, 18)); // NOI18N

        nFacturaTF.setFont(new java.awt.Font("Consolas", 1, 18)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(addBT, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(deleteBT, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(editBT, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(filterBT, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(notFilterBT, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(24, 24, 24)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(nFacturaTF, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(clientesCB, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 38, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 576, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(editBT, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(filterBT, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(notFilterBT, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(deleteBT, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(addBT, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(83, 83, 83)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(nFacturaTF, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(62, 62, 62)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(clientesCB, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void facturasTBMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_facturasTBMouseClicked
        // TODO add your handling code here:
        
        int selectedRow = facturasTB.getSelectedRow();
        nFacturaTF.setText(facturas.getValueAt(selectedRow,0).toString());
        facturasClien();
        
    }//GEN-LAST:event_facturasTBMouseClicked

    private void deleteBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteBTActionPerformed
        // TODO add your handling code here:
        
        try{
            
            int selectedRow = facturasTB.getSelectedRow();
        
            String pagada = facturasTB.getValueAt(selectedRow, 4).toString();
        
        
        
            if(pagada.equals("S")){

                JOptionPane.showMessageDialog(null, "La factura esta pagada, por lo tanto no se puede borrar");

            }else{

                    int nfac = Integer.parseInt(facturasTB.getValueAt(selectedRow, 0).toString());

                    st = con.createStatement();
                    rs = st.executeQuery("DELETE FROM FACTURAS WHERE NFACTURA = "+nfac);

                    rs.close();

                    refrescarInfo();

                }
            
        }catch (SQLException ex) {
                Logger.getLogger(Factura.class.getName()).log(Level.SEVERE, null, ex);
            }catch(ArrayIndexOutOfBoundsException e){
                
                JOptionPane.showMessageDialog(null, "Seleccione la fila que desee borrar");
                return;
            } 
    }//GEN-LAST:event_deleteBTActionPerformed

    private void filterBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_filterBTActionPerformed
        // TODO add your handling code here:

        try{
            
            facturas.setRowCount(0);
        
            String nfactura = nFacturaTF.getText();
            String cliente = clientesCB.getSelectedItem().toString();
            
            st = con.createStatement();
            rs = st.executeQuery("SELECT * FROM view_fact_clien WHERE NFACTURA = "+nfactura+" OR NOMBRE LIKE '"+cliente+"'");

            rsmetadata = rs.getMetaData();
            //obtenemos el numero de columnas
            int columnCount = rsmetadata.getColumnCount();
        
        
            
            while (rs.next()){
                
                Object [] fila = new Object[columnCount]; // Hay tres columnas en la tabla

                // Se rellena cada posición del array con el valor de las columnas de la tabla. 
                for (int z = 0; z < columnCount; z++)

                    fila[z] = rs.getObject(z+1); // El primer indice en rs es el 1, no el cero, por eso se suma 1.

                // Se añade al modelo la fila completa.
                facturas.addRow(fila); 
                
            }
            
            rs.close();
            
        }catch (SQLException ex) {
            ex.printStackTrace();
        }catch(NullPointerException e){
            
            JOptionPane.showMessageDialog(null, "No hay datos para realizar el filtrado");
            refrescarInfo();
            return;
        }
        
        
    }//GEN-LAST:event_filterBTActionPerformed

    private void notFilterBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_notFilterBTActionPerformed
        // TODO add your handling code here:
        
        refrescarInfo();
    }//GEN-LAST:event_notFilterBTActionPerformed

    private void addBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addBTActionPerformed
        // TODO add your handling code here:
        
        try{
            
            String cliente = clientesCB.getSelectedItem().toString();
            factura = Integer.parseInt(nFacturaTF.getText());
            editable = false;
            
            st = con.createStatement();
            rst = st.executeQuery("SELECT CODIGO FROM CLIENTES WHERE NOMBRE LIKE '"+cliente+"'");
            st = con.createStatement();
            rs = st.executeQuery("SELECT NFACTURA FROM view_fact_clien WHERE NFACTURA = "+factura);

            while(rst.next()){
                
                codcliente = rst.getInt(1);
                
            }
            
            rst.close();
            
            if (rs.next() == true) { 
                
                JOptionPane.showMessageDialog(null, "La factura ya existe o seleccione los datos correctaente");
            
            }else{
                
                new DetalleFactura(con,codcliente,factura,editable).setVisible(true);
                
            } 
            
            rs.close();
            
        }catch (SQLException ex) {
            ex.printStackTrace();
        }catch(NullPointerException e){
            
            JOptionPane.showMessageDialog(null, "La factura ya existe o seleccione los datos correctaente");
            return;
        }
        
    }//GEN-LAST:event_addBTActionPerformed

    private void editBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editBTActionPerformed
        // TODO add your handling code here:
        
        try{
            
            String cliente = clientesCB.getSelectedItem().toString();
            factura = Integer.parseInt(nFacturaTF.getText());
            editable = true;
            
            st = con.createStatement();
            rst = st.executeQuery("SELECT CODIGO FROM CLIENTES WHERE NOMBRE LIKE '"+cliente+"'");
            st = con.createStatement();
            rs = st.executeQuery("SELECT NFACTURA FROM view_fact_clien WHERE NFACTURA = "+factura);

            while(rst.next()){
                
                codcliente = rst.getInt(1);
                
            }
            
            rst.close();
            
            
            int selectedRow = facturasTB.getSelectedRow();
            String datoFila = "";
            //Si existe una fila seleccionada enonces:
            if(selectedRow>=0){
                
                //aqui recogemos el valor de dicho valor en la fila seleccionada y columna 0
                datoFila = facturas.getValueAt(selectedRow, 4).toString();  
            }
            
            if(datoFila.equals("S")){
                
                JOptionPane.showMessageDialog(null, "No se puede editar una factura que esta pagada");
                
            }else{
                
                new DetalleFactura(con,codcliente,factura,editable).setVisible(true);
                
            }

            rs.close();
            
        }catch (SQLException ex) {
            ex.printStackTrace();
        }catch(NullPointerException e){
            
            JOptionPane.showMessageDialog(null, "Seleccione los datos correctaente");
            return;
        }
        
    }//GEN-LAST:event_editBTActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Factura.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Factura.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Factura.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Factura.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Factura().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addBT;
    private javax.swing.JComboBox<String> clientesCB;
    private javax.swing.JButton deleteBT;
    private javax.swing.JButton editBT;
    private javax.swing.JTable facturasTB;
    private javax.swing.JButton filterBT;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField nFacturaTF;
    private javax.swing.JButton notFilterBT;
    // End of variables declaration//GEN-END:variables
}
