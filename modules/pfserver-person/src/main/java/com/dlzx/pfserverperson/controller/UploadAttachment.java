package com.dlzx.pfserverperson.controller;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Iterator;
import java.util.List;


/**
 * @author ylh
 * @program:dlzx
 * @description：上传附件
 * @create:2020-11-21 19-52
 */
@RestController
@RequestMapping("/uploadFile")
public class UploadAttachment  extends HttpServlet {

    @Override
    @PostMapping("/file")
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        doGet(req, resp);
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpServletRequest request = (HttpServletRequest)req;
        HttpServletResponse response = (HttpServletResponse)resp;

 /* response.setContentType("text/html;charset=gb2312");
  response.setCharacterEncoding("utf-8");*/

        OutputStream outputStream = null;
        InputStream inputStream = null;

        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload fileUpload = new ServletFileUpload(factory);

        try {
            List items = fileUpload.parseRequest(request);
            for (Iterator iterator = items.iterator(); iterator.hasNext();) {
                FileItem name = (FileItem) iterator.next();
                if(!name.isFormField()){
                    String fieldName  = name.getFieldName();  //这个是name值
                    String fileName = name.getName();     //这个是全路径
                    String lastFileName ="";

                    //这句话获取的是源文件的原名称，不做任何修改
                    String oldNamePath = fileName.substring(fileName.lastIndexOf("\\")+1);

                    if(fileName.endsWith(".docx")|| fileName.endsWith(".xls")||fileName.endsWith(".")){

                        lastFileName = request.getRealPath("/")+"\\upload\\"+ oldNamePath;
                        outputStream = new FileOutputStream(new File(lastFileName ));

                        inputStream = name.getInputStream();
                        byte[] bs = new byte[1024];
                        int length = 0;
                        while(null != inputStream && (length = inputStream.read(bs))!=-1){
                            outputStream.write(bs);
                        }
                    }
                    outputStream.flush();
                }
                //把lastFileName存到数据库（这里就不写了不只lz用的什么方式）}
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}