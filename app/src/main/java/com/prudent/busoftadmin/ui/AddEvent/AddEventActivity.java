package com.prudent.busoftadmin.ui.AddEvent;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.philliphsu.bottomsheetpickers.date.DatePickerDialog;
import com.philliphsu.bottomsheetpickers.time.BottomSheetTimePickerDialog;
import com.philliphsu.bottomsheetpickers.time.grid.GridTimePickerDialog;
import com.prudent.busoftadmin.R;
import com.prudent.busoftadmin.data.pref.SessionManager;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import petrov.kristiyan.colorpicker.ColorPicker;

public class AddEventActivity extends AppCompatActivity implements AddEventView {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.edt_add_event_title)
    EditText edtAddEventTitle;
    @BindView(R.id.textView_add_event_date)
    TextView textViewAddEventDate;
    @BindView(R.id.layout_add_event_date)
    LinearLayout layoutAddEventDate;
    @BindView(R.id.textView_add_event_starttime)
    TextView textViewAddEventStarttime;
    @BindView(R.id.textView_add_event_endtime)
    TextView textViewAddEventEndtime;
    @BindView(R.id.layout_add_event_time)
    LinearLayout layoutAddEventTime;
    @BindView(R.id.btn_color_picker)
    Button btnColorPicker;
    @BindView(R.id.btn_save_event)
    Button btnSaveEvent;
    private AddEventPresenter addEventPresenter;
    private ProgressDialog progressDialog;
    private SessionManager sessionManager;
    private String starttime, endtime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        sessionManager = new SessionManager(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initPresenter();
        onAttach();

        SetProgress();
    }

    private void ErrorDialog(String Title, String Message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(Title);
        builder.setCancelable(false);
        builder.setMessage(Message);
        builder.setPositiveButton("RETRY",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,
                                        int which) {

                    }
                });
        builder.show();
    }

    private void initPresenter() {
        addEventPresenter = new AddEventPresenter();
    }

    @Override
    public void onAttach() {
        addEventPresenter.onAttach(this);
    }

    @Override
    public void onDetach() {
        addEventPresenter.onDetach();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        onDetach();
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    private void SetProgress() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Loading");
        progressDialog.setMessage("Please Wait...");
        progressDialog.setCancelable(false);
    }

    @Override
    public void ShowProgress() {
        progressDialog.show();
    }

    @Override
    public void HideProgress() {
        progressDialog.dismiss();
    }

    @Override
    public void Error(String Title, String Message) {
        ErrorDialog(Title, Message);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @OnClick({R.id.textView_add_event_date, R.id.textView_add_event_starttime, R.id.textView_add_event_endtime, R.id.btn_color_picker, R.id.btn_save_event})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.textView_add_event_date:
                SetDate();
                break;
            case R.id.textView_add_event_starttime:
                SetStartTime();
                break;
            case R.id.textView_add_event_endtime:
                SetEndTime();
                break;
            case R.id.btn_color_picker:
                ChooseColor();
                break;
            case R.id.btn_save_event:
                break;
        }
    }

    private void ChooseColor() {
        final ColorPicker colorPicker = new ColorPicker(AddEventActivity.this);
        ArrayList<String> colors = new ArrayList<>();
        colors.add("#e53935");
        colors.add("#D81B60");
        colors.add("#8E24AA");
        colors.add("#5E35B1");
        colors.add("#3949AB");
        colors.add("#1E88E5");
        colors.add("#039BE5");
        colors.add("#00ACC1");
        colors.add("#00897B");
        colors.add("#43A047");
        colors.add("#7CB342");
        colors.add("#FB8C00");
        colors.add("#F4511E");
        colors.add("#6D4C41");
        colors.add("#546E7A");
        colors.add("#000000");
        colorPicker.setColors(colors)
                .setDefaultColorButton(Color.parseColor("#f84c44"))
                .setColumns(4)
                .setOnChooseColorListener(new ColorPicker.OnChooseColorListener() {
                    @Override
                    public void onChooseColor(int position, int color) {
                        // will be fired only when OK button was tapped
                        int red = Color.red(color);
                        int green = Color.green(color);
                        int blue = Color.blue(color);
                        Log.e("rgb", "" + red + " " + green + " " + blue);
                    }

                    @Override
                    public void onCancel() {

                    }
                }).setRoundColorButton(true).show();
    }

    private void SetDate() {
        Calendar now = Calendar.getInstance();
        DatePickerDialog date = new DatePickerDialog.Builder(
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePickerDialog dialog, int year, int monthOfYear, int dayOfMonth) {
                        String time = year+"-"+monthOfYear+"-"+dayOfMonth;
                        textViewAddEventDate.setTag(time);
                        try {
                            final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                            final Date dateObj = sdf.parse(time);
                            String starttime = new SimpleDateFormat("dd MMM, yyyy").format(dateObj);
                            textViewAddEventDate.setText(starttime);
                        } catch (final ParseException e) {
                            e.printStackTrace();
                        }
                    }
                },
                now.get(Calendar.YEAR),
                now.get(Calendar.MONTH),
                now.get(Calendar.DAY_OF_MONTH))
                .build();
        date.show(getSupportFragmentManager(), "");
    }

    private void SetStartTime() {
        Calendar now = Calendar.getInstance();
        GridTimePickerDialog grid = new GridTimePickerDialog.Builder(
                new BottomSheetTimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(ViewGroup viewGroup, int hourOfDay, int minute) {
                        Log.e("hour", "" + hourOfDay + " " + minute);
                        String time = hourOfDay+":"+minute;
                        textViewAddEventStarttime.setTag(time);
                        try {
                            final SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
                            final Date dateObj = sdf.parse(time);
                            String starttime = new SimpleDateFormat("hh:mm a").format(dateObj);
                            textViewAddEventStarttime.setText(starttime);
                        } catch (final ParseException e) {
                            e.printStackTrace();
                        }
                    }
                },
                now.get(Calendar.HOUR_OF_DAY),
                now.get(Calendar.MINUTE),
                DateFormat.is24HourFormat(AddEventActivity.this))
                .build();
        grid.show(getSupportFragmentManager(), "");
    }

    private void SetEndTime() {
        Calendar now = Calendar.getInstance();
        GridTimePickerDialog grid = new GridTimePickerDialog.Builder(
                new BottomSheetTimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(ViewGroup viewGroup, int hourOfDay, int minute) {
                        Log.e("hour", "" + hourOfDay + " " + minute);
                        String time = hourOfDay+":"+minute;
                        textViewAddEventEndtime.setTag(time);
                        try {
                            final SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
                            final Date dateObj = sdf.parse(time);
                            String starttime = new SimpleDateFormat("hh:mm a").format(dateObj);
                            textViewAddEventEndtime.setText(starttime);
                        } catch (final ParseException e) {
                            e.printStackTrace();
                        }
                    }
                },
                now.get(Calendar.HOUR_OF_DAY),
                now.get(Calendar.MINUTE),
                DateFormat.is24HourFormat(AddEventActivity.this))
                .build();
        grid.show(getSupportFragmentManager(), "");
    }
}
