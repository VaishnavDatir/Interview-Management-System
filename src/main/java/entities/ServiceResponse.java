package entities;

public class ServiceResponse {
	public ServiceResponse() {
		super();
	}

	private boolean success;
	private String message;
	private String msgDesc;
	private Object obj;

	public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getMsgDesc() {
		return msgDesc;
	}

	public void setMsgDesc(String errorDesc) {
		this.msgDesc = errorDesc;
	}

	public ServiceResponse(boolean success, String message) {
		super();
		this.success = success;
		this.message = message;
	}

	public ServiceResponse(boolean success, String message, String errorDesc) {
		super();
		this.success = success;
		this.message = message;
		this.msgDesc = errorDesc;
	}

	@Override
	public String toString() {
		return "ServiceResponse [success=" + success + ", message=" + message + ", errorDesc=" + msgDesc + ", obj="
				+ obj + "]";
	}

	

}
