package visualization;

import processing.core.PApplet;
import processing.core.PVector;
import java.util.LinkedList;
import java.util.ListIterator;
import algorithms.CubeSolver;
import structures.RubiksCube;

public class CubeSketch extends PApplet {

    public static final int LEFT_FACE = 2;
    public static final int RIGHT_FACE = 5;
    public static final int UP_FACE = 1;
    public static final int DOWN_FACE = 4;
    public static final int FRONT_FACE = 0;
    public static final int BACK_FACE = 3;


    private static final int CR = 0;
    private static final int CG = 1;
    private static final int CB = 2;

    private static final int ROTATEFORWARD = 5;
    private static final int ROTATESIDEWAYS = 6;


    public static final int CUBE_WHITE = 0;
    public static final int CUBE_ORANGE = 1;
    public static final int CUBE_GREEN = 2;
    public static final int CUBE_YELLOW = 3;
    public static final int CUBE_RED = 4;
    public static final int CUBE_BLUE = 5;

    private static final int[][] colorTable = {{255, 255, 255}, {255, 88, 0}, {0, 158, 30}, {255, 230, 0}, {204, 0, 0}, {0, 81, 186}};

    int WIDTH = 500;
    int HEIGHT = 500;

    int state = 0;
    int move = 0;
    boolean orientation = true;
    boolean doneReading = false;
    int nRotations = 90;
    int currentRotation = 0;
    int cubieSize = 70;

    int rotationsX = 0;
    int rotationsY = 0;

    //float rotx = -0.39f;
    //float roty = 0.78999984f;
    float rotx = 0;
    float roty = 0;


    int centerX = WIDTH / 2;
    int centerY = HEIGHT / 2;
    int centerZ = -130;

    Cube[] cubies = new Cube[27];

    RubiksCube rubiksCube = new RubiksCube();

    ListIterator<Integer> movesIterator = null;


    public void setup() {
        size(WIDTH, HEIGHT, P3D);
        frameRate(60);

        cubies[0] = new Cube(cubieSize, centerX - cubieSize, centerY - cubieSize, centerZ - cubieSize);
        cubies[1] = new Cube(cubieSize, centerX, centerY - cubieSize, centerZ - cubieSize);
        cubies[2] = new Cube(cubieSize, centerX + cubieSize, centerY - cubieSize, centerZ - cubieSize);

        cubies[3] = new Cube(cubieSize, centerX - cubieSize, centerY, centerZ - cubieSize);
        cubies[4] = new Cube(cubieSize, centerX, centerY, centerZ - cubieSize);
        cubies[5] = new Cube(cubieSize, centerX + cubieSize, centerY, centerZ - cubieSize);

        cubies[6] = new Cube(cubieSize, centerX - cubieSize, centerY + cubieSize, centerZ - cubieSize);
        cubies[7] = new Cube(cubieSize, centerX, centerY + cubieSize, centerZ - cubieSize);
        cubies[8] = new Cube(cubieSize, centerX + cubieSize, centerY + cubieSize, centerZ - cubieSize);

        cubies[9] = new Cube(cubieSize, centerX - cubieSize, centerY - cubieSize, centerZ);
        cubies[10] = new Cube(cubieSize, centerX, centerY - cubieSize, centerZ);
        cubies[11] = new Cube(cubieSize, centerX + cubieSize, centerY - cubieSize, centerZ);

        cubies[12] = new Cube(cubieSize, centerX - cubieSize, centerY, centerZ);
        cubies[13] = new Cube(cubieSize, centerX, centerY, centerZ);
        cubies[14] = new Cube(cubieSize, centerX + cubieSize, centerY, centerZ);

        cubies[15] = new Cube(cubieSize, centerX - cubieSize, centerY + cubieSize, centerZ);
        cubies[16] = new Cube(cubieSize, centerX, centerY + cubieSize, centerZ);
        cubies[17] = new Cube(cubieSize, centerX + cubieSize, centerY + cubieSize, centerZ);


        cubies[18] = new Cube(cubieSize, centerX - cubieSize, centerY - cubieSize, centerZ + cubieSize);
        cubies[19] = new Cube(cubieSize, centerX, centerY - cubieSize, centerZ + cubieSize);
        cubies[20] = new Cube(cubieSize, centerX + cubieSize, centerY - cubieSize, centerZ + cubieSize);

        cubies[21] = new Cube(cubieSize, centerX - cubieSize, centerY, centerZ + cubieSize);
        cubies[22] = new Cube(cubieSize, centerX, centerY, centerZ + cubieSize);
        cubies[23] = new Cube(cubieSize, centerX + cubieSize, centerY, centerZ + cubieSize);

        cubies[24] = new Cube(cubieSize, centerX - cubieSize, centerY + cubieSize, centerZ + cubieSize);
        cubies[25] = new Cube(cubieSize, centerX, centerY + cubieSize, centerZ + cubieSize);
        cubies[26] = new Cube(cubieSize, centerX + cubieSize, centerY + cubieSize, centerZ + cubieSize);

        initCubes();
        // testFill();
        //solve();
    }


