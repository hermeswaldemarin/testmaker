package br.com.dmarin.testmaker.web.util;

import java.util.ArrayList;
import java.util.List;

public class ResponseDeleteObject<T> {
	private boolean success;
	
	public ResponseDeleteObject(boolean success){
		this.success = success;
	}
	
	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	
}
