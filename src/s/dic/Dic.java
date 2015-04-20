package s.dic;

import java.io.Serializable;

public class Dic implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -950646627264935463L;
	
	private int id;
	private String txt;
	
	public Dic() {}
	public Dic(int id, String txt) {
		setId(id);
		setTxt(txt);
	}
	
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * @return the txt
	 */
	public String getTxt() {
		return txt;
	}
	/**
	 * @param txt the txt to set
	 */
	public void setTxt(String txt) {
		this.txt = txt;
	}
	
}
