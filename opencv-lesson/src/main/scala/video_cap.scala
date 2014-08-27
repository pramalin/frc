import org.opencv.core.Core
import org.opencv.highgui.VideoCapture
import org.opencv.core.Mat
import org.opencv.highgui.Highgui

object video_cap extends App {
  System.loadLibrary(Core.NATIVE_LIBRARY_NAME)
  def readCamera(continue: Boolean) {
    if (continue) {
      val frame = new Mat
      val cont = camera.read(frame)
      println("Frame Obtained")
      println(s"Captured Frame Width ${frame.width} Height ${frame.height}")
      Highgui.imwrite("scalacam.jpg", frame)
      println("OK")
      readCamera(!cont)
    }
  }

  val camera = new VideoCapture(0)
  if (!camera.isOpened()) println("Error")
  else readCamera(true)
  camera.release()
}