package com.example.magadaadmin;

import java.security.PublicKey;

public class MagadAdmin {
	/**
	 * @param _id
	 * @param _pwd
	 * @param _name
	 * @param _mobile
	 */
	public MagadAdmin()
	{
		//Empty Constructor
	}
	public MagadAdmin(String _id, String _pwd, String _name, String _mobile) {
		super();
		this._id = _id;
		this._pwd = _pwd;
		this._name = _name;
		this._mobile = _mobile;
	}
	String _id;
	String _pwd;
	String _name;
	String _mobile;
	public String get_id() {
		return _id;
	}
	public void set_id(String _id) {
		this._id = _id;
	}
	public String get_pwd() {
		return _pwd;
	}
	public void set_pwd(String _pwd) {
		this._pwd = _pwd;
	}
	public String get_name() {
		return _name;
	}
	public void set_name(String _name) {
		this._name = _name;
	}
	public String get_mobile() {
		return _mobile;
	}
	public void set_mobile(String _mobile) {
		this._mobile = _mobile;
	}
}
