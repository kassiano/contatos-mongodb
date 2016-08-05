package br.com.contatos.model.connect;

import com.mongodb.MongoClient;

public class MongoConnect {

	public static MongoClient conectarDb(){

		MongoClient mongo;

		mongo = new MongoClient( "10.107.134.55" , 27017 );

		return mongo;
	}

}
