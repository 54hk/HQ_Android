package project.hq.com.hq_android.ui.base;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 54hk on 2017/12/2.
 */

public class BaseListAdapter<T> extends BaseAdapter {
    //定义集合数据
    List<T> list = new ArrayList<T>();
    //上下文
    Context context;

    public BaseListAdapter(Context context, List<T> list, List<String> strings){
        this.context = context;
        this.list = list;
    }
    //传入的是一个集合的数据的情况
    public BaseListAdapter(Context context, List<T> list) {

        this.context = context;
        this.list = list;
    }

    //传入的是一个数组数据的情况
    //其实数组也是要转换为集合的数据，因为适配器只接受集合的数据
    public BaseListAdapter(Context context, T[] list) {
        this.context = context;
        for (T t : list) {
            this.list.add(t);
        }
    }

    //返回数据的总数
    @Override
    public int getCount() {
        return list == null ? 0 : list.size();
    }

    //返回集合中某个游标值的对象
    @Override
    public T getItem(int position) {
        return list == null ? null : list.get(position);
    }

    //返回选中的条目的游标值
    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }

}
