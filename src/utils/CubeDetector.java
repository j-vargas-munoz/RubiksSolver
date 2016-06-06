package utils;

import org.opencv.core.Mat;
import org.opencv.core.Core;
import org.opencv.features2d.Features2d;
import org.opencv.highgui.VideoCapture;
import org.opencv.core.MatOfKeyPoint;
import org.opencv.core.MatOfPoint2f;
import org.opencv.core.MatOfDMatch;
import org.opencv.core.MatOfPoint;
import org.opencv.core.MatOfByte;
import org.opencv.highgui.Highgui;
import org.opencv.features2d.FeatureDetector;
import org.opencv.features2d.DescriptorExtractor;
import org.opencv.features2d.DescriptorMatcher;
import org.opencv.features2d.DMatch;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.BoxLayout;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.Container;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.ArrayList;
import org.opencv.calib3d.Calib3d;
import org.opencv.features2d.KeyPoint;
import org.opencv.core.Point;
import org.opencv.core.CvType;
import org.opencv.imgproc.Imgproc;
import java.awt.Graphics2D;
import org.opencv.core.Rect;
import org.opencv.photo.Photo;
import structures.RectWrapper;

public class CubeDetector {

    static{ System.loadLibrary(Core.NATIVE_LIBRARY_NAME); }

    VideoCapture camera;
    public Mat img1;
    public Mat grayImage;
    public Mat cannyImage;
    public Mat harrisImage;
    public Mat redImage;
    public Mat secondredImage;
    public Mat yellowImage;
    public Mat blueImage;
    public Mat orangeImage;
    public Mat greenImage;
    public Mat whiteImage;
    public ArrayList<RectWrapper> rectList;
    MatOfKeyPoint keyPoints1;
    MatOfKeyPoint keyPoints2;
    Rect[] definite;
    int time;
    static int MAX_TIME = 20;
    int maxAppearances;
    int loops;
    public boolean done;
    public boolean start;
    public RectWrapper[] ordered;


    public CubeDetector() {
        img1 = new Mat();
        camera = new VideoCapture(0);
        rectList = new ArrayList<RectWrapper>();
        time = 0;
        done = false;
        start = false;
        loops = 0;
        maxAppearances = 0;
        definite = null;
        camera.read(img1);
        camera.read(img1);
    }


