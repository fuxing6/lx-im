package com.lx.lxim;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicLong;

import javax.sql.DataSource;

public class ThreadProcessUtils {

  private ThreadProcessUtils() {}

  public static void process(final DataSource dataSource, String name, final int loop,
      int threadCount, final AtomicLong physicalConnStat) throws Exception {
    realProcess(dataSource, name, loop, threadCount, physicalConnStat, null);
  }

  public static void processSql(final DataSource dataSource, String name, final int loop,
      int threadCount, final String sql) throws Exception {
    realProcess(dataSource, name, loop, threadCount, null, sql);
  }

  public static void realProcess(final DataSource dataSource, String name, final int loop,
      int threadCount, final AtomicLong physicalConnStat, final String sql) throws Exception {
    final CountDownLatch startLatch = new CountDownLatch(1);
    final CountDownLatch endLatch = new CountDownLatch(threadCount);
    final CountDownLatch dumpLatch = new CountDownLatch(1);

    Thread[] threads = new Thread[threadCount];
    for (int i = 0; i < threadCount; ++i) {
      Thread thread = new Thread() {

        @Override
        public void run() {
          try {
            startLatch.await();
            for (int i = 0; i < loop; i++) {
              Connection conn = dataSource.getConnection();
              if (sql != null) {
                PreparedStatement pstm = conn.prepareStatement(sql);
                pstm.execute();
                pstm.close();
              }
              conn.close();
            }
          } catch (Exception ex) {
            ex.printStackTrace();
          }
          endLatch.countDown();
          try {
            dumpLatch.await();
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
      };
      threads[i] = thread;
      thread.start();
    }
    long startMillis = System.currentTimeMillis();

    startLatch.countDown();
    endLatch.await();

    long millis = System.currentTimeMillis() - startMillis;

    long[] threadIdArray = new long[threads.length];
    for (int i = 0; i < threads.length; ++i) {
      threadIdArray[i] = threads[i].getId();
    }
    ThreadInfo[] threadInfoArray = ManagementFactory.getThreadMXBean().getThreadInfo(threadIdArray);

    dumpLatch.countDown();

    long blockedCount = 0;
    long waitedCount = 0;
    for (int i = 0; i < threadInfoArray.length; ++i) {
      ThreadInfo threadInfo = threadInfoArray[i];
      blockedCount += threadInfo.getBlockedCount();
      waitedCount += threadInfo.getWaitedCount();
    }

  }
}
