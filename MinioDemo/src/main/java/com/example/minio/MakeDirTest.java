package com.example.minio;

import java.io.File;

public class MakeDirTest {
    public static void main(String[] args) {
        StringBuilder cmd61 = new StringBuilder();
        cmd61.append("minio server ");
        String dir = "";
        for(int i=1;i<=4;i++){
            cmd61.append("http://192.168.106.61:9000/F:/qhdx_minio_data/data"+i+" ");
            cmd61.append("http://192.168.106.62:9000/F:/qhdx_minio_data/data"+i+" ");
            dir = "E:\\Data\\qhdx_minio_data\\data"+i;
            File file = new File(dir);

            if(file.exists()){
                if(file.isDirectory()){
                    deleteDir(file);
                }else{
                    file.delete();
                }
            }
            file.mkdir();
        }

        System.out.println(cmd61.toString());
    }

    private static boolean deleteDir(File dir) {
        if (dir.isDirectory()) {
            String[] children = dir.list();
            for (int i=0; i<children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }
        // 目录此时为空，可以删除
        return dir.delete();
    }
}
