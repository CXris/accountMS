/*
孟渝桓 18221785
2020移动平台程序设计
 */
package chris.accountms.ExpenseFolder;

public class Expense {
    public int ids;        //编号
    public String money;   //标题
    public String date;   //标题
    public String type;   //标题
    public String payer; //内容
    public String remark;   //时间

    public Expense(String money, String date, String type, String payer, String remark) {
        this.money = money;
        this.date = date;
        this.type = type;
        this.payer = payer;
        this.remark = remark;
    }

    public Expense(String money, int ids, String date, String type, String payer, String remark) {
        this.money = money;
        this.ids = ids;
        this.date = date;
        this.type = type;
        this.payer = payer;
        this.remark = remark;
    }

    public Expense(int id, String type, String money, String date) {
        this.ids = id;
        this.money = money;
        this.date = date;
        this.type = type;
    }

    public Expense() {

    }

    public int getIds() {
        return ids;
    }

    public String getMoney() {
        return String.valueOf(money);
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