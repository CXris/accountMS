/*
孟渝桓 18221785
2020移动平台程序设计
 */
package chris.accountms.Activities;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

public class XAxisValueFormatter implements IAxisValueFormatter {

    private final String[] mLabels;

    public XAxisValueFormatter(String[] labels) {
        mLabels = labels;
    }

    @Override
    public String getFormattedValue(float value, AxisBase axis) {
        try {
            return mLabels[(int) value];
        } catch (Exception e) {
            e.printStackTrace();
            return mLabels[0];
        }
    }

    @Override
    public int getDecimalDigits() {
        return 0;
    }
}

