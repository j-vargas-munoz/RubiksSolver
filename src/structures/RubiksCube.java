package structures;

import java.util.LinkedList;

public class RubiksCube {

    public static final String[] COLORS = new String[]{"WHITE", "ORANGE", "GREEN", "YELLOW", "RED", "BLUE"};

    public static final int WHITE = 0;
    public static final int ORANGE = 1;
    public static final int GREEN = 2;
    public static final int YELLOW = 3;
    public static final int RED = 4;
    public static final int BLUE = 5;

    private static final int RIGHT_FACE = 0;
    private static final int LEFT_FACE = 1;
    private static final int UP_FACE = 2;
    private static final int DOWN_FACE = 3;
    private static final int FRONT_FACE = 4;
    private static final int BACK_FACE = 5;


    public static final int R_MOVE = 0;
    public static final int RI_MOVE = 1;
    public static final int L_MOVE = 2;
    public static final int LI_MOVE = 3;
    public static final int B_MOVE = 4;
    public static final int BI_MOVE = 5;
    public static final int D_MOVE = 6;
    public static final int DI_MOVE = 7;
    public static final int F_MOVE = 8;
    public static final int FI_MOVE = 9;
    public static final int U_MOVE = 10;
    public static final int UI_MOVE = 11;



    private int[][][] faces;
    private MiniCube[][][] cubies;


    public RubiksCube() {
        faces = new int[6][3][3];
        cubies = new MiniCube[3][3][3];
        /*for (MiniCube c : cubies) {
            c = new MiniCube();
        }*/
    }


    public void setFace(int[][] face) {
        int whichFace = face[1][1];
        faces[whichFace] = new int[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                faces[whichFace][i][j] = face[i][j];
            }
        }
        int tmp = faces[whichFace][0][1];
        faces[whichFace][0][1] = faces[whichFace][1][0];
        faces[whichFace][1][0] = tmp;

        tmp = faces[whichFace][2][0];
        faces[whichFace][2][0] = faces[whichFace][0][2];
        faces[whichFace][0][2] = tmp;

