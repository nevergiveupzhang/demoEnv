package com.example.minio;

import io.minio.MinioClient;
import io.minio.Result;
import io.minio.errors.*;
import io.minio.messages.Item;
import io.minio.policy.PolicyType;
import org.apache.log4j.Logger;
import org.xmlpull.v1.XmlPullParserException;

import java.io.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

public class FileUploader {
    private static final Logger log = Logger.getLogger(FileUploader.class);

    private static MinioClient minioClient;
//    private final static String url = "http://192.168.106.62:9000/";
    private final static String url = "http://192.168.50.22:9000/";
    static {
        // 使用MinIO服务的URL，端口，Access key和Secret key创建一个MinioClient对象
        try {
            minioClient = new MinioClient(url, "minioadmin", "minioadmin");
//            minioClient = new MinioClient(url);
        } catch (InvalidEndpointException e) {
            e.printStackTrace();
        } catch (InvalidPortException e) {
            e.printStackTrace();
        }

    }
    public static void main(String[] args) throws NoSuchAlgorithmException, IOException, InvalidKeyException, XmlPullParserException, InsufficientDataException, InternalException, NoResponseException, RegionConflictException, ErrorResponseException, InvalidArgumentException, InvalidBucketNameException, InvalidObjectPrefixException {
//        putObject();
//        putObject("test");
//        putObject("test2","test1.png");
//        buckssetPolicy();
//        getObject();
//        getObject("test3","test1/test1.pdf");
//        listObjects();
//        removeBucket();
//        forceRemoveBucket("aaa");
//        createBucket();
//        forceCreateBucket("aaa");

//        upload("D:\\programs\\mysql-5.7.23-winx64\\data");

        upload();

//        removeObject();

//        download();
    }

    private static void download() throws IOException, InvalidKeyException, NoSuchAlgorithmException, InsufficientDataException, InternalException, NoResponseException, InvalidBucketNameException, XmlPullParserException, ErrorResponseException {
        String bucketName = "test";
        if(!minioClient.bucketExists(bucketName)){
            return;
        }
        long start = System.currentTimeMillis();
        for(int i=1;i<=100000;i++){
            if(i%10000 == 1){
                System.out.println("第"+(i/10000 + 1)+"个10000开始时间："+new Date());
            }
            try {
                InputStream is = minioClient.getObject(bucketName,bucketName+i+".png");
                writeToLocal(is,"E:\\Tmp\\download\\"+bucketName+i+".png");
            } catch(MinioException e) {
                System.out.println("Error occurred: " + e);
            }
        }
        System.out.println("结束时间："+new Date());
        System.out.println(System.currentTimeMillis() - start);
    }

    private static void removeObject() throws IOException, InvalidKeyException, NoSuchAlgorithmException, InsufficientDataException, InternalException, NoResponseException, InvalidBucketNameException, XmlPullParserException, ErrorResponseException {
        String bucketName = "test";
        if(!minioClient.bucketExists(bucketName)){
            return;
        }
        long start = System.currentTimeMillis();
        for(int i=1;i<=5000;i++){
            minioClient.removeObject(bucketName,bucketName+i+"-binlog");
        }
        System.out.println(System.currentTimeMillis() - start);
    }

    private static void upload() throws IOException, InvalidKeyException, NoSuchAlgorithmException, InsufficientDataException, InternalException, NoResponseException, InvalidBucketNameException, XmlPullParserException, ErrorResponseException, RegionConflictException, InvalidArgumentException {
        String bucketNamePrefix = "test";
        String bucketName = "test";
        if(!minioClient.bucketExists(bucketName)){
            minioClient.makeBucket(bucketName);
        }

        long start = System.currentTimeMillis();
        for(int i=1;i<=100000;i++){
            if(i%5000 == 1){
                log.info("第"+(i/5000 + 1)+"个5000开始时间："+new Date());
            }
            minioClient.putObject(bucketName,bucketNamePrefix+i+".png","E:\\Tmp\\test.png");
        }
        log.info("结束时间："+new Date());
        log.info(System.currentTimeMillis() - start);
    }

    private static void upload(String rootPath) {
        File root = new File(rootPath);
        if(!root.exists() || !root.isDirectory()){
            return;
        }
        long start = System.currentTimeMillis();
        String bucketName = rootPath.substring(rootPath.lastIndexOf(File.separator)+1).replace("@","-");
        File []childs =root.listFiles();
        if(childs.length == 0){
            return;
        }
        for(File child : childs){
            doUpload(bucketName,child);
        }
        System.out.println(System.currentTimeMillis()- start);
    }

    private static void doUpload(String bucketName,File currentFile){
        bucketName = bucketName.replace("@","-");
        String currentFilePath = currentFile.getPath();
        String currentFileName = currentFilePath.substring(currentFilePath.lastIndexOf(File.separator)+1).replace("@","-");
        if(currentFile.isFile()){
            try {
                if(!minioClient.bucketExists(bucketName)){
                    minioClient.makeBucket(bucketName);
                }
                minioClient.putObject(bucketName,currentFileName,currentFilePath);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else if(currentFile.isDirectory()){
            File []childs =currentFile.listFiles();
            if(childs.length == 0){
                return;
            }
            bucketName = bucketName + "-" +currentFileName;
            for(File child : childs){
                doUpload(bucketName,child);
            }
        }
    }

    private static void bucketPolicy() throws IOException, InvalidKeyException, NoSuchAlgorithmException, InsufficientDataException, ErrorResponseException, NoResponseException, InvalidBucketNameException, XmlPullParserException, InternalException, RegionConflictException, InvalidArgumentException, InvalidObjectPrefixException {
        minioClient.makeBucket("test");
        minioClient.putObject("test","test.png","E:\\Tmp\\test.png");
        minioClient.setBucketPolicy("test","test", PolicyType.READ_ONLY);
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
            long start = System.currentTimeMillis();
            minioClient.getObject(bucketName,objectName);
            System.out.println(System.currentTimeMillis() - start);
        } catch(MinioException e) {
            System.out.println("Error occurred: " + e);
        }
    }

    private static void getObject() throws XmlPullParserException, NoSuchAlgorithmException, InvalidKeyException, IOException {
        try {
            InputStream is = minioClient.getObject("test","test.png");
            writeToLocal(is,"E:\\Tmp\\copy.jpg");
        } catch(MinioException e) {
            System.out.println("Error occurred: " + e);
        }
    }

    private static void writeToLocal(InputStream is,String tmpPath) throws IOException {
        File target = new File(tmpPath);
        target.createNewFile();
        OutputStream os = new FileOutputStream(target);
        byte []bytes = new byte[1024];
        int bytesRead = 0;
        while((bytesRead = is.read(bytes)) > 0){
            os.write(bytes,0,bytesRead);
        }

        is.close();
        os.flush();
        os.close();
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
            for(char start = 'a';start<='z';start++){
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
            long start =System.currentTimeMillis();
            minioClient.putObject(bucketName,objectName, "E:\\Tmp\\2020102113352173404.pdf");
            System.out.println(System.currentTimeMillis() - start);
        } catch(MinioException e) {
            System.out.println("Error occurred: " + e);
        }
    }
}
