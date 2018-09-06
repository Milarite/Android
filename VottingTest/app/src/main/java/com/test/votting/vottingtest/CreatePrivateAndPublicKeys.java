package com.test.votting.vottingtest;

import android.app.Activity;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;
import org.web3j.crypto.CipherException;
import org.web3j.crypto.ECKeyPair;
import org.web3j.crypto.Keys;
import org.web3j.crypto.Wallet;
import org.web3j.crypto.WalletFile;

import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

public class CreatePrivateAndPublicKeys {


    Activity activity;
   public CreatePrivateAndPublicKeys(Activity act)
    {
        activity=act;

    }
    public JSONObject process(String seed){

        JSONObject processJson = new JSONObject();

        try {
            ECKeyPair ecKeyPair = Keys.createEcKeyPair();
            BigInteger privateKeyInDec = ecKeyPair.getPrivateKey();

            String sPrivatekeyInHex = privateKeyInDec.toString(16);

            WalletFile aWallet = Wallet.createLight(seed, ecKeyPair);
            String sAddress = aWallet.getAddress();


            try {
                processJson.put("address", "0x" + sAddress);
                processJson.put("privatekey", sPrivatekeyInHex);

            } catch (JSONException e) {
                e.printStackTrace();
            }


        } catch (CipherException e) {
            Toast.makeText(activity, e.getMessage(), Toast.LENGTH_SHORT).show();
            //
        } catch (NoSuchAlgorithmException e) {
            Toast.makeText(activity, e.getMessage(), Toast.LENGTH_SHORT).show();

            e.printStackTrace();
        } catch (NoSuchProviderException e) {
            Toast.makeText(activity, e.getMessage(), Toast.LENGTH_SHORT).show();

            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException e) {
            Toast.makeText(activity, e.getMessage(), Toast.LENGTH_SHORT).show();

            e.printStackTrace();
        }

        return processJson;
    }

}
