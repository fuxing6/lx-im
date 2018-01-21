package com.lx.lxim;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import junit.framework.TestCase;

public class ClearPoolTest extends TestCase {
  public static void main(String[] args) throws Exception {
    System.out.println(getConnection());
    // testJta();
    // testJmx();
  }

  //
  // public static void testJmx() throws InterruptedException {
  // ClearpoolDataSource dataSource = new ClearpoolDataSource();
  // List<ConfigurationVO> voList = new ArrayList<ConfigurationVO>();
  // ConfigurationVO vo1 = new ConfigurationVO();
  // vo1.setName("myclearpool1");
  // vo1.setDriverClassName("com.mysql.jdbc.Driver");
  // vo1.setUrl("jdbc:mysql://localhost:3306/fwt_schedule");
  // vo1.setCorePoolSize(10);
  // vo1.setMaxPoolSize(50);
  // vo1.setAcquireIncrement(10);
  // vo1.setAcquireRetryTimes(3);
  // vo1.setLimitIdleTime(5);
  // vo1.setShowSql(true);
  // vo1.setJtaSupport(false);
  // vo1.setUsername("root");
  // vo1.setPassword("fx");
  // dataSource.setPort(8805);
  // dataSource.initVO(vo1);
  // TimeUnit.SECONDS.sleep(180);
  // }
  //
  // public static void testJta() throws Exception {
  //
  // ClearpoolDataSource dataSource = new ClearpoolDataSource();
  //
  // List<ConfigurationVO> voList = new ArrayList<ConfigurationVO>();
  // ConfigurationVO vo1 = new ConfigurationVO();
  // vo1.setName("myclearpool1");
  // vo1.setDriverClassName("com.mysql.jdbc.Driver");
  // vo1.setUrl("jdbc:mysql://localhost:3306/fwt_schedule");
  // vo1.setCorePoolSize(10);
  // vo1.setMaxPoolSize(50);
  // vo1.setAcquireIncrement(10);
  // vo1.setAcquireRetryTimes(3);
  // vo1.setLimitIdleTime(5);
  // vo1.setShowSql(true);
  // vo1.setJtaSupport(false);
  // vo1.setUsername("root");
  // vo1.setPassword("fx");
  // ConfigurationVO vo2 = new ConfigurationVO();
  // vo2.setName("myclearpool2");
  // vo2.setDriverClassName("com.mysql.jdbc.Driver");
  // vo2.setUrl("jdbc:mysql://localhost:3306/fwt_schedule1");
  // vo2.setCorePoolSize(10);
  // vo2.setMaxPoolSize(50);
  // vo2.setAcquireIncrement(10);
  // vo2.setAcquireRetryTimes(3);
  // vo2.setLimitIdleTime(5);
  // vo2.setShowSql(true);
  // vo2.setJtaSupport(true);
  // vo2.setUsername("root");
  // vo2.setPassword("fx");
  // voList.add(vo1);
  // voList.add(vo2);
  // dataSource.initVOList(voList);
  // Connection connection = dataSource.getConnection("myclearpool1");
  // PreparedStatement stmt = connection.prepareStatement("insert into t_class values('2','1')");
  // stmt.execute();
  // Connection connection1 = dataSource.getConnection("myclearpool2");
  // PreparedStatement stmt1 = connection1.prepareStatement("insert into t_class values('2','2')");
  // stmt1.execute();
  // // ResultSet resultSet = stmt.getResultSet();
  // // System.out.println(resultSet.getString(0));
  // }
  //
  public static Connection getConnection() {
    // 不同的数据库有不同的驱动
    String driverName = "com.mysql.jdbc.Driver";
    String url = "jdbc:mysql://localhost:3306/fwt_schedule";
    String user = "root";
    String password = "fx";
    Properties connectProperties = new Properties();
    connectProperties.setProperty("user", "root");
    connectProperties.setProperty("password", "fx");

    try {
      // 加载驱动
      Class.forName(driverName);
      // 设置 配置数据
      // 1.url(数据看服务器的ip地址 数据库服务端口号 数据库实例)
      // 2.user
      // 3.password
      return DriverManager.getConnection(url, connectProperties);
      // 开始连接数据库
    } catch (ClassNotFoundException e) {
      // TODO 自动生成的 catch 块
      e.printStackTrace();
    } catch (SQLException e) {
      // TODO 自动生成的 catch 块
      e.printStackTrace();
    }
    return null;

  }
}


