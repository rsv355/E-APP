package com.example.android.educationapp.ui.ui;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Message;
import android.os.StrictMode;
import android.service.textservice.SpellCheckerService;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.android.educationapp.R;
import com.example.android.educationapp.ui.base.GMailSender;
import com.example.android.educationapp.ui.base.QuestionDetails;

import net.qiujuer.genius.util.Log;
import net.qiujuer.genius.widget.GeniusButton;
import net.qiujuer.genius.widget.GeniusEditText;
import java.util.ArrayList;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.sql.DataSource;


public class FeedbackActivity extends ActionBarActivity {
    private Toolbar toolbar;
    private GeniusButton btnSend;
    ProgressDialog dialog;
    public static int i;
    private EditText etPassword;
    private EditText etTestid;
    private ListView listview;
    private TextView txtCounter;
    private QuestionDetails ques_d;
    public static ArrayList<QuestionDetails> Ques_det;
    int tempParseSize=0;
    GeniusEditText etName,etEmail,etFeedback;

    final String username = "softeng.krishna@gmail.com";
    final String password = "rinkuamit";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setBackgroundColor(Color.parseColor("#3D3427"));
        toolbar.setNavigationIcon(R.drawable.icon_back);

              if (toolbar != null) {
            toolbar.setTitle("Feedback");
            setSupportActionBar(toolbar);
        }

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        initView();

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isOnline()) {
                    processSendMail();
                }
            }
        });
    }


 void processSendMail(){

     /*new AsyncTask<Void,Void,Void>(){

         @Override
         protected Void doInBackground(Void... params) {
            *//* String to = "www.krishnapatel1992@gmail.com";

             String from = "softeng.krishna@gmail.com";

             Properties properties = System.getProperties();

             properties.setProperty("mail.smtp.host", "smtp.gmail.com");

             Session session = Session.getInstance(properties, new Authenticator() {
                 @Override
                 protected PasswordAuthentication getPasswordAuthentication() {
                     return new PasswordAuthentication("softeng.krishna@gmail.com", "rinkuamit");
                 }
             });

             try {
                 MimeMessage message = new MimeMessage(session);

                 message.setFrom(new InternetAddress(from));

                 message.addRecipient(javax.mail.Message.RecipientType.TO, new InternetAddress(
                         to));

                 message.setSubject("This is the Subject Line!");

                 message.setText("This is actual message");

                 Transport.send(message);
                 System.out.println("Sent message successfully....");
             } catch (MessagingException mex) {
                 mex.printStackTrace();
             }*//*


             return null;
         }
     }.execute();
*/



     runOnUiThread(new Runnable() {
         @Override
         public void run() {
             try {

                 GMailSender sender = new GMailSender("www.krishnapatel1992@gmail.com", "rinkuamit");

                 sender.sendMail("This is Subject",
                         "This is Body",
                         "www.krishnapatel1992",
                         "www.krishnapatel1992");
                 Toast.makeText(FeedbackActivity.this,"Button is clicked",Toast.LENGTH_SHORT).show();
             } catch (Exception e) {
                 Toast.makeText(FeedbackActivity.this,""+e.toString(),Toast.LENGTH_SHORT).show();
                 Log.e("SendMail",e.toString());
             }
         }
     });



 }


    public boolean  isOnline() {
    /*    ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            return true;
        }
        return false;*/
        return true;
    }

    void initView(){
    btnSend = (GeniusButton)findViewById(R.id.btnSend);
    etName = (GeniusEditText)findViewById(R.id.etName);
    etEmail = (GeniusEditText)findViewById(R.id.etEmail);
    etFeedback = (GeniusEditText)findViewById(R.id.etFeedback);
}

    @Override
    protected void onResume() {
        super.onResume();

    }

    //end of min class
}