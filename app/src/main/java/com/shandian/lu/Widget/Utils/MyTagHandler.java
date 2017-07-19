package com.shandian.lu.Widget.Utils;

/**
 * Created by Administrator on 2017/7/19.
 */

import android.app.Application;
import android.content.Context;
import android.graphics.Color;
import android.text.Editable;
import android.text.Html;
import android.text.Spannable;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.StrikethroughSpan;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;

import org.xml.sax.XMLReader;

import java.lang.reflect.Field;
import java.util.HashMap;

/**
 *
 * span 标签 , 只识别了 style 下的 font-size, color  <span style="font-size:16px;color:#666666;size:16px"><font>title</font></span>
 *
 * 自定义的fonts 标签  <fonts size='24' color='#ff0000'>title</fonts>
 *
 * 参考
 * http://stackoverflow.com/questions/6952243/how-to-get-an-attribute-from-an-xmlreader
 * http://stackoverflow.com/questions/4044509/android-how-to-use-the-html-taghandler
 */
public class MyTagHandler implements Html.TagHandler {

    public static final String TAG = "MyTagHandler";

    public static final String TAG_FONTS = "fonts";
    public static final String TAG_FONTS_SIZE = "size";
    public static final String TAG_FONTS_COLOR = "color";

    public static final String TAG_SPAN = "span";
    public static final String TAG_SPAN_SIZE = "font-size";
    public static final String TAG_SPAN_COLOR = "color";

    public static final String TAG_STRIKE = "STRIKE";

    Context mContext;

    public MyTagHandler(Context context){
        mContext = context;
    }

    @Override
    public void handleTag(boolean opening, String tag, Editable output, XMLReader xmlReader) {
        //自定义的会走进来, 系统支持的标签 如<font> 标签不会进来
        Log.i(TAG, opening + "tag=" + tag + "  output=" + output + "xmlReader=" + xmlReader);

        if (tag.equalsIgnoreCase(TAG_FONTS)) {
            if (opening){
                processAttributes(xmlReader);
            }
            processFonts(opening, output);
        } else if(tag.equalsIgnoreCase(TAG_STRIKE)) {
//                if (opening){
//                    processAttributes(xmlReader);
//                }
            processStrike(opening,output);
        } else if(tag.equalsIgnoreCase(TAG_SPAN)){
            if (opening){
                processAttributes(xmlReader);
            }
            processSpan(opening, output);
        }
    }


    final HashMap<String, String> attributes = new HashMap<String, String>();

    void printHashMap() {
        Log.i(TAG, "HashMap is:");
        for (String key : attributes.keySet()) {
            Log.i(TAG, key + ":" + attributes.get(key));
        }
    }

    private void processAttributes(final XMLReader xmlReader) {
        attributes.clear();
        try {
            Field elementField = xmlReader.getClass().getDeclaredField("theNewElement");
            elementField.setAccessible(true);
            Object element = elementField.get(xmlReader);
            Field attsField = element.getClass().getDeclaredField("theAtts");
            attsField.setAccessible(true);
            Object atts = attsField.get(element);
            Field dataField = atts.getClass().getDeclaredField("data");
            dataField.setAccessible(true);
            String[] data = (String[]) dataField.get(atts);
            Field lengthField = atts.getClass().getDeclaredField("length");
            lengthField.setAccessible(true);
            int len = (Integer) lengthField.get(atts);

            /**
             * MSH: Look for supported attributes and add to hash map.
             * This is as tight as things can get :)
             * The data index is "just" where the keys and values are stored.
             */
            for (int i = 0; i < len; i++) {
                attributes.put(data[i * 5 + 1], data[i * 5 + 4]);
            }

        } catch (Exception e) {
            Log.d(TAG, "Exception: " + e);
            e.printStackTrace();
        }
    }

    private void processFonts(boolean opening, Editable output){
        int len = output.length();
        if(opening) {
            output.setSpan(new ForegroundColorSpan(Color.parseColor("#333333")), len, len, Spannable.SPAN_MARK_MARK);
        } else {
            Object obj = getLast(output, ForegroundColorSpan.class);
            int where = output.getSpanStart(obj);

            output.removeSpan(obj);

            if (where != len) {

                int size  = Integer.valueOf(attributes.get(TAG_FONTS_SIZE));
                String color = attributes.get("color");
                AbsoluteSizeSpan sizeSpan = new AbsoluteSizeSpan(dp2Px(size));
                ForegroundColorSpan colorSpan = new ForegroundColorSpan(Color.parseColor(color));
                output.setSpan(sizeSpan, where, len, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                output.setSpan(colorSpan, where, len, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
        }
    }

    private void processStrike(boolean opening, Editable output) {
        int len = output.length();
        if(opening) {
            output.setSpan(new StrikethroughSpan(), len, len, Spannable.SPAN_MARK_MARK);
        } else {
            Object obj = getLast(output, StrikethroughSpan.class);
            int where = output.getSpanStart(obj);

            output.removeSpan(obj);

            if (where != len) {
                output.setSpan(new StrikethroughSpan(), where, len, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
        }
    }

    private void processSpan(boolean opening, Editable output){
        int len = output.length();
        if(opening) {
            output.setSpan(new AbsoluteSizeSpan(dp2Px(12)), len, len, Spannable.SPAN_MARK_MARK);
        } else {
            Object obj = getLast(output, AbsoluteSizeSpan.class);
            int where = output.getSpanStart(obj);

            output.removeSpan(obj);

            if (where != len) {

                String style = attributes.get("style");

                if (TextUtils.isEmpty(style)){
                    return;
                }
                String[] attrs = style.split(";");

                HashMap<String,String> attrsHashMap = new HashMap<String, String>(3);

                for (int i=0 ; i < attrs.length; i++){
                    String[] map = attrs[i].split(":");
                    if (map.length>=2) {
                        attrsHashMap.put(map[0], map[1]);
                    }
                }

                if (attrsHashMap.size() > 0) {
                    if(where < 0){
                        where = 0;
                    }

                    if (attrsHashMap.containsKey(TAG_SPAN_SIZE)){
                        //remove px
                        String sizePx = attrsHashMap.get(TAG_SPAN_SIZE).toString().replace("px", "");
                        int size = Integer.valueOf(sizePx);
                        AbsoluteSizeSpan sizeSpan = new AbsoluteSizeSpan(dp2Px(size));
                        output.setSpan(sizeSpan, where, len, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    }

                    if(attrsHashMap.containsKey(TAG_SPAN_COLOR)){
                        String color = attrsHashMap.get(TAG_SPAN_COLOR);
                        ForegroundColorSpan colorSpan = new ForegroundColorSpan(Color.parseColor(color));
                        output.setSpan(colorSpan, where, len, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

                    }

                }
            }
        }
    }

    private Object getLast(Editable text, Class kind) {
        Object[] objs = text.getSpans(0, text.length(), kind);

        if (objs.length == 0) {
            return null;
        } else {
            for(int i = objs.length;i>0;i--) {
                if(text.getSpanFlags(objs[i-1]) == Spannable.SPAN_MARK_MARK) {
                    return objs[i-1];
                }
            }
            return null;
        }
    }


    private DisplayMetrics mDm;

    private  DisplayMetrics getDisplayMetrics(){
        if (mDm == null){
            mDm = mContext.getResources().getDisplayMetrics();
        }
        return mDm;
    }

    public  int dp2Px(float dp) {
        return (int) (TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, getDisplayMetrics()) + 0.5f);
    }
}
