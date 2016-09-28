/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package productsfactory.useful;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.FileUtils;

/**
 *
 * @author Daniel Paulo
 */
public class ArquivosUtil {

    /**
     *
     * @param path
     * @return <code>true</code> if the Directory is created with success and <code>false</false> if the Directory is not created
     * @throws java.io.IOException
     */
    public static boolean createDir(String path) throws IOException {

        File file = new File(path);
        if (file.exists()) {
            FileUtils.deleteDirectory(file);
        }
        return new File(path).mkdir();

    }

    /**
     * 
     * @param path
     * @param content
     * @return <code>true</code> if the File is create with success
     * and <code>false</code> if the File is not created
     */
    public static boolean createFile(String path, String content) {

        File file = new File(path);
        if (file.exists())
                file.delete();
        
        try (FileWriter fw = new FileWriter(path, true)) {
            fw.write(content);
            return true;
        } catch (IOException ex) {
            Logger.getLogger(ArquivosUtil.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
}
