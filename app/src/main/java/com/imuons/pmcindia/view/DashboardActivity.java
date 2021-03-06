package com.imuons.pmcindia.view;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;

import com.imuons.pmcindia.R;
import com.imuons.pmcindia.adapters.CustomExpandableListAdapter;
import com.imuons.pmcindia.fragments.ChatFragment;
import com.imuons.pmcindia.fragments.DashboardFragment;
import com.imuons.pmcindia.fragments.DirectReferralIncomeReportFragment;
import com.imuons.pmcindia.fragments.EstimateAmountReportFragment;
import com.imuons.pmcindia.fragments.FragmentInvestment;
import com.imuons.pmcindia.fragments.FragmentRequestReport;
import com.imuons.pmcindia.fragments.InvestmentReportFragment;
import com.imuons.pmcindia.fragments.LuckyWinnerReportFragment;
import com.imuons.pmcindia.fragments.MakeWithdrawalFragment;
import com.imuons.pmcindia.fragments.MyTeamFragment;
import com.imuons.pmcindia.fragments.PrincipleWithdrawFragment;
import com.imuons.pmcindia.fragments.ProfileFragment;
import com.imuons.pmcindia.fragments.ROIReportFragment;
import com.imuons.pmcindia.fragments.WithdrawReportFragment;
import com.imuons.pmcindia.models.ExpandableListModel;
import com.imuons.pmcindia.utils.AppCommon;
import com.imuons.pmcindia.utils.SharedPreferenceUtils;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.ButterKnife;

