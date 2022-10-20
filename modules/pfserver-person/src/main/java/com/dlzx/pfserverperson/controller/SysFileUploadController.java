package com.dlzx.pfserverperson.controller;

import com.dlzx.common.core.utils.file.FileUtils;
import com.dlzx.common.core.web.domain.AjaxResult;
import com.dlzx.common.minio.service.MinioService;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * 文件控制接口
 *
 * @author dlzx
 */
@RestController
@RequestMapping("/personFile")
public class SysFileUploadController {
    @Autowired
    private MinioService minioService;

    /**
     * 桶名
     */
    @Value("${minio.bucketNameImage}")
    private String bucketNameImage;

    /**
     * 图片url前缀
     */
    @Value("${minio.prefix}")
    private String prefix;


    /**
     * minio创建文件桶
     *
     */
    @PostMapping("/makeBucket")
    @ApiOperation(value="mini创建桶")
    public AjaxResult minioUpload(String bucketName){
        try{
            boolean flag = minioService.bucketExists(bucketName);
            if(!flag){
                return AjaxResult.error("bucket已存在");
            }
            return AjaxResult.success( minioService.makeBucket(bucketName));
        }catch (Exception e){
            return AjaxResult.error(e.getMessage());
        }
    }
    /**
     * minio上传文件
     *
     */
    @PostMapping("/minioUpload")
    @ApiOperation(value="minio上传文件")
    public AjaxResult minioUpload(MultipartFile file){
        try{
            minioService.putObject(bucketNameImage,file,file.getOriginalFilename());
            return AjaxResult.success();
        }catch (Exception e){
            System.out.println(e);
        }
        return AjaxResult.error();
    }
    /**
     * minio上传文件
     *
     */
    @PostMapping("/minioChooseBucketUpload")
    @ApiOperation(value="minio选择桶上传文件")
    public AjaxResult minioChooseBucketUpload(MultipartFile file){
        String bucket = "pfserverperson";
        try{
            boolean flag = minioService.bucketExists(bucket);
            if(!flag) {
                return AjaxResult.error("该文件桶不存在，请先创建bucket");
            }
            String randomFileName = this.getRandomFileName();
//            minioService.putObject(bucket,file,randomFileName+"-"+file.getOriginalFilename());
            minioService.putObject(bucket,file,file.getOriginalFilename());
            //拼接url
//            String url = prefix + "/" + bucket + "/" + randomFileName +"-"+ file.getOriginalFilename();
            String url = prefix + "/" + bucket + "/" + file.getOriginalFilename();
            return AjaxResult.success(url);
        }catch (Exception e){
            System.out.println(e);
        }
        return AjaxResult.error();
    }

    @PostMapping("/minioChooseBucketUploadList")
    @ApiOperation(value="minio选择桶上传文件")
    public AjaxResult minioChooseBucketUploadList(MultipartFile[] file){
        String bucket = "pfserverperson";
        try{
            boolean flag = minioService.bucketExists(bucket);
            if(!flag) {
                return AjaxResult.error("该文件桶不存在，请先创建bucket");
            }
            List<String> urlList = new ArrayList<>();
            for (MultipartFile multipartFile : file) {
                String randomFileName = this.getRandomFileName();
//            minioService.putObject(bucket,file,randomFileName+"-"+file.getOriginalFilename());
                minioService.putObject(bucket,multipartFile,multipartFile.getOriginalFilename());
                //拼接url
//            String url = prefix + "/" + bucket + "/" + randomFileName +"-"+ file.getOriginalFilename();
                String url = prefix + "/" + bucket + "/" + multipartFile.getOriginalFilename();
                urlList.add(url);
            }
            return AjaxResult.success(urlList);
        }catch (Exception e){
            System.out.println(e);
        }
        return AjaxResult.error();
    }



    /**
     * minio下载文件
     *
     */
    @GetMapping("/minioDownload")
    @ApiOperation(value="minio下载文件")
    public void minioDownload(String filename,String originalName,HttpServletResponse response){
        minioService.downloadFile(bucketNameImage,filename,originalName,response);
    }

    /**
     * minio下载文件
     *
     */
    @GetMapping("/minioChooseBucketDownload")
    @ApiOperation(value="minio选择桶下载文件")
    public void minioChooseBucketDownload(String filename,String originalName,HttpServletResponse response){
        String bucket = "pfserverperson";
        minioService.downloadFile(bucket,filename,originalName,response);
    }

    @GetMapping(value = "minio/view/**")
    public void minioView(HttpServletRequest request, HttpServletResponse response) {

// ISO-8859-1 ==> UTF-8 进行编码转换
        String imgPath = extractPathFromPattern(request);
        // 其余处理略
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            String bucketName = "";
            String fileName = "";
            response.setContentType("image/jpeg;charset=utf-8");
            if (StringUtils.isNotEmpty(imgPath)){
                String[] split = imgPath.split("/");
                bucketName = split[0];
                fileName = split[1];
            }
            inputStream =minioService.getObject(bucketName,fileName);
            outputStream = response.getOutputStream();
            byte[] buf = new byte[1024];
            int len;
            while ((len = inputStream.read(buf)) > 0) {
                outputStream.write(buf, 0, len);
            }
            response.flushBuffer();
        } catch (IOException e) {
            AjaxResult.error("预览图片失败" + e.getMessage());
            // e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    AjaxResult.error(e.getMessage(), e);
                }
            }
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    AjaxResult.error(e.getMessage(), e);
                }
            }
        }


