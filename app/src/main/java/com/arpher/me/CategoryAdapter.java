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

    private class Holder {
        TextView tv1;
        TextView tv2;
        public Holder(TextView tv1, TextView tv2) {
            this.tv1 = tv1;
            this.tv2 = tv2;
        }
    }

    // For each row, control the view assigned to the data
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        Holder holder;

        // Inflate the row view, if it doesn't exist.
        // ViewList is capable of reusing the views.
        if(v == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(viewId, parent, false);

            holder = new Holder((TextView) v.findViewById(R.id.seq),
                    (TextView) v.findViewById(R.id.name));
            v.setTag(holder);
        } else {
            holder = (Holder) v.getTag();
        }

        Category category = objects.get(position);

        // Add data to views
        if(category != null) {
            holder.tv1.setText(Integer.toString(category.getId()));
            holder.tv2.setText(category.getName());
        }

        return v;
    }
}
