package com.mycodeyourproject.senbuldiyabetkolaylassin;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Enumeration;
import java.util.Hashtable;

/**
 * Created by Burak on 13.08.2015.
 */
public class DatabaseQuery
{
    private static String dbUrl = "http://www.viadiabet.mycodeyourproject.com";
    private static String dbUrlDeneme = "http://www.viadiabet.mycodeyourproject.com/index.php?sql=TABLE&table=USER";
    private static URL url;
    private static HttpResponse response;

    public static URL GetUrl() {
        return url;
    }

    public static void SetUrl(String link)
    {
        try
        {
            url = new URL(link);
        }
        catch (MalformedURLException ex)
        {
            ex.toString();
        }
    }

    public static boolean PostRequest(Hashtable<Object, Object> params)
    {
        HttpClient client = new DefaultHttpClient();

        HttpGet request = new HttpGet();
        try
        {
            request.setURI(new URI(dbUrl));
            response = client.execute(request);
            return true;
        }
        catch (URISyntaxException ex)
        {
            return false;
        }
        catch (IOException ex)
        {
            return false;
        }
    }

    private String setParams(String link, Hashtable<Object, Object> params)
    {
        link = link + "?";
        Enumeration keys = params.keys();
        while (keys.hasMoreElements())
        {
            Object key = keys.nextElement();
            link = link + key.toString() + "=" + params.get(key) + "&";
        }

        link = link.substring(0, link.length() - 1);
        return link;
    }

    public static StringBuffer GetRequest()
    {
        StringBuffer result = null;

        try
        {
            BufferedReader in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

            result = new StringBuffer("");
            String line = "";

            while ((line = in.readLine()) != null)
            {
                result.append(line);
                break;
            }
            in.close();

            return result;
        }
        catch (IOException ex)
        {
            return null;
        }
    }

    public static String getTables() throws MalformedURLException, URISyntaxException, IOException
    {
        URL url = new URL(dbUrlDeneme);
        HttpClient client = new DefaultHttpClient();
        HttpGet request = new HttpGet();
        request.setURI(new URI(dbUrlDeneme));

        HttpResponse response = client.execute(request);
        BufferedReader in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

        StringBuffer sb = new StringBuffer("");
        String line="";

        while ((line = in.readLine()) != null)
        {
            sb.append(line);
            break;
        }

        in.close();
        return sb.toString();
    }
}