/*
孟渝桓 18221785
2020移动平台程序设计
 */
package chris.accountms.IncomeFolder;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class IncomeDB {
    Context context;
    IncomeDBOpenHelper incomeDBOpenHelper;
    SQLiteDatabase mydatabase;


    public IncomeDB(Context context) {
        this.context = context;
        incomeDBOpenHelper = new IncomeDBOpenHelper(context);
    }

    public ArrayList<Income> getarray() {            //获取listview中要显示的数据
        ArrayList<Income> arr = new ArrayList<Income>();
        ArrayList<Income> arr1 = new ArrayList<Income>();
        mydatabase = incomeDBOpenHelper.getWritableDatabase();
        Cursor cursor = mydatabase.rawQuery("select ids, money, type, date from myincome", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            int id = cursor.getInt(cursor.getColumnIndex("ids"));
            String type = cursor.getString(cursor.getColumnIndex("type"));
            String money = cursor.getString(cursor.getColumnIndex("money"));
            String date = cursor.getString(cursor.getColumnIndex("date"));
            Income income = new Income(id, type, money, date);
            arr.add(income);
            cursor.moveToNext();
        }
        mydatabase.close();
        for (int i = arr.size(); i > 0; i--) {
            arr1.add(arr.get(i - 1));
        }
        return arr1;
    }

    public Income getCon(int id) {           //获取要修改数据（就是当选择listview子项想要修改数据时，获取数据显示在新建页面）
        mydatabase = incomeDBOpenHelper.getWritableDatabase();
        Cursor cursor = mydatabase.rawQuery("select money, date, type, payer, remark from myincome where ids='" + id + "'", null);
        cursor.moveToFirst();
        String money = cursor.getString(cursor.getColumnIndex("money"));
        String date = cursor.getString(cursor.getColumnIndex("date"));
        String type = cursor.getString(cursor.getColumnIndex("type"));
        String payer = cursor.getString(cursor.getColumnIndex("payer"));
        String remark = cursor.getString(cursor.getColumnIndex("remark"));
        Income income = new Income(money, date, type, payer, remark);
        mydatabase.close();
        return income;
    }

    public void toUpdate(Income income) {           //修改表中数据
        mydatabase = incomeDBOpenHelper.getWritableDatabase();
        mydatabase.execSQL(
                "update myincome set money='" + income.getMoney() +
                        "',date='" + income.getDate() +
                        "',type='" + income.getType() +
                        "',payer='" + income.getPayer() +
                        "',remark='" + income.getRemark() +
                        "' where ids='" + income.getIds()
                        + "'");
        mydatabase.close();
    }

    public void toInsert(Income income) {           //在表中插入新建的便签的数据
        mydatabase = incomeDBOpenHelper.getWritableDatabase();
        mydatabase.execSQL("insert into myincome(money, date, type, payer, remark)values('"
                + income.getMoney() + "','"
                + income.getDate() + "','"
                + income.getType() + "','"
                + income.getPayer() + "','"
                + income.getRemark()
                + "')");
        mydatabase.close();
    }

    public void toDelete(int ids) {            //在表中删除数据
        mydatabase = incomeDBOpenHelper.getWritableDatabase();
        mydatabase.execSQL("delete from myincome where ids=" + ids + "");
        mydatabase.close();
    }

    public void toDeleteAllData() {            //在表中删除数据
        mydatabase = incomeDBOpenHelper.getWritableDatabase();
        mydatabase.execSQL("delete from myincome");
        mydatabase.close();
    }

    public Income[] queryAllData() {
        mydatabase = incomeDBOpenHelper.getWritableDatabase();
        Cursor results = mydatabase.rawQuery("select money, date, type, payer, remark from myincome", null);
        return ConvertToIncome(results);
    }

    private Income[] ConvertToIncome(Cursor cursor) {
        int resultCounts = cursor.getCount();
        if (resultCounts == 0 || !cursor.moveToFirst()) {
            return null;
        }

        Income[] incomes = new Income[resultCounts];
        for (int i = 0; i < resultCounts; i++) {
            incomes[i] = new Income();
            incomes[i].ids = cursor.getInt(0);
            incomes[i].money = String.valueOf(cursor.getFloat(cursor.getColumnIndex("money")));
            incomes[i].date = cursor.getString(cursor.getColumnIndex("date"));
            incomes[i].type = cursor.getString(cursor.getColumnIndex("type"));
            incomes[i].payer = cursor.getString(cursor.getColumnIndex("payer"));
            incomes[i].remark = cursor.getString(cursor.getColumnIndex("remark"));
            cursor.moveToNext();
        }
        return incomes;
    }
}