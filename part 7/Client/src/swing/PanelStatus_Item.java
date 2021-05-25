package swing;

import java.awt.event.ActionListener;

public class PanelStatus_Item extends javax.swing.JLayeredPane {

    public PanelStatus_Item() {
        initComponents();
    }

    public void showStatus(int values) {
        pro.setValue(values);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pro = new javax.swing.JProgressBar();
        cmd = new javax.swing.JButton();

        pro.setStringPainted(true);

        cmd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/save.png"))); // NOI18N
        cmd.setContentAreaFilled(false);
        cmd.setName("S"); // NOI18N
        cmd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdActionPerformed(evt);
            }
        });

        setLayer(pro, javax.swing.JLayeredPane.DEFAULT_LAYER);
        setLayer(cmd, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(cmd, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(pro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(cmd, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cmdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdActionPerformed
        if (cmd.getName().equals("R")) {
            cmd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/resume.png")));
            cmd.setName("P");
            eventPause.actionPerformed(evt);
        } else if (cmd.getName().equals("P")) {
            cmd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/pause.png")));
            cmd.setName("R");
            eventPause.actionPerformed(evt);
        } else if (cmd.getName().equals("S")) {
            eventSave.actionPerformed(evt);
        }
    }//GEN-LAST:event_cmdActionPerformed

    private ActionListener eventSave;
    private ActionListener eventPause;

    public void addEventSave(ActionListener eventSave) {
        this.eventSave = eventSave;
    }

    public void addEvent(ActionListener event) {
        this.eventPause = event;
    }

    public void done() {
        cmd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/done.png")));
        cmd.setName("D");
    }

    public boolean isPause() {
        return cmd.getName().equals("P");
    }

    public void startFile() {
        cmd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/pause.png")));
        cmd.setName("R");
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cmd;
    private javax.swing.JProgressBar pro;
    // End of variables declaration//GEN-END:variables
}
