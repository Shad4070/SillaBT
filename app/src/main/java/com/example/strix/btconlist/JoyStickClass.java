package com.example.strix.btconlist;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;

public class JoyStickClass {
    public static final int STICK_NONE = 0;
    public static final int STICK_UP = 1;
    public static final int STICK_UPRIGHT = 2;
    public static final int STICK_RIGHT = 3;
    public static final int STICK_DOWNRIGHT = 4;
    public static final int STICK_DOWN = 5;
    public static final int STICK_DOWNLEFT = 6;
    public static final int STICK_LEFT = 7;
    public static final int STICK_UPLEFT = 8;

    private int STICK_ALPHA = 100;
    private int LAYOUT_ALPHA = 100;
    private int OFFSET = 0;

    private Context mContext;
    private ViewGroup mLayout;
    private LayoutParams params;
    private int stick_width, stick_height;

    private int position_x = 0, position_y = 0, min_distance = 0;
    private float distance = 0, angle = 0;

    private DrawCanvas draw;
    private Paint paint;
    private Bitmap stick;

    private boolean touch_state = false;

    public JoyStickClass (Context context, ViewGroup layout, int stick_res_id) {
        mContext = context;

        stick = BitmapFactory.decodeResource(mContext.getResources(),
                stick_res_id);

        stick_width = stick.getWidth();
        stick_height = stick.getHeight();

        draw = new DrawCanvas(mContext);
        paint = new Paint();
        mLayout = layout;
        params = mLayout.getLayoutParams();
    }

    public void drawStick(MotionEvent arg1) {
        position_x = (int) (arg1.getX() - (params.width / 2));
        position_y = (int) (arg1.getY() - (params.height / 2));
        distance = (float) Math.sqrt(Math.pow(position_x, 2) + Math.pow(position_y, 2));
        angle = (float) cal_angle(position_x, position_y);


        if(arg1.getAction() == MotionEvent.ACTION_DOWN) {
            if(distance <= (params.width / 2) - OFFSET) {
                draw.position(arg1.getX(), arg1.getY());
                draw();
                touch_state = true;
            }
        } else if(arg1.getAction() == MotionEvent.ACTION_MOVE && touch_state) {
            if(distance <= (params.width / 2) - OFFSET) {
                draw.position(arg1.getX(), arg1.getY());
                draw();
            } else if(distance > (params.width / 2) - OFFSET){
                float x = (float) (Math.cos(Math.toRadians(cal_angle(position_x, position_y))) * ((params.width / 2) - OFFSET));
                float y = (float) (Math.sin(Math.toRadians(cal_angle(position_x, position_y))) * ((params.height / 2) - OFFSET));
                x += (params.width / 2);
                y += (params.height / 2);
                draw.position(x, y);
                draw();
            } else {
                mLayout.removeView(draw);
            }
        } else if(arg1.getAction() == MotionEvent.ACTION_UP) {
            mLayout.removeView(draw);
            touch_state = false;
        }
    }

    public int[] getPosition() {
        if(distance > min_distance && touch_state) {
            return new int[] { position_x, position_y };
        }
        return new int[] { 0, 0 };
    }

    public int getX() {
        if(distance > min_distance && touch_state) {
            return position_x;
        }
        return 0;
    }

    public int getY() {
        if(distance > min_distance && touch_state) {
            return position_y*-1;
        }
        return 0;
    }

