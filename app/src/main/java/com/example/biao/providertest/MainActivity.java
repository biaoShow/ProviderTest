package com.example.biao.providertest;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button btn_add_data, btn_query_data, btn_updata_data, btn_delete_data;
    private String newId=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_add_data = findViewById(R.id.btn_add_data);
        btn_query_data = findViewById(R.id.btn_query_data);
        btn_updata_data = findViewById(R.id.btn_updata_data);
        btn_delete_data = findViewById(R.id.btn_delete_data);

        btn_add_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //添加数据
                Uri uri = Uri.parse("content://com.example.biao.databasetest.provider/book");
                ContentValues values = new ContentValues();
                values.put("name", "lisidezhuanji");
                values.put("author", "lisi");
                values.put("price", "1888");
                values.put("pages", "564");
                Uri newUri = getContentResolver().insert(uri, values);
                newId = newUri.getPathSegments().get(1);
                Toast.makeText(MainActivity.this, "添加成功！", Toast.LENGTH_SHORT).show();
            }
        });
        btn_query_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //查询数据
                Uri uri = Uri.parse("content://com.example.biao.databasetest.provider/book");
                Cursor cursor = getContentResolver().query(uri, null, null, null, null);
                if (cursor != null) {
                    while (cursor.moveToNext()) {
                        String name = cursor.getString(cursor.getColumnIndex("name"));
                        String author = cursor.getString(cursor.getColumnIndex("author"));
                        int pages = cursor.getInt(cursor.getColumnIndex("name"));
                        double price = cursor.getDouble(cursor.getColumnIndex("price"));
                        Log.i("XXXXXX:", name + author + String.valueOf(pages) + String.valueOf(price));
                    }
                }
                cursor.close();
                Toast.makeText(MainActivity.this, "查询成功！", Toast.LENGTH_SHORT).show();
            }
        });
        btn_updata_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //更新数据
                Uri uri = Uri.parse("content://com.example.biao.databasetest.provider/book/" + newId);
                ContentValues values = new ContentValues();
                values.put("name", "The Da Vinci Code1111");
                values.put("price", "24.05");
                values.put("pages", "1216");
                getContentResolver().update(uri, values, null, null);
                Toast.makeText(MainActivity.this, "更新成功！", Toast.LENGTH_SHORT).show();
            }

        });
        btn_delete_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("content://com.example.biao.databasetest.provider/book/" + newId);
                getContentResolver().delete(uri, null, null);
                Toast.makeText(MainActivity.this, "删除成功！", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
