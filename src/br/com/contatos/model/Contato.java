package br.com.contatos.model;

import org.bson.types.ObjectId;

public class Contato {

	private int id;
	private String name;
	private String phone;
	private ObjectId _id;


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

	public ObjectId get_id() {
		return _id;
	}

	public void set_id(ObjectId _id) {
		this._id = _id;
	}


}
