package fr.ensicaen.dp.tp2;

import android.util.Log;
import java.io.BufferedReader; import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader; import java.net.HttpURLConnection; import java.net.URL;
public class HttpUtils {
    /**
     * Found on http://developer.android.com/training/basics/networkops/ connecting.html
     * @param myurl http web url
     * @return web page content
     * @throws IOException
     */
    public static String downloadUrl(String myurl) throws IOException { InputStream is = null;
        try {
// http://172.28.106.1:8080/fr.ensicaen.si.jersey/si/clients
            URL url = new URL(myurl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection(); conn.setReadTimeout(10000); // milliseconds conn.setConnectTimeout(15000); // milliseconds conn.setRequestProperty("Content-Type", "application/json"); conn.setRequestProperty("Accept", "application/json"); conn.setRequestMethod("GET");
            conn.setDoInput(true);
// Starts the query
            conn.connect();
            int response = conn.getResponseCode(); Log.i(HttpUtils.class.getSimpleName(), "The response is: " + response); is = conn.getInputStream();
// Convert the InputStream into a string
//String contentAsString = readIt(is, len);
            String contentAsString = readIt(is);
            return contentAsString;
// Makes sure that the InputStream is closed after the app is
// finished using it.
        } finally {
            if (is != null) {
                is.close();
            }
        } }
    /**
     * Download a text file line by line until end of file. * @return a string with the whole text file.
     */
    public static String readIt(InputStream stream) throws IOException { StringBuilder stringBuilder = new StringBuilder();
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream)); String line;
        while ((line = reader.readLine()) != null) { stringBuilder.append(line + '\n');
        }
        return stringBuilder.toString(); }
}