    public void harrisFirst() {
        Mat result = new Mat();
        Mat other = new Mat();
        grayImage = img1.clone();
        harrisImage = img1.clone();
        Mat colorImage = img1.clone();
        boolean useHarris = true;
        
        Imgproc.cvtColor(img1, result, Imgproc.COLOR_BGR2GRAY);
        Imgproc.cvtColor(img1, grayImage, Imgproc.COLOR_BGR2HSV);
        redImage = grayImage.clone();
        secondredImage = grayImage.clone();
        yellowImage = grayImage.clone();
        blueImage = grayImage.clone();
        orangeImage = grayImage.clone();
        greenImage = grayImage.clone();
        whiteImage = grayImage.clone();
        
        FeatureDetector surfDetector = FeatureDetector.create(FeatureDetector.HARRIS);
        MatOfKeyPoint keyPoints1 = new MatOfKeyPoint();
        thresholdHSV(grayImage);
        Imgproc.GaussianBlur(grayImage, grayImage, new Size(3,3),0,0);
        surfDetector.detect(result, keyPoints1);

        Features2d.drawKeypoints(img1, keyPoints1, harrisImage,new Scalar(2,254,255),Features2d.DRAW_RICH_KEYPOINTS);
        
        Imgproc.Canny(grayImage, result, 300, 100);
        cannyImage = result.clone();
        
        LinkedList<MatOfPoint> points_object = new LinkedList<MatOfPoint>();
        Imgproc.findContours(result, points_object, other, Imgproc.RETR_LIST, Imgproc.CHAIN_APPROX_SIMPLE);
        
        
        KeyPoint keypointsarr[] = keyPoints1.toArray();
        ArrayList<Rect> possible = new ArrayList<Rect>();
        
        for (int i = 0; i < keypointsarr.length ; i++ ) {
            for (int j = 0; j < keypointsarr.length && j!=i; j++ ) {
                Rect rect = new Rect(keypointsarr[i].pt, keypointsarr[j].pt);
                double area = rect.area();
                if (rect.width == rect.height && area > 2500 && area < 4000) {
                    possible.add(rect);
                    Core.rectangle(harrisImage, rect.tl(), rect.br(), new Scalar(0, 255, 0),2, 8,0);
                }
            }
        }
        
        Rect[] possibleArray = possible.toArray(new Rect[possible.size()]);
        if (possible.size() < 5) useHarris = false;
        ArrayList<Rect> refined = new ArrayList<Rect>();

        MatOfPoint2f approxCurve = new MatOfPoint2f();
        for (int i=0; i< points_object.size(); i++) {
            MatOfPoint2f contour2f = new MatOfPoint2f( points_object.get(i).toArray() );

            double approxDistance = Imgproc.arcLength(contour2f, true)*0.02;
            Imgproc.approxPolyDP(contour2f, approxCurve, approxDistance, true);

            MatOfPoint points = new MatOfPoint( approxCurve.toArray() );
            Rect rect = Imgproc.boundingRect(points);

            if (rect.width == rect.height) {
                if (useHarris) {
                    for (int j = 0; j < possibleArray.length ; j++ ) {
                        Rect aux = possibleArray[j];
                        if ((aux.width <= rect.width+20 && aux.width >= rect.width-20) && (aux.x <= rect.x+20 && aux.x >= rect.x-20)) {
                            refined.add(rect);
                            break;
                        }
                    }
                } else {
                    double area = rect.area();
                    if (rect.width == rect.height && area > 2500 && area < 4000) {
                        refined.add(rect);
                    }
                }
            }
        }

        Rect[] auxArray = refined.toArray(new Rect[refined.size()]);

        if (rectList.size() == 0) {
            for (int j = 0; j < auxArray.length; j++) {
                rectList.add(new RectWrapper(auxArray[j]));
            }
        }

        RectWrapper[] wrapperArray = rectList.toArray(new RectWrapper[rectList.size()]);
        time++;


        for (int j = 0; j < auxArray.length; j++) {
            Rect refinedAux = auxArray[j];
            int removed = 0;
            boolean wasUpdated = false;
            for (int i = 0; i < wrapperArray.length ; i++) {
                Rect wrapperAux = wrapperArray[i].rect;
                int width = wrapperAux.width/3;
                if ((wrapperAux.width <= refinedAux.width+20 && wrapperAux.width >= refinedAux.width-20) && 
                    (wrapperAux.x <= refinedAux.x+width && wrapperAux.x >= refinedAux.x-width) &&
                    (wrapperAux.y <= refinedAux.y+width && wrapperAux.y >= refinedAux.y-width)) {
                    if (!wasUpdated) {
                        wrapperArray[i].appearances++;
                        wasUpdated = true;
                    } else {
                        rectList.remove(i-removed++);
                        wrapperArray = rectList.toArray(new RectWrapper[rectList.size()]);
                    }
                }
            }
            if (!wasUpdated) {
                rectList.add(new RectWrapper(refinedAux));
                wrapperArray = rectList.toArray(new RectWrapper[rectList.size()]);
            }
            wasUpdated = false;
            removed = 0;
        }

        wrapperArray = rectList.toArray(new RectWrapper[rectList.size()]);

        if (time >= 5) {
            int removed = 0;
            time = 0;
            loops++;
            for (int i = 0; i < wrapperArray.length ; i++) {
                wrapperArray[i].time+= 5;
                if (wrapperArray[i].appearances < 2) {
                    rectList.remove(i-removed);
                    removed++;
                } else if (wrapperArray[i].time > MAX_TIME && wrapperArray[i].appearances < (2*loops)) {
                    rectList.remove(i-removed);
                    removed++;
                }
            }
        }

        wrapperArray = rectList.toArray(new RectWrapper[rectList.size()]);

        for (int i = 0; i < wrapperArray.length ; i++) {
            RectWrapper temp = wrapperArray[i];
            Rect rect = temp.rect;
            Core.rectangle(img1, temp.rect.tl(), temp.rect.br(), new Scalar(255, 0, 0),4, 8,0);
            Core.putText(img1, String.valueOf(i) ,new Point(rect.x+2, rect.y+15), Core.FONT_HERSHEY_COMPLEX_SMALL, 0.8, new Scalar(255, 0, 0));
        }

        if (wrapperArray.length == 9 && loops > 5) {
            System.out.println("nueve!");
            thresholdColors();
            done = true;
        }
    }


    public void step() {
        if (camera.read(img1)) {
            if (!done && start) {
                harrisFirst();
            }
            if (!start) {
                updateImages();
            }
            if (done) {
                ordered = orderRects(rectList.toArray(new RectWrapper[rectList.size()]));
            }
        }
    }

    public void updateImages() {
        grayImage = img1.clone();
        harrisImage = img1.clone();
        cannyImage = img1.clone();
        Imgproc.cvtColor(img1, grayImage, Imgproc.COLOR_BGR2HSV);
        redImage = grayImage.clone();
        secondredImage = grayImage.clone();
        yellowImage = grayImage.clone();
        blueImage = grayImage.clone();
        orangeImage = grayImage.clone();
        greenImage = grayImage.clone();
        whiteImage = grayImage.clone();
        thresholdHSV(grayImage);
    }

