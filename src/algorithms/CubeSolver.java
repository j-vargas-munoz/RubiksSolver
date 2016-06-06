package algorithms;

import structures.RubiksCube;
import java.util.LinkedList;
import java.util.ListIterator;

public class CubeSolver {

    private RubiksCube cube;
    private static final int FACE = 0;
    private static final int POSITIONINFACE = 1;
    private static final int ADJACENTFACE = 2;


    public CubeSolver(RubiksCube cube) {
        this.cube = cube;
    }

    public LinkedList<Integer> solveWhiteCross() {
        LinkedList<Integer> cross = new LinkedList<Integer>();

        int[] location = cube.getEdge(RubiksCube.WHITE, RubiksCube.BLUE);
        if (location[FACE] == RubiksCube.WHITE) {
                while (cube.getColorAt(RubiksCube.WHITE, 5) != RubiksCube.WHITE
                        && cube.getColorAt(RubiksCube.BLUE, 3) != RubiksCube.BLUE) {
                    cube.twistWhiteFace(true);
                    cross.add(RubiksCube.F_MOVE);
                }
        } else {
            switch (location[FACE]) {
                    case RubiksCube.RED : if (location[POSITIONINFACE] == 1) {
                                            cube.twistWhiteFace(true);
                                            cross.add(RubiksCube.F_MOVE);
                                          } else if (location[POSITIONINFACE] == 3) {
                                            cube.twistRedFace(false);
                                            cross.add(RubiksCube.DI_MOVE);
                                          } else if (location[POSITIONINFACE] == 5) {
                                            cube.twistRedFace(true);
                                            cross.add(RubiksCube.D_MOVE);
                                          } else if (location[POSITIONINFACE] == 7) {
                                            cube.twistRedFace(true);
                                            cross.add(RubiksCube.D_MOVE);
                                            cube.twistRedFace(true);
                                            cross.add(RubiksCube.D_MOVE);
                                          }
                                          break;
                    case RubiksCube.GREEN : if (location[POSITIONINFACE] == 1) {
                                                cube.twistGreenFace(false);
                                                cross.add(RubiksCube.LI_MOVE);
                                            } else if (location[POSITIONINFACE] == 3) {
                                                cube.twistGreenFace(false);
                                                cross.add(RubiksCube.LI_MOVE);
                                                cube.twistGreenFace(false);
                                                cross.add(RubiksCube.LI_MOVE);
                                            } else if (location[POSITIONINFACE] == 7) {
                                                cube.twistGreenFace(true);
                                                cross.add(RubiksCube.L_MOVE);
                                            }
                                            cube.twistWhiteFace(true);
                                            cross.add(RubiksCube.F_MOVE);
                                            cube.twistWhiteFace(true);
                                            cross.add(RubiksCube.F_MOVE);
                                            break;
                    case RubiksCube.ORANGE : if (location[POSITIONINFACE] == 1) {
                                                cube.twistOrangeFace(true);
                                                cross.add(RubiksCube.U_MOVE);
                                                cube.twistOrangeFace(true);
                                                cross.add(RubiksCube.U_MOVE);

                                            } else if (location[POSITIONINFACE] == 3) {
                                                cube.twistOrangeFace(false);
                                                cross.add(RubiksCube.UI_MOVE);
                                            } else if (location[POSITIONINFACE] == 5) {
                                                cube.twistOrangeFace(true);
                                                cross.add(RubiksCube.U_MOVE);
                                            }
                                            cube.twistWhiteFace(false);
                                            cross.add(RubiksCube.FI_MOVE);
                                            break;
                    case RubiksCube.BLUE : if (location[POSITIONINFACE] == 1) {
                                                cube.twistBlueFace(true);
                                                cross.add(RubiksCube.R_MOVE);
                                            } else if (location[POSITIONINFACE] == 5) {
                                                cube.twistBlueFace(true);
                                                cross.add(RubiksCube.R_MOVE);
                                            } else if (location[POSITIONINFACE] == 7) {
                                                cube.twistBlueFace(false);
                                                cross.add(RubiksCube.RI_MOVE);
                                            }
                                            break;
                    case RubiksCube.YELLOW : if (location[POSITIONINFACE] == 1) {
                                                cube.twistYellowFace(false);
                                                cross.add(RubiksCube.BI_MOVE);
                                            } else if (location[POSITIONINFACE] == 3) {
                                                cube.twistYellowFace(false);
                                                cross.add(RubiksCube.BI_MOVE);
                                                cube.twistYellowFace(false);
                                                cross.add(RubiksCube.BI_MOVE);
                                            } else if (location[POSITIONINFACE] == 7) {
                                                cube.twistYellowFace(true);
                                                cross.add(RubiksCube.B_MOVE);
                                            }
                                            cube.twistGreenFace(false);
                                            cross.add(RubiksCube.LI_MOVE);
                                            cube.twistGreenFace(false);
                                            cross.add(RubiksCube.LI_MOVE);
                }
            }

            // SIGUE NARANJA, SIN TOCAR AZUL

            location = cube.getEdge(RubiksCube.WHITE, RubiksCube.ORANGE);
            switch (location[FACE]) {
                case RubiksCube.WHITE : if (location[POSITIONINFACE] == 3) {
                                            cube.twistGreenFace(true);
                                            cross.add(RubiksCube.L_MOVE);
                                            cube.twistGreenFace(true);
                                            cross.add(RubiksCube.L_MOVE);

                                            cube.twistYellowFace(false);
                                            cross.add(RubiksCube.BI_MOVE);

                                            cube.twistOrangeFace(true);
                                            cross.add(RubiksCube.U_MOVE);
                                            cube.twistOrangeFace(true);
                                            cross.add(RubiksCube.U_MOVE);
                                        } else if (location[POSITIONINFACE] == 7) {
                                            cube.twistRedFace(true);
                                            cross.add(RubiksCube.D_MOVE);
                                            cube.twistRedFace(true);
                                            cross.add(RubiksCube.D_MOVE);

                                            cube.twistYellowFace(false);
                                            cross.add(RubiksCube.BI_MOVE);
                                            cube.twistYellowFace(false);
                                            cross.add(RubiksCube.BI_MOVE);

                                            cube.twistOrangeFace(true);
                                            cross.add(RubiksCube.U_MOVE);
                                            cube.twistOrangeFace(true);
                                            cross.add(RubiksCube.U_MOVE);               
                                        }
                                        break;
                case RubiksCube.BLUE : if (location[POSITIONINFACE] == 7) {
                                            cube.twistBlueFace(true);
                                            cross.add(RubiksCube.R_MOVE);

                                            cube.twistYellowFace(false);
                                            cross.add(RubiksCube.BI_MOVE);

                                            cube.twistBlueFace(false);
                                            cross.add(RubiksCube.RI_MOVE);

                                        } else if (location[POSITIONINFACE] == 1) { 
                                            cube.twistBlueFace(false);
                                            cross.add(RubiksCube.RI_MOVE);

                                            cube.twistYellowFace(false);
                                            cross.add(RubiksCube.BI_MOVE);

                                            cube.twistBlueFace(true);
                                            cross.add(RubiksCube.R_MOVE);

                                        } else if (location[POSITIONINFACE] == 5) {
                                            cube.twistYellowFace(false);
                                            cross.add(RubiksCube.BI_MOVE);

                                        }
                                        cube.twistOrangeFace(true);
                                        cross.add(RubiksCube.U_MOVE);
                                        cube.twistOrangeFace(true);
                                        cross.add(RubiksCube.U_MOVE);
                                        break;
                case RubiksCube.ORANGE : if (location[POSITIONINFACE] == 1) {
                                            cube.twistOrangeFace(true);
                                            cross.add(RubiksCube.U_MOVE);
                                            cube.twistOrangeFace(true);
                                            cross.add(RubiksCube.U_MOVE);
                                        } else if (location[POSITIONINFACE] == 3) {
                                            cube.twistOrangeFace(true);
                                            cross.add(RubiksCube.U_MOVE);
                                        } else if (location[POSITIONINFACE] == 5) {
                                            cube.twistOrangeFace(false);
                                            cross.add(RubiksCube.UI_MOVE);
                                        }
                                        break;
                case RubiksCube.GREEN : if (location[POSITIONINFACE] == 3) {
                                            cube.twistGreenFace(false);
                                            cross.add(RubiksCube.LI_MOVE);
                                        } else if (location[POSITIONINFACE] == 5) {
                                            cube.twistGreenFace(true);
                                            cross.add(RubiksCube.L_MOVE);
                                        } else if (location[POSITIONINFACE] == 7) {
                                            cube.twistGreenFace(true);
                                            cross.add(RubiksCube.L_MOVE);
                                            cube.twistGreenFace(true);
                                            cross.add(RubiksCube.L_MOVE);
                                        }
                                        cube.twistOrangeFace(true);
                                        cross.add(RubiksCube.U_MOVE);
                                        break;
                case RubiksCube.RED : if (location[POSITIONINFACE] == 1) {
                                            cube.twistRedFace(true);
                                            cross.add(RubiksCube.D_MOVE);
                                            cube.twistRedFace(true);
                                            cross.add(RubiksCube.D_MOVE);
                                      } else if (location[POSITIONINFACE] == 3) {
                                            cube.twistRedFace(true);
                                            cross.add(RubiksCube.D_MOVE);
                                      } else if (location[POSITIONINFACE] == 5) {
                                            cube.twistRedFace(false);
                                            cross.add(RubiksCube.DI_MOVE);
                                      }
                                      cube.twistYellowFace(true);
                                      cross.add(RubiksCube.B_MOVE);
                                      cube.twistYellowFace(true);
                                      cross.add(RubiksCube.B_MOVE);
                                      cube.twistOrangeFace(true);
                                      cross.add(RubiksCube.U_MOVE);
                                      cube.twistOrangeFace(true);
                                      cross.add(RubiksCube.U_MOVE);
                                      break;
                case RubiksCube.YELLOW : if (location[POSITIONINFACE] == 1) {
                                            cube.twistYellowFace(true);
                                            cross.add(RubiksCube.B_MOVE);
                                            cube.twistYellowFace(true);
                                            cross.add(RubiksCube.B_MOVE);
                                         } else if (location[POSITIONINFACE] == 3) {
                                            cube.twistYellowFace(true);
                                            cross.add(RubiksCube.B_MOVE);
                                        } else if (location[POSITIONINFACE] == 5) {
                                            cube.twistYellowFace(false);
                                            cross.add(RubiksCube.BI_MOVE);
                                        }
                                        break;
            }


            // SIGUE VERDE, SIN TOCAR AZUL NI NARANJA


            location = cube.getEdge(RubiksCube.WHITE, RubiksCube.GREEN);
            switch (location[FACE]) {
                case RubiksCube.WHITE : if (location[POSITIONINFACE] == 7) {
                                            cube.twistRedFace(true);
                                            cross.add(RubiksCube.D_MOVE);

                                            cube.twistGreenFace(true);
                                            cross.add(RubiksCube.L_MOVE);
                                        }
                                        break;
                case RubiksCube.BLUE : if (location[POSITIONINFACE] == 3)
                                            break;
                                        if (location[POSITIONINFACE] == 1) {
                                            cube.twistBlueFace(false);
                                            cross.add(RubiksCube.RI_MOVE);

                                            cube.twistYellowFace(true);
                                            cross.add(RubiksCube.B_MOVE);
                                            cube.twistYellowFace(true);
                                            cross.add(RubiksCube.B_MOVE);

                                            cube.twistBlueFace(true);
                                            cross.add(RubiksCube.R_MOVE);                                            

                                        } else if (location[POSITIONINFACE] == 7) {
                                            cube.twistBlueFace(true);
                                            cross.add(RubiksCube.R_MOVE);

                                            cube.twistYellowFace(true);
                                            cross.add(RubiksCube.B_MOVE);

                                            cube.twistBlueFace(false);
                                            cross.add(RubiksCube.RI_MOVE);                                            

                                        } 
                                        cube.twistYellowFace(true);
                                        cross.add(RubiksCube.B_MOVE);

                                        cube.twistGreenFace(true);
                                        cross.add(RubiksCube.L_MOVE);
                                        cube.twistGreenFace(true);
                                        cross.add(RubiksCube.L_MOVE);
                                        break;
                case RubiksCube.ORANGE : if (location[POSITIONINFACE] == 1) {
                                            cube.twistYellowFace(false);
                                            cross.add(RubiksCube.BI_MOVE);

                                            cube.twistGreenFace(true);
                                            cross.add(RubiksCube.L_MOVE);
                                            cube.twistGreenFace(true);
                                            cross.add(RubiksCube.L_MOVE);
                                        } else if (location[POSITIONINFACE] == 5) {
                                            cube.twistOrangeFace(true);
                                            cross.add(RubiksCube.U_MOVE);

                                            cube.twistYellowFace(false);
                                            cross.add(RubiksCube.BI_MOVE);

                                            cube.twistOrangeFace(false);
                                            cross.add(RubiksCube.UI_MOVE);

                                            cube.twistGreenFace(true);
                                            cross.add(RubiksCube.L_MOVE);
                                            cube.twistGreenFace(true);
                                            cross.add(RubiksCube.L_MOVE);
                                        } else if (location[POSITIONINFACE] == 3) {
                                            cube.twistGreenFace(false);
                                            cross.add(RubiksCube.LI_MOVE);
                                        }
                                        break;
                case RubiksCube.GREEN : System.out.println("IN GREEN");
                                        if (location[POSITIONINFACE] == 1) {
                                            cube.twistGreenFace(false);
                                            cross.add(RubiksCube.LI_MOVE);
                                        } else if (location[POSITIONINFACE] == 3) {
                                            cube.twistGreenFace(false);
                                            cross.add(RubiksCube.LI_MOVE);
                                            cube.twistGreenFace(false);
                                            cross.add(RubiksCube.LI_MOVE);
                                        } else if (location[POSITIONINFACE] == 7) {
                                            cube.twistGreenFace(true);
                                            cross.add(RubiksCube.L_MOVE);
                                        }
                                        break;
                case RubiksCube.RED : if (location[POSITIONINFACE] == 1) {
                                        twistFace(RubiksCube.RED, true, cross);
                                      } else if (location[POSITIONINFACE] == 5) {
                                        twistFace(RubiksCube.RED, true, cross);
                                        twistFace(RubiksCube.RED, true, cross);
                                      } else if (location[POSITIONINFACE] == 7) {
                                        twistFace(RubiksCube.RED, false, cross);
                                      }
                                      twistFace(RubiksCube.GREEN, true, cross);
                                      break;
                case RubiksCube.YELLOW : if (location[POSITIONINFACE] == 1) {
                                            twistFace(RubiksCube.YELLOW, true, cross);
                                         } else if (location[POSITIONINFACE] == 5) {
                                            twistFace(RubiksCube.YELLOW, true, cross);
                                            twistFace(RubiksCube.YELLOW, true, cross);
                                         } else if (location[POSITIONINFACE] == 7) {
                                            twistFace(RubiksCube.YELLOW, false, cross);
                                         }
                                         twistFace(RubiksCube.GREEN, true, cross);
                                         twistFace(RubiksCube.GREEN, true, cross);
            }

            //SIGUE ROJO, SIN TOCAR AZUL, NARANJA NI VERDE

            location = cube.getEdge(RubiksCube.WHITE, RubiksCube.RED);
            switch (location[FACE]) {
                case RubiksCube.BLUE : if (location[POSITIONINFACE] == 1) {
                                            twistFace(RubiksCube.BLUE, false, cross);
                                            twistFace(RubiksCube.YELLOW, true, cross);
                                            twistFace(RubiksCube.BLUE, true, cross);
                                            twistFace(RubiksCube.YELLOW, true, cross);
                                            twistFace(RubiksCube.RED, true, cross);
                                            twistFace(RubiksCube.RED, true, cross);
                                        } else if (location[POSITIONINFACE] == 7) {
                                            twistFace(RubiksCube.RED, true, cross);
                                        } else if (location[POSITIONINFACE] == 5) {
                                            twistFace(RubiksCube.YELLOW, true, cross);
                                            twistFace(RubiksCube.RED, true, cross);
                                            twistFace(RubiksCube.RED, true, cross);
                                        }
                                        break;
                case RubiksCube.ORANGE : if (location[POSITIONINFACE] == 7)
                                            break;
                                         if (location[POSITIONINFACE] == 3) {
                                            twistFace(RubiksCube.ORANGE, false, cross);
                                            twistFace(RubiksCube.YELLOW, true, cross);
                                            twistFace(RubiksCube.YELLOW, true, cross);
                                            twistFace(RubiksCube.ORANGE, true, cross);
                                         } else if (location[POSITIONINFACE] == 5) {
                                            twistFace(RubiksCube.ORANGE, true, cross);
                                            twistFace(RubiksCube.YELLOW, true, cross);
                                            twistFace(RubiksCube.YELLOW, true, cross);
                                            twistFace(RubiksCube.ORANGE, false, cross);
                                         } else if (location[POSITIONINFACE] == 1) {
                                            twistFace(RubiksCube.YELLOW, false, cross);
                                            twistFace(RubiksCube.YELLOW, false, cross);
                                         }
                                         twistFace(RubiksCube.RED, true, cross);
                                         twistFace(RubiksCube.RED, true, cross);
                                         break;
                case RubiksCube.GREEN : if (location[POSITIONINFACE] == 1) {
                                            twistFace(RubiksCube.GREEN, true, cross);
                                            twistFace(RubiksCube.YELLOW, false, cross);
                                            twistFace(RubiksCube.GREEN, false, cross);
                                            twistFace(RubiksCube.RED, false, cross);
                                            twistFace(RubiksCube.RED, false, cross);
                                        } else if (location[POSITIONINFACE] == 7) {
                                            twistFace(RubiksCube.RED, false, cross);
                                            break;
                                        } else if (location[POSITIONINFACE] == 3) {
                                            twistFace(RubiksCube.YELLOW, false, cross);
                                            twistFace(RubiksCube.RED, false, cross);
                                            twistFace(RubiksCube.RED, false, cross);
                                        }

                case RubiksCube.RED : if (location[POSITIONINFACE] == 3) {
                                        twistFace(RubiksCube.RED, false, cross);
                                     } else if (location[POSITIONINFACE] == 5) {
                                        twistFace(RubiksCube.RED, true, cross);
                                     } else if (location[POSITIONINFACE] == 7) {
                                        twistFace(RubiksCube.RED, true, cross);
                                        twistFace(RubiksCube.RED, true, cross);
                                     }
                                     break;

                case RubiksCube.YELLOW : if (location[POSITIONINFACE] == 3) {
                                            twistFace(RubiksCube.YELLOW, false, cross);
                                         } else if (location[POSITIONINFACE] == 5) {
                                            twistFace(RubiksCube.YELLOW, true, cross);
                                         } else if (location[POSITIONINFACE] == 7) {
                                            twistFace(RubiksCube.YELLOW, false, cross);
                                            twistFace(RubiksCube.YELLOW, false, cross);
                                         }
                                         twistFace(RubiksCube.RED, true, cross);
                                         twistFace(RubiksCube.RED, true, cross);
                                         break;
            }



        if (cube.getColorAt(RubiksCube.WHITE, 5) != RubiksCube.WHITE) {
            cube.twistWhiteFace(false);
            cross.add(RubiksCube.FI_MOVE);
            cube.twistRedFace(false);
            cross.add(RubiksCube.DI_MOVE);
            cube.twistWhiteFace(true);
            cross.add(RubiksCube.F_MOVE);
            cube.twistBlueFace(false);
            cross.add(RubiksCube.RI_MOVE);
        }
        if (cube.getColorAt(RubiksCube.WHITE, 1) != RubiksCube.WHITE) {
            cube.twistWhiteFace(false);
            cross.add(RubiksCube.FI_MOVE);
            cube.twistBlueFace(false);
            cross.add(RubiksCube.RI_MOVE);
            cube.twistWhiteFace(true);
            cross.add(RubiksCube.F_MOVE);
            cube.twistOrangeFace(false);
            cross.add(RubiksCube.UI_MOVE);
        }
        if (cube.getColorAt(RubiksCube.WHITE, 3) != RubiksCube.WHITE) {
            cube.twistWhiteFace(false);
            cross.add(RubiksCube.FI_MOVE);
            cube.twistRedFace(false);
            cross.add(RubiksCube.UI_MOVE);
            cube.twistWhiteFace(true);
            cross.add(RubiksCube.F_MOVE);
            cube.twistGreenFace(false);
            cross.add(RubiksCube.LI_MOVE);
        }
        if (cube.getColorAt(RubiksCube.WHITE, 7) != RubiksCube.WHITE) {
            twistFace(RubiksCube.WHITE, false, cross);
            twistFace(RubiksCube.GREEN, false, cross);
            twistFace(RubiksCube.WHITE, true, cross);
            twistFace(RubiksCube.RED, false, cross);
        }

        return cross;
    }


