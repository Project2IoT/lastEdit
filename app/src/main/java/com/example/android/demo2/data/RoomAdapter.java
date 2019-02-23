package com.example.android.demo2.data;

import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.demo2.R;


public class RoomAdapter extends CursorAdapter {
    private int quantity;


    public RoomAdapter(Context context, Cursor c) {
        super(context, c, 0 /* flags */);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        // Inflate a list room view using the layout specified in list_item.xml
        return LayoutInflater.from(context).inflate(R.layout.roomss, parent, false);
    }

    @Override
    public void bindView(View view, final Context context, Cursor cursor) {
        // Find individual views that we want to modify in the list room layout
        TextView nameTextView = (TextView) view.findViewById(R.id.room_name);
        TextView arduinoTextView = (TextView) view.findViewById(R.id.arduino_name);



        int roomNameColumnIndex = cursor.getColumnIndex(com.example.android.demo2.data.RoomContract.RoomEntry.COLUMN_ROOM_NAME);
        int arduinoNameColumnIndex = cursor.getColumnIndex(com.example.android.demo2.data.RoomContract.RoomEntry.COLUMN_ARDUINO_NAME);
        int idIndexcursor=cursor.getColumnIndex(com.example.android.demo2.data.RoomContract.RoomEntry._ID);


        String roomName = cursor.getString(roomNameColumnIndex);
        String arduinoName = cursor.getString(arduinoNameColumnIndex);
        String id = cursor.getString(idIndexcursor);



        final Uri mCurrentItemUri = ContentUris.withAppendedId(com.example.android.demo2.data.RoomContract.RoomEntry.CONTENT_URI,
                Long.parseLong(id));

        nameTextView.setText(roomName);
        arduinoTextView.setText(arduinoName);
    }
}