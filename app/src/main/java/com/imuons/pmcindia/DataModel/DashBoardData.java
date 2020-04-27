package com.imuons.pmcindia.DataModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DashBoardData {
    @SerializedName("btc_address")
    @Expose
    private String btcAddress;
    @SerializedName("winning_income")
    @Expose
    private Integer winningIncome;
    @SerializedName("winning_wallet_name")
    @Expose
    private String winningWalletName;
    @SerializedName("total_deposit")
    @Expose
    private Integer totalDeposit;
    @SerializedName("total_left_bv")
    @Expose
    private Integer totalLeftBv;
    @SerializedName("total_right_bv")
    @Expose
    private Integer totalRightBv;
    @SerializedName("coin")
    @Expose
    private Integer coin;
    @SerializedName("btc")
    @Expose
    private Integer btc;
    @SerializedName("usd")
    @Expose
    private Integer usd;
    @SerializedName("total_investment")
    @Expose
    private Integer totalInvestment;
    @SerializedName("active_investment")
    @Expose
    private Integer activeInvestment;
    @SerializedName("total_withdraw")
    @Expose
    private Integer totalWithdraw;
    @SerializedName("total_profit")
    @Expose
    private Integer totalProfit;
    @SerializedName("direct_income")
    @Expose
    private Integer directIncome;
    @SerializedName("direct_income_withdraw")
    @Expose
    private Integer directIncomeWithdraw;
    @SerializedName("direct_wallet_balance")
    @Expose
    private Integer directWalletBalance;
    @SerializedName("award")
    @Expose
    private Integer award;
    @SerializedName("direct_ref_income")
    @Expose
    private Integer directRefIncome;
    @SerializedName("direct_ref_income_withdraw")
    @Expose
    private Integer directRefIncomeWithdraw;
    @SerializedName("direct_ref_wallet_balance")
    @Expose
    private Integer directRefWalletBalance;
    @SerializedName("level_income")
    @Expose
    private Integer levelIncome;
    @SerializedName("level_income_roi")
    @Expose
    private Integer levelIncomeRoi;
    @SerializedName("level_income_withdraw")
    @Expose
    private Integer levelIncomeWithdraw;
    @SerializedName("level_income_balance")
    @Expose
    private Integer levelIncomeBalance;
    @SerializedName("roi_income")
    @Expose
    private Integer roiIncome;
    @SerializedName("roi_income_withdraw")
    @Expose
    private Integer roiIncomeWithdraw;
    @SerializedName("roi_income_balance")
    @Expose
    private Integer roiIncomeBalance;
    @SerializedName("binary_income")
    @Expose
    private Integer binaryIncome;
    @SerializedName("binary_income_withdraw")
    @Expose
    private Integer binaryIncomeWithdraw;
    @SerializedName("binary_income_balance")
    @Expose
    private Integer binaryIncomeBalance;
    @SerializedName("direct_income_balance")
    @Expose
    private Integer directIncomeBalance;
    @SerializedName("top_up_wallet")
    @Expose
    private Integer topUpWallet;
    @SerializedName("top_up_wallet_withdraw")
    @Expose
    private Integer topUpWalletWithdraw;
    @SerializedName("top_up_Wallet_balance")
    @Expose
    private Integer topUpWalletBalance;
    @SerializedName("transfer_wallet")
    @Expose
    private Integer transferWallet;
    @SerializedName("transfer_wallet_withdraw")
    @Expose
    private Integer transferWalletWithdraw;
    @SerializedName("transfer_Wallet_balance")
    @Expose
    private Integer transferWalletBalance;
    @SerializedName("working_wallet")
    @Expose
    private Integer workingWallet;
    @SerializedName("working_wallet_withdraw")
    @Expose
    private Integer workingWalletWithdraw;
    @SerializedName("principal_income")
    @Expose
    private Integer principalIncome;
    @SerializedName("principal_income_withdraw")
    @Expose
    private Integer principalIncomeWithdraw;
    @SerializedName("working_Wallet_balance")
    @Expose
    private Integer workingWalletBalance;
    @SerializedName("principle_Wallet_balance")
    @Expose
    private Integer principleWalletBalance;
    @SerializedName("leadership_income")
    @Expose
    private Integer leadershipIncome;
    @SerializedName("leadership_income_withdraw")
    @Expose
    private Integer leadershipIncomeWithdraw;
    @SerializedName("leadership_Wallet_balance")
    @Expose
    private Integer leadershipWalletBalance;
    @SerializedName("level_income_roi_withdraw")
    @Expose
    private Integer levelIncomeRoiWithdraw;
    @SerializedName("level_income_roi_balance")
    @Expose
    private Integer levelIncomeRoiBalance;
    @SerializedName("upline_income")
    @Expose
    private Integer uplineIncome;
    @SerializedName("upline_income_withdraw")
    @Expose
    private Integer uplineIncomeWithdraw;
    @SerializedName("upline_balance")
    @Expose
    private Integer uplineBalance;
    @SerializedName("award_income")
    @Expose
    private Integer awardIncome;
    @SerializedName("award_income_withdraw")
    @Expose
    private Integer awardIncomeWithdraw;
    @SerializedName("award_balance")
    @Expose
    private Integer awardBalance;
    @SerializedName("promotional_income")
    @Expose
    private Integer promotionalIncome;
    @SerializedName("show_popup")
    @Expose
    private Integer showPopup;
    @SerializedName("lasttopup_pin")
    @Expose
    private String lasttopupPin;
    @SerializedName("lasttopup_amount")
    @Expose
    private Integer lasttopupAmount;
    @SerializedName("lasttopup_per")
    @Expose
    private String lasttopupPer;
    @SerializedName("server_time")
    @Expose
    private ServerTimeData serverTime;
    @SerializedName("joining_date")
    @Expose
    private String joiningDate;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("fullname")
    @Expose
    private String fullname;
    @SerializedName("lid")
    @Expose
    private Integer lid;
    @SerializedName("rid")
    @Expose
    private Integer rid;
    @SerializedName("ip_address")
    @Expose
    private String ipAddress;
    @SerializedName("current_time")
    @Expose
    private Boolean currentTime;
    @SerializedName("total_income")
    @Expose
    private Integer totalIncome;
    @SerializedName("balance")
    @Expose
    private Integer balance;
    @SerializedName("last_investment")
    @Expose
    private Integer lastInvestment;
    @SerializedName("totalBusiness")
    @Expose
    private Integer totalBusiness;
    @SerializedName("last_roi_withdraw")
    @Expose
    private Integer lastRoiWithdraw;
    @SerializedName("pending_roi_withdraw")
    @Expose
    private Integer pendingRoiWithdraw;
    @SerializedName("confirmed_roi_withdraw")
    @Expose
    private Integer confirmedRoiWithdraw;
    @SerializedName("last_working_withdraw")
    @Expose
    private Integer lastWorkingWithdraw;
    @SerializedName("pending_working_withdraw")
    @Expose
    private Integer pendingWorkingWithdraw;
    @SerializedName("confirmed_working_withdraw")
    @Expose
    private Integer confirmedWorkingWithdraw;
    @SerializedName("total_team")
    @Expose
    private Integer totalTeam;
    @SerializedName("total_directs")
    @Expose
    private Integer totalDirects;
    @SerializedName("login_time")
    @Expose
    private String loginTime;
}
