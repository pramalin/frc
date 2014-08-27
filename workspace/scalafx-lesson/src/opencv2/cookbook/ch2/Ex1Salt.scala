package opencv2.cookbook.ch2
import util.Random
import scalafx.application.JFXApp
import org.opencv.core.Core
import org.opencv.highgui.Highgui
import org.opencv.core.MatOfByte
import org.opencv.core.Mat
import scalafx.application.JFXApp.PrimaryStage
import scalafx.scene.image.Image
import java.io.ByteArrayInputStream
import scalafx.scene.Scene
import scalafx.scene.image.ImageView

/**
 * Set individual, randomly selected, pixels to a fixed value.
 *
 * Illustrates access to pixel values using absolute indexing.
 */
object Ex1Salt extends JFXApp {
  System.loadLibrary(Core.NATIVE_LIBRARY_NAME)

  def mat2Image(mat: Mat): Image = {
    val memory = new MatOfByte
    Highgui.imencode(".jpg", mat, memory)
    new Image(new ByteArrayInputStream(memory.toArray()))
  }

    
  def salt(image: Mat, n: Int): Mat = {

    // Place 'n' white spots at random locations
    val nbChannels = image.channels
    val random = new Random
    for (i <- 1 to n) {
      // Create random index of a pixel
      val col = random.nextInt(image.cols) * nbChannels
      val row = random.nextInt(image.rows)
      // Set it to white by setting each of the channels to max (255)
      image.put(row, col, 255, 255, 255)
    }

    image
  }

  // load image
  val imgPath = getClass().getResource("/boldt.jpg").getPath().tail
  val boldt = Highgui.imread(imgPath)
  
  // Add salt noise
  val dest = salt(boldt, 2000)
  
  // display
  stage = new PrimaryStage {
    title = "Salt"
    scene = new Scene() {
      content = List(new ImageView(image = mat2Image(dest)))
    }
  }
}