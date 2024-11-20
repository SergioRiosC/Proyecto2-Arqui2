/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.proyecto2;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
/**
 *
 * @author sebastianqr.2208
 */
public class GUI extends javax.swing.JFrame {

    /**
     * Creates new form GUI
     */
    
    private JPanel processorPanel1, processorPanel2, processorPanel3, processorPanel4;
    private JLabel busLabel;
    private JTextArea logArea;
    private JButton startButton, stepButton, resetButton;
    private Llamada llamada;
    
    public GUI() {
        initComponents();
        customizeComponents();
        llamada = new Llamada();
    }
    
    
    private void customizeComponents() {
        // Crear y configurar los componentes


        // Crear paneles de procesadores
        processorPanel1 = createProcessorPanel("Procesador 1");
        processorPanel2 = createProcessorPanel("Procesador 2");
        processorPanel3 = createProcessorPanel("Procesador 3");
        processorPanel4 = createProcessorPanel("Procesador 4");

        // Crear un panel para agrupar los procesadores
        JPanel processorsPanel = new JPanel(new GridLayout(1, 4));
        processorsPanel.add(processorPanel1);
        processorsPanel.add(processorPanel2);
        processorsPanel.add(processorPanel3);
        processorsPanel.add(processorPanel4);

        // Crear bus label
        busLabel = new JLabel("Bus: Esperando instrucciones...");
        busLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // Crear área de logs
        logArea = new JTextArea(10, 50);
        logArea.setEditable(false);
        JScrollPane logScroll = new JScrollPane(logArea);

        // Crear botones de control
        JPanel controlPanel = new JPanel();
        startButton = new JButton("Iniciar");
        stepButton = new JButton("Paso Manual");
        resetButton = new JButton("Reiniciar");
        controlPanel.add(startButton);
        controlPanel.add(stepButton);
        controlPanel.add(resetButton);

        // Añadir eventos a los botones
        startButton.addActionListener(this::startSimulation);
        stepButton.addActionListener(this::stepSimulation);
        resetButton.addActionListener(this::resetSimulation);

        // Usar BorderLayout para organizar los elementos
        setLayout(new BorderLayout());
        add(controlPanel, BorderLayout.NORTH); // Panel de botones
        add(busLabel, BorderLayout.CENTER); // Etiqueta del bus
        add(processorsPanel, BorderLayout.SOUTH); // Panel de procesadores
        add(logScroll, BorderLayout.EAST); // Área de logs


    }
    
    
    private JPanel createProcessorPanel(String title) {
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createTitledBorder(title));
        panel.setPreferredSize(new Dimension(150, 100));
        JLabel stateLabel = new JLabel("Estado: I (Invalid)");
        panel.add(stateLabel);
        return panel;
    }
    
    
    
     private void startSimulation(ActionEvent e) {
        logArea.append("Iniciando simulación...\n");
        busLabel.setText("Bus: Procesando...");
        llamada.iniciar();
         logArea.append("Modificado...\n");
    }

    /**
     * Ejecuta un paso de la simulación.
     */
    private void stepSimulation(ActionEvent e) {
        logArea.append("Ejecutando paso manual...\n");
        // Aquí va la lógica de un paso del protocolo MESI
    }

    /**
     * Reinicia la simulación.
     */
    private void resetSimulation(ActionEvent e) {
        logArea.append("Reiniciando simulación...\n");
        busLabel.setText("Bus: Esperando instrucciones...");
        // Aquí va la lógica para reiniciar
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        memory_panel = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        memory_panel.setBackground(new java.awt.Color(204, 204, 204));

        javax.swing.GroupLayout memory_panelLayout = new javax.swing.GroupLayout(memory_panel);
        memory_panel.setLayout(memory_panelLayout);
        memory_panelLayout.setHorizontalGroup(
            memory_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 45, Short.MAX_VALUE)
        );
        memory_panelLayout.setVerticalGroup(
            memory_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 27, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(memory_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(1049, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 916, Short.MAX_VALUE)
                .addComponent(memory_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new GUI().setVisible(true));
        
        
        
        /* Create and display the form */
        /*java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUI().setVisible(true);
            }
        }); */
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel memory_panel;
    // End of variables declaration//GEN-END:variables
}
