package com.v2v.ecommerceapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.*;

import java.util.ArrayList;
import java.util.List;

public class CustomerViewActivity extends AppCompatActivity {

    RecyclerView productRecycler;
    ProductAdapter adapter;
    List<Product> productList;
    DatabaseReference productRef;
    Button showCartBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_view); // your layout file with button + recyclerview

        productRecycler = findViewById(R.id.productRecycler);
        showCartBtn = findViewById(R.id.showCartBtn);

        productRecycler.setLayoutManager(new LinearLayoutManager(this));
        productList = new ArrayList<>();
        adapter = new ProductAdapter(CustomerViewActivity.this, productList);
        productRecycler.setAdapter(adapter);

        // Firebase reference
        productRef = FirebaseDatabase.getInstance().getReference("products");

        productRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                productList.clear();
                for (DataSnapshot snap : snapshot.getChildren()) {
                    Product product = snap.getValue(Product.class);
                    productList.add(product);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle error
            }
        });

        // Show Cart Button logic
        showCartBtn.setOnClickListener(v -> {
            Intent intent = new Intent(CustomerViewActivity.this, CartActivity.class);
            startActivity(intent);
        });
    }
}
