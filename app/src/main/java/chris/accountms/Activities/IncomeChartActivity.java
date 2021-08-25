/*
孟渝桓 18221785
2020移动平台程序设计
 */
package chris.accountms.Activities;

import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.interfaces.datasets.IDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

import chris.accountms.IncomeFolder.Income;
import chris.accountms.IncomeFolder.IncomeDB;
import chris.accountms.R;

public class IncomeChartActivity extends AppCompatActivity {

    private IncomeDB incomeDB;
    private BarChart mBarChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_income_chart);
        initView();
    }

    private void initView() {
        //基本控件
        mBarChart = (BarChart) findViewById(R.id.mBarChart);
        mBarChart.setContentDescription("收入统计");
        mBarChart.setMaxVisibleValueCount(60);
        mBarChart.setPinchZoom(true);
        mBarChart.setDrawBarShadow(false);
        mBarChart.setDrawGridBackground(false);
        mBarChart.setScaleEnabled(false);

        XAxis xAxis = mBarChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setTextSize(10f);
        xAxis.setCenterAxisLabels(false);//设置标签居中

        incomeDB = new IncomeDB(this);
        Income[] incomes = incomeDB.queryAllData();
        if (incomes == null) {
            Toast.makeText(this, "数据库中没有收入数据", Toast.LENGTH_SHORT).show();
            return;
        }

        String[] str = new String[incomes.length];
        for(int i = 0; i < incomes.length; i++){
            str[i] = incomes[i].getType();
        }

        XAxisValueFormatter labelFormatter = new XAxisValueFormatter(str);
        xAxis.setValueFormatter(labelFormatter);
        xAxis.setLabelCount(incomes.length);
        xAxis.setAvoidFirstLastClipping(true);

        mBarChart.getAxisLeft().setDrawGridLines(true);
        mBarChart.animateY(2500);
        mBarChart.getLegend().setEnabled(true);

        setData();
    }

    private void setData() {

        incomeDB = new IncomeDB(this);
        Income[] incomes = incomeDB.queryAllData();
        if (incomes == null) {
            Toast.makeText(this, "数据库中没有收入数据", Toast.LENGTH_SHORT).show();
            return;
        }

        ArrayList<BarEntry> yVals = new ArrayList<BarEntry>();
        for (int i = 0; i < incomes.length; i++) {
            yVals.add(new BarEntry(i, incomes[i].MoneyNumber()));
        }

        BarDataSet set1;
        if (mBarChart.getData() != null && mBarChart.getData().getDataSetCount() > 0) {
            set1 = (BarDataSet) mBarChart.getData().getDataSetByIndex(0);
            set1.setValues(yVals);
            mBarChart.getData().notifyDataChanged();
            mBarChart.notifyDataSetChanged();
        } else {
            set1 = new BarDataSet(yVals, "收入类别");
            //设置多彩 也可以单一颜色
            set1.setColors(ColorTemplate.VORDIPLOM_COLORS);
            set1.setDrawValues(false);

            ArrayList<IBarDataSet> dataSets = new ArrayList<IBarDataSet>();
            dataSets.add(set1);
            BarData data = new BarData(dataSets);
            mBarChart.setData(data);
            mBarChart.setFitBars(true);
        }

        for (IDataSet set : mBarChart.getData().getDataSets())
            set.setDrawValues(!set.isDrawValuesEnabled());
        mBarChart.invalidate();
    }

    public void addincome(View v) {
        Intent intent = new Intent(this, MyIncomeActivity.class);
        startActivity(intent);
        finish();
    }

    public void sumexpense(View v) {
        Intent intent = new Intent(this, ExpenseChartActivity.class);
        startActivity(intent);
        finish();
    }

    public void backtomain(View v) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

}