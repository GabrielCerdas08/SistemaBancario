/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package vistaGUI;

/**
 *
 * @author fabih
 */
public class ConsultarGananciasTotales extends javax.swing.JFrame {

    /**
     * Creates new form ConsultarGananciasTotales
     */
    public ConsultarGananciasTotales() {
        initComponents();
        this.setLocationRelativeTo(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        linea = new javax.swing.JLabel();
        btConsultar = new javax.swing.JButton();
        back = new javax.swing.JButton();
        cbGanancias = new javax.swing.JComboBox<>();
        txtGanancias = new javax.swing.JPasswordField();
        pinActual1 = new javax.swing.JLabel();
        nombre1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setBackground(new java.awt.Color(0, 204, 51));
        jLabel2.setFont(new java.awt.Font("Gill Sans Ultra Bold Condensed", 0, 34)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 102));
        jLabel2.setText("CONSULTAR GANANCIAS TOTALES");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, -10, 500, 90));

        linea.setFont(new java.awt.Font("Snap ITC", 1, 36)); // NOI18N
        linea.setForeground(new java.awt.Color(0, 0, 102));
        linea.setText("_____________________________");
        getContentPane().add(linea, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 30, 570, 50));

        btConsultar.setBackground(new java.awt.Color(0, 0, 102));
        btConsultar.setFont(new java.awt.Font("Nirmala UI", 1, 17)); // NOI18N
        btConsultar.setForeground(new java.awt.Color(255, 255, 255));
        btConsultar.setText("CONSULTAR");
        btConsultar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btConsultarActionPerformed(evt);
            }
        });
        getContentPane().add(btConsultar, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 260, 160, 40));

        back.setBackground(new java.awt.Color(0, 102, 51));
        back.setFont(new java.awt.Font("Nirmala UI", 1, 16)); // NOI18N
        back.setForeground(new java.awt.Color(255, 255, 255));
        back.setText("BACK");
        back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backActionPerformed(evt);
            }
        });
        getContentPane().add(back, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 330, -1, 30));

        cbGanancias.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar...", "Depositos y retiros", "Depositos", "Retiros" }));
        cbGanancias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbGananciasActionPerformed(evt);
            }
        });
        getContentPane().add(cbGanancias, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 130, 380, 30));

        txtGanancias.setEnabled(false);
        txtGanancias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtGananciasActionPerformed(evt);
            }
        });
        getContentPane().add(txtGanancias, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 190, 300, 30));

        pinActual1.setFont(new java.awt.Font("Nirmala UI", 1, 36)); // NOI18N
        pinActual1.setText("¢");
        getContentPane().add(pinActual1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 180, -1, -1));

        nombre1.setFont(new java.awt.Font("Nirmala UI", 1, 15)); // NOI18N
        nombre1.setText("Seleccione el tipo de consulta");
        getContentPane().add(nombre1, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 100, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btConsultarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btConsultarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btConsultarActionPerformed

    private void backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backActionPerformed
        MenuConsultas abrir = new MenuConsultas();
        abrir.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_backActionPerformed

    private void cbGananciasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbGananciasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbGananciasActionPerformed

    private void txtGananciasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtGananciasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtGananciasActionPerformed

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
            java.util.logging.Logger.getLogger(ConsultarGananciasTotales.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ConsultarGananciasTotales.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ConsultarGananciasTotales.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ConsultarGananciasTotales.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ConsultarGananciasTotales().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton back;
    private javax.swing.JButton btConsultar;
    private javax.swing.JComboBox<String> cbGanancias;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel linea;
    private javax.swing.JLabel nombre1;
    private javax.swing.JLabel pinActual1;
    private javax.swing.JPasswordField txtGanancias;
    // End of variables declaration//GEN-END:variables
}