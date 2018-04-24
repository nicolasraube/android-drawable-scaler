/**
 *
 * @author Nicolas 27.02.2016
 *
Screen Capture is a tool for taking screenshots.
Copyright (C) 2016  Nicolas Raube
This program is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.
This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.
You should have received a copy of the GNU General Public License
along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Window extends javax.swing.JFrame {

    private BufferedImage[] drawables = new BufferedImage[4];
    private final int ldpi = 0, mdpi = 1, hdpi = 2, xhdpi = 3;
    private final String[] dpis = new String[]{"ldpi", "mdpi", "hdpi", "xhdpi"};
    private String outputFolder;
    private float[] factors = new float[] {.75f, 1, 1.5f, 2};
    private float ldpiWidth = -1, ldpiHeight = -1, mdpiWidth = -1, mdpiHeight = -1, hdpiWidth = -1, hdpiHeight = -1, xhdpiWidth = -1, xhdpiHeight = -1;
    private float[] calculatedWidth = new float[] {ldpiWidth, mdpiWidth, hdpiWidth, xhdpiWidth};
    private float[] calculatedHeight = new float[] {ldpiHeight, mdpiHeight, hdpiHeight, xhdpiHeight};

    public Window() {
        setSystemLookAndFeel();
        
        initComponents();

        init();

        outputFolder = System.getProperty("user.home");
        labelOutputFolder.setText(outputFolder);
    }
    
    void setSystemLookAndFeel() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Window.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(Window.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Window.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(Window.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void init() {

        setTitle("Android Drawable Scaler");
        setMinimumSize(new Dimension(getWidth(), getHeight()));
        setLocation((int) Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 2 - getWidth() / 2,
                (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 2 - getHeight() / 2);

    }

    private void initComponents() {

        jSeparator1 = new javax.swing.JSeparator();
        btnAddLdpi = new javax.swing.JButton();
        btnAddMdpi = new javax.swing.JButton();
        btnAddHdpi = new javax.swing.JButton();
        btnAddXhdpi = new javax.swing.JButton();
        btnScaleAllDrawables = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        btnChooseOutputFolder = new javax.swing.JButton();
        labelOutputFolder = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        btnReset = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        labelInputPath = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        textPaneDrawableName = new javax.swing.JTextPane();
        jSeparator4 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnAddLdpi.setText("add drawable-ldpi");
        btnAddLdpi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddLdpiActionPerformed(evt);
            }
        });

        btnAddMdpi.setText("add drawable-mdpi");
        btnAddMdpi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddMdpiActionPerformed(evt);
            }
        });

        btnAddHdpi.setText("add drawable-hdpi");
        btnAddHdpi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddHdpiActionPerformed(evt);
            }
        });

        btnAddXhdpi.setText("add drawable-xhdpi");
        btnAddXhdpi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddXhdpiActionPerformed(evt);
            }
        });

        btnScaleAllDrawables.setText("Scale drawable");
        btnScaleAllDrawables.setEnabled(false);
        btnScaleAllDrawables.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnScaleAllDrawablesActionPerformed(evt);
            }
        });

        btnCancel.setText("Cancel");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        btnChooseOutputFolder.setText("...");
        btnChooseOutputFolder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChooseOutputFolderActionPerformed(evt);
            }
        });

        labelOutputFolder.setText("Please choose an output directory");

        jLabel2.setText("Output folder:");

        btnReset.setText("Reset");
        btnReset.setEnabled(false);
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });

        jLabel1.setText("Input drawable:");

        labelInputPath.setText("Please add a drawable");

        jLabel3.setText("Drawable name:");

        jScrollPane2.setViewportView(textPaneDrawableName);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator4)
                    .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnAddLdpi)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnAddMdpi))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnAddHdpi)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnAddXhdpi))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnCancel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                        .addComponent(btnReset)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnScaleAllDrawables))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnChooseOutputFolder)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelOutputFolder, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jSeparator3)
                    .addComponent(jScrollPane2)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(labelInputPath)
                            .addComponent(jLabel1)
                            .addComponent(jLabel3))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAddLdpi)
                    .addComponent(btnAddMdpi))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAddHdpi)
                    .addComponent(btnAddXhdpi))
                .addGap(7, 7, 7)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelInputPath)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnChooseOutputFolder)
                    .addComponent(labelOutputFolder, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnScaleAllDrawables)
                    .addComponent(btnCancel)
                    .addComponent(btnReset))
                .addContainerGap())
        );

        pack();
    }

    void enableDisableButtons() {
        btnScaleAllDrawables.setEnabled(!btnScaleAllDrawables.isEnabled());
        btnReset.setEnabled(!btnReset.isEnabled());
        btnAddLdpi.setEnabled(!btnAddLdpi.isEnabled());
        btnAddMdpi.setEnabled(!btnAddMdpi.isEnabled());
        btnAddHdpi.setEnabled(!btnAddHdpi.isEnabled());
        btnAddXhdpi.setEnabled(!btnAddXhdpi.isEnabled());
    }

    String showFileChooserDialog(int fileSelectionMode) {
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new File(outputFolder));
        chooser.setFileSelectionMode(fileSelectionMode);
        if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            return chooser.getSelectedFile().getPath();
        }

        return null;
    }

    private void btnAddLdpiActionPerformed(java.awt.event.ActionEvent evt) {
        String path = showFileChooserDialog(JFileChooser.FILES_ONLY);
        if (path != null) {
            enableDisableButtons();
            try {
                drawables[ldpi] = ImageIO.read(new File(path));
            } catch (IOException ex) {
                Logger.getLogger(Window.class.getName()).log(Level.SEVERE, null, ex);
            }
            labelInputPath.setText(path);
        }
    }

    private void btnChooseOutputFolderActionPerformed(java.awt.event.ActionEvent evt) {
        String path = showFileChooserDialog(JFileChooser.DIRECTORIES_ONLY);
        if (path != null) {
            outputFolder = path;
            labelOutputFolder.setText(outputFolder);
        }
    }

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {
        System.exit(0);
    }

    private void btnAddMdpiActionPerformed(java.awt.event.ActionEvent evt) {
        String path = showFileChooserDialog(JFileChooser.FILES_ONLY);
        if (path != null) {
            enableDisableButtons();
            try {
                drawables[mdpi] = ImageIO.read(new File(path));
            } catch (IOException ex) {
                Logger.getLogger(Window.class.getName()).log(Level.SEVERE, null, ex);
            }
            labelInputPath.setText(path);
        }
    }

    private void btnAddHdpiActionPerformed(java.awt.event.ActionEvent evt) {
        String path = showFileChooserDialog(JFileChooser.FILES_ONLY);
        if (path != null) {
            enableDisableButtons();
            try {
                drawables[hdpi] = ImageIO.read(new File(path));
            } catch (IOException ex) {
                Logger.getLogger(Window.class.getName()).log(Level.SEVERE, null, ex);
            }
            labelInputPath.setText(path);
        }
    }

    private void btnAddXhdpiActionPerformed(java.awt.event.ActionEvent evt) {
        String path = showFileChooserDialog(JFileChooser.FILES_ONLY);
        if (path != null) {
            enableDisableButtons();
            try {
                drawables[xhdpi] = ImageIO.read(new File(path));
            } catch (IOException ex) {
                Logger.getLogger(Window.class.getName()).log(Level.SEVERE, null, ex);
            }
            labelInputPath.setText(path);
        }
    }

    private void btnScaleAllDrawablesActionPerformed(java.awt.event.ActionEvent evt) {

        String drawableName = textPaneDrawableName.getText();
        if (drawableName.trim().isEmpty()) {
            drawableName = "drawable";
        }

        createDpiFoldersInOutputFolder();
        int addedDrawable = -1;
        for (int i = 0; i < drawables.length; i++) {
            if (drawables[i] != null) {
                addedDrawable = i;
                break;
            }
        }

        try {
            ImageIO.write(drawables[addedDrawable], "png", new File(outputFolder + "\\" + dpis[addedDrawable] + "\\" + drawableName + ".png"));
        } catch (IOException ex) {
            Logger.getLogger(Window.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (addedDrawable == ldpi) {
            calculatedWidth[ldpi] = drawables[ldpi].getWidth();
            calculatedHeight[ldpi] = drawables[ldpi].getHeight();
            calculatedWidth[mdpi] = calculatedWidth[ldpi] / factors[ldpi];
            calculatedHeight[mdpi] = calculatedHeight[ldpi] / factors[ldpi];
            calculatedWidth[hdpi] = calculatedWidth[mdpi] * factors[hdpi];
            calculatedHeight[hdpi] = calculatedHeight[mdpi] * factors[hdpi];
            calculatedWidth[xhdpi] = calculatedWidth[mdpi] * factors[xhdpi];
            calculatedHeight[xhdpi] = calculatedHeight[mdpi] * factors[xhdpi];
            
            scaleDrawables(ldpi, drawableName);
        } else if (addedDrawable == mdpi) {
            calculatedWidth[mdpi] = drawables[mdpi].getWidth();
            calculatedHeight[mdpi] = drawables[mdpi].getHeight();
            calculatedWidth[ldpi] = calculatedWidth[mdpi] * factors[ldpi];
            calculatedHeight[ldpi] = calculatedHeight[mdpi] * factors[ldpi];
            calculatedWidth[hdpi] = calculatedWidth[mdpi] * factors[hdpi];
            calculatedHeight[hdpi] = calculatedHeight[mdpi] * factors[hdpi];
            calculatedWidth[xhdpi] = calculatedWidth[mdpi] * factors[xhdpi];
            calculatedHeight[xhdpi] = calculatedHeight[mdpi] * factors[xhdpi];
            
            scaleDrawables(mdpi, drawableName);
        } else if (addedDrawable == hdpi) {
            calculatedWidth[hdpi] = drawables[hdpi].getWidth();
            calculatedHeight[hdpi] = drawables[hdpi].getHeight();
            calculatedWidth[mdpi] = calculatedWidth[hdpi] / factors[hdpi];
            calculatedHeight[mdpi] = calculatedHeight[hdpi] / factors[hdpi];
            calculatedWidth[ldpi] = calculatedWidth[mdpi] * factors[ldpi];
            calculatedHeight[ldpi] = calculatedHeight[mdpi] * factors[ldpi];
            calculatedWidth[xhdpi] = calculatedWidth[mdpi] * factors[xhdpi];
            calculatedHeight[xhdpi] = calculatedHeight[mdpi] * factors[xhdpi];
            
            scaleDrawables(hdpi, drawableName);
        } else if (addedDrawable == xhdpi) {
            calculatedWidth[xhdpi] = drawables[xhdpi].getWidth();
            calculatedHeight[xhdpi] = drawables[xhdpi].getHeight();
            calculatedWidth[mdpi] = calculatedWidth[xhdpi] / factors[xhdpi];
            calculatedHeight[mdpi] = calculatedHeight[xhdpi] / factors[xhdpi];
            calculatedWidth[ldpi] = calculatedWidth[mdpi] * factors[ldpi];
            calculatedHeight[ldpi] = calculatedHeight[mdpi] * factors[ldpi];
            calculatedWidth[hdpi] = calculatedWidth[mdpi] * factors[hdpi];
            calculatedHeight[hdpi] = calculatedHeight[mdpi] * factors[hdpi];
            
            scaleDrawables(xhdpi, drawableName);
        }
        
        reset();
        enableDisableButtons();
        
    }

    void scaleDrawables(int dpi, String drawableName) {
        
        for (int i = 0; i < drawables.length; i++) {
            
            if(i != dpi) {

                System.out.println(calculatedWidth[i] + " " + calculatedHeight[i]);
                BufferedImage newImage = new BufferedImage((int) calculatedWidth[i], (int) calculatedHeight[i], BufferedImage.TYPE_INT_ARGB);
                Graphics2D g = newImage.createGraphics();
                g.drawImage(drawables[dpi], 0, 0, (int) calculatedWidth[i], (int) calculatedHeight[i], null);
                g.dispose();
                try {
                    ImageIO.write(newImage, "png", new File(outputFolder + "\\" + dpis[i] + "\\" + drawableName + ".png"));
                } catch (IOException ex) {
                    Logger.getLogger(Window.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
            
        }
        
    }
    
    void createDpiFoldersInOutputFolder() {
        new File(outputFolder + "\\" + dpis[ldpi]).mkdir();
        new File(outputFolder + "\\" + dpis[mdpi]).mkdir();
        new File(outputFolder + "\\" + dpis[hdpi]).mkdir();
        new File(outputFolder + "\\" + dpis[xhdpi]).mkdir();
    }

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {
        reset();
        enableDisableButtons();
    }

    void reset() {
        drawables[ldpi] = null;
        drawables[mdpi] = null;
        drawables[hdpi] = null;
        drawables[xhdpi] = null;

        calculatedWidth[ldpi] = -1;
        calculatedHeight[ldpi] = -1;
        calculatedWidth[mdpi] = -1;
        calculatedHeight[mdpi] = -1;
        calculatedWidth[hdpi] = -1;
        calculatedHeight[hdpi] = -1;
        calculatedWidth[xhdpi] = -1;
        calculatedHeight[xhdpi] = -1;

        labelInputPath.setText("Please add a drawable");
        outputFolder = System.getProperty("user.home");
        labelOutputFolder.setText(outputFolder);
        textPaneDrawableName.setText("");
    }
    
    private javax.swing.JButton btnAddHdpi;
    private javax.swing.JButton btnAddLdpi;
    private javax.swing.JButton btnAddMdpi;
    private javax.swing.JButton btnAddXhdpi;
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnChooseOutputFolder;
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnScaleAllDrawables;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JLabel labelInputPath;
    private javax.swing.JLabel labelOutputFolder;
    private javax.swing.JTextPane textPaneDrawableName;
}