    public void setLights() {
      //lights();

    /*
        pointLight(100, 100, 100,   // Color
                 200, -150, 0); // Position

    // Blue directional light from the left
      directionalLight(0, 102, 255, // Color
                       1, 0, 0);    // The x-, y-, z-axis direction
    */
      // Orange point light on the right

    ambientLight(125, 125, 125);

    spotLight(255, 225, 225,  // Color
                0, 40, 600,     // Position
                0, -0.5f, -0.5f,  // Direction
                PI / 2, 0.25f);   
    }


    public void draw() {

        background(220);
        setLights();

      // Center in display window
          translate(width/2, height/2, -130);

        if (state == ROTATEFORWARD) {
            if (rotationsX++ == 155) {
                state = 0;
                rotationsX = 0;
            } else {
                rotx += 0.01f;
            }
        } else if (state == ROTATESIDEWAYS) {
            if (rotationsY++ == 155) {
                state = 0;
                rotationsY = 0;
            } else {
                roty += 0.01f;
            }
        }


        rotateX(rotx);
        rotateY(roty);


        stroke(0);
        strokeCap(ROUND);
        strokeJoin(ROUND);
        strokeWeight(3);

        if (state == 1) {
            //leftMove();
            move(move, orientation);

            if (++currentRotation == nRotations) {
                if (movesIterator != null && movesIterator.hasNext()) {
                    state = 2;
                    currentRotation = 0;
                    updateCubies(move, orientation);
                } else {
                    state = 0;
                    currentRotation = 0;
                    updateCubies(move, orientation);
                    // System.out.println(rubiksCube);
                }
            }
        } else if (state == 2) {
            if (movesIterator.hasNext()) {
                int currentMove = movesIterator.next();
                updateVariables(currentMove);
                state = 1;
            } else {
                state = 0;
            }
        }

        for (int i = 0; i < cubies.length; i++) {
            cubies[i].drawCube();
        }

/*
        if (frameCount % 15 == 0)
            saveFrame("/media/jose/JOSÉ/frames/cube-########.jpg");
*/
    }


    public void updateVariables(int cubeMove) {
        switch (cubeMove) {
            case RubiksCube.F_MOVE : move = 0;
                                     orientation = true;
                                     break;
            case RubiksCube.FI_MOVE : move = 0;
                                     orientation = false;
                                     break;
            case RubiksCube.B_MOVE : move = 3;
                                     orientation = false;
                                     break;
            case RubiksCube.BI_MOVE : move = 3;
                                     orientation = true;
                                     break;
            case RubiksCube.L_MOVE : move = 2;
                                     orientation = true;
                                     break;
            case RubiksCube.LI_MOVE : move = 2;
                                     orientation = false;
                                     break;
            case RubiksCube.R_MOVE : move = 5;
                                     orientation = false;
                                     break;
            case RubiksCube.RI_MOVE : move = 5;
                                     orientation = true;
                                     break;
            case RubiksCube.U_MOVE : move = 1;
                                     orientation = true;
                                     break;
            case RubiksCube.UI_MOVE : move = 1;
                                     orientation = false;
                                     break;
            case RubiksCube.D_MOVE : move = 4;
                                     orientation = false;
                                     break;
            case RubiksCube.DI_MOVE : move = 4;
                                     orientation = true;
                                     break;
            default : break;
        }
    }


    public void solve() {
        CubeSolver cs = new CubeSolver(rubiksCube);
        LinkedList<Integer> moves = cs.solveWhiteCross();
        moves.addAll(cs.solveWhiteCorners());
        moves.addAll(cs.solveMiddleLayer());
        moves.addAll(cs.solveYellowCross());
        moves.addAll(cs.solveYellowCorners());
        moves.addAll(cs.rearrangeYellowCorners());
        moves.addAll(cs.rearrangeYellowEdges());
        movesIterator = moves.listIterator();
        state = 2;
    }


    public void tinyRotation(Cube cube, int axis, boolean orientation) {
        cube.displayAndRotate(axis, orientation);
    }

    public void keyPressed() {
        if (keyCode >= 48 && keyCode <= 53 && state == 0) {
            move = key - 48;
            state = 1;
        } else if (key == 's') {
            solve();
        } else if (key == 'p') {
            System.out.println(rubiksCube);
        } else if (key == 'f') {
            testFill();
            // System.out.println(rubiksCube);
            //state = 3;
        } else if (key == 'r') {
            rotateForward();
        } else if (key == 't') {
            rotateSideways();
        }
        for (Cube c : cubies) {
            // System.out.println(c.getFilledFaces());
        }
    }


