package nstv.spanyourworld.span;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.text.Layout;
import android.text.Spanned;
import android.text.style.BulletSpan;

/**
 * Created by Nicole Terc on 6/5/18.
 */
public class SuperBulletSpan extends BulletSpan {
    private static final int BULLET_RADIUS = 10;
    private static Path bulletPath = null;

    private final int gapWidth;
    private final boolean wantColor;
    private final int color;

    public SuperBulletSpan(int gapWidth, int color) {
        super(gapWidth, color);
        this.gapWidth = gapWidth;
        wantColor = true;
        this.color = color;
    }

    public int getLeadingMargin(boolean first) {
        return 2 * BULLET_RADIUS + gapWidth;
    }

    @Override
    public void drawLeadingMargin(Canvas c, Paint p, int x, int dir,
                                  int top, int baseline, int bottom,
                                  CharSequence text, int start, int end,
                                  boolean first, Layout l) {

        if (((Spanned) text).getSpanStart(this) == start) {
            Paint.Style style = p.getStyle();
            int oldcolor = 0;

            if (wantColor) {
                oldcolor = p.getColor();
                p.setColor(color);
            }

            p.setStyle(Paint.Style.FILL);

            if (c.isHardwareAccelerated()) {
                if (bulletPath == null) {
                    bulletPath = new Path();
                    bulletPath.addCircle(0.0f, 0.0f, 1.2f * BULLET_RADIUS, Path.Direction.CW);
                }

                c.save();
                c.translate(x + 1.2f * dir * BULLET_RADIUS, (top + bottom) / 2.0f);
                c.drawPath(bulletPath, p);
                c.restore();
            } else {
                c.drawCircle(x + 1.2f * dir * BULLET_RADIUS, (top + bottom) / 2.0f, BULLET_RADIUS, p);
            }

            if (wantColor) {
                p.setColor(oldcolor);
            }

            p.setStyle(style);
        }
    }
}
