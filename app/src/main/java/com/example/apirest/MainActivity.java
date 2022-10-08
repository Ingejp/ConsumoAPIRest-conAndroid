package com.example.apirest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    EditText txtIdUser, txtTitle, txtBody, txtId;
    Button btnTest, btnLimpiar, btnEliminar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtIdUser =findViewById(R.id.editTextTextId);
        txtTitle=findViewById(R.id.editTextTextTitulo);
        txtBody=findViewById(R.id.editTextTextDescripcion);
        txtId=findViewById(R.id.editTextTextId2);
        btnTest=findViewById(R.id.button);
        btnLimpiar=findViewById(R.id.button3);
        btnEliminar=findViewById(R.id.button2);
        btnTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //leerServicio();
                guardar(txtId.getText().toString(), txtTitle.getText().toString(), txtBody.getText().toString());
            }
        });

        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                eliminar(txtId.getText().toString());
            }
        });

        btnLimpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                limpiarCajasDeTexto();
            }
        });
    }

    private void limpiarCajasDeTexto(){
        txtId.setText("");
       txtIdUser.setText("");
        txtTitle.setText("");
        txtBody.setText("");
    }


    private void leerServicio(){
        String url="https://jsonplaceholder.typicode.com/posts/"+ txtIdUser.getText().toString();
        StringRequest postRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject json = new JSONObject(response);
                    txtId.setText(json.getString("id"));
                    txtIdUser.setText(json.getString("userId"));
                    txtTitle.setText(json.getString("title"));
                    txtBody.setText(json.getString("body"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Error al consultar", Toast.LENGTH_SHORT).show();
            }
        });
        Volley.newRequestQueue(this).add(postRequest);
    }

    private void guardar(String idUser, String title, String body){
        String url="https://jsonplaceholder.typicode.com/posts";
        StringRequest postRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject json = new JSONObject(response);
                    Toast.makeText(getApplicationContext(), "Guardado exitosamente "+ json.getString("id") , Toast.LENGTH_LONG).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Error al consultar", Toast.LENGTH_SHORT).show();
            }
        })
        {
            protected Map<String, String> getParams(){
                Map<String, String> params =new HashMap<>();
                params.put("userId", idUser);
                params.put("body", body);
                params.put("title", title);
                return  params;
            }
        }
                ;
        Volley.newRequestQueue(this).add(postRequest);
    }

    private void actualizar( String id, String idUser, String title, String body){
        String url="https://jsonplaceholder.typicode.com/posts/1";
        StringRequest postRequest = new StringRequest(Request.Method.PUT, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject json = new JSONObject(response);
                    Toast.makeText(getApplicationContext(), "Actualizado exitosamente "+ json.getString("id") , Toast.LENGTH_LONG).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Error al consultar", Toast.LENGTH_SHORT).show();
            }
        })
        {
            protected Map<String, String> getParams(){
                Map<String, String> params =new HashMap<>();
                params.put("id", id);
                params.put("userId", idUser);
                params.put("body", body);
                params.put("title", title);
                return  params;
            }
        }
                ;
        Volley.newRequestQueue(this).add(postRequest);
    }

    private void eliminar( String id){
        String url="https://jsonplaceholder.typicode.com/posts/"+id;
        //Toast.makeText(this, url, Toast.LENGTH_SHORT).show();
        StringRequest postRequest = new StringRequest(Request.Method.DELETE, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject json = new JSONObject(response);
                    Toast.makeText(getApplicationContext(), "Eliminado exitosamente "+ response , Toast.LENGTH_LONG).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Error al consultar", Toast.LENGTH_SHORT).show();
            }
        });
        Volley.newRequestQueue(this).add(postRequest);
    }
}