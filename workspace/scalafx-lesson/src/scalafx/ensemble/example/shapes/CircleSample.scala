/*
 * Copyright 2013 ScalaFX Project
 * All right reserved.
 */
package scalafx.ensemble.example.shapes

import scalafx.application.JFXApp
import scalafx.scene.Scene
import scalafx.geometry.Insets
import scalafx.scene.layout.HBox
import scalafx.scene.paint.Color
import scalafx.scene.shape.Circle

object CircleSample extends JFXApp {

  stage = new JFXApp.PrimaryStage {
    title = "Circle Example"
    scene = new Scene {
      root = {
    
        val circle1 = new Circle {
          centerX = 200
          centerY = 200
          radius = 50
          stroke = Color.BLACK
          strokeWidth = 3
          strokeDashArray ++= Seq(15d, 10d)
          fill = null
        }
    
        val circle2 = new Circle {
          centerX = 200
          centerY = 200
          radius = 50
          stroke = Color.BROWN
          strokeWidth = 2
          fill = Color.DARKKHAKI
        }
    
        new HBox {
          spacing = 25
          padding = Insets(20)
          content = List(circle1, circle2)
        }
      }
    }
  }
}