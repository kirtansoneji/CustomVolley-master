package com.example.customvolley;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class Base_Adapter extends BaseAdapter {
    Context context;
    List<DataModel>list;
    LayoutInflater inflater;

    public Base_Adapter(Context context, List<DataModel> list) {
        this.context = context;
        this.list = list;
        inflater=LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView=inflater.inflate(R.layout.item,null);
        TextView author=convertView.findViewById(R.id.author);
        ImageView imageUrl=convertView.findViewById(R.id.imageUrl);
        TextView book_title =convertView.findViewById(R.id.book_title);

        DataModel model=list.get(position);
        book_title.setText(model.getBook_title());
        author.setText(model.getAuthor());

        Glide.with(context)
                .load(model.imageUrl) // image url
                .into(imageUrl);

        return convertView;
    }
}
