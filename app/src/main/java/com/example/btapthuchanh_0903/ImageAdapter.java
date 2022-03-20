package com.example.btapthuchanh_0903;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

public class ImageAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private List<Image> images;

    public ImageAdapter(Context context, int layout, List<Image> images) {
        this.context = context;
        this.layout = layout;
        this.images = images;
    }

    @Override
    public int getCount() {
        return images.size();
    }

    @Override
    public Object getItem(int i) {
        return images.get(i);
    }

    @Override
    public long getItemId(int i) {
        Image image = images.get(i);
        return Long.parseLong(image.getId_image());
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        ViewHolder viewHolder;

        if (view == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout, null);
            viewHolder.imgPicture = view.findViewById(R.id.ivImage);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        Image image = images.get(i);

        viewHolder.imgPicture.setImageResource(image.getImage());

        return view;
    }
}
