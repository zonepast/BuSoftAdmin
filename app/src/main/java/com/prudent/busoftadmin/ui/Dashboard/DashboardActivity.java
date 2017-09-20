package com.prudent.busoftadmin.ui.Dashboard;

import android.app.Activity;
import android.app.ActivityOptions;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.BounceInterpolator;
import android.view.animation.ScaleAnimation;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.RadarChart;
import com.github.mikephil.charting.charts.ScatterChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.RadarData;
import com.github.mikephil.charting.data.RadarDataSet;
import com.github.mikephil.charting.data.ScatterData;
import com.github.mikephil.charting.data.ScatterDataSet;
import com.prudent.busoftadmin.Helper.Constant;
import com.prudent.busoftadmin.R;
import com.prudent.busoftadmin.adapter.CustomSpinnerAdapter;
import com.prudent.busoftadmin.adapter.DashboardGraphAdapter;
import com.prudent.busoftadmin.adapter.DashboardReport2Adapter;
import com.prudent.busoftadmin.adapter.DashboardReportAdapter;
import com.prudent.busoftadmin.adapter.DashboardStatementAdapter;
import com.prudent.busoftadmin.data.api.model.DashboardData.Response.DashboardDataResponse;
import com.prudent.busoftadmin.data.api.model.Logout.Response.LogoutResponse;
import com.prudent.busoftadmin.data.api.model.TranscationUploadDocument.Response.TranscationUploadDocumentResponse;
import com.prudent.busoftadmin.data.db.Local.Login.LoginRealm;
import com.prudent.busoftadmin.data.pref.SessionManager;
import com.prudent.busoftadmin.events.TranscationDocumentEvent;
import com.prudent.busoftadmin.ui.Calender.CalenderActivity;
import com.prudent.busoftadmin.ui.ChequeReceipt.ChequeActivity;
import com.prudent.busoftadmin.ui.Report2Detail.Report2Activity;
import com.prudent.busoftadmin.ui.UploadDocument.UploadDocumentActivity;
import com.prudent.busoftadmin.utils.DividerItemDecoration;
import com.prudent.busoftadmin.utils.MyMarkerView;
import com.prudent.busoftadmin.utils.PaddingItemDecoration;
import com.prudent.busoftadmin.utils.RecyclerGallery;
import com.prudent.busoftadmin.utils.ToggleAnimation;
import com.wajahatkarim3.easyflipview.EasyFlipView;

import net.cachapa.expandablelayout.ExpandableLayout;

import org.greenrobot.eventbus.EventBus;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;
import io.realm.RealmResults;

public class DashboardActivity extends AppCompatActivity implements DashboardView,DashboardReport2Adapter.Report2Adapter {

