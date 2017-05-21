/**
 * 
 */
package com.learnthing;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author ue
 *
 */
public class APIError {

	private String status;
	private String code;
	private List<String> errors;

	public APIError(String status, String code, List<String> errors) {
		super();
		this.status = status;
		this.code = code;
		this.errors = errors;
	}

	public String getStatus() {
		return status;
	}

	public String getCode() {
		return code;
	}

	public List<String> getErrors() {
		return errors;
	}

/*	@Override
	public String getMessage() {
	
		return new StringBuilder()
		        .append("status:"+status)
		        .append(",code:"+code)
		        .append(",errors:"+errors)
		        .toString();
	}
	{
		  "errors": [
		    {
		      "type": "",
		      "code": "",
		      "message": "",
		      "params": "map<string, string>"
		    }
		  ]
		}


*/	
}
