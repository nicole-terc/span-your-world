package nstv.spanyourworld.span

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.text.style.ReplacementSpan

/**
 * Created by Nicole Terc on 6/5/18.
 */
private const val BORDER_PAINT_STROKE_WIDTH = 3f

class SuperSpan(var color: Int, var padding: Int) : ReplacementSpan() {

    override fun getSize(paint: Paint, text: CharSequence, start: Int, end: Int, fm: Paint.FontMetricsInt?): Int {
        val textWidth = paint.measureText(text, start, end).toInt()
        return textWidth.plus(padding * 2)
    }

    override fun draw(canvas: Canvas, text: CharSequence, start: Int, end: Int, x: Float, top: Int, y: Int, bottom: Int, paint: Paint) {
        canvas.save()

        val textBounds = Rect()
        paint.getTextBounds(text.toString(), start, end, textBounds)

        val borderTop = (y - textBounds.height() - padding).toFloat()
        val borderLeft = x
        val borderRight = x + textBounds.width() + 2 * padding
        val borderBottom = (y + padding).toFloat()
        canvas.drawRect(borderLeft, borderTop, borderRight, borderBottom, getBorderPaint())

        canvas.drawText(text, start, end, x + padding.toFloat(), y.toFloat(), paint)

        canvas.restore()
    }

    private fun getBorderPaint(): Paint {
        val paintBorder = Paint()
        paintBorder.style = Paint.Style.STROKE
        paintBorder.strokeWidth = BORDER_PAINT_STROKE_WIDTH
        paintBorder.isAntiAlias = true
        paintBorder.color = color
        return paintBorder
    }

}