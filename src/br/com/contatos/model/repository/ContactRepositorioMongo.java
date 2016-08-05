package br.com.contatos.model.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.bson.BsonDocument;
import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.conversions.Bson;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import br.com.contatos.model.Contato;
import br.com.contatos.model.connect.MongoConnect;
import br.com.contatos.model.connect.MySqlConnect;

public class ContactRepositorioMongo {


	public List<Contato> obterContatos(){

		MongoClient mongo = MongoConnect.conectarDb();

		List<Contato> ret = new ArrayList<>();

		MongoDatabase db= mongo.getDatabase("contatos");

		MongoCollection<Document> table = db.getCollection("contact");

		FindIterable<Document> rs = table.find();

		for(Document item : rs){

			Contato c = new Contato();
			c.setName(item.getString("name"));
			c.setPhone(item.getString("phone"));
			c.set_id(item.getObjectId("_id"));
			ret.add(c);
		}

	    return ret;
	}

	public boolean deletar(Contato item){

		MongoClient mongo = MongoConnect.conectarDb();
		MongoDatabase db= mongo.getDatabase("contatos");
		MongoCollection<Document> table = db.getCollection("contact");


		Document d = new Document();
		d.append("_id", item.get_id());

		table.deleteOne(d);

		return true;
	}

	public boolean inserir(Contato item){

		MongoClient mongo = MongoConnect.conectarDb();
		MongoDatabase db= mongo.getDatabase("contatos");
		MongoCollection<Document> table = db.getCollection("contact");

		Document novo = new Document();
		novo.append("name", item.getName());
		novo.append("phone", item.getName());

		table.insertOne(novo);

		return true;
	}


	public boolean atualizar(Contato item){

		MongoClient mongo = MongoConnect.conectarDb();
		MongoDatabase db= mongo.getDatabase("contatos");
		MongoCollection<Document> table = db.getCollection("contact");



		return true;
	}

}
