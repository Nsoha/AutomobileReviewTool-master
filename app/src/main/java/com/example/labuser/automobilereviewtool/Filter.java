package com.example.labuser.automobilereviewtool;

import android.app.SearchManager;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
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
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);

        mDbHelper = new ReviewDBHelper(this);
        db = mDbHelper.getReadableDatabase();

    }

            public Cursor filter (String query, String[] columns) {
                String selection = ((TextView) findViewById(R.id.txtFilter)).getText().toString() + " MATCH ?";
                String[] selectionArgs = new String[] {query+"*"};

                return query(selection, selectionArgs, columns);
            }

            public Cursor query (String selection, String[] selectionArgs, String[] columns) {
                SQLiteQueryBuilder builder = new SQLiteQueryBuilder();
                builder.setTables(TABLE_NAME);

                Cursor cursor = builder.query(mDbHelper.getReadableDatabase(),
                        columns, selection, selectionArgs, null, null, null);

                if (cursor == null) {
                    return null;
                } else if (!cursor.moveToFirst()) {
                    cursor.close();
                    return null;
                }
                return cursor;
            }


                public void handleIntent(Intent intent) {

                    if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
                        String query = intent.getStringExtra(SearchManager.QUERY);
                        Cursor c = filter(query, null);
                        //process Cursor and display results


                        ReviewCursorAdapter adapter = new ReviewCursorAdapter(this, c);

                        ListView listview = (ListView) findViewById(R.id.listView);

                        listview.setAdapter(adapter);


                        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                                Intent intent = new Intent(view.getContext(), DetailView.class);

                                intent.putExtra("position", position);

                                startActivity(intent);
                            }


                        });
                    }
                }
            }


