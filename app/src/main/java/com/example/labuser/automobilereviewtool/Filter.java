package com.example.labuser.automobilereviewtool;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import static android.R.attr.filter;
import static com.example.labuser.automobilereviewtool.ReviewSchema.Review.COLUMN_NAME_RATING;
import static com.example.labuser.automobilereviewtool.ReviewSchema.Review.COLUMN_NAME_REVIEWER;
import static com.example.labuser.automobilereviewtool.ReviewSchema.Review.COLUMN_NAME_TITLE;
import static com.example.labuser.automobilereviewtool.ReviewSchema.Review.TABLE_NAME;

public class Filter extends AppCompatActivity {

    ReviewDBHelper mDbHelper;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);

        mDbHelper = new ReviewDBHelper(this);
        db = mDbHelper.getReadableDatabase();
    }

        protected void filterRecord(View v){

            String f = ((TextView) findViewById(R.id.btnSearch)).getText().toString();

        Cursor c = db.query(true, TABLE_NAME, new String[] {COLUMN_NAME_REVIEWER,
                        COLUMN_NAME_TITLE }, COLUMN_NAME_RATING + " LIKE ?",
                new String[] { f+"%" }, null, null, null,
                null);



        ReviewCursorAdapter adapter = new ReviewCursorAdapter(this,c);

        ListView listview = (ListView) findViewById(R.id.listView);

        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(view.getContext(), DetailView.class);

                intent.putExtra("position",position);

                startActivity(intent);
            }


        });
    }
}
