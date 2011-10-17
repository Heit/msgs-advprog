package net.whiteants.lete;

import java.io.Console;

/**
 * User: Heit
 * Date: 10/16/11
 * Time: 12:24 AM
 */
public class ReaderThread extends Thread {

  private WorkerProtocol workerProtocol;

  public static boolean alive = true;

  @Override
  public void run() {
    workerProtocol = new WorkerProtocol();
    Console console = System.console();
    String data = null;
    while ((data = console.readLine()) != null) {
      String result = "";
      if ((result = workerProtocol.processInput(data)).equals("quit")){
        alive = false;
        return;
      }
      else {
        console.writer().write(result + "\n");
        console.writer().flush();
      }
    }
  }
}