    public LinkedList<Integer> solveWhiteCorners() {
        LinkedList<Integer> corners = new LinkedList<Integer>();

        int[] location = cube.getCorner(RubiksCube.WHITE, RubiksCube.GREEN, RubiksCube.ORANGE);
        boolean done = false;

        if (location[FACE] == RubiksCube.WHITE) {
            if (location[POSITIONINFACE] == 0) {
                done = true;
            } else {
                if (location[POSITIONINFACE] == 2) {
                    twistFace(RubiksCube.ORANGE, true, corners);
                    twistFace(RubiksCube.YELLOW, true, corners);
                    twistFace(RubiksCube.ORANGE, false, corners);
                    twistFace(RubiksCube.YELLOW, false, corners);

                } else if (location[POSITIONINFACE] == 6) {
                    twistFace(RubiksCube.RED, true, corners);
                    twistFace(RubiksCube.YELLOW, true, corners);
                    twistFace(RubiksCube.RED, false, corners);

                } else if (location[POSITIONINFACE] == 8) {
                    twistFace(RubiksCube.BLUE, true, corners);
                    twistFace(RubiksCube.YELLOW, true, corners);
                    twistFace(RubiksCube.BLUE, false, corners);
                    twistFace(RubiksCube.YELLOW, true, corners);
                }

                twistFace(RubiksCube.GREEN, true, corners);
                twistFace(RubiksCube.YELLOW, true, corners);
                twistFace(RubiksCube.GREEN, false, corners);
                twistFace(RubiksCube.YELLOW, false, corners);
            }
        }
        if (!done) {
            switch (location[FACE]) {
                case RubiksCube.YELLOW : if (location[POSITIONINFACE] == 0) {
                                twistFace(RubiksCube.YELLOW, true, corners);
                              } else if (location[POSITIONINFACE] == 2) {
                                twistFace(RubiksCube.YELLOW, true, corners);
                                twistFace(RubiksCube.YELLOW, true, corners);
                              } else if (location[POSITIONINFACE] == 8) {
                                twistFace(RubiksCube.YELLOW, false, corners);
                              }
                              twistFace(RubiksCube.GREEN, true, corners);
                              twistFace(RubiksCube.YELLOW, true, corners);
                              twistFace(RubiksCube.GREEN, false, corners);
                              twistFace(RubiksCube.YELLOW, false, corners);
                              break;
                case RubiksCube.BLUE : if (location[POSITIONINFACE] == 0) {
                                twistFace(RubiksCube.ORANGE, true, corners);
                                twistFace(RubiksCube.YELLOW, false, corners);
                                twistFace(RubiksCube.ORANGE, false, corners);
                                twistFace(RubiksCube.YELLOW, false, corners);
                            } else if (location[POSITIONINFACE] == 2) {
                                twistFace(RubiksCube.YELLOW, false, corners);
                            } else if (location[POSITIONINFACE] == 6) {
                                twistFace(RubiksCube.BLUE, true, corners);
                                twistFace(RubiksCube.YELLOW, true, corners);
                                twistFace(RubiksCube.BLUE, false, corners);
                                twistFace(RubiksCube.YELLOW, false, corners);

                                twistFace(RubiksCube.YELLOW, false, corners);
                                twistFace(RubiksCube.YELLOW, false, corners);
                            } else if (location[POSITIONINFACE] == 8) {
                                twistFace(RubiksCube.YELLOW, false, corners);
                                twistFace(RubiksCube.YELLOW, false, corners);                                
                            }
                            twistFace(RubiksCube.GREEN, true, corners);
                            twistFace(RubiksCube.YELLOW, true, corners);
                            twistFace(RubiksCube.GREEN, false, corners);
                            twistFace(RubiksCube.YELLOW, false, corners);

                            break;
                case RubiksCube.ORANGE : if (location[POSITIONINFACE] == 2) {
                                            twistFace(RubiksCube.YELLOW, false, corners);

                                            twistFace(RubiksCube.GREEN, true, corners);
                                            twistFace(RubiksCube.YELLOW, true, corners);
                                            twistFace(RubiksCube.GREEN, false, corners);
                                            twistFace(RubiksCube.YELLOW, false, corners);
                                        } else if (location[POSITIONINFACE] == 0) {
                                            twistFace(RubiksCube.GREEN, true, corners);
                                            twistFace(RubiksCube.YELLOW, true, corners);
                                            twistFace(RubiksCube.GREEN, false, corners);
                                            twistFace(RubiksCube.YELLOW, false, corners);
                                        } else if (location[POSITIONINFACE] == 8) {
                                            twistFace(RubiksCube.ORANGE, true, corners);
                                            twistFace(RubiksCube.YELLOW, false, corners);
                                            twistFace(RubiksCube.ORANGE, false, corners);
                                            twistFace(RubiksCube.YELLOW, false, corners);

                                            twistFace(RubiksCube.GREEN, true, corners);
                                            twistFace(RubiksCube.YELLOW, true, corners);
                                            twistFace(RubiksCube.GREEN, false, corners);
                                            twistFace(RubiksCube.YELLOW, false, corners);
                                        }
                                        break;
                case RubiksCube.GREEN : if (location[POSITIONINFACE] == 0) {
                                            twistFace(RubiksCube.GREEN, true, corners);
                                            twistFace(RubiksCube.YELLOW, true, corners);
                                            twistFace(RubiksCube.GREEN, false, corners);
                                            twistFace(RubiksCube.YELLOW, false, corners);
                                        } else if (location[POSITIONINFACE] == 6) {
                                            twistFace(RubiksCube.YELLOW, true, corners);

                                            twistFace(RubiksCube.GREEN, true, corners);
                                            twistFace(RubiksCube.YELLOW, true, corners);
                                            twistFace(RubiksCube.GREEN, false, corners);
                                            twistFace(RubiksCube.YELLOW, false, corners);
                                        } else if (location[POSITIONINFACE] == 8) {
                                            twistFace(RubiksCube.RED, true, corners);
                                            twistFace(RubiksCube.YELLOW, true, corners);
                                            twistFace(RubiksCube.RED, false, corners);

                                            twistFace(RubiksCube.GREEN, true, corners);
                                            twistFace(RubiksCube.YELLOW, true, corners);
                                            twistFace(RubiksCube.GREEN, false, corners);
                                            twistFace(RubiksCube.YELLOW, false, corners);
                                        }
                                        break;
                case RubiksCube.RED : if (location[POSITIONINFACE] == 0) {
                                        twistFace(RubiksCube.RED, true, corners);
                                        twistFace(RubiksCube.YELLOW, true, corners);
                                        twistFace(RubiksCube.RED, false, corners);

                                     } else if (location[POSITIONINFACE] == 2) {
                                        twistFace(RubiksCube.BLUE, true, corners);
                                        twistFace(RubiksCube.YELLOW, true, corners);
                                        twistFace(RubiksCube.BLUE, false, corners);

                                        twistFace(RubiksCube.YELLOW, true, corners);

                                     } else if (location[POSITIONINFACE] == 6) {
                                        twistFace(RubiksCube.YELLOW, true, corners);

                                     } else if (location[POSITIONINFACE] == 8) {
                                        twistFace(RubiksCube.YELLOW, true, corners);
                                        twistFace(RubiksCube.YELLOW, true, corners);

                                    }
                                    twistFace(RubiksCube.GREEN, true, corners);
                                    twistFace(RubiksCube.YELLOW, true, corners);
                                    twistFace(RubiksCube.GREEN, false, corners);
                                    twistFace(RubiksCube.YELLOW, false, corners);
                                    break;
            }
            while (cube.getColorAt(RubiksCube.WHITE, 0) != RubiksCube.WHITE
                || cube.getColorAt(RubiksCube.GREEN, 2) != RubiksCube.GREEN
                || cube.getColorAt(RubiksCube.ORANGE, 6) != RubiksCube.ORANGE) {

                twistFace(RubiksCube.GREEN, true, corners);
                twistFace(RubiksCube.YELLOW, true, corners);
                twistFace(RubiksCube.GREEN, false, corners);
                twistFace(RubiksCube.YELLOW, false, corners);

            }
        }

        location = cube.getCorner(RubiksCube.WHITE, RubiksCube.GREEN, RubiksCube.RED);
        done = false;

        if (location[FACE] == RubiksCube.WHITE) {
            if (location[POSITIONINFACE] == 6) {
                done = true;
            } else {
                if (location[POSITIONINFACE] == 2) {
                    twistFace(RubiksCube.BLUE, false, corners);
                    twistFace(RubiksCube.YELLOW, true, corners);
                    twistFace(RubiksCube.BLUE, true, corners);
                    
                } else if (location[POSITIONINFACE] == 8) {
                    twistFace(RubiksCube.BLUE, true, corners);
                    twistFace(RubiksCube.YELLOW, true, corners);
                    twistFace(RubiksCube.BLUE, false, corners);
                }
            }
        }
        if (!done) {
            switch (location[FACE]) {
                case RubiksCube.YELLOW : if (location[POSITIONINFACE] == 2) {
                                            twistFace(RubiksCube.YELLOW, true, corners);
                                        } else if (location[POSITIONINFACE] == 6) {
                                            twistFace(RubiksCube.YELLOW, false, corners);
                                        } else if (location[POSITIONINFACE] == 8) {
                                            twistFace(RubiksCube.YELLOW, false, corners);
                                            twistFace(RubiksCube.YELLOW, false, corners);
                                        }
                                        twistFace(RubiksCube.RED, true, corners);
                                        twistFace(RubiksCube.YELLOW, true, corners);
                                        twistFace(RubiksCube.RED, false, corners);
                                        twistFace(RubiksCube.YELLOW, false, corners);
                                        break;
                case RubiksCube.BLUE : if (location[POSITIONINFACE] == 0) {
                                            twistFace(RubiksCube.BLUE, false, corners);
                                            twistFace(RubiksCube.YELLOW, true, corners);
                                            twistFace(RubiksCube.BLUE, true, corners);

                                            twistFace(RubiksCube.YELLOW, true, corners);
                                            twistFace(RubiksCube.YELLOW, true, corners);

                                        } else if (location[POSITIONINFACE] == 2) {
                                            twistFace(RubiksCube.YELLOW, true, corners);
                                            twistFace(RubiksCube.YELLOW, true, corners);

                                        } else if  (location[POSITIONINFACE] == 6) {
                                            twistFace(RubiksCube.BLUE, true, corners);
                                            twistFace(RubiksCube.YELLOW, true, corners);
                                            twistFace(RubiksCube.BLUE, false, corners);

                                        } else if (location[POSITIONINFACE] == 8) {
                                            twistFace(RubiksCube.YELLOW, true, corners);
                                        }
                                        twistFace(RubiksCube.RED, true, corners);
                                        twistFace(RubiksCube.YELLOW, true, corners);
                                        twistFace(RubiksCube.RED, false, corners);
                                        twistFace(RubiksCube.YELLOW, false, corners);
                                        break;
                case RubiksCube.ORANGE : if (location[POSITIONINFACE] == 0) {
                                            twistFace(RubiksCube.YELLOW, false, corners);

                                        } else if (location[POSITIONINFACE] == 2) {
                                            twistFace(RubiksCube.YELLOW, false, corners);
                                            twistFace(RubiksCube.YELLOW, false, corners);

                                        } else if (location[POSITIONINFACE] == 8) { // NO HAY 6 PORQUE YA ACOODAMOS (WHITE, ORANGE; GREEN)
                                            twistFace(RubiksCube.ORANGE, true, corners);
                                            twistFace(RubiksCube.YELLOW, true, corners);
                                            twistFace(RubiksCube.ORANGE, false, corners);

                                            twistFace(RubiksCube.YELLOW, true, corners);

                                        }
                                        twistFace(RubiksCube.RED, true, corners);
                                        twistFace(RubiksCube.YELLOW, true, corners);
                                        twistFace(RubiksCube.RED, false, corners);
                                        twistFace(RubiksCube.YELLOW, false, corners);
                                        break;
                case RubiksCube.GREEN : if (location[POSITIONINFACE] == 0) {
                                            twistFace(RubiksCube.YELLOW, false, corners);

                                            twistFace(RubiksCube.RED, true, corners);
                                            twistFace(RubiksCube.YELLOW, true, corners);
                                            twistFace(RubiksCube.RED, false, corners);
                                            twistFace(RubiksCube.YELLOW, false, corners);
                                        } else if (location[POSITIONINFACE] == 6) {
                                            twistFace(RubiksCube.RED, true, corners);
                                            twistFace(RubiksCube.YELLOW, true, corners);
                                            twistFace(RubiksCube.RED, false, corners);
                                            twistFace(RubiksCube.YELLOW, false, corners);
                                        }
                                        break;
                case RubiksCube.RED : if (location[POSITIONINFACE] == 6) {
                                        twistFace(RubiksCube.BLUE, true, corners);
                                        twistFace(RubiksCube.YELLOW, true, corners);
                                        twistFace(RubiksCube.BLUE, true, corners);

                                      } else if (location[POSITIONINFACE] == 2) {
                                        twistFace(RubiksCube.YELLOW, true, corners);
                                        twistFace(RubiksCube.YELLOW, true, corners);

                                      } else if (location[POSITIONINFACE] == 8) {
                                        twistFace(RubiksCube.YELLOW, true, corners);

                                      } else if (location[POSITIONINFACE] == 0) {
                                        twistFace(RubiksCube.ORANGE, true, corners);
                                        twistFace(RubiksCube.YELLOW, true, corners);
                                        twistFace(RubiksCube.ORANGE, false, corners);
                                        twistFace(RubiksCube.YELLOW, true, corners);

                                      }
                                      twistFace(RubiksCube.RED, true, corners);
                                      twistFace(RubiksCube.YELLOW, true, corners);
                                      twistFace(RubiksCube.RED, false, corners);
                                      twistFace(RubiksCube.YELLOW, false, corners);
            }
            while (cube.getColorAt(RubiksCube.WHITE, 6) != RubiksCube.WHITE
                || cube.getColorAt(RubiksCube.GREEN, 8) != RubiksCube.GREEN
                || cube.getColorAt(RubiksCube.RED, 0) != RubiksCube.RED) {

                twistFace(RubiksCube.RED, true, corners);
                twistFace(RubiksCube.YELLOW, true, corners);
                twistFace(RubiksCube.RED, false, corners);
                twistFace(RubiksCube.YELLOW, false, corners);

            }
        }

        location = cube.getCorner(RubiksCube.WHITE, RubiksCube.RED, RubiksCube.BLUE);
        done = false;

        if (location[FACE] == RubiksCube.WHITE) {
            if (location[POSITIONINFACE] == 8) {
                done = true;
            } else {
                if (location[POSITIONINFACE] == 2) {
                    twistFace(RubiksCube.ORANGE, true, corners);
                    twistFace(RubiksCube.YELLOW, true, corners);
                    twistFace(RubiksCube.ORANGE, false, corners);

                }
            }
        }
        if (!done) {
            switch(location[FACE]) {
                case RubiksCube.YELLOW : if (location[POSITIONINFACE] == 0) {
                                            twistFace(RubiksCube.YELLOW, false, corners);

                                        } else if (location[POSITIONINFACE] == 6) {
                                            twistFace(RubiksCube.YELLOW, true, corners);
                                            twistFace(RubiksCube.YELLOW, true, corners);

                                        } else if (location[POSITIONINFACE] == 8)  {
                                            twistFace(RubiksCube.YELLOW, true, corners);

                                        }
                                        twistFace(RubiksCube.BLUE, true, corners);
                                        twistFace(RubiksCube.YELLOW, true, corners);
                                        twistFace(RubiksCube.BLUE, false, corners);
                                        twistFace(RubiksCube.YELLOW, false, corners);
                                        break;
                case RubiksCube.BLUE :  if (location[POSITIONINFACE] == 6)
                                            break;
                                        if (location[POSITIONINFACE] == 0) {
                                            twistFace(RubiksCube.ORANGE, true, corners);
                                            twistFace(RubiksCube.YELLOW, true, corners);
                                            twistFace(RubiksCube.ORANGE, false, corners);

                                       } else if (location[POSITIONINFACE] == 2) {
                                            twistFace(RubiksCube.YELLOW, true, corners);

                                       }
                                       twistFace(RubiksCube.BLUE, true, corners);
                                       twistFace(RubiksCube.YELLOW, true, corners);
                                       twistFace(RubiksCube.BLUE, false, corners);
                                       twistFace(RubiksCube.YELLOW, false, corners);
                                       break;
                case RubiksCube.ORANGE : if (location[POSITIONINFACE] == 6) // POSICION YA OCUPADA POR (WHITE, GREEN, ORANGE)
                                            break;
                                         if (location[POSITIONINFACE] == 0) {
                                            twistFace(RubiksCube.YELLOW, true, corners);
                                            twistFace(RubiksCube.YELLOW, true, corners);

                                         } else if (location[POSITIONINFACE] == 2) {
                                             twistFace(RubiksCube.YELLOW, true, corners);

                                         } else if (location[POSITIONINFACE] == 8) {
                                            twistFace(RubiksCube.ORANGE, true, corners);
                                            twistFace(RubiksCube.YELLOW, true, corners);
                                            twistFace(RubiksCube.ORANGE, false, corners);

                                         }
                                         twistFace(RubiksCube.BLUE, true, corners);
                                         twistFace(RubiksCube.YELLOW, true, corners);
                                         twistFace(RubiksCube.BLUE, false, corners);
                                         twistFace(RubiksCube.YELLOW, false, corners);
                                         break;
                case RubiksCube.GREEN : if (location[POSITIONINFACE] == 2 || location[POSITIONINFACE] == 8) // POSICIONes OCUPADAs
                                            break;
                                        if (location[POSITIONINFACE] == 0) {
                                            twistFace(RubiksCube.YELLOW, true, corners);
                                            twistFace(RubiksCube.YELLOW, true, corners);

                                        } else if (location[POSITIONINFACE] == 6) {
                                            twistFace(RubiksCube.YELLOW, false, corners);

                                        }
                                        twistFace(RubiksCube.BLUE, true, corners);
                                        twistFace(RubiksCube.YELLOW, true, corners);
                                        twistFace(RubiksCube.BLUE, false, corners);
                                        twistFace(RubiksCube.YELLOW, false, corners);
                                        break;
                case RubiksCube.RED : if (location[POSITIONINFACE] == 0 || location[POSITIONINFACE] == 2)
                                        break;
                                      if (location[POSITIONINFACE] == 6) {
                                        twistFace(RubiksCube.YELLOW, false, corners);
                                      }
                                      twistFace(RubiksCube.BLUE, true, corners);
                                      twistFace(RubiksCube.YELLOW, true, corners);
                                      twistFace(RubiksCube.BLUE, false, corners);
                                      twistFace(RubiksCube.YELLOW, false, corners);
                                      break;
            }
            while (cube.getColorAt(RubiksCube.WHITE, 8) != RubiksCube.WHITE
                || cube.getColorAt(RubiksCube.RED, 2) != RubiksCube.RED
                || cube.getColorAt(RubiksCube.BLUE, 6) != RubiksCube.BLUE) {

                twistFace(RubiksCube.BLUE, true, corners);
                twistFace(RubiksCube.YELLOW, true, corners);
                twistFace(RubiksCube.BLUE, false, corners);
                twistFace(RubiksCube.YELLOW, false, corners);

            }
        }

        location = cube.getCorner(RubiksCube.WHITE, RubiksCube.BLUE, RubiksCube.ORANGE);
        done = false;

        if (location[FACE] == RubiksCube.WHITE) {
            if (location[POSITIONINFACE] == 8)
                done = true;
        }
        if (!done) {
            switch (location[FACE]) {
                case RubiksCube.YELLOW : if (location[POSITIONINFACE] == 0) {
                                            twistFace(RubiksCube.YELLOW, true, corners);
                                            twistFace(RubiksCube.YELLOW, true, corners);

                                         } else if (location[POSITIONINFACE] == 2) {
                                            twistFace(RubiksCube.YELLOW, false, corners);

                                         } else if (location[POSITIONINFACE] == 6) {
                                            twistFace(RubiksCube.YELLOW, true, corners);

                                         }
                                         twistFace(RubiksCube.ORANGE, true, corners);
                                         twistFace(RubiksCube.YELLOW, true, corners);
                                         twistFace(RubiksCube.ORANGE, false, corners);
                                         twistFace(RubiksCube.YELLOW, false, corners);
                                         break;
                case RubiksCube.BLUE :  if (location[POSITIONINFACE] == 6 || location[POSITIONINFACE] == 0)
                                            break;
                                        if (location[POSITIONINFACE] == 8)
                                            twistFace(RubiksCube.YELLOW, false, corners);
                                        twistFace(RubiksCube.ORANGE, true, corners);
                                        twistFace(RubiksCube.YELLOW, true, corners);
                                        twistFace(RubiksCube.ORANGE, false, corners);
                                        twistFace(RubiksCube.YELLOW, false, corners);
                                        break;
                case RubiksCube.ORANGE : if (location[POSITIONINFACE] == 6 || location[POSITIONINFACE] == 8)
                                            break;
                                        if (location[POSITIONINFACE] == 0)
                                            twistFace(RubiksCube.YELLOW, true, corners);
                                        twistFace(RubiksCube.ORANGE, true, corners);
                                        twistFace(RubiksCube.YELLOW, true, corners);
                                        twistFace(RubiksCube.ORANGE, false, corners);
                                        twistFace(RubiksCube.YELLOW, false, corners);
                                        break;
                case RubiksCube.GREEN : if (location[POSITIONINFACE] == 2 || location[POSITIONINFACE] == 8)
                                            break;
                                        if (location[POSITIONINFACE] == 0)
                                            twistFace(RubiksCube.YELLOW, true, corners);
                                        if (location[POSITIONINFACE] == 6) {
                                            twistFace(RubiksCube.YELLOW, true, corners);
                                            twistFace(RubiksCube.YELLOW, true, corners);
                                        }
                                        twistFace(RubiksCube.ORANGE, true, corners);
                                        twistFace(RubiksCube.YELLOW, true, corners);
                                        twistFace(RubiksCube.ORANGE, false, corners);
                                        twistFace(RubiksCube.YELLOW, false, corners);
                                        break;
                case RubiksCube.RED :   if (location[POSITIONINFACE] == 0 || location[POSITIONINFACE] == 2)
                                            break;
                                        if (location[POSITIONINFACE] == 6) {
                                            twistFace(RubiksCube.YELLOW, true, corners);
                                            twistFace(RubiksCube.YELLOW, true, corners);
                                        } else if (location[POSITIONINFACE] == 8) {
                                            twistFace(RubiksCube.YELLOW, false, corners);
                                        }
                                        twistFace(RubiksCube.ORANGE, true, corners);
                                        twistFace(RubiksCube.YELLOW, true, corners);
                                        twistFace(RubiksCube.ORANGE, false, corners);
                                        twistFace(RubiksCube.YELLOW, false, corners);
                                        break;
            }
            while (cube.getColorAt(RubiksCube.WHITE, 2) != RubiksCube.WHITE
                || cube.getColorAt(RubiksCube.BLUE, 0) != RubiksCube.BLUE
                || cube.getColorAt(RubiksCube.ORANGE, 8) != RubiksCube.ORANGE) {

                twistFace(RubiksCube.ORANGE, true, corners);
                twistFace(RubiksCube.YELLOW, true, corners);
                twistFace(RubiksCube.ORANGE, false, corners);
                twistFace(RubiksCube.YELLOW, false, corners);
            }
        }

        return corners;
    }


