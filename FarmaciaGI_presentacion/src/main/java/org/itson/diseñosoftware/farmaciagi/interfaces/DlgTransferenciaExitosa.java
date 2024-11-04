package org.itson.diseñosoftware.farmaciagi.interfaces;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import org.itson.disenosoftware.farmaciagi_dtos.VentaDTO;

public class DlgTransferenciaExitosa extends javax.swing.JDialog {
    
    /**
     * Creates new form DlgTransferenciaExitosa
     * @param parent
     * @param modal
     * @param venta
     */
    public DlgTransferenciaExitosa(java.awt.Frame parent, boolean modal, VentaDTO venta) {
        super(parent, modal);
        initComponents();
        
        Timer timer = new Timer(3000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                DlgResumenVenta pResumenVenta = new DlgResumenVenta(parent, true, venta, 0.0F, 0.0F);
                pResumenVenta.setVisible(true);
            }
        });
        timer.setRepeats(false); 
        timer.start(); 

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Transferencia completada.png"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
