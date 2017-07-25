package MazeView;

import MazeModel.Maze;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Alexandre
 */
public class MazeFrame extends javax.swing.JFrame {
    /**
     * Creates new form MazeFrame
     */
    private int SIZE;
    private int MAXLEVEL;
    private Maze maze;
    public MazeFrame() throws IOException{   
        initComponents();
        setLocationRelativeTo(null);
        setResizable(false);
   }
    public void updateStat(BufferedImage[][] imageList, String str){
        int length = (int)Math.sqrt(jPanel1.getWidth() * jPanel1.getHeight()/((SIZE)*(SIZE))) - 6;
        for(int i = 0; i < SIZE-2; i++)
            for(int j = 0; j < SIZE-2; j++){
                   ((JLabel)jPanel1.getComponent((i+1) * (SIZE) + j+1)).setIcon(
                           new ImageIcon(imageList[i][j].getScaledInstance(length, length,  java.awt.Image.SCALE_SMOOTH)));
            }
        statusLabel.setText("you are on: " + str);
    }
    public void expand(int SIZE, int MAXLEVEL){
        this.SIZE = SIZE;
        this.MAXLEVEL = MAXLEVEL;
        jPanel1.setLayout(new GridLayout(SIZE,SIZE));
        int area = SIZE * SIZE;
        JLabel lab;
        for(int i = 0; i < SIZE ; i++)
            for(int j = 0; j < SIZE; j++){
                lab = new JLabel();
                lab.setOpaque(true);
                if(0 <= i && i < SIZE || 1 <= j && j < SIZE - 1 || 
                        SIZE <= i && i < area || SIZE < j && j < area)
                    lab.setBackground(Color.BLACK);
                jPanel1.add(lab);
                
            }
        add(jPanel1);
        add(jPanel2);
        add(jScrollPane1);
        add(jPanel3);
        levelField.setFocusable(false);
        this.setFocusable(true);
        this.requestFocus();
    }
    public void updateLevel(int currentLevel){
        levelLabel.setText("Floor: " + (currentLevel + 1) + "/" + MAXLEVEL);
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
        jTextArea1 = new javax.swing.JTextArea();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        levelLabel = new javax.swing.JLabel();
        statusLabel = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        levelField = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Maze ");
        setBackground(new java.awt.Color(255, 0, 0));
        setForeground(new java.awt.Color(255, 0, 0));

        jScrollPane1.setAutoscrolls(true);

        jTextArea1.setBackground(new java.awt.Color(153, 255, 153));
        jTextArea1.setColumns(20);
        jTextArea1.setLineWrap(true);
        jTextArea1.setRows(5);
        jTextArea1.setText("Instruction: \nYou are the blue square who wants to get to red square which is at the top floor\n\n Keys:\n w - up\n a - left\n s - down\n d - right\n e - go upstairs\n c - go downstairs\n q - show solution\n r - create new maze\n\n Colors:\n Yellow - you (the player)\n Red - exit\n Pink - can go upstairs\n Yellow - can go downstairs\n Green - can go either upstairs or downstairs\n Cyan - path to the exit");
        jTextArea1.setToolTipText("");
        jTextArea1.setWrapStyleWord(true);
        jTextArea1.setFocusable(false);
        jScrollPane1.setViewportView(jTextArea1);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel2.setBackground(new java.awt.Color(0, 0, 0));

        levelLabel.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        levelLabel.setForeground(new java.awt.Color(255, 204, 0));
        levelLabel.setText("Label");

        statusLabel.setBackground(new java.awt.Color(0, 0, 0));
        statusLabel.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        statusLabel.setForeground(new java.awt.Color(255, 255, 51));
        statusLabel.setText("You are on :");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(levelLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(statusLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(levelLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(statusLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel3.setBackground(new java.awt.Color(204, 204, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel2.setText("Levels:");

        levelField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                levelFieldActionPerformed(evt);
            }
        });

        jLabel1.setText("<html>how many floors do you want? (must between 5 and 10,000)</html>");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(levelField, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(levelField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void generateGame(){
        // Initialize the maze
        expand(maze.getSize()+2, maze.getMaxlevel());
        updateLevel(maze.getLevel());
        updateStat(maze.getImage(), "start");
        
        // Register the key lister and handle a key event.
        this.addKeyListener(new KeyList());
    }
    private void levelFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_levelFieldActionPerformed
        try{
        int levels = Integer.parseInt(levelField.getText());
        if(5 <= levels && levels <= 10000){
                System.out.println("Generating maze...");
                maze = new Maze(levels, 9);
                generateGame();
        }
        else
            System.out.println("Not within range");
        }
        catch(NumberFormatException e){
            levelField.setText("");
        } catch (IOException ex) {
        }
    }//GEN-LAST:event_levelFieldActionPerformed
    private class KeyList extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e){
            if(e.getKeyCode() == KeyEvent.VK_Q)
                maze.showPath();
            else if(e.getKeyCode() == KeyEvent.VK_R){
                updateLevel(maze.getLevel());
                maze.create();
            }
            maze.move(e.getKeyCode());

            // Check for certain states
            if(maze.hasFloorChanged())
                updateLevel(maze.getLevel());
            updateStat(maze.getImage(), maze.getCurrentCell().getPermImageName());

            // Toggle game over
            if(maze.isGameOver()){
                statusLabel.setText("GAME OVER!");
                removeKeyListener(this);
            }
        }
    }
    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField levelField;
    private javax.swing.JLabel levelLabel;
    private javax.swing.JLabel statusLabel;
    // End of variables declaration//GEN-END:variables
}
