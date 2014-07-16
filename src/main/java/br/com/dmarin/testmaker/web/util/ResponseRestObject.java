package br.com.dmarin.testmaker.web.util;

import java.util.ArrayList;
import java.util.List;

public class ResponseRestObject<T> {
	private boolean success;
	private long total;
	private List<T> data;
	
	public ResponseRestObject(boolean success, T data){
		this.success = success;
		this.data = new ArrayList<T>();
		this.data.add(data);
	}
	
	public ResponseRestObject(boolean success, List<T> data, long total){
		this.success = success;
		this.data = data;
		this.total = total;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}
	
	
}
