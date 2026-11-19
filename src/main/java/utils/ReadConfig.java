package utils;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ReadConfig {

    Properties pro;

    public ReadConfig(){

        File src = new File("src/main/resources/config.properties");

        try{
            FileInputStream fis = new FileInputStream(src);
            pro = new Properties();
            pro.load(fis);
        }catch (Exception e){
            System.out.println("Exception is "+ e.getMessage());
        }
    }

    public String getApplicationURL(){
        String url = pro.getProperty(("baseURL"));
        return url;
    }

    public String getUserName(){
        String userName = pro.getProperty(("userName"));
        return userName;
    }

    public String getpassword(){
        String passWord = pro.getProperty(("passWord"));
        return passWord;
    }

    public String getChromePath(){
        String chromePath = pro.getProperty(("chromePath"));
        return chromePath;
    }

    public String getFirefoxPath(){
        String fireFoxPath = pro.getProperty(("fireFoxPath"));
        return fireFoxPath;
    }

    public String getMsEdgePath(){
        String msEdgePath = pro.getProperty(("edgePath"));
        return msEdgePath;
    }
}