    public LinkedList<Integer> solveMiddleLayer() {
        LinkedList<Integer> layer = new LinkedList<Integer>();

        while (cube.getColorAt(RubiksCube.RED, 3) != RubiksCube.RED || cube.getColorAt(RubiksCube.GREEN, 7) != RubiksCube.GREEN) {
            int[] location = cube.getEdge(RubiksCube.RED, RubiksCube.GREEN);

            if (location[FACE] == RubiksCube.YELLOW) {
                //Hay que rotar green en lugar de red
                if (location[POSITIONINFACE] == 1) {
                    twistFace(RubiksCube.YELLOW, true, layer);
                } else if (location[POSITIONINFACE] == 5) {
                    twistFace(RubiksCube.YELLOW, true, layer);
                    twistFace(RubiksCube.YELLOW, true, layer);
                } else if (location[POSITIONINFACE] == 7) {
                    twistFace(RubiksCube.YELLOW, false, layer);
                }
                twistFace(RubiksCube.YELLOW, true, layer);
                twistFace(RubiksCube.RED, true, layer);
                twistFace(RubiksCube.YELLOW, false, layer);
                twistFace(RubiksCube.RED, false, layer);
                twistFace(RubiksCube.YELLOW, false, layer);
                twistFace(RubiksCube.GREEN, false, layer);
                twistFace(RubiksCube.YELLOW, true, layer);
                twistFace(RubiksCube.GREEN, true, layer);


            } else {
                switch (location[FACE]) {
                    case RubiksCube.RED : if (location[POSITIONINFACE] == 7 || location[POSITIONINFACE] == 3) { // HACIA DERECHA
                                            twistFace(RubiksCube.YELLOW, false, layer);
                                            twistFace(RubiksCube.GREEN, false, layer);
                                            twistFace(RubiksCube.YELLOW, true, layer);
                                            twistFace(RubiksCube.GREEN, true, layer);
                                            twistFace(RubiksCube.YELLOW, true, layer);
                                            twistFace(RubiksCube.RED, true, layer);
                                            twistFace(RubiksCube.YELLOW, false, layer);
                                            twistFace(RubiksCube.RED, false, layer);
                                          } else if (location[POSITIONINFACE] == 5) { // HACIA IZQUIERDA
                                            twistFace(RubiksCube.YELLOW, true, layer);
                                            twistFace(RubiksCube.BLUE, true, layer);
                                            twistFace(RubiksCube.YELLOW, false, layer);
                                            twistFace(RubiksCube.BLUE, false, layer);
                                            twistFace(RubiksCube.YELLOW, false, layer);
                                            twistFace(RubiksCube.RED, false, layer);
                                            twistFace(RubiksCube.YELLOW, true, layer);
                                            twistFace(RubiksCube.RED, true, layer);
                                          }
                                          break;
                    case RubiksCube.GREEN : if (location[POSITIONINFACE] == 3) {
                                                twistFace(RubiksCube.YELLOW, false, layer);
                                            } else if (location[POSITIONINFACE] == 1) {
                                                twistFace(RubiksCube.YELLOW, false, layer);
                                                twistFace(RubiksCube.ORANGE, false, layer);
                                                twistFace(RubiksCube.YELLOW, true, layer);
                                                twistFace(RubiksCube.ORANGE, true, layer);
                                                twistFace(RubiksCube.YELLOW, true, layer);
                                                twistFace(RubiksCube.GREEN, true, layer);
                                                twistFace(RubiksCube.YELLOW, false, layer);
                                                twistFace(RubiksCube.GREEN, false, layer);
                                            } else if (location[POSITIONINFACE] == 7) {
                                                twistFace(RubiksCube.YELLOW, true, layer);
                                                twistFace(RubiksCube.RED, true, layer);
                                                twistFace(RubiksCube.YELLOW, false, layer);
                                                twistFace(RubiksCube.RED, false, layer);
                                                twistFace(RubiksCube.YELLOW, false, layer);
                                                twistFace(RubiksCube.GREEN, false, layer);
                                                twistFace(RubiksCube.YELLOW, true, layer);
                                                twistFace(RubiksCube.GREEN, true, layer);
                                            }
                                            break;
                    case RubiksCube.ORANGE : if (location[POSITIONINFACE] == 1) {
                                                twistFace(RubiksCube.YELLOW, false, layer);
                                                twistFace(RubiksCube.YELLOW, false, layer);
                                             } else if (location[POSITIONINFACE] == 5) {
                                                twistFace(RubiksCube.YELLOW, false, layer);
                                                twistFace(RubiksCube.BLUE, false, layer);
                                                twistFace(RubiksCube.YELLOW, true, layer);
                                                twistFace(RubiksCube.BLUE, true, layer);
                                                twistFace(RubiksCube.YELLOW, true, layer);
                                                twistFace(RubiksCube.ORANGE, true, layer);
                                                twistFace(RubiksCube.YELLOW, false, layer);
                                                twistFace(RubiksCube.ORANGE, false, layer);
                                             } else if (location[POSITIONINFACE] == 3) {
                                                twistFace(RubiksCube.YELLOW, true, layer);
                                                twistFace(RubiksCube.GREEN, true, layer);
                                                twistFace(RubiksCube.YELLOW, false, layer);
                                                twistFace(RubiksCube.GREEN, false, layer);
                                                twistFace(RubiksCube.YELLOW, false, layer);
                                                twistFace(RubiksCube.ORANGE, false, layer);
                                                twistFace(RubiksCube.YELLOW, true, layer);
                                                twistFace(RubiksCube.ORANGE, true, layer);
                                             }
                                             break;
                    case RubiksCube.BLUE : if (location[POSITIONINFACE] == 5) {
                                                twistFace(RubiksCube.YELLOW, true, layer);
                                            } else if (location[POSITIONINFACE] == 7) {
                                                twistFace(RubiksCube.YELLOW, false, layer);
                                                twistFace(RubiksCube.RED, false, layer);
                                                twistFace(RubiksCube.YELLOW, true, layer);
                                                twistFace(RubiksCube.RED, true, layer);
                                                twistFace(RubiksCube.YELLOW, true, layer);
                                                twistFace(RubiksCube.BLUE, true, layer);
                                                twistFace(RubiksCube.YELLOW, false, layer);
                                                twistFace(RubiksCube.BLUE, false, layer);
                                            } else if (location[POSITIONINFACE] == 1) {
                                                twistFace(RubiksCube.YELLOW, true, layer);
                                                twistFace(RubiksCube.ORANGE, true, layer);
                                                twistFace(RubiksCube.YELLOW, false, layer);
                                                twistFace(RubiksCube.ORANGE, false, layer);
                                                twistFace(RubiksCube.YELLOW, false, layer);
                                                twistFace(RubiksCube.BLUE, false, layer);
                                                twistFace(RubiksCube.YELLOW, true, layer);
                                                twistFace(RubiksCube.BLUE, true, layer);
                                            }
                }
            }
        }

        while (cube.getColorAt(RubiksCube.GREEN, 1) != RubiksCube.GREEN || cube.getColorAt(RubiksCube.ORANGE, 3) != RubiksCube.ORANGE) {
            int[] location = cube.getEdge(RubiksCube.GREEN, RubiksCube.ORANGE);

            if (location[FACE] == RubiksCube.YELLOW) {
                
                if (location[POSITIONINFACE] == 1) {
                    twistFace(RubiksCube.YELLOW, true, layer);
                    twistFace(RubiksCube.YELLOW, true, layer);
                } else if (location[POSITIONINFACE] == 3) {
                    twistFace(RubiksCube.YELLOW, true, layer);
                } else if (location[POSITIONINFACE] == 5) {
                    twistFace(RubiksCube.YELLOW, false, layer);
                }

                twistFace(RubiksCube.YELLOW, true, layer);
                twistFace(RubiksCube.GREEN, true, layer);
                twistFace(RubiksCube.YELLOW, false, layer);
                twistFace(RubiksCube.GREEN, false, layer);
                twistFace(RubiksCube.YELLOW, false, layer);
                twistFace(RubiksCube.ORANGE, false, layer);
                twistFace(RubiksCube.YELLOW, true, layer);
                twistFace(RubiksCube.ORANGE, true, layer);

            } else {
                switch (location[FACE]) {
                    case RubiksCube.RED : if (location[POSITIONINFACE] == 7) {
                                            twistFace(RubiksCube.YELLOW, true, layer);
                                          } else if (location[POSITIONINFACE] == 5) { // HACIA IZQUIERDA
                                            twistFace(RubiksCube.YELLOW, true, layer);
                                            twistFace(RubiksCube.BLUE, true, layer);
                                            twistFace(RubiksCube.YELLOW, false, layer);
                                            twistFace(RubiksCube.BLUE, false, layer);
                                            twistFace(RubiksCube.YELLOW, false, layer);
                                            twistFace(RubiksCube.RED, false, layer);
                                            twistFace(RubiksCube.YELLOW, true, layer);
                                            twistFace(RubiksCube.RED, true, layer);
                                          }
                                          break;
                    case RubiksCube.GREEN : if (location[POSITIONINFACE] == 3) {
                                                twistFace(RubiksCube.YELLOW, false, layer);
                                                twistFace(RubiksCube.ORANGE, false, layer);
                                                twistFace(RubiksCube.YELLOW, true, layer);
                                                twistFace(RubiksCube.ORANGE, true, layer);
                                                twistFace(RubiksCube.YELLOW, true, layer);
                                                twistFace(RubiksCube.GREEN, true, layer);
                                                twistFace(RubiksCube.YELLOW, false, layer);
                                                twistFace(RubiksCube.GREEN, false, layer);
                                            } else if (location[POSITIONINFACE] == 7) {
                                                twistFace(RubiksCube.YELLOW, true, layer);
                                                twistFace(RubiksCube.RED, true, layer);
                                                twistFace(RubiksCube.YELLOW, false, layer);
                                                twistFace(RubiksCube.RED, false, layer);
                                                twistFace(RubiksCube.YELLOW, false, layer);
                                                twistFace(RubiksCube.GREEN, false, layer);
                                                twistFace(RubiksCube.YELLOW, true, layer);
                                                twistFace(RubiksCube.GREEN, true, layer);
                                            }
                                            break;
                    case RubiksCube.ORANGE : if (location[POSITIONINFACE] == 1) {
                                                twistFace(RubiksCube.YELLOW, false, layer);
                                             } else if (location[POSITIONINFACE] == 5) {
                                                twistFace(RubiksCube.YELLOW, false, layer);
                                                twistFace(RubiksCube.BLUE, false, layer);
                                                twistFace(RubiksCube.YELLOW, true, layer);
                                                twistFace(RubiksCube.BLUE, true, layer);
                                                twistFace(RubiksCube.YELLOW, true, layer);
                                                twistFace(RubiksCube.ORANGE, true, layer);
                                                twistFace(RubiksCube.YELLOW, false, layer);
                                                twistFace(RubiksCube.ORANGE, false, layer);
                                             } else if (location[POSITIONINFACE] == 3) {
                                                twistFace(RubiksCube.YELLOW, true, layer);
                                                twistFace(RubiksCube.GREEN, true, layer);
                                                twistFace(RubiksCube.YELLOW, false, layer);
                                                twistFace(RubiksCube.GREEN, false, layer);
                                                twistFace(RubiksCube.YELLOW, false, layer);
                                                twistFace(RubiksCube.ORANGE, false, layer);
                                                twistFace(RubiksCube.YELLOW, true, layer);
                                                twistFace(RubiksCube.ORANGE, true, layer);
                                             }
                                             break;
                    case RubiksCube.BLUE : if (location[POSITIONINFACE] == 5) {
                                                twistFace(RubiksCube.YELLOW, true, layer);
                                                twistFace(RubiksCube.YELLOW, true, layer);
                                            } else if (location[POSITIONINFACE] == 7) {
                                                twistFace(RubiksCube.YELLOW, false, layer);
                                                twistFace(RubiksCube.RED, false, layer);
                                                twistFace(RubiksCube.YELLOW, true, layer);
                                                twistFace(RubiksCube.RED, true, layer);
                                                twistFace(RubiksCube.YELLOW, true, layer);
                                                twistFace(RubiksCube.BLUE, true, layer);
                                                twistFace(RubiksCube.YELLOW, false, layer);
                                                twistFace(RubiksCube.BLUE, false, layer);
                                            } else if (location[POSITIONINFACE] == 1) {
                                                twistFace(RubiksCube.YELLOW, true, layer);
                                                twistFace(RubiksCube.ORANGE, true, layer);
                                                twistFace(RubiksCube.YELLOW, false, layer);
                                                twistFace(RubiksCube.ORANGE, false, layer);
                                                twistFace(RubiksCube.YELLOW, false, layer);
                                                twistFace(RubiksCube.BLUE, false, layer);
                                                twistFace(RubiksCube.YELLOW, true, layer);
                                                twistFace(RubiksCube.BLUE, true, layer);
                                            }
                }
            }
        }

        while (cube.getColorAt(RubiksCube.ORANGE, 5) != RubiksCube.ORANGE || cube.getColorAt(RubiksCube.BLUE, 1) != RubiksCube.BLUE) {
            int[] location = cube.getEdge(RubiksCube.ORANGE, RubiksCube.BLUE);

            if (location[FACE] == RubiksCube.YELLOW) {
                
                if (location[POSITIONINFACE] == 1) {
                    twistFace(RubiksCube.YELLOW, false, layer);
                } else if (location[POSITIONINFACE] == 3) {
                    twistFace(RubiksCube.YELLOW, true, layer);
                    twistFace(RubiksCube.YELLOW, true, layer);
                } else if (location[POSITIONINFACE] == 7) {
                    twistFace(RubiksCube.YELLOW, true, layer);
                }
                twistFace(RubiksCube.YELLOW, true, layer);
                twistFace(RubiksCube.ORANGE, true, layer);
                twistFace(RubiksCube.YELLOW, false, layer);
                twistFace(RubiksCube.ORANGE, false, layer);
                twistFace(RubiksCube.YELLOW, false, layer);
                twistFace(RubiksCube.BLUE, false, layer);
                twistFace(RubiksCube.YELLOW, true, layer);
                twistFace(RubiksCube.BLUE, true, layer);


            } else {
                switch (location[FACE]) {
                    case RubiksCube.RED : if (location[POSITIONINFACE] == 7) { // HACIA DERECHA
                                            twistFace(RubiksCube.YELLOW, true, layer);
                                            twistFace(RubiksCube.YELLOW, true, layer);
                                          } else if (location[POSITIONINFACE] == 5) { // HACIA IZQUIERDA
                                            twistFace(RubiksCube.YELLOW, true, layer);
                                            twistFace(RubiksCube.BLUE, true, layer);
                                            twistFace(RubiksCube.YELLOW, false, layer);
                                            twistFace(RubiksCube.BLUE, false, layer);
                                            twistFace(RubiksCube.YELLOW, false, layer);
                                            twistFace(RubiksCube.RED, false, layer);
                                            twistFace(RubiksCube.YELLOW, true, layer);
                                            twistFace(RubiksCube.RED, true, layer);
                                          }
                                          break;
                    case RubiksCube.GREEN : if (location[POSITIONINFACE] == 3) {
                                                twistFace(RubiksCube.YELLOW, true, layer);
                                            } 
                                            break;
                    case RubiksCube.ORANGE : if (location[POSITIONINFACE] == 1) {
                                                twistFace(RubiksCube.YELLOW, false, layer);
                                                twistFace(RubiksCube.BLUE, false, layer);
                                                twistFace(RubiksCube.YELLOW, true, layer);
                                                twistFace(RubiksCube.BLUE, true, layer);
                                                twistFace(RubiksCube.YELLOW, true, layer);
                                                twistFace(RubiksCube.ORANGE, true, layer);
                                                twistFace(RubiksCube.YELLOW, false, layer);
                                                twistFace(RubiksCube.ORANGE, false, layer);
                                             }
                                             break;
                    case RubiksCube.BLUE : if (location[POSITIONINFACE] == 5) {
                                                twistFace(RubiksCube.YELLOW, false, layer);
                                            } else if (location[POSITIONINFACE] == 7) {
                                                twistFace(RubiksCube.YELLOW, false, layer);
                                                twistFace(RubiksCube.RED, false, layer);
                                                twistFace(RubiksCube.YELLOW, true, layer);
                                                twistFace(RubiksCube.RED, true, layer);
                                                twistFace(RubiksCube.YELLOW, true, layer);
                                                twistFace(RubiksCube.BLUE, true, layer);
                                                twistFace(RubiksCube.YELLOW, false, layer);
                                                twistFace(RubiksCube.BLUE, false, layer);
                                            } else if (location[POSITIONINFACE] == 1) {
                                                twistFace(RubiksCube.YELLOW, true, layer);
                                                twistFace(RubiksCube.ORANGE, true, layer);
                                                twistFace(RubiksCube.YELLOW, false, layer);
                                                twistFace(RubiksCube.ORANGE, false, layer);
                                                twistFace(RubiksCube.YELLOW, false, layer);
                                                twistFace(RubiksCube.BLUE, false, layer);
                                                twistFace(RubiksCube.YELLOW, true, layer);
                                                twistFace(RubiksCube.BLUE, true, layer);
                                            }
                                            break;
                }
            }
        }

        while (cube.getColorAt(RubiksCube.BLUE, 7) != RubiksCube.BLUE || cube.getColorAt(RubiksCube.RED, 5) != RubiksCube.RED) {
            int[] location = cube.getEdge(RubiksCube.BLUE, RubiksCube.RED);

            if (location[FACE] == RubiksCube.YELLOW) {
                
                if (location[POSITIONINFACE] == 3) {
                    twistFace(RubiksCube.YELLOW, false, layer);
                } else if (location[POSITIONINFACE] == 5) {
                    twistFace(RubiksCube.YELLOW, true, layer);
                } else if (location[POSITIONINFACE] == 7) {
                    twistFace(RubiksCube.YELLOW, false, layer);
                    twistFace(RubiksCube.YELLOW, false, layer);
                }
                twistFace(RubiksCube.YELLOW, true, layer);
                twistFace(RubiksCube.BLUE, true, layer);
                twistFace(RubiksCube.YELLOW, false, layer);
                twistFace(RubiksCube.BLUE, false, layer);
                twistFace(RubiksCube.YELLOW, false, layer);
                twistFace(RubiksCube.RED, false, layer);
                twistFace(RubiksCube.YELLOW, true, layer);
                twistFace(RubiksCube.RED, true, layer);


            } else {
                switch (location[FACE]) {
                    case RubiksCube.RED : if (location[POSITIONINFACE] == 7) { // HACIA DERECHA
                                            twistFace(RubiksCube.YELLOW, false, layer);
                                          } else if (location[POSITIONINFACE] == 5) { // HACIA IZQUIERDA
                                            twistFace(RubiksCube.YELLOW, true, layer);
                                            twistFace(RubiksCube.BLUE, true, layer);
                                            twistFace(RubiksCube.YELLOW, false, layer);
                                            twistFace(RubiksCube.BLUE, false, layer);
                                            twistFace(RubiksCube.YELLOW, false, layer);
                                            twistFace(RubiksCube.RED, false, layer);
                                            twistFace(RubiksCube.YELLOW, true, layer);
                                            twistFace(RubiksCube.RED, true, layer);
                                          }
                                          break;
                    case RubiksCube.GREEN : if (location[POSITIONINFACE] == 3) {
                                                twistFace(RubiksCube.YELLOW, false, layer);
                                                twistFace(RubiksCube.YELLOW, false, layer);
                                            }
                                            break;
                    case RubiksCube.ORANGE : if (location[POSITIONINFACE] == 1) {
                                                twistFace(RubiksCube.YELLOW, true, layer);
                                             }
                                             break;
                    case RubiksCube.BLUE : if (location[POSITIONINFACE] == 5) {
                                                twistFace(RubiksCube.YELLOW, false, layer);
                                                twistFace(RubiksCube.RED, false, layer);
                                                twistFace(RubiksCube.YELLOW, true, layer);
                                                twistFace(RubiksCube.RED, true, layer);
                                                twistFace(RubiksCube.YELLOW, true, layer);
                                                twistFace(RubiksCube.BLUE, true, layer);
                                                twistFace(RubiksCube.YELLOW, false, layer);
                                                twistFace(RubiksCube.BLUE, false, layer);
                                            }
                                            break;
                }
            }
        }
        return layer;
    }