    public void mouseDragged() {
        //if (!doneReading)
            //return;
        float rate = 0.01f;
        rotx += (pmouseY-mouseY) * rate;
        roty += (mouseX-pmouseX) * rate;
    }


    public void rotateForward() {
        state = ROTATEFORWARD;
    }


    public void rotateSideways() {
        state = ROTATESIDEWAYS;
    }


    public int getRGB(int[] rgb) {
        return (rgb[CR] << 16) | (rgb[CG] << 8) | rgb[CB];
    }


    private void initCubes() {
        getFrontFace()[1][1].drawCube(Cube.FRONT, getRGB(colorTable[CUBE_WHITE]));
        getUpperFace()[1][1].drawCube(Cube.UP, getRGB(colorTable[CUBE_ORANGE]));
        getLeftFace()[1][1].drawCube(Cube.LEFT, getRGB(colorTable[CUBE_BLUE]));
        getDownFace()[1][1].drawCube(Cube.DOWN, getRGB(colorTable[CUBE_RED]));
        getBackFace()[1][1].drawCube(Cube.BACK, getRGB(colorTable[CUBE_YELLOW]));
        getRightFace()[1][1].drawCube(Cube.RIGHT, getRGB(colorTable[CUBE_GREEN]));
    }
/*
    public void testFill() {
        int[][] faceColors = new int[3][3];
        faceColors[0][0] = CUBE_YELLOW;
        faceColors[0][1] = CUBE_ORANGE;
        faceColors[0][2] = CUBE_ORANGE;
        faceColors[1][0] = CUBE_WHITE;
        faceColors[1][1] = CUBE_WHITE;
        faceColors[1][2] = CUBE_WHITE;
        faceColors[2][0] = CUBE_YELLOW;
        faceColors[2][1] = CUBE_ORANGE;
        faceColors[2][2] = CUBE_ORANGE;
        //System.out.println(getRGB(colorTable[CUBE_ORANGE]));
        
        fillFace(FRONT_FACE, faceColors);
        rubiksCube.setFace(faceColors);


        faceColors[0][0] = CUBE_ORANGE;
        faceColors[0][1] = CUBE_BLUE;
        faceColors[0][2] = CUBE_ORANGE;
        faceColors[1][0] = CUBE_GREEN;
        faceColors[1][1] = CUBE_BLUE;
        faceColors[1][2] = CUBE_BLUE;
        faceColors[2][0] = CUBE_BLUE;
        faceColors[2][1] = CUBE_BLUE;
        faceColors[2][2] = CUBE_BLUE;
        //System.out.println(CUBE_ORANGE);
        fillFace(LEFT_FACE, faceColors);
        rubiksCube.setFace(faceColors);


        faceColors[0][0] = CUBE_YELLOW;
        faceColors[0][1] = CUBE_YELLOW;
        faceColors[0][2] = CUBE_RED;
        faceColors[1][0] = CUBE_GREEN;
        faceColors[1][1] = CUBE_ORANGE;
        faceColors[1][2] = CUBE_ORANGE;
        faceColors[2][0] = CUBE_GREEN;
        faceColors[2][1] = CUBE_ORANGE;
        faceColors[2][2] = CUBE_RED;
        //System.out.println(CUBE_ORANGE);
        fillFace(UP_FACE, faceColors);
        rubiksCube.setFace(faceColors);


        faceColors[0][0] = CUBE_GREEN;
        faceColors[0][1] = CUBE_YELLOW;
        faceColors[0][2] = CUBE_GREEN;
        faceColors[1][0] = CUBE_GREEN;
        faceColors[1][1] = CUBE_GREEN;
        faceColors[1][2] = CUBE_GREEN;
        faceColors[2][0] = CUBE_WHITE;
        faceColors[2][1] = CUBE_YELLOW;
        faceColors[2][2] = CUBE_WHITE;
        //System.out.println(CUBE_ORANGE);
        fillFace(RIGHT_FACE, faceColors);
        rubiksCube.setFace(faceColors);


        faceColors[0][0] = CUBE_BLUE;
        faceColors[0][1] = CUBE_YELLOW;
        faceColors[0][2] = CUBE_GREEN;
        faceColors[1][0] = CUBE_RED;
        faceColors[1][1] = CUBE_YELLOW;
        faceColors[1][2] = CUBE_RED;
        faceColors[2][0] = CUBE_RED;
        faceColors[2][1] = CUBE_RED;
        faceColors[2][2] = CUBE_RED;
        //System.out.println(CUBE_ORANGE);
        fillFace(BACK_FACE, faceColors);


        // Hay que parchar para la cara amarilla ¬¬' PTM
        int tmp = faceColors[0][0];
        faceColors[0][0] = faceColors[0][2];
        faceColors[0][2] = tmp;

        tmp = faceColors[1][0];
        faceColors[1][0] = faceColors[1][2];
        faceColors[1][2] = tmp;

        tmp = faceColors[2][0];
        faceColors[2][0] = faceColors[2][2];
        faceColors[2][2] = tmp;

        rubiksCube.setFace(faceColors);

        faceColors[0][0] = CUBE_WHITE;
        faceColors[0][1] = CUBE_WHITE;
        faceColors[0][2] = CUBE_YELLOW;
        faceColors[1][0] = CUBE_RED;
        faceColors[1][1] = CUBE_RED;
        faceColors[1][2] = CUBE_BLUE;
        faceColors[2][0] = CUBE_WHITE;
        faceColors[2][1] = CUBE_WHITE;
        faceColors[2][2] = CUBE_BLUE;
        //System.out.println(getRGB(colorTable[CUBE_ORANGE]));
        fillFace(DOWN_FACE, faceColors);
        rubiksCube.setFace(faceColors);

    }*/



