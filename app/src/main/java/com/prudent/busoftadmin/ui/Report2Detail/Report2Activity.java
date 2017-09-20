package com.prudent.busoftadmin.ui.Report2Detail;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.prudent.busoftadmin.Helper.Constant;
import com.prudent.busoftadmin.R;
import com.prudent.busoftadmin.data.api.apihelper.ApiConfig;
import com.prudent.busoftadmin.data.api.apihelper.AppConfig;
import com.prudent.busoftadmin.data.api.model.Report2Detail.Request.Report2DetailResponse;
import com.prudent.busoftadmin.data.pref.SessionManager;
import com.prudent.busoftadmin.utils.CustomTableDataAdapter;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.codecrafters.tableview.TableView;
import de.codecrafters.tableview.toolkit.SimpleTableDataAdapter;
import de.codecrafters.tableview.toolkit.SimpleTableHeaderAdapter;
import de.codecrafters.tableview.toolkit.TableDataRowBackgroundProviders;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Report2Activity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tableView_report_2_detail)
    TableView<String[]> tableViewReport2Detail;
    @BindView(R.id.fab_report_detail_close)
    FloatingActionButton fabReportDetailClose;

    private SessionManager sessionManager;
    private ProgressDialog progressDialog;
    private String mReportName,mDate;
    List<String> key_list = new ArrayList<>();
    String[][] table_content;
    String[] table_header;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report2);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        SetProgress();

        sessionManager = new SessionManager(this);

        mReportName = GetReportName();
        mDate = GetDate();

        SetUpTableView();

        LoadDetail();

    }

    private void SetUpTableView() {
        int colorEvenRows = getResources().getColor(R.color.white);
        int colorOddRows = getResources().getColor(R.color.grey);
        tableViewReport2Detail.setDataRowBackgroundProvider(TableDataRowBackgroundProviders.alternatingRowColors(colorEvenRows, colorOddRows));
    }

    private String GetReportName() {
        return getIntent().getExtras().getString(Constant.REPORTNAME);
    }

    private String GetDate() {
        return getIntent().getExtras().getString(Constant.DATE);
    }

    private void SetProgress() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please Wait...");
        progressDialog.setTitle("Loading...");
    }

    public void ShowProgress() {
        progressDialog.show();
    }

    public void HideProgress() {
        progressDialog.dismiss();
    }

    private void LoadDetail() {
        ShowProgress();
        ApiConfig getResponse = AppConfig.ApiClient().create(ApiConfig.class);
        Call<JsonElement> call = getResponse.GetReport2Detail(new
                Report2DetailResponse(sessionManager.getCorpCode(), mDate, mReportName, sessionManager.getUserCode()));
        call.enqueue(new Callback<JsonElement>() {
            @Override
            public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                JsonElement jsonElement = response.body();
                HideProgress();
                if (response.isSuccessful()) {
                    if (jsonElement.isJsonObject()) {
                        JsonObject objectWhichYouNeed = jsonElement.getAsJsonObject();
                        JsonArray jsonArray = objectWhichYouNeed.getAsJsonArray("table");

                        if (jsonArray.size() > 0) {
                            JsonObject object3 = jsonArray.get(0).getAsJsonObject();
                            String yourJson = object3.toString();
                            JsonParser parser = new JsonParser();
                            JsonElement element = parser.parse(yourJson);
                            JsonObject obj = element.getAsJsonObject(); //since you know it's a JsonObject
                            Set<Map.Entry<String, JsonElement>> entries = obj.entrySet();//will return members of your object
                            for (Map.Entry<String, JsonElement> entry : entries) {
                                key_list.add(entry.getKey());
                            }

                            tableViewReport2Detail.setColumnCount(key_list.size());

                            table_header = new String[key_list.size()];
                            table_content = new String[jsonArray.size()][key_list.size()];

                            for (int i = 0; i < jsonArray.size(); i++) {
                                JsonObject json_object = jsonArray.get(i).getAsJsonObject();
                                try {
                                    JSONObject jo2 = new JSONObject(json_object.toString());
                                    for (int k = 0; k < key_list.size(); k++) {
                                        String valueString = jo2.getString(key_list.get(k));
                                        Log.e("value", valueString);
                                        table_content[i][k] = valueString;
                                    }

                                /*
                                    Iterator<String> keys = jo2.keys();
                                    while (keys.hasNext()) {
                                    String keyValue = (String) keys.next();
                                    String valueString = jo2.getString(keyValue);
                                }*/
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }

                            SimpleTableHeaderAdapter simpleTableHeaderAdapter = new SimpleTableHeaderAdapter(getApplicationContext(), key_list.toArray(table_header));
                            simpleTableHeaderAdapter.setTextSize(14);
                            simpleTableHeaderAdapter.setTextColor(Color.WHITE);

                            CustomTableDataAdapter simpleTableDataAdapter = new CustomTableDataAdapter(getApplicationContext(), table_content);
                          //  simpleTableDataAdapter.setTextSize(12);

                            tableViewReport2Detail.setHeaderAdapter(simpleTableHeaderAdapter);
                            tableViewReport2Detail.setDataAdapter(simpleTableDataAdapter);
                        } else {
                            tableViewReport2Detail.setEmptyDataIndicatorView(SetEmptyLayout());
                        }
                    }

                    //ReportDetailResponse(serverResponse);
                } else {
                    Error("Try Again", "Something went wrong!");
                }
            }

            @Override
            public void onFailure(Call<JsonElement> call, Throwable t) {
                HideProgress();
                if (t instanceof ConnectException) {
                    Error("Internet Error", "Please, Check your Internet Connection!");
                } else if (t instanceof SocketTimeoutException) {
                    Error("Timeout Error", "Please, Try again!");
                } else {
                    Error("Internet Error", "Please, Check your Internet Connection!");
                }
            }
        });
    }

    public void Error(String Title, String Message) {

    }

    @OnClick(R.id.fab_report_detail_close)
    public void onClick() {
        onBackPressed();
    }

    private View SetEmptyLayout() {
        //LinearLayOut Setup
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);

        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT));

        ImageView imageView = new ImageView(this);
        imageView.setImageResource(R.drawable.document_empty_icon);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(200,200);
        layoutParams.gravity = Gravity.CENTER;
        imageView.setLayoutParams(layoutParams);

        linearLayout.addView(imageView);
        setContentView(linearLayout);

        return linearLayout;
    }
}
