package com.mycodeyourproject.senbuldiyabetkolaylassin;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.provider.ContactsContract;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class MealCalculator extends BaseViaDiabetActivity {
    ArrayList<Parent> list;
    ExpandableListView expListView;
    EditText textBoxCarbonhydrade;
    EditText textBoxProtein;
    EditText textBoxCalori;
    EditText textBoxFat;
    Integer type;
    String userValue;
    Button actionBarSave;
    Button actionBarBack;
    List<Map<Object,Object>> seciliYemekler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_calculator);

        expListView = (ExpandableListView) findViewById(R.id.listView);

        LayoutInflater mInflater = LayoutInflater.from(this);
        View mCustomView = mInflater.inflate(R.layout.actionbar_with_buttons, null);

        //super.mActionBar.setCustomView(mCustomView);
        //mActionBar.setDisplayShowCustomEnabled(true);

        Resources res = this.getResources();
        Drawable devider = res.getDrawable(R.drawable.decorativeline);

        actionBarBack = (Button) mCustomView.findViewById(R.id.actionbar_back_button);
        actionBarBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent data = new Intent(Intent.ACTION_VIEW);
                data.putExtra("Kalori", "0");
                data.putExtra("Protein", "0");
                data.putExtra("Yağ", "0");
                //data.putExtra("Karbonhidrat", "0");
                setResult(0, data);
                finish();
            }
        });

        actionBarSave = (Button) mCustomView.findViewById(R.id.actionbar_save_button);
        actionBarSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent data = new Intent(Intent.ACTION_VIEW);
                data.putExtra("Kalori", textBoxCalori.getText());
                data.putExtra("Protein", textBoxProtein.getText());
                data.putExtra("Yağ", textBoxFat.getText());
                //data.putExtra("Karbonhidrat", textBoxCarbonhydrade.getText());
                setResult(0, data);
                finish();
            }
        });

        //textBoxCarbonhydrade = (EditText) findViewById(R.id.textbox_carbonhydrade).findViewById(R.id.textbox_editText);
        textBoxProtein = (EditText) findViewById(R.id.textbox_protein).findViewById(R.id.textbox_editText);
        textBoxCalori = (EditText) findViewById(R.id.textbox_calori).findViewById(R.id.textbox_editText);
        textBoxFat = (EditText) findViewById(R.id.textbox_fat).findViewById(R.id.textbox_editText);

        // Set ExpandableListView values
        expListView.setGroupIndicator(null);
        expListView.setDivider(devider);
        expListView.setChildDivider(devider);
        expListView.setDividerHeight(1);
        registerForContextMenu(expListView);

        Intent intent = getIntent();
        type = intent.getIntExtra("MealSelector", 0);

        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        userValue = sharedPref.getString(getString(R.string.signeduser), "");

        seciliYemekler = new LinkedList<>();
        List<Map<Object, Object>> yemekler = DataTransferObjects.UserMeal.getUserMealList(userValue);

        if (yemekler != null)
            if (yemekler.size() > 0) {
                for (int i = 0; i < yemekler.size(); i++) {
                    if (yemekler.get(i).get("TYPE").toString().matches(type.toString()))
                        seciliYemekler.add(yemekler.get(i));
                }
            }

        buildMealCalculatorData();

        // Adding ArrayList data to ExpandableListView values
        loadHosts(list);
    }

    //Initialize variables
    private int ParentClickStatus = -1;
    private int ChildClickStatus = -1;
    private ArrayList<Parent> parents;

    private void buildMealCalculatorData() {
        list = new ArrayList<Parent>();

        float calori = 0;
        float protein = 0;
        float fat = 0;

        List<Map<Object, Object>> kategoriler = DataTransferObjects.MealCategory.getMealCategoryList(Long.MIN_VALUE);

        for (int i = 0; i < kategoriler.size(); i++) {
            final Parent parent = new Parent();
            Map<Object, Object> kategori = kategoriler.get(i);

            parent.setId(Long.valueOf(kategori.get("ID").toString()));
            parent.setName(kategori.get("NAME").toString());
            parent.setTotalCal(0);
            //parent.setTotalCarbonhydrade(0);
            parent.setTotalFat(0);
            parent.setTotalProtein(0);
            parent.setChildren(new ArrayList<Child>());

            List<Map<Object, Object>> kategoriYemekleri = DataTransferObjects.Meal.getMealList(parent.getId());

            if (kategoriYemekleri != null) {
                for (int j = 0; j < kategoriYemekleri.size(); j++) {
                    final Child child = new Child();
                    Map<Object, Object> yemek = kategoriYemekleri.get(j);
                    child.setId(Long.valueOf(yemek.get("ID").toString()));
                    child.setName(yemek.get("NAME").toString());
                    child.setCal(Float.valueOf(yemek.get("CALORI").toString()));
                    child.setFat(Float.valueOf(yemek.get("FAT").toString()));
                    child.setProtein(Float.valueOf(yemek.get("PROTEIN").toString()));

                    if (seciliYemekler != null)
                        if (seciliYemekler.size() > 0) {
                            for (int k = 0; k < seciliYemekler.size(); k++) {
                                if (seciliYemekler.get(k).get("MEALID").toString().matches(String.valueOf(child.getId()))) {
                                    child.setChecked(true);
                                    calori = calori + child.getCal();
                                    protein = protein + child.getProtein();
                                    fat = fat + child.getFat();
                                }
                            }
                        }

                    //Add Child class object to parent class object
                    parent.getChildren().add(child);
                }
            }
            //Adding Parent class object to ArrayList
            list.add(parent);
        }

        textBoxCalori.setText(String.valueOf(calori) + " kCal");
        textBoxProtein.setText(String.valueOf(protein) + " gr");
        textBoxFat.setText(String.valueOf(fat) + " kJ");
    }

    private void loadHosts(final ArrayList<Parent> newParents) {
        if (newParents == null)
            return;

        parents = newParents;

        // Check for ExpandableListAdapter object
        if (this.expListView != null) {
            //Create ExpandableListAdapter Object
            final MyExpandableListAdapter mAdapter = new MyExpandableListAdapter();

            // Set Adapter to ExpandableList Adapter
            expListView.setAdapter(mAdapter);
        }
    }

    /**
     * A Custom adapter to create Parent view (Used grouprow.xml) and Child View((Used childrow.xml).
     */
    private class MyExpandableListAdapter extends BaseExpandableListAdapter {
        private LayoutInflater inflater;

        public MyExpandableListAdapter() {
            // Create Layout Inflator
            inflater = LayoutInflater.from(MealCalculator.this);
        }

        // This Function used to inflate parent rows view

        @Override
        public View getGroupView(int groupPosition, boolean isExpanded,
                                 View convertView, ViewGroup parentView) {
            final Parent parent = parents.get(groupPosition);

            // Inflate grouprow.xml file for parent rows
            convertView = inflater.inflate(R.layout.group_row, parentView, false);

            // Get grouprow.xml file elements and set values
            ((TextView) convertView.findViewById(R.id.text_name)).setText(parent.getName());

            parent.setSummary("Yağ: " + parent.getTotalFat() + " kJ, Protein: " + parent.getTotalProtein() +
                    " kJ, Kalori: " + parent.getTotalCal() + " cal");

            ((TextView) convertView.findViewById(R.id.text_summary_group)).setText(parent.getSummary());
/*            ImageView image = (ImageView) convertView.findViewById(R.id.image);

            image.setImageResource(
                    getResources().getIdentifier(
                            "com.mycodeyourproject.senbuldiyabetkolaylassin:drawable/meal" + parent.getId(), null, null));*/

            // Get grouprow.xml file checkbox elements
            CheckBox checkbox = (CheckBox) convertView.findViewById(R.id.checkbox);
            checkbox.setChecked(parent.isChecked());

            // Set CheckUpdateListener for CheckBox (see below CheckUpdateListener class)
            checkbox.setOnCheckedChangeListener(new CheckUpdateListener(parent));

            return convertView;
        }


        // This Function used to inflate child rows view
        @Override
        public View getChildView(int groupPosition, final int childPosition, boolean isLastChild,
                                 View convertView, final ViewGroup parentView) {
            final Parent parent = parents.get(groupPosition);
            final Child child = parent.getChildren().get(childPosition);

            // Inflate childrow.xml file for child rows
            convertView = inflater.inflate(R.layout.child_row, parentView, false);

            child.setSummary(" Kalori: " + child.getCal() +
                    " cal, Yağ: " + child.getFat() + " kJ, Protein: " + child.getProtein() + " kJ");

            // Get childrow.xml file elements and set values
            ((TextView) convertView.findViewById(R.id.text_name)).setText(child.getName());
            ((TextView) convertView.findViewById(R.id.text_summary_row)).setText(child.getSummary());
/*            ImageView image = (ImageView) convertView.findViewById(R.id.image);
            image.setImageResource(
                    getResources().getIdentifier(
                            "com.mycodeyourproject.senbuldiyabetkolaylassin:drawable/meal" + parent.getId(), null, null));*/

            // Get grouprow.xml file checkbox elements
            final TableLayout tableLayout = (TableLayout) convertView.findViewById(R.id.row);
            final CheckBox checkBox = (CheckBox) convertView.findViewById(R.id.checkbox);
            // Set CheckUpdateListener for CheckBox (see below CheckUpdateListener class)
            tableLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    runOnUiThread(new Runnable() {

                        @Override
                        public void run() {

                            View groupRow = LayoutInflater.from(getApplication()).inflate(R.layout.group_row, null);
                            TextView parentSummary = (TextView) groupRow.findViewById(R.id.text_summary_group);

                            if (child.isChecked()) {
                                checkBox.setChecked(false);
                                child.setChecked(false);
                                parent.setTotalCal(parent.getTotalCal() - child.getCal());
                                parent.setTotalProtein(parent.getTotalProtein() - child.getProtein());
                                parent.setTotalFat(parent.getTotalFat() - child.getFat());
                                parent.setSummary("Yağ: " + parent.getTotalFat() + " kJ, Protein: " + parent.getTotalProtein() +
                                        " kJ, Kalori: " + parent.getTotalCal() + " cal");
                                parentSummary.setText(parent.getSummary());
                            } else {
                                checkBox.setChecked(true);
                                child.setChecked(true);
                                parent.setTotalCal(parent.getTotalCal() + child.getCal());
                                parent.setTotalProtein(parent.getTotalProtein() + child.getProtein());
                                parent.setTotalFat(parent.getTotalFat() + child.getFat());
                                parent.setSummary("Yağ: " + parent.getTotalFat() + " kJ, Protein: " + parent.getTotalProtein() +
                                        " kJ, Kalori: " + parent.getTotalCal() + " cal");
                                parentSummary.setText(parent.getSummary());
                            }

                            float calori = 0;
                            float protein = 0;
                            float fat = 0;
                            for (int i = 0; i < list.size(); i++) {
                                Parent parent = list.get(i);
                                calori = calori + parent.getTotalCal();
                                protein = protein + parent.getTotalProtein();
                                fat = fat + parent.getTotalFat();
                            }

                            textBoxCalori.setText(String.valueOf(calori) + " kCal");
                            textBoxProtein.setText(String.valueOf(protein) + " gr");
                            textBoxFat.setText(String.valueOf(fat) + " kJ");
                        }
                    });

                }
            });

            return convertView;
        }

        @Override
        public Object getChild(int groupPosition, int childPosition) {
            //Log.i("Childs", groupPosition+"=  getChild =="+childPosition);
            return parents.get(groupPosition).getChildren().get(childPosition);
        }

        //Call when child row clicked
        @Override
        public long getChildId(int groupPosition, int childPosition) {
            /****** When Child row clicked then this function call *******/

            //Log.i("Noise", "parent == "+groupPosition+"=  child : =="+childPosition);
            if (ChildClickStatus != childPosition) {
                ChildClickStatus = childPosition;
            }

            return childPosition;
        }

        @Override
        public int getChildrenCount(int groupPosition) {
            int size = 0;
            if (parents.get(groupPosition).getChildren() != null)
                size = parents.get(groupPosition).getChildren().size();
            return size;
        }


        @Override
        public Object getGroup(int groupPosition) {
            return parents.get(groupPosition);
        }

        @Override
        public int getGroupCount() {
            return parents.size();
        }

        //Call when parent row clicked
        @Override
        public long getGroupId(int groupPosition) {
            ParentClickStatus = groupPosition;
            if (ParentClickStatus == 0)
                ParentClickStatus = -1;

            return groupPosition;
        }

        @Override
        public void notifyDataSetChanged() {
            // Refresh List rows
            super.notifyDataSetChanged();
        }

        @Override
        public boolean isEmpty() {
            return ((parents == null) || parents.isEmpty());
        }

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return true;
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }

        @Override
        public boolean areAllItemsEnabled() {
            return true;
        }

        /*******************
         * Checkbox Checked Change Listener
         ********************/

        private final class CheckUpdateListener implements CompoundButton.OnCheckedChangeListener {
            private final Parent parent;

            private CheckUpdateListener(Parent parent) {
                this.parent = parent;
            }

            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                parent.setChecked(isChecked);
                notifyDataSetChanged();
            }
        }
        /***********************************************************************/

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_meal_calculator, menu);
        return super.onCreateOptionsMenu(menu);
    }

    /**
     * On selecting action bar icons
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent data = new Intent(Intent.ACTION_VIEW);
        // Take appropriate action for each action item click
        switch (item.getItemId()) {
            case R.id.action_save:
                data.putExtra("Calori", textBoxCalori.getText());
                data.putExtra("Protein", textBoxProtein.getText());
                data.putExtra("Fat", textBoxFat.getText());
                //data.putExtra("Carbonhydrade", textBoxCarbonhydrade.getText());
                setResult(0, data);

                if (type != 0) {
                    for (int i = 0; i < list.size(); i++) {
                        Parent parent = list.get(i);
                        ArrayList<Child> children = parent.getChildren();
                        for (int j = 0; j < children.size(); j++) {
                            Child child = children.get(j);
                            if (child.isChecked()) {
                                DataTransferObjects.UserMeal userMeal = new DataTransferObjects.UserMeal(userValue, type, child.getId());
                                boolean result = DatabaseQuery.Insert("USERMEAL", userMeal.getUserMealObject());
                                if (result)
                                    Toast.makeText(this, "Başarılı", Toast.LENGTH_LONG).show();
                                else
                                    Toast.makeText(this, "Başarısız", Toast.LENGTH_LONG).show();
                            }
                        }
                    }
                }

                finish();
                return true;

            case R.id.action_back:
                data.putExtra("Calori", textBoxCalori.getText());
                data.putExtra("Protein", textBoxProtein.getText());
                data.putExtra("Fat", textBoxFat.getText());
                //data.putExtra("Carbonhydrade", textBoxCarbonhydrade.getText());
                setResult(0, data);

                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}