/**
 *
 *	created by Mr.Simple, Aug 28, 201412:26:52 PM.
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
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * 这是一个通用、抽象的适配器类,覆写了BaseAdapter的getCount, getItem, getItemId,
 * getView方法,在getView方法中通过
 * 通用的CommonViewHolder来对convertView的进行处理,并且缓存convertView中的其他View元素
 * ，降低了ListView、GridView 等组件的Adapter和ViewHolder的代码量.
 * 用户只需要在fillItemData函数中将第position位置里的数据填充到listview或者gridview的第position的view中即可
 * ,具体使用实例参考文档.
 * 
 * @author mrsimple
 * @param <T> 数据源的类型
 */
public abstract class CommonAdapter<T> extends BaseAdapter {

    /**
     * Context
     */
    Context mContext;
    /**
     * 要展示的数据列表
     */
    List<T> mData;
    /**
     * 每一项的布局id,例如R.layout.my_listview_item.
     */
    private int mItemLayoutId = -1;

    /**
     * @param context Context
     * @param itemLayoutResId
     *            每一项(适用于listview、gridview等AbsListView子类)的布局资源id,例如R.layout.
     *            my_listview_item.
     * @param dataSource 数据源
     */
    public CommonAdapter(Context context, int itemLayoutResId, List<T> dataSource) {
        checkParams(context, itemLayoutResId, dataSource);
        mContext = context;
        mItemLayoutId = itemLayoutResId;
        mData = dataSource;
    }

    /**
     * 检查参数的有效性
     * 
     * @param context
     * @param itemLayoutResId
     * @param dataSource
     */
    private void checkParams(Context context, int itemLayoutResId, List<T> dataSource) {
        if (context == null || itemLayoutResId < 0 || dataSource == null) {
            throw new RuntimeException(
                    "context == null || itemLayoutResId < 0 || dataSource == null, please check your params");
        }
    }

    /**
     * 返回数据的总数
     */
    @Override
    public int getCount() {
        return mData.size();
    }

    /**
     * 返回position位置的数据
     */
    @Override
    public T getItem(int position) {
        return mData.get(position);
    }

    /**
     * item id, 返回position
     */
    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * 返回position位置的view, 即listview、gridview的第postion个view
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // 获取ViewHolder
        CommonViewHolder viewHolder = CommonViewHolder.getViewHolder(mContext, convertView, parent,
                mItemLayoutId);
        // 填充数据
        fillItemData(viewHolder, position, getItem(position));
        // 返回convertview
        return viewHolder.getContentView();
    }

    /**
     * 用户必须覆写该方法来讲数据填充到视图中
     * 
     * @param viewHolder 通用的ViewHolder, 里面会装载listview,
     *            gridview等组件的每一项的视图，并且缓存其子view
     * @param item 数据源的第position项数据
     */
    protected abstract void fillItemData(CommonViewHolder viewHolder, int position, T item);

}
