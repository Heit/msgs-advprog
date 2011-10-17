package net.whiteants.lete;

import org.apache.mina.core.session.IoSession;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * User: Heit
 * Date: 10/16/11
 * Time: 12:24 AM
 */
public class WorkerProtocol {

  private static final Pattern patternMessage = Pattern.compile("<msg session=\"(\\d*)\">(.*)</msg>");
  private static final Pattern quitMessaqe = Pattern.compile("quit");
  private static final Pattern policyMessage = Pattern.compile("<policy-file-request/>(.*)");
  private static final Pattern updateIdMessage = Pattern.compile("<session>(.*)</session>(.*)");

  public class Command {

    private String sessionId;

    private String message;

    private String command;

    private IoSession session;

    public Command(String sessionId, String message, String command) {
      this.sessionId = sessionId;
      this.message = message;
      this.command = command;
    }

    public String getSessionId() {
      return sessionId;
    }

    public String getMessage() {
      return message;
    }

    public String getCommand() {
      return command;
    }

    public IoSession getSession() {
      return session;
    }

    public void setSession(IoSession session) {
      this.session = session;
    }
  }

  public String processInput(String data) {
    Command command = parseCommand(data);
    return processCommand(command);
  }

  public String processMessage(String data, IoSession session) {
    Command command = parseCommand(data);
    command.setSession(session);
    return processCommand(command);
  }

  private Command parseCommand(String data) {
    Matcher messageMatcher = patternMessage.matcher(data);
    Matcher quitMatcher = quitMessaqe.matcher(data);
    Matcher policyMatcher = policyMessage.matcher(data);
    Matcher updateIdMatcher = updateIdMessage.matcher(data);
    Command command;
    if (messageMatcher.matches()) {
      command = new Command(messageMatcher.group(1), messageMatcher.group(2), "MESSAGE");
    } else if (policyMatcher.matches()) {
      command = new Command(null, data, "POLICY");
    } else if (quitMatcher.matches()) {
      command = new Command(null, null, "QUIT");
    } else if (updateIdMatcher.matches()){
      command = new Command(null, updateIdMatcher.group(1), "UPDATEID");
    } else {
      command = new Command(null, null, "UNKNOWN");
    }
    return command;
  }

  private String processCommand(Command command){
    SessionCollector collector = SessionCollector.getInstance();
    if (command.getCommand().equals("MESSAGE")){
      return collector.sendMessage(command);
    } else if (command.getCommand().equals("POLICY")){
      return collector.sendPolicy(command);
    } else if (command.getCommand().equals("UPDATEID")){
      return collector.updateSessionId(command);
    } else if (command.getCommand().equals("QUIT")){
      return "quit";
    } else {
      return "Unknown command";
    }
  }



}
