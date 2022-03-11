package com.example.fpbm_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class MainActivity extends AppCompatActivity {
    TextView afficher;
    Button btn1;
    TextView erreurs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        afficher=(TextView) findViewById(R.id.textView);
        erreurs=(TextView) findViewById(R.id.textView2);
        btn1=(Button) findViewById(R.id.button);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Task().execute();
            }
        });
    }
    public class Task extends AsyncTask<Void,Void,Void>{
        String data="";
        String errors="";
        @Override
        protected Void doInBackground(Void... voids) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection conn=DriverManager.getConnection("jdbc:mysql://192.168.137.130:3306/fpbm","root","");
                Statement statement=conn.createStatement();
                ResultSet rs=statement.executeQuery("SELECT nom FROM etudiant where id=1");
                while(rs.next()){
                    data+=rs.getString(1);
                }
            }
            catch (Exception e){
                errors=e.toString();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void unused) {
            afficher.setText(data);
            if(errors!=null){
                erreurs.setText(errors);
            }
            super.onPostExecute(unused);
        }
    }

}