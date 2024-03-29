package Tanks.shared;

import java.io.IOException;

import Tanks.shared.messageTypes.Message;

public interface ConnectionManage {
	
	void notifyConnectionLoss(Receiver receiver2);
	void run();
	void createComms() throws IOException;
	void sendMessage(Message message);
}
