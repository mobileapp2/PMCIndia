package com.imuons.pmcindia.view;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.imuons.pmcindia.R;
import com.imuons.pmcindia.adapters.CustomExpandableListAdapter;
import com.imuons.pmcindia.fragments.DashboardFragment;
import com.imuons.pmcindia.models.ExpandableListModel;
import com.imuons.pmcindia.utils.AppCommon;
import com.imuons.pmcindia.utils.SharedPreferenceUtils;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.ButterKnife;

public class DashboardActivity extends AppCompatActivity {

    private ExpandableListAdapter mExpandableListAdapter;
    private ArrayList<ExpandableListModel> mExpandableListTitle;
    private HashMap<String, ArrayList<String>> mExpandableListData;
    ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;
    private ExpandableListView mExpandableListView;
    private FragmentManager fragmentManager;
    private long lastPressedTime;
    private static final int PERIOD = 2000;
    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(R.string.title_activity_dashboard);
        }
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mExpandableListView = findViewById(R.id.navList);
        mExpandableListView.setIndicatorBounds(mExpandableListView.getRight() + 120, mExpandableListView.getWidth());
        setupToolbar();
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setHomeButtonEnabled(true);
        prepareListData();

        fragmentManager = getSupportFragmentManager();
        mDrawerLayout.addDrawerListener(mDrawerToggle);
        fragmentManager.beginTransaction().replace(R.id.content_frame, DashboardFragment.newInstance()).commit();
        setupDrawerToggle();
        mExpandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                int index = parent.getFlatListPosition(ExpandableListView.getPackedPositionForChild(groupPosition, childPosition));
                parent.setItemChecked(index, true);

                return true;
            }
        });
        mExpandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v,
                                        int groupPosition, long id) {


                switch (groupPosition) {
                    case 0:
                        fragmentManager.beginTransaction().replace(R.id.content_frame, DashboardFragment.newInstance()).commit();
                        getSupportActionBar().setTitle("Dashboard");
                        mDrawerLayout.closeDrawers();

                        break;

                    case 7:
                        fragmentManager.beginTransaction().replace(R.id.content_frame, DashboardFragment.newInstance()).commit();
                        getSupportActionBar().setTitle("Security");
                        mDrawerLayout.closeDrawers();
                        break;

                    case 8:
                        Intent i = new Intent(DashboardActivity.this, DashboardFragment.class);
                        startActivity(i);
                        break;

                    case 9:
                        showAlertDialog();
                        break;
                }
                return false;
            }
        });
        mExpandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                // TODO Auto-generated method stub
                switch (groupPosition) {

                    case 1:
                        switch (childPosition) {

                            case 0:
                                fragmentManager.beginTransaction().replace(R.id.content_frame, DashboardFragment.newInstance()).commit();
                                getSupportActionBar().setTitle("Add Fund");
                                mExpandableListView.setItemChecked(childPosition, true);
                                mExpandableListView.setSelection(childPosition);
                                break;
                            case 1:
                                fragmentManager.beginTransaction().replace(R.id.content_frame, DashboardFragment.newInstance()).commit();
                                getSupportActionBar().setTitle("Pending Fund Report");
                                mExpandableListView.setItemChecked(childPosition, true);
                                mExpandableListView.setSelection(childPosition);
                                break;
                            case 2:
                                fragmentManager.beginTransaction().replace(R.id.content_frame, DashboardFragment.newInstance()).commit();
                                getSupportActionBar().setTitle("Add Fund Report");
                                mExpandableListView.setItemChecked(childPosition, true);
                                mExpandableListView.setSelection(childPosition);
                                break;
                            case 3:
                                fragmentManager.beginTransaction().replace(R.id.content_frame, DashboardFragment.newInstance()).commit();
                                getSupportActionBar().setTitle("Invest From Purchase Wallet");
                                mExpandableListView.setItemChecked(childPosition, true);
                                mExpandableListView.setSelection(childPosition);
                                break;
                            case 4:
                                fragmentManager.beginTransaction().replace(R.id.content_frame, DashboardFragment.newInstance()).commit();
                                getSupportActionBar().setTitle("Transfer Purchase Wallet Balance");
                                mExpandableListView.setItemChecked(childPosition, true);
                                mExpandableListView.setSelection(childPosition);
                                break;

                        }
                        break;

                    case 2:
                        switch (childPosition) {
                            case 0:
                                fragmentManager.beginTransaction().replace(R.id.content_frame, DashboardFragment.newInstance()).commit();
                                getSupportActionBar().setTitle("Profile");
                                mExpandableListView.setItemChecked(childPosition, true);
                                mExpandableListView.setSelection(childPosition);
                                break;

                            case 1:
                                fragmentManager.beginTransaction().replace(R.id.content_frame, DashboardFragment.newInstance()).commit();
                                getSupportActionBar().setTitle("Edit Profile");
                                mExpandableListView.setItemChecked(childPosition, true);
                                mExpandableListView.setSelection(childPosition);
                                break;
                            case 2:
                                fragmentManager.beginTransaction().replace(R.id.content_frame, DashboardFragment.newInstance()).commit();
                                getSupportActionBar().setTitle("Change Password");
                                mExpandableListView.setItemChecked(childPosition, true);
                                mExpandableListView.setSelection(childPosition);
                                break;

                        }
                        break;

                    case 3:
                        switch (childPosition) {
                            case 0:
                                fragmentManager.beginTransaction().replace(R.id.content_frame, DashboardFragment.newInstance()).commit();
                                getSupportActionBar().setTitle("Tree View");
                                mExpandableListView.setItemChecked(childPosition, true);
                                mExpandableListView.setSelection(childPosition);
                                break;

                            case 1:
                                fragmentManager.beginTransaction().replace(R.id.content_frame, DashboardFragment.newInstance()).commit();
                                getSupportActionBar().setTitle("Direct User List");
                                mExpandableListView.setItemChecked(childPosition, true);
                                mExpandableListView.setSelection(childPosition);
                                break;

                        }
                        break;

                    case 4:
                        switch (childPosition) {
                            case 0:
                                fragmentManager.beginTransaction().replace(R.id.content_frame, DashboardFragment.newInstance()).commit();
                                getSupportActionBar().setTitle("Direct Income");
                                mExpandableListView.setItemChecked(childPosition, true);
                                mExpandableListView.setSelection(childPosition);
                                break;

                            case 1:
                                fragmentManager.beginTransaction().replace(R.id.content_frame, DashboardFragment.newInstance()).commit();
                                getSupportActionBar().setTitle("ROI Income");
                                mExpandableListView.setItemChecked(childPosition, true);
                                mExpandableListView.setSelection(childPosition);
                                break;
                            case 2:
                                fragmentManager.beginTransaction().replace(R.id.content_frame, DashboardFragment.newInstance()).commit();
                                getSupportActionBar().setTitle("Binary Income");
                                mExpandableListView.setItemChecked(childPosition, true);
                                mExpandableListView.setSelection(childPosition);
                                break;
                            case 3:
                                fragmentManager.beginTransaction().replace(R.id.content_frame, DashboardFragment.newInstance()).commit();
                                getSupportActionBar().setTitle("Franchise Income");
                                mExpandableListView.setItemChecked(childPosition, true);
                                mExpandableListView.setSelection(childPosition);
                                break;

                        }
                        break;

                    case 5:
                        switch (childPosition) {
                            case 0:
                                fragmentManager.beginTransaction().replace(R.id.content_frame, DashboardFragment.newInstance()).commit();
                                getSupportActionBar().setTitle("Account Wallet");
                                mExpandableListView.setItemChecked(childPosition, true);
                                mExpandableListView.setSelection(childPosition);
                                break;

                            case 1:
                                fragmentManager.beginTransaction().replace(R.id.content_frame, DashboardFragment.newInstance()).commit();
                                getSupportActionBar().setTitle("Withdrawal Wallet Report");
                                mExpandableListView.setItemChecked(childPosition, true);
                                mExpandableListView.setSelection(childPosition);
                                break;


                        }
                        break;

                    case 6:
                        switch (childPosition) {
                            case 0:
                                fragmentManager.beginTransaction().replace(R.id.content_frame, DashboardFragment.newInstance()).commit();
                                getSupportActionBar().setTitle("Investment Report");
                                mExpandableListView.setItemChecked(childPosition, true);
                                mExpandableListView.setSelection(childPosition);
                                break;

                            case 1:
                                fragmentManager.beginTransaction().replace(R.id.content_frame, DashboardFragment.newInstance()).commit();
                                getSupportActionBar().setTitle("Transfer Purchase Wallet Report");
                                mExpandableListView.setItemChecked(childPosition, true);
                                mExpandableListView.setSelection(childPosition);
                                break;
//                            case 2:
//                                fragmentManager.beginTransaction().replace(R.id.content_frame, AddFundReportFragment.newInstance()).commit();
//                                getSupportActionBar().setTitle("Add Fund Report");
//                                mExpandableListView.setItemChecked(childPosition, true);
//                                mExpandableListView.setSelection(childPosition);
//                                break;

                        }
                        break;

                  /*  case 8:
                        switch (childPosition) {



                        }
                        break;
*/
                }
                mDrawerLayout.closeDrawers();
                return false;

            }
        });

        mExpandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                for (int i = 0; i < mExpandableListView.getCount(); i++) {
                    if (i != groupPosition) {
                        mExpandableListView.collapseGroup(i);
                    }
                }
            }
        });

    }

    private void prepareListData() {
        mExpandableListTitle = new ArrayList<ExpandableListModel>();
        mExpandableListData = new HashMap<String, ArrayList<String>>();

        mExpandableListTitle.add(new ExpandableListModel(R.drawable.user, "Dashboard"));
        mExpandableListTitle.add(new ExpandableListModel(R.drawable.user, "Invest Wallet"));
        mExpandableListTitle.add(new ExpandableListModel(R.drawable.user, "Profile"));
        mExpandableListTitle.add(new ExpandableListModel(R.drawable.user, "Team"));
        mExpandableListTitle.add(new ExpandableListModel(R.drawable.user, "Income"));
        mExpandableListTitle.add(new ExpandableListModel(R.drawable.user, "Withdrawal Wallet"));
        mExpandableListTitle.add(new ExpandableListModel(R.drawable.user, "Report"));
        mExpandableListTitle.add(new ExpandableListModel(R.drawable.user, "Security"));
        mExpandableListTitle.add(new ExpandableListModel(R.drawable.user, "Chat"));
        mExpandableListTitle.add(new ExpandableListModel(R.drawable.user, "Logout"));

        //one
        ArrayList<String> invest = new ArrayList<String>();
        invest.add("Add Fund");
        invest.add("Pending Fund Report");
        invest.add("Add Fund Report");
        invest.add("Invest From Purchase Wallet");
        invest.add("Transfer Purchase Wallet Balance To Other ID");

        //two
        ArrayList<String> profile = new ArrayList<String>();
        profile.add("Profile");
        profile.add("Edit Profile");
        profile.add("Change Password");

        //three
        ArrayList<String> team = new ArrayList<String>();
        team.add("Tree View");
        team.add("Direct User List");

        //four
        ArrayList<String> incomereport = new ArrayList<String>();
        incomereport.add("Direct Income");
        incomereport.add("ROI Income");
        incomereport.add("Binary Income");
        incomereport.add("Franchise Income");

        //five
        ArrayList<String> wallet = new ArrayList<String>();
        wallet.add("Account Wallet");
        wallet.add("Withdrawal Wallet Report");

        //Six
        ArrayList<String> report = new ArrayList<String>();
        report.add("Investment Report");
        report.add("Transfer Purchase Wallet Report");


        ArrayList<String> allTransactions = new ArrayList<String>();
        mExpandableListData.put(mExpandableListTitle.get(0).title, allTransactions);
        mExpandableListData.put(mExpandableListTitle.get(1).title, invest);
        mExpandableListData.put(mExpandableListTitle.get(2).title, profile);
        mExpandableListData.put(mExpandableListTitle.get(3).title, team);
        mExpandableListData.put(mExpandableListTitle.get(4).title, incomereport);
        mExpandableListData.put(mExpandableListTitle.get(5).title, wallet);
        mExpandableListData.put(mExpandableListTitle.get(6).title, report);
        mExpandableListData.put(mExpandableListTitle.get(7).title, allTransactions);
        mExpandableListData.put(mExpandableListTitle.get(8).title, allTransactions);
        mExpandableListData.put(mExpandableListTitle.get(9).title, allTransactions);

        mExpandableListAdapter = new CustomExpandableListAdapter(DashboardActivity.this, mExpandableListTitle, mExpandableListData);
        mExpandableListView.setAdapter(mExpandableListAdapter);

    }

    private void setupToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    void setupDrawerToggle() {
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.app_name, R.string.app_name);
        //This is necessary to change the icon of the Drawer Toggle upon state change.
        mDrawerToggle.syncState();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
            switch (event.getAction()) {
                case KeyEvent.ACTION_DOWN:
                    if (event.getDownTime() - lastPressedTime < PERIOD) {
                        finish();
                    } else {
                        Toast.makeText(getApplicationContext(), "Press again to exit.",
                                Toast.LENGTH_SHORT).show();
                        lastPressedTime = event.getEventTime();
                    }
                    return true;
            }
        }
        return false;
    }

    private void showAlertDialog() {
        AlertDialog.Builder builder1 = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.HONEYCOMB) {
            builder1 = new AlertDialog.Builder(DashboardActivity.this, AlertDialog.THEME_HOLO_LIGHT);
        }
        builder1.setTitle("Exit");
        builder1.setMessage("Are you sure you want to Logout ?");
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                        DashboardActivity.this.finish();
                        SharedPreferenceUtils.clearPreferences(DashboardActivity.this);
                        SharedPreferenceUtils.clearID(DashboardActivity.this);
                        SharedPreferenceUtils.clearAccess_Token(DashboardActivity.this);
                        SharedPreferenceUtils.storeSplash(DashboardActivity.this, "stop");
                        AppCommon.getInstance(DashboardActivity.this).clearPreference();
                        startActivity(new Intent(DashboardActivity.this, LoginActivity.class));
                        finishAffinity();
                        Toast.makeText(DashboardActivity.this, "Logout Successfully", Toast.LENGTH_SHORT).show();
                    }
                });

        builder1.setNegativeButton(
                "No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();
    }

}
