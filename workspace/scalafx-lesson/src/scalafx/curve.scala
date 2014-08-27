package scalafx

import scalafx.application.JFXApp
import scalafx.geometry.Insets
import scalafx.scene.Scene
import scalafx.scene.layout.HBox
import scalafx.scene.paint.Color
import scalafx.scene.shape.CubicCurve

object CircleSample extends JFXApp {

  stage = new JFXApp.PrimaryStage {
    title = "Circle Example"
    scene = new Scene {
      root = {

        val cubic = new CubicCurve {
          startX = 0.0f
          startY = 50.0f
          controlX1 = 25.0f
          controlY1 = 0.0f
          controlX2 = 75.0f
          controlY2 = 100.0f
          endX = 100.0f
          endY = 50.0f
        
          strokeWidth = 2 
          stroke = Color.Black
          fill = Color.White
          
        }
        new HBox {
          spacing = 25
          padding = Insets(20)
          content = List(cubic)
        }
      }
    }
  }
}