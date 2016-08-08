package br.com.contatos.model.connect;

import java.sql.Connection;
import java.sql.DriverManager;

import br.com.contatos.helper.Config;

public class MySqlConnect {

	public static Connection ConectarDb(){
		Connection conn=null;

		try {
			Class.forName("com.mysql.jdbc.Driver");

			String ip = Config.DB_SERVER;

			conn = DriverManager.getConnection("jdbc:mysql://"+ ip +"/contatos", "root", "root");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return conn;
	}

}
