package Tanks.shared;

import java.io.IOException;
import java.net.SocketException;

public interface ConnectionManage {
	
	void notifyConnectionLoss(Receiver receiver2);
	void run();
	void createComms() throws IOException;
	void sendMessage(Message message);
}
