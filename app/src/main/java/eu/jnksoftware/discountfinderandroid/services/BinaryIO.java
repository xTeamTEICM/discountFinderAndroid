package eu.jnksoftware.discountfinderandroid.services;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Created by iordkost on 25/10/2017.
 */

public class BinaryIO {
    public boolean saveFile(FileOutputStream fos, Object object) {

        boolean status = false;
        try {
            ObjectOutputStream oos;
            oos = new ObjectOutputStream(fos);
            oos.writeObject(object);
            oos.close();
            fos.close();
            status = true;
        } catch (Exception e) {
            //e.printStackTrace();
        }

        return status;
    }

    public Object readFile(FileInputStream fis) {
        Object object = null;

        try {
            ObjectInputStream is = new ObjectInputStream(fis);
            object = is.readObject();
            is.close();
            fis.close();
        } catch (Exception e) {
            //e.printStackTrace();
        }

        return object;
    }
}
