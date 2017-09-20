package com.prudent.busoftadmin.ui.ReportDetail;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.prudent.busoftadmin.Helper.Constant;
import com.prudent.busoftadmin.R;
import com.prudent.busoftadmin.adapter.ReportDetailTableAdapter;
import com.prudent.busoftadmin.data.api.model.ReportDetail.Response.ReportDetailResponse;
import com.prudent.busoftadmin.data.pref.SessionManager;
import com.prudent.busoftadmin.utils.SortableReportDetailTableView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.codecrafters.tableview.listeners.OnScrollListener;
import de.codecrafters.tableview.toolkit.SimpleTableHeaderAdapter;
import de.codecrafters.tableview.toolkit.TableDataRowBackgroundProviders;

public class ReportDetailActivity extends AppCompatActivity implements ReportDetailView {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tableView_report_detail)
    SortableReportDetailTableView tableViewReportDetail;
    @BindView(R.id.fab_report_detail_close)
    FloatingActionButton fabReportDetailClose;

    private SessionManager sessionManager;
    private ProgressDialog progressDialog;
    private ReportDetailPresenter reportDetailPresenter;
    private String ReportName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_detail);
        ButterKnife.bind(this);

        sessionManager = new SessionManager(this);

        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }

        initPresenter();
        onAttach();
        SetProgress();

        ReportName = ReportName();
        LoadReport();
    }

    private String ReportName(){
        return getIntent().getExtras().getString(Constant.REPORTNAME);
    }

    private void LoadReport() {
        reportDetailPresenter.LoadReport(sessionManager.getCorpCode(), ReportName,
                "DashBoard", sessionManager.getUserCode());
    }

    private void initPresenter() {
        reportDetailPresenter = new ReportDetailPresenter();
    }

    @Override
    public void onAttach() {
        reportDetailPresenter.onAttach(this);
    }

    @Override
    public void onDetach() {
        reportDetailPresenter.onDetach();
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
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        return true;
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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
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
    public void Error(String Title, String Message) {

    }

    @Override
    public void ReportDetailResponse(ReportDetailResponse reportDetailResponse) {
        if (reportDetailResponse.getTable().size() == 0) {

        } else {
            ReportDetailTableAdapter reportDetailTableAdapter = new ReportDetailTableAdapter(this, reportDetailResponse.getTable(), tableViewReportDetail);
            tableViewReportDetail.setDataAdapter(reportDetailTableAdapter);
        }
    }

    @OnClick(R.id.fab_report_detail_close)
    public void onClick() {
        onBackPressed();
    }
}
