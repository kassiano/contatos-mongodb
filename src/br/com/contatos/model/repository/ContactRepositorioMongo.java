package br.com.contatos.model.repository;


import java.util.ArrayList;
import java.util.List;
import org.bson.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import br.com.contatos.model.Contato;
import br.com.contatos.model.connect.MongoConnect;


public class ContactRepositorioMongo implements Repositorio  {

	MongoClient mongo;
	MongoDatabase db;
	MongoCollection<Document> table;

	private final static String DB_NAME="DBContatos";
	private final static String COLLECTION_NAME="contato";


	public ContactRepositorioMongo() {
		mongo = MongoConnect.conectarDb();
		db= mongo.getDatabase(DB_NAME);
		table = db.getCollection(COLLECTION_NAME);
	}


	public List<Contato> obterContatos(){

		List<Contato> ret = new ArrayList<>();

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


		Document d = new Document();
		d.append("_id", item.get_id());

		table.deleteOne(d);

		return true;
	}

	public boolean inserir(Contato item){


		Document novo = new Document();
		novo.append("name", item.getName());
		novo.append("phone", item.getName());

		table.insertOne(novo);

		return true;
	}

	public boolean atualizar(Contato item){

		Document old = new Document("_id", item.get_id());

		Document atualizado = new Document();
		atualizado.append("name", item.getName());
		atualizado.append("phone", item.getPhone());

		table.updateOne(
			   old,
			    new Document("$set", atualizado)
			);

		return true;
	}

}
