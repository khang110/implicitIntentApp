package com.example.implicitintent;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class NewsAdapter extends BaseAdapter {

    public NewsAdapter(Context context, int layout, List<News> newsList) {
        myContext = context;
        myLayout = layout;
        arrayNews = newsList;
    }

    Context myContext;
    int myLayout;
    List<News> arrayNews;

    @Override
    public int getCount() {
        return arrayNews.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) myContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        convertView = inflater.inflate(myLayout, null);

        TextView txtName = convertView.findViewById(R.id.tv_Name);
        txtName.setText(arrayNews.get(position).Name);

        TextView txtGrade = convertView.findViewById(R.id.tv_Grade);
        txtGrade.setText(arrayNews.get(position).Grade);

        TextView txtDate = convertView.findViewById(R.id.tv_Date);
        txtDate.setText(arrayNews.get(position).DatePost.toString());

        TextView txtPrice = convertView.findViewById(R.id.tv_Price);
        txtPrice.setText(arrayNews.get(position).Price);

        TextView txtStatus = convertView.findViewById(R.id.tv_Status);
        txtStatus.setText(arrayNews.get(position).Status);

        ImageView imgHinh = convertView.findViewById(R.id.imageViewHinh);
        imgHinh.setImageResource(arrayNews.get(position).Hinh);
        return convertView;
    }
}
