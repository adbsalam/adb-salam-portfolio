package uk.adbsalam.portfolio.shared.ui.design

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.asSkiaPath
import androidx.compose.ui.graphics.isSpecified
import androidx.compose.ui.graphics.toComposeImageBitmap
import androidx.compose.ui.graphics.toPixelMap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.VectorGroup
import androidx.compose.ui.graphics.vector.VectorNode
import androidx.compose.ui.graphics.vector.VectorPath
import androidx.compose.ui.graphics.vector.toPath
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.convert
import kotlinx.cinterop.refTo
import org.jetbrains.skia.Surface
import platform.CoreGraphics.CGBitmapContextCreate
import platform.CoreGraphics.CGBitmapContextCreateImage
import platform.CoreGraphics.CGColorSpaceCreateDeviceRGB
import platform.CoreGraphics.CGColorSpaceRelease
import platform.CoreGraphics.CGContextRelease
import platform.CoreGraphics.CGImageAlphaInfo
import platform.CoreGraphics.CGImageRelease
import platform.UIKit.UIColor
import platform.UIKit.UIImage
import platform.UIKit.UIImageOrientation
import platform.UIKit.UITabBar
import platform.UIKit.UITabBarDelegateProtocol
import platform.UIKit.UITabBarItem
import platform.darwin.NSObject

data class UIKitTabBarConfiguration(
    val selectedItemColor: Color = Color.Unspecified,
    val unselectedItemColor: Color = Color.Unspecified,
    val isTranslucent: Boolean = true,
)

data class UIKitUITabBarItem(
    val title: String,
    val image: UIKitImage? = null,
    val selectedImage: UIKitImage? = null,
)

/**
 * Represents an image that can be converted to a UIImage on iOS.
 *
 * Supports different image types:
 * - [SystemName]: An iOS SF Symbol system image name.
 * - [Vector]: A Compose [ImageVector].
 * - [Bitmap]: A Compose [ImageBitmap].
 */
@Immutable
sealed interface UIKitImage {
    /**
     * An iOS SF Symbol system image name.
     *
     * @param name The SF Symbol name (e.g., "house.fill", "heart.fill").
     */
    data class SystemName(
        val name: String,
    ) : UIKitImage

    /**
     * A Compose [ImageVector].
     *
     * On iOS, this will be converted to a UIImage using rasterization.
     *
     * @param imageVector The Compose [ImageVector] to use.
     * @param width The width in dp for rasterization on iOS. Default is 24.dp.
     * @param height The height in dp for rasterization on iOS. Default is 24.dp.
     */
    data class Vector(
        val imageVector: ImageVector,
        val width: Dp = 24.dp,
        val height: Dp = 24.dp,
    ) : UIKitImage

    /**
     * A Compose [ImageBitmap].
     *
     * On iOS, this will be converted to a UIImage.
     *
     * @param imageBitmap The Compose [ImageBitmap] to use.
     */
    data class Bitmap(
        val imageBitmap: ImageBitmap,
    ) : UIKitImage
}

/**
 * A helper class that manages a native iOS UITabBar, handling item setup and selection.
 *
 * @param tabBar The native UITabBar instance to manage.
 * @param onItemSelected Callback invoked when a tab bar item is selected, with the item index.
 */
@OptIn(ExperimentalForeignApi::class)
internal class TabBarManager(
    private val tabBar: UITabBar,
    onItemSelected: (Int) -> Unit,
) {
    private val uiTabBarDelegate =
        UITabBarDelegate(
            onItemSelected = onItemSelected,
        )

    // Capture the original tint colors so we can restore iOS defaults when the user
    // switches back to Color.Unspecified.
    private val defaultTintColor: UIColor = tabBar.tintColor
    private val defaultUnselectedItemTintColor: UIColor? = tabBar.unselectedItemTintColor

    private var currentItems = emptyList<UIKitUITabBarItem>()
    private var currentConfiguration = UIKitTabBarConfiguration()

    init {
        tabBar.delegate = uiTabBarDelegate
    }

    fun setItems(
        items: List<UIKitUITabBarItem>,
        selectedIndex: Int,
        density: Float,
    ) {
        if (items.isEmpty()) return

        if (items != currentItems) {
            val uiKitItems =
                items.mapIndexed { index, item ->
                    item.toUITabBarItem(index.toLong(), density)
                }
            tabBar.setItems(uiKitItems)
            currentItems = items
            val safeSelectedIndex = selectedIndex.coerceIn(uiKitItems.indices)
            tabBar.selectedItem = uiKitItems[safeSelectedIndex]
        } else {
            val uiKitItems = tabBar.items?.filterIsInstance<UITabBarItem>() ?: return
            val safeSelectedIndex = selectedIndex.coerceIn(uiKitItems.indices)
            tabBar.selectedItem = uiKitItems[safeSelectedIndex]
        }
    }

    fun updateConfiguration(configuration: UIKitTabBarConfiguration) {
        if (configuration == currentConfiguration) return

        tabBar.translucent = configuration.isTranslucent

        // Selected item tint — UITabBar.tintColor controls the selected icon+label color
        tabBar.tintColor =
            if (configuration.selectedItemColor.isSpecified) {
                configuration.selectedItemColor.toUIColor()
            } else {
                defaultTintColor
            }

        // Unselected item color works on pre-Liquid Glass iOS via unselectedItemTintColor.
        // Note: Liquid Glass (iOS 26+) ignores this property; not yet supported.
        tabBar.unselectedItemTintColor =
            if (configuration.unselectedItemColor.isSpecified) {
                configuration.unselectedItemColor.toUIColor()
            } else {
                defaultUnselectedItemTintColor
            }

        currentConfiguration = configuration
    }
}

