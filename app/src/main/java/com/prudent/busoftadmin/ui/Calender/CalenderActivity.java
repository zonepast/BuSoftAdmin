package com.prudent.busoftadmin.ui.Calender;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.RectF;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.alamkanak.weekview.DateTimeInterpreter;
import com.alamkanak.weekview.MonthLoader;
import com.alamkanak.weekview.WeekView;
import com.alamkanak.weekview.WeekViewEvent;
import com.prudent.busoftadmin.R;
import com.prudent.busoftadmin.adapter.EventAdapater;
import com.prudent.busoftadmin.adapter.YearAdapter;
import com.prudent.busoftadmin.data.CalenderDate;
import com.prudent.busoftadmin.data.api.model.Calender.Response.CalenderResponse;
import com.prudent.busoftadmin.data.pref.SessionManager;
import com.prudent.busoftadmin.ui.AddEvent.AddEventActivity;
import com.prudent.busoftadmin.utils.DividerItemDecoration;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CalenderActivity extends AppCompatActivity implements CalenderView, WeekView.EventClickListener, MonthLoader.MonthChangeListener, WeekView.EventLongPressListener {

    @BindView(R.id.calender_spin_year)
    Spinner calenderSpinYear;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.weekView)
    WeekView weekView;
    @BindView(R.id.recyclerView_event_list)
    RecyclerView recyclerViewEventList;
    @BindView(R.id.layout_event_list_title)
    LinearLayout txtEventListTitle;
    @BindView(R.id.add_event)
    FloatingActionButton addEvent;
    @BindView(R.id.design_bottom_sheet)
    LinearLayout designBottomSheet;

    private CalenderPresenter calenderPresenter;
    private ProgressDialog progressDialog;
    private SessionManager sessionManager;
    List<WeekViewEvent> events = new ArrayList<WeekViewEvent>();
    private static final int TYPE_DAY_VIEW = 1;
    private static final int TYPE_THREE_DAY_VIEW = 2;
    private static final int TYPE_WEEK_VIEW = 3;
    private int mWeekViewType = TYPE_THREE_DAY_VIEW;
    private String yearId, yearName;
    BottomSheetBehavior behavior;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calender);
        ButterKnife.bind(this);

        sessionManager = new SessionManager(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initPresenter();
        onAttach();

        SetProgress();
        ShowEventList();

        SetupCalender();

        SetYearSpinner();
    }

    private List<CalenderDate> GetYear() {
        List<CalenderDate> calenderDateList = new ArrayList<>();
        calenderDateList.add(new CalenderDate("01-01-2012", "2012"));
        calenderDateList.add(new CalenderDate("01-01-2013", "2013"));
        calenderDateList.add(new CalenderDate("01-01-2014", "2014"));
        calenderDateList.add(new CalenderDate("01-01-2015", "2015"));
        calenderDateList.add(new CalenderDate("01-01-2016", "2016"));
        calenderDateList.add(new CalenderDate("01-01-2017", "2017"));
        return calenderDateList;
    }

    private void SetYearSpinner() {
        YearAdapter spinAdapter = new YearAdapter(
                getApplicationContext(), GetYear());
        calenderSpinYear.setAdapter(spinAdapter);
        calenderSpinYear.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapter, View v,
                                       int position, long id) {
                yearName = ((TextView) v.findViewById(R.id.tvCategory)).getText().toString();
                yearId = ((TextView) v.findViewById(R.id.tvCategory)).getTag().toString();
                LoadCalender();
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });
    }

    private void DeleteEvent(long id) {
        LayoutInflater inflater = LayoutInflater.from(getApplicationContext());
        View dialogLayout = inflater.inflate(R.layout.layout_delete_calender, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Are you Sure You want to Delete Event?");
        builder.setView(dialogLayout);
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub
                dialog.dismiss();
            }
        });
        builder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub

            }
        });
        AlertDialog customAlertDialog = builder.create();
        customAlertDialog.show();
    }

    private void LoadCalender() {
        if (events.size() > 0) {
            events.clear();
        }
        calenderPresenter.Calender(sessionManager.getCorpCode(), sessionManager.getUserCode(), "1",
                yearId);
    }

    private void SetupCalender() {
        weekView.setOnEventClickListener(this);
        weekView.setMonthChangeListener(this);
        weekView.setEventLongPressListener(this);
        setupDateTimeInterpreter(false);
    }

    private void setupDateTimeInterpreter(final boolean shortDate) {
        weekView.setDateTimeInterpreter(new DateTimeInterpreter() {
            @Override
            public String interpretDate(Calendar date) {
                SimpleDateFormat weekdayNameFormat = new SimpleDateFormat("EEE", Locale.getDefault());
                String weekday = weekdayNameFormat.format(date.getTime());
                SimpleDateFormat format = new SimpleDateFormat(" M/d", Locale.getDefault());

                if (shortDate)
                    weekday = String.valueOf(weekday.charAt(0));
                return weekday.toUpperCase() + format.format(date.getTime());
            }

            @Override
            public String interpretTime(int hour) {
                return hour > 11 ? (hour - 12) + " PM" : (hour == 0 ? "12 AM" : hour + " AM");
            }
        });
    }

    public WeekView getWeekView() {
        return weekView;
    }

    private boolean eventMatches(WeekViewEvent event, int year, int month) {
        return (event.getStartTime().get(Calendar.YEAR) == year && event.getStartTime().get(Calendar.MONTH) == month - 1) || (event.getEndTime().get(Calendar.YEAR) == year && event.getEndTime().get(Calendar.MONTH) == month - 1);
    }

    private void SaveEvent(CalenderResponse response) {

        for (int i = 0; i < response.getTable().size(); i++) {

            long id = response.getTable().get(i).getId();
            String name = response.getTable().get(i).getName();
            String StartTime = response.getTable().get(i).getStartTime();
            String EndTime = response.getTable().get(i).getEndTime();
            String date = response.getTable().get(i).getFromDate();
            String color = response.getTable().get(i).getColor();

            String Date2 = date.replaceAll("T", " ");
            SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            Date dt1 = null;
            try {
                dt1 = format1.parse(Date2);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
            Date start = new Date();
            Date end = new Date();
            try {
                start = sdf.parse(StartTime);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            try {
                end = sdf.parse(EndTime);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            Calendar cal = Calendar.getInstance();
            cal.setTime(dt1);
            int month = cal.get(Calendar.MONTH);
            int day = cal.get(Calendar.DAY_OF_MONTH);
            int year = cal.get(Calendar.YEAR);
            // Initialize start and end time.
            Calendar now = Calendar.getInstance();
            Calendar startTime = (Calendar) now.clone();
            startTime.setTimeInMillis(start.getTime());
            // startTime.setTime(dt1);
            //Log.e("Date",""+Integer.parseInt(finalDay));
            startTime.set(Calendar.YEAR, year);
            startTime.set(Calendar.MONTH, month);
            startTime.set(Calendar.DAY_OF_MONTH, day);

            Calendar endTime = (Calendar) startTime.clone();
            endTime.setTimeInMillis(end.getTime());
            //endTime.setTime(dt1);
            endTime.set(Calendar.YEAR, year);
            endTime.set(Calendar.MONTH, month);
            endTime.set(Calendar.DAY_OF_MONTH, day);

            String[] colorList = color.split(", ");
            int r = Integer.parseInt(colorList[0]);
            int g = Integer.parseInt(colorList[1]);
            int b = Integer.parseInt(colorList[1]);
            // Create an week view event.
            WeekViewEvent weekViewEvent = new WeekViewEvent();
            weekViewEvent.setId(id);
            weekViewEvent.setName(name);
            weekViewEvent.setStartTime(startTime);
            weekViewEvent.setEndTime(endTime);
            weekViewEvent.setColor(Color.rgb(r, g, b));

            events.add(weekViewEvent);

        }

        getWeekView().notifyDatasetChanged();
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
        calenderPresenter = new CalenderPresenter();
    }

    @Override
    public void onAttach() {
        calenderPresenter.onAttach(this);
    }

    @Override
    public void onDetach() {
        calenderPresenter.onDetach();
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
    public void Error(String Title, String Message) {
        ErrorDialog(Title, Message);
    }

    @Override
    public void CalenderResponse(CalenderResponse response) {
        if (response.getTable().size() == 0) {

        } else {
            SaveEvent(response);
            EventAdapater eventAdapater = new EventAdapater(response.getTable(), this);
            recyclerViewEventList.setAdapter(eventAdapater);
        }
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
    public void onEventClick(WeekViewEvent event, RectF eventRect) {
        Toast.makeText(getApplicationContext(), event.getName(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public List<? extends WeekViewEvent> onMonthChange(int newYear, int newMonth) {
        List<WeekViewEvent> matchedEvents = new ArrayList<WeekViewEvent>();
        for (WeekViewEvent event : events) {
            if (eventMatches(event, newYear, newMonth)) {
                matchedEvents.add(event);
            }
        }
        return matchedEvents;
    }

    @Override
    public void onEventLongPress(WeekViewEvent event, RectF eventRect) {
        long id = event.getId();
        DeleteEvent(id);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_calender, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        setupDateTimeInterpreter(id == R.id.action_week_view);
        switch (id) {
            case android.R.id.home:
                onBackPressed();
                return true;
            case R.id.action_today:
                weekView.goToToday();
                return true;
            case R.id.action_day_view:
                if (mWeekViewType != TYPE_DAY_VIEW) {
                    item.setChecked(!item.isChecked());
                    mWeekViewType = TYPE_DAY_VIEW;
                    weekView.setNumberOfVisibleDays(1);

                    // Lets change some dimensions to best fit the view.
                    weekView.setColumnGap((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8, getResources().getDisplayMetrics()));
                    weekView.setTextSize((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 12, getResources().getDisplayMetrics()));
                    weekView.setEventTextSize((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 12, getResources().getDisplayMetrics()));
                }
                return true;
            case R.id.action_three_day_view:
                if (mWeekViewType != TYPE_THREE_DAY_VIEW) {
                    item.setChecked(!item.isChecked());
                    mWeekViewType = TYPE_THREE_DAY_VIEW;
                    weekView.setNumberOfVisibleDays(3);

                    // Lets change some dimensions to best fit the view.
                    weekView.setColumnGap((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8, getResources().getDisplayMetrics()));
                    weekView.setTextSize((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 12, getResources().getDisplayMetrics()));
                    weekView.setEventTextSize((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 12, getResources().getDisplayMetrics()));
                }
                return true;
            case R.id.action_week_view:
                if (mWeekViewType != TYPE_WEEK_VIEW) {
                    item.setChecked(!item.isChecked());
                    mWeekViewType = TYPE_WEEK_VIEW;
                    weekView.setNumberOfVisibleDays(7);

                    // Lets change some dimensions to best fit the view.
                    weekView.setColumnGap((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 2, getResources().getDisplayMetrics()));
                    weekView.setTextSize((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 10, getResources().getDisplayMetrics()));
                    weekView.setEventTextSize((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 10, getResources().getDisplayMetrics()));
                }
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void ShowEventList() {
        SetUpRecyclerView();
        View bottomSheet = findViewById(R.id.design_bottom_sheet);
        behavior = BottomSheetBehavior.from(bottomSheet);
        behavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                switch (newState) {
                    case BottomSheetBehavior.STATE_DRAGGING:
                        addEvent.hide();
                        Log.i("BottomSheetCallback", "BottomSheetBehavior.STATE_DRAGGING");
                        break;
                    case BottomSheetBehavior.STATE_SETTLING:
                        Log.i("BottomSheetCallback", "BottomSheetBehavior.STATE_SETTLING");
                        break;
                    case BottomSheetBehavior.STATE_EXPANDED:
                        addEvent.hide();
                        Log.i("BottomSheetCallback", "BottomSheetBehavior.STATE_EXPANDED");
                        break;
                    case BottomSheetBehavior.STATE_COLLAPSED:
                        addEvent.show();
                        Log.i("BottomSheetCallback", "BottomSheetBehavior.STATE_COLLAPSED");
                        break;
                    case BottomSheetBehavior.STATE_HIDDEN:
                        Log.i("BottomSheetCallback", "BottomSheetBehavior.STATE_HIDDEN");
                        break;
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
                Log.i("BottomSheetCallback", "slideOffset: " + slideOffset);
            }
        });
    }

    private void SetUpRecyclerView() {
        LinearLayoutManager gridLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerViewEventList.setLayoutManager(gridLayoutManager);
        recyclerViewEventList.setHasFixedSize(true);
        recyclerViewEventList.addItemDecoration(new DividerItemDecoration(this));
        setUpItemTouchHelper();
        setUpAnimationDecoratorHelper();
    }

    private void setUpItemTouchHelper() {

        ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {

            // we want to cache these and not allocate anything repeatedly in the onChildDraw method
            Drawable background;
            Drawable xMark;
            int xMarkMargin;
            boolean initiated;

            private void init() {
                background = new ColorDrawable(Color.RED);
                xMark = ContextCompat.getDrawable(CalenderActivity.this, R.drawable.ic_delete);
                xMark.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP);
                xMarkMargin = (int) CalenderActivity.this.getResources().getDimension(R.dimen.delete);
                initiated = true;
            }

            // not important, we don't want drag & drop
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public int getSwipeDirs(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                int position = viewHolder.getAdapterPosition();
                /*TestAdapter testAdapter = (TestAdapter)recyclerView.getAdapter();
                if (testAdapter.isUndoOn() && testAdapter.isPendingRemoval(position)) {
                    return 0;
                }*/
                //DeleteEvent(position);
                return super.getSwipeDirs(recyclerView, viewHolder);
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {
                int position = viewHolder.getAdapterPosition();
                /*TestAdapter adapter = (TestAdapter)mRecyclerView.getAdapter();
                boolean undoOn = adapter.isUndoOn();
                if (undoOn) {
                    adapter.pendingRemoval(swipedPosition);
                } else {
                    adapter.remove(swipedPosition);
                }*/
                DeleteEvent(position);
            }

            @Override
            public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
                View itemView = viewHolder.itemView;

                // not sure why, but this method get's called for viewholder that are already swiped away
                if (viewHolder.getAdapterPosition() == -1) {
                    // not interested in those
                    return;
                }

                if (!initiated) {
                    init();
                }

                // draw red background
                background.setBounds(itemView.getRight() + (int) dX, itemView.getTop(), itemView.getRight(), itemView.getBottom());
                background.draw(c);

                // draw x mark
                int itemHeight = itemView.getBottom() - itemView.getTop();
                int intrinsicWidth = xMark.getIntrinsicWidth();
                int intrinsicHeight = xMark.getIntrinsicWidth();

                int xMarkLeft = itemView.getRight() - xMarkMargin - intrinsicWidth;
                int xMarkRight = itemView.getRight() - xMarkMargin;
                int xMarkTop = itemView.getTop() + (itemHeight - intrinsicHeight) / 2;
                int xMarkBottom = xMarkTop + intrinsicHeight;
                xMark.setBounds(xMarkLeft, xMarkTop, xMarkRight, xMarkBottom);

                xMark.draw(c);

                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
            }

        };
        ItemTouchHelper mItemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        mItemTouchHelper.attachToRecyclerView(recyclerViewEventList);
    }

    /**
     * We're gonna setup another ItemDecorator that will draw the red background in the empty space while the items are animating to thier new positions
     * after an item is removed.
     */
    private void setUpAnimationDecoratorHelper() {
        recyclerViewEventList.addItemDecoration(new RecyclerView.ItemDecoration() {

            // we want to cache this and not allocate anything repeatedly in the onDraw method
            Drawable background;
            boolean initiated;

            private void init() {
                background = new ColorDrawable(Color.RED);
                initiated = true;
            }

            @Override
            public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {

                if (!initiated) {
                    init();
                }

                // only if animation is in progress
                if (parent.getItemAnimator().isRunning()) {

                    // some items might be animating down and some items might be animating up to close the gap left by the removed item
                    // this is not exclusive, both movement can be happening at the same time
                    // to reproduce this leave just enough items so the first one and the last one would be just a little off screen
                    // then remove one from the middle

                    // find first child with translationY > 0
                    // and last one with translationY < 0
                    // we're after a rect that is not covered in recycler-view views at this point in time
                    View lastViewComingDown = null;
                    View firstViewComingUp = null;

                    // this is fixed
                    int left = 0;
                    int right = parent.getWidth();

                    // this we need to find out
                    int top = 0;
                    int bottom = 0;

                    // find relevant translating views
                    int childCount = parent.getLayoutManager().getChildCount();
                    for (int i = 0; i < childCount; i++) {
                        View child = parent.getLayoutManager().getChildAt(i);
                        if (child.getTranslationY() < 0) {
                            // view is coming down
                            lastViewComingDown = child;
                        } else if (child.getTranslationY() > 0) {
                            // view is coming up
                            if (firstViewComingUp == null) {
                                firstViewComingUp = child;
                            }
                        }
                    }

                    if (lastViewComingDown != null && firstViewComingUp != null) {
                        // views are coming down AND going up to fill the void
                        top = lastViewComingDown.getBottom() + (int) lastViewComingDown.getTranslationY();
                        bottom = firstViewComingUp.getTop() + (int) firstViewComingUp.getTranslationY();
                    } else if (lastViewComingDown != null) {
                        // views are going down to fill the void
                        top = lastViewComingDown.getBottom() + (int) lastViewComingDown.getTranslationY();
                        bottom = lastViewComingDown.getBottom();
                    } else if (firstViewComingUp != null) {
                        // views are coming up to fill the void
                        top = firstViewComingUp.getTop();
                        bottom = firstViewComingUp.getTop() + (int) firstViewComingUp.getTranslationY();
                    }

                    background.setBounds(left, top, right, bottom);
                    background.draw(c);

                }
                super.onDraw(c, parent, state);
            }

        });
    }

    @OnClick({R.id.add_event, R.id.layout_event_list_title})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.add_event:
                startActivity(new Intent(getApplicationContext(), AddEventActivity.class));
                break;
            case R.id.layout_event_list_title:
                if (behavior.getState() == BottomSheetBehavior.STATE_COLLAPSED) {
                    behavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                } else {
                    behavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }
                break;
        }
    }
}
