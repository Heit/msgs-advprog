package net.whiteants.lete;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * User: Heit
 * Date: 10/16/11
 * Time: 12:24 AM
 */
public class SessionCollector {

  private static SessionCollector instance;

  private List<SessionWrapper> sessions = new ArrayList<SessionWrapper>();

  private SessionCollector() {
  }

  private Integer sessionCounter = 0;

  public static SessionCollector getInstance() {
    if (instance == null) instance = new SessionCollector();
    return instance;
  }

  public synchronized void addSession(IoSession session) {
    sessionCounter++;
    this.sessions.add(new SessionWrapper(sessionCounter.toString(), session));
  }

  public synchronized void removeSession(IoSession session) {
    for (SessionWrapper sw : this.sessions)
      if (sw.getSession().equals(session)) {
        this.sessions.remove(sw);
        break;
      }
  }

  public String sendMessage(WorkerProtocol.Command command) {
    if (command.getSession() == null)
      return sendMessage(command.getSessionId(), command.getMessage());
    else
      return sendMessage(command.getSession(), command.getMessage());
  }

  private String sendMessage(String sessionId, String message) {
    String status = "No session found with Id " + sessionId;
    for (SessionWrapper sw : this.sessions)
      if (sw.getId().equals(sessionId)) {
        this.sendMessage(sw.getSession(), message);
        status = "Message sent";
      }
    return status;
  }

  private String sendMessage(IoSession session, String message) {
    IoBuffer buffer = IoBuffer.allocate(100);
    buffer.setAutoExpand(true);
    String zero = "\u0000";
    try {
      buffer.putString(message + zero, Charset.forName("UTF-8").newEncoder());
    } catch (CharacterCodingException e) {
      e.printStackTrace();
    }
    buffer.flip();
    session.write(buffer);
    return "Message sent";
  }

  public String sendPolicy(WorkerProtocol.Command command) {
    return sendPolicy(command.getSession());
  }

  private String sendPolicy(IoSession session) {
    String result = "No policy found";
    try {
      InputStream is = new BufferedInputStream(this.getClass()
          .getClassLoader().getResourceAsStream("crossdomain.xml"));
      byte[] data = new byte[is.available()];
      is.read(data);
      is.close();
      sendMessage(session, new String(data, "UTF-8"));
      result = "Policy was send";
    } catch (Exception e) {
      e.printStackTrace();
    }
    return result;
  }

  public String updateSessionId(WorkerProtocol.Command command) {
    for (SessionWrapper sw : this.sessions)
      if (sw.getSession().equals(command.getSession()))
        sw.setId(command.getMessage());
    return "Id updated";
  }


}

