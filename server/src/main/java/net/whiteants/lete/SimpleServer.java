package net.whiteants.lete;


import java.io.*;
import java.net.*;

public class SimpleServer {

  public static void main(String[] args) throws IOException, InterruptedException {
    System.out.println("Server started >>");
    ReaderThread readerThred = new ReaderThread();
    WriterThread writerThread = new WriterThread(readerThred);
    readerThred.start();
    writerThread.start();
    writerThread.join();
    System.exit(0);
    System.out.println("<< Server stopped ");
  }
}