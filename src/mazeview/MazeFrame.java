package mazeview;

import mazemodel.Maze;
import mazemodel.MazeCell;
import mazemodel.Tile;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;

import mazecontroller.Controller;

public class MazeFrame extends javax.swing.JFrame {
	private static final long serialVersionUID = 1L;
	private int SIZE;
    private int MAXLEVEL;
    private JLabel[][] tile;
    private ImageIcon[][] image;
    private Controller controller;
    private int length;
    public MazeFrame() throws IOException {
        initComponents();
    }

    private void setImage(int x,int y, Tile t) {
        image[x][y].setImage(t.getImage().getScaledInstance(length, length, java.awt.Image.SCALE_SMOOTH));
        tile[x+1][y+1].setIcon(image[x][y]);
        repaint();
    }
    private void move(String str, MazeCell[] pair) {
    	Tile[] tile=  {pair[0].getRealTile(), pair[1].getFrontTile()};
        setImage(pair[0].getX(), pair[0].getY(), tile[0]);
        setImage(pair[1].getX(), pair[1].getY(), tile[1]);
        repaint();
        updateStat(str);
    }
    private void updateStat(String str) {
    	statusLabel.setText("you are on: " + str);
    }
    
    public void paintMaze(Tile[][] imageList, String str) {
        for (int i = 0; i < SIZE - 2; i++) {
            for (int j = 0; j < SIZE - 2; j++)
            	setImage(i,j,imageList[i][j]);
        }
        updateStat(str);
    }

