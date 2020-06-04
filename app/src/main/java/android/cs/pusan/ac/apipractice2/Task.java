package android.cs.pusan.ac.apipractice2;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Task extends AsyncTask<String, Void, String> {

    String clientKey = "#########################";
    ;
    private String str, receiveMsg;
    private final String ID = "########";

    @Override
    protected String doInBackground(String... params) {
        URL url = null;
        try {
            url = new URL("http://openapi.airkorea.or.kr/openapi/services/rest/ArpltnInforInqireSvc" +
                    "/getMsrstnAcctoRltmMesureDnsty?stationName=%ED%95%99%EC%9E%A5%EB%8F%99&dataTerm=month&pageNo=1&numOfRows=10" +
                    "&ServiceKey=W%2FIZgDCgC%2FSr9cWTAbtUkoV3A%2Bv1Xg2430j86PDLrqR4if7HEIYcQVGix0dJmMaSGTeuqUxNCeZntb%2Bmeq%2FnMw%3D%3D&ver=1.3");

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
            conn.setRequestProperty("x-waple-authorization", clientKey);

            if (conn.getResponseCode() == conn.HTTP_OK) {
                InputStreamReader tmp = new InputStreamReader(conn.getInputStream(), "UTF-8");
                BufferedReader reader = new BufferedReader(tmp);
                StringBuffer buffer = new StringBuffer();
                while ((str = reader.readLine()) != null) {
                    buffer.append(str);
                }
                receiveMsg = buffer.toString();
                Log.i("receiveMsg : ", receiveMsg);

                reader.close();
            } else {
                Log.i("통신 결과", conn.getResponseCode() + "에러");
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return receiveMsg;
    }
}
