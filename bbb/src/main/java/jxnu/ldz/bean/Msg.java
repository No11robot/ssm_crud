package jxnu.ldz.bean;

import java.util.HashMap;
import java.util.Map;

import jxnu.ldz.bean.Msg;

public class Msg {

	//״̬��   100-�ɹ�    200-ʧ��
	private int code;
	//��ʾ��Ϣ
	private String msg;
		
	//�û�Ҫ���ظ����������Ϣ
	private Map<String, Object> extend = new HashMap<String, Object>();
	
	public static Msg success(){
		Msg result = new Msg();
		result.setCode(100);
		result.setMsg("����ɹ�");
		return result;
	}
	
	public static Msg fail(){
		Msg result = new Msg();
		result.setCode(200);
		result.setMsg("����ʧ��");
		return result;
	}
	
	public Msg add(String key,Object value){
		this.getExtend().put(key, value);
		return this;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Map<String, Object> getExtend() {
		return extend;
	}

	public void setExtend(Map<String, Object> extend) {
		this.extend = extend;
	}
}