    public short getYc(){
        short x=0;
        int geto=getY();
        if (geto<20&&geto>=0){
            x=(short)50;
        }else if (geto>=20&&geto<30) {
            x = (short) 55;
        }else if (geto>=30&&geto<39){
            x= (short) 57.5;
        }else if (geto>=40&&geto<49) {
            x = (short) 60;
        }else if (geto>=50&&geto<59) {
            x = (short) 62.5;
        }else if (geto>=60&&geto<69) {
            x = (short) 65;
        }else if (geto>=70&&geto<79) {
            x = (short) 67.5;
        }else if (geto>=80&&geto<89) {
            x = (short) 70;
        }else if (geto>=90&&geto<99) {
            x = (short) 72.5;
        }else if (geto>=100&&geto<109) {
            x = (short) 75;
        }else if (geto>=110&&geto<119) {
            x = (short) 77.5;
        }else if (geto>=120&&geto<129) {
            x = (short) 80;
        }else if (geto>=130&&geto<139) {
            x = (short) 82.5;
        }else if (geto>=140&&geto<149) {
            x = (short) 85;
        }else if (geto>=150&&geto<159) {
            x = (short) 87.5;
        }else if (geto>=160&&geto<169) {
            x = (short) 90;
        }else if (geto>=170&&geto<179) {
            x = (short) 92.5;
        }else if (geto>=180&&geto<189) {
            x = (short) 95;
        }else if (geto>=190&&geto<199) {
            x = (short) 97.5;
        }

        if(geto>-20&&geto<=0){
            x=(short)50;
        }else if (geto<=-20&&geto>-30) {
            x = (short) 45;
        }else if (geto<=-30&&geto>-39){
            x= (short) 42.5;
        }else if (geto<=-40&&geto>-49) {
            x = (short) 40;
        }else if (geto<=-50&&geto>-59) {
            x = (short) 37.5;
        }else if (geto<=-60&&geto>-69) {
            x = (short) 35;
        }else if (geto<=-70&&geto>-79) {
            x = (short) 32.5;
        }else if (geto<=-80&&geto>-89) {
            x = (short) 30;
        }else if (geto<=-90&&geto>-99) {
            x = (short) 27.5;
        }else if (geto<=-100&&geto>-109) {
            x = (short) 25;
        }else if (geto<=-110&&geto>-119) {
            x = (short) 22.5;
        }else if (geto<=-120&&geto>-129) {
            x = (short) 20;
        }else if (geto<=-130&&geto>-139) {
            x = (short) 17.5;
        }else if (geto<=-140&&geto>-149) {
            x = (short) 15;
        }else if (geto<=-150&&geto>-159) {
            x = (short) 12.5;
        }else if (geto<=-160&&geto>-169) {
            x = (short) 10;
        }else if (geto<=-170&&geto>-179) {
            x = (short) 7.5;
        }else if (geto<=-180&&geto>-189) {
            x = (short) 5;
        }else if (geto<=-190&&geto>-199) {
            x = (short) 2.5;
        }
        return x;
    }

    public short getXc(){
        short x=0;
        int geo=getX();
        if(geo<20&&geo>=0){
            x=(short) 50;
        }else if (geo>=20&&geo<30) {
            x = (short) 55;
        }else if (geo>=30&&geo<39){
            x= (short) 57.5;
        }else if (geo>=40&&geo<49) {
            x = (short) 60;
        }else if (geo>=50&&geo<59) {
            x = (short) 62.5;
        }else if (geo>=60&&geo<69) {
            x = (short) 65;
        }else if (geo>=70&&geo<79) {
            x = (short) 67.5;
        }else if (geo>=80&&geo<89) {
            x = (short) 70;
        }else if (geo>=90&&geo<99) {
            x = (short) 72.5;
        }else if (geo>=100&&geo<109) {
            x = (short) 75;
        }else if (geo>=110&&geo<119) {
            x = (short) 77.5;
        }else if (geo>=120&&geo<129) {
            x = (short) 80;
        }else if (geo>=130&&geo<139) {
            x = (short) 82.5;
        }else if (geo>=140&&geo<149) {
            x = (short) 85;
        }else if (geo>=150&&geo<159) {
            x = (short) 87.5;
        }else if (geo>=160&&geo<169) {
            x = (short) 90;
        }else if (geo>=170&&geo<179) {
            x = (short) 92.5;
        }else if (geo>=180&&geo<189) {
            x = (short) 95;
        }else if (geo>=190&&geo<199) {
            x = (short) 97.5;
        }

        if (geo>-20&&geo<=0){
            x=(short)50;
        }else if (geo<=-20&&geo>-30) {
            x = (short) 45;
        }else if (geo<=-30&&geo>-39){
            x= (short) 42.5;
        }else if (geo<=-40&&geo>-49) {
            x = (short) 40;
        }else if (geo<=-50&&geo>-59) {
            x = (short) 37.5;
        }else if (geo<=-60&&geo>-69) {
            x = (short) 35;
        }else if (geo<=-70&&geo>-79) {
            x = (short) 32.5;
        }else if (geo<=-80&&geo>-89) {
            x = (short) 30;
        }else if (geo<=-90&&geo>-99) {
            x = (short) 27.5;
        }else if (geo<=-100&&geo>-109) {
            x = (short) 25;
        }else if (geo<=-110&&geo>-119) {
            x = (short) 22.5;
        }else if (geo<=-120&&geo>-129) {
            x = (short) 20;
        }else if (geo<=-130&&geo>-139) {
            x = (short) 17.5;
        }else if (geo<=-140&&geo>-149) {
            x = (short) 15;
        }else if (geo<=-150&&geo>-159) {
            x = (short) 12.5;
        }else if (geo<=-160&&geo>-169) {
            x = (short) 10;
        }else if (geo<=-170&&geo>-179) {
            x = (short) 7.5;
        }else if (geo<=-180&&geo>-189) {
            x = (short) 5;
        }else if (geo<=-190&&geo>-199) {
            x = (short) 2.5;
        }
        return x;
    }
    public float getAngle() {
        if(distance > min_distance && touch_state) {
            return angle;
        }
        return 0;
    }