    public void testFill() {
        int[][] faceColors = new int[3][3];
        faceColors[0][0] = CUBE_GREEN;
        faceColors[0][1] = CUBE_ORANGE;
        faceColors[0][2] = CUBE_ORANGE;
        faceColors[1][0] = CUBE_ORANGE;
        faceColors[1][1] = CUBE_WHITE;
        faceColors[1][2] = CUBE_YELLOW;
        faceColors[2][0] = CUBE_BLUE;
        faceColors[2][1] = CUBE_BLUE;
        faceColors[2][2] = CUBE_ORANGE;
        //System.out.println(getRGB(colorTable[CUBE_ORANGE]));
        
        fillFace(FRONT_FACE, faceColors);
        rubiksCube.setFace(faceColors);


        faceColors[0][0] = CUBE_YELLOW;
        faceColors[0][1] = CUBE_WHITE;
        faceColors[0][2] = CUBE_ORANGE;
        faceColors[1][0] = CUBE_WHITE;
        faceColors[1][1] = CUBE_GREEN;
        faceColors[1][2] = CUBE_RED;
        faceColors[2][0] = CUBE_WHITE;
        faceColors[2][1] = CUBE_GREEN;
        faceColors[2][2] = CUBE_BLUE;
        //System.out.println(CUBE_ORANGE);
        fillFace(LEFT_FACE, faceColors);
        rubiksCube.setFace(faceColors);


        faceColors[0][0] = CUBE_RED;
        faceColors[0][1] = CUBE_ORANGE;
        faceColors[0][2] = CUBE_RED;
        faceColors[1][0] = CUBE_ORANGE;
        faceColors[1][1] = CUBE_ORANGE;
        faceColors[1][2] = CUBE_BLUE;
        faceColors[2][0] = CUBE_WHITE;
        faceColors[2][1] = CUBE_YELLOW;
        faceColors[2][2] = CUBE_YELLOW;
        //System.out.println(CUBE_ORANGE);
        fillFace(UP_FACE, faceColors);
        rubiksCube.setFace(faceColors);


        faceColors[0][0] = CUBE_RED;
        faceColors[0][1] = CUBE_RED;
        faceColors[0][2] = CUBE_GREEN;
        faceColors[1][0] = CUBE_RED;
        faceColors[1][1] = CUBE_BLUE;
        faceColors[1][2] = CUBE_YELLOW;
        faceColors[2][0] = CUBE_RED;
        faceColors[2][1] = CUBE_GREEN;
        faceColors[2][2] = CUBE_ORANGE;
        //System.out.println(CUBE_ORANGE);
        fillFace(RIGHT_FACE, faceColors);
        rubiksCube.setFace(faceColors);


        faceColors[0][0] = CUBE_GREEN;
        faceColors[0][1] = CUBE_BLUE;
        faceColors[0][2] = CUBE_GREEN;
        faceColors[1][0] = CUBE_YELLOW;
        faceColors[1][1] = CUBE_YELLOW;
        faceColors[1][2] = CUBE_WHITE;
        faceColors[2][0] = CUBE_BLUE;
        faceColors[2][1] = CUBE_RED;
        faceColors[2][2] = CUBE_BLUE;
        //System.out.println(CUBE_ORANGE);
        fillFace(BACK_FACE, faceColors);


        // Hay que parchar para la cara amarilla ¬¬' PTM
        int tmp = faceColors[0][0];
        faceColors[0][0] = faceColors[0][2];
        faceColors[0][2] = tmp;

        tmp = faceColors[1][0];
        faceColors[1][0] = faceColors[1][2];
        faceColors[1][2] = tmp;

        tmp = faceColors[2][0];
        faceColors[2][0] = faceColors[2][2];
        faceColors[2][2] = tmp;

        rubiksCube.setFace(faceColors);

        faceColors[0][0] = CUBE_YELLOW;
        faceColors[0][1] = CUBE_WHITE;
        faceColors[0][2] = CUBE_WHITE;
        faceColors[1][0] = CUBE_BLUE;
        faceColors[1][1] = CUBE_RED;
        faceColors[1][2] = CUBE_GREEN;
        faceColors[2][0] = CUBE_YELLOW;
        faceColors[2][1] = CUBE_GREEN;
        faceColors[2][2] = CUBE_WHITE;
        //System.out.println(getRGB(colorTable[CUBE_ORANGE]));
        fillFace(DOWN_FACE, faceColors);
        rubiksCube.setFace(faceColors);

    }





