package com.androidplayground.rogue.helper;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.androidplayground.rogue.swamphacksandroid.MainActivity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivityHelper {

    public static String fileName = "contactsList";
    public static void writeNumberToStorage(String number, Context context)
    {
        try {
            File fileToWrite = new File(context.getFilesDir(), fileName);
            if (!fileToWrite.exists()) {
                Log.e("MainActivity", "File does not exist, creating one");
                fileToWrite.createNewFile();
            }

            number = number+"\n";
            FileOutputStream fos = new FileOutputStream(fileToWrite, true);
            Log.e("MainActivity", "Writing to the file:" + fileToWrite.getName());
            //mContcts.setText("Adding the text");
            fos.write(number.getBytes());
            fos.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }


    public static List<String> readContactsList(Context context) {
        StringBuilder text = new StringBuilder();

        Log.e("MainActivity","Reading the contacts list");
        File file = new File(context.getFilesDir(), fileName);
        if(!file.exists()) {
            Log.e("MainActivity","File does not exist");
            return null;
        }

        List<String> list = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            while ((line = br.readLine()) != null) {
                text.append(line);
                text.append('\n');
                list.add(line);
                Log.e("MainActivity","Reading the LIst");
            }
            br.close();
        }
        catch (IOException e) {
            //You'll need to add proper error handling here
            e.printStackTrace();
        }

        return list;
    }

    public static void updateListView(Context context, ListView listView) {
        List<String> contactsList = MainActivityHelper.readContactsList(context);
        if(contactsList!=null && contactsList.size() > 0) {
            ArrayAdapter adapter = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, contactsList);
            listView.setAdapter(adapter);
        }
        else
        {
            Log.e("MainActivity","The contacts list is NULL");
        }
    }


}