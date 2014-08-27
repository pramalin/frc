package frc

import java.io.ByteArrayInputStream
import org.opencv.core.Core
import org.opencv.core.Mat
import org.opencv.core.MatOfByte
import org.opencv.highgui.Highgui
import org.opencv.highgui.VideoCapture
import javafx.application.Application
import javafx.beans.property.SimpleObjectProperty
import javafx.concurrent.Service
import javafx.concurrent.Task
import javafx.concurrent.WorkerStateEvent
import javafx.event.EventHandler
import javafx.scene.Scene
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import javafx.scene.layout.StackPane
import javafx.scene.paint.Color
import javafx.scene.shape.Rectangle
import javafx.stage.Stage

object color_detector {
  def main(args: Array[String]): Unit = {
    Application.launch(classOf[color_detector], args: _*)
  }
}

trait OpenCVUtilsJ {
  def mat2Image(mat: Mat): Image = {
    val memory = new MatOfByte
    Highgui.imencode(".png", mat, memory)
    new Image(new ByteArrayInputStream(memory.toArray()))
  }
}

trait ImageSourceJ {
  def videoCapture: VideoCapture
  def takeImage: Mat = {
    val image = new Mat()
    while (videoCapture.read(image) == false) {
      Thread.sleep(1)
      println("waiting for camera ...")
    }
    image
  }

  def sourceMat: Either[Exception, Mat] = {
    assert(videoCapture.isOpened())
    if (videoCapture.grab) {
      Right(takeImage)
    } else {
      Left(new RuntimeException("Couldn't grab image!"))
    }
  }
}

class color_detector extends Application with OpenCVUtilsJ {
  override def init() {
    System.loadLibrary(Core.NATIVE_LIBRARY_NAME)
  }

  class WebcamService extends Service[Either[Exception, Mat]] with OpenCVUtilsJ with ImageSourceJ {
    val videoCapture: VideoCapture = new VideoCapture(0)
    def createTask(): Task[Either[Exception, Mat]] = {
      new Task[Either[Exception, Mat]] {
        override def call(): Either[Exception, Mat] = sourceMat
      }
    }
  }

  val imageProperty = new SimpleObjectProperty[Image]()
  def setImage(image: Image) = imageProperty.set(image)
  def getImage(): Image = imageProperty.get
  val imageView = new ImageView()
  imageView.imageProperty().bind(imageProperty)

  val MaxWidth = 1024
  val MaxHeight = 720

  override def start(stage: Stage): Unit = {
    stage.setTitle("Color Detector")
    val imageService = new WebcamService

    imageService.setOnSucceeded(new EventHandler[WorkerStateEvent] {
      override def handle(event: WorkerStateEvent) = {
        event.getSource().getValue match {
          case Left(e: RuntimeException) => println(e.getMessage)
          case Right(mat: Mat) => {
            setImage(mat2Image(mat))
            imageService.restart
          }
        }
      }
    })
    imageService.start

    val stack = new StackPane()
    val rect = new Rectangle()
    rect.setWidth(200)
    rect.setHeight(70)
    rect.setStrokeWidth(3)
    rect.setFill(null)
    rect.setStroke(Color.BURLYWOOD)

    stack.getChildren().addAll(imageView, rect)
    val scene = new Scene(stack, 1280, 920)
    stage.setScene(scene)
    stage.show()
  }
}
