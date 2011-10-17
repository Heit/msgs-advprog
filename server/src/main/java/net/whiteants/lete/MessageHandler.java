package net.whiteants.lete;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

/**
 * User: Heit
 * Date: 10/16/11
 * Time: 12:24 AM
 */
public class MessageHandler extends IoHandlerAdapter {

  private WorkerProtocol workerProtocol = new WorkerProtocol();

  public static Charset charset = Charset.forName("UTF-8");

  public static CharsetDecoder decoder = charset.newDecoder();

  @Override
  public void sessionCreated(IoSession session) throws Exception {
    SessionCollector.getInstance().addSession(session);
  }

  @Override
  public void sessionClosed(IoSession session) throws Exception {
    SessionCollector.getInstance().removeSession(session);
  }

  @Override
  public void messageReceived(IoSession session, Object message) throws Exception {
    IoBuffer buffer = (IoBuffer)message;
    String msg = bb_to_str(buffer.buf());
    System.out.println(workerProtocol.processMessage(msg, session));
  }

  public static String bb_to_str(ByteBuffer buffer){
  String data = "";
  try{
    int old_position = buffer.position();
    data = decoder.decode(buffer).toString();
    // reset buffer's position to its original so it is not altered:
    buffer.position(old_position);
  }catch (Exception e){
    e.printStackTrace();
    return "";
  }
  return data;
}
}
