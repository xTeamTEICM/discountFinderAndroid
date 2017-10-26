package eu.jnksoftware.discountfinderandroid.services;

import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by iordkost on 25/10/2017.
 */
public class BinaryIOTest {

    @Test
    public void saveNull() throws Exception {
        BinaryIO test = new BinaryIO();
        assertFalse(test.saveFile(null, null));
    }

    @Test
    public void saveFile() throws Exception {
        File file = new File("file.dat");
        BinaryIO test = new BinaryIO();
        assertTrue(test.saveFile(new FileOutputStream(file), "SomeDataOfFile"));
        assertTrue(file.exists());
    }

    @Test
    public void readNull() throws Exception {
        BinaryIO test = new BinaryIO();
        assertNull(test.readFile(null));
    }

    @Test
    public void readFileExist() throws Exception {
        File file = new File("file.dat");
        BinaryIO test = new BinaryIO();
        assertEquals("SomeDataOfFile", test.readFile(new FileInputStream(file)));
    }

    @Test
    public void readFileNotExist() throws Exception {
        File file = new File("fileAAA.dat");
        BinaryIO test = new BinaryIO();
        assertNull(test.readFile(null));
    }

}