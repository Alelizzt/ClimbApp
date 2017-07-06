package com.testapp.udistrital.climbapp;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
    private ProgressDialog pd;
    private TextView ciudad;
    private TextView pais;
    private Button boton1;
    private String res;
    private Context context;

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        context=this;
        setContentView(R.layout.activity_main);
        pais = (TextView)findViewById(R.id.txtPais);
        ciudad = (TextView)findViewById(R.id.txtCiudad);
        boton1 = (Button)findViewById(R.id.button);

        boton1.setOnClickListener(listener);
    }

    private View.OnClickListener listener = new View.OnClickListener() {
        public void onClick(View arg0) {
            new DownloadTask2().execute("");
            pd= ProgressDialog.show(context, "Por favor espere", "Consultando clima", true, false);

        }
    };

    private class DownloadTask2 extends AsyncTask<String, Void, Object>{
        protected Integer doInBackground(String... args){
            CargaDatosWS ws = new CargaDatosWS();

            res= ws.getClima(ciudad.getText().toString(), pais.getText().toString());
            return 1;
        }

        protected void onPostExecute(Object result){
            pd.dismiss();
            Toast.makeText(context,"Clima: "+res,Toast.LENGTH_LONG).show();
            super.onPostExecute(result);
        }
    }


}
