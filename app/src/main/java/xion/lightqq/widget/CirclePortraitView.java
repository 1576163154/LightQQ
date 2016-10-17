package xion.lightqq.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

import xion.lightqq.R;

/**
 * Created by Administrator on 2016/10/13.
 */

public class CirclePortraitView extends ImageView {
    private Paint paint;

    public CirclePortraitView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CirclePortraitView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public CirclePortraitView(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Drawable drawable = getDrawable();//获取 当前 图片drawable对象
        if (null != drawable) {
            Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();//转换为bitmap对象
            Bitmap b = getCircleBitmap(bitmap);

            final Rect rectSrc = new Rect(0, 0, b.getWidth(), b.getHeight());//(这里获取 宽高 都为px)，指明绘制图所需要的区域
            final Rect rectDest = new Rect(0,0,getWidth(),getHeight());//这片区域就是 Circle..view 的大小。指明要将上面的图绘制在哪里
            //这里所设置的坐标是相当与于 该CirclePortraitView的
            paint.reset();
            canvas.drawBitmap(b, rectSrc, rectDest, paint);
        } else {
            super.onDraw(canvas);
        }
    }
    private Bitmap getCircleBitmap(Bitmap bitmap) {
        paint = new Paint();//线条样式默认实心（即线条颜色会填充所画图形区域）
        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        //创建一个可变的bitmap对象。宽高为之前所得的bitmap的宽高
        Canvas canvas = new Canvas(output);//新建 画布对象，传入一个output，最后canvas所绘制的都会传入bitmap
        final int color = 0xff424242;
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);//设置 画布背景颜色
        paint.setColor(color);
        int x = bitmap.getWidth();
        canvas.drawCircle(x / 2, x / 2, x / 2, paint);//(x/2,x/2), r = x/2
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));// 重叠图片 所产生的新图片的模式
        canvas.drawBitmap(bitmap, rect, rect, paint);//这个方法。只是简单的依据原bitmap大小，指定大小，和指定位置的区域画图而已。
        return output;
        //先画的为下层，后画的为上层。返回的是上层重叠的那部分即传入图片。
        //若将模式改为DST _IN则与之相反
    }
}
