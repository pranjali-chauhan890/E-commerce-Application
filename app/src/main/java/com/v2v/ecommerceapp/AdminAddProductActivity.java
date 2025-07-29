package com.v2v.ecommerceapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AdminAddProductActivity extends AppCompatActivity {

    EditText nameInput, priceInput, descInput;
    Button addButton;
    DatabaseReference productRef;
    ImageView productImageView;
    Button selectImageBtn;
    Uri selectedImageUri;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add_product);

        nameInput = findViewById(R.id.productName);
        priceInput = findViewById(R.id.productPrice);
        descInput = findViewById(R.id.productDesc);
        addButton = findViewById(R.id.addProductButton);

        productRef = FirebaseDatabase.getInstance().getReference("products");
        Button viewProductsButton = findViewById(R.id.viewProductsButton);
        productImageView = findViewById(R.id.productImageView);
        selectImageBtn = findViewById(R.id.selectImageBtn);

        selectImageBtn.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setType("image/*");
            startActivityForResult(intent, 100);
        });

        viewProductsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminAddProductActivity.this, AdminProductListActivity.class);
                startActivity(intent);
            }
        });

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = productRef.push().getKey();
                String name = nameInput.getText().toString();
                String price = priceInput.getText().toString();
                String desc = descInput.getText().toString();
                String imageUri = selectedImageUri != null ? selectedImageUri.toString() : "";

                Product product = new Product(id, name, price, desc, imageUri.toString());
                productRef.child(id).setValue(product);

                Toast.makeText(AdminAddProductActivity.this, "Product Added", Toast.LENGTH_SHORT).show();
                nameInput.setText("");
                priceInput.setText("");
                descInput.setText("");
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 100 && resultCode == RESULT_OK && data != null) {
            selectedImageUri = data.getData();
            productImageView.setImageURI(selectedImageUri);
        }
    }
}
