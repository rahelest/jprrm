package frontend;

import java.util.HashMap;
import java.util.Map;

public class EventAndUIStatusFinder {

	public Map<String, String> find(HttpServletRequest request, HttpServletResponse response) {
		Map<String, String> events = new HashMap<String, String>();
		
		if (request.getParameter("action") != null) {
			events.put("action", request.getParameter("action"))
		}
		return events;
		
	}
}
