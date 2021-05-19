package swing;

public class PanelStatus_Item extends javax.swing.JPanel {

    public PanelStatus_Item() {
        initComponents();
    }

    public void showStatus(int values) {
        pro.setValue(values);
    }

    public void setFile(String fileName, String fileSize) {
        lbFileName.setText(fileName);
        lbSize.setText(fileSize);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pro = new javax.swing.JProgressBar();
        lbFileName = new javax.swing.JLabel();
        lbSize = new javax.swing.JLabel();

        pro.setStringPainted(true);

        lbFileName.setText("File Name");

        lbSize.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbSize.setText("Size");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbFileName, javax.swing.GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbSize)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(pro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(lbFileName)
                .addComponent(lbSize))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lbFileName;
    private javax.swing.JLabel lbSize;
    private javax.swing.JProgressBar pro;
    // End of variables declaration//GEN-END:variables
}