    public void clearRectList() {
        rectList.clear();
    }

    public void thresholdHSV(Mat img) {
        Scalar max = new Scalar(180,255,255);
        Scalar min = new Scalar(0, 30, 50);
        Core.inRange(img, min, max, img);
        max = new Scalar(255,255,255);
        min = new Scalar(0, 200, 200);
        Imgproc.threshold(img, img,127,255,0);
    }

    public void thresholdColors() {
        Scalar max = new Scalar(34, 255, 255);
        Scalar min = new Scalar(22, 100, 100);
        Core.inRange(yellowImage, min, max, yellowImage);
        Imgproc.threshold(yellowImage, yellowImage,127,255,0);
        max = new Scalar(7, 255, 255);
        min = new Scalar(0, 50, 60);
        Core.inRange(redImage, min, max, redImage);
        Imgproc.threshold(redImage, redImage,127,255,0);
        max = new Scalar(180, 255, 255);
        min = new Scalar(169, 50, 60);
        Core.inRange(secondredImage, min, max, secondredImage);
        Imgproc.threshold(secondredImage, secondredImage,127,255,0);
        max = new Scalar(74, 255, 255);
        min = new Scalar(40, 50, 60);
        Core.inRange(greenImage, min, max, greenImage);
        Imgproc.threshold(greenImage, greenImage,127,255,0);
        max = new Scalar(21, 255, 255);
        min = new Scalar(8, 50, 60);
        Core.inRange(orangeImage, min, max, orangeImage);
        Imgproc.threshold(orangeImage, orangeImage,127,255,0);
        max = new Scalar(127, 255, 255);
        min = new Scalar(76, 50, 60);
        Core.inRange(blueImage, min, max, blueImage);
        Imgproc.threshold(blueImage, blueImage,127,255,0);
        max = new Scalar(180, 80, 255);
        min = new Scalar(0, 0, 110);
        Core.inRange(whiteImage, min, max, whiteImage);
        Imgproc.threshold(whiteImage, whiteImage,127,255,0);
    }

    public int detectColor(Rect rect) {
        int num = 0;
        int sumVal = 0;
        int val = 0;
        
        System.out.println("rect x: " + rect.x + " y:" + rect.y + " width:" + rect.width);
        
        for (int i = rect.x+(rect.width/3); i < rect.x+((rect.width/3)*2) ; i++ ) {
            for (int j = rect.y+(rect.width/3); j < rect.y+((rect.width/3)*2) ; j++ ) {
                sumVal += (int)yellowImage.get(j,i)[0];
                num++;
            }
        }
        val = sumVal/num;
        System.out.println("val amarillo " + val);
        if ( val > 120) {
            System.out.println("amarillo!");
            return 3;
        }

        val = 0;
        sumVal = 0;
        num = 0;
        int otherVal = 0;
        for (int i = rect.x+(rect.width/3); i < rect.x+((rect.width/3)*2) ; i++ ) {
            for (int j = rect.y+(rect.width/3); j < rect.y+((rect.width/3)*2) ; j++ ) {
                sumVal += (int)redImage.get(j,i)[0];
                otherVal += (int)secondredImage.get(j,i)[0];
                num++;
            }
        }
        val = sumVal/num;
        otherVal = otherVal/num;
        System.out.println("val rojo " + val);
        if ( val > 120 || otherVal > 120) {
            System.out.println("rojo!");
            return 4;
        }

        val = 0;
        sumVal = 0;
        num = 0;
        for (int i = rect.x+(rect.width/3); i < rect.x+((rect.width/3)*2) ; i++ ) {
            for (int j = rect.y+(rect.width/3); j < rect.y+((rect.width/3)*2) ; j++ ) {
                sumVal += (int)greenImage.get(j,i)[0];
                num++;
            }
        }
        val = sumVal/num;
        System.out.println("val verde " + val);
        if ( val > 120 ) {
            System.out.println("verde!");
            return 2;
        }

        val = 0;
        sumVal = 0;
        num = 0;
        for (int i = rect.x+(rect.width/3); i < rect.x+((rect.width/3)*2) ; i++ ) {
            for (int j = rect.y+(rect.width/3); j < rect.y+((rect.width/3)*2) ; j++ ) {
                sumVal += (int)orangeImage.get(j,i)[0];
                num++;
            }
        }
        val = sumVal/num;
        System.out.println("val naranja " + val);
        if ( val > 120 ) {
            System.out.println("naranja!");
            return 1;
        }

        val = 0;
        sumVal = 0;
        num = 0;
        for (int i = rect.x+(rect.width/3); i < rect.x+((rect.width/3)*2) ; i++ ) {
            for (int j = rect.y+(rect.width/3); j < rect.y+((rect.width/3)*2) ; j++ ) {
                sumVal += (int)blueImage.get(j,i)[0];
                num++;
            }
        }
        val = sumVal/num;
        System.out.println("val azul " + val);
        if ( val > 120 ) {
            System.out.println("azul!");
            return 5;
        }

        val = 0;
        sumVal = 0;
        num = 0;
        for (int i = rect.x+(rect.width/3); i < rect.x+((rect.width/3)*2) ; i++ ) {
            for (int j = rect.y+(rect.width/3); j < rect.y+((rect.width/3)*2) ; j++ ) {
                sumVal += (int)whiteImage.get(j,i)[0];
                num++;
            }
        }
        val = sumVal/num;
        System.out.println("val blanco " + val);
        if ( val > 120 ) {
            System.out.println("blanco!");
            return 0;
        }
        return 0;
    }

