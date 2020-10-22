package com.example.minio;

import java.io.File;

public class MakeDirTest {
    public static void main(String[] args) {
//        createServerCmd();
        createLocalCmd();
    }

    private static void createLocalCmd() {
        StringBuilder local = new StringBuilder();
        local.append("minio server ");
        String dir = "";
        for(int i=1;i<=8;i++){
            local.append("E:\\Data\\qhdx_minio_data\\data"+i+" ");
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

        System.out.println(local.toString());
    }

    private static void createServerCmd() {
        StringBuilder server = new StringBuilder();
        server.append("minio server ");
        String dir = "";
        for(int i=1;i<=4;i++){
            server.append("http://192.168.106.61:9000/F:/qhdx_minio_data/data"+i+" ");
            server.append("http://192.168.106.62:9000/G:/qhdx_minio_data/data"+i+" ");
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

        System.out.println(server.toString());
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
