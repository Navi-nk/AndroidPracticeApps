package com.example.magada;

public class MagadaStats {
	
	String _Id;
	Integer _Par1;
	Integer _Par2;
	Integer _Par3;
	Integer _Par4;
	Integer _Par5;
	Integer _Par6;
	Integer _PI;
	Double  _Sdpa;
	Double  _Sdph;
	String  _Dt;
	/**
	 * @param _Id
	 * @param _Par1
	 * @param _Par2
	 * @param _Par3
	 * @param _Par4
	 * @param _Par5
	 * @param _Par6
	 * @param _PI
	 * @param _Sdpa
	 * @param _Sdph
	 * @param _Dt
	 */

	public MagadaStats(String _Id, Integer _Par1, Integer _Par2, Integer _Par3,
			Integer _Par4, Integer _Par5, Integer _Par6, Integer _PI,
			Double _Sdpa, Double _Sdph, String _Dt) {
		super();
		this._Id = _Id;
		this._Par1 = _Par1;
		this._Par2 = _Par2;
		this._Par3 = _Par3;
		this._Par4 = _Par4;
		this._Par5 = _Par5;
		this._Par6 = _Par6;
		this._PI = _PI;
		this._Sdpa = _Sdpa;
		this._Sdph = _Sdph;
		this._Dt = _Dt;
	}
	public String get_Id() {
		return _Id;
	}
	public void set_Id(String _Id) {
		this._Id = _Id;
	}
	public Integer get_Par1() {
		return _Par1;
	}
	public void set_Par1(Integer _Par1) {
		this._Par1 = _Par1;
	}
	public Integer get_Par2() {
		return _Par2;
	}
	public void set_Par2(Integer _Par2) {
		this._Par2 = _Par2;
	}
	public Integer get_Par3() {
		return _Par3;
	}
	public void set_Par3(Integer _Par3) {
		this._Par3 = _Par3;
	}
	public Integer get_Par4() {
		return _Par4;
	}
	public void set_Par4(Integer _Par4) {
		this._Par4 = _Par4;
	}
	public Integer get_Par5() {
		return _Par5;
	}
	public void set_Par5(Integer _Par5) {
		this._Par5 = _Par5;
	}
	public Integer get_Par6() {
		return _Par6;
	}
	public void set_Par6(Integer _Par6) {
		this._Par6 = _Par6;
	}
	public Integer get_PI() {
		return _PI;
	}
	public void set_PI(Integer _PI) {
		this._PI = _PI;
	}
	public Double get_Sdpa() {
		return _Sdpa;
	}
	public void set_Sdpa(Double _Sdpa) {
		this._Sdpa = _Sdpa;
	}
	public Double get_Sdph() {
		return _Sdph;
	}
	public void set_Sdph(Double _Sdph) {
		this._Sdph = _Sdph;
	}
	public String get_Dt() {
		return _Dt;
	}
	public void set_Dt(String _Dt) {
		this._Dt = _Dt;
	}
	
}
