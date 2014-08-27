package opencv2.cookbook.ch1

import org.opencv.core.Core
import org.opencv.highgui.Highgui
import scalafx.application.JFXApp
import org.opencv.core.Mat
import scalafx.scene.image.Image
import org.opencv.core.MatOfByte
import java.io.ByteArrayInputStream
import scalafx.application.JFXApp.PrimaryStage
import scalafx.scene.Scene
import scalafx.scene.image.ImageView

object display_image extends JFXApp {
  System.loadLibrary(Core.NATIVE_LIBRARY_NAME)

  def mat2Image(mat: Mat): Image = {
    val memory = new MatOfByte
    Highgui.imencode(".png", mat, memory)
    new Image(new ByteArrayInputStream(memory.toArray()))
  }

  val imgPath = getClass().getResource("/lena.png").getPath().tail

  val matimage = Highgui.imread(imgPath)

  stage = new PrimaryStage {
    title = "Lena"
    scene = new Scene() {
      content = List(new ImageView(image = mat2Image(matimage)))
    }
  }

}