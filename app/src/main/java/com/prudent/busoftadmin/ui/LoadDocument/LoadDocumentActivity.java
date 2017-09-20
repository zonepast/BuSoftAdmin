package com.prudent.busoftadmin.ui.LoadDocument;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.prudent.busoftadmin.R;
import com.prudent.busoftadmin.events.LoadDocumentEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoadDocumentActivity extends AppCompatActivity {

    @BindView(R.id.img_load_document_image)
    ImageView imgLoadDocumentImage;
    private static String DIR = "FMS/DOCUMENT/PDF";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_load_document);
        ButterKnife.bind(this);

    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void OnDocumentEvent(LoadDocumentEvent response) {
        String base64 = response.getDocument();
        String name = response.getDocumentname();
        String type = response.getDocumenttype();
        if (base64==null){
            Toast.makeText(getApplicationContext(),"No Documents Available...!",Toast.LENGTH_SHORT).show();
        }else if(type==null){
            //pdfView.setVisibility(View.VISIBLE);
            imgLoadDocumentImage.setVisibility(View.GONE);
            try {
                ConvertToPdf(base64, name);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if (type.equals(".jpg") || type.equals(".png") || type.equals(".jpeg")) {
            ConvertToImage(base64);
            imgLoadDocumentImage.setVisibility(View.VISIBLE);
           // pdfView.setVisibility(View.GONE);
        }
        else if (type.equals(".pdf")) {
           // pdfView.setVisibility(View.VISIBLE);
            imgLoadDocumentImage.setVisibility(View.GONE);
            try {
                ConvertToPdf(base64, name);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else  {
           // pdfView.setVisibility(View.VISIBLE);
            imgLoadDocumentImage.setVisibility(View.GONE);
            try {
                ConvertToPdf(base64, name);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
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
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, path);
            startActivity(browserIntent);
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

    private void ConvertToImage(String base64) {
        byte[] imageBytes = Base64.decode(base64, Base64.DEFAULT);
        Bitmap decodedImage = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
        imgLoadDocumentImage.setImageBitmap(decodedImage);
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
}
