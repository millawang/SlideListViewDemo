package com.fia.mia.slidelistviewdemo;

/**
 * Created by milla.wang on 15/10/6.
 */
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.fia.mia.slidelistviewdemo.data.MyDemoData;

import java.util.ArrayList;

public class MySlideAdapter extends BaseAdapter {

    private Context context;
    private LayoutInflater li;
    private int last_position = -1;
    private boolean isInit = true;

    // 清單的資料，常用一個可變動的陣列或是集合來儲存，在此以ArrayList<E>為例
    private ArrayList<MyDemoData> array;


    public MySlideAdapter(Context context, ArrayList<MyDemoData> array) {
        this.context = context;
        this.array = array;
        this.li = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return array.size();
    }

    @Override
    public Object getItem(int position) {
        return array.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int pos, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = li.inflate(R.layout.list_item, parent, false);
            viewHolder.title = (TextView) convertView.findViewById(R.id.item_title);
            viewHolder.description = (TextView) convertView.findViewById(R.id.item_description);
            viewHolder.cover = (ImageView) convertView.findViewById(R.id.item_cover);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        MyDemoData data = (MyDemoData) getItem(pos);
        viewHolder.title.setText(data.getTitle());
        viewHolder.description.setText(data.getContent());

        final View view = convertView;
        if (pos > last_position) {
            last_position = pos;
            Animation animation = new TranslateAnimation(
                    Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 0.0f,
                    Animation.RELATIVE_TO_SELF, 1.0f, Animation.RELATIVE_TO_SELF, 0.0f);

            animation.setDuration(350);
            animation.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                    if (last_position > pos && !isInit)
                        view.clearAnimation();
                }

                @Override
                public void onAnimationRepeat(Animation animation) {
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                }
            });
            convertView.startAnimation(animation);
        } else {
            convertView.clearAnimation();
        }

        return convertView;
    }


    static class ViewHolder {
        TextView title;
        TextView description;
        ImageView cover;
    }
}

