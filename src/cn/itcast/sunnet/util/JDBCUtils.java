package cn.itcast.sunnet.util;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * Created by cdx on 2019/10/28.
 * desc:
 */
public class JDBCUtils {
    private static final String TAG = "jdbcutils";
    private static DataSource ds;

    static {
        //创建properties配置文件对象
        Properties properties = null;
        try {
            properties = new Properties();
            //加载配置文件
            properties.load(JDBCUtils.class.getClassLoader().getResourceAsStream("druid.properties"));
            //创建连接池对象

            ds = DruidDataSourceFactory.createDataSource(properties);


        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /*
     * @author: cdx
     * @desc:获取连接
     * @param ds
     * @return: java.sql.Connection
     * @TO DO:
     * @date: 2019/10/28 14:25
     * @throws:
     */
    public static Connection getConnection() {

        try {
            return ds.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /*
     * @author: cdx
     * @desc:
     * @param stmt
     * @param conn
     * @return: void
     * @TO DO:
     * @date: 2019/10/28 14:48
     * @throws:
     */
    public static void close(Statement stmt, Connection conn) {

        close(null, stmt, conn);
    }

    /*
     * @author: cdx
     * @desc:
     * @param rs
     * @param stmt
     * @param conn
     * @return: void
     * @TO DO:
     * @date: 2019/10/28 14:50
     * @throws:
     */
    public static void close(ResultSet rs, Statement stmt, Connection conn) {

        try {
            if (rs != null)
                rs.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (stmt != null)
                stmt.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {

            if (conn != null)
                conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /*
     * @author: cdx
     * @desc: 获取DataSource
     * @param null
     * @return: ds
     * @TO DO:
     * @date: 2019/10/28 14:52
     * @throws:
     */
    public static DataSource getDataSource() {
        return ds;
    }
}
