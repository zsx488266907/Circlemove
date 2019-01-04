package zsx.com.circle;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.widget.ImageView;

@SuppressLint("AppCompatCustomView")
public class Circleview extends ImageView {
    public Circleview(Context context) {
        super(context);
    }

    public Circleview(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public Circleview(Context context,  AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

   /* public Circleview(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }*/

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        BitmapDrawable bitmapDrawable = (BitmapDrawable) getDrawable();
        Bitmap bitmap = bitmapDrawable.getBitmap();
            if (bitmap!=null){
             Bitmap bit= getRound(bitmap);
             canvas.drawBitmap(bit,0,0,null);
            }
    }
    private Bitmap getRound(Bitmap bitmap){
        //宽高缩放比
        float widthScale = getMeasuredWidth() / bitmap.getWidth();
        float heightScale= getMeasuredHeight()/bitmap.getHeight();
        //矩阵变换类
        Matrix matrix = new Matrix();
        matrix.setScale(widthScale,heightScale);
        //对bitmap进行变换
        Bitmap bitmap1 = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        //最终输出的对象
        Bitmap bitmap2 = Bitmap.createBitmap(getMeasuredWidth(), getMeasuredHeight(), Bitmap.Config.RGB_565);
// 创建画布
        Canvas canvas = new Canvas(bitmap2);
   //创建画笔
        Paint paint = new Paint();
//个圆角的图形
        RectF rectF = new RectF(0, 0, getMeasuredWidth(), getMeasuredHeight());

        canvas.drawRoundRect(rectF,30,30,paint);
        //设置画笔的xfermode模式
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));

        canvas.drawBitmap(bitmap1,0,0,paint);

        return bitmap2;
    }
}
