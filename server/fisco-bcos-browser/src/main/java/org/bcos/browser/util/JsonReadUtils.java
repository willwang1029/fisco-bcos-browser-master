package org.bcos.browser.util;

import org.json.JSONObject;

import java.io.*;

public class JsonReadUtils {

    public static JSONObject readJson(String fileName) {
        try {
            BufferedReader br = new BufferedReader(new FileReader("./data/" + fileName));
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

    public static void saveJson(String fileName, JSONObject json) {
        BufferedWriter writer = null;
        File file = new File("./data/" + fileName);
        try {
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file,false), "UTF-8"));
            writer.write(format(json.toString()));
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String format(String jsonStr){
        try {
            ByteArrayInputStream in = new ByteArrayInputStream(jsonStr.getBytes());
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            char ch;
            int read;
            int space=0;
            while((read = in.read()) > 0){
                ch = (char)read;
                switch (ch){
                    case '{': {
                        space = outputAndRightMove(space, ch, out);
                        break;
                    }
                    case '[': {
                        out.write(ch);
                        space += 2;
                        break;
                    }
                    case '}': {
                        space = outputAndLeftMove(space, ch, out);
                        break;
                    }
                    case ']': {
                        space = outputAndLeftMove(space, ch, out);
                        break;
                    }
                    case ',': {
                        out.write(ch);
                        outputNewline(out);
                        out.write(getBlankingStringBytes(space));
                        break;
                    }
                    default: {
                        out.write(ch);
                        break;
                    }
                }
            }
            return out.toString();
        } catch (IOException e){
            e.printStackTrace();
        }

        return null;
    }

    public static int outputAndRightMove(int space, char ch, ByteArrayOutputStream out) throws IOException {
        //换行
        outputNewline(out);
        //向右缩进
        out.write(getBlankingStringBytes(space));
        out.write(ch);
        outputNewline(out);
        space += 2;
        //再向右缩进多两个字符
        out.write(getBlankingStringBytes(space));
        return space;
    }
    public static int outputAndLeftMove(int space, char ch, ByteArrayOutputStream out) throws IOException{
        outputNewline(out);
        space -= 2;
        out.write(getBlankingStringBytes(space));
        out.write(ch);
        return space;
    }
    public static byte[] getBlankingStringBytes(int space){
        StringBuilder sb = new StringBuilder("");
        for (int i = 0; i < space; i++) {
            sb.append(" ");
        }
        return sb.toString().getBytes();
    }

    public static void outputNewline(ByteArrayOutputStream out){
        out.write('\n');
    }
}
