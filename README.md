common_adapter_viewholder
=========================


# CommonAdapter的使用

## 介绍与使用
  一般我们在项目中使用到ListView和GridView组件都是都会用到Adapter，比较多的情况是继承自BaseAdapter，然后实现getCount、getView等方法，再使用ViewHolder来提高一下效率.我们看下面一个例子 :
  
### ListView布局文件
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

### ListView子项的布局文件
listview_item_layout.xml :
```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="horizontal" >

    <TextView
        android:id="@+id/my_textview"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginLeft="20dp"
        android:textSize="18sp" />

</LinearLayout>
```    
  
### 我们通常情况下要写的Adapter代码
```java
public class NormalAdapter extends BaseAdapter {

    Context mContext;

    LayoutInflater mInflater;

    List<String> mDataList;

    /**
     * @param context
     * @param data
     */
    public NormalAdapter(Context context, List<String> data) {
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mDataList = data;
    }

    @Override
    public int getCount() {
        return mDataList.size();
    }

    @Override
    public Object getItem(int position) {
        return mDataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.listview_item_layout, null, false);
            viewHolder = new ViewHolder();
            viewHolder.mTextView = (TextView) convertView.findViewById(R.id.my_textview);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.mTextView.setText((String) getItem(position));
        return convertView;
    }

    static class ViewHolder {
        TextView mTextView;
    }

}
```      

   然而写过多遍以后我们发现我们总是重复地在写这些getCount、getItem、getView方法以及ViewHolder，导致了很多重复工作，于是我把这些重复工作抽象起来(以前也有在github上看到这样的通用Adapter实现)便于自己使用，也是自己学习的一个过程。下面我们看看使用CommonAdapter后我们做与上面同样的工作需要怎么写。
   
### 使用CommonAdapter后要写的代码
```java
CommonAdapter<String> listAdapter = new CommonAdapter<String>(getActivity(),
                    R.layout.listview_item_layout, mockListViewItems()) {

                @Override
                protected void fillItemData(CommonViewHolder viewHolder, String item) {
                    // 设置text
                    viewHolder.setTextForTextView(R.id.my_textview, item);
                }
            }
```    
   可以看到，我们的代码量减少了很多，如果一个项目中有好几个ListView、GridView等组件，我们就不需要重复做那么多工作了。

### 运行demo看效果吧 
	   


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
