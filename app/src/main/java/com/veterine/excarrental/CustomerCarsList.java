package com.veterine.excarrental;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class CustomerCarsList extends AppCompatActivity {

    int[] image = {R.drawable.dublinhonda, R.drawable.markx, R.drawable.toyota_land_cruiser_prado,
            R.drawable.bmwx5, R.drawable.bmwx4, R.drawable.x_trail, R.drawable.canterdyna, R.drawable.cruiser, R.drawable.fuel_coaster, R.drawable.ranger,
            R.drawable.toyota_coaster, R.drawable.a4};

    String[] BRAND = {"Honda", "Toyota", "Toyota", "BMW", "BMW", "NISSAN", "Toyota", "Toyota", "Toyota", "Ford", "Toyota", "Audi"};
    String[] NAMES = {"Dumblin Honda", "Mark X", "Landcruiser Prado", "BMW X5", "BMW X4", "X-Trail", "Canter Dyana", "LandCruiser", "Fuel Coaster", "Ford Ranger",
            "Coaster", "a4"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_cars_list);

        ListView listView = (ListView)findViewById(R.id.customer_listView);
        CustomAdapter customAdapter = new CustomAdapter();
        listView.setAdapter(customAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                int templistView = image[position];

                Intent intent = new Intent(CustomerCarsList.this, MakeReservation.class);
                intent.putExtra("ListViewClickValue", templistView);
                startActivity(intent);
            }
        });



    }

    class CustomAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return image.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            convertView = getLayoutInflater().inflate(R.layout.customercarslistrow,null);

            ImageView image_id = convertView.findViewById(R.id.customer_image);
            TextView brand = convertView.findViewById(R.id.customer_brand);
            TextView name = convertView.findViewById(R.id.customer_name);

            image_id.setImageResource(image[position]);
            brand.setText(BRAND[position]);
            name.setText(NAMES[position]);

            return convertView;
        }
    }
}



