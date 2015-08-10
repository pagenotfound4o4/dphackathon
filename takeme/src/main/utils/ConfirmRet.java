package main.utils;

public class ConfirmRet {
	private int status;
	private String message;
	private int info;
	
	public ConfirmRet(int status, String message, int info) {
		this.status = status;
		this.message = message;
		this.info = info;
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

	public int getInfo() {
		return info;
	}

	public void setInfo(int info) {
		this.info = info;
	}
}
