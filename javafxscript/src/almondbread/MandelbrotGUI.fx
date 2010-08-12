package almondbread;

import javafx.scene.Scene;
import java.awt.image.BufferedImage;
import javafx.scene.image.ImageView;
import javafx.ext.swing.SwingUtils;

def width = 640;
def height = 480;

def image = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_BINARY);

Mandelbrot.eachPoint(width, height, function (x: Integer, y: Integer, v: Integer) {
        image.setRGB(x, y, v.byteValue());
    });

Scene {
    content: ImageView {
        image: SwingUtils.toFXImage(image)
    }
}
