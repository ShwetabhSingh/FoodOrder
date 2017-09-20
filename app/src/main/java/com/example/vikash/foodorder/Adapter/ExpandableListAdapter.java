package com.example.vikash.foodorder.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.vikash.foodorder.R;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.vikash.foodorder.Utils.StaticFields.imageUrl;

public class ExpandableListAdapter extends BaseExpandableListAdapter {
    private int count = 0;
    int childpos = -1;
    Map<String, Integer> pos = new HashMap<>();

    private Context _context;
    private List<String> _listDataHeader; // header titles
    // child data in format of header title, child title
    private HashMap<String, List<String>> _listDataChild;

    public ExpandableListAdapter(Context context, List<String> listDataHeader,
                                 HashMap<String, List<String>> listChildData, ListAdapterListener mListener) {
        this._context = context;
        this._listDataHeader = listDataHeader;
        this._listDataChild = listChildData;
        this.mListener = mListener;
    }
    private ListAdapterListener mListener;


    public interface ListAdapterListener { // create an interface
        void onClickAtOKButton(int noofItems,int cost,String text); // create callback function
    }
    @Override
    public Object getChild(int groupPosition, int childPosititon) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition))
                .get(childPosititon);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {


        final String childText = (String) getChild(groupPosition, childPosition);

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_item, null);
        }

        final TextView txtListChild = (TextView) convertView
                .findViewById(R.id.lblListItem);

        final TextView txtListChildCost = (TextView) convertView
                .findViewById(R.id.cost);
        ImageView imageView = (ImageView) convertView.findViewById(R.id.imageView);
        ImageView plus = (ImageView) convertView.findViewById(R.id.plus);
        final ImageView minus = (ImageView) convertView.findViewById(R.id.minus);
        final TextView quantity = (TextView) convertView.findViewById(R.id.quantity);


        //  quantity.setText(childPosition);
        // txtListChild.setText(childText);

        String nameCost = childText;
        final String[] parts = nameCost.split("\\-");
        String part1 = parts[0];
        String part2 = parts[1];
        String part3 = parts[2];

        txtListChild.setText(part1);
        txtListChildCost.setText("Rs. " + parts[1]);
      //  final Stack st = new Stack();
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    //  st.push(childPosition);
               //  int check = (int) st.pop();
                count= count+1;

                minus.setVisibility(View.VISIBLE);
                int cost = Integer.parseInt(parts[1]);
                mListener.onClickAtOKButton(count,cost,childText);
                ///    show = true;
              //  Log.e("show in adapter", String.valueOf(show));
                if (count != 0) {

                    cost = Integer.parseInt(parts[1]);
                    //detail = count * cost;
                }

                quantity.setText(String.valueOf(count));
                //  quantity.setText(((String.valueOf(pos.get(String.valueOf(txtListChild.getText()))))));
            }
        });
        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  pos.put(String.valueOf(txtListChild.getText()), --count);
                //  quantity.setText(((String.valueOf(pos.get(String.valueOf(txtListChild.getText()))))));
                if (count != 0) {
                    count = count - 1;
                    int cost = Integer.parseInt(parts[1]);
                    quantity.setText(String.valueOf(count));
                    mListener.onClickAtOKButton(count,cost,childText);
                } else if (count == 0) {
                    minus.setVisibility(View.INVISIBLE);
                    //  show = false;
                }

            }
        });

        //  if (parts[1].equals("nil")){
        Picasso.with(convertView.getContext()).load(imageUrl+part3).error(R.drawable.err).into(imageView);

        //}
         /* else
               Picasso.with(convertView.getContext()).load("http://35.154.42.68/testing/files/"+parts[1]).into(imageView);*/
        return convertView;

    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition))
                .size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this._listDataHeader.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return this._listDataHeader.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        String headerTitle = (String) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_group, null);
        }

        TextView lblListHeader = (TextView) convertView
                .findViewById(R.id.lblListHeader);
        lblListHeader.setTypeface(null, Typeface.BOLD);
        lblListHeader.setText(headerTitle);
        if(isExpanded)
            lblListHeader.setTextColor(Color.parseColor("#fde2754d"));
        else
            lblListHeader.setTextColor(Color.BLACK);

        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    class ChildElement {
        int pos;
    }
}