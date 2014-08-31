
package com.uit.comm.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

import com.uit.commons.CommonAdapter;
import com.uit.commons.CommonViewHolder;
import com.uit.commons.utils.ViewFinder;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {

            // View rootView = inflater.inflate(R.layout.fragment_main,
            // container, false);
            // // 获取ListView对象
            // ListView listView = (ListView)
            // rootView.findViewById(R.id.my_listview);

            // 初始化ViewFinder, 将Context和该页面布局id作为参数
            ViewFinder.initContentView(getActivity(), R.layout.fragment_main);
            // 获取ListView对象
            final ListView listView = ViewFinder.findViewById(R.id.my_listview);
            // 设置CommonAdapter
            listView.setAdapter(new CommonAdapter<ListViewItem>(getActivity(),
                    R.layout.listview_item_layout, mockListViewItems()) {

                @Override
                protected void fillItemData(CommonViewHolder viewHolder, ListViewItem item) {
                    // 设置图片
                    viewHolder.setImageForView(R.id.my_imageview, item.mDrawableId);
                    // 设置text
                    viewHolder.setTextForTextView(R.id.my_textview, item.mText);
                }
            });
            listView.setOnItemClickListener(new OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Toast.makeText(getActivity(),
                            "click " + listView.getAdapter().getItem(position), Toast.LENGTH_SHORT)
                            .show();
                }

            });
            return ViewFinder.getContentView();
        }

        /**
         * 模拟一些数据
         * 
         * @return
         */
        private List<ListViewItem> mockListViewItems() {
            List<ListViewItem> dataItems = new ArrayList<ListViewItem>();
            dataItems.add(new ListViewItem(R.drawable.girl_96, "girl_96.png"));
            dataItems.add(new ListViewItem(R.drawable.fire_96, "fire_96.png"));
            dataItems.add(new ListViewItem(R.drawable.grimace_96, "grimace_96.png"));
            dataItems.add(new ListViewItem(R.drawable.laugh_96, "laugh_96.png"));
            return dataItems;
        }
    }

}
