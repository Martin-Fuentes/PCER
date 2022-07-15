
import conectar.conectar;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
/**
 *
 * @author Martin Fuentes
 */
public class Informe_Calificación extends javax.swing.JFrame implements Printable {

    /**
     * Creates new form Informe_Calificación
     */
    public Informe_Calificación(int opcion) {
        initComponents();
        this.setLocationRelativeTo(null);
        String sql = "";
        switch (opcion) {
            case 1:
                sql = "SELECT  cal.Nombre, cal.Cedula_Estudiante, cal.Materia ,cal.Calificacion,cal.Nivel ,us.Nombre FROM Usuario as us, Calificacion_Materia as cal where cal.Cedula_Usuario=us.Cedula_Usuario Order by cal.Nombre ASC";
                break;

            case 2:
                sql = "SELECT  cal.Nombre, cal.Cedula_Estudiante, cal.Materia ,cal.Calificacion,cal.Nivel ,us.Nombre FROM Usuario as us, Calificacion_Materia as cal where cal.Cedula_Usuario=us.Cedula_Usuario AND cal.Calificacion<3 Order by cal.Nombre ASC";
                break;
            case 3:
                sql = "select cal.Cedula_Estudiante,cal.Nombre,cal.Nivel,sum(case when cal.Calificacion <= 3 then 1 else 0 end) as 'Cantidad' from Calificacion_Materia as cal group by cal.Cedula_Estudiante";
                break;
        }
        if (opcion == 3) {

            try {
                conectar conecta = new conectar();
                Connection con = conecta.getConexion();
                DefaultTableModel modelo = new DefaultTableModel();

                modelo.addColumn("Cedula");
                modelo.addColumn("Nombre");
                modelo.addColumn("Nivel");
                modelo.addColumn("Cantidad");
                modelo.addColumn("Materias");
                Statement pst = con.createStatement();
                ResultSet rs = pst.executeQuery(sql);
                String ced, nom, niv,mat="";
                int cant;
                int id;
                
                while (rs.next()) {
                    ced = rs.getString("cal.Cedula_Estudiante");
                    nom = rs.getString("cal.Nombre");
                    niv = rs.getString("cal.Nivel");
                    cant = rs.getInt("Cantidad");
                    if(cant<=3){
                        String sql2=" SELECT cal.Materia from Calificacion_Materia cal where cal.Cedula_Estudiante='"+ced+"'";
                        mat="";
                        conectar conecta2 = new conectar();
                        Connection con2 = conecta2.getConexion();
                        Statement pst2 = con2.createStatement();
                        ResultSet rs2 = pst2.executeQuery(sql2);
                        while(rs2.next()){
                            mat+=rs2.getString("cal.Materia"+",");
                        }
                        
                    }
                    
                     modelo.addRow(new Object[]{ced, nom, niv, cant, mat});
                }
                lista.setModel(modelo);

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error de conexión:" + e.getMessage());

            }

        } else {
            DefaultTableModel modelo = new DefaultTableModel();
            String Estudiante;
            Estudiante = txtBuscar.getText();
            conectar conecta = new conectar();
            Connection con = conecta.getConexion();

            try {

                Statement pst = con.createStatement();
                ResultSet rs = pst.executeQuery(sql);

                modelo.setColumnIdentifiers(new Object[]{"Cedula", "Nombre", "Materia", "Calificacion", "Nivel", "Docente"});

                while (rs.next()) {
                    modelo.addRow(new Object[]{
                        rs.getString("cal.Cedula_Estudiante"),
                        rs.getString("cal.Nombre"),
                        rs.getDouble("cal.Calificacion"),
                        rs.getString("cal.Materia"),
                        rs.getString("cal.Nivel"),
                        rs.getString("us.Nombre")});

                   
                }
                 lista.setModel(modelo);
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error de conexión:" + e.getMessage());

            }
        }

    }

