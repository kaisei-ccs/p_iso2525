package com.example.taiken.p_iso2525;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.net.MalformedURLException;
import java.net.URL;

public class configActivity extends Activity implements View.OnClickListener {
    // Activity初期値
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.config);
        Button btn = (Button)findViewById(R.id.configButton);
        btn.setOnClickListener(this);

        Intent intent = getIntent();
        EditText editText = (EditText) findViewById(R.id.ipEditText);
        String ip = intent.getStringExtra("ipAddress");
        editText.setText(ip);
    }
    public void onClick(View view) {
        Intent intent = new Intent();
        EditText text = (EditText)findViewById(R.id.ipEditText);
        intent.putExtra("ipAddress", new String(text.getText().toString()));
        setResult(RESULT_OK, intent);
        finish();
    }
}