    public Cube[][] getFrontFace() {
        Cube[][] cubes = new Cube[3][3];
        int i = 0;
        int j = 0;
        for (int k = 0; k < 27; k++) {
            if (k >= 18) {
                cubes[i++][j] = cubies[k];
                if (i == 3) {
                    i = 0;
                    j++;
                }
            }
        }
        return cubes;
    }


    public Cube[][] getLeftFace() {
        Cube[][] cubes = new Cube[3][3];
        int i = 0;
        int j = 0;
        for (int k = 0; k < 27; k++) {
            if (k % 3 == 0) {
                cubes[i][j++] = cubies[k];
                if (j == 3) {
                    j = 0;
                    i++;
                }
            }
        }
        return cubes;
    }


    public Cube[][] getUpperFace() {
        Cube[][] cubes = new Cube[3][3];
        int i = 0;
        int j = 0;
        for (int k = 0; k < 27; k++) {
            if (k % 9 <= 2) {
                cubes[i++][j] = cubies[k];
                if (i == 3) {
                    i = 0;
                    j++;
                }
            }
        }
        return cubes;
    }

    public Cube[][] getRightFace() {
        Cube[][] cubes = new Cube[3][3];

        cubes[0][0] = cubies[20];
        cubes[0][1] = cubies[23];
        cubes[0][2] = cubies[26];
        cubes[1][0] = cubies[11];
        cubes[1][1] = cubies[14];
        cubes[1][2] = cubies[17];
        cubes[2][0] = cubies[2];
        cubes[2][1] = cubies[5];
        cubes[2][2] = cubies[8];
        return cubes;
    }


    public Cube[][] getBackFace() {
        Cube[][] cubes = new Cube[3][3];
        int i = 0;
        int j = 0;
        for (int k = 0; k < 27; k++) {
            if (k <= 8) {
                cubes[i++][j] = cubies[k];
                if (i == 3) {
                    i = 0;
                    j++;
                }
            }
        }
        return cubes;
    }


    public Cube[][] getDownFace() {
        Cube[][] cubes = new Cube[3][3];

        cubes[0][0] = cubies[24];
        cubes[0][1] = cubies[15];
        cubes[0][2] = cubies[6];
        cubes[1][0] = cubies[25];
        cubes[1][1] = cubies[16];
        cubes[1][2] = cubies[7];
        cubes[2][0] = cubies[26];
        cubes[2][1] = cubies[17];
        cubes[2][2] = cubies[8];
        return cubes;
    }

    public RubiksCube getCube() {
        return rubiksCube;
    }


    public void fillFace(int face, int[][] colors) {
        switch (face) {
            case FRONT_FACE : matchface(Cube.FRONT, getFrontFace(), colors);
                              break;
            case LEFT_FACE : matchface(Cube.LEFT, getLeftFace(), colors);
                              break;
            case UP_FACE : matchface(Cube.UP, getUpperFace(), colors);
                              break;
            case RIGHT_FACE : matchface(Cube.RIGHT, getRightFace(), colors);
                              break;
            case BACK_FACE : matchface(Cube.BACK, getBackFace(), colors);
                              break;
            case DOWN_FACE : matchface(Cube.DOWN, getDownFace(), colors);
                              break;
            default : break;
        }
    }


