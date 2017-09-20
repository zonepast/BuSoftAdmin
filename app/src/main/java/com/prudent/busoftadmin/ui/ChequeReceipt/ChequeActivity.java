package com.prudent.busoftadmin.ui.ChequeReceipt;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.prudent.busoftadmin.R;
import com.prudent.busoftadmin.adapter.ChequeReceiptBasePartySpinner;
import com.prudent.busoftadmin.adapter.ChequeReceiptBaseRateSpinner;
import com.prudent.busoftadmin.data.api.model.CheckReceiptAmountCalc.Response.ChequeReceiptAmountResponse;
import com.prudent.busoftadmin.data.api.model.CheckReceiptBase.Response.ChequeReceiptBaseResponse;
import com.prudent.busoftadmin.data.api.model.CheckReceiptSave.Response.CheckReceiptSaveResponse;
import com.prudent.busoftadmin.data.pref.SessionManager;
import com.prudent.busoftadmin.model.CustomSpinner;
import com.prudent.busoftadmin.utils.DateUtils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChequeActivity extends AppCompatActivity implements ChequeReceiptView {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.spinner_party_name_cheque_receipt)
    Spinner spinnerPartyNameChequeReceipt;
    @BindView(R.id.spinner_rate_cheque_receipt)
    Spinner spinnerRateChequeReceipt;
    @BindView(R.id.txt_cheque_receipt_bank_name)
    AutoCompleteTextView txtChequeReceiptBankName;
    @BindView(R.id.txt_cheque_receipt_date)
    TextView txtChequeReceiptDate;
    @BindView(R.id.txt_cheque_receipt_cheque_date)
    TextView txtChequeReceiptChequeDate;
    @BindView(R.id.txt_cheque_receipt_cheque_no)
    EditText txtChequeReceiptChequeNo;
    @BindView(R.id.txt_cheque_receipt_days)
    TextView txtChequeReceiptDays;
    @BindView(R.id.cheque_receipt_cheque_date)
    TextView chequeReceiptChequeDate;
    @BindView(R.id.cheque_receipt_date)
    TextView chequeReceiptDate;

    LinearLayout linearLayout_bottom_sheet;
    ProgressBar progressBar_bottom_sheet;
    Button btn_save;
    EditText editText_interest, editText_net_amount, editText_payable, editText_adjustable;
    @BindView(R.id.edt_amount_cheque_receipt)
    EditText edtAmountChequeReceipt;

    private ChequeReceiptPresenter chequeReceiptPresenter;
    private SessionManager sessionManager;
    private ProgressDialog progressDialog;
    private DateUtils dateUtils;
    private BottomSheetDialog mBottomSheetDialog;
    private String mPartyXcode, mRateXcode;

    private List<CustomSpinner> partyNameList = new ArrayList<CustomSpinner>();
    private List<CustomSpinner> RateList = new ArrayList<CustomSpinner>();
    private String[] bank = {"ABHYUDAYA COOPERATIVE BANK LIMITED",
            "ABU DHABI COMMERCIAL BANK",
            "AHMEDABAD MERCANTILE COOPERATIVE BANK",
            "AIRTEL PAYMENTS BANK LIMITED",
            "AKOLA JANATA COMMERCIAL COOPERATIVE BANK",
            "ALLAHABAD BANK",
            "ALMORA URBAN COOPERATIVE BANK LIMITED",
            "ANDHRA BANK", "ANDHRA PRAGATHI GRAMEENA BANK",
            "APNA SAHAKARI BANK LIMITED",
            "AUSTRALIA AND NEW ZEALAND BANKING GROUP LIMITED",
            "AXIS BANK", "B N P PARIBAS", "BANDHAN BANK LIMITED",
            "BANK INTERNASIONAL INDONESIA", "BANK OF AMERICA",
            "BANK OF BAHARAIN AND KUWAIT BSC", "BANK OF BARODA",
            "BANK OF CEYLON", "BANK OF INDIA", "BANK OF MAHARASHTRA",
            "BANK OF TOKYO MITSUBISHI LIMITED", "BARCLAYS BANK",
            "BASSEIN CATHOLIC COOPERATIVE BANK LIMITED",
            "BHARAT COOPERATIVE BANK MUMBAI LIMITED", "CANARA BANK",
            "CAPITAL SMALL FINANCE BANK LIMITED", "CATHOLIC SYRIAN BANK LIMITED",
            "CENTRAL BANK OF INDIA", "CHINATRUST COMMERCIAL BANK LIMITED",
            "CITI BANK", "CITIZEN CREDIT COOPERATIVE BANK LIMITED",
            "CITY UNION BANK LIMITED", "COMMONWEALTH BANK OF AUSTRALIA",
            "CORPORATION BANK", "CREDIT AGRICOLE CORPORATE AND INVESTMENT BANK CALYON BANK",
            "CREDIT SUISEE AG", "DCB BANK LIMITED", "DENA BANK",
            "DEOGIRI NAGARI SAHAKARI BANK LTD. AURANGABAD",
            "DEPOSIT INSURANCE AND CREDIT GUARANTEE CORPORATION",
            "DEUSTCHE BANK", "DEVELOPMENT BANK OF SINGAPORE",
            "DHANALAKSHMI BANK", "DOHA BANK", "DOHA BANK QSC",
            "DOMBIVLI NAGARI SAHAKARI BANK LIMITED",
            "EQUITAS SMALL FINANCE BANK LIMITED",
            "ESAF SMALL FINANCE BANK LIMITED",
            "EXPORT IMPORT BANK OF INDIA", "FEDERAL BANK",
            "FIRSTRAND BANK LIMITED", "G P PARSIK BANK", "GURGAON GRAMIN BANK",
            "HDFC BANK", "HIMACHAL PRADESH STATE COOPERATIVE BANK LTD",
            "HSBC BANK", "HSBC BANK OMAN SAOG", "ICICI BANK LIMITED",
            "IDBI BANK", "IDFC BANK LIMITED", "IDUKKI DISTRICT CO OPERATIVE BANK LTD",
            "INDIAN BANK", "INDIAN OVERSEAS BANK", "INDUSIND BANK",
            "INDUSTRIAL AND COMMERCIAL BANK OF CHINA LIMITED", "INDUSTRIAL BANK OF KOREA",
            "JALGAON JANATA SAHAKARI BANK LIMITED", "JAMMU AND KASHMIR BANK LIMITED"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
            Transition enter_transition =
                    TransitionInflater.from(this).
                            inflateTransition(R.transition.trns_cheque_receipt);
            getWindow().setAllowEnterTransitionOverlap(true);
            //getWindow().setExitTransition(exit_transition);
            getWindow().setEnterTransition(enter_transition);
        }

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        setContentView(R.layout.activity_cheque);
        ButterKnife.bind(this);

        sessionManager = new SessionManager(this);
        dateUtils = new DateUtils(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        SetBottomSheetCalculate();

        initPresenter();
        onAttach();

        SetProgress();

        SetCurrentDate();
        SetAutoTextBank();

        LoadBase();
    }

    private void Reset() {
        SetCurrentDate();
        SetAutoTextBank();
        if (partyNameList.size() > 0) {
            partyNameList.clear();
        }
        if (RateList.size() > 0) {
            RateList.clear();
        }
        txtChequeReceiptChequeNo.setText("");
        txtChequeReceiptBankName.setText("");
        edtAmountChequeReceipt.setText("");
        LoadBase();
    }

    private void ChangeReceiptDays() {
        txtChequeReceiptDate.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String cheque_date = txtChequeReceiptChequeDate.getTag().toString();
                String receipt_date = txtChequeReceiptDate.getTag().toString();

                txtChequeReceiptDays.setText(dateUtils.getDaysBetweenDates(receipt_date, cheque_date, "yyyy-MM-dd") + " Days");
                txtChequeReceiptDays.setTag(dateUtils.getDaysBetweenDates(receipt_date, cheque_date, "yyyy-MM-dd"));

                chequeReceiptChequeDate.setText(txtChequeReceiptDate.getText().toString());
            }
        });
    }

    private void ChangeChequeDays() {
        txtChequeReceiptChequeDate.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String cheque_date = txtChequeReceiptChequeDate.getTag().toString();
                String receipt_date = txtChequeReceiptDate.getTag().toString();

                txtChequeReceiptDays.setText(dateUtils.getDaysBetweenDates(receipt_date, cheque_date, "yyyy-MM-dd") + " Days");
                txtChequeReceiptDays.setTag(dateUtils.getDaysBetweenDates(receipt_date, cheque_date, "yyyy-MM-dd"));

                chequeReceiptDate.setText(txtChequeReceiptChequeDate.getText().toString());
            }
        });
    }

    private void SetCurrentDate() {
        txtChequeReceiptChequeDate.setText(dateUtils.GetCurrentDate("dd/MM/yyyy"));
        txtChequeReceiptChequeDate.setTag(dateUtils.GetCurrentDate("yyyy-MM-dd"));

        txtChequeReceiptDate.setText(dateUtils.GetCurrentDate("dd/MM/yyyy"));
        txtChequeReceiptDate.setTag(dateUtils.GetCurrentDate("yyyy-MM-dd"));

        String cheque_date = txtChequeReceiptChequeDate.getTag().toString();
        String receipt_date = txtChequeReceiptDate.getTag().toString();

        txtChequeReceiptDays.setText(dateUtils.getDaysBetweenDates(receipt_date, cheque_date, "yyyy-MM-dd") + " Days");
        txtChequeReceiptDays.setTag(dateUtils.getDaysBetweenDates(receipt_date, cheque_date, "yyyy-MM-dd"));

        chequeReceiptChequeDate.setText(dateUtils.GetCurrentDate("dd/MM/yyyy"));
        chequeReceiptDate.setText(dateUtils.GetCurrentDate("dd/MM/yyyy"));

        ChangeReceiptDays();
        ChangeChequeDays();
    }

    private void SetAutoTextBank() {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (this, R.layout.layout_autotextview, bank);
        txtChequeReceiptBankName.setThreshold(1);
        txtChequeReceiptBankName.setAdapter(adapter);
    }

    private void SetPartyNameSpinner() {
        ChequeReceiptBasePartySpinner chequeReceiptBasePartySpinner = new ChequeReceiptBasePartySpinner(this, partyNameList);
        spinnerPartyNameChequeReceipt.setAdapter(chequeReceiptBasePartySpinner);
        spinnerPartyNameChequeReceipt.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapter, View v,
                                       int position, long id) {
                // BranchXname = ((TextView) v.findViewById(R.id.tvCategory)).getText().toString();
                try {
                    mPartyXcode = ((TextView) v.findViewById(R.id.tvCategory)).getTag().toString();
                } catch (Exception e) {

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });
    }

    private void SetRateSpinner() {
        ChequeReceiptBaseRateSpinner chequeReceiptBaseRateSpinner = new ChequeReceiptBaseRateSpinner(this, RateList);
        spinnerRateChequeReceipt.setAdapter(chequeReceiptBaseRateSpinner);
        spinnerRateChequeReceipt.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapter, View v,
                                       int position, long id) {
                // BranchXname = ((TextView) v.findViewById(R.id.tvCategory)).getText().toString();
                try {
                    mRateXcode = ((TextView) v.findViewById(R.id.tvCategory)).getTag().toString();
                } catch (Exception e) {

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });
    }

    private void LoadBase() {
        chequeReceiptPresenter.Base("base", sessionManager.getCorpCode(), sessionManager.getUserCode());
    }

    private void SetProgress() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please Wait...");
        progressDialog.setCancelable(false);
        progressDialog.setTitle("Loading...");
    }

    private void initPresenter() {
        chequeReceiptPresenter = new ChequeReceiptPresenter();
    }

    @Override
    public void onAttach() {
        chequeReceiptPresenter.onAttach(this);
    }

    @Override
    public void onDetach() {
        chequeReceiptPresenter.onDetach();
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

    @Override
    public void ShowProgress() {
        progressDialog.show();
    }

    @Override
    public void HideProgress() {
        progressDialog.dismiss();
    }

    @Override
    public void ShowBottomSheetProgress() {
        linearLayout_bottom_sheet.setVisibility(View.GONE);
        progressBar_bottom_sheet.setVisibility(View.VISIBLE);
    }

    @Override
    public void HideBottomSheetProgress() {
        linearLayout_bottom_sheet.setVisibility(View.VISIBLE);
        progressBar_bottom_sheet.setVisibility(View.GONE);
    }

    @Override
    public void Error(String Title, String Message) {
        ErrorDialog(Title, Message);
    }

    @Override
    public void ShowChequeReceiptResponse(ChequeReceiptBaseResponse response) {
        if (response.getTable().size() > 0) {

            for (int i = 0; i < response.getTable().size(); i++) {
                if (response.getTable().get(i).getMaster().equals("Party")) {
                    partyNameList.add(new CustomSpinner(response.getTable().get(i).getXcode(), response.getTable().get(i).getXname()));
                }
                if (response.getTable().get(i).getMaster().equals("CHRATE")) {
                    RateList.add(new CustomSpinner(response.getTable().get(i).getXcode(), response.getTable().get(i).getXname()));
                }
            }

            SetPartyNameSpinner();
            SetRateSpinner();

        } else {

        }
    }

    @Override
    public void ShowChequeReceiptAmountResponse(ChequeReceiptAmountResponse response) {
        editText_interest.setText(String.format("%.2f", response.getTable().get(0).getColumn1()));
        editText_net_amount.setText(String.format("%.2f", response.getTable().get(0).getColumn2()));

        editText_payable.setText(String.format("%.2f", response.getTable().get(0).getColumn2()));
        editText_adjustable.setText(GetAdjustable(String.format("%.2f", response.getTable().get(0).getColumn2()), String.format("%.2f", response.getTable().get(0).getColumn2())));
    }

    @Override
    public void ShowChequeReceiptSaveResponse(CheckReceiptSaveResponse response) {
        if (response.getTable().size() > 0) {
            if (response.getTable().get(0).getSuccess() == 1) {
                mBottomSheetDialog.dismiss();
                Toast.makeText(getApplicationContext(), response.getTable().get(0).getMessage(), Toast.LENGTH_SHORT).show();
                Reset();
            } else {
                Toast.makeText(getApplicationContext(), response.getTable().get(0).getMessage(), Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(getApplicationContext(), "Something went wrong!", Toast.LENGTH_SHORT).show();
        }
    }

    private void ErrorDialog(String Title, String Message) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(Title);
        builder.setCancelable(false);
        builder.setMessage(Message);
        builder.setPositiveButton("RETRY",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,
                                        int which) {
                        Reset();
                    }
                });
        builder.show();
    }

    @Override
    public void ErrorAmountDialog(String Title, String Message) {
        mBottomSheetDialog.dismiss();
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(Title);
        builder.setCancelable(false);
        builder.setMessage(Message);
        builder.setPositiveButton("OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,
                                        int which) {
                        dialog.dismiss();
                    }
                });
        builder.show();
    }

    @Override
    public void ErrorSaveDialog(String Title, String Message) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(Title);
        builder.setCancelable(false);
        builder.setMessage(Message);
        builder.setPositiveButton("RETRY",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,
                                        int which) {
                        SaveChequeReceipt();
                    }
                });
        builder.show();
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
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        return true;
    }

    @OnClick({R.id.txt_cheque_receipt_date, R.id.txt_cheque_receipt_cheque_date,
            R.id.btn_cheque_receipt_calculate, R.id.btn_cheque_receipt_reset})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.txt_cheque_receipt_date:
                ShowDate(txtChequeReceiptDate);
                break;
            case R.id.txt_cheque_receipt_cheque_date:
                ShowDate(txtChequeReceiptChequeDate);
                break;
            case R.id.btn_cheque_receipt_calculate:
                ShowBottomSheetCalculate();
                break;
            case R.id.btn_cheque_receipt_reset:
                Reset();
                break;
        }
    }

    private void ShowDate(final TextView textView) {
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
                        textView.setTag(year + "-" + formattedMonth + "-" + formattedDayOfMonth);
                        textView.setText(formattedDayOfMonth + "/" + formattedMonth + "/" + year);
                    }
                }, mYear, mMonth, mDay);
        dpd.show();
    }

    private void ShowBottomSheetCalculate() {
        GetAmountInterest();
    }

    private void SetBottomSheetCalculate() {
        mBottomSheetDialog = new BottomSheetDialog(this);
        View sheetView = this.getLayoutInflater().inflate(R.layout.layout_bottom_sheet_cheque_receipt, null);
        mBottomSheetDialog.setContentView(sheetView);

        linearLayout_bottom_sheet = (LinearLayout) sheetView.findViewById(R.id.layout_cheque_receipt);
        progressBar_bottom_sheet = (ProgressBar) sheetView.findViewById(R.id.layout_cheque_receipt_progressbar);
        btn_save = (Button) sheetView.findViewById(R.id.btn_cheque_receipt_save);
        editText_interest = (EditText) sheetView.findViewById(R.id.txt_cheque_receipt_interest);
        editText_net_amount = (EditText) sheetView.findViewById(R.id.txt_cheque_receipt_net_amount);
        editText_payable = (EditText) sheetView.findViewById(R.id.txt_cheque_receipt_payable);
        editText_adjustable = (EditText) sheetView.findViewById(R.id.txt_cheque_receipt_adjustable);

        editText_payable.addTextChangedListener(new TextWatcher() {

            final android.os.Handler handler = new android.os.Handler();
            Runnable runnable;

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(final CharSequence charSequence, int i, int i1, int i2) {
                //handler.removeCallbacks(runnable);
                editText_adjustable.setText(GetAdjustable(editText_net_amount.getText().toString(), charSequence.toString()));

            }

            @Override
            public void afterTextChanged(final Editable editable) {
              /*  runnable = new Runnable() {
                    @Override
                    public void run() {
                    }
                };
                handler.postDelayed(runnable, 500);*/
            }
        });

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SaveChequeReceipt();
            }
        });
    }

    private void GetAmountInterest() {
        String amount = edtAmountChequeReceipt.getText().toString();
        String date1 = txtChequeReceiptDate.getTag().toString();
        String date2 = txtChequeReceiptChequeDate.getTag().toString();
        String bankName = txtChequeReceiptBankName.getText().toString();
        String days = txtChequeReceiptDays.getTag().toString();
        String chequeNo = txtChequeReceiptChequeNo.getText().toString();

        Boolean validation = true;
        if (amount.isEmpty()) {
            edtAmountChequeReceipt.setError("Amount cannot be empty!");
            validation = false;
        }
        if (chequeNo.isEmpty()) {
            txtChequeReceiptChequeNo.setError("Cheque No. cannot be empty!");
            validation = false;
        }
        if (bankName.isEmpty()) {
            txtChequeReceiptBankName.setError("Bank Name cannot be empty!");
            validation = false;
        }
        if (validation) {
            mBottomSheetDialog.show();
            chequeReceiptPresenter.AmountCalc("amount", date1, mPartyXcode, mRateXcode, bankName, date2,
                    days, amount);
        }
    }

    private String GetAdjustable(String NetAmount, String Payable) {
        float netAmount,payable;
        try{
            netAmount = Float.parseFloat(NetAmount);
        }catch (Exception e){
            netAmount = 0.00f;
        }
        try{
            payable = Float.parseFloat(Payable);
        }catch (Exception e){
            payable = 0.00f;
        }
        float adjustable = netAmount - payable;
        return String.format("%.2f", adjustable);
    }

    private void SaveChequeReceipt() {
        String srNo = "";
        String date1 = txtChequeReceiptDate.getTag().toString();
        String partyName = mPartyXcode;
        String Rate = mRateXcode;
        String chequeNo = txtChequeReceiptChequeNo.getText().toString();
        String bankName = txtChequeReceiptBankName.getText().toString();
        String date2 = txtChequeReceiptChequeDate.getTag().toString();
        String days = txtChequeReceiptDays.getTag().toString();
        String amount = edtAmountChequeReceipt.getText().toString();
        String interest = editText_interest.getText().toString();
        String netamt = editText_net_amount.getText().toString();
        String adjamt = editText_adjustable.getText().toString();
        String payable = editText_payable.getText().toString();
        String userId = sessionManager.getUserCode();
        String entrydatetime = "";
        String editedby = "";
        String editdatetime = "";
        String corpcentre = sessionManager.getCorpCode();
        String unit_corp = sessionManager.getUnitCode();
        String terminal = "";

        chequeReceiptPresenter.SaveChequeReceipt(srNo, date1, partyName, Rate, chequeNo,
                bankName, date2, days, amount, interest, netamt, adjamt, payable, userId,
                entrydatetime, editedby, editdatetime, corpcentre, unit_corp, terminal);
    }
}
