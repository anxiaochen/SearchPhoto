package com.example.searchphotoapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.example.searchphotoapp.R;
import com.example.searchphotoapp.model.MainModelBean;
import com.example.searchphotoapp.ui.MainActivity;

import java.util.List;

public class PhotoListAdapter extends BaseAdapter {

    public Context context;
    private LayoutInflater inflater;
    private List<MainModelBean> Items;
    //ImageLoader imageLoader = MainActivity.getInstance().getImageLoader();

    public PhotoListAdapter(Context context, List<MainModelBean> Items) {
        this.context = context;
        this.Items = Items;
    }

    @Override
    public int getCount() {
        return Items.size();
    }

    @Override
    public Object getItem(int position) {
        return Items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (inflater == null)
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.list_item, null);

        //if (imageLoader == null)
        //imageLoader = MainActivity.getInstance().getImageLoader();

        //NetworkImageView thumbNail = (NetworkImageView) convertView.findViewById(R.id.thumbnail);
        TextView id = (TextView) convertView.findViewById(R.id.id);
        TextView title = (TextView) convertView.findViewById(R.id.title);
        TextView description = (TextView) convertView.findViewById(R.id.description);
        TextView views = (TextView) convertView.findViewById(R.id.views);
        MainModelBean m = Items.get(position);

        // thumbnail image
        //thumbNail.setImageUrl(m.getImage(), imageLoader);
        // id
        id.setText(m.getID());
        // title
        title.setText(m.getTitle());
        // description
        description.setText( String.valueOf(m.getDescription()));
        // views
        views.setText(String.valueOf(m.getViews()));


        return convertView;
    }
}

