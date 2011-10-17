package net.whiteants.lete;

import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.charset.Charset;

/**
 * User: Heit
 * Date: 10/16/11
 * Time: 12:24 AM
 */
public class WriterThread extends Thread {

  private static final int PORT = 8099;

  private ReaderThread readerThread;

  public WriterThread(ReaderThread readerThread) {
    this.readerThread = readerThread;
  }

  @Override
  public void run() {
    IoAcceptor acceptor = new NioSocketAcceptor();
    try {
      acceptor.setHandler(new MessageHandler());
      acceptor.getSessionConfig().setReadBufferSize(2048);
      acceptor.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, 10);
      acceptor.bind(new InetSocketAddress(PORT));
      readerThread.join();
      acceptor.unbind();
    } catch (IOException e) {
      e.printStackTrace();
    } catch (InterruptedException e) {
      e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
    }

  }
}
