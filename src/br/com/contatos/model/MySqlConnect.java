package br.com.contatos.model;

import java.sql.Connection;
import java.sql.DriverManager;

public class MySqlConnect {



	public static Connection ConectarDb(){
		Connection conn=null;

		try {
			Class.forName("com.mysql.jdbc.Driver");

			conn = DriverManager.getConnection("jdbc:mysql://10.107.134.52/contatos", "root", "root");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return conn;
	}

}
