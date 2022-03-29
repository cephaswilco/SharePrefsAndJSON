package com.dvallieres.menusandprefintro;

import android.app.Activity;
import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.GridLayout;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

class SpecialObjectAdapter extends BaseAdapter {

    Context context;
    ArrayList<SpecialObject> data;
    private static LayoutInflater inflater = null;

    public SpecialObjectAdapter(Context context, ArrayList<SpecialObject> data) {
        // TODO Auto-generated constructor stub
        this.context = context;
        this.data = data;
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    public ArrayList<SpecialObject> RemoveLastItem(){

        if (this.data.size() > 0) {
            this.data.remove(this.data.size() - 1);
        }

        notifyDataSetChanged();

        return data;
    }

    public ArrayList<SpecialObject> AddItem(SpecialObject item){

        data.add(item);
        notifyDataSetChanged();

        return data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        View vi = convertView;
        if (vi == null)
            vi = inflater.inflate(R.layout.special_list_adapter, null);

        TextView nameText = (TextView) vi.findViewById(R.id.name);
        TextView quantityText = (TextView) vi.findViewById(R.id.quantity);

        nameText.setText(data.get(position).GetName());
        quantityText.setText( data.get(position).GetQuantity() + "" );

        return vi;
    }
}
