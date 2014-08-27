package lesson
import boofcv.io.image.UtilImageIO
import boofcv.core.image.ConvertBufferedImage
import boofcv.struct.image.ImageFloat32
import boofcv.struct.image.ImageUInt8
import boofcv.alg.misc.ImageStatistics
import boofcv.alg.filter.binary.ThresholdImageOps
import java.awt.image.BufferedImage
import boofcv.gui.image.ShowImages
import boofcv.alg.filter.binary.BinaryImageOps
import boofcv.struct.ConnectRule
import boofcv.gui.binary.VisualizeBinaryData
import boofcv.struct.image.ImageSInt32

object binOp {

  def main(args: Array[String]): Unit = {
    
    val image = UtilImageIO.loadImage("c:/home/source/BoofCV-0.17/data/applet/particles01.jpg")

		// convert into a usable format
		val input = ConvertBufferedImage.convertFromSingle(image, null, classOf[ImageFloat32])
		
		val binary = new ImageUInt8(input.width,input.height)
    	val label = new ImageSInt32(input.width,input.height)

		// the mean pixel value is often a reasonable threshold when creating a binary image
		val mean = ImageStatistics.mean(input)

		// create a binary image by thresholding
		ThresholdImageOps.threshold(input,binary,mean.toFloat,true)

		// remove small blobs through erosion and dilation
		// The null in the input indicates that it should internally declare the work image it needs
		// this is less efficient, but easier to code.
		var filtered = BinaryImageOps.erode8(binary, 1, null)
		filtered = BinaryImageOps.dilate8(filtered, 1, null)

		// Detect blobs inside the image using an 8-connect rule
		val contours = BinaryImageOps.contour(filtered, ConnectRule.EIGHT, label)

		// colors of contours
		val colorExternal = 0xFFFFFF
		val colorInternal = 0xFF2020

		// display the results
		val visualBinary = VisualizeBinaryData.renderBinary(binary, null)
		val visualFiltered = VisualizeBinaryData.renderBinary(filtered, null)
		val visualLabel = VisualizeBinaryData.renderLabeledBG(label, contours.size(), null)
		val visualContour = VisualizeBinaryData.renderContours(contours,colorExternal,colorInternal,
				input.width,input.height,null)

		ShowImages.showWindow(visualBinary,"Binary Original")
		ShowImages.showWindow(visualFiltered,"Binary Filtered")
		ShowImages.showWindow(visualLabel,"Labeled Blobs")
		ShowImages.showWindow(visualContour,"Contours")
  }
}