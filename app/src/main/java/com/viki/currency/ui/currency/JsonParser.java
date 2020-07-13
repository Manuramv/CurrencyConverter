package com.viki.currency.ui.currency;

import android.util.Log;

import org.json.JSONObject;

import java.util.Iterator;

public class JsonParser {
    void mdJsonParser(String s){
        // searchResult refers to the current element in the array "search_result"
        try {
            //String response = "{\"rates\":{\"CAD\":1.5336,\"HKD\":8.7396,\"ISK\":159.0,\"PHP\":55.794,\"DKK\":7.4483,\"HUF\":353.7,\"CZK\":26.691,\"AUD\":1.6247,\"RON\":4.8428,\"SEK\":10.398,\"IDR\":16276.91,\"INR\":84.841,\"BRL\":6.0691,\"RUB\":80.2104,\"HRK\":7.5345,\"JPY\":120.48,\"THB\":35.316,\"CHF\":1.0625,\"SGD\":1.5703,\"PLN\":4.4743,\"BGN\":1.9558,\"TRY\":7.7417,\"CNY\":7.8952,\"NOK\":10.7163,\"NZD\":1.7189,\"ZAR\":19.0889,\"USD\":1.1276,\"MXN\":25.6953,\"ILS\":3.9006,\"GBP\":0.8957,\"KRW\":1354.7,\"MYR\":4.8109},\"base\":\"EUR\",\"date\":\"2020-07-10\"}";

            JSONObject questionMark = new JSONObject(s);
            Iterator keys = questionMark.keys();

            while (keys.hasNext()) {
                // loop to get the dynamic key
                String currentDynamicKey = (String) keys.next();

                // get the value of the dynamic key
                JSONObject currentDynamicValue = questionMark.getJSONObject(currentDynamicKey);

                // do something here with the value...
            }
        } catch (Exception e){
            Log.d("TAG","Exception in JsonParser==" + e);
        }
    }
}
