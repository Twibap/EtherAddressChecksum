package io.mystudy.tnn.myhashstudyapplication;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.web3j.crypto.Hash;
import org.web3j.utils.Numeric;

import java.nio.charset.StandardCharsets;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView viewAddress;
    TextView viewHash;
    TextView viewChecksum;

    EditText inputAddress;
    Button btChecksum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewAddress = findViewById(R.id.textViewInput);
        viewHash = findViewById(R.id.textViewHash);
        viewChecksum = findViewById(R.id.textViewChecksum);

        inputAddress = findViewById(R.id.editAddress);
        btChecksum = findViewById(R.id.buttonChecksum);
        btChecksum.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        String address = inputAddress.getText().toString();

        TextInputLayout textInputLayout = findViewById(R.id.editAddressLayout);
        if ( AddressUtils.isValidChecksumAddress(address) ){
            textInputLayout.setErrorEnabled(false);
        } else {
            textInputLayout.setError(getString(R.string.address_invalid));
            textInputLayout.setErrorEnabled(true);
        }

        address = address.toLowerCase();
        byte[] hash = Hash.sha3(address.replace("0x", "").getBytes(StandardCharsets.UTF_8));
        String checksumAddress = AddressUtils.toChecksumAddress(address);

        viewAddress.setText(address);
        viewHash.setText( Numeric.toHexString(hash).substring(0, 42) );
        viewChecksum.setText(checksumAddress);
    }
}
