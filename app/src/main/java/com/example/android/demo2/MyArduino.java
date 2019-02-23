package com.example.android.demo2;

import android.app.LoaderManager;
import android.content.ContentValues;
import android.content.CursorLoader;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import com.example.android.demo2.R;
import com.example.android.demo2.data.RoomDBHelper;
import com.example.android.demo2.data.RoomContract;
import com.example.android.demo2.data.RoomContract.RoomEntry;
import com.example.android.demo2.data.RoomDBHelper;

import static com.example.android.demo2.R.id.action_delete;

public class MyArduino extends AppCompatActivity  {

    private static final int LOADER = 0;
    private Uri mCurrentItemUri;
    private EditText mNameEditText;
    private EditText mArduinoNameEditText;

    String roomNameString;
    String arduinoNameString;

    boolean nameEntered = true;
    boolean priceEntered = true;
    private boolean mItemHasChanged = false;

    private View.OnTouchListener mTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            mItemHasChanged = true;
            return false;
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_arduino2);

    }
    private void insertItem() {

        arduinoNameString = mArduinoNameEditText.getText().toString().trim();

        if (
                TextUtils.isEmpty(arduinoNameString) && TextUtils.isEmpty(roomNameString)){
            Toast.makeText(this, "please fill in the fields", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(arduinoNameString)) {
            mArduinoNameEditText.setHintTextColor(Color.RED);

            Toast.makeText(this, "please fill in arduino name ", Toast.LENGTH_SHORT).show();
        } else {
            nameEntered = true;

            // Create database helper
            RoomDBHelper mDbHelper = new RoomDBHelper(this);
            SQLiteDatabase db = mDbHelper.getWritableDatabase();
            ContentValues values = new ContentValues();

            values.put(RoomEntry.COLUMN_ARDUINO_NAME, arduinoNameString);

            if (mCurrentItemUri == null) {
                Uri newUri = getContentResolver().insert(RoomEntry.CONTENT_URI, values);
                if (newUri == null) {
                    Toast.makeText(this, getString(R.string.failed), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, getString(R.string.successful), Toast.LENGTH_SHORT).show();
                }
            } else {
                int rowsAffected = getContentResolver().update(mCurrentItemUri, values, null, null);
                if (rowsAffected == 0) {
                    Toast.makeText(this, getString(R.string.failed), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, getString(R.string.successful), Toast.LENGTH_SHORT).show();
                }

            }
        }
    }

    public boolean allFieldsEntered() {
        if (nameEntered == false) {
            return false;
        } else
            return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_save: {
                insertItem();
                if (allFieldsEntered()) {
                    finish();
                } else {
                    Toast.makeText(this, "please fill in all field", Toast.LENGTH_SHORT).show();
                }
                return true;
            }

        }
        return super.onOptionsItemSelected(item);
    }


}