private fun UIKitUITabBarItem.toUITabBarItem(
    tag: Long,
    density: Float,
): UITabBarItem {
    val item =
        UITabBarItem(
            title = title,
            image = image?.toUIImage(density),
            tag = tag,
        )
    selectedImage?.toUIImage(density)?.let { item.selectedImage = it }
    return item
}

internal class UITabBarDelegate(
    private val onItemSelected: (Int) -> Unit,
) : NSObject(),
    UITabBarDelegateProtocol {
    override fun tabBar(
        tabBar: UITabBar,
        didSelectItem: UITabBarItem,
    ) {
        tabBar.selectedItem = didSelectItem
        onItemSelected(didSelectItem.tag.toInt())
    }
}

/**
 * Converts a [UIKitImage] to a native iOS [UIImage].
 *
 * @param density The screen density (pixels per dp) used for rasterization of vector images.
 * @return The converted [UIImage], or null if the conversion fails.
 */
internal fun UIKitImage.toUIImage(density: Float): UIImage? =
    when (this) {
        is UIKitImage.SystemName -> UIImage.systemImageNamed(name)
        is UIKitImage.Vector -> imageVector.toUIImage(width, height, density)
        is UIKitImage.Bitmap -> imageBitmap.toUIImage()
    }

private fun ImageVector.toUIImage(
    width: Dp,
    height: Dp,
    density: Float,
): UIImage? {
    val pixelWidth = (width.value * density).toInt()
    val pixelHeight = (height.value * density).toInt()
    return this.toImageBitmap(pixelWidth, pixelHeight).toUIImage(scale = density.toDouble())
}

private fun ImageVector.toImageBitmap(
    width: Int,
    height: Int,
): ImageBitmap {
    val surface = Surface.makeRasterN32Premul(width, height)
    val canvas = surface.canvas

    canvas.clear(org.jetbrains.skia.Color.TRANSPARENT)

    // Scale from viewport coordinates to pixel dimensions
    val scaleX = width.toFloat() / viewportWidth
    val scaleY = height.toFloat() / viewportHeight
    canvas.scale(scaleX, scaleY)

    with(canvas) {
        val paint =
            org.jetbrains.skia.Paint().apply {
                color = org.jetbrains.skia.Color.BLACK
            }
        drawPath(toPath().asSkiaPath(), paint)
    }

    return surface.makeImageSnapshot().toComposeImageBitmap()
}

@OptIn(ExperimentalForeignApi::class)
internal fun ImageBitmap.toUIImage(scale: Double = 1.0): UIImage? {
    val pixelMap = toPixelMap()
    val width = pixelMap.width
    val height = pixelMap.height

    val colorSpace = CGColorSpaceCreateDeviceRGB()
    val data = pixelMap.buffer.toUIntArray()

    val rowBytes = 4 * width

    val context =
        CGBitmapContextCreate(
            data = data.refTo(0),
            width = width.convert(),
            height = height.convert(),
            bitsPerComponent = 8.convert(),
            bytesPerRow = rowBytes.convert(),
            space = colorSpace,
            bitmapInfo = CGImageAlphaInfo.kCGImageAlphaPremultipliedLast.value,
        )

    val cgImage = CGBitmapContextCreateImage(context) ?: return null

    @Suppress("DEPRECATION")
    val orientation = UIImageOrientation.UIImageOrientationUp
    val image = UIImage(cGImage = cgImage, scale = scale, orientation = orientation)

    CGContextRelease(context)
    CGColorSpaceRelease(colorSpace)
    CGImageRelease(cgImage)

    return image
}

private fun traverseNodes(
    node: VectorNode,
    path: Path,
) {
    when (node) {
        is VectorGroup -> {
            node.iterator().forEach { childNode ->
                traverseNodes(childNode, path)
            }
        }
        is VectorPath -> {
            path.addPath(node.pathData.toPath())
        }
    }
}

private fun ImageVector.toPath(): Path =
    Path().apply {
        traverseNodes(root, this)
    }
