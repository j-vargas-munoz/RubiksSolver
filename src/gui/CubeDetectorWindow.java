package gui;

import javax.swing.JFrame;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JFileChooser;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.Dimension;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileReader;
import java.io.LineNumberReader;
import java.io.BufferedReader;
import java.io.IOException;
import visualization.CubeSketch;
import utils.CubeDetector;


/**
 * Clase CubeDetectorWindow, que despliega el frame principal
 * 
 * @author <a href="mailto:j.vargas.munoz@gmail.com">Jos&eacute; de Jes&uacute;s Vargas Mu&ntilde;oz</a>
 * @author <a href="mailto:siul.al4@gmail.com">Liz&aacute;rraga Santos Luis Alfredo</a>
 * @version 21/02/2015
 */
public class CubeDetectorWindow {


    private CubeDetector cd;
    private CubeSketch sketch;
    private static final int HEIGHT = 500;
    private static final int WIDTH = 1300;
    private String fileName;
    private BufferedImage cameraImg;
    private BufferedImage modifiedImg;
    private JFrame frame;
    private JPanel panel;
    private JLabel imageLabelCamera;
    private JLabel imageLabelModified;
    private JScrollPane scrollPaneCamera;
    private JScrollPane scrollPaneModified;
    private String currentPath;
    private BufferedReader bf;
    private boolean imageLoaded;
    private boolean detect;
    JFrame grayFrame;
    JFrame cannyFrame;
    JFrame harrisFrame;
    JFrame greenFrame;
    JFrame blueFrame;
    JFrame yellowFrame;
    JFrame whiteFrame;
    JFrame redFrame;
    JFrame orangeFrame;
    JLabel grayLabel;
    JLabel cannyLabel;
    JLabel harrisLabel;
    JLabel greenLabel;
    JLabel blueLabel;
    JLabel yellowLabel;
    JLabel whiteLabel;
    JLabel redLabel;
    JLabel orangeLabel;


    /**
     * Constructor
     */
    public CubeDetectorWindow() {
        imageLoaded = false;
        detect = false;

        initCubeSketch();
        loadWindows();
        initCubeDetection();
    }


