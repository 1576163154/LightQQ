package xion.lightqq.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import xion.lightqq.R;

/**
 * Created by Administrator on 2016/10/20.
 */

public class RightSearchView extends View {
    private static String[] b = { "A", "B", "C" ,"D", "E", "F", "G", "H", "I","J", "K", "L", "M",
            "N","O", "P", "Q","R", "S", "T", "U","V","W", "X", "Y", "Z"};
    private int choose = -1;
    private Paint paint = new Paint();
    private TextView mTextDialog;

    public void setmTextDialog(TextView mTextDialog) {
        this.mTextDialog = mTextDialog;
    }

    public RightSearchView(Context context) {
        super(context);
    }

    public RightSearchView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RightSearchView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setColor(Color.rgb(33,65,98));
        paint.setTypeface(Typeface.DEFAULT_BOLD);//设置字体
        paint.setAntiAlias(true);
        paint.setTextSize(35f);

        int screenHeight = getHeight();//获取屏幕高度
        View titleView = inflate(getContext(), R.layout.choosecountry,null);
        int titleHeight = titleView.findViewById(R.id.rl_tittle).getHeight();
        int width = getWidth();
        int singleHeight = (screenHeight - titleHeight )/b.length;//确定每个字母高度
        for (int i = 0; i < b.length; i++) {

            float xPos = width - 30f - paint.measureText(b[i])/2;//为了让字母对齐这里粗略计算了每个字符的宽度
            float yPos = singleHeight*i+singleHeight;
            canvas.drawText(b[i],xPos,yPos,paint);
        }
    }
}
