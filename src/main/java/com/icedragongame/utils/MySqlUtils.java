package com.icedragongame.utils;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.Arrays;
import java.util.List;

/**
 * @author gengxuelong
 * @date 2023/7/2 16:07
 */
@Component
@Data
@Slf4j
@ConfigurationProperties( prefix = "spring.datasource.druid")
public class MySqlUtils {
    private String url;
    private String password;
    private String username;
    private String tableName="category";


    public void test(){
        System.out.println(url);
        System.out.println(password);
        System.out.println(username);
        System.out.println(tableName);
    }

    public void initDatabase(){
        List<String> tableList = Arrays.asList("category","user","post","reply","game");
        boolean flag = true;
        for (String s : tableList) {
            boolean tableExist = isTableExist(s);
            if(!tableExist){
                flag = false;
                break;
            }
        }
        if(!flag){
            writeSqlFileToDatabase("project.sql");
        }else{
            log.info("数据库已存在,不用初始化");
        }
    }
    public boolean isTableExist(String tableName){
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            DatabaseMetaData meta = conn.getMetaData();
            ResultSet resultSet = meta.getTables(null, null, tableName, new String[] {"TABLE"});
            if (resultSet.next()) {
                System.out.println("Table " + tableName + " exists in the database.");
                return true;
            } else {
                System.out.println("Table " + tableName + " does not exist in the database.");
            }
        } catch (SQLException e) {
            System.out.println("An error occurred while checking for table existence: " + e.getMessage());
        }
        return false;
    }

    public void writeSqlFileToDatabase(String filePath){
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url,username,password);
            log.info("没有发现数据库，开始创建......");
            // 创建ScriptRunner，用于执行SQL脚本
            ScriptRunner runner = new ScriptRunner(connection);
            runner.setErrorLogWriter(null);
            runner.setLogWriter(null);
            // 执行SQL脚本
            Class<? extends MySqlUtils> currentClazz = this.getClass();
            InputStream fis = currentClazz.getClassLoader().getResourceAsStream(filePath);
            if (fis==null){
                throw new FileNotFoundException("没有找到初始化sql文件");
            }
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
            runner.runScript(in);
            // 若成功，打印提示信息
            log.info("创建成功");
            System.out.println("SQL file executed successfully.");
        } catch (Exception e) {
            System.out.println("An error occurred while executing SQL file: " + e.getMessage());
        }finally {
            try {
                assert connection != null;
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
