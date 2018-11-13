package io.mystudy.tnn.myhashstudyapplication;

import android.util.Log;

import org.web3j.crypto.Hash;
import org.web3j.utils.Numeric;

import java.nio.charset.StandardCharsets;

class AddressUtils {
    private static final String TAG = AddressUtils.class.getSimpleName();

    // Account 주소 정규표현식 확인
    static boolean isValidAddress(String address){
        return !address.isEmpty() && address.matches("^0x[a-fA-F0-9]{40}$");
    }

    // Account Checksum 검사
    static boolean isValidChecksumAddress(String address){
        return isValidAddress(address) && (toChecksumAddress(address).equals(address) );
    }

    // Account Checksum 변환
    static String toChecksumAddress(String address){
        address = address.toLowerCase();
        Log.d(TAG, "toChecksumAddress: source   -> "+address);
        byte[] byteHash = Hash.sha3(address.replace("0x", "").getBytes(StandardCharsets.UTF_8));
        String hash = Numeric.toHexString(byteHash);
        Log.d(TAG, "toChecksumAddress: Hash     -> "+hash);

        String result = "0x";
        for(int i = 2 ; i < address.length() ; i++ ){   // i = 2; Prefix 다음부터

            // hash의 각 자릿수가 16진수 8보다 크면 주소값의 같은 자리수 대문자 변환
            if( Integer.parseInt( Character.toString(hash.charAt(i)),  16 ) >= 8){
                result += Character.toString(address.charAt(i)).toUpperCase() ;
            } else {
                result += Character.toString(address.charAt(i));    // 소문자 그대로
            }
        }

        Log.d(TAG, "toChecksumAddress: checksum -> "+ result);
        return result;
    }

}
