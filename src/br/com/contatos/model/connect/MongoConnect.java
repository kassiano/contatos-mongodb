package br.com.contatos.model.connect;

import com.mongodb.MongoClient;

import br.com.contatos.helper.Config;

public class MongoConnect {

	public static MongoClient conectarDb(){

		MongoClient mongo;

		mongo = new MongoClient( Config.DB_SERVER , 27017 );

		return mongo;
	}

}
