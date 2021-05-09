package com.example.implicitintent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    ListView lvNews;
    ArrayList<News> arrayNews;
    Button btnUpNews;

    private static final String DATE_FORMAT = "dd/MM/yyyy";
    private static final String TIME_FORMAT_24 = "HH:mm";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        lvNews = findViewById(R.id.listViewNews);
        arrayNews = new ArrayList<News>();
        btnUpNews = findViewById(R.id.btn_UpNews);

        Date currentTime = Calendar.getInstance().getTime();
        String date = currentTime.toString();
        String timeNow, dateNow;
        timeNow = getTime24String(currentTime);
        dateNow = getDateString(currentTime);



        arrayNews.add(new News("Có ít sách vỡ cũ", "Sách lớp 10", timeNow + ", " + dateNow,"Miễn phí", "Đang hiển thị", R.drawable.book));
        arrayNews.add(new News("Có ít đồ cũ", "Đồ trẻ em nam", timeNow + ", " + dateNow,"Miễn phí", "Đang hiển thị", R.drawable.phu));
        arrayNews.add(new News("Giáo trình bác sĩ đa khoa năm 3", "Dh y dược", timeNow + ", " + dateNow,"100.000đ", "Đang hiển thị", R.drawable.book));
        arrayNews.add(new News("Có ít sách vỡ cũ", "Sách lớp 10", timeNow + ", " + dateNow,"Miễn phí", "Đang hiển thị", R.drawable.book));
        arrayNews.add(new News("Có ít sách vỡ cũ", "Sách lớp 10", timeNow + ", " + dateNow,"Miễn phí", "Đang hiển thị", R.drawable.book));
        arrayNews.add(new News("Có ít sách vỡ cũ", "Sách lớp 10", timeNow + ", " + dateNow,"Miễn phí", "Đang hiển thị", R.drawable.book));

        NewsAdapter adapter = new NewsAdapter(
                MainActivity.this,
                R.layout.line_news,
                arrayNews
        );

        lvNews.setAdapter(adapter);

        btnUpNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, xacnhanhdiachi.class);
                startActivity(intent);
            }
        });

        lvNews.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(MainActivity.this, CallPermission.class);
                startActivity(intent);
            }
        });

    }
    public static String getTime24String(Date date) {
        SimpleDateFormat format = new SimpleDateFormat(TIME_FORMAT_24);
        return format.format(date);}
    public static String getDateString(Date date) {
        SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
        return format.format(date);}

}