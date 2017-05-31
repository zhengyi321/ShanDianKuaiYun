package com.zhyan.shandiankuaiyuanwidgetlib;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * �ĸ�����ʾ����
 * Created by zz on 2017/2/6.
 */

public class ProportionView extends View {

	 /*���˵ļ��*/
    public final static int PLACE = 16;
    /*Բ�뾶*/
    public final static int C_R = 13;
    /*�߿�*/
    public final static int LINE_WIDTH = 4;


    /*�Ƿ��Ѿ���ʼ��*/
    private boolean isInit = false;

    /*����ɫ�ı�*/
    private Paint paint_colorful;
    //��ɫ�ı�
    private Paint paint_balck;
    //��ɫ�ı�
    private Paint paint_gray;


    /*view����Ŀ�*/
    private float width;
    /*view ����ĸ�*/
    private float heith;

    /*����Բ֮��ļ�� �����Ϊ�߳�*/
    private float item_place;

    //��ͼ�����
    private float start_x;
    private float start_y;

    /*�� ���¶�*/
    private float line_Y_up;
    private float line_Y_down;

    //��ǰ��״̬λ�ã�ʵ��ʹ�ÿ�������arrt ,�������ˡ���
    private int position = 1;
    //��ĸ���
    private int num = 4;

    public ProportionView(Context context) {
        this(context, null);
    }

    public ProportionView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ProportionView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (!isInit) {
            init();
        }

        onMyDraw(canvas);
    }

    /*
     * ��Ҫ�߼�
     * �Ȼ��ߣ�����Ϊ���ڵײ㣬��ֹԲ���ڸ�
     */
    private void onMyDraw(Canvas canvas) {
        /*����*/
        //�߱��ֳ������֣�ǰ���ֺ󲿷֣����㴦��
        Paint paint_line_f, paint_line_s;
        for (int j = 0; j < num - 1; j++) {
            //ǰ�벿��
            if (j < position) {
                paint_line_f = paint_colorful;
                paint_line_s = paint_colorful;
            } else if (j > position) {
                paint_line_f = paint_gray;
                paint_line_s = paint_gray;
            } else {
                paint_line_f = paint_colorful;
                paint_line_s = paint_gray;
            }

            float f_lx = start_x + item_place * j;
            float f_lx_end = f_lx + item_place / 2;
            canvas.drawRect(f_lx, line_Y_up, f_lx_end, line_Y_down, paint_line_f);

            //��벿��
            float s_lx_end = f_lx + item_place;
            float s_lx = f_lx_end;
            canvas.drawRect(f_lx_end, line_Y_up, s_lx_end, line_Y_down, paint_line_s);
        }

        /*��Բ*/
        //ԲΪ ����ͬ��Բ����СǶ�ף������Ⱥ�˳�򲻿ɸı�
        Paint paint_circle_b;
        for (int i = 0; i < num; i++) {
            if (i <= position) {
                paint_circle_b = paint_colorful;
            } else {
                paint_circle_b = paint_balck;
            }

            float cx = start_x + item_place * i;
            canvas.drawCircle(cx, start_y, C_R, paint_circle_b);
            canvas.drawCircle(cx, start_y, C_R / 2, paint_gray);
        }

        /*д�֣��ȼ���ʶ��*/
        float text_x = C_R;
        float text_y = start_y - 15;
        paint_colorful.setTextSize(20);
        canvas.drawText("+10", start_x + item_place - text_x, text_y, paint_colorful);
        canvas.drawText("+10", start_x + item_place * 2 - text_x, text_y, paint_colorful);
        canvas.drawText("+10", start_x + item_place * 3 - text_x, text_y, paint_colorful);

    }

    @SuppressLint("ResourceAsColor")
	private void init() {
        /*һЩ ����� flost�ļ���*/
        width = getMeasuredWidth();
        heith = getMeasuredHeight();


        start_x = PLACE + C_R;
        start_y = heith / 2;

        line_Y_up = start_y - LINE_WIDTH / 2;
        line_Y_down = LINE_WIDTH / 2 + start_y;

        item_place = (width - start_x * 2) / (num - 1);


        /*���ʵĳ�ʼ��*/
        paint_colorful = new Paint();
        paint_colorful.setColor(getResources().getColor(R.color.orange));

        paint_balck = new Paint();
        paint_balck.setColor(getResources().getColor(R.color.gra));

        paint_gray = new Paint();
        paint_gray.setColor(getResources().getColor(R.color.gray));

        isInit = true;
    }


    /*
    * ---------------------------------------------------
    *  *****************���÷���*************************
    * ---------------------------------------------------
    */

    /**
     * ����״̬λ��
     *
     * @param position ��ǰ״̬ λ��
     */
    public void setPosition(int position) {
        this.position = position;
        //�ػ�����
        postInvalidate();
    }


}
