package com.example.vikash.foodorder.fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vikash.foodorder.Adapter.ExpandableListAdapter;
import com.example.vikash.foodorder.Adapter.MyArrayAdapter;
import com.example.vikash.foodorder.Checkout;
import com.example.vikash.foodorder.Model.MyDataModel;
import com.example.vikash.foodorder.R;
import com.example.vikash.foodorder.Utils.Keys;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.example.vikash.foodorder.Parser.JSONParser.resp;

public class FiveFragment extends Fragment {
    private ListView listView;
    private ArrayList<MyDataModel> list;
    private MyArrayAdapter adapter;
    String names;
    TextView details;
    private static String text;
    private String totcost;
    private String quan;


    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;

    public FiveFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_five, container, false);

        details = (TextView) view.findViewById(R.id.details);


   /*     list = new ArrayList<>();
        *//**
         * Binding that List to Adapter
         *//*
        adapter = new MyArrayAdapter(getActivity(), list);

        *//**
         * Getting List and Setting List Adapter
         *//*
        listView = (ListView)view.findViewById(R.id.listView);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Snackbar.make(findViewById(R.id.parentLayout), list.get(position).getName() + " => " + list.get(position).getPhone(), Snackbar.LENGTH_LONG).show();
            }
        });*/

        // Inflate the layout for this fragment
// preparing list data
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();
        new GetDataTask().execute();

        // get the listview
        expListView = (ExpandableListView) view.findViewById(R.id.lvExp);


        //  listAdapter = new ExpandableListAdapter(getActivity(), listDataHeader, listDataChild);

        // setting list adapter
        // expListView.setAdapter(listAdapter);

        // Listview Group click listener
        expListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {

            @Override
            public boolean onGroupClick(ExpandableListView parent, View v,
                                        int groupPosition, long id) {
                // Toast.makeText(getApplicationContext(),
                // "Group Clicked " + listDataHeader.get(groupPosition),
                // Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        // Listview Group expanded listener
        expListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {
                Toast.makeText(getActivity(),
                        listDataHeader.get(groupPosition) + " Expanded",
                        Toast.LENGTH_SHORT).show();
            }
        });

        // Listview Group collasped listener
        expListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {

            @Override
            public void onGroupCollapse(int groupPosition) {
                Toast.makeText(getActivity(),
                        listDataHeader.get(groupPosition) + " Collapsed",
                        Toast.LENGTH_SHORT).show();

            }
        });


        // Listview on child click listener
     /*  expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                // TODO Auto-generated method stub
                Toast.makeText(
                        getActivity(),
                        listDataHeader.get(groupPosition)
                                + " : "
                                + listDataChild.get(
                                listDataHeader.get(groupPosition)).get(
                                childPosition), Toast.LENGTH_SHORT)
                        .show();
                return false;
            }
        });*/


        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public void onResume() {
        super.onResume();


    }

    class GetDataTask extends AsyncTask<Void, Void, Void> {

        ProgressDialog dialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            /**
             * Progress Dialog for User Interaction
             */
         //   dialog = new ProgressDialog(getActivity());
         //   dialog.setTitle(" Please Wait...");
         //   dialog.setMessage("Fetching data ....");
         //   dialog.show();

        }

        @Nullable
        @Override
        protected Void doInBackground(Void... params) {
            List<String> additional = new ArrayList<>();
            String image;
            /**
             * Getting JSON Object from Web Using okHttp
             */
         //   JSONParser.getDataFromWeb();

            try {
                JSONArray json = new JSONArray(resp);


                for (int n = 0; n < json.length(); n++) {
                    try {
                        JSONObject object = json.getJSONObject(n);

                        names = object.getString(Keys.KEY_NAME);


                        if (names.equalsIgnoreCase("PIZZA & SANDWICH")) {
                            JSONArray array = object.getJSONArray(Keys.KEY_CHILDRENS);

                            for (int i = 0; i < array.length(); i++) {
                                JSONObject innerObject = array.getJSONObject(i);

                                names = innerObject.getString(Keys.KEY_NAME);
                                String parent = innerObject.getString(Keys.KEY_PARENT);
                                String type = innerObject.getString(Keys.KEY_TYPE);
                                String status = innerObject.getString(Keys.KEY_STATUS);
                                String products = innerObject.getString(Keys.KEY_PRODUCTS);


                                additional.add(names);
                                List<String> restData = new ArrayList<String>();
                                listDataHeader.add(i, names);


                                JSONArray jsonproduct = new JSONArray(products);
                                for (int j = 0; j < jsonproduct.length(); j++) {
                                    JSONObject innerfoodarray = jsonproduct.getJSONObject(j);
                                    String name = innerfoodarray.getString(Keys.KEY_NAME);
                                     image = innerfoodarray.getString(Keys.KEY_IMAGE);
                                    String code = innerfoodarray.getString(Keys.KEY_CODE);
                                    String category = innerfoodarray.getString(Keys.KEY_CATEGORY);
                                    String prices = innerfoodarray.getString(Keys.KEY_PRICES);


                                    JSONArray jsonprices = new JSONArray(prices);
                                    for (int k = 0; k < jsonprices.length(); k++) {
                                        JSONObject jsonpriceslist = jsonprices.getJSONObject(k);
                                        String region = jsonpriceslist.getString(Keys.KEY_REGION);
                                        String amount = jsonpriceslist.getString(Keys.KEY_AMOUNT);
                                        String tax = jsonpriceslist.getString(Keys.KEY_TAX);


                                        region = "Bengaluru";

                                        if (region.equalsIgnoreCase("Andhra Pradesh")) {

                                            if (image!=null || !image.isEmpty()|| !image.equalsIgnoreCase(""))

                                                restData.add(name +"-"+"Rs. " + amount );

                                            else
                                                restData.add(name +"-"+"Rs. " + amount );


                                        } else if (region.equalsIgnoreCase("Bengaluru")) {
                                            if (image.equals("")) {
                                                image = "image";
                                                restData.add(name +"-"+ amount +"-"+image);
                                            }
                                            else {

                                                restData.add(name +"-"+ amount+"-"+image);
                                            }

                                        } else if (region.equalsIgnoreCase("Chennai")) {
                                            if (image!=null || !image.isEmpty()|| !image.equalsIgnoreCase("")) {

                                                restData.add(name +"-"+"Rs. " + amount );
                                            }


                                            else {
                                                restData.add(name +"-"+"Rs. " + amount );
                                            }

                                        }

                                        listDataChild.put(names, restData);
                                    }
                                }
                            }
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                }


            } catch (JSONException e) {
                e.printStackTrace();
            }


            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
           // dialog.dismiss();

            listAdapter = new ExpandableListAdapter(getActivity(), listDataHeader, listDataChild, new ExpandableListAdapter.ListAdapterListener() {
                @Override
                public void onClickAtOKButton(int position,int cost,String childtext) {
                    text = childtext;
                    totcost = String.valueOf(position*cost);
                    quan  = String.valueOf(position);
                    Toast.makeText(getActivity(), position+" items(s) in cart Rs." + position*cost  , Toast.LENGTH_SHORT).show();
                    details.setVisibility(View.VISIBLE);
                    details.setText(position+" items(s) in cart Rs." + position*cost);



                }
            });
            expListView.setAdapter(listAdapter);

            details.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(),Checkout.class);
                    intent.putExtra("childtext",text);
                    intent.putExtra("totcost",totcost);
                    intent.putExtra("quan",quan);
                    startActivity(intent);
                }
            });

        }
    }

  }
