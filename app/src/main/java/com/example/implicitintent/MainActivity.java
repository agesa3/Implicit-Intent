package com.example.implicitintent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ShareCompat;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText edt_web;
    Button web_btn;
    EditText edt_loc;
    Button btn_loc;
    EditText edt_share;
    Button btn_share;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edt_web = findViewById(R.id.website_edittext);
        web_btn = findViewById(R.id.open_website_button);
        edt_loc = findViewById(R.id.location_edittext);
        btn_loc = findViewById(R.id.open_location_button);
        edt_share=findViewById(R.id.share_edittext);
        btn_share=findViewById(R.id.share_text_button);


    }

    public void openWebsite(View view) {
        String url = edt_web.getText().toString();
        Uri webPage = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, webPage);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            Toast.makeText(this, "Unable to complete this action", Toast.LENGTH_SHORT).show();
        }

    }

    public void openLocation(View view) {
        String location = edt_loc.getText().toString();
        Uri addressUri = Uri.parse("geo:0,0?q=" + location);
        Intent locIntent = new Intent(Intent.ACTION_VIEW, addressUri);
        if (locIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(locIntent);
        } else {
            Log.d("ImplicitIntents", "Can't handle this intent!");
        }

    }

    public void shareText(View view) {
        String text=edt_share.getText().toString();
        String mimeType = "text/plain";
        ShareCompat.IntentBuilder
                .from(this)
                .setType(mimeType)
                .setChooserTitle("Share this text with: ")
                .setText(text)
                .startChooser();

    }
}
