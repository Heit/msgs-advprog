package net.whiteants.lete;

import org.apache.mina.core.session.IoSession;

/**
 * User: Heit
 * Date: 10/16/11
 * Time: 12:24 AM
 */
public class SessionWrapper {

  private String id;

  private IoSession session;

  public SessionWrapper(String id, IoSession session) {
    this.id = id;
    this.session = session;
  }

  public synchronized String getId() {
    return id;
  }

  public synchronized void setId(String id) {
    this.id = id;
  }

  public IoSession getSession() {
    return session;
  }

}
