package com.example.magada;

public class MagadaUser {

	String _id;
	String _pwd;
	String _name;
	Double _hechldg;
	String _mobile;
	String _species;
	String _dist;
	String _taluk;
	String _village;
	
	public MagadaUser()
	{
	//Empty Constructor	
	}
	
	public MagadaUser(String _id, String _pwd, String _name, Double _hechldg,
			String _mobile, String _species, String _dist, String _taluk,
			String _village) {
		super();
		this._id = _id;
		this._pwd = _pwd;
		this._name = _name;
		this._hechldg = _hechldg;
		this._mobile = _mobile;
		this._species = _species;
		this._dist = _dist;
		this._taluk = _taluk;
		this._village = _village;
	}
	
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
	public Double get_hechldg() {
		return _hechldg;
	}
	public void set_hechldg(Double _hechldg) {
		this._hechldg = _hechldg;
	}
	public String get_mobile() {
		return _mobile;
	}
	public void set_mobile(String _mobile) {
		this._mobile = _mobile;
	}
	public String get_species() {
		return _species;
	}
	public void set_species(String _species) {
		this._species = _species;
	}
	public String get_dist() {
		return _dist;
	}
	public void set_dist(String _dist) {
		this._dist = _dist;
	}
	public String get_taluk() {
		return _taluk;
	}
	public void set_taluk(String _taluk) {
		this._taluk = _taluk;
	}
	public String get_village() {
		return _village;
	}
	public void set_village(String _village) {
		this._village = _village;
	}

}
