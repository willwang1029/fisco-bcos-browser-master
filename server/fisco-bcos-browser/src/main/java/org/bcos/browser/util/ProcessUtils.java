package org.bcos.browser.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ProcessUtils {

    public static String start(String path) {
        try {
            Process ps = Runtime.getRuntime().exec("/root/fisco/nodes/127.0.0.1/" + path);
            ps.waitFor();

            BufferedReader br = new BufferedReader(new InputStreamReader(ps.getInputStream()));
            StringBuffer sb = new StringBuffer();
            String line;
            while ((line = br.readLine()) != null) { sb.append(line).append("\n"); }
            return sb.toString();
        }
        catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static void test() {
        try {
            Process ps = Runtime.getRuntime().exec("cd /root/fisco/caliper &" +
                    " node benchmark/fisco-bcos/v2.0/hellowprld/main.js");
            ps.waitFor();
            System.out.println("Test finish!");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
