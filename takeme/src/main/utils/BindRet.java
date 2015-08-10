package main.utils;

public class BindRet {
	private int status;
	private String message;
	private int binding;
	
	public BindRet(int stat, String msg, int binding) {
		this.status = stat;
		this.message = msg;
		this.binding = binding;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public int getBinding() {
		return binding;
	}
	public void setBinding(int binding) {
		this.binding = binding;
	}
}