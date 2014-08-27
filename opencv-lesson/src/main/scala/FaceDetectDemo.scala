import org.opencv.objdetect.CascadeClassifier
import org.opencv.highgui.Highgui
import org.opencv.core.MatOfRect
import org.opencv.core.Point
import org.opencv.core.Core
import org.opencv.core.Scalar

object FaceDetectDemo extends App {
    System.loadLibrary(Core.NATIVE_LIBRARY_NAME)
    
    val faceCfg = getClass().getResource("/lbpcascade_frontalface.xml").getPath().tail
    val faceDetector = new CascadeClassifier(faceCfg)
    val imgPath = getClass().getResource("/ec.png").getPath().tail
    var image = Highgui.imread(imgPath)

    val faceDetections = new MatOfRect
    faceDetector.detectMultiScale(image, faceDetections)

    println(s"Detected ${faceDetections.toArray().length} faces")

    // Draw a bounding box around each face.
    for (rect <- faceDetections.toArray) {
      Core.rectangle(image,
          new Point(rect.x, rect.y),
          new Point(rect.x + rect.width, rect.y + rect.height),
          new Scalar(0, 255, 0))
    }

    // Save the visualized detection.
    val filename = "faceDetection-ec.png"
    println(s"Writing $filename")
    Highgui.imwrite(filename, image)
}