    // Add components to the frame once the size of the maze is determined
    public void expand(int SIZE, int MAXLEVEL) {
        this.SIZE = SIZE;
        this.MAXLEVEL = MAXLEVEL;
        mazePanel.setLayout(new GridLayout(SIZE, SIZE));
        tile = new JLabel[SIZE][SIZE];
        image = new ImageIcon[SIZE - 2][SIZE - 2];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                tile[i][j] = new JLabel();
                tile[i][j].setOpaque(true);
                if (j == 0 || i == 0 || j == SIZE - 1 || i == SIZE - 1) {
                    tile[i][j].setBackground(Color.BLACK);
                } else {
                    image[i - 1][j - 1] = new ImageIcon();
                    tile[i][j].setIcon(image[i - 1][j - 1]);
                }
                mazePanel.add(tile[i][j]);
            }
        }
        length = (int) Math.ceil(Math.sqrt(mazePanel.getWidth() * mazePanel.getHeight() / (SIZE * SIZE)));
        getContentPane().add(mazePanel);
        getContentPane().add(statusPanel);
        getContentPane().add(jScrollPane1);
        getContentPane().add(selectPanel);
        jComboBox.setEnabled(false);
        levelField.setEnabled(false);
        this.setFocusable(true);
        this.requestFocus();
    }

    public void updateLevel(int currentLevel) {
        levelLabel.setText("Floor: " + (currentLevel + 1) + "/" + MAXLEVEL);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        mazePanel = new javax.swing.JPanel();
        statusPanel = new javax.swing.JPanel();
        levelLabel = new javax.swing.JLabel();
        statusLabel = new javax.swing.JLabel();
        selectPanel = new javax.swing.JPanel();
        levelField = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jComboBox = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Maze ");
        setBackground(new java.awt.Color(255, 0, 0));
        setForeground(new java.awt.Color(255, 0, 0));
        setResizable(false);

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

        javax.swing.GroupLayout mazePanelLayout = new javax.swing.GroupLayout(mazePanel);
        mazePanel.setLayout(mazePanelLayout);
        mazePanelLayout.setHorizontalGroup(
            mazePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        mazePanelLayout.setVerticalGroup(
            mazePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        statusPanel.setBackground(new java.awt.Color(0, 0, 0));

        levelLabel.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        levelLabel.setForeground(new java.awt.Color(255, 204, 0));
        levelLabel.setText("Floor:");

        statusLabel.setBackground(new java.awt.Color(0, 0, 0));
        statusLabel.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        statusLabel.setForeground(new java.awt.Color(255, 255, 51));
        statusLabel.setText("You are on :");

        javax.swing.GroupLayout statusPanelLayout = new javax.swing.GroupLayout(statusPanel);
        statusPanel.setLayout(statusPanelLayout);
        statusPanelLayout.setHorizontalGroup(
            statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(statusPanelLayout.createSequentialGroup()
                .addComponent(levelLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(statusLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 259, Short.MAX_VALUE))
        );
        statusPanelLayout.setVerticalGroup(
            statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(levelLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 59, Short.MAX_VALUE)
            .addComponent(statusLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        selectPanel.setBackground(new java.awt.Color(204, 204, 255));
        selectPanel.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        levelField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                levelFieldActionPerformed(evt);
            }
        });

        jLabel1.setText("<html>Select Max Floor: </html>");

        jComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "DFS", "Prim's Algorithm"}));
        jComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxActionPerformed(evt);
            }
        });

        jLabel3.setText("Select Algorithm:");

        javax.swing.GroupLayout selectPanelLayout = new javax.swing.GroupLayout(selectPanel);
        selectPanel.setLayout(selectPanelLayout);
        selectPanelLayout.setHorizontalGroup(
            selectPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(selectPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(selectPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(selectPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(selectPanelLayout.createSequentialGroup()
                        .addComponent(levelField, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        selectPanelLayout.setVerticalGroup(
            selectPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(selectPanelLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(selectPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(selectPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(levelField))
                .addGap(6, 6, 6))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(statusPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(mazePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 235, Short.MAX_VALUE)
                    .addComponent(selectPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(mazePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(statusPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(selectPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        setBounds(0, 0, 657, 504);
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxActionPerformed
        @SuppressWarnings("unchecked")
		JComboBox<Integer> c = (JComboBox<Integer>) evt.getSource();
        Maze.algorithmID = c.getSelectedIndex();
    }//GEN-LAST:event_jComboBoxActionPerformed

    private void levelFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_levelFieldActionPerformed
        try {
            int level = Integer.valueOf(levelField.getText());
            if (5 <= level && level <= 10000) {
            	System.out.println("Generating maze...");
                controller.setLevel(level);
                expand(11, level);
                updateLevel(0);
                paintMaze(controller.getTile(), "magenta");
                addKeyListener(new KeyList());
            } else {
                System.out.println("Not within range");
            }
        } catch (NumberFormatException ex) {
            System.out.println("Invalid number");
            levelField.setText("");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }//GEN-LAST:event_levelFieldActionPerformed

    public void setController(Controller controller) {
        this.controller = controller;
    }

    private class KeyList extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            int keyCode = e.getKeyCode();
            // Show the path to the exit
            if (keyCode == KeyEvent.VK_Q) {
                controller.showPath();
                paintMaze(controller.getTile(), controller.getCurrentCell().getTileName());
                return;
            } // Player resets the maze
            else if (keyCode == KeyEvent.VK_R) {
                updateLevel(controller.getLevel());
                controller.resetCell();
                controller.init();
                paintMaze(controller.getTile(), controller.getCurrentCell().getTileName());
                return;
            }

            // The player moves around the maze
            controller.move(keyCode);

            // Check for certain states after the player moves
            if (controller.hasFloorChanged()) {
                updateLevel(controller.getLevel());
                paintMaze(controller.getTile(), controller.getCurrentCell().getTileName());
            } else {
                move(controller.getCurrentCell().getTileName(), controller.getPairs());
            }
            // Toggle game over
            if (controller.isGameOver()) {
                statusLabel.setText("GAME OVER!");
                removeKeyListener(this);
            }
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> jComboBox;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField levelField;
    private javax.swing.JLabel levelLabel;
    private javax.swing.JPanel mazePanel;
    private javax.swing.JPanel selectPanel;
    private javax.swing.JLabel statusLabel;
    private javax.swing.JPanel statusPanel;
    // End of variables declaration//GEN-END:variables
}
