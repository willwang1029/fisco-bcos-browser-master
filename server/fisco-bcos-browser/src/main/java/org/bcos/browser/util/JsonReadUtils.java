package org.bcos.browser.util;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileReader;

public class JsonReadUtils {

    public static JSONObject readJson(String fileNmae) {
        try {
            BufferedReader br = new BufferedReader(new FileReader("./data/" + fileNmae));
            StringBuilder sb = new StringBuilder();
            String s = null;
            while ((s = br.readLine()) != null) { sb.append(s); }
            JSONObject data = new JSONObject(sb.toString());
            return data;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
