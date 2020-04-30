package com.imuons.pmcindia.Entity;

import java.io.Serializable;

public class WithdrawAmountEntity implements Serializable {
    String working_wallet,mode;
    Integer addTopup,binary_income_balance,direct_income_balance,level_income_balance,
    principal_income,roi_balance,topup_wallet,transfer_wallet;

    public WithdrawAmountEntity(String working_wallet, String mode, Integer addTopup, Integer binary_income_balance, Integer direct_income_balance, Integer level_income_balance, Integer principal_income, Integer roi_balance, Integer topup_wallet, Integer transfer_wallet) {
        this.working_wallet = working_wallet;
        this.mode = mode;
        this.addTopup = addTopup;
        this.binary_income_balance = binary_income_balance;
        this.direct_income_balance = direct_income_balance;
        this.level_income_balance = level_income_balance;
        this.principal_income = principal_income;
        this.roi_balance = roi_balance;
        this.topup_wallet = topup_wallet;
        this.transfer_wallet = transfer_wallet;
    }


}
