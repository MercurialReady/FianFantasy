package com.example.mercu.finalfantasy.utils.io;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.URL;
import java.net.URLConnection;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

import okhttp3.ResponseBody;

/**
 * Created by qicheng on 2018/9/27.
 */

public class IOUtil
{
    public static void writeCache(ResponseBody responseBody, String url) throws IOException
    {
        URL conn = new URL(url);
        URLConnection urlConnection = conn.openConnection();
        int totalLength = urlConnection.getContentLength();

        //File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath(), "QQ.apk");
        //File dir = new File(Environment.getExternalStorageDirectory() + "");
        File file = new File("/storage/sdcard0","12345.apk");
        if(!file.exists())
        {
            file.createNewFile();
        }
        //Log.d("Mercurial","contentLength is" + responseBody.contentLength());
        //Log.d("Mercurial","totalLength is" + totalLength);
        RandomAccessFile randomAccessFile = new RandomAccessFile(file,"rwd");
        FileChannel channel = randomAccessFile.getChannel();
        MappedByteBuffer mappedByteBuffer = channel.map(FileChannel.MapMode.READ_WRITE,0,totalLength);

        int len;
        byte[] buffer = new byte[100*1024*1024];
        while ((len = responseBody.byteStream().read(buffer)) != -1)
        {
            mappedByteBuffer.put(buffer,0,len);
            //Log.d("Mercurial","len is" + len);
        }
        channel.close();
        responseBody.byteStream().close();
        randomAccessFile.close();
    }
}
