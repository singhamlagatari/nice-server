//package com.sl.nice.ui.entrypoint;
//
//import com.sl.nice.ui.model.request.FileRequest;
//import com.sun.jersey.core.header.FormDataContentDisposition;
//import com.sun.jersey.multipart.FormDataParam;
//
//import javax.ws.rs.Consumes;
//import javax.ws.rs.POST;
//import javax.ws.rs.Path;
//import javax.ws.rs.core.MediaType;
//import javax.ws.rs.core.Response;
//import java.io.*;
//import java.util.Date;
//
//@Path("/file")
//public class FileEntrypoint {
//
//    @POST
//    @Path("/upload")
//    @Consumes(MediaType.MULTIPART_FORM_DATA)
//    public Response uploadFile(
//            @FormDataParam("file") InputStream fileInputStream,
//            @FormDataParam("file") FormDataContentDisposition contentDispositionHeader
//    ) {
//
//        Long fileName = new Date().getTime(); //
//        String filePath = "./com/sl/nice/fileUploads/" + fileName.toString();
//
//        // save the file to the server
//
//        try {
//            OutputStream outputStream = new FileOutputStream(new File(filePath));
//
//            int read = 0;
//            byte[] bytes = new byte[1024];
//
//            outputStream = new FileOutputStream(new File(filePath));
//            while ((read = fileInputStream.read(bytes)) != -1) {
//                outputStream.write(bytes, 0, read);
//            }
//            outputStream.flush();
//            outputStream.close();
//        } catch (IOException e) {
//
//            e.printStackTrace();
//        }
//
//
//        String output = "File saved to server location : " + filePath;
//
//        return Response.status(200).entity(output).build();
//
//    }
//
//}
