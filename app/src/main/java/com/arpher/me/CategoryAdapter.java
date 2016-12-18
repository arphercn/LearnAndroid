package com.arpher.me;

import java.util.List;

import com.arpher.me.model.Category;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class CategoryAdapter extends ArrayAdapter<Category> {
    private int viewId;
    private Context context;
    private List<Category> objects;

    // Constructor
    public CategoryAdapter(Context context, int viewId, List<Category> objects) {
        super(context, viewId, objects);
        this.context = context;
        this.viewId  = viewId;
        this.objects = objects;
    }

    // For each row, control the view assigned to the data
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;

        // Inflate the row view, if it doesn't exist.
        // ViewList is capable of reusing the views.
        if(v == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(viewId, parent, false);
        }

        Category category = objects.get(position);

        // Add data to views
        if(category != null) {
            TextView tv1 = (TextView) v.findViewById(R.id.seq);
            if(tv1 != null) {
                tv1.setText(Integer.toString(category.getId()));
            }

            TextView tv2 = (TextView) v.findViewById(R.id.name);
            if(tv2 != null) {
                tv2.setText(category.getName());
            }
        }

        return v;
    }
}