    public float getDistance() {
        if(distance > min_distance && touch_state) {
            return distance;
        }
        return 0;
    }

    public void setMinimumDistance(int minDistance) {
        min_distance = minDistance;
    }

    public int getMinimumDistance() {
        return min_distance;
    }

    public int get8Direction() {
        if(distance > min_distance && touch_state) {
            if(angle >= 247.5 && angle < 292.5 ) {
                return STICK_UP;
            } else if(angle >= 292.5 && angle < 337.5 ) {
                return STICK_UPRIGHT;
            } else if(angle >= 337.5 || angle < 22.5 ) {
                return STICK_RIGHT;
            } else if(angle >= 22.5 && angle < 67.5 ) {
                return STICK_DOWNRIGHT;
            } else if(angle >= 67.5 && angle < 112.5 ) {
                return STICK_DOWN;
            } else if(angle >= 112.5 && angle < 157.5 ) {
                return STICK_DOWNLEFT;
            } else if(angle >= 157.5 && angle < 202.5 ) {
                return STICK_LEFT;
            } else if(angle >= 202.5 && angle < 247.5 ) {
                return STICK_UPLEFT;
            }
        } else if(distance <= min_distance && touch_state) {
            return STICK_NONE;
        }
        return 0;
    }

    public int get4Direction() {
        if(distance > min_distance && touch_state) {
            if(angle >= 225 && angle < 315 ) {
                return STICK_UP;
            } else if(angle >= 315 || angle < 45 ) {
                return STICK_RIGHT;
            } else if(angle >= 45 && angle < 135 ) {
                return STICK_DOWN;
            } else if(angle >= 135 && angle < 225 ) {
                return STICK_LEFT;
            }
        } else if(distance <= min_distance && touch_state) {
            return STICK_NONE;
        }
        return 0;
    }

    public void setOffset(int offset) {
        OFFSET = offset;
    }

    public int getOffset() {
        return OFFSET;
    }

    public void setStickAlpha(int alpha) {
        STICK_ALPHA = alpha;
        paint.setAlpha(alpha);
    }

    public int getStickAlpha() {
        return STICK_ALPHA;
    }

    public void setLayoutAlpha(int alpha) {
        LAYOUT_ALPHA = alpha;
        mLayout.getBackground().setAlpha(alpha);
    }

    public int getLayoutAlpha() {
        return LAYOUT_ALPHA;
    }

    public void setStickSize(int width, int height) {
        stick = Bitmap.createScaledBitmap(stick, width, height, false);
        stick_width = stick.getWidth();
        stick_height = stick.getHeight();
    }

    public void setStickWidth(int width) {
        stick = Bitmap.createScaledBitmap(stick, width, stick_height, false);
        stick_width = stick.getWidth();
    }

    public void setStickHeight(int height) {
        stick = Bitmap.createScaledBitmap(stick, stick_width, height, false);
        stick_height = stick.getHeight();
    }

    public int getStickWidth() {
        return stick_width;
    }

    public int getStickHeight() {
        return stick_height;
    }

    public void setLayoutSize(int width, int height) {
        params.width = width;
        params.height = height;
    }

    public int getLayoutWidth() {
        return params.width;
    }

    public int getLayoutHeight() {
        return params.height;
    }

    private double cal_angle(float x, float y) {
        if(x >= 0 && y >= 0)
            return Math.toDegrees(Math.atan(y / x));
        else if(x < 0 && y >= 0)
            return Math.toDegrees(Math.atan(y / x)) + 180;
        else if(x < 0 && y < 0)
            return Math.toDegrees(Math.atan(y / x)) + 180;
        else if(x >= 0 && y < 0)
            return Math.toDegrees(Math.atan(y / x)) + 360;
        return 0;
    }

    private void draw() {
        try {
            mLayout.removeView(draw);
        } catch (Exception e) { }
        mLayout.addView(draw);
    }

    private class DrawCanvas extends View{
        float x, y;

        private DrawCanvas(Context mContext) {
            super(mContext);
        }

        public void onDraw(Canvas canvas) {
            canvas.drawBitmap(stick, x, y, paint);
        }

        private void position(float pos_x, float pos_y) {
            x = pos_x - (stick_width / 2);
            y = pos_y - (stick_height / 2);
        }
    }
}
