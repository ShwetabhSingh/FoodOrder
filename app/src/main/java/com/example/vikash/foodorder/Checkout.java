package com.example.vikash.foodorder;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.vikash.foodorder.Adapter.ExpandableListAdapter;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.List;

import static com.example.vikash.foodorder.Utils.StaticFields.imageUrl;


public class Checkout
        extends AppCompatActivity {

    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.checkout);

        String childtext = getIntent().getStringExtra("childtext");
        String totcost = getIntent().getStringExtra("totcost");
        String quan = getIntent().getStringExtra("quan");
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
       // toolbar.setTitle("My Cart");
       // Toast.makeText(this, childtext, Toast.LENGTH_SHORT).show();
        final String[] parts = childtext.split("\\-");
        String part1 = parts[0];
        String part2 = parts[1];
        String part3 = parts[2];

        TextView costone = (TextView) findViewById(R.id.costone);
        costone.setText("Rs." + part2);
        TextView textView = (TextView) findViewById(R.id.textView);
        TextView quantity = (TextView) findViewById(R.id.quantity);
        TextView lblListItem = (TextView) findViewById(R.id.lblListItem);
        textView.setText("Rs. " + totcost);
        lblListItem.setText(part1);
        quantity.setText(quan);

        ImageView imageView = (ImageView) findViewById(R.id.imageView);
        Picasso.with(this).load(imageUrl + part3).error(R.drawable.err).into(imageView);
        TextView price = (TextView) findViewById(R.id.price);
        TextView amountpayable = (TextView) findViewById(R.id.amountpayable);
        TextView details = (TextView) findViewById(R.id.details);
        price.setText("Rs." + totcost);
        amountpayable.setText("Rs." + totcost);
        details.setText("Rs. " + totcost);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }
}