    private void loadWindows() {
        frame = new JFrame("Detector y solucionador cubo Rubik");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        imageLabelCamera = new JLabel();
        imageLabelModified = new JLabel();
        scrollPaneCamera = new JScrollPane(imageLabelCamera, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPaneCamera.setPreferredSize(new Dimension(WIDTH/2, HEIGHT));
        scrollPaneModified = new JScrollPane(sketch, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPaneModified.setPreferredSize(new Dimension(WIDTH/2, HEIGHT));
        panel.add(scrollPaneCamera);
        panel.add(scrollPaneModified);

        grayLabel = new JLabel();
        cannyLabel = new JLabel();
        harrisLabel = new JLabel();
        greenLabel = new JLabel();
        blueLabel = new JLabel();
        yellowLabel = new JLabel();
        whiteLabel = new JLabel();
        redLabel = new JLabel();
        orangeLabel = new JLabel();
        JPanel grayPanel = new JPanel();
        JPanel cannyPanel = new JPanel();
        JPanel harrisPanel = new JPanel();
        JPanel greenPanel = new JPanel();
        JPanel bluePanel = new JPanel();
        JPanel yellowPanel = new JPanel();
        JPanel whitePanel = new JPanel();
        JPanel redPanel = new JPanel();
        JPanel orangePanel = new JPanel();
        grayPanel.setLayout(new BoxLayout(grayPanel, BoxLayout.X_AXIS));
        grayPanel.add(grayLabel);
        cannyPanel.setLayout(new BoxLayout(cannyPanel, BoxLayout.X_AXIS));
        cannyPanel.add(cannyLabel);
        harrisPanel.setLayout(new BoxLayout(harrisPanel, BoxLayout.X_AXIS));
        harrisPanel.add(harrisLabel);
        greenPanel.setLayout(new BoxLayout(greenPanel, BoxLayout.X_AXIS));
        greenPanel.add(greenLabel);
        bluePanel.setLayout(new BoxLayout(bluePanel, BoxLayout.X_AXIS));
        bluePanel.add(blueLabel);
        yellowPanel.setLayout(new BoxLayout(yellowPanel, BoxLayout.X_AXIS));
        yellowPanel.add(yellowLabel);
        whitePanel.setLayout(new BoxLayout(whitePanel, BoxLayout.X_AXIS));
        whitePanel.add(whiteLabel);
        redPanel.setLayout(new BoxLayout(redPanel, BoxLayout.X_AXIS));
        redPanel.add(redLabel);
        orangePanel.setLayout(new BoxLayout(orangePanel, BoxLayout.X_AXIS));
        orangePanel.add(orangeLabel);
        grayFrame = new JFrame("Threshold de negro");
        cannyFrame = new JFrame("Canny");
        harrisFrame = new JFrame("Harris");
        greenFrame = new JFrame("Green");
        blueFrame = new JFrame("Blue");
        yellowFrame = new JFrame("Yellow");
        whiteFrame = new JFrame("White");
        redFrame = new JFrame("Red");
        orangeFrame = new JFrame("Orange");

        JMenuBar menuBar = new JMenuBar();
        JMenu menuAcciones = new JMenu("Acciones");
        JMenu menuVentanas = new JMenu("Ventanas");
        menuBar.add(menuAcciones);
        menuBar.add(menuVentanas);

        JMenuItem menuItemIniciaDeteccion = new JMenuItem("Detectar");
        menuItemIniciaDeteccion.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        detect = true;
                }
        });
        menuAcciones.add(menuItemIniciaDeteccion);
        JMenuItem menuItemIniciaSolver = new JMenuItem("Resolver cubo");
        menuItemIniciaSolver.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        sketch.solve();
                }
        });
        menuAcciones.add(menuItemIniciaSolver);

        JMenuItem menuItemIniciaFill = new JMenuItem("Test Fill");
        menuItemIniciaFill.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        sketch.testFill();
                }
        });
        menuAcciones.add(menuItemIniciaFill);

        JMenuItem menuItemGuardaImagen = new JMenuItem("Guardar");
        menuItemGuardaImagen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_2, ActionEvent.ALT_MASK));
        menuItemGuardaImagen.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        saveImage();
                }
        });
        menuAcciones.add(menuItemGuardaImagen);


        JMenuItem menuItemSalir = new JMenuItem("Salir");
        menuItemSalir.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_3, ActionEvent.ALT_MASK));
        menuItemSalir.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        System.exit(0);
                }
        });
        menuAcciones.add(menuItemSalir);

        JMenuItem menuItemVerGray = new JMenuItem("Threshold de negro");
        menuItemVerGray.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        grayFrame.setVisible(true);
                }
        });
        menuVentanas.add(menuItemVerGray);

        JMenuItem menuItemVerCanny = new JMenuItem("Canny");
        menuItemVerCanny.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        cannyFrame.setVisible(true);
                }
        });
        menuVentanas.add(menuItemVerCanny);

        JMenuItem menuItemVerHarris = new JMenuItem("Harris");
        menuItemVerHarris.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        harrisFrame.setVisible(true);
                }
        });
        menuVentanas.add(menuItemVerHarris);

        JMenuItem menuItemVerGreen = new JMenuItem("Threshold de verde");
        menuItemVerGreen.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        greenFrame.setVisible(true);
                }
        });
        menuVentanas.add(menuItemVerGreen);

        JMenuItem menuItemVerBlue = new JMenuItem("Threshold de azul");
        menuItemVerBlue.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        blueFrame.setVisible(true);
                }
        });
        menuVentanas.add(menuItemVerBlue);

        JMenuItem menuItemVerWhite = new JMenuItem("Threshold de blanco");
        menuItemVerWhite.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        whiteFrame.setVisible(true);
                }
        });
        menuVentanas.add(menuItemVerWhite);

        JMenuItem menuItemVerRed = new JMenuItem("Threshold de rojo");
        menuItemVerRed.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        redFrame.setVisible(true);
                }
        });
        menuVentanas.add(menuItemVerRed);

        JMenuItem menuItemVerOrange = new JMenuItem("Threshold de naranja");
        menuItemVerOrange.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        orangeFrame.setVisible(true);
                }
        });
        menuVentanas.add(menuItemVerOrange);

        JMenuItem menuItemVerYellow = new JMenuItem("Threshold de amarillo");
        menuItemVerYellow.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        yellowFrame.setVisible(true);
                }
        });
        menuVentanas.add(menuItemVerYellow);

        frame.setJMenuBar(menuBar);

        Container c1 = frame.getContentPane();
        c1.add(panel);
        frame.pack();
        frame.setVisible(true);
        Container c2 = grayFrame.getContentPane();
        c2.add(grayPanel);
        grayFrame.pack();
        grayFrame.setVisible(false);
        grayFrame.setSize(640,480);
        Container c3 = cannyFrame.getContentPane();
        c3.add(cannyPanel);
        cannyFrame.pack();
        cannyFrame.setVisible(false);
        cannyFrame.setSize(640,480);
        Container c5 = harrisFrame.getContentPane();
        c5.add(harrisPanel);
        harrisFrame.pack();
        harrisFrame.setVisible(false);
        harrisFrame.setSize(640,480);
        Container c6 = greenFrame.getContentPane();
        c6.add(greenPanel);
        greenFrame.pack();
        greenFrame.setVisible(false);
        greenFrame.setSize(640,480);
        Container c7 = blueFrame.getContentPane();
        c7.add(bluePanel);
        blueFrame.pack();
        blueFrame.setVisible(false);
        blueFrame.setSize(640,480);
        Container c8 = whiteFrame.getContentPane();
        c8.add(whitePanel);
        whiteFrame.pack();
        whiteFrame.setVisible(false);
        whiteFrame.setSize(640,480);
        Container c9 = yellowFrame.getContentPane();
        c9.add(yellowPanel);
        yellowFrame.pack();
        yellowFrame.setVisible(false);
        yellowFrame.setSize(640,480);
        Container c10 = redFrame.getContentPane();
        c10.add(redPanel);
        redFrame.pack();
        redFrame.setVisible(false);
        redFrame.setSize(640,480);
        Container c11 = orangeFrame.getContentPane();
        c11.add(orangePanel);
        orangeFrame.pack();
        orangeFrame.setVisible(false);
        orangeFrame.setSize(640,480);
    }


    public void initCubeDetection() {
        cd = new CubeDetector();
    }


    public void initCubeSketch() {
        sketch = new CubeSketch();
        sketch.init();
    }


    public void updateFromCamera() {
        while(true) {
            if (detect) {
                return;
            }
            cd.step();
            updateCameraImage(cd.Mat2BufferedImage(cd.img1));
            updateGrayImage(cd.Mat2BufferedImage(cd.grayImage));
            updateHarrisImage(cd.Mat2BufferedImage(cd.harrisImage));
            updateCannyImage(cd.Mat2BufferedImage(cd.cannyImage));
            updateRedImage(cd.Mat2BufferedImage(cd.redImage));
            updateBlueImage(cd.Mat2BufferedImage(cd.blueImage));
            updateYellowImage(cd.Mat2BufferedImage(cd.yellowImage));
            updateGreenImage(cd.Mat2BufferedImage(cd.greenImage));
            updateOrangeImage(cd.Mat2BufferedImage(cd.orangeImage));
            updateWhiteImage(cd.Mat2BufferedImage(cd.whiteImage));
            // if (cd.done) {
            //     Object[] options = {"Yes, please",
            //         "No, thanks", "Holy moley"};
            //     int n = JOptionPane.showOptionDialog(frame,
            //         "Deseas continuar?",
            //         "A Silly Question",
            //         JOptionPane.YES_NO_CANCEL_OPTION,
            //         JOptionPane.QUESTION_MESSAGE,
            //         null,
            //         options,
            //         options[2]);
            //     if (n == 0) {
            //         cd.done = false;
            //         cd.clearRectList();
            //     }
            // }
        }
    }

    public void doDetection() {
        while(true) {
            // if (detect) {
            //     cd.start = true;
            //     detect = false;
            // }
            cd.step();
            updateCameraImage(cd.Mat2BufferedImage(cd.img1));
            updateGrayImage(cd.Mat2BufferedImage(cd.grayImage));
            updateHarrisImage(cd.Mat2BufferedImage(cd.harrisImage));
            updateCannyImage(cd.Mat2BufferedImage(cd.cannyImage));
            updateRedImage(cd.Mat2BufferedImage(cd.redImage));
            updateBlueImage(cd.Mat2BufferedImage(cd.blueImage));
            updateYellowImage(cd.Mat2BufferedImage(cd.yellowImage));
            updateGreenImage(cd.Mat2BufferedImage(cd.greenImage));
            updateOrangeImage(cd.Mat2BufferedImage(cd.orangeImage));
            updateWhiteImage(cd.Mat2BufferedImage(cd.whiteImage));
            if (cd.done) {
                // Object[] options = {"Yes, please",
                //     "No, thanks", "Holy moley"};
                int n = JOptionPane.showConfirmDialog(frame,
                    "Se detectó correctamente cada cuadrado del cubo?\n",
                    "Detección",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE);
                if (n == JOptionPane.YES_OPTION) {
                    // cd.done = false;
                    cd.clearRectList();
                    return;
                }
                if (n == JOptionPane.NO_OPTION) {
                    cd.done = false;
                    cd.clearRectList();
                }
            }
        }
    }

    public void work() {
        int[][] colors = new int[6][9];
        int[][] faceColors = new int[3][3];
        updateFromCamera();
        detect = false;
        cd.start = true;
        for (int i = 0; i < 6 ; i++ ) {
            if (detect) {
                cd.done = false;
                cd.start = false;
                return;
            }
            doDetection();
            colors[i] = cd.getColors();
            for (int j = 0 ; j < 9 ; j++ ) {
                System.out.println("i :" + i + " color:" + colors[i][j]);
            }
            applyColors(colors[i]);
            cd.done = false;
        }

        cd.done = false;
        detect = false;
        cd.start = false;
    }


    private void applyColors(int[] colors) {
        int[][] faceColors = new int[3][3];

        faceColors[0][0] = colors[0];
        faceColors[0][1] = colors[1];
        faceColors[0][2] = colors[2];
        faceColors[1][0] = colors[3];
        faceColors[1][1] = colors[4];
        faceColors[1][2] = colors[5];
        faceColors[2][0] = colors[6];
        faceColors[2][1] = colors[7];
        faceColors[2][2] = colors[8];

        if (faceColors[1][1] == CubeSketch.CUBE_WHITE) {
            sketch.fillFace(CubeSketch.FRONT_FACE, faceColors);
            sketch.getCube().setFace(faceColors);
        }

        if (faceColors[1][1] == CubeSketch.CUBE_BLUE) {
            sketch.fillFace(CubeSketch.LEFT_FACE, faceColors);
            sketch.getCube().setFace(faceColors);
        }

        if (faceColors[1][1] == CubeSketch.CUBE_RED) {
            sketch.fillFace(CubeSketch.DOWN_FACE, faceColors);
            sketch.getCube().setFace(faceColors);
        }

        if (faceColors[1][1] == CubeSketch.CUBE_ORANGE) {
            sketch.fillFace(CubeSketch.UP_FACE, faceColors);
            sketch.getCube().setFace(faceColors);
        }

        if (faceColors[1][1] == CubeSketch.CUBE_GREEN) {
            sketch.fillFace(CubeSketch.RIGHT_FACE, faceColors);
            sketch.getCube().setFace(faceColors);
        }

        if (faceColors[1][1] == CubeSketch.CUBE_YELLOW) {
            int tmp = faceColors[0][0];
            faceColors[0][0] = faceColors[2][0];
            faceColors[2][0] = tmp;

            tmp = faceColors[0][1];
            faceColors[0][1] = faceColors[2][1];
            faceColors[2][1] = tmp;

            tmp = faceColors[0][2];
            faceColors[0][2] = faceColors[2][2];
            faceColors[2][2] = tmp;

            // tmp = faceColors[1][0];
            // faceColors[1][0] = faceColors[1][2];
            // faceColors[1][2] = tmp;

            // tmp = faceColors[2][0];
            // faceColors[2][0] = faceColors[2][2];
            // faceColors[2][2] = tmp;
            sketch.fillFace(CubeSketch.BACK_FACE, faceColors);
            sketch.getCube().setFace(faceColors);
        }
    }

    /**
     * Actualiza el campo de la imagen modificada
     * @param result Imagen que actualizara el campo de imagen modificada
     */
    private void updateCameraImage(BufferedImage resultImage) {
        if (resultImage == null)
            return;
        ImageIcon resultIcon = new ImageIcon(resultImage);
        imageLabelCamera.setIcon(resultIcon);
        // imageLabelCamera.setSize(new Dimension(resultImage.getHeight(), resultImage.getWidth()));
        modifiedImg = resultImage;
    }


    private void updateGrayImage(BufferedImage resultImage) {
        if (resultImage == null)
            return;
        ImageIcon resultIcon = new ImageIcon(resultImage);
        grayLabel.setIcon(resultIcon);
        grayLabel.repaint();
    }


    private void updateHarrisImage(BufferedImage resultImage) {
        if (resultImage == null)
            return;
        ImageIcon resultIcon = new ImageIcon(resultImage);
        harrisLabel.setIcon(resultIcon);
        harrisLabel.repaint();
    }


    private void updateCannyImage(BufferedImage resultImage) {
        if (resultImage == null)
            return;
        ImageIcon resultIcon = new ImageIcon(resultImage);
        cannyLabel.setIcon(resultIcon);
        cannyLabel.repaint();
    }


    private void updateRedImage(BufferedImage resultImage) {
        if (resultImage == null)
            return;
        ImageIcon resultIcon = new ImageIcon(resultImage);
        redLabel.setIcon(resultIcon);
        redLabel.repaint();
    }


    private void updateBlueImage(BufferedImage resultImage) {
        if (resultImage == null)
            return;
        ImageIcon resultIcon = new ImageIcon(resultImage);
        blueLabel.setIcon(resultIcon);
        blueLabel.repaint();
    }


    private void updateYellowImage(BufferedImage resultImage) {
        if (resultImage == null)
            return;
        ImageIcon resultIcon = new ImageIcon(resultImage);
        yellowLabel.setIcon(resultIcon);
        yellowLabel.repaint();
    }


    private void updateOrangeImage(BufferedImage resultImage) {
        if (resultImage == null)
            return;
        ImageIcon resultIcon = new ImageIcon(resultImage);
        orangeLabel.setIcon(resultIcon);
        orangeLabel.repaint();
    }


    private void updateWhiteImage(BufferedImage resultImage) {
        if (resultImage == null)
            return;
        ImageIcon resultIcon = new ImageIcon(resultImage);
        whiteLabel.setIcon(resultIcon);
        whiteLabel.repaint();
    }


    private void updateGreenImage(BufferedImage resultImage) {
        if (resultImage == null)
            return;
        ImageIcon resultIcon = new ImageIcon(resultImage);
        greenLabel.setIcon(resultIcon);
        greenLabel.repaint();
    }


    public void showInfo(){
        JOptionPane.showMessageDialog(frame,
        "Para poder detectar correctamente los colores,\n verifica que la cara de arriba del cubo sea\n la cara que tiene el centro de color naranja.");
    }

    /**
     * Despliega una interfaz que permite cargar una imagen sin mostrarla
     * @return BufferedImage Imagen cargada
     */
    private BufferedImage loadSecondImage() {
        JFileChooser fileChooser = new JFileChooser();

        fileChooser.setFileFilter(new javax.swing.filechooser.FileFilter() {
        public boolean accept(File f) {            
            if (f.isDirectory())
                return true;
            fileName = f.getName().toLowerCase();
            if (fileName.endsWith(".jpg") || fileName.endsWith(".bmp"))
                return true;
            return false;
        }

        public String getDescription() {
          return "Archivo de imagen";
        }
      });
        int status = fileChooser.showOpenDialog(null);
        BufferedImage loaded = null;
        if (status == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            try {
                loaded = ImageIO.read(selectedFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return loaded;
    }


    /**
     * Guarda la imagen
     */
    public void saveImage() {
        try {
            File outputfile = new File(fileName.substring(0, fileName.lastIndexOf(".")) + "_modified.jpg");
            ImageIO.write(modifiedImg, "jpg", outputfile);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }


}
