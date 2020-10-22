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
    private static MinioClient minioClient;
//    private final static String url = "http://192.168.106.61:9000/";
    private final static String url = "http://127.0.0.1:9000/";
    static {
        // 使用MinIO服务的URL，端口，Access key和Secret key创建一个MinioClient对象
        try {
            minioClient = new MinioClient(url, "minioadmin", "minioadmin");
        } catch (InvalidEndpointException e) {
            e.printStackTrace();
        } catch (InvalidPortException e) {
            e.printStackTrace();
        }

    }
    public static void main(String[] args) throws NoSuchAlgorithmException, IOException, InvalidKeyException, XmlPullParserException, InsufficientDataException, InternalException, NoResponseException, RegionConflictException, ErrorResponseException, InvalidArgumentException, InvalidBucketNameException {
//        putObject();
        putObject("test");
//        putObject("ccc","ccc2.png");
//        getObject();
//        getObject("aaa","aaa.png");
//        listObjects();
//        removeBucket();
//        forceRemoveBucket("aaa");
//        createBucket();
//        forceCreateBucket("aaa");
    }

    private static void forceCreateBucket(String bucketName) throws IOException, InvalidKeyException, NoSuchAlgorithmException, InsufficientDataException, InternalException, NoResponseException, InvalidBucketNameException, XmlPullParserException, ErrorResponseException, RegionConflictException {
        minioClient.makeBucket(bucketName);
        System.out.println(bucketName + " Bucket created.");
    }

    private static void createBucket(String bucketName) throws IOException, InvalidKeyException, NoSuchAlgorithmException, InsufficientDataException, InternalException, NoResponseException, InvalidBucketNameException, XmlPullParserException, ErrorResponseException, RegionConflictException {
        boolean isExist = minioClient.bucketExists(bucketName);
        if(!isExist) {
            minioClient.makeBucket(bucketName);
            System.out.println(bucketName + " Bucket created.");
        }
    }

    private static void createBucket() throws IOException, InvalidKeyException, NoSuchAlgorithmException, InsufficientDataException, InternalException, NoResponseException, InvalidBucketNameException, XmlPullParserException, ErrorResponseException, RegionConflictException {
        for(char start = 'a';start<='z';start++){
            String bucketName = ""+start+start+start;
            boolean isExist = minioClient.bucketExists(bucketName);
            if(!isExist) {
                minioClient.makeBucket(bucketName);
                System.out.println(bucketName + " Bucket created.");
            }
        }
    }

    private static void removeBucket() throws IOException, InvalidKeyException, NoSuchAlgorithmException, InsufficientDataException, InternalException, NoResponseException, InvalidBucketNameException, XmlPullParserException, ErrorResponseException, RegionConflictException, InvalidArgumentException {
        // 使用putObject上传一个文件到存储桶中。
        for(char start = 'a';start<='z';start++){
            String bucketName = ""+start+start+start;
            removeBucket(bucketName);
        }
    }

    private static void forceRemoveBucket(String bucketName) throws IOException, InvalidKeyException, NoSuchAlgorithmException, InsufficientDataException, InternalException, NoResponseException, InvalidBucketNameException, XmlPullParserException, ErrorResponseException, RegionConflictException, InvalidArgumentException {
        minioClient.removeBucket(bucketName);
        System.out.println("Bucket removed.");
    }

    private static void removeBucket(String bucketName) throws IOException, InvalidKeyException, NoSuchAlgorithmException, InsufficientDataException, InternalException, NoResponseException, InvalidBucketNameException, XmlPullParserException, ErrorResponseException, RegionConflictException, InvalidArgumentException {
        boolean isExist = minioClient.bucketExists(bucketName);
        if(isExist) {
            Iterable<Result<Item>> myObjects = minioClient.listObjects(bucketName);
            for (Result<Item> result : myObjects) {
                Item item = result.get();
                minioClient.removeObject(bucketName,item.objectName());
            }
            minioClient.removeBucket(bucketName);
            System.out.println("Bucket removed.");
        }
    }

    private static void getObject(String bucketName,String objectName) throws XmlPullParserException, NoSuchAlgorithmException, InvalidKeyException, IOException {
        try {
            minioClient.getObject(bucketName,objectName);
        } catch(MinioException e) {
            System.out.println("Error occurred: " + e);
        }
    }

    private static void getObject() throws XmlPullParserException, NoSuchAlgorithmException, InvalidKeyException, IOException {
        try {
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

    private static void putObject(String bucketName) throws XmlPullParserException, NoSuchAlgorithmException, InvalidKeyException, IOException {
        try {
            for(char start = 'a';start<='c';start++){
                String objectName = ""+start+start+start+".png";
                boolean isExist = minioClient.bucketExists(bucketName);
                if(isExist) {
                    System.out.println("Bucket already exists.");
                } else {
                    // 创建一个名为asiatrip的存储桶，用于存储照片的zip文件。
                    minioClient.makeBucket(bucketName);
                }
                minioClient.putObject(bucketName,objectName, "E:\\Tmp\\test.png");
            }
            System.out.println("E:\\Tmp\\test.jpg is successfully uploaded as test.jpg to `test` bucket.");
        } catch(MinioException e) {
            System.out.println("Error occurred: " + e);
        }
    }

    private static void putObject() throws XmlPullParserException, NoSuchAlgorithmException, InvalidKeyException, IOException {
        try {
            for(char start = 'a';start<='c';start++){
                String bucketName = ""+start+start+start;
                boolean isExist = minioClient.bucketExists(bucketName);
                if(isExist) {
                    System.out.println("Bucket already exists.");
                } else {
                    // 创建一个名为asiatrip的存储桶，用于存储照片的zip文件。
                    minioClient.makeBucket(bucketName);
                }
                minioClient.putObject(bucketName,bucketName+".png", "E:\\Tmp\\test.png");
            }
            System.out.println("E:\\Tmp\\test.jpg is successfully uploaded as test.jpg to `test` bucket.");
        } catch(MinioException e) {
            System.out.println("Error occurred: " + e);
        }
    }

    private static void putObject(String bucketName,String objectName) throws XmlPullParserException, NoSuchAlgorithmException, InvalidKeyException, IOException {
        try {
            boolean isExist = minioClient.bucketExists(bucketName);
            if(isExist) {
                System.out.println("Bucket already exists.");
            } else {
                // 创建一个名为asiatrip的存储桶，用于存储照片的zip文件。
                minioClient.makeBucket(bucketName);
            }
            minioClient.putObject(bucketName,objectName, "E:\\Tmp\\test.png");
            System.out.println("E:\\Tmp\\test.jpg is successfully uploaded as test.jpg to `test` bucket.");
        } catch(MinioException e) {
            System.out.println("Error occurred: " + e);
        }
    }
}
