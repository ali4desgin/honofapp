package com.alhonof.app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
public class CountryAdapter extends BaseAdapter {


    Context context;
    ArrayList<String> arrayList = new ArrayList<String>();


    CountryAdapter(Context context){
        this.context = context;
        arrayList.add("الرياض");
        arrayList.add("المدينة");
        arrayList.add("مكة");
        arrayList.add("جدة");
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return arrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = LayoutInflater.from(context).inflate(R.layout.country_row,viewGroup,false);

        TextView textView = view.findViewById(R.id.countryname);

        textView.setText(arrayList.get(i));




        return view;
    }
}
