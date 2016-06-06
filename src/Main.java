import gui.CubeDetectorWindow;

public class Main {

    /**
     * Metodo Main
     */
    public static void main(String[] args) {
        CubeDetectorWindow cdw = new CubeDetectorWindow();
        cdw.showInfo();
        while (true) {
            cdw.work();
        }
    }
}