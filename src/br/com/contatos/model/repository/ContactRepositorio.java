package br.com.contatos.model.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.contatos.model.Contato;
import br.com.contatos.model.connect.MySqlConnect;

public class ContactRepositorio implements Repositorio {


	public List<Contato> obterContatos(){

		Connection con = MySqlConnect.ConectarDb();

		String SQL = "SELECT * from contact";

		List<Contato> ret = new ArrayList<>();

	    //ResultSet
	    try {
			ResultSet rs = con.createStatement().executeQuery(SQL);

			 while(rs.next()){

				 Contato c = new Contato();
				 c.setId(rs.getInt("id"));
				 c.setName(rs.getString("name"));
				 c.setPhone(rs.getString("phone"));

				 ret.add(c);
			 }

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	    return ret;
	}

	public boolean deletar(Contato item){

		Connection con = MySqlConnect.ConectarDb();

		String SQL = "delete from contact where id= ?;";

		PreparedStatement preparedStatement;

		try {
			preparedStatement = con.prepareStatement(SQL);
			preparedStatement.setInt(1, item.getId());

			preparedStatement.execute();
			con.close();

			return true;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;
	}

	public boolean inserir(Contato item){

		Connection con = MySqlConnect.ConectarDb();

		String SQL = "insert into contact (name, phone) values(?, ?);";

		PreparedStatement preparedStatement;

		try {
			preparedStatement = con.prepareStatement(SQL);
			preparedStatement.setString(1, item.getName() );
			preparedStatement.setString(2, item.getPhone());
			preparedStatement.executeUpdate();
			con.close();

			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	public boolean atualizar(Contato item){

		Connection con = MySqlConnect.ConectarDb();

		String SQL = "update contact set name=?, phone=? where id= ?;";

		PreparedStatement preparedStatement;

		System.out.println(item);

		try {
			preparedStatement = con.prepareStatement(SQL);
			preparedStatement.setString(1, item.getName());
			preparedStatement.setString(2, item.getPhone());
			preparedStatement.setInt(3, item.getId());

			preparedStatement.execute();
			con.close();

			return true;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;

	}

}