    public void listado() {

        DefaultTableModel modelo = new DefaultTableModel();
        String Estudiante;
        Estudiante = txtBuscar.getText();
        conectar conecta = new conectar();
        Connection con = conecta.getConexion();

        //falta el nombre del docente
        String sql = "Select * from Calificacion_Materia where Cedula_Estudiante like'" + "%" + Estudiante + "%'" + "'";

        try {
            Statement pst = con.createStatement();
            ResultSet rs = pst.executeQuery(sql);

            modelo.setColumnIdentifiers(new Object[]{"Cedula_Estudiante", "Nombre", "Materia", "Calificacion", "Nivel"});

            while (rs.next()) {
                modelo.addRow(new Object[]{
                    rs.getString("Cedula_Estudiante"),
                    rs.getString("Nombre"),
                    rs.getDouble("Calificacion"),
                    rs.getString("Materia"),
                    rs.getString("Nivel")});

                lista.setModel(modelo);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error de conexión:" + e.getMessage());

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

        panelinforme = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        lista = new javax.swing.JTable();
        txtBuscar = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(244, 242, 227));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelinforme.setBackground(new java.awt.Color(244, 242, 227));
        panelinforme.setLayout(null);

        jLabel1.setBackground(new java.awt.Color(59, 74, 107));
        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 28)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(59, 74, 107));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Informe Calificación");
        panelinforme.add(jLabel1);
        jLabel1.setBounds(0, 30, 720, 50);

        lista.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        lista.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        lista.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Nombre", "Cédula", "Materia", "Calificación", "Nivel", "Docente"
            }
        ));
        jScrollPane1.setViewportView(lista);

        panelinforme.add(jScrollPane1);
        jScrollPane1.setBounds(10, 140, 670, 402);

        txtBuscar.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txtBuscar.setActionCommand("<Not Set>");
        txtBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBuscarActionPerformed(evt);
            }
        });
        panelinforme.add(txtBuscar);
        txtBuscar.setBounds(30, 80, 530, 39);

        jButton1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/lupa.png"))); // NOI18N
        jButton1.setText("Buscar");
        jButton1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        panelinforme.add(jButton1);
        jButton1.setBounds(560, 80, 100, 39);

        getContentPane().add(panelinforme, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 690, 580));

        jPanel1.setBackground(new java.awt.Color(244, 242, 227));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton2.setBackground(new java.awt.Color(59, 74, 107));
        jButton2.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/imprimir.png"))); // NOI18N
        jButton2.setText("Imprimir");
        jButton2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, null, new java.awt.Color(255, 255, 102), null, null));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 0, 160, 50));

        jButton3.setBackground(new java.awt.Color(59, 74, 107));
        jButton3.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/volver.png"))); // NOI18N
        jButton3.setText("Regresar");
        jButton3.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, null, new java.awt.Color(255, 255, 102), null, null));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 0, 160, 50));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 580, 690, 70));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        PrinterJob job = PrinterJob.getPrinterJob();
        job.setPrintable(this);
        if (job.printDialog()) {
            try {
                job.print();

            } catch (PrinterException ex) {

            }
        } else {
            JOptionPane.showMessageDialog(this, "La impresión se cancelo ");
        }

    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // buscar
        listado();

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        this.setVisible(false);
        new Interfaz_Informe().setVisible(true);
    }//GEN-LAST:event_jButton3ActionPerformed

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
            java.util.logging.Logger.getLogger(Informe_Calificación.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Informe_Calificación.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Informe_Calificación.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Informe_Calificación.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Informe_Calificación(1).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable lista;
    private javax.swing.JPanel panelinforme;
    private javax.swing.JTextField txtBuscar;
    // End of variables declaration//GEN-END:variables

    @Override
    public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
        if (pageIndex == 0) {
            Graphics2D graphics2d = (Graphics2D) graphics;
            graphics2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());
            //printAll(graphics2d);
            panelinforme.printAll(graphics2d);
            return PAGE_EXISTS;
        } else {
            return NO_SUCH_PAGE;
        }

    }
}
