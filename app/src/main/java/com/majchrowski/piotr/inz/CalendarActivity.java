package com.majchrowski.piotr.inz;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.FrameLayout;
import android.widget.Toast;


import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.Event;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CalendarActivity extends AppCompatActivity {

    static CompactCalendarView calendar;
    FrameLayout frameLayout;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    Toolbar toolBar;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        frameLayout = findViewById(R.id.frameLayout);





        calendar = (CompactCalendarView) findViewById(R.id.compactcalendar_view);
       calendar.setUseThreeLetterAbbreviation(true);

        toolBar = (Toolbar) findViewById(R.id.toolbarCalendar);

        toolBar.setTitle(R.string.this_month);
        setSupportActionBar(toolBar);

        DrawerUtil.getDrawer(this,toolBar);




        calendar.setListener(new CompactCalendarView.CompactCalendarViewListener() {
            @Override
            public void onDayClick(Date dateClicked) {
                String stringDate = dateFormat.format(dateClicked);
                Context context = getApplicationContext();
                DailyFragment fragment = new DailyFragment();
                Bundle args = new Bundle();
                args.putString("date", stringDate);
                fragment.setArguments(args);
                getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, fragment).commit();

            }

            @Override
            public void onMonthScroll(Date firstDayOfNewMonth) {
                toolBar.setTitle(dateFormat.format(firstDayOfNewMonth));

            }
        });



    }

    public void AddEvent(Event event){
        CompactCalendarView calendar = (CompactCalendarView) findViewById(R.id.compactcalendar_view);
        calendar.setUseThreeLetterAbbreviation(true);
        calendar.addEvent(event);

    }


}
