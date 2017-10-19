package com.qbao.log;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * @author song.j
 * @create 2017-10-16 17:17:18
 **/
public class Constant {

    private static Properties properties;


    //email config
    public static boolean sendEmailEnable = false;
    public static String emailSmtpHost = "smtp.exmail.qq.com";
    public static String emailUser = "";
    public static String emailPass = "";
    public static String subject = "zabbix qblogger error";
    public static String sendTo = "";


    //message config
    public static boolean sendMessageEnable = false;

    static {

        loadProperties();

        if (properties != null && properties.size() > 0) {
            loadPropConfig();
        } else {
            loadDefaultConfig();
        }

    }

    /**
     * 加载默认配置
     */
    private static void loadDefaultConfig() {
//        sendEmailEnable = false;
//        sendMessageEnable = false;
    }


    /**
     * 加载配置文件
     */
    private static void loadPropConfig() {

        //load email properties
        sendEmailEnable = Boolean.valueOf(properties.getProperty("email.enable", "false"));
        emailSmtpHost = properties.getProperty("email.smtp.host", emailSmtpHost);
        emailUser = properties.getProperty("email.user", emailUser);
        emailPass = properties.getProperty("email.password", emailPass);
        sendTo = properties.getProperty("email.send.user", sendTo);
        subject = properties.getProperty("email.subject", subject);


        //load message properties
        sendMessageEnable = Boolean.valueOf(properties.getProperty("message.enable", "false"));

    }


    private static void loadProperties() {

        String classpath = Constant.class.getResource("/").getPath();

        System.out.println("classpath: " + classpath);

        try {

            File file = new File(classpath + "qb-logger.properties");

            if (!file.exists()) {
                System.out.println("classpath qb-logger.properties not found ");

                String sysPaht = System.getProperty("qb-logger");
                if (sysPaht == null){
                    System.out.println("system qb-logger.properties not found use default config");
                    return;
                }
                file = new File(sysPaht);

                if (!file.exists()){
                    System.out.println("system qb-logger = " + sysPaht + " but file not exist");
                    return;
                }
            }

            properties = new Properties();
            properties.load(new FileInputStream(file));

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("load properties error");
        }
    }

}
