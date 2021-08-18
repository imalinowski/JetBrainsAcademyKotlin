package seamcarving

import java.awt.Color
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO
import kotlin.math.*

fun main(args : Array<String>) {

    val nameIn = args[1]
    val nameOut = args[3]

    val image = ImageIO.read(File(nameIn))

    val bImage = transform(image)

    ImageIO.write(bImage, "png", File(nameOut))
}

fun transform(image:BufferedImage) : BufferedImage{
    val bImage = BufferedImage(image.width, image.height,BufferedImage.TYPE_INT_RGB)
    val (array, eMax) = calcEnergy(image)

    for (y in array.indices){
        for (x in array[y].indices){
            val intensity = (255.0 * array[y][x] / eMax).toInt()
            bImage.setRGB(x,y,Color(intensity,intensity,intensity).rgb)
        }
    }
    return bImage
}

fun calcEnergy(image:BufferedImage) : Pair <Array<IntArray>,Int>  {
    val array = Array(image.height){ IntArray(image.width) }
    var maxEnergy = 0

    for (x in 0 until image.width)
        for (y in 0 until image.height) {
            val (xp1,xm1) = when {
                    x - 1 < 0 -> x to x + 2
                    x + 1 >= image.width -> x - 2 to x
                    else -> x - 1 to x + 1
                }

            val (yp1,ym1) = when {
                y - 1 < 0 -> y to y + 2
                y + 1 >= image.height -> y - 2 to y
                else -> y - 1 to y + 1
            }

            val Rx = Color(image.getRGB(xp1, y)).red - Color(image.getRGB(xm1, y)).red.toDouble()
            val Gx = Color(image.getRGB(xp1, y)).green - Color(image.getRGB(xm1, y)).green.toDouble()
            val Bx = Color(image.getRGB(xp1, y)).blue - Color(image.getRGB(xm1, y)).blue.toDouble()

            val Ry = Color(image.getRGB(x,yp1)).red - Color(image.getRGB(x,ym1)).red.toDouble()
            val Gy = Color(image.getRGB(x,yp1)).green - Color(image.getRGB(x, ym1)).green.toDouble()
            val By = Color(image.getRGB(x,yp1)).blue - Color(image.getRGB(x, ym1)).blue.toDouble()

            val deltaXSq = Rx.pow(2) + Gx.pow(2) + Bx.pow(2)
            val deltaYSq = Ry.pow(2) + Gy.pow(2) + By.pow(2)
            array[y][x] = sqrt(deltaXSq + deltaYSq).toInt()
            if(array[y][x] > maxEnergy) maxEnergy = array[y][x]
        }

    return array to maxEnergy
}
