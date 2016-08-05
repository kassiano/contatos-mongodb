package br.com.contatos.model;

public class Contato {

	private int id;
	private String name;
	private String phone;


	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return String.format("%s\t\t%s", name , phone);
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}


}
