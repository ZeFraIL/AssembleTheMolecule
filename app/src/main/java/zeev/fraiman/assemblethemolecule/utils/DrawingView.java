package zeev.fraiman.assemblethemolecule.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class DrawingView extends View {

    private Paint drawPaint, canvasPaint, textPaint;
    private Path drawPath;
    private Canvas drawCanvas;
    private Bitmap canvasBitmap;
    
    private List<ElementText> elementTexts = new ArrayList<>();
    private String currentSelectedElement = null;

    public DrawingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setupDrawing();
    }

    private void setupDrawing() {
        drawPath = new Path();
        drawPaint = new Paint();
        drawPaint.setColor(Color.BLACK);
        drawPaint.setAntiAlias(true);
        drawPaint.setStrokeWidth(5);
        drawPaint.setStyle(Paint.Style.STROKE);
        drawPaint.setStrokeJoin(Paint.Join.ROUND);
        drawPaint.setStrokeCap(Paint.Cap.ROUND);

        canvasPaint = new Paint(Paint.DITHER_FLAG);
        
        textPaint = new Paint();
        textPaint.setColor(Color.BLUE);
        textPaint.setTextSize(60);
        textPaint.setFakeBoldText(true);
        textPaint.setTextAlign(Paint.Align.CENTER);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (w <= 0 || h <= 0) return;
        
        Bitmap newBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        Canvas newCanvas = new Canvas(newBitmap);
        
        // If we had a previous drawing, preserve it
        if (canvasBitmap != null) {
            newCanvas.drawBitmap(canvasBitmap, 0, 0, canvasPaint);
        }
        
        canvasBitmap = newBitmap;
        drawCanvas = newCanvas;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(canvasBitmap, 0, 0, canvasPaint);
        canvas.drawPath(drawPath, drawPaint);
        for (ElementText et : elementTexts) {
            canvas.drawText(et.text, et.x, et.y, textPaint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float touchX = event.getX();
        float touchY = event.getY();

        if (currentSelectedElement != null) {
            if (event.getAction() == MotionEvent.ACTION_UP) {
                elementTexts.add(new ElementText(currentSelectedElement, touchX, touchY));
                currentSelectedElement = null; // Reset after placement
                invalidate();
            }
            return true;
        }

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                drawPath.moveTo(touchX, touchY);
                break;
            case MotionEvent.ACTION_MOVE:
                drawPath.lineTo(touchX, touchY);
                break;
            case MotionEvent.ACTION_UP:
                drawCanvas.drawPath(drawPath, drawPaint);
                drawPath.reset();
                break;
            default:
                return false;
        }
        invalidate();
        return true;
    }

    public void clearCanvas() {
        drawCanvas.drawColor(Color.WHITE, android.graphics.PorterDuff.Mode.CLEAR);
        elementTexts.clear();
        invalidate();
    }

    public void setCurrentElement(String element) {
        this.currentSelectedElement = element;
    }

    public Bitmap getBitmap() {
        Bitmap result = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(result);
        canvas.drawColor(Color.WHITE); // Ensure white background for AI
        draw(canvas);
        return result;
    }

    private static class ElementText {
        String text;
        float x, y;
        ElementText(String text, float x, float y) {
            this.text = text;
            this.x = x;
            this.y = y;
        }
    }
}