    public LinkedList<Integer> solveYellowCross() {
        LinkedList<Integer> cross = new LinkedList<Integer>();

        while (cube.getColorAt(RubiksCube.YELLOW, 1) != RubiksCube.YELLOW || cube.getColorAt(RubiksCube.YELLOW, 3) != RubiksCube.YELLOW
               || cube.getColorAt(RubiksCube.YELLOW, 5) != RubiksCube.YELLOW || cube.getColorAt(RubiksCube.YELLOW, 7) != RubiksCube.YELLOW) {

            if (cube.getColorAt(RubiksCube.YELLOW, 3) == RubiksCube.YELLOW && cube.getColorAt(RubiksCube.YELLOW, 5) == RubiksCube.YELLOW) {
                twistFace(RubiksCube.ORANGE, false, cross);
                twistFace(RubiksCube.BLUE, false, cross);
                twistFace(RubiksCube.YELLOW, false, cross);
                twistFace(RubiksCube.BLUE, true, cross);
                twistFace(RubiksCube.YELLOW, true, cross);
                twistFace(RubiksCube.ORANGE, true, cross);
            } else if (cube.getColorAt(RubiksCube.YELLOW, 1) == RubiksCube.YELLOW && cube.getColorAt(RubiksCube.YELLOW, 7) == RubiksCube.YELLOW) {
                twistFace(RubiksCube.BLUE, false, cross);
                twistFace(RubiksCube.RED, false, cross);
                twistFace(RubiksCube.YELLOW, false, cross);
                twistFace(RubiksCube.RED, true, cross);
                twistFace(RubiksCube.YELLOW, true, cross);
                twistFace(RubiksCube.BLUE, true, cross);
            } else if (cube.getColorAt(RubiksCube.YELLOW, 1) == RubiksCube.YELLOW && cube.getColorAt(RubiksCube.YELLOW, 3) == RubiksCube.YELLOW) {
                twistFace(RubiksCube.ORANGE, false, cross);
                twistFace(RubiksCube.YELLOW, false, cross);
                twistFace(RubiksCube.BLUE, false, cross);
                twistFace(RubiksCube.YELLOW, true, cross);
                twistFace(RubiksCube.BLUE, true, cross);
                twistFace(RubiksCube.ORANGE, true, cross);
            } else if (cube.getColorAt(RubiksCube.YELLOW, 1) == RubiksCube.YELLOW && cube.getColorAt(RubiksCube.YELLOW, 5) == RubiksCube.YELLOW) {
                twistFace(RubiksCube.GREEN, false, cross);
                twistFace(RubiksCube.YELLOW, false, cross);
                twistFace(RubiksCube.ORANGE, false, cross);
                twistFace(RubiksCube.YELLOW, true, cross);
                twistFace(RubiksCube.ORANGE, true, cross);
                twistFace(RubiksCube.GREEN, true, cross);
            } else if (cube.getColorAt(RubiksCube.YELLOW, 3) == RubiksCube.YELLOW && cube.getColorAt(RubiksCube.YELLOW, 7) == RubiksCube.YELLOW) {
                twistFace(RubiksCube.BLUE, false, cross);
                twistFace(RubiksCube.YELLOW, false, cross);
                twistFace(RubiksCube.RED, false, cross);
                twistFace(RubiksCube.YELLOW, true, cross);
                twistFace(RubiksCube.RED, true, cross);
                twistFace(RubiksCube.BLUE, true, cross);
            } else if (cube.getColorAt(RubiksCube.YELLOW, 5) == RubiksCube.YELLOW && cube.getColorAt(RubiksCube.YELLOW, 7) == RubiksCube.YELLOW) {
                twistFace(RubiksCube.RED, false, cross);
                twistFace(RubiksCube.YELLOW, false, cross);
                twistFace(RubiksCube.GREEN, false, cross);
                twistFace(RubiksCube.YELLOW, true, cross);
                twistFace(RubiksCube.GREEN, true, cross);
                twistFace(RubiksCube.RED, true, cross);
            } else {
                twistFace(RubiksCube.ORANGE, false, cross);
                twistFace(RubiksCube.YELLOW, false, cross);
                twistFace(RubiksCube.BLUE, false, cross);
                twistFace(RubiksCube.YELLOW, true, cross);
                twistFace(RubiksCube.BLUE, true, cross);
                twistFace(RubiksCube.ORANGE, true, cross);
            }
        }
        return cross;
    }


