package com.mycodeyourproject.senbuldiyabetkolaylassin;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Burak on 13.08.2015.
 */
public class DatabaseQuery
{
    private static String dbUrl = "http://www.viadiabet.mycodeyourproject.com/index.php?";
    private static String url;
    private static AsyncPhpConnection phpData = new AsyncPhpConnection();

    private static boolean checkResult(String result)
    {
        if (result.contains("True"))
            return true;
        else
            return false;
    }

    private static String parametersToString(Map<String,String> parameters, String parameterFormat)
    {
        Iterator<Map.Entry<String, String>> itCondition = parameters.entrySet().iterator();
        String str = "";

        while (itCondition.hasNext())
        {
            try {
                Map.Entry<String, String> entry = itCondition.next();
                str = str + Extensions.Format(parameterFormat, entry.getKey(), URLEncoder.encode(entry.getValue(), "UTF-8"));
            }
            catch (UnsupportedEncodingException ex)
            {
                return str;
            }
        }

        str = str.substring(0, str.length() - 1);

        return str;
    }

    public static Map<String, String> GetParameterList(String table)
    {
        String urlTableColumns = Extensions.Format("{0}sql={1}&table={2}", dbUrl, Enums.PhpSqlOperation.TABLE.getStatusCode(), table);
        String columnStr = phpData.StartAsyncTask(urlTableColumns);
        String[] columns = columnStr.split(",");

        Map<String, String> result = new LinkedHashMap<>();

        for (int j = 0; j < columns.length; j++)
        {
            result.put(columns[j], "");
        }

        return result;
    }

    public static String GetTableColumns(String table)
    {
        url = Extensions.Format("{0}sql={1}&table={2}", dbUrl, Enums.PhpSqlOperation.TABLE.getStatusCode(), table);
        String dbResult = phpData.StartAsyncTask(url);
        return dbResult;
    }

    public static List<Map<Object,Object>> Select(String table, Map<String,String> condition)
    {
        url = Extensions.Format("{0}sql={1}&table={2}", dbUrl, Enums.PhpSqlOperation.SELECT.getStatusCode(), table);
        String urlTableColumns = Extensions.Format("{0}sql={1}&table={2}", dbUrl, Enums.PhpSqlOperation.TABLE.getStatusCode(), table);
        String conditions = "";
        String dbResult="";

        String columnStr = phpData.StartAsyncTask(urlTableColumns);
        String[] columns = columnStr.split(",");

        if(condition!=null)
        {
            conditions = parametersToString(condition, "{0}:{1},");
            url = Extensions.Format("{0}&conditions={1}", url, conditions);
            String dataList = phpData.StartAsyncTask(url);

            if(dataList.contentEquals(""))
                return null;

            String[] rows=dataList.split("\n");

            List<Map<Object,Object>> result = new ArrayList<Map<Object, Object>>();
            Map<Object,Object> row=new LinkedHashMap<>();

            for (int i=0; i<rows.length;i++)
            {
                String[] rowValues=rows[i].split(",");
                for (int j=0; j<columns.length; j++)
                {
                    row.put(columns[j],rowValues[j]);
                }

                result.add(row);
            }

            return result;
        }
        return null;
    }

    public static boolean Insert(String table, Map<String,String> columnValue)
    {
        url = Extensions.Format("{0}sql={1}&table={2}", dbUrl, Enums.PhpSqlOperation.INSERT.getStatusCode(), table);
        String param = "";
        String dbResult="";

        if(columnValue!=null)
        {
            param = parametersToString(columnValue, "{1},");

            url = Extensions.Format("{0}&values={1}", url, param);
            dbResult = phpData.StartAsyncTask(url);
        }

        return checkResult(dbResult);
    }

    public static boolean UpdateOrDelete(Enums.PhpSqlOperation operation, String table, Map<String,String> columnValue, Map<String,String> condition)
    {
        url = Extensions.Format("{0}sql={1}&table={2}", dbUrl, operation.getStatusCode(), table);
        String values = "";
        String conditions = "";
        String dbResult="";

        if(columnValue!=null)
        {
            values = parametersToString(columnValue, "{0}:{1},");
        }

        if(condition!=null)
        {
            conditions = parametersToString(condition, "{0}:{1},");
        }

        switch (operation)
        {
            case DELETE:
                url = Extensions.Format("{0}&conditions={1}", url, conditions);
                break;
            case UPDATE:
                url = Extensions.Format("{0}&column-value={1}&conditions={2}", url, values, conditions);
                break;
        }

        dbResult = phpData.StartAsyncTask(url);
        return checkResult(dbResult);
    }
}