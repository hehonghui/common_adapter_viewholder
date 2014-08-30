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
import android.graphics.BitmapFactory;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * 这是一个通用的ViewHolder, 将会装载AbsListView子类的item View, 并且将item
 * view中的子视图进行缓存和索引，使得用户能够方便的获取这些子view, 减少了代码重复。
 * 
 * @author mrsimple
 */
public class CommonViewHolder {
    /**
     * Context
     */
    Context mContext;

    /**
     * 每项的View的sub view Map
     */
    private SparseArray<View> mViewMap = new SparseArray<View>();

    /**
     * ListView、GridView或者其他AbsListVew子类的 Item View
     */
    View mConvertView;

    /**
     * 构造函数
     * 
     * @param context Context
     * @param layoutId ListView、GridView或者其他AbsListVew子类的 Item View的资源布局id
     */
    protected CommonViewHolder(Context context, int layoutId) {
        mContext = context;
        mConvertView = LayoutInflater.from(mContext).inflate(layoutId, null, false);
        mConvertView.setTag(this);
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
        return mConvertView;
    }

    /**
     * 获取convertView中的子view, 该函数为泛型函数,首先从mViewMap中找该view是否已经缓存,如果已经缓存则直接使用缓存中的；
     * 否则使用convertView.findViewById(textVewId)来获取该view，再将其存储到mViewMap中
     * 
     * @param viewId 子视图的id, 例如R.id.my_title等
     * @see setTextForTextView
     * @return
     */
    @SuppressWarnings("unchecked")
    public <T extends View> T findViewById(int viewId) {
        View targetView = mViewMap.get(viewId);
        if (targetView == null) {
            targetView = mConvertView.findViewById(viewId);
            mViewMap.put(viewId, targetView);
        }

        return targetView == null ? null : (T) targetView;
    }

    /**
     * 为id为textViewId的TextView设置文本内容
     * 
     * @param textViewId 视图id
     * @param text 要设置的文本内容
     */
    public void setTextForTextView(int textViewId, CharSequence text) {
        TextView textView = this.findViewById(textViewId);
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
        this.setImageForView(imageViewId,
                BitmapFactory.decodeResource(mContext.getResources(), drawableId));
    }

    /**
     * 为ImageView设置图片
     * 
     * @param imageViewId ImageView的id, 例如R.id.my_imageview
     * @param bmp Bitmap图片
     */
    public void setImageForView(int imageViewId, Bitmap bmp) {
        ImageView imageView = this.findViewById(imageViewId);
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
        CheckBox checkBox = this.findViewById(checkViewId);
        if (checkBox != null) {
            checkBox.setChecked(isCheck);
        }
    }
}
