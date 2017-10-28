package eu.jnksoftware.discountfinderandroid.models;

import android.os.Build;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Date;

import eu.jnksoftware.discountfinderandroid.services.BinaryIO;

import static org.junit.Assert.*;

/**
 * Created by makis on 26/10/2017.
 */
public class UserTest {
    @Test
    public void getID() throws Exception {
        User testUser= new User(1,"makis","kovanidis","makis_kovanidis@hotmail.com",Role.CUSTOMER,new Date(),new Date(),"aaaaaaaa");
        assertEquals(1, testUser.getID());
    }

    @Test
    public void setID() throws Exception {
        User testUser= new User(1,"makis","kovanidis","makis_kovanidis@hotmail.com",Role.CUSTOMER,new Date(),new Date(),"aaaaaaaa");
        testUser.setID(222);
        assertEquals(222, testUser.getID());
    }

    @Test
    public void getFirstName() throws Exception {
        User testUser= new User(1,"makis","kovanidis","makis_kovanidis@hotmail.com",Role.CUSTOMER,new Date(),new Date(),"aaaaaaaa");
        assertEquals("makis", testUser.getFirstName());
    }

    @Test
    public void setFirstName() throws Exception {
        User testUser= new User(1,"makis","kovanidis","makis_kovanidis@hotmail.com",Role.CUSTOMER,new Date(),new Date(),"aaaaaaaa");
        testUser.setFirstName("nikos");
        assertEquals("nikos", testUser.getFirstName());
    }

    @Test
    public void getLastName() throws Exception {
        User testUser= new User(1,"makis","kovanidis","makis_kovanidis@hotmail.com",Role.CUSTOMER,new Date(),new Date(),"aaaaaaaa");
        assertEquals("kovanidis", testUser.getLastName());
    }

    @Test
    public void setLastName() throws Exception {
        User testUser= new User(1,"makis","kovanidis","makis_kovanidis@hotmail.com",Role.CUSTOMER,new Date(),new Date(),"aaaaaaaa");
        testUser.setLastName("papadopoylos");
        assertEquals("papadopoylos", testUser.getLastName());
    }

    @Test
    public void getEmail() throws Exception {
        User testUser= new User(1,"makis","kovanidis","makis_kovanidis@hotmail.com",Role.CUSTOMER,new Date(),new Date(),"aaaaaaaa");
        assertEquals("makis_kovanidis@hotmail.com", testUser.getEmail());
    }

    @Test
    public void setEmail() throws Exception {
        User testUser= new User(1,"makis","kovanidis","makis_kovanidis@hotmail.com",Role.CUSTOMER,new Date(),new Date(),"aaaaaaaa");
        testUser.setEmail("makis1986@windowslive.com");
        assertEquals("makis1986@windowslive.com", testUser.getEmail());
    }

    @Test
    public void getRole() throws Exception {
        User testUser= new User(1,"makis","kovanidis","makis_kovanidis@hotmail.com",Role.CUSTOMER,new Date(),new Date(),"aaaaaaaa");
        assertEquals(Role.CUSTOMER, testUser.getEmail());
    }

    @Test
    public void setRole() throws Exception {
        User testUser= new User(1,"makis","kovanidis","makis_kovanidis@hotmail.com",Role.CUSTOMER,new Date(),new Date(),"aaaaaaaa");
        testUser.setRole(Role.SELLER);
        assertEquals(Role.SELLER, testUser.getEmail());
    }

    @Test
    public void getCreationDate() throws Exception {
        User testUser= new User(1,"makis","kovanidis","makis_kovanidis@hotmail.com",Role.CUSTOMER,new Date(2017,5,20),new Date(),"aaaaaaaa");
        assertEquals(2017, testUser.getCreationDate().getYear());
        assertEquals(5, testUser.getCreationDate().getMonth());
        assertEquals(20, testUser.getCreationDate().getDate());
    }

    @Test
    public void setCreationDate() throws Exception {
        User testUser= new User(1,"makis","kovanidis","makis_kovanidis@hotmail.com",Role.CUSTOMER,new Date(2017,5,20),new Date(),"aaaaaaaa");
        testUser.setCreationDate(new Date(2017, 8,4));
        assertEquals(2017, testUser.getCreationDate().getYear());
        assertEquals(8, testUser.getCreationDate().getMonth());
        assertEquals(4, testUser.getCreationDate().getDate());
    }

    @Test
    public void getLastLogin() throws Exception {
        User testUser= new User(1,"makis","kovanidis","makis_kovanidis@hotmail.com",Role.CUSTOMER,new Date(),new Date(2017,5,20),"aaaaaaaa");
        assertEquals(2017, testUser.getLastLogin().getYear());
        assertEquals(5, testUser.getLastLogin().getMonth());
        assertEquals(20, testUser.getLastLogin().getDate());
    }

    @Test
    public void setLastLogin() throws Exception {
        User testUser= new User(1,"makis","kovanidis","makis_kovanidis@hotmail.com",Role.CUSTOMER,new Date(),new Date(2017,5,20),"aaaaaaaa");
        testUser.setCreationDate(new Date(2017, 8,4));
        assertEquals(2017, testUser.getLastLogin().getYear());
        assertEquals(8, testUser.getLastLogin().getMonth());
        assertEquals(4, testUser.getLastLogin().getDate());
    }

    @Test
    public void getToken() throws Exception {
        User testUser= new User(1,"makis","kovanidis","makis_kovanidis@hotmail.com",Role.CUSTOMER,new Date(),new Date(),"aaaaaaaa");
        assertEquals("aaaaaaaa",testUser.getToken());
    }

    @Test
    public void setToken() throws Exception {
        User testUser= new User(1,"makis","kovanidis","makis_kovanidis@hotmail.com",Role.CUSTOMER,new Date(),new Date(),"aaaaaaaa");
        testUser.setToken("otheosBohthos");
        assertEquals("otheosBohthos",testUser.getToken());
    }

    @Test
    public void saveTest() throws Exception{
        User testUser= new User(1,"makis","kovanidis","makis_kovanidis@hotmail",Role.CUSTOMER,new Date(),new Date(),"aaaaaaaa");
        BinaryIO testIO=new BinaryIO();
        assertTrue(testIO.saveFile(new FileOutputStream("myUSer.dat"),testUser));
    }

    @Test
    public  void loadTest() throws Exception{
        User testUSer;
        BinaryIO testIO=new BinaryIO();
        testUSer=(User) testIO.readFile(new FileInputStream("myUSer.dat"));
        assertNotNull(testUSer);
        assertEquals("makis",testUSer.getFirstName());
    }





}