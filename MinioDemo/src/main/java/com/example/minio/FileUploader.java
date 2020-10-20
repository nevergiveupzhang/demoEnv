package com.example.minio;

import io.minio.MinioClient;
import io.minio.Result;
import io.minio.errors.*;
import io.minio.messages.Item;
import org.xmlpull.v1.XmlPullParserException;

import java.io.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class FileUploader {
    public static void main(String[] args) throws NoSuchAlgorithmException, IOException, InvalidKeyException, XmlPullParserException {
//        putObject();
        getObject();
//        listObjects();
    }

    private static void getObject() throws XmlPullParserException, NoSuchAlgorithmException, InvalidKeyException, IOException {
        try {
            // 使用MinIO服务的URL，端口，Access key和Secret key创建一个MinioClient对象
            MinioClient minioClient = new MinioClient("http://192.168.106.61:9000/", "minioadmin", "minioadmin");

            InputStream is = minioClient.getObject("test","test.png");
            OutputStream os = new FileOutputStream(new File("E:\\Tmp\\copy.jpg"));
            byte []bytes = new byte[1024];
            int bytesRead = 0;
            while((bytesRead = is.read(bytes)) > 0){
                os.write(bytes,0,bytesRead);
            }

            is.close();
            os.flush();
            os.close();
        } catch(MinioException e) {
            System.out.println("Error occurred: " + e);
        }
    }

    private static void listObjects() throws XmlPullParserException, NoSuchAlgorithmException, InvalidKeyException, IOException {
        try {
            // 使用MinIO服务的URL，端口，Access key和Secret key创建一个MinioClient对象
            MinioClient minioClient = new MinioClient("http://192.168.106.61:9000/", "minioadmin", "minioadmin");

            // 检查存储桶是否已经存在
            boolean isExist = minioClient.bucketExists("test");
            if(isExist) {
                // 列出'my-bucketname'里的对象
                Iterable<Result<Item>> myObjects = minioClient.listObjects("test","test");
                for (Result<Item> result : myObjects) {
                    Item item = result.get();
                    System.out.println(item.lastModified() + ", " + item.size() + ", " + item.objectName());
                }
            }
        } catch(MinioException e) {
            System.out.println("Error occurred: " + e);
        }

    }

    private static void putObject() throws XmlPullParserException, NoSuchAlgorithmException, InvalidKeyException, IOException {
        try {
            // 使用MinIO服务的URL，端口，Access key和Secret key创建一个MinioClient对象
            MinioClient minioClient = new MinioClient("http://192.168.106.61:9000/", "minioadmin", "minioadmin");

            // 检查存储桶是否已经存在
            boolean isExist = minioClient.bucketExists("test");
            if(isExist) {
                System.out.println("Bucket already exists.");
            } else {
                // 创建一个名为asiatrip的存储桶，用于存储照片的zip文件。
                minioClient.makeBucket("test");
            }

            // 使用putObject上传一个文件到存储桶中。
            minioClient.putObject("test","test.png", "E:\\Tmp\\test.png");
            System.out.println("E:\\Tmp\\test.jpg is successfully uploaded as test.jpg to `test` bucket.");
        } catch(MinioException e) {
            System.out.println("Error occurred: " + e);
        }
    }
}
