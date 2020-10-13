package com.example.minio;

import io.minio.MinioClient;
import io.minio.errors.MinioException;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class FileUploader {
    public static void main(String[] args) throws NoSuchAlgorithmException, IOException, InvalidKeyException, XmlPullParserException {
        try {
            // 使用MinIO服务的URL，端口，Access key和Secret key创建一个MinioClient对象
            MinioClient minioClient = new MinioClient("http://127.0.0.1:9000/", "minioadmin", "minioadmin");

            // 检查存储桶是否已经存在
            boolean isExist = minioClient.bucketExists("test");
            if(isExist) {
                System.out.println("Bucket already exists.");
            } else {
                // 创建一个名为asiatrip的存储桶，用于存储照片的zip文件。
                minioClient.makeBucket("test");
            }

            // 使用putObject上传一个文件到存储桶中。
            minioClient.putObject("test","test.jpg", "E:\\Tmp\\功能模块.jpg");
            System.out.println("E:\\Tmp\\功能模块.jpg is successfully uploaded as test.jpg to `test` bucket.");
        } catch(MinioException e) {
            System.out.println("Error occurred: " + e);
        }
    }
}
