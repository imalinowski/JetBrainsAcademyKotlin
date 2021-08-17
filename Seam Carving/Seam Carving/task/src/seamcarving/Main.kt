package seamcarving

import java.awt.Color
import java.awt.Image
import java.awt.image.BufferedImage
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.File
import javax.imageio.ImageIO


fun main() {
    println("Enter rectangle width:")
    val width = readLine()!!.toInt()
    println("Enter rectangle height:")
    val height = readLine()!!.toInt()
    println("Enter image name:")
    val name = readLine()!!

    val bImage  = BufferedImage(width, height, BufferedImage.TYPE_INT_RGB)
    val g = bImage.graphics

    g.color = Color.BLACK
    g.drawRect(0,0,width,height)

    g.color = Color.RED
    g.drawLine(0,0,width - 1,height - 1)
    g.drawLine(width - 1,0,0,height - 1)

    ImageIO.write(bImage, "png", File("$name"))
}
