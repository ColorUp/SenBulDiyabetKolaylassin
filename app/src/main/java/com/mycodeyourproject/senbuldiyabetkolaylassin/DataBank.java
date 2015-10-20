package com.mycodeyourproject.senbuldiyabetkolaylassin;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by Burak on 30.07.2015.
 */
public class DataBank extends BaseViaDiabetActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_bank);

        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String userValue = sharedPref.getString(getString(R.string.signeduser), "");

        //Veritabanından verilerin hepsi okunur
        List<Map<Object, Object>> userDataList = DataTransferObjects.UserDatalog.getUserDatalogList(userValue);
        Date bugun = Calendar.getInstance().getTime();
        long DAY_IN_MS = 1000 * 60 * 60 * 24;
        Date yediGunOnce = new Date(bugun.getTime() - (7 * DAY_IN_MS));
        Date onDortGunOnce = new Date(bugun.getTime() - (14 * DAY_IN_MS));
        Date otuzGunOnce = new Date(bugun.getTime() - (30 * DAY_IN_MS));
        Date doksanGunOnce = new Date(bugun.getTime() - (90 * DAY_IN_MS));
        List<Map<Object, Object>> sonYediGunlukDegerler = new LinkedList<>();
        List<Map<Object, Object>> sonOnDortGunlukDegerler = new LinkedList<>();
        List<Map<Object, Object>> sonOtuzGunlukDegerler = new LinkedList<>();
        List<Map<Object, Object>> sonDoksanGunlukDegerler = new LinkedList<>();

        for (int say = 0; say < userDataList.size(); say++) {
            Date dataDate = Converter.StringToDate(userDataList.get(say).get("DATETIME").toString());
            Map<Object, Object> data = userDataList.get(say);
            if (dataDate.compareTo(doksanGunOnce) > 0)
                sonDoksanGunlukDegerler.add(data);
            if (dataDate.compareTo(otuzGunOnce) > 0)
                sonOtuzGunlukDegerler.add(data);
            if (dataDate.compareTo(onDortGunOnce) > 0)
                sonOnDortGunlukDegerler.add(data);
            if (dataDate.compareTo(yediGunOnce) > 0)
                sonYediGunlukDegerler.add(data);
        }

        Comparator<Object> cmp = new Comparator<Object>() {
            @Override
            public int compare(Object o1, Object o2) {
                return Integer.valueOf(((Map<Object, Object>) o1).get("ID").toString()).compareTo(Integer.valueOf(((Map<Object, Object>) o2).get("ID").toString()));
            }
        };

        String process = "";
        String value = "";
        String date = "";
        int count = 0;
        Float average = 0f;
        Float min = 0f;
        Float max = 0f;
        String analysis = "";
        Float weightStat = 0f;
        Float weightChange = 0f;

        List<Map<Object, Object>> degerler = new LinkedList<>();

        for (int k = 0; k < 4; k++) {
            Map<Object, Object> lastData = Collections.max(userDataList, cmp); //Son kaydı bulur
            date = lastData.get("DATETIME").toString();
            float glucose = Float.parseFloat(lastData.get("GLUCOSE").toString());
            float weight = Float.parseFloat(lastData.get("WEIGHT").toString());
            float hba1c = Float.parseFloat(lastData.get("HBA1C").toString());
            if (glucose > 0) {
                process = "Kan Ölçümü";
                value = Extensions.Format("{0} {1}", String.valueOf(glucose), "mg/dL");
            } else if (weight > 0) {
                process = "Ağırlık Ölçümü";
                value = Extensions.Format("{0} {1}", String.valueOf(weight), "kg");
            } else if (hba1c > 0) {
                process = "Son 3 Aylık Kan Ölçümü";
                value = Extensions.Format("{0} {1}", String.valueOf(hba1c), "%");
            }

            TextView textView = null;
            TextView txtProcess = null;
            TextView txtValue = null;
            TextView txtCount = null;
            TextView txtAverage = null;
            TextView txtMin = null;
            TextView txtMax = null;
            TextView txtAnalysis = null;
            TextView txtWeightStat = null;

            if (k == 0) {
                textView = (TextView) findViewById(R.id.lastdata_datetime);
                txtProcess = (TextView) findViewById(R.id.lastdata_process);
                txtValue = (TextView) findViewById(R.id.lastdata_value);
                degerler = sonYediGunlukDegerler;
                txtAnalysis = (TextView) findViewById(R.id.lastdata_analysis);
                txtAverage = (TextView) findViewById(R.id.lastdata_average);
                txtCount = (TextView) findViewById(R.id.lastdata_count);
                txtMax = (TextView) findViewById(R.id.lastdata_max);
                txtMin = (TextView) findViewById(R.id.lastdata_min);
                txtWeightStat = (TextView) findViewById(R.id.lastdata_weight);
            } else if (k == 1) {
                textView = (TextView) findViewById(R.id.lastdata1_datetime);
                txtProcess = (TextView) findViewById(R.id.lastdata1_process);
                txtValue = (TextView) findViewById(R.id.lastdata1_value);
                degerler = sonOnDortGunlukDegerler;
                txtAnalysis = (TextView) findViewById(R.id.lastdata1_analysis);
                txtAverage = (TextView) findViewById(R.id.lastdata1_average);
                txtCount = (TextView) findViewById(R.id.lastdata1_count);
                txtMax = (TextView) findViewById(R.id.lastdata1_max);
                txtMin = (TextView) findViewById(R.id.lastdata1_min);
                txtWeightStat = (TextView) findViewById(R.id.lastdata1_weight);
            } else if (k == 2) {
                textView = (TextView) findViewById(R.id.lastdata2_datetime);
                txtProcess = (TextView) findViewById(R.id.lastdata2_process);
                txtValue = (TextView) findViewById(R.id.lastdata2_value);
                degerler = sonOtuzGunlukDegerler;
                txtAnalysis = (TextView) findViewById(R.id.lastdata2_analysis);
                txtAverage = (TextView) findViewById(R.id.lastdata2_average);
                txtCount = (TextView) findViewById(R.id.lastdata2_count);
                txtMax = (TextView) findViewById(R.id.lastdata2_max);
                txtMin = (TextView) findViewById(R.id.lastdata2_min);
                txtWeightStat = (TextView) findViewById(R.id.lastdata2_weight);
            } else if (k == 3) {
                textView = (TextView) findViewById(R.id.lastdata3_datetime);
                txtProcess = (TextView) findViewById(R.id.lastdata3_process);
                txtValue = (TextView) findViewById(R.id.lastdata3_value);
                degerler = sonDoksanGunlukDegerler;
                txtAnalysis = (TextView) findViewById(R.id.lastdata3_analysis);
                txtAverage = (TextView) findViewById(R.id.lastdata3_average);
                txtCount = (TextView) findViewById(R.id.lastdata3_count);
                txtMax = (TextView) findViewById(R.id.lastdata3_max);
                txtMin = (TextView) findViewById(R.id.lastdata3_min);
                txtWeightStat = (TextView) findViewById(R.id.lastdata3_weight);
            }

            count = degerler.size();
            for (int son = 0; son < degerler.size(); son++) {
                Float kanDegeri = Float.parseFloat(degerler.get(son).get("GLUCOSE").toString());
                if (son == 0)
                    min = kanDegeri;

                if (kanDegeri < min)
                    min = kanDegeri;

                if (kanDegeri > max)
                    max = kanDegeri;

                average = average + kanDegeri;

                if (weight > 0) {
                    if (weightStat <= 0)
                        weightStat = weight;

                    if (weightStat > weight)
                        weightStat = weight;
                }
            }

            average = average / count;

            textView.setText(date);
            txtProcess.setText(process);
            txtValue.setText(value);

            txtCount.setText(String.valueOf(count));
            txtAverage.setText(String.valueOf(average));
            txtMin.setText(String.valueOf(min));
            txtMax.setText(String.valueOf(max));

            if (average < 80)
                analysis = "Düşük";
            else if (80 < average && average < 120)
                analysis = "Normal";
            else if (120 < average && average < 200)
                analysis = "Biraz Yüksek";
            else if (200 < average)
                analysis = "Yüksek";
            txtAnalysis.setText(analysis);

            txtWeightStat.setText(String.valueOf(weightStat));

            userDataList.remove(lastData);
        }

        Map<Object, Object> userLifeStyle = DataTransferObjects.UserLifeStyle.getUserLifeStyleList(userValue).get(0);
        Float userWeight = Float.parseFloat(userLifeStyle.get("WEIGHT").toString());
        Float degisim = 0f;
        TextView weightChange4 = (TextView) findViewById(R.id.lastdata3_weightchange);
        TextView weightStat4 = (TextView) findViewById(R.id.lastdata3_weight);
        degisim = Float.parseFloat(weightStat4.getText().toString()) - userWeight;
        weightChange4.setText(String.valueOf(degisim));
        TextView weightChange3 = (TextView) findViewById(R.id.lastdata2_weightchange);
        TextView weightStat3 = (TextView) findViewById(R.id.lastdata2_weight);
        degisim = Float.parseFloat(weightStat3.getText().toString()) - Float.parseFloat(weightStat4.getText().toString());
        weightChange3.setText(String.valueOf(degisim));
        TextView weightChange2 = (TextView) findViewById(R.id.lastdata1_weightchange);
        TextView weightStat2 = (TextView) findViewById(R.id.lastdata1_weight);
        degisim = Float.parseFloat(weightStat2.getText().toString()) - Float.parseFloat(weightStat3.getText().toString());
        weightChange2.setText(String.valueOf(degisim));
        TextView weightChange1 = (TextView) findViewById(R.id.lastdata_weightchange);
        TextView weightStat1 = (TextView) findViewById(R.id.lastdata_weight);
        degisim = Float.parseFloat(weightStat1.getText().toString()) - Float.parseFloat(weightStat2.getText().toString());
        weightChange1.setText(String.valueOf(degisim));
    }
}