    public void matchface(int whichSide, Cube[][] face, int[][] colors) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                face[i][j].drawCube(whichSide, getRGB(colorTable[colors[i][j]]));
            }
        }
    }


    public void move(int face, boolean orientation) {
        for (int i = 0; i < cubies.length; i++) {
            switch (face) {
                case LEFT_FACE : if (i % 3 == 0) tinyRotation(cubies[i], Cube.XAXIS, !orientation);
                                 break;
                case RIGHT_FACE : if (i % 3 == 2) tinyRotation(cubies[i], Cube.XAXIS, !orientation);
                                 break;
                case UP_FACE : if (i % 9 <= 2) tinyRotation(cubies[i], Cube.YAXIS, orientation);
                                 break;
                case DOWN_FACE : if (i % 9 >= 6 && i % 9 <= 8) tinyRotation(cubies[i], Cube.YAXIS, orientation);
                                 break;
                case FRONT_FACE : if (i >= 18) tinyRotation(cubies[i], Cube.ZAXIS, orientation);
                                 break;
                case BACK_FACE : if (i <= 8) tinyRotation(cubies[i], Cube.ZAXIS, orientation);
                                 break;
                default : break;
            }
        }
    }




    public void updateCubies(int face, boolean orientation) {
        Cube tmp;
        switch (face) {
            case LEFT_FACE : if (orientation == Cube.CLOCKWISE) {
                                // System.out.println("Aqui LEFT CLOCKWISE");
                                tmp = cubies[6];
                                cubies[6] = cubies[24];
                                cubies[24] = cubies[18];
                                cubies[18] = cubies[0];
                                cubies[0] = tmp;

                                tmp = cubies[15];
                                cubies[15] = cubies[21];
                                cubies[21] = cubies[9];
                                cubies[9] = cubies[3];
                                cubies[3] = tmp;
                            } else {
                                // System.out.println("Aqui LEFT COUNTERCLOCKWISE");
                                tmp = cubies[0];
                                cubies[0] = cubies[18];
                                cubies[18] = cubies[24];
                                cubies[24] = cubies[6];
                                cubies[6] = tmp;

                                tmp = cubies[3];
                                cubies[3] = cubies[9];
                                cubies[9] = cubies[21];
                                cubies[21] = cubies[15];
                                cubies[15] = tmp;
                            }
                            break;
            case RIGHT_FACE : if (orientation == Cube.CLOCKWISE) {
                                // System.out.println("Aqui RIGHT CLOCKWISE");
                                tmp = cubies[20];
                                cubies[20] = cubies[2];
                                cubies[2] = cubies[8];
                                cubies[8] = cubies[26];
                                cubies[26] = tmp;

                                tmp = cubies[23];
                                cubies[23] = cubies[11];
                                cubies[11] = cubies[5];
                                cubies[5] = cubies[17];
                                cubies[17] = tmp;
                            } else {
                                // System.out.println("Aqui RIGHT COUNTERCLOCKWISE");
                                tmp = cubies[26];
                                cubies[26] = cubies[8];
                                cubies[8] = cubies[2];
                                cubies[2] = cubies[20];
                                cubies[20] = tmp;

                                tmp = cubies[17];
                                cubies[17] = cubies[5];
                                cubies[5] = cubies[11];
                                cubies[11] = cubies[23];
                                cubies[23] = tmp;
                            }
                            break;
            case UP_FACE : if (orientation == Cube.CLOCKWISE) {
                                // System.out.println("Aca UP CLOCKWISE");
                                tmp = cubies[18];
                                cubies[18] = cubies[20];
                                cubies[20] = cubies[2];
                                cubies[2] = cubies[0];
                                cubies[0] = tmp;

                                tmp = cubies[9];
                                cubies[9] = cubies[19];
                                cubies[19] = cubies[11];
                                cubies[11] = cubies[1];
                                cubies[1] = tmp;
                            } else {
                                // System.out.println("Aca UP COUNTERCLOCKWISE");
                                tmp = cubies[0];
                                cubies[0] = cubies[2];
                                cubies[2] = cubies[20];
                                cubies[20] = cubies[18];
                                cubies[18] = tmp;

                                tmp = cubies[1];
                                cubies[1] = cubies[11];
                                cubies[11] = cubies[19];
                                cubies[19] = cubies[9];
                                cubies[9] = tmp;
                            }
                            break;
            case DOWN_FACE : if (orientation == Cube.CLOCKWISE) {
                                // System.out.println("Aca DOWN CLOCKWISE");
                                tmp = cubies[8];
                                cubies[8] = cubies[6];
                                cubies[6] = cubies[24];
                                cubies[24] = cubies[26];
                                cubies[26] = tmp;

                                tmp = cubies[17];
                                cubies[17] = cubies[7];
                                cubies[7] = cubies[15];
                                cubies[15] = cubies[25];
                                cubies[25] = tmp;
                            } else {
                                // System.out.println("Aca DOWN COUNTERCLOCKWISE");
                                tmp = cubies[26];
                                cubies[26] = cubies[24];
                                cubies[24] = cubies[6];
                                cubies[6] = cubies[8];
                                cubies[8] = tmp;

                                tmp = cubies[25];
                                cubies[25] = cubies[15];
                                cubies[15] = cubies[7];
                                cubies[7] = cubies[17];
                                cubies[17] = tmp;
                            }
                            break;
            case FRONT_FACE : if (orientation == Cube.CLOCKWISE) {
                                // System.out.println("Aca front CLOCKWISE");
                                tmp = cubies[20];
                                cubies[20] = cubies[18];
                                cubies[18] = cubies[24];
                                cubies[24] = cubies[26];
                                cubies[26] = tmp;

                                tmp = cubies[23];
                                cubies[23] = cubies[19];
                                cubies[19] = cubies[21];
                                cubies[21] = cubies[25];
                                cubies[25] = tmp;
                            } else {
                                // System.out.println("Aca front COUNTERCLOCKWISE");
                                tmp = cubies[26];
                                cubies[26] = cubies[24];
                                cubies[24] = cubies[18];
                                cubies[18] = cubies[20];
                                cubies[20] = tmp;

                                tmp = cubies[25];
                                cubies[25] = cubies[21];
                                cubies[21] = cubies[19];
                                cubies[19] = cubies[23];
                                cubies[23] = tmp;
                            }
                            break;
            case BACK_FACE : if (orientation == Cube.CLOCKWISE) {
                                // System.out.println("Aca back CLOCKWISE");
                                tmp = cubies[2];
                                cubies[2] = cubies[0];
                                cubies[0] = cubies[6];
                                cubies[6] = cubies[8];
                                cubies[8] = tmp;

                                tmp = cubies[5];
                                cubies[5] = cubies[1];
                                cubies[1] = cubies[3];
                                cubies[3] = cubies[7];
                                cubies[7] = tmp;
                            } else {
                                // System.out.println("Aca back COUNTERCLOCKWISE");
                                tmp = cubies[8];
                                cubies[8] = cubies[6];
                                cubies[6] = cubies[0];
                                cubies[0] = cubies[2];
                                cubies[2] = tmp;

                                tmp = cubies[7];
                                cubies[7] = cubies[3];
                                cubies[3] = cubies[1];
                                cubies[1] = cubies[5];
                                cubies[5] = tmp;
                            }
                            break;
            default : break;
        }
    }


