package com.prudent.busoftadmin.ui.UploadDocument;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.IdRes;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Base64OutputStream;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.codekidlabs.storagechooser.StorageChooser;
import com.prudent.busoftadmin.R;
import com.prudent.busoftadmin.adapter.DetailSpinner;
import com.prudent.busoftadmin.adapter.SearchSpinner;
import com.prudent.busoftadmin.data.api.model.DetailUploadDocument.Response.DetailUploadDocumentResponse;
import com.prudent.busoftadmin.data.api.model.ShowDocument.Response.ShowDocumentResponse;
import com.prudent.busoftadmin.data.api.model.UploadDocument.Response.UploadDocumentResponse;
import com.prudent.busoftadmin.data.pref.SessionManager;
import com.prudent.busoftadmin.events.LoadDocumentEvent;
import com.prudent.busoftadmin.events.TranscationDocumentEvent;
import com.prudent.busoftadmin.ui.Dashboard.DashboardActivity;
import com.prudent.busoftadmin.ui.LoadDocument.LoadDocumentActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import gun0912.tedbottompicker.TedBottomPicker;

public class UploadDocumentActivity extends AppCompatActivity implements UploadDocumentView {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.spinner_transcation)
    Spinner spinnerTranscation;
    @BindView(R.id.radio_Upload)
    RadioButton radioUpload;
    @BindView(R.id.radio_Update)
    RadioButton radioUpdate;
    @BindView(R.id.radioGroup_upload_type)
    RadioGroup radioGroupUploadType;
    @BindView(R.id.spinner_detail)
    Spinner spinnerDetail;
    @BindView(R.id.txt_date_upload_document)
    TextView txtDateUploadDocument;
    @BindView(R.id.layout_date_upload_document)
    LinearLayout layoutDateUploadDocument;
    @BindView(R.id.edt_remark_upload_document)
    EditText edtRemarkUploadDocument;
    @BindView(R.id.btn_show_document)
    Button btnShowDocument;
    @BindView(R.id.txt_file_name)
    TextView txtFileName;
    @BindView(R.id.btn_browse_document)
    Button btnBrowseDocument;
    @BindView(R.id.btn_save_document)
    Button btnSaveDocument;
    @BindView(R.id.line)
    TextView line;

    private UploadDocumentPresenter uploadDocumentPresenter;
    private SessionManager sessionManager;
    private ProgressDialog progressDialog;
    private String mTransactionXcode, mTransactionXname, mDetailXcode, mDetailXname;
    private long length;
    private String strFileName = "", extension;
    private File file;
    private StorageChooser chooser;
    private TedBottomPicker tedBottomPicker;

    private static String DIR = "FMS/DOCUMENT/PDF";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_document);
        ButterKnife.bind(this);

        sessionManager = new SessionManager(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        FileChooser();

        initPresenter();
        onAttach();

        SetProgress();
        setRadioButton();
        SpinnerTransaction();
        SpinnerDetail();

        txtDateUploadDocument.setText(GetCurrentDate());

    }

    private void FileChooser() {
        tedBottomPicker = new TedBottomPicker.Builder(UploadDocumentActivity.this)
                .setOnImageSelectedListener(new TedBottomPicker.OnImageSelectedListener() {
                    @Override
                    public void onImageSelected(Uri uri) {
                        String path = uri.getPath();
                        if (path.equals("")) {

                        } else {
                            file = new File(path);
                            strFileName = file.getName();
                            txtFileName.setText(strFileName);
                            extension = file.getAbsolutePath().substring(file.getAbsolutePath().lastIndexOf("."));
                            length = file.length();
                            length = length / 1024;
                        }
                    }
                })
                .create();
        chooser = new StorageChooser.Builder()
                .withActivity(UploadDocumentActivity.this)
                .withFragmentManager(getSupportFragmentManager())
                .allowCustomPath(true)
                .setType(StorageChooser.FILE_PICKER)
                .build();
        chooser.setOnSelectListener(new StorageChooser.OnSelectListener() {
            @Override
            public void onSelect(String path) {
                if (path.equals("")) {

                } else {
                    file = new File(path);
                    strFileName = file.getName();
                    txtFileName.setText(strFileName);
                    extension = file.getAbsolutePath().substring(file.getAbsolutePath().lastIndexOf("."));
                    length = file.length();
                    length = length / 1024;
                }

            }
        });
    }

    private String GetCurrentDate() {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return df.format(c.getTime());
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void OnTransactionEvent(TranscationDocumentEvent response) {
        SearchSpinner spinAdapter = new SearchSpinner(getApplicationContext(), response.getResponse());
        spinnerTranscation.setAdapter(spinAdapter);
    }

    private void setRadioButton() {
        radioGroupUploadType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                int selectedId = radioGroupUploadType.getCheckedRadioButtonId();
                RadioButton radioButton = (RadioButton) findViewById(selectedId);
                if (radioButton.getText().equals("Upload")) {
                    btnShowDocument.setVisibility(View.GONE);
                    line.setVisibility(View.GONE);
                } else {
                    line.setVisibility(View.VISIBLE);
                    btnShowDocument.setVisibility(View.VISIBLE);
                }
                LoadDetail();
            }
        });
    }

    private void SetProgress() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please Wait...");
        progressDialog.setCancelable(false);
        progressDialog.setTitle("Loading...");
    }

    private void SpinnerTransaction() {
        spinnerTranscation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //if(mIsSpinnerFirstCall) {
                mTransactionXcode = ((TextView) view.findViewById(R.id.tvCategory)).getTag().toString();
                mTransactionXname = ((TextView) view.findViewById(R.id.tvCategory)).getText().toString();
                LoadDetail();
                //mIsSpinnerFirstCall = true;
                // }
                //mIsSpinnerFirstCall = false;/*else {


                // }*/
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void SpinnerDetail() {
        spinnerDetail.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mDetailXcode = ((TextView) view.findViewById(R.id.tvCategory)).getTag().toString();
                mDetailXname = ((TextView) view.findViewById(R.id.tvCategory)).getText().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void LoadDetail() {
        String radio;
        if (radioUpload.isChecked()) {
            radio = "Upload";
        } else {
            radio = "Update";
        }
        uploadDocumentPresenter.LoadDetail("Account", radio,
                sessionManager.getUserCode(), sessionManager.getCorpCode(),
                mTransactionXcode, "transaction");
    }

    private void initPresenter() {
        uploadDocumentPresenter = new UploadDocumentPresenter();
    }

    @Override
    public void onAttach() {
        uploadDocumentPresenter.onAttach(this);
    }

    @Override
    public void onDetach() {
        uploadDocumentPresenter.onDetach();
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
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
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        //Intent intent = getIntent();
        //setResult(RESULT_OK, intent);
        //finish();
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

    private void ErrorDialog(String Title, String Message) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(Title);
        builder.setCancelable(false);
        builder.setMessage(Message);
        builder.setPositiveButton("OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,
                                        int which) {
                    }
                });
        builder.show();
    }

    @Override
    public void ShowDetailResponse(DetailUploadDocumentResponse response) {
        if (response.getTable().size() == 0) {

        } else {
            DetailSpinner spinAdapter = new DetailSpinner(getApplicationContext(), response.getTable());
            spinnerDetail.setAdapter(spinAdapter);
        }
    }

    @Override
    public void ShowDocumentResponse(ShowDocumentResponse response) {
        if (response.getTable().size() == 0) {

        } else {
            String base64 = response.getTable().get(0).getDocValue();
            String name = response.getTable().get(0).getDocName();
            String type = response.getTable().get(0).getDocExt();
            if (base64 == null) {
                Toast.makeText(getApplicationContext(), "No Documents Available...!", Toast.LENGTH_SHORT).show();
            } else if (type == null) {
                try {
                    ConvertToPdf(base64, name);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (type.equals(".jpg") || type.equals(".png") || type.equals(".jpeg")) {
                EventBus.getDefault().postSticky(new LoadDocumentEvent(response.getTable().get(0).getDocValue(),
                        response.getTable().get(0).getDocName(),
                        response.getTable().get(0).getDocExt()));
                startActivity(new Intent(getApplicationContext(), LoadDocumentActivity.class));
               // Toast.makeText(getApplicationContext(), "Document Successfully Load", Toast.LENGTH_SHORT).show();
            } else if (type.equals(".pdf")) {
                try {
                    ConvertToPdf(base64, name);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    ConvertToPdf(base64, name);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void UploadDocumentResponse(UploadDocumentResponse response) {
        if (response.getTable().get(0).getSuccess() == 1) {
            String message = response.getTable().get(0).getMesssage();
            Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
            startActivity(new Intent(getApplicationContext(), DashboardActivity.class));
        } else {
            String message = response.getTable().get(0).getMesssage();
            Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
        }
    }

    @OnClick({R.id.layout_date_upload_document, R.id.btn_show_document,
            R.id.btn_browse_document, R.id.btn_save_document})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.layout_date_upload_document:
                ShowDate();
                break;
            case R.id.btn_show_document:
                LoadShowDocument();
                break;
            case R.id.btn_browse_document:
                ShowAlertDialogWithListView();
                break;
            case R.id.btn_save_document:
                if (strFileName.equals("")) {
                    Toast.makeText(getApplicationContext(), "Please, Select image or pdf Document", Toast.LENGTH_SHORT).show();
                } else {
                    if (extension.equals(".pdf") || extension.equals(".png") || extension.equals(".jpg")
                            || extension.equals(".gif") || extension.equals(".jpeg")) {
                        SaveDocument();
                    } else {
                        Toast.makeText(getApplicationContext(), "Please, Select file in image or pdf Document", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
        }
    }

    private void LoadShowDocument() {
        uploadDocumentPresenter.LoadDocument("Account", sessionManager.getUserCode(),
                sessionManager.getCorpCode(), "btnShow", mTransactionXcode, mDetailXcode);
    }

    private void SaveDocument() {
        String date = txtDateUploadDocument.getText().toString();
        String remark = edtRemarkUploadDocument.getText().toString();
        String base64 = getStringFile(file);
        String filesize = Long.toString(length);
        uploadDocumentPresenter.UploadDocument(mTransactionXcode, mDetailXcode, date, remark,
                base64, strFileName, extension, filesize, sessionManager.getCorpCode(),
                sessionManager.getUserCode(), GetCurrentDate());
    }

    public String getStringFile(File f) {
        InputStream inputStream = null;
        String encodedFile = "", lastVal;
        try {
            try {
                inputStream = new FileInputStream(f.getAbsolutePath());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            byte[] buffer = new byte[10240];//specify the size to allow
            int bytesRead;
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            Base64OutputStream output64 = new Base64OutputStream(output, Base64.DEFAULT);

            while ((bytesRead = inputStream.read(buffer)) != -1) {
                output64.write(buffer, 0, bytesRead);
            }
            output64.close();
            encodedFile = output.toString();
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        lastVal = encodedFile;
        return lastVal;
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
                        txtDateUploadDocument.setText(year + "-" + formattedMonth + "-" + formattedDayOfMonth);
                    }
                }, mYear, mMonth, mDay);
        dpd.show();
    }

    public void ShowAlertDialogWithListView() {
        List<String> mAnimals = new ArrayList<String>();
        mAnimals.add("Image");
        mAnimals.add("Document");
        final CharSequence[] Animals = mAnimals.toArray(new String[mAnimals.size()]);
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        dialogBuilder.setTitle("Select Type");
        dialogBuilder.setItems(Animals, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int item) {
                if (item == 0) {
                    tedBottomPicker.show(getSupportFragmentManager());
                }
                if (item == 1) {
                    chooser.show();
                }
            }
        });
        AlertDialog alertDialogObject = dialogBuilder.create();
        alertDialogObject.show();
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

    private void ConvertToPdf(String base64, String filename) throws IOException {
        getFileFromBase64AndSaveInSDCard(base64, filename);
        OpenPdf(filename);
    }

    public static GetFilePathAndStatus getFileFromBase64AndSaveInSDCard(String base64, String filename) {
        GetFilePathAndStatus getFilePathAndStatus = new GetFilePathAndStatus();
        try {
            byte[] pdfAsBytes = Base64.decode(base64, Base64.NO_WRAP);
            FileOutputStream os;
            os = new FileOutputStream(getReportPath(filename));
            os.write(pdfAsBytes);
            os.flush();
            os.close();
            getFilePathAndStatus.filStatus = true;
            getFilePathAndStatus.filePath = getReportPath(filename);
            return getFilePathAndStatus;
        } catch (IOException e) {
            e.printStackTrace();
            getFilePathAndStatus.filStatus = false;
            getFilePathAndStatus.filePath = getReportPath(filename);
            return getFilePathAndStatus;
        }
    }

    public static String getReportPath(String filename) {
        File file = new File(Environment.getExternalStorageDirectory().getPath(), DIR);
        if (!file.exists()) {
            file.mkdirs();
        }
        return file.getAbsolutePath() + "/" + filename;
    }

    private void OpenPdf(String filename) {
        File pdfFile = new File(Environment.getExternalStorageDirectory() + "/"+DIR, filename);//File path
        if (pdfFile.exists()) {
            Uri path = Uri.fromFile(pdfFile);
            Intent target = new Intent(Intent.ACTION_VIEW);
            target.setDataAndType(path,"application/pdf");
            target.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);

            Intent intent = Intent.createChooser(target, "Open File");
            try {
                startActivity(intent);
            } catch (ActivityNotFoundException e) {
                Toast.makeText(getApplicationContext(),"Please , Install Atleast one Pdf Viewer to Open Pdf",Toast.LENGTH_SHORT).show();
                // Instruct the user to install a PDF reader here, or something
            }
            /*pdfView.fromUri(path)
                    .enableSwipe(true) // allows to block changing pages using swipe
                    .swipeHorizontal(false)
                    .enableDoubletap(true)
                    .defaultPage(0)
                    .enableAnnotationRendering(false) // render annotations (such as comments, colors or forms)
                    .password(null)
                    .scrollHandle(null)
                    .enableAntialiasing(true) // improve rendering a little bit on low-res screens
                    .load();*/

        } else {
            Toast.makeText(getApplicationContext(), "The file not exists! ", Toast.LENGTH_SHORT).show();
        }
    }

    private static class GetFilePathAndStatus {
        public boolean filStatus;
        public String filePath;
    }
}
