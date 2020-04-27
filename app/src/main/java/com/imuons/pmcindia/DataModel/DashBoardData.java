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

    public String getBtcAddress() {
        return btcAddress;
    }

    public void setBtcAddress(String btcAddress) {
        this.btcAddress = btcAddress;
    }

    public Integer getWinningIncome() {
        return winningIncome;
    }

    public void setWinningIncome(Integer winningIncome) {
        this.winningIncome = winningIncome;
    }

    public String getWinningWalletName() {
        return winningWalletName;
    }

    public void setWinningWalletName(String winningWalletName) {
        this.winningWalletName = winningWalletName;
    }

    public Integer getTotalDeposit() {
        return totalDeposit;
    }

    public void setTotalDeposit(Integer totalDeposit) {
        this.totalDeposit = totalDeposit;
    }

    public Integer getTotalLeftBv() {
        return totalLeftBv;
    }

    public void setTotalLeftBv(Integer totalLeftBv) {
        this.totalLeftBv = totalLeftBv;
    }

    public Integer getTotalRightBv() {
        return totalRightBv;
    }

    public void setTotalRightBv(Integer totalRightBv) {
        this.totalRightBv = totalRightBv;
    }

    public Integer getCoin() {
        return coin;
    }

    public void setCoin(Integer coin) {
        this.coin = coin;
    }

    public Integer getBtc() {
        return btc;
    }

    public void setBtc(Integer btc) {
        this.btc = btc;
    }

    public Integer getUsd() {
        return usd;
    }

    public void setUsd(Integer usd) {
        this.usd = usd;
    }

    public Integer getTotalInvestment() {
        return totalInvestment;
    }

    public void setTotalInvestment(Integer totalInvestment) {
        this.totalInvestment = totalInvestment;
    }

    public Integer getActiveInvestment() {
        return activeInvestment;
    }

    public void setActiveInvestment(Integer activeInvestment) {
        this.activeInvestment = activeInvestment;
    }

    public Integer getTotalWithdraw() {
        return totalWithdraw;
    }

    public void setTotalWithdraw(Integer totalWithdraw) {
        this.totalWithdraw = totalWithdraw;
    }

    public Integer getTotalProfit() {
        return totalProfit;
    }

    public void setTotalProfit(Integer totalProfit) {
        this.totalProfit = totalProfit;
    }

    public Integer getDirectIncome() {
        return directIncome;
    }

    public void setDirectIncome(Integer directIncome) {
        this.directIncome = directIncome;
    }

    public Integer getDirectIncomeWithdraw() {
        return directIncomeWithdraw;
    }

    public void setDirectIncomeWithdraw(Integer directIncomeWithdraw) {
        this.directIncomeWithdraw = directIncomeWithdraw;
    }

    public Integer getDirectWalletBalance() {
        return directWalletBalance;
    }

    public void setDirectWalletBalance(Integer directWalletBalance) {
        this.directWalletBalance = directWalletBalance;
    }

    public Integer getAward() {
        return award;
    }

    public void setAward(Integer award) {
        this.award = award;
    }

    public Integer getDirectRefIncome() {
        return directRefIncome;
    }

    public void setDirectRefIncome(Integer directRefIncome) {
        this.directRefIncome = directRefIncome;
    }

    public Integer getDirectRefIncomeWithdraw() {
        return directRefIncomeWithdraw;
    }

    public void setDirectRefIncomeWithdraw(Integer directRefIncomeWithdraw) {
        this.directRefIncomeWithdraw = directRefIncomeWithdraw;
    }

    public Integer getDirectRefWalletBalance() {
        return directRefWalletBalance;
    }

    public void setDirectRefWalletBalance(Integer directRefWalletBalance) {
        this.directRefWalletBalance = directRefWalletBalance;
    }

    public Integer getLevelIncome() {
        return levelIncome;
    }

    public void setLevelIncome(Integer levelIncome) {
        this.levelIncome = levelIncome;
    }

    public Integer getLevelIncomeRoi() {
        return levelIncomeRoi;
    }

    public void setLevelIncomeRoi(Integer levelIncomeRoi) {
        this.levelIncomeRoi = levelIncomeRoi;
    }

    public Integer getLevelIncomeWithdraw() {
        return levelIncomeWithdraw;
    }

    public void setLevelIncomeWithdraw(Integer levelIncomeWithdraw) {
        this.levelIncomeWithdraw = levelIncomeWithdraw;
    }

    public Integer getLevelIncomeBalance() {
        return levelIncomeBalance;
    }

    public void setLevelIncomeBalance(Integer levelIncomeBalance) {
        this.levelIncomeBalance = levelIncomeBalance;
    }

    public Integer getRoiIncome() {
        return roiIncome;
    }

    public void setRoiIncome(Integer roiIncome) {
        this.roiIncome = roiIncome;
    }

    public Integer getRoiIncomeWithdraw() {
        return roiIncomeWithdraw;
    }

    public void setRoiIncomeWithdraw(Integer roiIncomeWithdraw) {
        this.roiIncomeWithdraw = roiIncomeWithdraw;
    }

    public Integer getRoiIncomeBalance() {
        return roiIncomeBalance;
    }

    public void setRoiIncomeBalance(Integer roiIncomeBalance) {
        this.roiIncomeBalance = roiIncomeBalance;
    }

    public Integer getBinaryIncome() {
        return binaryIncome;
    }

    public void setBinaryIncome(Integer binaryIncome) {
        this.binaryIncome = binaryIncome;
    }

    public Integer getBinaryIncomeWithdraw() {
        return binaryIncomeWithdraw;
    }

    public void setBinaryIncomeWithdraw(Integer binaryIncomeWithdraw) {
        this.binaryIncomeWithdraw = binaryIncomeWithdraw;
    }

    public Integer getBinaryIncomeBalance() {
        return binaryIncomeBalance;
    }

    public void setBinaryIncomeBalance(Integer binaryIncomeBalance) {
        this.binaryIncomeBalance = binaryIncomeBalance;
    }

    public Integer getDirectIncomeBalance() {
        return directIncomeBalance;
    }

    public void setDirectIncomeBalance(Integer directIncomeBalance) {
        this.directIncomeBalance = directIncomeBalance;
    }

    public Integer getTopUpWallet() {
        return topUpWallet;
    }

    public void setTopUpWallet(Integer topUpWallet) {
        this.topUpWallet = topUpWallet;
    }

    public Integer getTopUpWalletWithdraw() {
        return topUpWalletWithdraw;
    }

    public void setTopUpWalletWithdraw(Integer topUpWalletWithdraw) {
        this.topUpWalletWithdraw = topUpWalletWithdraw;
    }

    public Integer getTopUpWalletBalance() {
        return topUpWalletBalance;
    }

    public void setTopUpWalletBalance(Integer topUpWalletBalance) {
        this.topUpWalletBalance = topUpWalletBalance;
    }

    public Integer getTransferWallet() {
        return transferWallet;
    }

    public void setTransferWallet(Integer transferWallet) {
        this.transferWallet = transferWallet;
    }

    public Integer getTransferWalletWithdraw() {
        return transferWalletWithdraw;
    }

    public void setTransferWalletWithdraw(Integer transferWalletWithdraw) {
        this.transferWalletWithdraw = transferWalletWithdraw;
    }

    public Integer getTransferWalletBalance() {
        return transferWalletBalance;
    }

    public void setTransferWalletBalance(Integer transferWalletBalance) {
        this.transferWalletBalance = transferWalletBalance;
    }

    public Integer getWorkingWallet() {
        return workingWallet;
    }

    public void setWorkingWallet(Integer workingWallet) {
        this.workingWallet = workingWallet;
    }

    public Integer getWorkingWalletWithdraw() {
        return workingWalletWithdraw;
    }

    public void setWorkingWalletWithdraw(Integer workingWalletWithdraw) {
        this.workingWalletWithdraw = workingWalletWithdraw;
    }

    public Integer getPrincipalIncome() {
        return principalIncome;
    }

    public void setPrincipalIncome(Integer principalIncome) {
        this.principalIncome = principalIncome;
    }

    public Integer getPrincipalIncomeWithdraw() {
        return principalIncomeWithdraw;
    }

    public void setPrincipalIncomeWithdraw(Integer principalIncomeWithdraw) {
        this.principalIncomeWithdraw = principalIncomeWithdraw;
    }

    public Integer getWorkingWalletBalance() {
        return workingWalletBalance;
    }

    public void setWorkingWalletBalance(Integer workingWalletBalance) {
        this.workingWalletBalance = workingWalletBalance;
    }

    public Integer getPrincipleWalletBalance() {
        return principleWalletBalance;
    }

    public void setPrincipleWalletBalance(Integer principleWalletBalance) {
        this.principleWalletBalance = principleWalletBalance;
    }

    public Integer getLeadershipIncome() {
        return leadershipIncome;
    }

    public void setLeadershipIncome(Integer leadershipIncome) {
        this.leadershipIncome = leadershipIncome;
    }

    public Integer getLeadershipIncomeWithdraw() {
        return leadershipIncomeWithdraw;
    }

    public void setLeadershipIncomeWithdraw(Integer leadershipIncomeWithdraw) {
        this.leadershipIncomeWithdraw = leadershipIncomeWithdraw;
    }

    public Integer getLeadershipWalletBalance() {
        return leadershipWalletBalance;
    }

    public void setLeadershipWalletBalance(Integer leadershipWalletBalance) {
        this.leadershipWalletBalance = leadershipWalletBalance;
    }

    public Integer getLevelIncomeRoiWithdraw() {
        return levelIncomeRoiWithdraw;
    }

    public void setLevelIncomeRoiWithdraw(Integer levelIncomeRoiWithdraw) {
        this.levelIncomeRoiWithdraw = levelIncomeRoiWithdraw;
    }

    public Integer getLevelIncomeRoiBalance() {
        return levelIncomeRoiBalance;
    }

    public void setLevelIncomeRoiBalance(Integer levelIncomeRoiBalance) {
        this.levelIncomeRoiBalance = levelIncomeRoiBalance;
    }

    public Integer getUplineIncome() {
        return uplineIncome;
    }

    public void setUplineIncome(Integer uplineIncome) {
        this.uplineIncome = uplineIncome;
    }

    public Integer getUplineIncomeWithdraw() {
        return uplineIncomeWithdraw;
    }

    public void setUplineIncomeWithdraw(Integer uplineIncomeWithdraw) {
        this.uplineIncomeWithdraw = uplineIncomeWithdraw;
    }

    public Integer getUplineBalance() {
        return uplineBalance;
    }

    public void setUplineBalance(Integer uplineBalance) {
        this.uplineBalance = uplineBalance;
    }

    public Integer getAwardIncome() {
        return awardIncome;
    }

    public void setAwardIncome(Integer awardIncome) {
        this.awardIncome = awardIncome;
    }

    public Integer getAwardIncomeWithdraw() {
        return awardIncomeWithdraw;
    }

    public void setAwardIncomeWithdraw(Integer awardIncomeWithdraw) {
        this.awardIncomeWithdraw = awardIncomeWithdraw;
    }

    public Integer getAwardBalance() {
        return awardBalance;
    }

    public void setAwardBalance(Integer awardBalance) {
        this.awardBalance = awardBalance;
    }

    public Integer getPromotionalIncome() {
        return promotionalIncome;
    }

    public void setPromotionalIncome(Integer promotionalIncome) {
        this.promotionalIncome = promotionalIncome;
    }

    public Integer getShowPopup() {
        return showPopup;
    }

    public void setShowPopup(Integer showPopup) {
        this.showPopup = showPopup;
    }

    public String getLasttopupPin() {
        return lasttopupPin;
    }

    public void setLasttopupPin(String lasttopupPin) {
        this.lasttopupPin = lasttopupPin;
    }

    public Integer getLasttopupAmount() {
        return lasttopupAmount;
    }

    public void setLasttopupAmount(Integer lasttopupAmount) {
        this.lasttopupAmount = lasttopupAmount;
    }

    public String getLasttopupPer() {
        return lasttopupPer;
    }

    public void setLasttopupPer(String lasttopupPer) {
        this.lasttopupPer = lasttopupPer;
    }

    public ServerTimeData getServerTime() {
        return serverTime;
    }

    public void setServerTime(ServerTimeData serverTime) {
        this.serverTime = serverTime;
    }

    public String getJoiningDate() {
        return joiningDate;
    }

    public void setJoiningDate(String joiningDate) {
        this.joiningDate = joiningDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public Integer getLid() {
        return lid;
    }

    public void setLid(Integer lid) {
        this.lid = lid;
    }

    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public Boolean getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(Boolean currentTime) {
        this.currentTime = currentTime;
    }

    public Integer getTotalIncome() {
        return totalIncome;
    }

    public void setTotalIncome(Integer totalIncome) {
        this.totalIncome = totalIncome;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    public Integer getLastInvestment() {
        return lastInvestment;
    }

    public void setLastInvestment(Integer lastInvestment) {
        this.lastInvestment = lastInvestment;
    }

    public Integer getTotalBusiness() {
        return totalBusiness;
    }

    public void setTotalBusiness(Integer totalBusiness) {
        this.totalBusiness = totalBusiness;
    }

    public Integer getLastRoiWithdraw() {
        return lastRoiWithdraw;
    }

    public void setLastRoiWithdraw(Integer lastRoiWithdraw) {
        this.lastRoiWithdraw = lastRoiWithdraw;
    }

    public Integer getPendingRoiWithdraw() {
        return pendingRoiWithdraw;
    }

    public void setPendingRoiWithdraw(Integer pendingRoiWithdraw) {
        this.pendingRoiWithdraw = pendingRoiWithdraw;
    }

    public Integer getConfirmedRoiWithdraw() {
        return confirmedRoiWithdraw;
    }

    public void setConfirmedRoiWithdraw(Integer confirmedRoiWithdraw) {
        this.confirmedRoiWithdraw = confirmedRoiWithdraw;
    }

    public Integer getLastWorkingWithdraw() {
        return lastWorkingWithdraw;
    }

    public void setLastWorkingWithdraw(Integer lastWorkingWithdraw) {
        this.lastWorkingWithdraw = lastWorkingWithdraw;
    }

    public Integer getPendingWorkingWithdraw() {
        return pendingWorkingWithdraw;
    }

    public void setPendingWorkingWithdraw(Integer pendingWorkingWithdraw) {
        this.pendingWorkingWithdraw = pendingWorkingWithdraw;
    }

    public Integer getConfirmedWorkingWithdraw() {
        return confirmedWorkingWithdraw;
    }

    public void setConfirmedWorkingWithdraw(Integer confirmedWorkingWithdraw) {
        this.confirmedWorkingWithdraw = confirmedWorkingWithdraw;
    }

    public Integer getTotalTeam() {
        return totalTeam;
    }

    public void setTotalTeam(Integer totalTeam) {
        this.totalTeam = totalTeam;
    }

    public Integer getTotalDirects() {
        return totalDirects;
    }

    public void setTotalDirects(Integer totalDirects) {
        this.totalDirects = totalDirects;
    }

    public String getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(String loginTime) {
        this.loginTime = loginTime;
    }
}