    @BindView(R.id.dashboard_spin_branch)
    Spinner dashboardSpinBranch;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.txt_dashboard)
    TextView txtDashboard;
    @BindView(R.id.btn_upload_document)
    Button btnUploadDocument;
    @BindView(R.id.progressBar_dashboard)
    ProgressBar progressBarDashboard;
    @BindView(R.id.layout_dashboard_progressBar)
    LinearLayout layoutDashboardProgressBar;
    @BindView(R.id.cardView_graph)
    CardView cardViewGraph;
    @BindView(R.id.recyclerview_dashboard_reports)
    RecyclerGallery recyclerviewDashboardReports;
    @BindView(R.id.layout_dashboard)
    LinearLayout layoutDashboard;
    @BindView(R.id.layout_dashboard_main)
    LinearLayout layoutDashboardMain;
    @BindView(R.id.txt_dashboard_statement_title)
    TextView txtDashboardStatementTitle;
    @BindView(R.id.recyclerview_dashboard_statement)
    RecyclerView recyclerviewDashboardStatement;
    @BindView(R.id.layout_dashboard_statement)
    LinearLayout layoutDashboardStatement;
    @BindView(R.id.cardView_statement)
    CardView cardViewStatement;
    @BindView(R.id.navigation_viewuser)
    NavigationView navigationViewuser;
    @BindView(R.id.draweruser)
    DrawerLayout draweruser;
    @BindView(R.id.expandableLayout_statement)
    ExpandableLayout expandableLayoutStatement;
    @BindView(R.id.txt_dashboard_graph)
    TextView txtDashboardGraph;
    @BindView(R.id.chart_dashboard_main)
    LineChart chartDashboardMain;
    @BindView(R.id.barchart_dashboard_main)
    BarChart barchartDashboardMain;
    @BindView(R.id.radarchart_dashboard_main)
    RadarChart radarchartDashboardMain;
    @BindView(R.id.scatterchart_dashboard_main)
    ScatterChart scatterchartDashboardMain;
    @BindView(R.id.txt_chart_type)
    TextView txtChartType;
    @BindView(R.id.icon_switch_report)
    ToggleButton iconSwitchReport;
    @BindView(R.id.recyclerview_dashboard_report_graph)
    RecyclerView recyclerviewDashboardReportGraph;
    @BindView(R.id.flip_layout_report_dashboard)
    EasyFlipView flipLayoutReportDashboard;
    @BindView(R.id.x_text_dashborad_report)
    TextView xTextDashboradReport;
    @BindView(R.id.y_text_dashborad_report)
    TextView yTextDashboradReport;
    @BindView(R.id.z_text_dashborad_report)
    TextView zTextDashboradReport;
    @BindView(R.id.a_text_dashborad_report)
    TextView aTextDashboradReport;
    @BindView(R.id.txt_dashboard_report2_title)
    TextView txtDashboardReport2Title;
    @BindView(R.id.recyclerView_dashboard_report2)
    RecyclerView recyclerViewDashboardReport2;
    @BindView(R.id.expandableLayout_report2)
    ExpandableLayout expandableLayoutReport2;
    @BindView(R.id.layout_dashboard_report2)
    LinearLayout layoutDashboardReport2;
    @BindView(R.id.cardView_report2)
    CardView cardViewReport2;

    private Realm realm;
    private SessionManager sessionManager;
    private ProgressDialog progressDialog;
    private DashboardPresenter dashboardPresenter;
    String BranchXname, BranchXcode;
    Boolean doubleBackToExitPressedOnce = false;
    DrawerLayout drawerLayout;

    private String[] mActivities;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        ButterKnife.bind(this);

        sessionManager = new SessionManager(this);
        realm = Realm.getDefaultInstance();

        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }

        initPresenter();
        onAttach();

        SetProgress();
        SetUpRecyclerView();
        SetBranchSpinner();
        LoadDashboard();
        initUserNavigationDrawer();

        flipLayoutReportDashboard.flipTheView();

        iconSwitchReport.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                compoundButton.startAnimation(new ToggleAnimation().GetToggleAnimation());
                flipLayoutReportDashboard.flipTheView();
            }
        });

    }

    // Navigation Drawer Method
    public void initUserNavigationDrawer() {
        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_viewuser);
        View header = navigationView.getHeaderView(0);
        TextView txtCorpname = (TextView) header.findViewById(R.id.txt_corpname);
        TextView txtUsername = (TextView) header.findViewById(R.id.txt_username);
        ImageButton btn_logout = (ImageButton) header.findViewById(R.id.btn_logout);
        txtCorpname.setText(sessionManager.getCorpName());
        txtUsername.setText(sessionManager.getUserName());
        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Logout();
            }
        });
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                int id = menuItem.getItemId();
                switch (id) {
                    case R.id.menu_upload_document:
                        drawerLayout.closeDrawers();
                        dashboardPresenter.LoadTransaction("Account", sessionManager.getCorpCode(), sessionManager.getUserCode(),
                                "Base");
                        break;
                    case R.id.menu_cheque_receipt:
                        drawerLayout.closeDrawers();
                        Intent i = new Intent(getApplicationContext(), ChequeActivity.class);
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            startActivity(i,
                                    ActivityOptions.makeSceneTransitionAnimation((Activity) DashboardActivity.this).toBundle());
                        } else {
                            startActivity(i);
                        }
                        break;
                }
                return true;
            }
        });

        drawerLayout = (DrawerLayout) findViewById(R.id.draweruser);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.userdrawer_open, R.string.userdrawer_close) {

            @Override
            public void onDrawerClosed(View v) {
                super.onDrawerClosed(v);
            }

            @Override
            public void onDrawerOpened(View v) {
                super.onDrawerOpened(v);
            }
        };
        actionBarDrawerToggle.syncState();
    }

    private void SetUpRecyclerView() {
        new LinearSnapHelper().attachToRecyclerView(recyclerviewDashboardReports);
        recyclerviewDashboardReports.setHasFixedSize(true);
        recyclerviewDashboardReports.setNestedScrollingEnabled(false);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerviewDashboardReports.setLayoutManager(linearLayoutManager);
        int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.statement);
        recyclerviewDashboardReports.addItemDecoration(new PaddingItemDecoration(this, spacingInPixels, spacingInPixels));
        // Statement RecyclerView
        LinearLayoutManager gridLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerviewDashboardStatement.setLayoutManager(gridLayoutManager);
        recyclerviewDashboardStatement.setHasFixedSize(true);
        recyclerviewDashboardStatement.setNestedScrollingEnabled(false);
        recyclerviewDashboardStatement.addItemDecoration(new DividerItemDecoration(this));
        // Report RecyclerView
        LinearLayoutManager LayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerviewDashboardReportGraph.setLayoutManager(LayoutManager);
        recyclerviewDashboardReportGraph.setHasFixedSize(true);
        recyclerviewDashboardReportGraph.addItemDecoration(new DividerItemDecoration(this));
        recyclerviewDashboardReportGraph.setNestedScrollingEnabled(false);
        // Report2 RecyclerView
        LinearLayoutManager LayoutManagerh = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewDashboardReport2.setLayoutManager(LayoutManagerh);
        recyclerViewDashboardReport2.setHasFixedSize(true);
        int spacingInPixels1 = getResources().getDimensionPixelSize(R.dimen.report2);
        recyclerViewDashboardReport2.addItemDecoration(new PaddingItemDecoration(this, spacingInPixels1, spacingInPixels1));
        //recyclerViewDashboardReport2.addItemDecoration(new DividerItemDecoration(this));
        recyclerViewDashboardReport2.setNestedScrollingEnabled(false);
    }

    private void SetBranchSpinner() {
        CustomSpinnerAdapter spinAdapter = new CustomSpinnerAdapter(
                getApplicationContext(), GetBranch());
        dashboardSpinBranch.setAdapter(spinAdapter);
        dashboardSpinBranch.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapter, View v,
                                       int position, long id) {
                BranchXname = ((TextView) v.findViewById(R.id.tvCategory)).getText().toString();
                BranchXcode = ((TextView) v.findViewById(R.id.tvCategory)).getTag().toString();
                sessionManager.SaveBranch(BranchXname, BranchXcode);
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });
    }

    private RealmResults<LoginRealm> GetBranch() {
        return realm.where(LoginRealm.class)
                .equalTo("xmaster", "UNIT_CORP")
                .findAll();
    }


    private void initPresenter() {
        dashboardPresenter = new DashboardPresenter();
    }

    @Override
    public void onAttach() {
        dashboardPresenter.onAttach(this);
    }

    @Override
    public void onDetach() {
        dashboardPresenter.onDetach();
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
        realm.close();
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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_dashboard, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.menu_calender:
                startActivity(new Intent(getApplicationContext(), CalenderActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);//***Change Here***
            startActivity(intent);
            finish();
            System.exit(0);
            return;
        }
        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 2000);
    }

    private void SetProgress() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please Wait...");
        progressDialog.setTitle("Loading...");
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
    public void ShowProgressBar() {
        layoutDashboardProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void HideProgressBar() {
        layoutDashboardProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void Error(String Title, String Message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(Title);
        builder.setCancelable(false);
        builder.setMessage(Message);
        builder.setNegativeButton("OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,
                                        int which) {
                        onBackPressed();
                    }
                });
        builder.setPositiveButton("RETRY",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,
                                        int which) {
                        LoadDashboard();
                    }
                });
        builder.show();
    }

    @Override
    public void ShowTransactionResponse(TranscationUploadDocumentResponse response) {
        if (response.getTable().size() == 0) {

        } else {
            EventBus.getDefault().postSticky(new TranscationDocumentEvent(response.getTable()));
            startActivity(new Intent(getApplicationContext(), UploadDocumentActivity.class));
        }
    }

    @Override
    public void LogoutResponse(LogoutResponse response) {
        if (response.getTable().get(0).getSuccess() == 1) {
            Toast.makeText(getApplicationContext(), response.getTable().get(0).getMessage(), Toast.LENGTH_LONG).show();
            sessionManager.logoutUser();
        } else {
            Toast.makeText(getApplicationContext(), response.getTable().get(0).getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void DashboardResponse(DashboardDataResponse response) {
        layoutDashboardMain.setVisibility(View.VISIBLE);
        if (response.getTable3().size() > 0) {
            DashboardReportAdapter dashboardReportAdapter = new DashboardReportAdapter(response.getTable3(), this);
            recyclerviewDashboardReports.setAdapter(dashboardReportAdapter);
        }
        if (response.getTable2().size() > 0) {
            txtDashboardStatementTitle.setText(response.getTable().get(0).getC());
            DashboardStatementAdapter dashboardStatementAdapter = new DashboardStatementAdapter(response.getTable2(), this);
            recyclerviewDashboardStatement.setAdapter(dashboardStatementAdapter);
        }
        if (response.getTable1().size() > 0) {
            txtDashboardGraph.setText(response.getTable().get(0).getA() + " " + response.getTable().get(0).getB());
            LoadGraph(response);

            DashboardGraphAdapter dashboardGraphAdapter = new DashboardGraphAdapter(response.getTable1(), this);
            recyclerviewDashboardReportGraph.setAdapter(dashboardGraphAdapter);

            yTextDashboradReport.setText(response.getTable().get(0).getD());
            zTextDashboradReport.setText(response.getTable().get(0).getE());
            aTextDashboradReport.setText(response.getTable().get(0).getF());
        }
        if (response.getTable4().size() > 0) {
            txtDashboardReport2Title.setText(GetCurrentDateText());
            txtDashboardReport2Title.setTag(GetCurrentDate());
            // txtDashboardStatementTitle.setText(response.getTable().get(0).getC());
            DashboardReport2Adapter dashboardReport2Adapter = new DashboardReport2Adapter(response.getTable4(), this, this);
            recyclerViewDashboardReport2.setAdapter(dashboardReport2Adapter);
        }
    }

    @OnClick({R.id.txt_dashboard_statement_title, R.id.txt_chart_type,
            R.id.txt_dashboard_report2_title})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.txt_dashboard_report2_title:
                ShowDate();
                break;
            case R.id.txt_dashboard_statement_title:
                expandableLayoutStatement.toggle();
                break;
            case R.id.txt_chart_type:
                GraphMenu(txtChartType);
                break;
        }
    }

    private void GraphMenu(View Button) {
        PopupMenu popup = new PopupMenu(this, Button);
        popup.getMenuInflater().inflate(R.menu.menu_chart, popup.getMenu());
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            public boolean onMenuItemClick(MenuItem item) {
                int id = item.getItemId();
                switch (id) {
                    case R.id.action_line_chart:
                        barchartDashboardMain.setVisibility(View.GONE);
                        scatterchartDashboardMain.setVisibility(View.GONE);
                        radarchartDashboardMain.setVisibility(View.GONE);
                        chartDashboardMain.setVisibility(View.VISIBLE);
                        barchartDashboardMain.animate().alpha(0.0f);
                        scatterchartDashboardMain.animate().alpha(0.0f);
                        radarchartDashboardMain.animate().alpha(0.0f);
                        chartDashboardMain.animate().alpha(1.0f);

                        txtChartType.setText("Line Chart");
                        return true;
                    case R.id.action_bar_chart:
                        chartDashboardMain.setVisibility(View.GONE);
                        radarchartDashboardMain.setVisibility(View.GONE);
                        scatterchartDashboardMain.setVisibility(View.GONE);
                        barchartDashboardMain.setVisibility(View.VISIBLE);
                        barchartDashboardMain.animate().alpha(1.0f);
                        scatterchartDashboardMain.animate().alpha(0.0f);
                        radarchartDashboardMain.animate().alpha(0.0f);
                        chartDashboardMain.animate().alpha(0.0f);
                        txtChartType.setText("Bar Chart");
                        return true;
                    case R.id.action_radar_chart:
                        barchartDashboardMain.animate().alpha(0.0f);
                        scatterchartDashboardMain.animate().alpha(0.0f);
                        radarchartDashboardMain.animate().alpha(1.0f);
                        chartDashboardMain.animate().alpha(0.0f);
                        chartDashboardMain.setVisibility(View.GONE);
                        barchartDashboardMain.setVisibility(View.GONE);
                        scatterchartDashboardMain.setVisibility(View.GONE);
                        radarchartDashboardMain.setVisibility(View.VISIBLE);
                        txtChartType.setText("Radar Chart");
                        return true;
                    case R.id.action_scatter_chart:
                        barchartDashboardMain.animate().alpha(0.0f);
                        scatterchartDashboardMain.animate().alpha(1.0f);
                        radarchartDashboardMain.animate().alpha(0.0f);
                        chartDashboardMain.animate().alpha(0.0f);
                        chartDashboardMain.setVisibility(View.GONE);
                        barchartDashboardMain.setVisibility(View.GONE);
                        radarchartDashboardMain.setVisibility(View.GONE);
                        scatterchartDashboardMain.setVisibility(View.VISIBLE);
                        txtChartType.setText("Scatter Chart");
                        return true;
                }
                return true;
            }
        });
        popup.show();
    }

    private void LoadDashboard() {
        dashboardPresenter.LoadDashboardData("Base", sessionManager.getCorpCode(), sessionManager.getUserCode());
    }

    private void Logout() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want to logout?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dashboardPresenter.Logout("Login", "Logout", sessionManager.getCorpCode(),
                                sessionManager.getUserCode(), "", "", "");
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }

    private void SetupGraph() {
        SetUpLineGraph();
        SetUpBarGraph();
        SetUpRadarGraph();
        SetUpScatterGraph();
    }

    private void SetUpLineGraph() {
        MyMarkerView mv = new MyMarkerView(this, R.layout.custom_marker_view);
        chartDashboardMain.setMarkerView(mv);

        chartDashboardMain.setDrawGridBackground(false);
        chartDashboardMain.setDescription("");

        chartDashboardMain.setHighlightEnabled(true);
        chartDashboardMain.setTouchEnabled(true);
        chartDashboardMain.setDragEnabled(true);
        chartDashboardMain.setScaleEnabled(true);
        chartDashboardMain.setPinchZoom(false);

        chartDashboardMain.animateXY(2000, 2000);

        YAxis yAxis = chartDashboardMain.getAxisLeft();
        yAxis.setTextColor(Color.parseColor("#757575"));
        yAxis.setStartAtZero(false);

        chartDashboardMain.getAxisRight().setEnabled(false);
        chartDashboardMain.getAxisLeft().setDrawGridLines(false);
        chartDashboardMain.getXAxis().setDrawGridLines(false);
        XAxis xLabels = chartDashboardMain.getXAxis();
        xLabels.setPosition(XAxis.XAxisPosition.BOTTOM);
        xLabels.setTextColor(Color.parseColor("#757575"));

        Legend legend = chartDashboardMain.getLegend();
        legend.setPosition(Legend.LegendPosition.BELOW_CHART_RIGHT);
    }

    private void SetUpScatterGraph() {
        MyMarkerView mv = new MyMarkerView(this, R.layout.custom_marker_view);
        scatterchartDashboardMain.setMarkerView(mv);

        scatterchartDashboardMain.setDrawGridBackground(false);
        scatterchartDashboardMain.setDescription("");

        scatterchartDashboardMain.setHighlightEnabled(true);
        scatterchartDashboardMain.setTouchEnabled(true);
        scatterchartDashboardMain.setDragEnabled(true);
        scatterchartDashboardMain.setScaleEnabled(true);
        scatterchartDashboardMain.setPinchZoom(false);

        scatterchartDashboardMain.animateXY(2000, 2000);

        scatterchartDashboardMain.getAxisRight().setEnabled(false);
        scatterchartDashboardMain.getAxisLeft().setDrawGridLines(false);
        scatterchartDashboardMain.getXAxis().setDrawGridLines(false);

        XAxis xLabels = scatterchartDashboardMain.getXAxis();
        xLabels.setPosition(XAxis.XAxisPosition.BOTTOM);
        xLabels.setTextColor(Color.parseColor("#757575"));

        YAxis yAxis = scatterchartDashboardMain.getAxisLeft();
        yAxis.setTextColor(Color.parseColor("#757575"));
        yAxis.setStartAtZero(false);

        Legend legend = scatterchartDashboardMain.getLegend();
        legend.setPosition(Legend.LegendPosition.BELOW_CHART_RIGHT);
    }

    private void SetUpBarGraph() {

        MyMarkerView mv = new MyMarkerView(this, R.layout.custom_marker_view);
        barchartDashboardMain.setMarkerView(mv);

        barchartDashboardMain.setDescription("");
        barchartDashboardMain.setDrawBarShadow(false);
        barchartDashboardMain.setHighlightEnabled(true);
        barchartDashboardMain.setTouchEnabled(true);
        barchartDashboardMain.setDragEnabled(true);
        barchartDashboardMain.setScaleEnabled(true);
        barchartDashboardMain.setPinchZoom(false);
        barchartDashboardMain.setDrawGridBackground(false);

        Legend l = barchartDashboardMain.getLegend();
        l.setPosition(Legend.LegendPosition.BELOW_CHART_RIGHT);

        YAxis leftAxis = barchartDashboardMain.getAxisLeft();
        leftAxis.setTextColor(Color.parseColor("#757575"));
        leftAxis.setStartAtZero(false);

        barchartDashboardMain.getAxisRight().setEnabled(false);
        barchartDashboardMain.animateXY(3000, 3000);

        barchartDashboardMain.getAxisLeft().setDrawGridLines(false);
        barchartDashboardMain.getXAxis().setDrawGridLines(false);
        XAxis xLabels = barchartDashboardMain.getXAxis();
        xLabels.setPosition(XAxis.XAxisPosition.BOTTOM);
        xLabels.setTextColor(Color.parseColor("#757575"));


    }

    private void SetUpRadarGraph() {
        radarchartDashboardMain.setDescription("");
        radarchartDashboardMain.setWebLineWidth(1.5f);
        radarchartDashboardMain.setWebLineWidthInner(0.75f);
        radarchartDashboardMain.setWebAlpha(100);

        MyMarkerView mv = new MyMarkerView(this, R.layout.custom_marker_view);
        radarchartDashboardMain.setMarkerView(mv);

        XAxis xAxis = radarchartDashboardMain.getXAxis();
        xAxis.setTextSize(9f);
        xAxis.setYOffset(0f);
        xAxis.setXOffset(0f);

        YAxis yAxis = radarchartDashboardMain.getYAxis();
        yAxis.setTextSize(9f);
        yAxis.setLabelCount(mActivities.length, true);
        yAxis.setStartAtZero(false);
        yAxis.setDrawLabels(false);

        Legend l = radarchartDashboardMain.getLegend();
        l.setPosition(Legend.LegendPosition.RIGHT_OF_CHART);
        l.setXEntrySpace(7f);
        l.setYEntrySpace(5f);
    }

    private void LoadGraph(DashboardDataResponse response) {
        mActivities = new String[response.getTable1().size()];
        for (int i = 0; i < response.getTable1().size(); i++) {
            mActivities[i] = response.getTable1().get(i).getX();
        }
        LoadLineGraph(response);
        LoadBarGraph(response);
        LoadRadarGraph(response);
        LoadScatterGraph(response);
        SetupGraph();
    }

    private void LoadLineGraph(DashboardDataResponse response) {
        ArrayList<LineDataSet> dataSets = new ArrayList<LineDataSet>();
        ArrayList<Entry> values = new ArrayList<Entry>();
        ArrayList<Entry> values1 = new ArrayList<Entry>();
        ArrayList<Entry> values2 = new ArrayList<Entry>();
        for (int i = 0; i < response.getTable1().size(); i++) {
            double y = response.getTable1().get(i).getY();
            double z = response.getTable1().get(i).getZ();
            double a = response.getTable1().get(i).getA();
            values.add(new Entry((float) y, i));
            values1.add(new Entry((float) z, i));
            values2.add(new Entry((float) a, i));
        }
        LineDataSet d = new LineDataSet(values, response.getTable().get(0).getD());
        LineDataSet e = new LineDataSet(values1, response.getTable().get(0).getE());
        LineDataSet f = new LineDataSet(values2, response.getTable().get(0).getF());

        // set the line to be drawn like this "- - - - - -"
        d.setColor(Color.parseColor("#ffff0000"));
        d.setFillColor(Color.parseColor("#ffff0000"));
        d.setCircleSize(2f);
        d.setCircleColor(Color.parseColor("#ffff0000"));
        d.setLineWidth(2f);
        d.setDrawValues(false);
        d.setDrawCircleHole(false);
        d.setValueTextSize(8f);
        d.setDrawFilled(true);
        d.setDrawCubic(true);

        e.setColor(Color.parseColor("#00ddff"));
        e.setFillColor(Color.parseColor("#00ddff"));
        e.setCircleColor(Color.parseColor("#00ddff"));
        e.setLineWidth(2f);
        e.setCircleSize(2f);
        e.setDrawValues(false);
        e.setDrawCircleHole(false);
        e.setValueTextSize(8f);
        e.setDrawFilled(true);
        e.setDrawCubic(true);

        f.setColor(Color.parseColor("#1aa14c"));
        f.setFillColor(Color.parseColor("#1aa14c"));
        f.setCircleSize(2f);
        f.setCircleColor(Color.parseColor("#1aa14c"));
        f.setLineWidth(2f);
        f.setDrawValues(false);
        f.setDrawCircleHole(false);
        f.setValueTextSize(8f);
        f.setDrawFilled(true);
        f.setDrawCubic(true);

        dataSets.add(d);
        dataSets.add(e);
        dataSets.add(f);
        LineData data = new LineData(mActivities, dataSets);

        chartDashboardMain.setData(data);
        chartDashboardMain.invalidate();
    }

    private void LoadBarGraph(DashboardDataResponse response) {

        ArrayList<BarDataSet> barDataSets = new ArrayList<BarDataSet>();
        ArrayList<BarEntry> values = new ArrayList<BarEntry>();
        ArrayList<BarEntry> values1 = new ArrayList<BarEntry>();
        ArrayList<BarEntry> values2 = new ArrayList<BarEntry>();
        for (int i = 0; i < response.getTable1().size(); i++) {
            double y = response.getTable1().get(i).getY();
            double z = response.getTable1().get(i).getZ();
            double a = response.getTable1().get(i).getA();
            values.add(new BarEntry((float) y, i));
            values1.add(new BarEntry((float) z, i));
            values2.add(new BarEntry((float) a, i));
        }

        BarDataSet d = new BarDataSet(values, response.getTable().get(0).getD());
        BarDataSet e = new BarDataSet(values1, response.getTable().get(0).getE());
        BarDataSet f = new BarDataSet(values2, response.getTable().get(0).getF());

        // set the line to be drawn like this "- - - - - -"
        d.setColor(Color.parseColor("#ffff0000"));
        d.setDrawValues(false);
        d.setValueTextSize(8f);

        e.setColor(Color.parseColor("#00ddff"));
        e.setDrawValues(false);
        e.setValueTextSize(8f);

        f.setColor(Color.parseColor("#1aa14c"));
        f.setDrawValues(false);
        f.setValueTextSize(8f);

        barDataSets.add(d);
        barDataSets.add(e);
        barDataSets.add(f);

        BarData data = new BarData(mActivities, barDataSets);
        barchartDashboardMain.setData(data);
        barchartDashboardMain.invalidate();
    }

    private void LoadRadarGraph(DashboardDataResponse response) {
        ArrayList<Entry> values = new ArrayList<Entry>();
        ArrayList<Entry> values1 = new ArrayList<Entry>();
        ArrayList<Entry> values2 = new ArrayList<Entry>();
        for (int i = 0; i < response.getTable1().size(); i++) {
            double y = response.getTable1().get(i).getY();
            double z = response.getTable1().get(i).getZ();
            double a = response.getTable1().get(i).getA();
            values.add(new Entry((float) y, i));
            values1.add(new Entry((float) z, i));
            values2.add(new Entry((float) a, i));
        }

        RadarDataSet d = new RadarDataSet(values, response.getTable().get(0).getD());
        RadarDataSet e = new RadarDataSet(values1, response.getTable().get(0).getE());
        RadarDataSet f = new RadarDataSet(values2, response.getTable().get(0).getF());

        // set the line to be drawn like this "- - - - - -"
        d.setColor(Color.parseColor("#ffff0000"));
        d.setDrawValues(false);
        d.setDrawFilled(true);
        d.setValueTextSize(8f);
        d.setFillColor(Color.BLACK);

        e.setColor(Color.parseColor("#00ddff"));
        e.setDrawValues(false);
        e.setValueTextSize(8f);
        e.setDrawFilled(true);
        e.setFillColor(Color.BLACK);

        f.setColor(Color.parseColor("#1aa14c"));
        f.setDrawValues(false);
        f.setDrawFilled(true);
        f.setValueTextSize(8f);
        f.setFillColor(Color.BLACK);

        ArrayList<RadarDataSet> sets = new ArrayList<RadarDataSet>();
        sets.add(d);
        sets.add(e);
        sets.add(f);

        RadarData data = new RadarData(mActivities, sets);
        radarchartDashboardMain.setData(data);
        radarchartDashboardMain.invalidate();

    }

    private void LoadScatterGraph(DashboardDataResponse response) {

        ArrayList<ScatterDataSet> barDataSets = new ArrayList<ScatterDataSet>();
        ArrayList<Entry> values = new ArrayList<Entry>();
        ArrayList<Entry> values1 = new ArrayList<Entry>();
        ArrayList<Entry> values2 = new ArrayList<Entry>();
        for (int i = 0; i < response.getTable1().size(); i++) {
            double y = response.getTable1().get(i).getY();
            double z = response.getTable1().get(i).getZ();
            double a = response.getTable1().get(i).getA();
            values.add(new Entry((float) y, i));
            values1.add(new Entry((float) z, i));
            values2.add(new Entry((float) a, i));
        }

        ScatterDataSet d = new ScatterDataSet(values, response.getTable().get(0).getD());
        ScatterDataSet e = new ScatterDataSet(values1, response.getTable().get(0).getE());
        ScatterDataSet f = new ScatterDataSet(values2, response.getTable().get(0).getF());

        // set the line to be drawn like this "- - - - - -"
        d.setColor(Color.parseColor("#ffff0000"));
        d.setDrawValues(false);
        d.setValueTextSize(8f);
        d.setScatterShape(ScatterChart.ScatterShape.SQUARE);
        d.setScatterShapeSize(8f);

        e.setColor(Color.parseColor("#00ddff"));
        e.setDrawValues(false);
        e.setValueTextSize(8f);
        e.setScatterShape(ScatterChart.ScatterShape.CIRCLE);
        e.setScatterShapeSize(8f);

        f.setColor(Color.parseColor("#1aa14c"));
        f.setDrawValues(false);
        f.setValueTextSize(8f);
        f.setScatterShape(ScatterChart.ScatterShape.TRIANGLE);
        f.setScatterShapeSize(8f);

        barDataSets.add(d);
        barDataSets.add(e);
        barDataSets.add(f);

        ScatterData data = new ScatterData(mActivities, barDataSets);
        scatterchartDashboardMain.setData(data);
        barchartDashboardMain.invalidate();
    }

    private void ShowDate() {
        int mYear, mMonth, mDay;
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

        // Launch Date Picker Dialog
        DatePickerDialog dpd = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        int month = monthOfYear + 1;
                        String formattedMonth = "" + month;
                        String formattedDayOfMonth = "" + dayOfMonth;

                        if (month < 10) {

                            formattedMonth = "0" + month;
                        }
                        if (dayOfMonth < 10) {

                            formattedDayOfMonth = "0" + dayOfMonth;
                        }
                        txtDashboardReport2Title.setTag(year + "-" + formattedMonth + "-" + formattedDayOfMonth);
                        txtDashboardReport2Title.setText(formattedDayOfMonth + "-" + formattedMonth + "-" + year);
                    }
                }, mYear, mMonth, mDay);
        dpd.show();
    }

    private String GetCurrentDate() {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return df.format(c.getTime());
    }

    private String GetCurrentDateText() {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        return df.format(c.getTime());
    }

    @Override
    public void GetReportData(String s) {
        Intent i = new Intent(this, Report2Activity.class);
        i.putExtra(Constant.REPORTNAME,s);
        i.putExtra(Constant.DATE,txtDashboardReport2Title.getTag().toString());
        startActivity(i);
    }
}
