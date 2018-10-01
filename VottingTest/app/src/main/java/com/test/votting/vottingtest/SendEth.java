package com.test.votting.vottingtest;

import android.app.Activity;

import org.web3j.crypto.RawTransaction;
import org.web3j.crypto.TransactionEncoder;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.EthSendTransaction;
import org.web3j.utils.Numeric;

import java.io.IOException;
import java.math.BigInteger;
import java.util.concurrent.ExecutionException;

import static android.provider.Telephony.Carriers.PASSWORD;

public class SendEth {
    EthSendTransaction ethSendTransaction;
    BigInteger nonce;
   public SendEth(String voterAddress)
    {
        {
            try {
                nonce = HelperCLass.web3.ethGetTransactionCount("0xb248d34f2431824afe5147170fb98a7aa0f7499d",
                        DefaultBlockParameterName.LATEST).send().getTransactionCount();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //Constructing transaction
        RawTransaction rawTransaction  = RawTransaction.createEtherTransaction(
                new BigInteger(String.valueOf(nonce.intValue()+1)),
                new BigInteger("1000000000"),
                new BigInteger("210000"),
                voterAddress,
                new BigInteger("1000000000")
        );

        //Signing transaction
        byte[] signedMessage = TransactionEncoder.signMessage(rawTransaction,HelperCLass.credentials);
        String hexValue = Numeric.toHexString(signedMessage);




        {
            try {
                ethSendTransaction = HelperCLass.web3.ethSendRawTransaction(hexValue).send();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }




    public String getTxHash()
    {
        return   ethSendTransaction.getTransactionHash();
    }
}