//        // ISO-8859-1 ==> UTF-8 进行编码转换
//        String imgPath = extractPathFromPattern(request);
//        // 其余处理略
//        InputStream inputStream = null;
//        OutputStream outputStream = null;
//        try {
//            String bucketName = "";
//            String fileName = "";
//            response.setContentType("image/jpeg;charset=utf-8");
//            if (StringUtils.isNotEmpty(imgPath)){
//                String[] split = imgPath.split("/");
//                bucketName = split[0];
//                fileName = split[1];
//            }
//
//
//            String range = request.getHeader("Range");
//            int start = 0, end = 0;
//            if(range != null && range.startsWith("bytes=")){
//                String[] values = range.split("=")[1].split("-");
//                start = Integer.parseInt(values[0]);
//                if(values.length > 1){
//                    end = Integer.parseInt(values[1]);
//                }
//            }
//            int requestSize = 0;
//            if(end != 0 && end > start){
//                requestSize = end - start + 1;
//            } else {
//                requestSize = Integer.MAX_VALUE;
//            }
//
//            response.setContentType("video/mp4");
//            response.setHeader("Accept-Ranges", "bytes");
//            response.setHeader("Last-Modified", new Date().toString());
//            //第一次请求只返回content length来让客户端请求多次实际数据
//            Long contentLength = Long.valueOf(minioService.getObject(bucketName,fileName).available());
//            if(range == null){
//                response.setHeader("Content-length", contentLength + "");
//                inputStream =minioService.getObject(bucketName,fileName);
//            }else{
//                //以后的多次以断点续传的方式来返回视频数据
//                response.setStatus(HttpServletResponse.SC_PARTIAL_CONTENT);//206
//                long requestStart = 0, requestEnd = 0;
//                String[] ranges = range.split("=");
//                if(ranges.length > 1){
//                    String[] rangeDatas = ranges[1].split("-");
//                    requestStart = Integer.parseInt(rangeDatas[0]);
//                    if(rangeDatas.length > 1){
//                        requestEnd = Integer.parseInt(rangeDatas[1]);
//                    }
//                }
//                long length = 0;
//                if(requestEnd > 0){
//                    length = requestEnd - requestStart + 1;
//                    inputStream =minioService.getObject(bucketName,fileName,requestStart,length);
//                    response.setHeader("Content-length", "" + length);
//                    response.setHeader("Content-Range", "bytes " + requestStart + "-" + requestEnd + "/" + contentLength);
//                }else{
//                    length = contentLength - requestStart;
//                    inputStream =minioService.getObject(bucketName,fileName,requestStart,length);
//                    response.setHeader("Content-length", "" + length);
//                    response.setHeader("Content-Range", "bytes "+ requestStart + "-" + (contentLength - 1) + "/" + contentLength);
//                }
//            }
//
//            outputStream = response.getOutputStream();
//            byte[] buf = new byte[1024];
//            int len;
//            while ((len = inputStream.read(buf)) > 0) {
//                outputStream.write(buf, 0, len);
//            }
//            response.flushBuffer();
//        } catch (IOException e) {
//            AjaxResult.error("预览图片失败" + e.getMessage());
//            // e.printStackTrace();
//        } finally {
//            if (inputStream != null) {
//                try {
//                    inputStream.close();
//                } catch (IOException e) {
//                    AjaxResult.error(e.getMessage(), e);
//                }
//            }
//            if (outputStream != null) {
//                try {
//                    outputStream.close();
//                } catch (IOException e) {
//                    AjaxResult.error(e.getMessage(), e);
//                }
//            }
//        }

    }

    /**
     *  把指定URL后的字符串全部截断当成参数
     *  这么做是为了防止URL中包含中文或者特殊字符（/等）时，匹配不了的问题
     * @param request
     * @return
     */
    private static String extractPathFromPattern(final HttpServletRequest request) {
        String path = (String) request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
        String bestMatchPattern = (String) request.getAttribute(HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE);
        return new AntPathMatcher().extractPathWithinPattern(bestMatchPattern, path);
    }


    /**
     * 生成随机文件名：当前年月日时分秒+五位随机数
     *
     * @return
     */
    private String getRandomFileName() {
        SimpleDateFormat simpleDateFormat;
        simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        Random random = new Random();
        int rannum = (int) (random.nextDouble() * (99999 - 10000 + 1)) + 10000;// 获取5位随机数
        return rannum+ "-" +simpleDateFormat.format(new Date());// 当前时间
    }

}
