package com.dc.sharesdemo;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class SharesDemoApplicationTests {

    // Endpoint以杭州为例，其它Region请按实际情况填写。
    String endpoint = "oss-cn-hongkong.aliyuncs.com";
    // 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建RAM账号。
    String accessKeyId = "LTAI47T0AW2UZd0F";
    String accessKeySecret = "3vFcPoSndiZuzrRGqrr9TeUNr6YCR1";

    String bucketName = "test-img-d";

    /**
     * 创建存储空间
     */
    @Test
    public void createBucket() {
        // 创建OSSClient实例。
        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);

        CreateBucketRequest createBucketRequest = new CreateBucketRequest(bucketName);
        // 设置存储空间的权限为公共读，默认是私有。
        createBucketRequest.setCannedACL(CannedAccessControlList.PublicRead);
        // 设置存储空间的存储类型为低频访问类型，默认是标准类型。
        //createBucketRequest.setStorageClass(StorageClass.IA);
        ossClient.createBucket(createBucketRequest);

        // 关闭OSSClient。
        ossClient.shutdown();

    }

    /**
     * 列举存储空间
     *  - sw-t-img2019-03-23
     *  - sw-tt-img
     *  - sw-tt-img2019-03-23
     *  - test-img-d
     */
    @Test
    public void listBuckets(){
        // 创建OSSClient实例。
        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);

        // 列举存储空间。
        List<Bucket> buckets = ossClient.listBuckets();
        for (Bucket bucket : buckets) {
            System.out.println(" - " + bucket.getName());
        }

    // 关闭OSSClient。
        ossClient.shutdown();
    }

    /**
     * 获取存储空间的地域
     */
    @Test
    public void getBucketLocation(){
        // 创建OSSClient实例。
        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);

        String location = ossClient.getBucketLocation("test-img-d");
        System.out.println(location);

        // 关闭OSSClient。
        ossClient.shutdown();
    }

    /**
     * 删除存储空间
     */
    @Test
    public void deleteBucket(){
        // 创建OSSClient实例。
        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);

        // 删除存储空间。
        ossClient.deleteBucket("sw-tt-img2019-03-23");

        // 关闭OSSClient。
        ossClient.shutdown();
    }

    /**
     * 分页列举所有文件
     * 1553320862029
     * 	211efd1ba5f7c1a3f1fe4b1d23e091d7
     */
    @Test
    public void listObjects(){
        // 创建OSSClient实例。
        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);

        final int maxKeys = 200;
        String nextMarker = null;
        ObjectListing objectListing;

        do {
            objectListing = ossClient.listObjects(new ListObjectsRequest(bucketName).withMarker(nextMarker).withMaxKeys(maxKeys));

            List<OSSObjectSummary> sums = objectListing.getObjectSummaries();
            for (OSSObjectSummary s : sums) {
                System.out.println("\t" + s.getKey());
            }

            nextMarker = objectListing.getNextMarker();

        } while (objectListing.isTruncated());

        // 关闭OSSClient。
        ossClient.shutdown();
    }

    /**
     * 删除文件
     */
    @Test
    public void deleteObjects(){
        // 创建OSSClient实例。
        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);

        // 删除文件。
        List<String> keys = new ArrayList<String>();
        keys.add("1553320862029");
        keys.add("211efd1ba5f7c1a3f1fe4b1d23e091d7");

        DeleteObjectsResult deleteObjectsResult = ossClient.deleteObjects(new DeleteObjectsRequest("sw-tt-img2019-03-23").withKeys(keys));
        List<String> deletedObjects = deleteObjectsResult.getDeletedObjects();

        for (String deletedObject:
        deletedObjects) {
            System.out.println(deletedObject);
        }

        // 关闭OSSClient。
        ossClient.shutdown();
    }

    /**
     * 上传图片
     * https://test-img-d.oss-cn-hongkong.aliyuncs.com/123.jpg
     * 用url访问图片的格式为
     * "https://"+bucketName+endpoint+"/"+文件名
     */
    @Test
    public void uploadImg(){
        // 创建OSSClient实例。
        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);

        // 上传文件流。
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream("D:\\USER\\Desktop\\iphoneX.jpg");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ossClient.putObject(bucketName, "123.jpg", inputStream);

        // 关闭OSSClient。
        ossClient.shutdown();
    }
}
