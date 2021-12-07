package models;

import com.google.gson.annotations.Expose;

public class MemberNotFoundFailure {
	
	@Expose
	private String msg;
	
	public MemberNotFoundFailure() {}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	@Override
	public String toString() {
		return "MemberNotFoundFailure [msg=" + msg + "]";
	};
	
}