    public LinkedList<Integer> solveYellowCorners() {
        LinkedList<Integer> corners = new LinkedList<Integer>();

        while (cube.getColorAt(RubiksCube.YELLOW, 0) != RubiksCube.YELLOW || cube.getColorAt(RubiksCube.YELLOW, 2) != RubiksCube.YELLOW
               || cube.getColorAt(RubiksCube.YELLOW, 6) != RubiksCube.YELLOW || cube.getColorAt(RubiksCube.YELLOW, 8) != RubiksCube.YELLOW) {

            if ((cube.getColorAt(RubiksCube.YELLOW, 0) == RubiksCube.YELLOW && cube.getColorAt(RubiksCube.YELLOW, 2) == RubiksCube.YELLOW)
                || (cube.getColorAt(RubiksCube.YELLOW, 0) == RubiksCube.YELLOW && cube.getColorAt(RubiksCube.YELLOW, 8) == RubiksCube.YELLOW)
                || (cube.getColorAt(RubiksCube.YELLOW, 0) == RubiksCube.YELLOW && cube.getColorAt(RubiksCube.YELLOW, 6) == RubiksCube.YELLOW)
                || (cube.getColorAt(RubiksCube.YELLOW, 2) == RubiksCube.YELLOW && cube.getColorAt(RubiksCube.YELLOW, 8) == RubiksCube.YELLOW)
                || (cube.getColorAt(RubiksCube.YELLOW, 2) == RubiksCube.YELLOW && cube.getColorAt(RubiksCube.YELLOW, 6) == RubiksCube.YELLOW)
                || (cube.getColorAt(RubiksCube.YELLOW, 6) == RubiksCube.YELLOW && cube.getColorAt(RubiksCube.YELLOW, 8) == RubiksCube.YELLOW)) {

                while (cube.getColorAt(RubiksCube.ORANGE, 0) != RubiksCube.YELLOW) {
                    twistFace(RubiksCube.YELLOW, true, corners);
                }
            } else if (cube.getColorAt(RubiksCube.YELLOW, 0) == RubiksCube.YELLOW) {
                twistFace(RubiksCube.YELLOW, true, corners);
            } else if (cube.getColorAt(RubiksCube.YELLOW, 2) == RubiksCube.YELLOW) {
                twistFace(RubiksCube.YELLOW, true, corners);
                twistFace(RubiksCube.YELLOW, true, corners);
            } else if (cube.getColorAt(RubiksCube.YELLOW, 8) == RubiksCube.YELLOW) {
                twistFace(RubiksCube.YELLOW, false, corners);
            } else if (cube.getColorAt(RubiksCube.YELLOW, 6) != RubiksCube.YELLOW) {
                while (cube.getColorAt(RubiksCube.GREEN, 0) != RubiksCube.YELLOW) {
                    twistFace(RubiksCube.YELLOW, false, corners);
                }
            }

            twistFace(RubiksCube.BLUE, false, corners);
            twistFace(RubiksCube.YELLOW, false, corners);
            twistFace(RubiksCube.BLUE, true, corners);
            twistFace(RubiksCube.YELLOW, false, corners);
            twistFace(RubiksCube.BLUE, false, corners);
            twistFace(RubiksCube.YELLOW, false, corners);
            twistFace(RubiksCube.YELLOW, false, corners);
            twistFace(RubiksCube.BLUE, true, corners);
        }

        return corners;
    }


