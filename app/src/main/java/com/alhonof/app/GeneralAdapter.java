package com.alhonof.app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.ArrayList;

public class GeneralAdapter extends BaseAdapter {

    ArrayList<String> list = new ArrayList<String>();
    Context context;


    GeneralAdapter(Context context){

        list.add("Stop Warinings");
        list.add("Stop Sending SMS");

        this.context = context;
    }


    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }



    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = LayoutInflater.from(context).inflate(R.layout.general_list_row,viewGroup,false);

        TextView textView = view.findViewById(R.id.textView3);


        textView.setText(list.get(i));

        return view;
    }
}
