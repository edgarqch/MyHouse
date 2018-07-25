package com.example.edgar.myhouse;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class LoginResult extends AppCompatActivity {

    private String avatar, email, name;
    private Context root;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        root = this;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_result);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        avatar = this.getIntent().getExtras().getString("atavar");
        email = this.getIntent().getExtras().getString("email");
        name = this.getIntent().getExtras().getString("name");


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        loadComponets();
    }

    private void loadComponets() {
        TextView nametxt = (TextView)this.findViewById(R.id.name);
        TextView emailtxt = (TextView)this.findViewById(R.id.email);
        ImageView avatar = (ImageView)this.findViewById(R.id.avatar);
        Button btn = (Button)this.findViewById(R.id.btnIngresar);
        nametxt.setText(name);
        emailtxt.setText(email);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent main = new Intent(root, MainActivity.class);
                root.startActivity(main);
            }
        });

    }

}
