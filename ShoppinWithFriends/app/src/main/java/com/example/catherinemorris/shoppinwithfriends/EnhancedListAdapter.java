package com.example.catherinemorris.shoppinwithfriends;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class EnhancedListAdapter extends BaseAdapter {

    private Context mContext;
    private String[] items;

    public EnhancedListAdapter(Context mContext, String[] listItems) {
        this.mContext = mContext;
        this.items = listItems;
        // TODO Auto-generated constructor stub
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v;
        TextView tv;
        ImageView iv;
        v = setupView(convertView);

        tv = (TextView) v.findViewById(R.id.list_item);
        iv = (ImageView) v.findViewById(R.id.icon);

        // iv.setImageDrawable(packageManager.getApplicationIcon(info));
        iv.setImageResource(R.drawable.ic_launcher);
        tv.setText(this.items[position]);
        return v;
    }

    private View setupView(View convertView) {
        View v;
        ImageView iv;
        final LayoutInflater li = (LayoutInflater) this.mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            v = li.inflate(R.layout.abc_list_menu_item_layout, null);

            iv = (ImageView) v.findViewById(R.id.icon);
            iv.setScaleType(ImageView.ScaleType.CENTER_CROP);
            iv.setPadding(8, 8, 8, 8);
        } else {
            v = convertView;
        }
        return v;
    }

    @Override
    public int getCount() {
        return this.items.length;
    }

    @Override
    public Object getItem(int position) {
        return this.items[position];
    }

    @Override
    public long getItemId(int position) {
        return this.items[position].hashCode();
    }
}