    public RectWrapper[] orderRects(RectWrapper[] rectArray) {
        RectWrapper min = rectArray[0];
        int index = 0;
        for (int i = 0; i < rectArray.length ; i++) {
            RectWrapper temp = rectArray[i];
            if (temp.rect.x < min.rect.x+10) {
                if (temp.rect.y < min.rect.y+10) {
                    min = temp;
                    index = i;
                }
            }
        }
        rectArray[index] = rectArray[0];
        rectArray[0] = min;

        for (int i = 1; i < 3 ; i++) {
            for (int j = i; j < rectArray.length ; j++ ) {
                RectWrapper temp = rectArray[j];
                if (temp.rect.x < min.rect.x+(min.rect.width/3) && temp.rect.x > min.rect.x-(min.rect.width/3)) {
                    if (temp.rect.y < min.rect.y+(min.rect.width*i)+(min.rect.width/3) && temp.rect.y > min.rect.y+(min.rect.width*i)-(min.rect.width/3)) {
                        rectArray[j] = rectArray[i];
                        rectArray[i] = temp;
                    }
                }
            }
        }

        int newX = min.rect.x + min.rect.width;
        for (int i = 3; i < 6 ; i++) {
            for (int j = i; j < rectArray.length ; j++ ) {
                RectWrapper temp = rectArray[j];
                if (temp.rect.x < newX+(min.rect.width/3) && temp.rect.x > newX-(min.rect.width/3)) {
                    if (temp.rect.y < min.rect.y+(min.rect.width*(i-3))+(min.rect.width/3) && temp.rect.y > min.rect.y+(min.rect.width*(i-3))-(min.rect.width/3)) {
                        rectArray[j] = rectArray[i];
                        rectArray[i] = temp;
                    }
                }
            }
        }

        for (int i = 0; i < rectArray.length ; i++) {
            Rect rect = rectArray[i].rect;
            System.out.println("primera rect x: " + rect.x + " y:" + rect.y + " width:" + rect.width);
        }

        newX = min.rect.x + min.rect.width*2;
        for (int i = 6; i < 9 ; i++) {
            for (int j = i; j < rectArray.length ; j++ ) {
                RectWrapper temp = rectArray[j];
                if (temp.rect.x < newX+(min.rect.width/3) && temp.rect.x > newX-(min.rect.width/3)) {
                    if (temp.rect.y < min.rect.y+(min.rect.width*(i-6))+(min.rect.width/3) && temp.rect.y > min.rect.y+(min.rect.width*(i-6))-(min.rect.width/3)) {
                        rectArray[j] = rectArray[i];
                        rectArray[i] = temp;
                    }
                }
            }
        }

        for (int i = 0; i < rectArray.length ; i++) {
            Rect rect = rectArray[i].rect;
            System.out.println("rect x: " + rect.x + " y:" + rect.y + " width:" + rect.width);
        }

        return rectArray;
    }

    public int[] getColors() {
        int[] aux = new int[9];
        for (int i = 0; i < ordered.length ; i++) {
            aux[i] = detectColor(ordered[i].rect);
        }
        return aux;
    }

    public static BufferedImage Mat2BufferedImage(Mat m){
    // source: http://answers.opencv.org/question/10344/opencv-java-load-image-to-gui/
    // Fastest code
    // The output can be assigned either to a BufferedImage or to an Image

        int type = BufferedImage.TYPE_BYTE_GRAY;
        if ( m.channels() > 1 ) {
            type = BufferedImage.TYPE_3BYTE_BGR;
        }
        int bufferSize = m.channels()*m.cols()*m.rows();
        byte [] b = new byte[bufferSize];
        m.get(0,0,b); // get all the pixels
        BufferedImage image = new BufferedImage(m.cols(),m.rows(), type);
        final byte[] targetPixels = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
        System.arraycopy(b, 0, targetPixels, 0, b.length);  
        return image;

    }

}