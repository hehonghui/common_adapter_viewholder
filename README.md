common_adapter_viewholder
=========================

common_adapter_viewholder


# CommonAdapter的使用

## Fragment布局
fragment_main.xml :
```xml
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:paddingBottom="@dimen/activity_vertical_margin"
    android:paddingLeft="@dimen/activity_horizontal_margin"
    android:paddingRight="@dimen/activity_horizontal_margin"
    android:paddingTop="@dimen/activity_vertical_margin"
    tools:context="com.example.push_demo_1.MainActivity$PlaceholderFragment" >

    <ListView
        android:id="@+id/my_listview"
        android:layout_width="match_parent"
        android:layout_height="match_parent" />

</RelativeLayout>
```   

## ListView每项的布局
listview_item_layout.xml :
```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="horizontal" >

    <ImageView
        android:id="@+id/my_imageview"
        android:layout_width="64dp"
        android:layout_height="64dp"
        android:contentDescription="@string/app_name" />

    <TextView
        android:id="@+id/my_textview"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginLeft="20dp"
        android:textSize="18sp" />

</LinearLayout>
```    

## MainActivity中加载的Fragment 
```java
public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            ListView listView = (ListView) rootView.findViewById(R.id.my_listview);
            // 设置CommonAdapter,泛型参数为ListViewImte
            listView.setAdapter(new CommonAdapter<ListViewItem>(getActivity(),
                    R.layout.listview_item_layout, mockListViewItems()) {

                @Override
                protected void fillItemData(CommonViewHolder viewHolder, ListViewItem item) {
                    // 设置图片
                    viewHolder.setImageForView(R.id.my_imageview, item.mDrawableId);
                    // 设置text
                    viewHolder.setTextForTextView(R.id.my_textview, item.mName);
                }
            });
            return rootView;
        }

        private List<ListViewItem> mockListViewItems() {
            List<ListViewItem> dataItems = new ArrayList<MainActivity.ListViewItem>();
            dataItems.add(new ListViewItem(R.drawable.girl_96, "girl_96.png"));
            dataItems.add(new ListViewItem(R.drawable.fire_96, "fire_96.png"));
            dataItems.add(new ListViewItem(R.drawable.grimace_96, "grimace_96.png"));
            dataItems.add(new ListViewItem(R.drawable.laugh_96, "laugh_96.png"));
            return dataItems;
        }
    }

    /**
     * @author mrsimple
     */
    static class ListViewItem {
        public int mDrawableId;
        public String mName;

        /**
         * @param id
         * @param name
         */
        public ListViewItem(int id, String name) {
            mDrawableId = id;
            mName = name;
        }

    }
```    

MainActivity的onCreate方法 : 
```java
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
```    


## 运行demo看效果吧 
	   