        tmp = faces[whichFace][1][2];
        faces[whichFace][1][2] = faces[whichFace][2][1];
        faces[whichFace][2][1] = tmp;
    }



    public int[][] getFace(int color) {
        return faces[color];
    }


    public int[][][] getFaces() {
        return faces;
    }


    /**
     *
     *  -----------  
     * | 0 | 1 | 2 |
     *  -----------
     * | 3 | 4 | 5 |
     *  -----------
     * | 6 | 7 | 8 |
     *  -----------
     *
     */
    public int getColorAt(int faceColor, int position) {
        int i = position / 3;
        int j = position % 3;
        return faces[faceColor][i][j];
    }

    public void setColorAt(int faceColor, int position, int color) {
        int i = position / 3;
        int j = position % 3;
        faces[faceColor][i][j] = color;
    }

    public void twistFace(int face, boolean counterClockwise) {
        switch (face) {
            case WHITE : twistWhiteFace(counterClockwise);
                         break;
            case ORANGE : twistOrangeFace(counterClockwise);
                            break;
            case BLUE : twistBlueFace(counterClockwise);
                        break;
            case RED : twistRedFace(counterClockwise);
                        break;
            case GREEN : twistGreenFace(counterClockwise);
                         break;
            case YELLOW : twistYellowFace(counterClockwise);
                            break;
        }
    }


    public void twistWhiteFace(boolean counterClockwise) {
        int[] tmpColors = new int[3];
        int faceFacingMe = WHITE;
        
        if (counterClockwise) {
            for (int i = 0; i < tmpColors.length; i++) {
                tmpColors[i] = getColorAt(getLeftFace(faceFacingMe), 3*i + 2);
            }

            for (int i = 0; i < tmpColors.length; i++) {
                setColorAt(getLeftFace(faceFacingMe), 3*i + 2, getColorAt(getUpperFace(faceFacingMe), (2-i) + 6));
            }

            for (int i = 0; i < tmpColors.length; i++) {
                setColorAt(getUpperFace(faceFacingMe), i + 6, getColorAt(getRightFace(faceFacingMe), 3*i));
            }

            for (int i = 0; i < tmpColors.length; i++) {
                setColorAt(getRightFace(faceFacingMe), 3*i, getColorAt(getLowerFace(faceFacingMe), 2-i));
            }

            for (int i = 0; i < tmpColors.length; i++) {
                setColorAt(getLowerFace(faceFacingMe), i, tmpColors[i]);
            }

            int tmp = getColorAt(faceFacingMe, 1);
            setColorAt(faceFacingMe, 1, getColorAt(faceFacingMe, 5));
            setColorAt(faceFacingMe, 5, getColorAt(faceFacingMe, 7));
            setColorAt(faceFacingMe, 7, getColorAt(faceFacingMe, 3));
            setColorAt(faceFacingMe, 3, tmp);

            tmp = getColorAt(faceFacingMe, 0);
            setColorAt(faceFacingMe, 0, getColorAt(faceFacingMe, 2));
            setColorAt(faceFacingMe, 2, getColorAt(faceFacingMe, 8));
            setColorAt(faceFacingMe, 8, getColorAt(faceFacingMe, 6));
            setColorAt(faceFacingMe, 6, tmp);

        } else {
            for (int i = 0; i < tmpColors.length; i++) {
                tmpColors[i] = getColorAt(getLeftFace(faceFacingMe), 3*i + 2);
            }

            for (int i = 0; i < tmpColors.length; i++) {
                setColorAt(getLeftFace(faceFacingMe), 3*i + 2, getColorAt(getLowerFace(faceFacingMe), i));
            }

            for (int i = 0; i < tmpColors.length; i++) {
                setColorAt(getLowerFace(faceFacingMe), i, getColorAt(getRightFace(faceFacingMe), 3*(2-i)));
            }

            for (int i = 0; i < tmpColors.length; i++) {
                setColorAt(getRightFace(faceFacingMe), 3*i, getColorAt(getUpperFace(faceFacingMe), i + 6));
            }

            for (int i = 0; i < tmpColors.length; i++) {
                setColorAt(getUpperFace(faceFacingMe), i + 6, tmpColors[2-i]);
            }
            
            int tmp = getColorAt(faceFacingMe, 1);
            setColorAt(faceFacingMe, 1, getColorAt(faceFacingMe, 3));
            setColorAt(faceFacingMe, 3, getColorAt(faceFacingMe, 7));
            setColorAt(faceFacingMe, 7, getColorAt(faceFacingMe, 5));
            setColorAt(faceFacingMe, 5, tmp);

            tmp = getColorAt(faceFacingMe, 0);
            setColorAt(faceFacingMe, 0, getColorAt(faceFacingMe, 6));
            setColorAt(faceFacingMe, 6, getColorAt(faceFacingMe, 8));
            setColorAt(faceFacingMe, 8, getColorAt(faceFacingMe, 2));
            setColorAt(faceFacingMe, 2, tmp);
        }
    }


    public void twistOrangeFace(boolean counterClockwise) {
        int[] tmpColors = new int[3];
        int faceFacingMe = ORANGE;
        
        if (counterClockwise) {
            for (int i = 0; i < tmpColors.length; i++) {
                tmpColors[i] = getColorAt(getLeftFace(faceFacingMe), i);
            }

            for (int i = 0; i < tmpColors.length; i++) {
                setColorAt(getLeftFace(faceFacingMe), i, getColorAt(getUpperFace(faceFacingMe), (2-i) + 6));
            }

            for (int i = 0; i < tmpColors.length; i++) {
                setColorAt(getUpperFace(faceFacingMe), i + 6, getColorAt(getRightFace(faceFacingMe), 2-i));
            }

            for (int i = 0; i < tmpColors.length; i++) {
                setColorAt(getRightFace(faceFacingMe), i, getColorAt(getLowerFace(faceFacingMe), i));
            }

            for (int i = 0; i < tmpColors.length; i++) {
                setColorAt(getLowerFace(faceFacingMe), i, tmpColors[i]);
            }

            int tmp = getColorAt(faceFacingMe, 1);
            setColorAt(faceFacingMe, 1, getColorAt(faceFacingMe, 5));
            setColorAt(faceFacingMe, 5, getColorAt(faceFacingMe, 7));
            setColorAt(faceFacingMe, 7, getColorAt(faceFacingMe, 3));
            setColorAt(faceFacingMe, 3, tmp);

            tmp = getColorAt(faceFacingMe, 0);
            setColorAt(faceFacingMe, 0, getColorAt(faceFacingMe, 2));
            setColorAt(faceFacingMe, 2, getColorAt(faceFacingMe, 8));
            setColorAt(faceFacingMe, 8, getColorAt(faceFacingMe, 6));
            setColorAt(faceFacingMe, 6, tmp);

        } else {
            for (int i = 0; i < tmpColors.length; i++) {
                tmpColors[i] = getColorAt(getLeftFace(faceFacingMe), i);
            }

            for (int i = 0; i < tmpColors.length; i++) {
                setColorAt(getLeftFace(faceFacingMe), i, getColorAt(getLowerFace(faceFacingMe), i));
            }

            for (int i = 0; i < tmpColors.length; i++) {
                setColorAt(getLowerFace(faceFacingMe), i, getColorAt(getRightFace(faceFacingMe), i));
            }

            for (int i = 0; i < tmpColors.length; i++) {
                setColorAt(getRightFace(faceFacingMe), 2-i, getColorAt(getUpperFace(faceFacingMe), i + 6));
            }

            for (int i = 0; i < tmpColors.length; i++) {
                setColorAt(getUpperFace(faceFacingMe), i + 6, tmpColors[2-i]);
            }
            
            int tmp = getColorAt(faceFacingMe, 1);
            setColorAt(faceFacingMe, 1, getColorAt(faceFacingMe, 3));
            setColorAt(faceFacingMe, 3, getColorAt(faceFacingMe, 7));
            setColorAt(faceFacingMe, 7, getColorAt(faceFacingMe, 5));
            setColorAt(faceFacingMe, 5, tmp);

            tmp = getColorAt(faceFacingMe, 0);
            setColorAt(faceFacingMe, 0, getColorAt(faceFacingMe, 6));
            setColorAt(faceFacingMe, 6, getColorAt(faceFacingMe, 8));
            setColorAt(faceFacingMe, 8, getColorAt(faceFacingMe, 2));
            setColorAt(faceFacingMe, 2, tmp);
        }
    }


    public void twistYellowFace(boolean counterClockwise) {
        int[] tmpColors = new int[3];
        int faceFacingMe = YELLOW;
        
        if (counterClockwise) {
            for (int i = 0; i < tmpColors.length; i++) {
                tmpColors[i] = getColorAt(getLeftFace(faceFacingMe), 3*(2-i));
            }

            for (int i = 0; i < tmpColors.length; i++) {
                setColorAt(getLeftFace(faceFacingMe), 3*(2-i), getColorAt(getUpperFace(faceFacingMe), (2-i) + 6));
            }

            for (int i = 0; i < tmpColors.length; i++) {
                setColorAt(getUpperFace(faceFacingMe), i + 6, getColorAt(getRightFace(faceFacingMe), 3*(2-i) + 2));
            }

            for (int i = 0; i < tmpColors.length; i++) {
                setColorAt(getRightFace(faceFacingMe), 3*i + 2, getColorAt(getLowerFace(faceFacingMe), i));
            }

            for (int i = 0; i < tmpColors.length; i++) {
                setColorAt(getLowerFace(faceFacingMe), i, tmpColors[i]);
            }

            int tmp = getColorAt(faceFacingMe, 1);
            setColorAt(faceFacingMe, 1, getColorAt(faceFacingMe, 5));
            setColorAt(faceFacingMe, 5, getColorAt(faceFacingMe, 7));
            setColorAt(faceFacingMe, 7, getColorAt(faceFacingMe, 3));
            setColorAt(faceFacingMe, 3, tmp);

            tmp = getColorAt(faceFacingMe, 0);
            setColorAt(faceFacingMe, 0, getColorAt(faceFacingMe, 2));
            setColorAt(faceFacingMe, 2, getColorAt(faceFacingMe, 8));
            setColorAt(faceFacingMe, 8, getColorAt(faceFacingMe, 6));
            setColorAt(faceFacingMe, 6, tmp);

        } else {
            for (int i = 0; i < tmpColors.length; i++) {
                tmpColors[i] = getColorAt(getLeftFace(faceFacingMe), 3*(2-i));
            }

            for (int i = 0; i < tmpColors.length; i++) {
                setColorAt(getLeftFace(faceFacingMe), 3*(2-i), getColorAt(getLowerFace(faceFacingMe), i));
            }

            for (int i = 0; i < tmpColors.length; i++) {
                setColorAt(getLowerFace(faceFacingMe), i, getColorAt(getRightFace(faceFacingMe), 3*i + 2));
            }

            for (int i = 0; i < tmpColors.length; i++) {
                setColorAt(getRightFace(faceFacingMe), 3*i + 2, getColorAt(getUpperFace(faceFacingMe), (2-i) + 6));
            }

            for (int i = 0; i < tmpColors.length; i++) {
                setColorAt(getUpperFace(faceFacingMe), i + 6, tmpColors[2-i]);
            }
            
            int tmp = getColorAt(faceFacingMe, 1);
            setColorAt(faceFacingMe, 1, getColorAt(faceFacingMe, 3));
            setColorAt(faceFacingMe, 3, getColorAt(faceFacingMe, 7));
            setColorAt(faceFacingMe, 7, getColorAt(faceFacingMe, 5));
            setColorAt(faceFacingMe, 5, tmp);

            tmp = getColorAt(faceFacingMe, 0);
            setColorAt(faceFacingMe, 0, getColorAt(faceFacingMe, 6));
            setColorAt(faceFacingMe, 6, getColorAt(faceFacingMe, 8));
            setColorAt(faceFacingMe, 8, getColorAt(faceFacingMe, 2));
            setColorAt(faceFacingMe, 2, tmp);
        }
    }


    public void twistRedFace(boolean counterClockwise) {
        int[] tmpColors = new int[3];
        int faceFacingMe = RED;
        
        if (counterClockwise) {
            for (int i = 0; i < tmpColors.length; i++) {
                tmpColors[i] = getColorAt(getLeftFace(faceFacingMe), 8-i);
            }

            for (int i = 0; i < tmpColors.length; i++) {
                setColorAt(getLeftFace(faceFacingMe), 8-i, getColorAt(getUpperFace(faceFacingMe), (2-i) + 6));
            }

            for (int i = 0; i < tmpColors.length; i++) {
                setColorAt(getUpperFace(faceFacingMe), i + 6, getColorAt(getRightFace(faceFacingMe), i+6));
            }

            for (int i = 0; i < tmpColors.length; i++) {
                setColorAt(getRightFace(faceFacingMe), (2-i)+6, getColorAt(getLowerFace(faceFacingMe), i));
            }

            for (int i = 0; i < tmpColors.length; i++) {
                setColorAt(getLowerFace(faceFacingMe), i, tmpColors[i]);
            }

            int tmp = getColorAt(faceFacingMe, 1);
            setColorAt(faceFacingMe, 1, getColorAt(faceFacingMe, 5));
            setColorAt(faceFacingMe, 5, getColorAt(faceFacingMe, 7));
            setColorAt(faceFacingMe, 7, getColorAt(faceFacingMe, 3));
            setColorAt(faceFacingMe, 3, tmp);

            tmp = getColorAt(faceFacingMe, 0);
            setColorAt(faceFacingMe, 0, getColorAt(faceFacingMe, 2));
            setColorAt(faceFacingMe, 2, getColorAt(faceFacingMe, 8));
            setColorAt(faceFacingMe, 8, getColorAt(faceFacingMe, 6));
            setColorAt(faceFacingMe, 6, tmp);

        } else {
            for (int i = 0; i < tmpColors.length; i++) {
                tmpColors[i] = getColorAt(getLeftFace(faceFacingMe), 8-i);
            }

            for (int i = 0; i < tmpColors.length; i++) {
                setColorAt(getLeftFace(faceFacingMe), 8-i, getColorAt(getLowerFace(faceFacingMe), i));
            }

            for (int i = 0; i < tmpColors.length; i++) {
                setColorAt(getLowerFace(faceFacingMe), i, getColorAt(getRightFace(faceFacingMe), (2-i)+6));
            }

            for (int i = 0; i < tmpColors.length; i++) {
                setColorAt(getRightFace(faceFacingMe), i+6, getColorAt(getUpperFace(faceFacingMe), i+6));
            }

            for (int i = 0; i < tmpColors.length; i++) {
                setColorAt(getUpperFace(faceFacingMe), i + 6, tmpColors[2-i]);
            }
            
            int tmp = getColorAt(faceFacingMe, 1);
            setColorAt(faceFacingMe, 1, getColorAt(faceFacingMe, 3));
            setColorAt(faceFacingMe, 3, getColorAt(faceFacingMe, 7));
            setColorAt(faceFacingMe, 7, getColorAt(faceFacingMe, 5));
            setColorAt(faceFacingMe, 5, tmp);

            tmp = getColorAt(faceFacingMe, 0);
            setColorAt(faceFacingMe, 0, getColorAt(faceFacingMe, 6));
            setColorAt(faceFacingMe, 6, getColorAt(faceFacingMe, 8));
            setColorAt(faceFacingMe, 8, getColorAt(faceFacingMe, 2));
            setColorAt(faceFacingMe, 2, tmp);
        }
    }


    public void twistGreenFace(boolean counterClockwise) {
        int[] tmpColors = new int[3];
        int faceFacingMe = GREEN;
        
        if (counterClockwise) {
            for (int i = 0; i < tmpColors.length; i++) {
                tmpColors[i] = getColorAt(getLeftFace(faceFacingMe), 3*i);
            }

            for (int i = 0; i < tmpColors.length; i++) {
                setColorAt(getLeftFace(faceFacingMe), 3*(2-i), getColorAt(getUpperFace(faceFacingMe), 3*(2-i)));
            }

            for (int i = 0; i < tmpColors.length; i++) {
                setColorAt(getUpperFace(faceFacingMe), 3*(2-i), getColorAt(getRightFace(faceFacingMe), 3*(2-i)));
            }

            for (int i = 0; i < tmpColors.length; i++) {
                setColorAt(getRightFace(faceFacingMe), 3*(2-i), getColorAt(getLowerFace(faceFacingMe), 3*(2-i)));
            }

            for (int i = 0; i < tmpColors.length; i++) {
                setColorAt(getLowerFace(faceFacingMe), 3*i, tmpColors[i]);
            }

            int tmp = getColorAt(faceFacingMe, 1);
            setColorAt(faceFacingMe, 1, getColorAt(faceFacingMe, 5));
            setColorAt(faceFacingMe, 5, getColorAt(faceFacingMe, 7));
            setColorAt(faceFacingMe, 7, getColorAt(faceFacingMe, 3));
            setColorAt(faceFacingMe, 3, tmp);

            tmp = getColorAt(faceFacingMe, 0);
            setColorAt(faceFacingMe, 0, getColorAt(faceFacingMe, 2));
            setColorAt(faceFacingMe, 2, getColorAt(faceFacingMe, 8));
            setColorAt(faceFacingMe, 8, getColorAt(faceFacingMe, 6));
            setColorAt(faceFacingMe, 6, tmp);

        } else {
            for (int i = 0; i < tmpColors.length; i++) {
                tmpColors[i] = getColorAt(getLeftFace(faceFacingMe), 3*(2-i));
            }

            for (int i = 0; i < tmpColors.length; i++) {
                setColorAt(getLeftFace(faceFacingMe), 3*(2-i), getColorAt(getLowerFace(faceFacingMe), 3*(2-i)));
            }

            for (int i = 0; i < tmpColors.length; i++) {
                setColorAt(getLowerFace(faceFacingMe), 3*i, getColorAt(getRightFace(faceFacingMe), 3*i));
            }

            for (int i = 0; i < tmpColors.length; i++) {
                setColorAt(getRightFace(faceFacingMe), 3*i, getColorAt(getUpperFace(faceFacingMe), 3*i));
            }

            for (int i = 0; i < tmpColors.length; i++) {
                setColorAt(getUpperFace(faceFacingMe), 3*i, tmpColors[2-i]);
            }
            
            int tmp = getColorAt(faceFacingMe, 1);
            setColorAt(faceFacingMe, 1, getColorAt(faceFacingMe, 3));
            setColorAt(faceFacingMe, 3, getColorAt(faceFacingMe, 7));
            setColorAt(faceFacingMe, 7, getColorAt(faceFacingMe, 5));
            setColorAt(faceFacingMe, 5, tmp);

            tmp = getColorAt(faceFacingMe, 0);
            setColorAt(faceFacingMe, 0, getColorAt(faceFacingMe, 6));
            setColorAt(faceFacingMe, 6, getColorAt(faceFacingMe, 8));
            setColorAt(faceFacingMe, 8, getColorAt(faceFacingMe, 2));
            setColorAt(faceFacingMe, 2, tmp);
        }
    }


    public void twistBlueFace(boolean counterClockwise) {
        int[] tmpColors = new int[3];
        int faceFacingMe = BLUE;
        
        if (counterClockwise) {
            for (int i = 0; i < tmpColors.length; i++) {
                tmpColors[i] = getColorAt(getLeftFace(faceFacingMe), 3*i + 2);
            }

            for (int i = 0; i < tmpColors.length; i++) {
                setColorAt(getLeftFace(faceFacingMe), 3*i+2, getColorAt(getUpperFace(faceFacingMe), 3*i+2));
            }

            for (int i = 0; i < tmpColors.length; i++) {
                setColorAt(getUpperFace(faceFacingMe), 3*i+2, getColorAt(getRightFace(faceFacingMe), 3*i+2));
            }

            for (int i = 0; i < tmpColors.length; i++) {
                setColorAt(getRightFace(faceFacingMe), 3*i+2, getColorAt(getLowerFace(faceFacingMe), 3*i+2));
            }

            for (int i = 0; i < tmpColors.length; i++) {
                setColorAt(getLowerFace(faceFacingMe), 3*i+2, tmpColors[i]);
            }

            int tmp = getColorAt(faceFacingMe, 1);
            setColorAt(faceFacingMe, 1, getColorAt(faceFacingMe, 5));
            setColorAt(faceFacingMe, 5, getColorAt(faceFacingMe, 7));
            setColorAt(faceFacingMe, 7, getColorAt(faceFacingMe, 3));
            setColorAt(faceFacingMe, 3, tmp);

            tmp = getColorAt(faceFacingMe, 0);
            setColorAt(faceFacingMe, 0, getColorAt(faceFacingMe, 2));
            setColorAt(faceFacingMe, 2, getColorAt(faceFacingMe, 8));
            setColorAt(faceFacingMe, 8, getColorAt(faceFacingMe, 6));
            setColorAt(faceFacingMe, 6, tmp);

        } else {
            for (int i = 0; i < tmpColors.length; i++) {
                tmpColors[i] = getColorAt(getLeftFace(faceFacingMe), 3*i + 2);
            }

            for (int i = 0; i < tmpColors.length; i++) {
                setColorAt(getLeftFace(faceFacingMe), 3*i + 2, getColorAt(getLowerFace(faceFacingMe), 3*i + 2));
            }

            for (int i = 0; i < tmpColors.length; i++) {
                setColorAt(getLowerFace(faceFacingMe), 3*i + 2, getColorAt(getRightFace(faceFacingMe), 3*i + 2));
            }

            for (int i = 0; i < tmpColors.length; i++) {
                setColorAt(getRightFace(faceFacingMe), 3*i + 2, getColorAt(getUpperFace(faceFacingMe), 3*i + 2));
            }

            for (int i = 0; i < tmpColors.length; i++) {
                setColorAt(getUpperFace(faceFacingMe), 3*i + 2, tmpColors[i]);
            }
            
            int tmp = getColorAt(faceFacingMe, 1);
            setColorAt(faceFacingMe, 1, getColorAt(faceFacingMe, 3));
            setColorAt(faceFacingMe, 3, getColorAt(faceFacingMe, 7));
            setColorAt(faceFacingMe, 7, getColorAt(faceFacingMe, 5));
            setColorAt(faceFacingMe, 5, tmp);

            tmp = getColorAt(faceFacingMe, 0);
            setColorAt(faceFacingMe, 0, getColorAt(faceFacingMe, 6));
            setColorAt(faceFacingMe, 6, getColorAt(faceFacingMe, 8));
            setColorAt(faceFacingMe, 8, getColorAt(faceFacingMe, 2));
            setColorAt(faceFacingMe, 2, tmp);
        }
    }


    public int getOppositeFace(int face) {
        return (face + 3) % 6;
    }

    /**
     * Si la cara blanca está mirando hacia nosotros con la verde a la izquierda
     */
    public int getLeftFace(int face) {
        switch (face) {
            case WHITE : return GREEN;
            case BLUE : return WHITE;
            case GREEN : return YELLOW;
            case ORANGE : return GREEN;
            case YELLOW : return GREEN;
            case RED : return GREEN;
        }
        return -1;
    }


    public int getRightFace(int face) {
        return getOppositeFace(getLeftFace(face));
    }


    public int getUpperFace(int face) {
        switch (face) {
            case WHITE : return ORANGE;
            case ORANGE : return YELLOW;
            case YELLOW : return RED;
            case RED : return WHITE;
            case GREEN : return ORANGE;
            case BLUE : return ORANGE;
        }
        return -1;
    }


    public int getLowerFace(int face) {
        return getOppositeFace(getUpperFace(face));
    }




    @Override
    public String toString() {
        String display = "";
        for (int i = 0; i < 6; i++)
            display += COLORS[i] + " FACE: \n" + faceToString(faces[i]) + "\n\n";
        return display;
    }



    public String faceToString(int[][] face) {
        String f = "";
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++)
                f += COLORS[face[i][j]] + "\t";
            f += "\n";
        }
        return f;
    }


    public LinkedList<Integer> getEdges(int color) {
        LinkedList<Integer> edges = new LinkedList<Integer>();
        edges.add(getColorAt(color, 1));
        edges.add(getColorAt(color, 3));
        edges.add(getColorAt(color, 5));
        edges.add(getColorAt(color, 7));
        return edges;
    }


    public int getAdjacentEdgePosition(int face, int pos) {
        if (face == WHITE) {
            switch (pos) {
                case 1 : return 7;
                case 5 : return 3;
                case 3 : return 5;
                case 7 : return 1;
            }
        } else if (face == ORANGE) {
            switch (pos) {
                case 1 : return 7;
                case 3 : return 1;
                case 5 : return 1;
                case 7 : return 1;
            }
        } else if (face == YELLOW) {
            switch (pos) {
                case 1 : return 7;
                case 3 : return 3;
                case 5 : return 5;
                case 7 : return 1;
            }
        } else if (face == RED) {
            switch (pos) {
                case 1 : return 7;
                case 3 : return 7;
                case 5 : return 7;
                case 7 : return 1;
            }
        } else if (face == GREEN) {
            switch (pos) {
                case 1 : return 3;
                case 3 : return 3;
                case 5 : return 3;
                case 7 : return 3;
            }
        } else if (face == BLUE) {
            switch (pos) {
                case 1 : return 5;
                case 3 : return 5;
                case 5 : return 5;
                case 7 : return 5;
            }
        }
        return 0;
    }


    public int[] getEdge(int firstColor, int secondColor) {
        for (int j = 0; j < 6; j++) {
            int[][] currentFace = faces[j];
            for (int i = 1; i <= 7; i+=2) {
                int x = i / 3;
                int y = i % 3;
                if (currentFace[x][y] == firstColor) {
                    int adjacent = getAdjacentEdgePosition(j, i);
                    int whichFace = 0;
                    switch (i) {
                        case 1 : whichFace = getUpperFace(j);
                                 if (getColorAt(whichFace, adjacent) == secondColor)
                                    return new int[]{j, i, whichFace};
                                 break;
                        case 3 : whichFace = getLeftFace(j);
                                 if (getColorAt(whichFace, adjacent) == secondColor)
                                    return new int[]{j, i, whichFace};
                                 break;
                        case 5 : whichFace = getRightFace(j);
                                 if (getColorAt(whichFace, adjacent) == secondColor)
                                    return new int[]{j, i, whichFace};
                                 break;
                        case 7 : whichFace = getLowerFace(j);
                                 if (getColorAt(whichFace, adjacent) == secondColor)
                                    return new int[]{j, i, whichFace};
                                 break;
                    }
                }
            }
        }
        return null;
    }


    public int[] getAdjacentCornersPosition(int face, int pos) {
        if (face == WHITE) {
            switch (pos) {
                case 0 : return new int[]{GREEN, 2, ORANGE, 6};
                case 2 : return new int[]{BLUE, 0, ORANGE, 8};
                case 6 : return new int[]{RED, 0, GREEN, 8};
                case 8 : return new int[]{RED, 2, BLUE, 6};
            }
        } else if (face == ORANGE) {
            switch (pos) {
                case 0 : return new int[]{GREEN, 0, YELLOW, 6};
                case 2 : return new int[]{BLUE, 2, YELLOW, 8};
                case 6 : return new int[]{GREEN, 2, WHITE, 0};
                case 8 : return new int[]{WHITE, 2, BLUE, 0};
            }
        } else if (face == YELLOW) {
            switch (pos) {
                case 0 : return new int[]{GREEN, 6, RED, 6};
                case 2 : return new int[]{RED, 8, BLUE, 8};
                case 6 : return new int[]{RED, 0, ORANGE, 0};
                case 8 : return new int[]{ORANGE, 2, BLUE, 2};
            }
        } else if (face == RED) {
            switch (pos) {
                case 0 : return new int[]{GREEN, 8, WHITE, 6};
                case 2 : return new int[]{WHITE, 8, BLUE, 6};
                case 6 : return new int[]{GREEN, 6, YELLOW, 0};
                case 8 : return new int[]{YELLOW, 2, BLUE, 8};
            }
        } else if (face == GREEN) {
            switch (pos) {
                case 0 : return new int[]{ORANGE, 0, YELLOW, 6};
                case 2 : return new int[]{ORANGE, 6, WHITE, 0};
                case 6 : return new int[]{YELLOW, 0, RED, 6};
                case 8 : return new int[]{RED, 0, WHITE, 6};
            }
        } else if (face == BLUE) {
            switch (pos) {
                case 0 : return new int[]{WHITE, 2, ORANGE, 8};
                case 2 : return new int[]{ORANGE, 2, YELLOW, 8};
                case 6 : return new int[]{RED, 2, WHITE, 8};
                case 8 : return new int[]{YELLOW, 2, RED, 8};
            }
        }
        return null;
    }


    public int[] getCorner(int firstColor, int secondColor, int thirdColor) {
        for (int j = 0; j < 6; j++) {
            int[][] currentFace = faces[j];
            for (int i = 0; i <= 8; i+=2) {
                if (i == 4)
                    continue;

                int x = i / 3;
                int y = i % 3;
                if (currentFace[x][y] == firstColor) {
                    int[] adjacent = getAdjacentCornersPosition(j, i);


                    if ((getColorAt(adjacent[0], adjacent[1]) == secondColor && getColorAt(adjacent[2], adjacent[3]) == thirdColor)
                        || (getColorAt(adjacent[0], adjacent[1]) == thirdColor && getColorAt(adjacent[2], adjacent[3]) == secondColor))
                        return new int[]{j, i};
                }
            }
        }
        return null;
    }


    public void fill() {
        int[][] whiteFace = new int[][]{{RubiksCube.GREEN, RubiksCube.ORANGE, RubiksCube.BLUE},
                                        {RubiksCube.ORANGE, RubiksCube.WHITE, RubiksCube.BLUE},
                                        {RubiksCube.ORANGE, RubiksCube.YELLOW, RubiksCube.ORANGE}};
        int[][] orangeFace = new int[][]{{RubiksCube.RED, RubiksCube.ORANGE, RubiksCube.WHITE},
                                        {RubiksCube.ORANGE, RubiksCube.ORANGE, RubiksCube. YELLOW},
                                        {RubiksCube.RED, RubiksCube.BLUE, RubiksCube.YELLOW}};
        int[][] yellowFace = new int[][]{{RubiksCube.GREEN, RubiksCube.WHITE, RubiksCube.BLUE},
                                        {RubiksCube.BLUE, RubiksCube.YELLOW, RubiksCube.RED},
                                        {RubiksCube.GREEN, RubiksCube.YELLOW, RubiksCube.BLUE}};
        int[][] redFace = new int[][]{{RubiksCube.YELLOW, RubiksCube.BLUE, RubiksCube.YELLOW},
                                      {RubiksCube.WHITE, RubiksCube.RED, RubiksCube.GREEN},
                                      {RubiksCube.WHITE, RubiksCube.GREEN, RubiksCube.WHITE}};
        int[][] greenFace = new int[][]{{RubiksCube.YELLOW, RubiksCube.WHITE, RubiksCube.WHITE},
                                        {RubiksCube.WHITE, RubiksCube.GREEN, RubiksCube.GREEN},
                                        {RubiksCube.ORANGE, RubiksCube.RED, RubiksCube.BLUE}};
        int[][] blueFace = new int[][]{{RubiksCube.RED, RubiksCube.RED, RubiksCube.RED},
                                        {RubiksCube.RED,RubiksCube.BLUE, RubiksCube.GREEN},
                                        {RubiksCube.GREEN, RubiksCube.YELLOW, RubiksCube.ORANGE}};



        //System.out.println(faceToString(whiteFace));
        setFace(whiteFace);
        setFace(orangeFace);
        setFace(yellowFace);
        setFace(redFace);
        setFace(greenFace);
        setFace(blueFace);
        //System.out.println(this);
        //System.out.println(COLORS[getColorAt(WHITE, 5)]);
    }

    public class MiniCube {

        public static final int CENTER = 0;
        public static final int EDGE = 1;
        public static final int CORNER = 2;


        private int type;
        private int[] colors;
        private int[] faces;

        public MiniCube() {
            type = -1;
        }

        public MiniCube(int type) {
            this.type = type;
            colors = new int[type+1];
            faces = new int[type+1];
        }


        public void setType() {
            this.type = type;
            colors = new int[type+1];
            faces = new int[type+1];
        }

        public int[] getColors() {
            return colors;
        }
    }





    public static void main(String[] args) {
        int[][] newFace = new int[][]{{2,0,2},{5,0,5},{0,0,3}};
        RubiksCube rc = new RubiksCube();
        rc.setFace(newFace);
        System.out.println(rc.faceToString(rc.getFace(4)));
        System.out.println(rc);
    }
}