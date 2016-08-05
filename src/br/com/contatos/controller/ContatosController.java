package br.com.contatos.controller;



import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import br.com.contatos.model.Contato;
import br.com.contatos.model.connect.MongoConnect;
import br.com.contatos.model.repository.ContactRepositorio;
import br.com.contatos.model.repository.ContactRepositorioMongo;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

public class ContatosController implements Initializable {


	@FXML TextField txtNome;
	@FXML TextField txtTelefone;
	@FXML Button btnInserir;
	@FXML ListView<Contato> lstView;
	@FXML Button btnDelete;

	ContactRepositorioMongo contatoRepo = new ContactRepositorioMongo();


	@FXML public void InserirContato() {

		Contato novo = new Contato();
		novo.setName(txtNome.getText());
		novo.setPhone(txtTelefone.getText());

		boolean sucesso = contatoRepo.inserir(novo);

		if(sucesso){

			txtNome.setText("");
			txtTelefone.setText("");
			PreencherListView();
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {


		PreencherListView();

		btnInserir.setDisable(true);
		btnDelete.setDisable(true);

		txtNome.textProperty().addListener(p-> validaCampos());

		txtTelefone.textProperty().addListener(p-> validaCampos());


		lstView.getSelectionModel().selectedItemProperty().addListener(p->{

			System.out.println(lstView.getSelectionModel());
			btnDelete.setDisable(false);

		});

	}

	private void PreencherListView() {

		btnDelete.setDisable(true);

		lstView.getItems().clear();


		List<Contato> lista = contatoRepo.obterContatos();
		lstView.getItems().addAll(lista);

	}

	private void validaCampos(){
		if(!txtNome.getText().isEmpty() && !txtTelefone.getText().isEmpty())
		{
			btnInserir.setDisable(false);
		}else{
			btnInserir.setDisable(true);
		}
	}

	@FXML public void Deletar() {


		Contato item = lstView.getSelectionModel().getSelectedItem();

		if(item == null) return;

		boolean sucesso =contatoRepo.deletar(item);

		if(sucesso)
			PreencherListView();



	}

	@FXML public void Editar() {

	}

}
