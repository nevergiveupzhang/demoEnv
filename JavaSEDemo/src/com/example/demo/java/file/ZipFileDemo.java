package com.example.demo.java.file;

import java.io.*;
import java.nio.charset.Charset;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

public class ZipFileDemo {
    public static void main(String[] args) throws IOException {
        String path = "E:\\Tmp\\testUpload\\item.zip";
        InputStream in = new BufferedInputStream(new FileInputStream(path));
        ZipInputStream zin = new ZipInputStream(in,Charset.forName("gbk"));
        ZipEntry ze;
        while((ze = zin.getNextEntry()) != null){
            if(!ze.isDirectory()){
                System.out.println(ze.getName());
            }
        }
        zin.closeEntry();
    }
}
