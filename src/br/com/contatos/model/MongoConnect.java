package br.com.contatos.model;

import com.mongodb.MongoClient;

public class MongoConnect {

	public static MongoClient conectarDb(){
		
		MongoClient mongo;
		
		mongo = new MongoClient( "localhost" , 27017 );
		
		return mongo;
	}
	
}
