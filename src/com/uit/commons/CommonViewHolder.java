/**
 *
 *	created by Mr.Simple, Aug 28, 201412:32:45 PM.
 *	Copyright (c) 2014, hehonghui@umeng.com All Rights Reserved.
 *
 *                #####################################################
 *                #                                                   #
 *                #                       _oo0oo_                     #   
 *                #                      o8888888o                    #
 *                #                      88" . "88                    #
 *                #                      (| -_- |)                    #
 *                #                      0\  =  /0                    #   
 *                #                    ___/`---'\___                  #
 *                #                  .' \\|     |# '.                 #
 *                #                 / \\|||  :  |||# \                #
 *                #                / _||||| -:- |||||- \              #
 *                #               |   | \\\  -  #/ |   |              #
 *                #               | \_|  ''\---/''  |_/ |             #
 *                #               \  .-\__  '-'  ___/-. /             #
 *                #             ___'. .'  /--.--\  `. .'___           #
 *                #          ."" '<  `.___\_<|>_/___.' >' "".         #
 *                #         | | :  `- \`.;`\ _ /`;.`/ - ` : | |       #
 *                #         \  \ `_.   \_ __\ /__ _/   .-` /  /       #
 *                #     =====`-.____`.___ \_____/___.-`___.-'=====    #
 *                #                       `=---='                     #
 *                #     ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~   #
 *                #                                                   #
 *                #               佛祖保佑         永无BUG              #
 *                #                                                   #
 *                #####################################################
 */

package com.uit.commons;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.uit.commons.utils.ViewFinder;

/**
 * 这是一个通用的ViewHolder, 将会装载AbsListView子类的item View, 并且将item
 * view中的子视图进行缓存和索引，使得用户能够方便的获取这些子view, 减少了代码重复。
 * 
 * @author mrsimple
 */
public class CommonViewHolder {

    /**
     * 构造函数
     * 
     * @param context Context
     * @param layoutId ListView、GridView或者其他AbsListVew子类的 Item View的资源布局id
     */
    protected CommonViewHolder(Context context, int layoutId) {
        // 初始化布局, 装载ContentView
        ViewFinder.initContentView(context, layoutId);
        // 将ViewHolder存储在ContentView的tag中
        ViewFinder.getContentView().setTag(this);
    }

    /**
     * 获取CommonViewHolder，当convertView为空的时候从布局xml装载item view,
     * 并且将该CommonViewHolder设置为convertView的tag, 便于复用convertView.
     * 
     * @param context Context
     * @param convertView Item view
     * @param layoutId 布局资源id, 例如R.layout.my_listview_item.
     * @return 通用的CommonViewHolder实例
     */
    public static CommonViewHolder getViewHolder(Context context, View convertView, int layoutId) {
        if (convertView == null) {
            return new CommonViewHolder(context, layoutId);
        }

        return (CommonViewHolder) convertView.getTag();
    }

    /**
     * @return 当前项的convertView, 在构造函数中装载
     */
    public View getConvertView() {
        return ViewFinder.getContentView();
    }

    /**
     * 为id为textViewId的TextView设置文本内容
     * 
     * @param textViewId 视图id
     * @param text 要设置的文本内容
     */
    public void setTextForTextView(int textViewId, CharSequence text) {
        TextView textView = ViewFinder.findViewById(textViewId);
        if (textView != null) {
            textView.setText(text);
        }
    }

    /**
     * 为ImageView设置图片
     * 
     * @param imageViewId ImageView的id, 例如R.id.my_imageview
     * @param drawableId Drawable图片的id, 例如R.drawable.my_photo
     */
    public void setImageForView(int imageViewId, int drawableId) {
        ImageView imageView = ViewFinder.findViewById(imageViewId);
        if (imageView != null) {
            imageView.setImageResource(drawableId);
        }
    }

    /**
     * 为ImageView设置图片
     * 
     * @param imageViewId ImageView的id, 例如R.id.my_imageview
     * @param bmp Bitmap图片
     */
    public void setImageForView(int imageViewId, Bitmap bmp) {
        ImageView imageView = ViewFinder.findViewById(imageViewId);
        if (imageView != null) {
            imageView.setImageBitmap(bmp);
        }
    }

    /**
     * 为CheckBox设置是否选中
     * 
     * @param checkViewId CheckBox的id
     * @param isCheck 是否选中
     */
    public void setCheckForCheckBox(int checkViewId, boolean isCheck) {
        CheckBox checkBox = ViewFinder.findViewById(checkViewId);
        if (checkBox != null) {
            checkBox.setChecked(isCheck);
        }
    }
}
