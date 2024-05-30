package com.example.javaviewtest.url;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.javaviewtest.R;

import java.util.List;

public class HistoryAdapter extends ArrayAdapter<History> {

    public HistoryAdapter(@NonNull Context context, List<History> histories) {
        super(context, 0, histories);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        History history = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_history, parent, false);
        }

        // Lookup view for data population
        TextView urlTextView = convertView.findViewById(R.id.url);
        TextView titleTextView = convertView.findViewById(R.id.title);
        TextView dateTextView = convertView.findViewById(R.id.date);

        // Populate the data into the template view using the data object
        urlTextView.setText(history.getUrl());
        titleTextView.setText(history.getTitle());
        dateTextView.setText(history.getTime());

        // Return the completed view to render on screen
        return convertView;
    }
}
