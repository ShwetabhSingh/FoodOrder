package com.example.vikash.foodorder;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.widget.ListView;
import android.widget.Toast;

import com.example.vikash.foodorder.Adapter.MyArrayAdapter;
import com.example.vikash.foodorder.Model.MyDataModel;
import com.example.vikash.foodorder.Utils.InternetConnection;
import com.example.vikash.foodorder.fragments.EightFragment;
import com.example.vikash.foodorder.fragments.FiveFragment;
import com.example.vikash.foodorder.fragments.FourFragment;
import com.example.vikash.foodorder.fragments.NineFragment;
import com.example.vikash.foodorder.fragments.OneFragment;
import com.example.vikash.foodorder.fragments.SevenFragment;
import com.example.vikash.foodorder.fragments.SixFragment;
import com.example.vikash.foodorder.fragments.ThreeFragment;
import com.example.vikash.foodorder.fragments.TwoFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ScrollableTabsActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    private ListView listView;
    private ArrayList<MyDataModel> list;
    private MyArrayAdapter adapter;
    Set<String> stabs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrollable_tabs);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
       // getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        /**
         * Array List for Binding Data from JSON to this List
         */
      //  list = new ArrayList<>();
        /**
         * Binding that List to Adapter
         */
     //   adapter = new MyArrayAdapter(this, list);

        /**
         * Getting List and Setting List Adapter
         */
       // listView = (ListView) findViewById(R.id.listView);
     //   listView.setAdapter(adapter);
     //   listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
       //     @Override
       //     public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Snackbar.make(findViewById(R.id.parentLayout), list.get(position).getName() + " => " + list.get(position).getPhone(), Snackbar.LENGTH_LONG).show();
    //        }
   //     });


        if (InternetConnection.checkConnection(getApplicationContext())) {
           // new GetDataTask().execute();
        } else {
            Toast toast = Toast.makeText(getApplicationContext(), "Internet Connection Not Available", Toast.LENGTH_LONG);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();

        }

    }



    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFrag(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }



    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        adapter.addFrag(new OneFragment(), "CHAT");
        adapter.addFrag(new TwoFragment(), "CHINESE");
        adapter.addFrag(new ThreeFragment(), "CONTINENTAL");
        adapter.addFrag(new FourFragment(), "NORTH INDIAN");
        adapter.addFrag(new FiveFragment(), "PIZZA & SANDWICH");
        adapter.addFrag(new SixFragment(), "SAVOURIES");
        adapter.addFrag(new SevenFragment(), "SOUTH INDIAN");
        adapter.addFrag(new EightFragment(), "SWEETS");
        adapter.addFrag(new NineFragment(), "TANDOORI");
        //adapter.addFrag(new TenFragment(), "TEN");

        viewPager.setAdapter(adapter);
    }


}