// Custom Cube Class

    class Cube {

        public static final int FRONT = 0;
        public static final int LEFT = 1;
        public static final int RIGHT = 2;
        public static final int BACK = 3;
        public static final int UP = 4;
        public static final int DOWN = 5;

        public static final boolean CLOCKWISE = false;
        public static final boolean COUNTERCLOCKWISE = true;

        public static final int XAXIS = 1;
        public static final int YAXIS = 2;
        public static final int ZAXIS = 3;

        private float xRotation = 0;
        private float yRotation = 0;

        private int filledFaces = 0;

        PVector position;
      
        // Para la rotación en tres ejes
        PVector rotation;



        // Vertices del cubo
        PVector[] vertices = new PVector[24];
        // width, height, depth
        float w, h, d;

        // colors for faces of cube
        int[] quadBG = new int[6];
        int[] quadBGInitial = new int[6];

        public Cube(float size, float posX, float posY, float posZ) {
            this.w = this.h = this.d = size;

            quadBG[0] = (128 << 16) | (128 << 8) | 128;
            quadBG[1] = (128 << 16) | (128 << 8) | 128;
            quadBG[2] = (128 << 16) | (128 << 8) | 128;
            quadBG[3] = (128 << 16) | (128 << 8) | 128;
            quadBG[4] = (128 << 16) | (128 << 8) | 128;
            quadBG[5] = (128 << 16) | (128 << 8) | 128;

            quadBGInitial[0] = (128 << 16) | (128 << 8) | 128;
            quadBGInitial[1] = (128 << 16) | (128 << 8) | 128;
            quadBGInitial[2] = (128 << 16) | (128 << 8) | 128;
            quadBGInitial[3] = (128 << 16) | (128 << 8) | 128;
            quadBGInitial[4] = (128 << 16) | (128 << 8) | 128;
            quadBGInitial[5] = (128 << 16) | (128 << 8) | 128;
           
            // Start in center
            position = new PVector(posX, posY, posZ);

            // Random rotation
            rotation = new PVector(45, 45, 45);

            // cube composed of 6 quads
            //front
            vertices[0] = new PVector((posX - centerX)-w/2, (posY - centerY)-h/2, (posZ - centerZ)+d/2);
            vertices[1] = new PVector((posX - centerX)+w/2, (posY - centerY)-h/2, (posZ - centerZ)+d/2);
            vertices[2] = new PVector((posX - centerX)+w/2, (posY - centerY)+h/2, (posZ - centerZ)+d/2);
            vertices[3] = new PVector((posX - centerX)-w/2, (posY - centerY)+h/2, (posZ - centerZ)+d/2);
            //left
            vertices[4] = new PVector((posX - centerX)-w/2, (posY - centerY)-h/2, (posZ - centerZ)+d/2);
            vertices[5] = new PVector((posX - centerX)-w/2, (posY - centerY)-h/2, (posZ - centerZ)-d/2);
            vertices[6] = new PVector((posX - centerX)-w/2, (posY - centerY)+h/2, (posZ - centerZ)-d/2);
            vertices[7] = new PVector((posX - centerX)-w/2, (posY - centerY)+h/2, (posZ - centerZ)+d/2);
            //right
            vertices[8] = new PVector((posX - centerX)+w/2, (posY - centerY)-h/2, (posZ - centerZ)+d/2);
            vertices[9] = new PVector((posX - centerX)+w/2, (posY - centerY)-h/2, (posZ - centerZ)-d/2);
            vertices[10] = new PVector((posX - centerX)+w/2, (posY - centerY)+h/2, (posZ - centerZ)-d/2);
            vertices[11] = new PVector((posX - centerX)+w/2, (posY - centerY)+h/2, (posZ - centerZ)+d/2);
            //back
            vertices[12] = new PVector((posX - centerX)-w/2, (posY - centerY)-h/2, (posZ - centerZ)-d/2); 
            vertices[13] = new PVector((posX - centerX)+w/2, (posY - centerY)-h/2, (posZ - centerZ)-d/2);
            vertices[14] = new PVector((posX - centerX)+w/2, (posY - centerY)+h/2, (posZ - centerZ)-d/2);
            vertices[15] = new PVector((posX - centerX)-w/2, (posY - centerY)+h/2, (posZ - centerZ)-d/2);
            //top
            vertices[16] = new PVector((posX - centerX)-w/2, (posY - centerY)-h/2, (posZ - centerZ)+d/2);
            vertices[17] = new PVector((posX - centerX)-w/2, (posY - centerY)-h/2, (posZ - centerZ)-d/2);
            vertices[18] = new PVector((posX - centerX)+w/2, (posY - centerY)-h/2, (posZ - centerZ)-d/2);
            vertices[19] = new PVector((posX - centerX)+w/2, (posY - centerY)-h/2, (posZ - centerZ)+d/2);
            //bottom
            vertices[20] = new PVector((posX - centerX)-w/2, (posY - centerY)+h/2, (posZ - centerZ)+d/2);
            vertices[21] = new PVector((posX - centerX)-w/2, (posY - centerY)+h/2, (posZ - centerZ)-d/2);
            vertices[22] = new PVector((posX - centerX)+w/2, (posY - centerY)+h/2, (posZ - centerZ)-d/2);
            vertices[23] = new PVector((posX - centerX)+w/2, (posY - centerY)+h/2, (posZ - centerZ)+d/2);
        } 


        public int getFilledFaces() {
            return filledFaces;
        }


        public void rTransform(PVector p, int axis, float dir){
            float temp;
            if(axis == 1){
              temp = p.y;
              p.y = p.y * cos(radians(dir))- p.z * sin(radians(dir));
              p.z = temp * sin(radians(dir))+ p.z * cos(radians(dir));
            }else if(axis == 2){
              temp = p.x;
              p.x = p.x * cos(radians(dir)) - p.z * sin(radians(dir));
              p.z = temp * sin(radians(dir)) + p.z * cos(radians(dir));
            }else{
              temp = p.x;
              p.x = p.x * cos(radians(dir))- p.y * sin(radians(dir));
              p.y = temp *sin(radians(dir))+ p.y * cos(radians(dir));
            }
        }


        public void drawCube() {
            for (int i=0; i<6; i++) {
              fill(quadBGInitial[i]);
              beginShape(QUADS);
              for (int j=0; j<4; j++) {

                fill(quadBGInitial[i]);
                
                int red = quadBGInitial[i] >> 16 & 0xFF;
                int green = quadBGInitial[i] >> 8 & 0xFF;
                int blue = quadBGInitial[i] & 0xFF;

                int redDest = quadBG[i] >> 16 & 0xFF;
                int greenDest = quadBG[i] >> 8 & 0xFF;
                int blueDest = quadBG[i] & 0xFF;

                if (red != redDest || green != greenDest || blue != blueDest) {
                    if (red < redDest) {
                        red++;
                    } else if (red > redDest) {
                        red--;
                    }
                    if (green < greenDest) {
                        green++;
                    } else if (green > greenDest) {
                        green--;
                    }
                    if (blue < blueDest) {
                        blue++;
                    } else if (blue > blueDest) {
                        blue--;
                    }
                    quadBGInitial[i] = (red << 16) | (green << 8) | blue;
                }


                fill(red, green, blue);

                vertex(vertices[j+4*i].x, vertices[j+4*i].y, vertices[j+4*i].z);
              }
              endShape();
            }
        }


        public void drawCube(int face, int rgb) {
            filledFaces++;
            quadBG[face] = rgb;
        }


        public void mouseRotate(float rotx, float roty) {
            pushMatrix();
            rotateX(rotx);
            rotateY(roty);
            popMatrix();
        }


        public void recordRotation(int axis, boolean counterClockwise) {
            for (int i = 0; i < 24; i++) {
                rTransform(vertices[i], axis, counterClockwise ? -1 : 1);
            }
        }


        public void displayAndRotate(int axis, boolean counterClockwise) {
            pushMatrix();

            recordRotation(axis, counterClockwise);

            stroke(0);
            strokeCap(ROUND);
            strokeJoin(ROUND);
            strokeWeight(3);
            drawCube(); // Farm out shape to another method
            popMatrix();
        }

    }
}