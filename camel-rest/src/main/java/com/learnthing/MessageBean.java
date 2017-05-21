package com.learnthing;

import java.util.Arrays;
import java.util.List;

public class MessageBean {

	public String ping() throws Exception{
		if(true){
			List<String> errors= Arrays.asList("Security ID required");
			APIError error= new APIError("400", "INVALID INPUT", errors);
			
			throw new Exception("Invalid Input");
		}
		return "Rest DSL service is live.";
	}
}
