common_adapter_viewholder
=========================


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
            // 获取ListView实例
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
	
		// 模拟一些数据
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
     * ListView每项对应的数据项
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
	   

## ViewFinder的使用

  ViewFinder是一个在一个布局中找某个子控件的工具类,用户需要在使用时调用ViewFinder.initContentView函数来初始化ContentView，参数为Context和布局id。然后使用ViewFinder.findViewById来获取需要的view,findViewById为泛型方法,返回的view则直接是你接收的类型,而不需要进行强制类型转换.比如,以前我们在Activity中找一些子控件一般是这样 : 
```java
@Override
protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //  查找子控件
        TextView textView = (TextView)findViewById(R.id.my_textview); 
 		ImageView imageView = (ImageView)findViewById(R.id.my_imageview); 
 		ListView listView = (ListView)findViewById(R.id.my_listview);
}
```    
   如果页面中的控件比较多，就会有很多的类型转换,而使用ViewFinder则免去了类型转换,示例如下 : 
```java
@Override
protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 初始化
        ViewFinder.initContentView(this, R.layout.activity_main) ;
        // 查找子控件
        TextView textView = ViewFinder.findViewById(R.id.my_textview); 
 		ImageView imageView = ViewFinder.findViewById(R.id.my_imageview); 
 		ListView listView = ViewFinder.findViewById(R.id.my_listview);
}
```      
