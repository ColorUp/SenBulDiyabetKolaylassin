package com.mycodeyourproject.senbuldiyabetkolaylassin;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class MealCalculator extends BaseViaDiabetActivity {
    ArrayList<Parent> list;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String[]>> listDataChild;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_calculator);
        expListView = (ExpandableListView) findViewById(R.id.listView);

        Resources res = this.getResources();
        Drawable devider = res.getDrawable(R.drawable.decorativeline);

        // Set ExpandableListView values
        expListView.setGroupIndicator(null);
        expListView.setDivider(devider);
        expListView.setChildDivider(devider);
        expListView.setDividerHeight(1);
        registerForContextMenu(expListView);

        buildDummyData();

        // Adding ArrayList data to ExpandableListView values
        loadHosts(list);
    }

    //Initialize variables
    private int ParentClickStatus = -1;
    private int ChildClickStatus = -1;
    private ArrayList<Parent> parents;


    private void buildDummyData() {
        // Creating ArrayList of type parent class to store parent class objects
        list = new ArrayList<Parent>();
        for (int i = 1; i < 4; i++) {
            //Create parent class object
            final Parent parent = new Parent();

            // Set values in parent class object
            if (i == 1) {
                parent.setId(i);
                parent.setName("Hamur İşleri");
                parent.setTotalCal(0);
                parent.setTotalCarbonhydrade(0);
                parent.setTotalFat(0);
                parent.setTotalProtein(0);
                parent.setChildren(new ArrayList<Child>());

                // Create Child class object
                final Child child = new Child();
                child.setId(i);
                child.setName("Ekmek");
                child.setCal(33);
                child.setFat(5);
                child.setProtein(55);

                //Add Child class object to parent class object
                parent.getChildren().add(child);

                final Child child1 = new Child();
                child1.setId(i);
                child1.setName("Poğaça");
                child1.setCal(33);
                child1.setFat(65);
                child1.setProtein(15);
                parent.getChildren().add(child1);

                final Child child2 = new Child();
                child2.setId(i);
                child2.setName("Simit");
                child2.setCal(133);
                child2.setFat(15);
                child2.setProtein(5);
                parent.getChildren().add(child2);
            } else if (i == 2) {
                parent.setId(i);
                parent.setName("Meyveler");
                parent.setTotalCal(0);
                parent.setTotalCarbonhydrade(0);
                parent.setTotalFat(0);
                parent.setTotalProtein(0);
                parent.setChildren(new ArrayList<Child>());

                // Create Child class object
                final Child child = new Child();
                child.setId(i);
                child.setName("Elma");
                child.setCal(55);
                child.setFat(12);
                child.setProtein(52);

                //Add Child class object to parent class object
                parent.getChildren().add(child);

                final Child child1 = new Child();
                child1.setId(i);
                child1.setName("Muz");
                child1.setCal(23);
                child1.setFat(7);
                child1.setProtein(18);
                parent.getChildren().add(child1);

                final Child child2 = new Child();
                child2.setId(i);
                child2.setName("Çilek");
                child2.setCal(19);
                child2.setFat(45);
                child2.setProtein(13);
                parent.getChildren().add(child2);
            } else if (i == 3) {
                parent.setId(i);
                parent.setName("Salatalar");
                parent.setTotalCal(0);
                parent.setTotalCarbonhydrade(0);
                parent.setTotalFat(0);
                parent.setTotalProtein(0);
                parent.setChildren(new ArrayList<Child>());

                // Create Child class object
                final Child child = new Child();
                child.setId(i);
                child.setName("Sezar Salata");
                child.setCal(19);
                child.setFat(45);
                child.setProtein(13);

                //Add Child class object to parent class object
                parent.getChildren().add(child);

                final Child child1 = new Child();
                child1.setId(i);
                child1.setName("Mevsim Salata");
                child1.setCal(19);
                child1.setFat(45);
                child1.setProtein(13);
                parent.getChildren().add(child1);

                final Child child2 = new Child();
                child2.setId(i);
                child2.setName("Çoban Salata");
                child2.setCal(19);
                child2.setFat(45);
                child2.setProtein(13);
                parent.getChildren().add(child2);
            }

            //Adding Parent class object to ArrayList
            list.add(parent);
        }
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

            parent.setSummary("K.Hidrat: " + parent.getTotalCarbonhydrade() +
                    " gr, Yağ: " + parent.getTotalFat() + " kJ, Protein: " + parent.getTotalProtein() +
                    " kJ, Kalori: " + parent.getTotalCal() + " cal");

            ((TextView) convertView.findViewById(R.id.text_summary_group)).setText(parent.getSummary());
            ImageView image = (ImageView) convertView.findViewById(R.id.image);

            image.setImageResource(
                    getResources().getIdentifier(
                            "com.mycodeyourproject.senbuldiyabetkolaylassin:drawable/meal" + parent.getId(), null, null));

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
            ImageView image = (ImageView) convertView.findViewById(R.id.image);
            image.setImageResource(
                    getResources().getIdentifier(
                            "com.mycodeyourproject.senbuldiyabetkolaylassin:drawable/meal" + parent.getId(), null, null));

            // Get grouprow.xml file checkbox elements
            TableLayout tableLayout = (TableLayout) convertView.findViewById(R.id.row);

            // Set CheckUpdateListener for CheckBox (see below CheckUpdateListener class)
            tableLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    View groupRow = LayoutInflater.from(getApplication()).inflate(R.layout.group_row, null);
                    TextView parentSummary = (TextView) groupRow.findViewById(R.id.text_summary_group);
                    if (child.isChecked()) {
                        child.setChecked(false);
                        parent.setTotalCal(parent.getTotalCal() - child.getCal());
                        parent.setTotalProtein(parent.getTotalProtein() - child.getProtein());
                        parent.setTotalFat(parent.getTotalFat() - child.getFat());
                        parent.setSummary("K.Hidrat: " + parent.getTotalCarbonhydrade() +
                                " gr, Yağ: " + parent.getTotalFat() + " kJ, Protein: " + parent.getTotalProtein() +
                                " kJ, Kalori: " + parent.getTotalCal() + " cal");
                        parentSummary.setText(parent.getSummary());
                    } else {
                        child.setChecked(true);
                        parent.setTotalCal(parent.getTotalCal() + child.getCal());
                        parent.setTotalProtein(parent.getTotalProtein() + child.getProtein());
                        parent.setTotalFat(parent.getTotalFat() + child.getFat());
                        parent.setSummary("K.Hidrat: " + parent.getTotalCarbonhydrade() +
                                " gr, Yağ: " + parent.getTotalFat() + " kJ, Protein: " + parent.getTotalProtein() +
                                " kJ, Kalori: " + parent.getTotalCal() + " cal");
                        parentSummary.setText(parent.getSummary());
                    }

                    View mealCalculator = LayoutInflater.from(getApplication()).inflate(R.layout.activity_meal_calculator, null);
                    EditText textBoxCarbonhydrade = (EditText) mealCalculator.findViewById(R.id.textbox_carbonhydrade).findViewById(R.id.textbox_editText);
                    EditText textBoxProtein = (EditText) mealCalculator.findViewById(R.id.textbox_protein).findViewById(R.id.textbox_editText);
                    EditText textBoxCalori = (EditText) mealCalculator.findViewById(R.id.textbox_calori).findViewById(R.id.textbox_editText);
                    EditText textBoxFat = (EditText) mealCalculator.findViewById(R.id.textbox_calori).findViewById(R.id.textbox_editText);

                    float calori=0;
                    float protein=0;
                    float fat=0;
                    for (int i = 0; i < list.size(); i++) {
                        Parent parent = list.get(i);
                        calori=calori+parent.getTotalCal();
                        protein=protein+parent.getTotalProtein();
                        fat=fat+parent.getTotalFat();
                    }

                    textBoxCalori.setText(String.valueOf(calori));
                    textBoxProtein.setText(String.valueOf(protein));
                    textBoxFat.setText(String.valueOf(fat));
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
}