public class DashboardActivity extends AppCompatActivity implements FragmentInvestment.OnResponseHandle {

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
    private FragmentInvestment fragmentInvestment;
    private String tag = "Dashboard";
    private TextView userid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
       /* userid = findViewById(R.id.tv_user);
        String userID = AppCommon.getInstance(DashboardActivity.this).getUserObject();
        userid.setText(userID);*/
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);
        TextView user_id =toolbar.findViewById(R.id.tv_user);
        user_id.setText(AppCommon.getInstance(this).getUserId());
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
                    case 1:
                        fragmentManager.beginTransaction().replace(R.id.content_frame, ProfileFragment.newInstance()).commit();
                        getSupportActionBar().setTitle("Profile");
                        mDrawerLayout.closeDrawers();

                        break;

                    case 2:
                        fragmentManager.beginTransaction().replace(R.id.content_frame, MyTeamFragment.newInstance()).commit();
                        getSupportActionBar().setTitle("My Team");
                        mDrawerLayout.closeDrawers();
                        break;

                    case 6:
                        fragmentManager.beginTransaction().replace(R.id.content_frame, LuckyWinnerReportFragment.newInstance()).commit();
                        getSupportActionBar().setTitle("Lucky Winner Report");
                        mDrawerLayout.closeDrawers();
                        break;
                    case 7:
                        fragmentManager.beginTransaction().replace(R.id.content_frame, ChatFragment.newInstance()).commit();
                        getSupportActionBar().setTitle("Chat");
                        mDrawerLayout.closeDrawers();
                        break;


                    case 8:
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

                    case 3:
                        switch (childPosition) {

                            case 0:
                                fragmentInvestment =
                                        FragmentInvestment.newInstance(DashboardActivity.this);
                                fragmentManager.beginTransaction().replace(R.id.content_frame, fragmentInvestment).commit();
                                getSupportActionBar().setTitle("Investment");
                                mExpandableListView.setItemChecked(childPosition, true);
                                mExpandableListView.setSelection(childPosition);
                                break;
                            case 1:
                                fragmentManager.beginTransaction().replace(R.id.content_frame, InvestmentReportFragment.newInstance()).commit();
                                getSupportActionBar().setTitle("Investment Report");
                                mExpandableListView.setItemChecked(childPosition, true);
                                mExpandableListView.setSelection(childPosition);
                                break;
                            case 2:
                                fragmentManager.beginTransaction().replace(R.id.content_frame, FragmentRequestReport.newInstance()).commit();
                                getSupportActionBar().setTitle("Investment Request Report");
                                mExpandableListView.setItemChecked(childPosition, true);
                                mExpandableListView.setSelection(childPosition);
                                break;

                        }
                        break;

                    case 4:
                        switch (childPosition) {
                            case 0:
                                fragmentManager.beginTransaction().replace(R.id.content_frame, DirectReferralIncomeReportFragment.newInstance()).commit();
                                getSupportActionBar().setTitle("Direct Referral Income Report");
                                mExpandableListView.setItemChecked(childPosition, true);
                                mExpandableListView.setSelection(childPosition);
                                break;

                            case 1:
                                fragmentManager.beginTransaction().replace(R.id.content_frame, ROIReportFragment.newInstance()).commit();
                                getSupportActionBar().setTitle("ROI Report");
                                mExpandableListView.setItemChecked(childPosition, true);
                                mExpandableListView.setSelection(childPosition);
                                break;
                            case 2:
                                fragmentManager.beginTransaction().replace(R.id.content_frame, EstimateAmountReportFragment.newInstance()).commit();
                                getSupportActionBar().setTitle("Estimate Amount Report");
                                mExpandableListView.setItemChecked(childPosition, true);
                                mExpandableListView.setSelection(childPosition);
                                break;

                        }
                        break;

                    case 5:
                        switch (childPosition) {
                            case 0:
                                fragmentManager.beginTransaction().replace(R.id.content_frame, MakeWithdrawalFragment.newInstance()).commit();
                                getSupportActionBar().setTitle("Make Withdraw");
                                mExpandableListView.setItemChecked(childPosition, true);
                                mExpandableListView.setSelection(childPosition);
                                break;

                            case 1:
                                fragmentManager.beginTransaction().replace(R.id.content_frame, PrincipleWithdrawFragment.newInstance()).commit();
                                getSupportActionBar().setTitle("Principal Withdraw");
                                mExpandableListView.setItemChecked(childPosition, true);
                                mExpandableListView.setSelection(childPosition);
                                break;
                            case 2:

                                fragmentManager.beginTransaction().replace(R.id.content_frame, WithdrawReportFragment.newInstance()).commit();
                                getSupportActionBar().setTitle("Withdrawal Report");
                                mExpandableListView.setItemChecked(childPosition, true);
                                mExpandableListView.setSelection(childPosition);
                                break;

                        }
                        break;


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

        mExpandableListTitle.add(new ExpandableListModel(R.drawable.dashboard, "Dashboard"));
        mExpandableListTitle.add(new ExpandableListModel(R.drawable.userss, "Profile"));
        mExpandableListTitle.add(new ExpandableListModel(R.drawable.people, "My Team"));
        mExpandableListTitle.add(new ExpandableListModel(R.drawable.money, "Investment"));
        mExpandableListTitle.add(new ExpandableListModel(R.drawable.report, "Income Report"));
        mExpandableListTitle.add(new ExpandableListModel(R.drawable.withdraw, "Withdrawal"));
        mExpandableListTitle.add(new ExpandableListModel(R.drawable.cup, "Lucky Winner Report"));
        mExpandableListTitle.add(new ExpandableListModel(R.drawable.chat, "Chat"));
        mExpandableListTitle.add(new ExpandableListModel(R.drawable.logout, "Logout"));

        //three
        ArrayList<String> investment = new ArrayList<String>();
        investment.add("Investment");
        investment.add("Investment Report");
        investment.add("Investment Request Report");


        //four
        ArrayList<String> incomereport = new ArrayList<String>();
        incomereport.add("Direct Referral Income Report");
        incomereport.add("ROI Report");
        incomereport.add("Estimate Amount Report");

        //five
        ArrayList<String> withdrawal = new ArrayList<String>();
        withdrawal.add("Make Withdrawal");
        withdrawal.add("Principal Withdrawal");
        withdrawal.add("Withdrawal Report");


        ArrayList<String> allTransactions = new ArrayList<String>();
        mExpandableListData.put(mExpandableListTitle.get(0).title, allTransactions);
        mExpandableListData.put(mExpandableListTitle.get(1).title, allTransactions);
        mExpandableListData.put(mExpandableListTitle.get(2).title, allTransactions);
        mExpandableListData.put(mExpandableListTitle.get(3).title, investment);
        mExpandableListData.put(mExpandableListTitle.get(4).title, incomereport);
        mExpandableListData.put(mExpandableListTitle.get(5).title, withdrawal);
        mExpandableListData.put(mExpandableListTitle.get(6).title, allTransactions);
        mExpandableListData.put(mExpandableListTitle.get(7).title, allTransactions);
        mExpandableListData.put(mExpandableListTitle.get(8).title, allTransactions);

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
                        if (fragmentInvestment != null) {
                            if (fragmentInvestment.is_payment_dialog_open) {
                                fragmentInvestment.dialogBox.setVisibility(View.GONE);
                                fragmentInvestment.is_payment_dialog_open = false;
                            } else {
                                Toast.makeText(getApplicationContext(), "Press again to exit.",
                                        Toast.LENGTH_SHORT).show();
                                lastPressedTime = event.getEventTime();
                            }
                        } else {
                            Toast.makeText(getApplicationContext(), "Press again to exit.",
                                    Toast.LENGTH_SHORT).show();
                            lastPressedTime = event.getEventTime();
                        }

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
        builder1.setTitle("Alert");
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d(tag, "------requestCode code" + requestCode);
        Log.d(tag, "------resultCode code" + resultCode);
        if (resultCode == RESULT_OK) {
            if (requestCode == 200 || requestCode == 100) {
                fragmentInvestment.setImage(requestCode, resultCode, data);
            } else if (requestCode == 100) {

            } else if (requestCode == 1010) {

            }

        } else {

        }
    }

    @Override
    public void onResponse(int flag) {
        if(flag==1){
            fragmentManager.beginTransaction().replace(R.id.content_frame, FragmentRequestReport.newInstance()).commit();
            getSupportActionBar().setTitle("Investment Request Report");
            mExpandableListView.setItemChecked(2, true);
            mExpandableListView.setSelection(2);
        }
    }
}
