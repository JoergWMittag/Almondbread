package almondbread

import scala.swing._
import java.awt.image.BufferedImage

object MandelbrotGUI extends SimpleSwingApplication {
  private val width = 640
  private val height = 480

  private val image = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_BINARY)

  def top = new MainFrame {
    title = "Mandelbrot"
    contents = new Panel {
      override def paint(g: Graphics2D) = {
        super.paint(g)
        g.drawImage(image, 0, 0, null)
      }
      preferredSize = new Dimension(width, height)
    }
  }

  override def startup(args: Array[String]) = {
    super.startup(args)
    Mandelbrot.eachPoint(width, height) { (x, y, v) =>
        image.setRGB(x, y, v.toByte)
    }
  }
}
