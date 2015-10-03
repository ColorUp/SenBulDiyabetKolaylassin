package com.mycodeyourproject.senbuldiyabetkolaylassin;

import android.os.AsyncTask;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.concurrent.ExecutionException;

/**
 * Created by Burak on 26.08.2015.
 */
public class AsyncPhpConnection
{
    private String textResult;

    public String StartAsyncTask(String phpUrl)
    {
        MyTask task=new MyTask();

        task.SetTextUrl(phpUrl);

        try
        {
            task.execute().get();
            return textResult;
        }
        catch (ExecutionException ex)
        {
            return ex.toString();
        }
        catch (InterruptedException ex)
        {
            return ex.toString();
        }
    }

    private class MyTask extends AsyncTask<Void, Void, Void>
    {
        private String textUrl;

        public void SetTextUrl(String url){ textUrl=url; }

        @Override
        protected Void doInBackground(Void... params)
        {
            try
            {
                URL url = new URL(textUrl);
                HttpClient client = new DefaultHttpClient();
                HttpGet request = new HttpGet();
                request.setURI(new URI(textUrl));

                HttpResponse response = client.execute(request);
                BufferedReader in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

                StringBuffer sb = new StringBuffer("");
                String line = "";

                while ((line = in.readLine()) != null) {
                    sb.append(line);
                    break;
                }

                in.close();
                textResult = sb.toString();
            }
            catch (MalformedURLException e)
            {
                textResult = e.toString();
            }
            catch (IOException e)
            {
                textResult = e.toString();
            }
            catch (URISyntaxException ex)
            {
                textResult = ex.toString();
            }
            catch (Exception ex)
            {
                textResult = ex.toString();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result)
        {
            super.onPostExecute(result);
        }
    }
}