    public LinkedList<Integer> rearrangeYellowCorners() {
        LinkedList<Integer> corners = new LinkedList<Integer>();

        while (cube.getColorAt(RubiksCube.RED, 6) != RubiksCube.RED || cube.getColorAt(RubiksCube.RED, 8) != RubiksCube.RED
               || cube.getColorAt(RubiksCube.ORANGE, 0) != RubiksCube.ORANGE || cube.getColorAt(RubiksCube.ORANGE, 2) != RubiksCube.ORANGE) {

            while (cube.getColorAt(RubiksCube.RED, 6) != RubiksCube.RED) {
                twistFace(RubiksCube.YELLOW, true, corners);
            }

            if (cube.getColorAt(RubiksCube.RED, 8) == RubiksCube.RED && cube.getColorAt(RubiksCube.RED, 8) == RubiksCube.RED) {

                twistFace(RubiksCube.BLUE, true, corners);
                twistFace(RubiksCube.ORANGE, false, corners);
                twistFace(RubiksCube.BLUE, true, corners);
                twistFace(RubiksCube.RED, false, corners);
                twistFace(RubiksCube.RED, false, corners);
                twistFace(RubiksCube.BLUE, false, corners);
                twistFace(RubiksCube.ORANGE, true, corners);
                twistFace(RubiksCube.BLUE, true, corners);
                twistFace(RubiksCube.RED, false, corners);
                twistFace(RubiksCube.RED, false, corners);
                twistFace(RubiksCube.BLUE, false, corners);
                twistFace(RubiksCube.BLUE, false, corners);
                twistFace(RubiksCube.YELLOW, true, corners);

            } else if (cube.getColorAt(RubiksCube.GREEN, 0) == RubiksCube.GREEN && cube.getColorAt(RubiksCube.GREEN, 6) == RubiksCube.GREEN) {

                twistFace(RubiksCube.RED, true, corners);
                twistFace(RubiksCube.BLUE, false, corners);
                twistFace(RubiksCube.RED, true, corners);
                twistFace(RubiksCube.GREEN, false, corners);
                twistFace(RubiksCube.GREEN, false, corners);
                twistFace(RubiksCube.RED, false, corners);
                twistFace(RubiksCube.BLUE, true, corners);
                twistFace(RubiksCube.RED, true, corners);
                twistFace(RubiksCube.GREEN, false, corners);
                twistFace(RubiksCube.GREEN, false, corners);
                twistFace(RubiksCube.RED, false, corners);
                twistFace(RubiksCube.RED, false, corners);
                twistFace(RubiksCube.YELLOW, true, corners);
            } else if (cube.getColorAt(RubiksCube.ORANGE, 0) == RubiksCube.ORANGE && cube.getColorAt(RubiksCube.ORANGE, 2) == RubiksCube.ORANGE) {
                twistFace(RubiksCube.GREEN, true, corners);
                twistFace(RubiksCube.RED, false, corners);
                twistFace(RubiksCube.GREEN, true, corners);
                twistFace(RubiksCube.ORANGE, false, corners);
                twistFace(RubiksCube.ORANGE, false, corners);
                twistFace(RubiksCube.GREEN, false, corners);
                twistFace(RubiksCube.RED, true, corners);
                twistFace(RubiksCube.GREEN, true, corners);
                twistFace(RubiksCube.ORANGE, false, corners);
                twistFace(RubiksCube.ORANGE, false, corners);
                twistFace(RubiksCube.GREEN, false, corners);
                twistFace(RubiksCube.GREEN, false, corners);
                twistFace(RubiksCube.YELLOW, true, corners);
            } else {
                twistFace(RubiksCube.ORANGE, true, corners);
                twistFace(RubiksCube.GREEN, false, corners);
                twistFace(RubiksCube.ORANGE, true, corners);
                twistFace(RubiksCube.BLUE, false, corners);
                twistFace(RubiksCube.BLUE, false, corners);
                twistFace(RubiksCube.ORANGE, false, corners);
                twistFace(RubiksCube.GREEN, true, corners);
                twistFace(RubiksCube.ORANGE, true, corners);
                twistFace(RubiksCube.BLUE, false, corners);
                twistFace(RubiksCube.BLUE, false, corners);
                twistFace(RubiksCube.ORANGE, false, corners);
                twistFace(RubiksCube.ORANGE, false, corners);
                twistFace(RubiksCube.YELLOW, true, corners);
            }
        }
        return corners;
    }


