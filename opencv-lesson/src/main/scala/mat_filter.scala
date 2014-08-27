import org.opencv.core.CvType._
import org.opencv.core.Mat
import org.opencv.core.Point
import org.opencv.imgproc.Imgproc._
import org.opencv.highgui.Highgui
import org.opencv.core.Core

object mat_filter extends App {
  System.loadLibrary(Core.NATIVE_LIBRARY_NAME)

  def sharpen(image: Mat): Mat = {
    val kernel = new Mat(3, 3, CV_32F)
    kernel.put(1, 1, 5)
    kernel.put(0, 1, -1)
    kernel.put(2, 1, -1)
    kernel.put(1, 0, -1)
    kernel.put(1, 2, -1)

    val result = new Mat
    result.create(image.size(), image.`type`)
    
    filter2D(image, result, image.depth(), kernel)
    result
  }
  
   val imgPath = getClass().getResource("/lena.png").getPath().tail
   val image = Highgui.imread(imgPath)
   val dest = sharpen(image)
       // Save the visualized detection.
    val filename = "sharpened.png"
    println(s"Writing $filename")
    Highgui.imwrite(filename, dest)
}