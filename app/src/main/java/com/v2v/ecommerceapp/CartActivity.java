package com.v2v.ecommerceapp;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Map;

public class CartActivity extends AppCompatActivity {

    LinearLayout cartLayout;
    TextView totalAmount;
    Button placeOrderBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        cartLayout = findViewById(R.id.cartItemsLayout);
        totalAmount = findViewById(R.id.totalAmount);
        placeOrderBtn = findViewById(R.id.placeOrderBtn);

        loadCartItems();

        placeOrderBtn.setOnClickListener(v -> {
            Toast.makeText(this, "Order Placed Successfully!", Toast.LENGTH_LONG).show();
            SharedPreferences.Editor editor = getSharedPreferences("cart", MODE_PRIVATE).edit();
            editor.clear();
            editor.apply();
            cartLayout.removeAllViews();
            totalAmount.setText("₹0");
        });
    }

    private void loadCartItems() {
        SharedPreferences prefs = getSharedPreferences("cart", MODE_PRIVATE);
        Map<String, ?> allItems = prefs.getAll();
        int total = 0;

        cartLayout.removeAllViews(); // clear previous

        for (Map.Entry<String, ?> entry : allItems.entrySet()) {
            String name = entry.getKey();
            int price = Integer.parseInt(entry.getValue().toString());

            View itemView = getLayoutInflater().inflate(R.layout.cart_item, cartLayout, false);

            TextView itemName = itemView.findViewById(R.id.itemName);
            TextView itemPrice = itemView.findViewById(R.id.itemPrice);
            ImageButton deleteBtn = itemView.findViewById(R.id.deleteBtn);

            itemName.setText(name);
            itemPrice.setText("₹" + price);

            // Delete logic
            deleteBtn.setOnClickListener(v -> {
                SharedPreferences.Editor editor = prefs.edit();
                editor.remove(name).apply();
                loadCartItems(); // refresh list
                Toast.makeText(this, "item deleted sucessfully", Toast.LENGTH_SHORT).show();
            });

            cartLayout.addView(itemView);
            total += price;
        }

        totalAmount.setText("₹" + total);
    }
}