    public LinkedList<Integer> rearrangeYellowEdges() {
        LinkedList<Integer> edges = new LinkedList<Integer>();

        while (cube.getColorAt(RubiksCube.BLUE, 5) != RubiksCube.BLUE || cube.getColorAt(RubiksCube.ORANGE, 1) != RubiksCube.ORANGE
               || cube.getColorAt(RubiksCube.GREEN, 3) != RubiksCube.GREEN || cube.getColorAt(RubiksCube.RED, 7) != RubiksCube.RED) {

            if (cube.getColorAt(RubiksCube.GREEN, 3) == RubiksCube.GREEN) {
                if (cube.getColorAt(RubiksCube.ORANGE, 1) == RubiksCube.RED) { //CLOCKWISE

                    twistFace(RubiksCube.BLUE, false, edges);
                    twistFace(RubiksCube.BLUE, false, edges);
                    twistFace(RubiksCube.YELLOW, false, edges);
                    twistFace(RubiksCube.ORANGE, false, edges);
                    twistFace(RubiksCube.RED, true, edges);
                    twistFace(RubiksCube.BLUE, false, edges);
                    twistFace(RubiksCube.BLUE, false, edges);
                    twistFace(RubiksCube.ORANGE, true, edges);
                    twistFace(RubiksCube.RED, false, edges);
                    twistFace(RubiksCube.YELLOW, false, edges);
                    twistFace(RubiksCube.BLUE, false, edges);
                    twistFace(RubiksCube.BLUE, false, edges);
                } else if (cube.getColorAt(RubiksCube.ORANGE, 1) == RubiksCube.BLUE) { //COUNTERCLOCKWISE

                    twistFace(RubiksCube.BLUE, false, edges);
                    twistFace(RubiksCube.BLUE, false, edges);
                    twistFace(RubiksCube.YELLOW, true, edges);
                    twistFace(RubiksCube.ORANGE, false, edges);
                    twistFace(RubiksCube.RED, true, edges);
                    twistFace(RubiksCube.BLUE, false, edges);
                    twistFace(RubiksCube.BLUE, false, edges);
                    twistFace(RubiksCube.ORANGE, true, edges);
                    twistFace(RubiksCube.RED, false, edges);
                    twistFace(RubiksCube.YELLOW, true, edges);
                    twistFace(RubiksCube.BLUE, false, edges);
                    twistFace(RubiksCube.BLUE, false, edges);
                }
            } else if (cube.getColorAt(RubiksCube.ORANGE, 1) == RubiksCube.ORANGE) {
                if (cube.getColorAt(RubiksCube.BLUE, 5) == RubiksCube.GREEN) { //CLOCKWISE

                    twistFace(RubiksCube.RED, false, edges);
                    twistFace(RubiksCube.RED, false, edges);
                    twistFace(RubiksCube.YELLOW, false, edges);
                    twistFace(RubiksCube.BLUE, false, edges);
                    twistFace(RubiksCube.GREEN, true, edges);
                    twistFace(RubiksCube.RED, false, edges);
                    twistFace(RubiksCube.RED, false, edges);
                    twistFace(RubiksCube.BLUE, true, edges);
                    twistFace(RubiksCube.GREEN, false, edges);
                    twistFace(RubiksCube.YELLOW, false, edges);
                    twistFace(RubiksCube.RED, false, edges);
                    twistFace(RubiksCube.RED, false, edges);
                } else if (cube.getColorAt(RubiksCube.BLUE, 5) == RubiksCube.RED) { //COUNTERCLOCKWISE

                    twistFace(RubiksCube.RED, false, edges);
                    twistFace(RubiksCube.RED, false, edges);
                    twistFace(RubiksCube.YELLOW, true, edges);
                    twistFace(RubiksCube.BLUE, false, edges);
                    twistFace(RubiksCube.GREEN, true, edges);
                    twistFace(RubiksCube.RED, false, edges);
                    twistFace(RubiksCube.RED, false, edges);
                    twistFace(RubiksCube.BLUE, true, edges);
                    twistFace(RubiksCube.GREEN, false, edges);
                    twistFace(RubiksCube.YELLOW, true, edges);
                    twistFace(RubiksCube.RED, false, edges);
                    twistFace(RubiksCube.RED, false, edges);
                }
            } else if (cube.getColorAt(RubiksCube.BLUE, 5) == RubiksCube.BLUE) {
                if (cube.getColorAt(RubiksCube.RED, 7) == RubiksCube.ORANGE) { //CLOCKWISE

                    twistFace(RubiksCube.GREEN, false, edges);
                    twistFace(RubiksCube.GREEN, false, edges);
                    twistFace(RubiksCube.YELLOW, false, edges);
                    twistFace(RubiksCube.RED, false, edges);
                    twistFace(RubiksCube.ORANGE, true, edges);
                    twistFace(RubiksCube.GREEN, false, edges);
                    twistFace(RubiksCube.GREEN, false, edges);
                    twistFace(RubiksCube.RED, true, edges);
                    twistFace(RubiksCube.ORANGE, false, edges);
                    twistFace(RubiksCube.YELLOW, false, edges);
                    twistFace(RubiksCube.GREEN, false, edges);
                    twistFace(RubiksCube.GREEN, false, edges);
                } else if (cube.getColorAt(RubiksCube.RED, 7) == RubiksCube.GREEN) { //COUNTERCLOCKWISE

                    twistFace(RubiksCube.GREEN, false, edges);
                    twistFace(RubiksCube.GREEN, false, edges);
                    twistFace(RubiksCube.YELLOW, true, edges);
                    twistFace(RubiksCube.RED, false, edges);
                    twistFace(RubiksCube.ORANGE, true, edges);
                    twistFace(RubiksCube.GREEN, false, edges);
                    twistFace(RubiksCube.GREEN, false, edges);
                    twistFace(RubiksCube.RED, true, edges);
                    twistFace(RubiksCube.ORANGE, false, edges);
                    twistFace(RubiksCube.YELLOW, true, edges);
                    twistFace(RubiksCube.GREEN, false, edges);
                    twistFace(RubiksCube.GREEN, false, edges);
                }
            } else if (cube.getColorAt(RubiksCube.RED, 7) == RubiksCube.RED) {
                if (cube.getColorAt(RubiksCube.GREEN, 3) == RubiksCube.BLUE) { //CLOCKWISE

                    twistFace(RubiksCube.ORANGE, false, edges);
                    twistFace(RubiksCube.ORANGE, false, edges);
                    twistFace(RubiksCube.YELLOW, false, edges);
                    twistFace(RubiksCube.GREEN, false, edges);
                    twistFace(RubiksCube.BLUE, true, edges);
                    twistFace(RubiksCube.ORANGE, false, edges);
                    twistFace(RubiksCube.ORANGE, false, edges);
                    twistFace(RubiksCube.GREEN, true, edges);
                    twistFace(RubiksCube.BLUE, false, edges);
                    twistFace(RubiksCube.YELLOW, false, edges);
                    twistFace(RubiksCube.ORANGE, false, edges);
                    twistFace(RubiksCube.ORANGE, false, edges);
                } else if (cube.getColorAt(RubiksCube.GREEN, 3) == RubiksCube.ORANGE) { //COUNTERCLOCKWISE

                    twistFace(RubiksCube.ORANGE, false, edges);
                    twistFace(RubiksCube.ORANGE, false, edges);
                    twistFace(RubiksCube.YELLOW, true, edges);
                    twistFace(RubiksCube.GREEN, false, edges);
                    twistFace(RubiksCube.BLUE, true, edges);
                    twistFace(RubiksCube.ORANGE, false, edges);
                    twistFace(RubiksCube.ORANGE, false, edges);
                    twistFace(RubiksCube.GREEN, true, edges);
                    twistFace(RubiksCube.BLUE, false, edges);
                    twistFace(RubiksCube.YELLOW, true, edges);
                    twistFace(RubiksCube.ORANGE, false, edges);
                    twistFace(RubiksCube.ORANGE, false, edges);
                }
            } else {
                twistFace(RubiksCube.ORANGE, false, edges);
                twistFace(RubiksCube.ORANGE, false, edges);
                twistFace(RubiksCube.YELLOW, true, edges);
                twistFace(RubiksCube.GREEN, false, edges);
                twistFace(RubiksCube.BLUE, true, edges);
                twistFace(RubiksCube.ORANGE, false, edges);
                twistFace(RubiksCube.ORANGE, false, edges);
                twistFace(RubiksCube.GREEN, true, edges);
                twistFace(RubiksCube.BLUE, false, edges);
                twistFace(RubiksCube.YELLOW, true, edges);
                twistFace(RubiksCube.ORANGE, false, edges);
                twistFace(RubiksCube.ORANGE, false, edges);
            }
        }

        return edges;
    }


