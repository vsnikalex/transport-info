package com.tsystems.transportinfo.service;

import org.json.JSONObject;
import org.junit.Test;

import javax.transaction.Transactional;
import java.util.Iterator;

public class DeliveryServiceImplTest {

    @Test
    @Transactional
    public void routeConversionIsValid() {
        String route = "{\"0\":\"48.752525,18.145055\",\"1\":\"46.094432,11.115757\",\"2\":\"48.752525,18.145055\"}";

        JSONObject jsonObject = new JSONObject(route);

        Iterator<String> iterator = jsonObject.keys();

        while (iterator.hasNext()) {
            System.out.println(jsonObject.get(iterator.next()));
        }
    }

}
