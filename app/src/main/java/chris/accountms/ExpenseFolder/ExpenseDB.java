/*
孟渝桓 18221785
2020移动平台程序设计
 */
package chris.accountms.ExpenseFolder;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class ExpenseDB {
    Context context;
    ExpenseDBOpenHelper expenseDBOpenHelper;
    SQLiteDatabase mydatabase;

    public ExpenseDB(Context context) {
        this.context = context;
        expenseDBOpenHelper = new ExpenseDBOpenHelper(context);
    }

    public ArrayList<Expense> getarray() {            //获取listview中要显示的数据
        ArrayList<Expense> arr = new ArrayList<Expense>();
        ArrayList<Expense> arr1 = new ArrayList<Expense>();
        mydatabase = expenseDBOpenHelper.getWritableDatabase();
        Cursor cursor = mydatabase.rawQuery("select ids, money, type, date from myexpense", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            int id = cursor.getInt(cursor.getColumnIndex("ids"));
            String type = cursor.getString(cursor.getColumnIndex("type"));
            String money = cursor.getString(cursor.getColumnIndex("money"));
            String date = cursor.getString(cursor.getColumnIndex("date"));
            Expense expense = new Expense(id, type, money, date);
            arr.add(expense);
            cursor.moveToNext();
        }
        mydatabase.close();
        for (int i = arr.size(); i > 0; i--) {
            arr1.add(arr.get(i - 1));
        }
        return arr1;
    }

    public Expense getCon(int id) {           //获取要修改数据（就是当选择listview子项想要修改数据时，获取数据显示在新建页面）
        mydatabase = expenseDBOpenHelper.getWritableDatabase();
        Cursor cursor = mydatabase.rawQuery("select money, date, type, payer, remark from myexpense where ids='" + id + "'", null);
        cursor.moveToFirst();
        String money = cursor.getString(cursor.getColumnIndex("money"));
        String date = cursor.getString(cursor.getColumnIndex("date"));
        String type = cursor.getString(cursor.getColumnIndex("type"));
        String payer = cursor.getString(cursor.getColumnIndex("payer"));
        String remark = cursor.getString(cursor.getColumnIndex("remark"));
        Expense expense = new Expense(money, date, type, payer, remark);
        mydatabase.close();
        return expense;
    }

    public void toUpdate(Expense expense) {           //修改表中数据
        mydatabase = expenseDBOpenHelper.getWritableDatabase();
        mydatabase.execSQL(
                "update myexpense set money='" + expense.getMoney() +
                        "',date='" + expense.getDate() +
                        "',type='" + expense.getType() +
                        "',payer='" + expense.getPayer() +
                        "',remark='" + expense.getRemark() +
                        "' where ids='" + expense.getIds()
                        + "'");
        mydatabase.close();
    }

    public void toInsert(Expense expense) {           //在表中插入新建的便签的数据
        mydatabase = expenseDBOpenHelper.getWritableDatabase();
        mydatabase.execSQL("insert into myexpense(money, date, type, payer, remark)values('"
                + expense.getMoney() + "','"
                + expense.getDate() + "','"
                + expense.getType() + "','"
                + expense.getPayer() + "','"
                + expense.getRemark()
                + "')");
        mydatabase.close();
    }

    public void toDelete(int ids) {            //在表中删除数据
        mydatabase = expenseDBOpenHelper.getWritableDatabase();
        mydatabase.execSQL("delete from myexpense where ids=" + ids + "");
        mydatabase.close();
    }

    public void toDeleteAllData() {            //在表中删除数据
        mydatabase = expenseDBOpenHelper.getWritableDatabase();
        mydatabase.execSQL("delete from myexpense");
    }

    public Expense[] queryAllData() {
        mydatabase = expenseDBOpenHelper.getWritableDatabase();
        Cursor results = mydatabase.rawQuery("select money, date, type, payer, remark from myexpense", null);
        return ConvertToExpense(results);
    }

    private Expense[] ConvertToExpense(Cursor cursor) {
        int resultCounts = cursor.getCount();
        if (resultCounts == 0 || !cursor.moveToFirst()) {
            return null;
        }

        Expense[] expenses = new Expense[resultCounts];
        for (int i = 0; i < resultCounts; i++) {
            expenses[i] = new Expense();
            expenses[i].ids = cursor.getInt(0);
            expenses[i].money = String.valueOf(cursor.getFloat(cursor.getColumnIndex("money")));
            expenses[i].date = cursor.getString(cursor.getColumnIndex("date"));
            expenses[i].type = cursor.getString(cursor.getColumnIndex("type"));
            expenses[i].payer = cursor.getString(cursor.getColumnIndex("payer"));
            expenses[i].remark = cursor.getString(cursor.getColumnIndex("remark"));
            cursor.moveToNext();
        }
        return expenses;
    }
}