    private void twistFace(int face, boolean counterClockwise, LinkedList<Integer> list) {
        switch (face) {
            case RubiksCube.WHITE : if (counterClockwise) {
                                        cube.twistWhiteFace(counterClockwise);
                                        list.add(RubiksCube.F_MOVE);
                                    } else {
                                        cube.twistWhiteFace(counterClockwise);
                                        list.add(RubiksCube.FI_MOVE);
                                    }
                                    break;
            case RubiksCube.ORANGE : if (counterClockwise) {
                                        cube.twistOrangeFace(counterClockwise);
                                        list.add(RubiksCube.U_MOVE);
                                    } else {
                                        cube.twistOrangeFace(counterClockwise);
                                        list.add(RubiksCube.UI_MOVE);
                                    }
                                    break;
            case RubiksCube.YELLOW : if (counterClockwise) {
                                        cube.twistYellowFace(counterClockwise);
                                        list.add(RubiksCube.B_MOVE);
                                    } else {
                                        cube.twistYellowFace(counterClockwise);
                                        list.add(RubiksCube.BI_MOVE);
                                    }
                                    break;
            case RubiksCube.RED : if (counterClockwise) {
                                    cube.twistRedFace(counterClockwise);
                                    list.add(RubiksCube.D_MOVE);
                                  } else {
                                    cube.twistRedFace(counterClockwise);
                                    list.add(RubiksCube.DI_MOVE);
                                  }
                                  break;
            case RubiksCube.BLUE : if (counterClockwise) {
                                    cube.twistBlueFace(counterClockwise);
                                    list.add(RubiksCube.R_MOVE);
                                   } else {
                                    cube.twistBlueFace(counterClockwise);
                                    list.add(RubiksCube.RI_MOVE);
                                   }
                                   break;
            case RubiksCube.GREEN : if (counterClockwise) {
                                        cube.twistGreenFace(counterClockwise);
                                        list.add(RubiksCube.L_MOVE);
                                    } else {
                                        cube.twistGreenFace(counterClockwise);
                                        list.add(RubiksCube.LI_MOVE);
                                    }
        }
    }

}