/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.CommonFeature;

import Controller.admin.post.EditPostController;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import static com.sun.xml.internal.ws.spi.db.BindingContextFactory.LOGGER;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.Part;

/**
 *
 * @author Duy Hiep
 */
public class FileUploadHelper {
    public String getFileName(final Part part) {
        final String partHeader = part.getHeader("content-disposition");
        LOGGER.log(Level.INFO, "Part Header = {0}", partHeader);
        for (String content : part.getHeader("content-disposition").split(";")) {
            if (content.trim().startsWith("filename")) {
                return content.substring(
                        content.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;
    }
    
    public void getFileContent(String fileName,Part part,String path){
        FileOutputStream out = null;
        InputStream filecontent = null;
        try {
            out = new FileOutputStream(new File(path + File.separator
                    + fileName));
            try {
                filecontent = part.getInputStream();
            } catch (IOException ex) {
                Logger.getLogger(EditPostController.class.getName()).log(Level.SEVERE, null, ex);
            }

            int read = 0;
            final byte[] bytes = new byte[1024];

            while ((read = filecontent.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
            LOGGER.log(Level.INFO, "File{0}being uploaded to {1}", 
                    new Object[]{fileName, path});
        } catch (FileNotFoundException fne) {
            LOGGER.log(Level.SEVERE, "Problems during file upload. Error: {0}", 
                    new Object[]{fne.getMessage()});
        } catch (IOException ex) {
            Logger.getLogger(EditPostController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException ex) {
                    Logger.getLogger(EditPostController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (filecontent != null) {
                try {
                    filecontent.close();
                } catch (IOException ex) {
                    Logger.getLogger(EditPostController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
    public String getUrlCloudinaryForEdit(File fileUpload,String filename){
        try {
            Cloudinary cloudinary = new Cloudinary();
            Map upload = cloudinary.uploader().upload(fileUpload,
                    ObjectUtils.asMap("cloud_name", "dvr6kh3fs",
                            "api_key", "845592666737482",
                            "api_secret", "92VOfJo1EKYA9DDug8IpQQWSD10",
                            "folder","blog",
                            "public_id",filename,
                            "overwrite",true));
            fileUpload.delete();
            return upload.get("url").toString();
        } catch (IOException ex) {
            Logger.getLogger(FileUploadHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
       return null; 
    }
    
    public String getUrlCloudinaryForAdd(File fileUpload,String filename){
        try {
            Cloudinary cloudinary = new Cloudinary();
            Map upload = cloudinary.uploader().upload(fileUpload,
                    ObjectUtils.asMap("cloud_name", "dvr6kh3fs",
                            "api_key", "845592666737482",
                            "api_secret", "92VOfJo1EKYA9DDug8IpQQWSD10",
                            "folder","upload",
                            "public_id",filename,
                            "overwrite",true));
            fileUpload.delete();
            return upload.get("url").toString();
        } catch (IOException ex) {
            Logger.getLogger(FileUploadHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
       return null; 
    }

}
