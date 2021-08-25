/*
孟渝桓 18221785
2020移动平台程序设计
 */
package chris.accountms.IncomeFolder;

public class Income {
    public int ids;        //编号
    public String money;   //金额
    public String date;    //时间
    public String type;    //种类
    public String payer;   //付款方
    public String remark;  //备注

    public Income(String money, String date, String type, String payer, String remark) {
        this.money = money;
        this.date = date;
        this.type = type;
        this.payer = payer;
        this.remark = remark;
    }

    public Income(String money, int ids, String date, String type, String payer, String remark) {
        this.money = money;
        this.ids = ids;
        this.date = date;
        this.type = type;
        this.payer = payer;
        this.remark = remark;
    }

    public Income(int ids, String type, String money, String date) {
        this.ids = ids;
        this.money = money;
        this.date = date;
        this.type = type;
    }

    public Income() {

    }

    public int getIds() {
        return ids;
    }


    public String getMoney() {
        return money;
    }

    public Float MoneyNumber() {
        return Float.valueOf(this.money);
    }

    public String getDate() {
        return date;
    }

    public String getType() {
        return type;
    }

    public String getPayer() {
        return payer;
    }

    public String getRemark() {
        return remark;
    }
}