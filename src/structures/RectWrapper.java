package structures;

import org.opencv.core.Rect;

public class RectWrapper {

    public Rect rect;
    public int appearances = 0;
    public int time = 0;

    public RectWrapper(Rect rect) {
        this.rect = rect;
    }

    @Override
    public boolean equals(Object obj) {
        return rect.equals(((RectWrapper)obj).rect);
    }

}