package br.com.contatos.controller;



import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import br.com.contatos.helper.Config;
import br.com.contatos.model.Contato;
import br.com.contatos.model.connect.MongoConnect;
import br.com.contatos.model.repository.ContactRepositorio;
import br.com.contatos.model.repository.ContactRepositorioMongo;
import br.com.contatos.model.repository.Repositorio;
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
	@FXML Button btnEditar;


	private Repositorio contatoRepo; //Repositorio de acesso a dados
	private boolean modoEdit =false; //variavel para controlar se está em modo de edição ou inserção
	private Contato itemselecionado=null;//variavel que guarda o item selecionado para edição

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		if(Config.MongoDB){
			contatoRepo = new ContactRepositorioMongo();
		}else{
			contatoRepo = new ContactRepositorio();
		}

		PreencherListView();

		btnInserir.setDisable(true);
		btnDelete.setDisable(true);


		//Esses dois listenners verificam se os campos estão preenchidos para, então,
		//habilitar o botão de inserir/salvar
		txtNome.textProperty().addListener(p-> validaCampos());
		txtTelefone.textProperty().addListener(p-> validaCampos());


		//Este listenner verifica se existe algum contato selcionado na ListView
		//Para então, habilitar as ações de deletar e editar
		lstView.getSelectionModel().selectedItemProperty().addListener(p->{

			Contato item = lstView.getSelectionModel().getSelectedItem();

			//se tiver algum item selecionado, habilita os botões, caso contrario desabilitar
			if(item !=null){
				btnDelete.setDisable(false);
				btnEditar.setDisable(false);
			}else{
				btnDelete.setDisable(true);
				btnEditar.setDisable(true);
			}
		});

	}

	@FXML public void InserirContato() {

		Contato item = new Contato();
		item.setName(txtNome.getText());
		item.setPhone(txtTelefone.getText());

		boolean sucesso =false;

		if(!modoEdit){
			//inserir novo elemento
		    sucesso = contatoRepo.inserir(item);

		}else{
			//atualizar
			item.set_id(itemselecionado.get_id());//mongoDb
			item.setId(itemselecionado.getId());//mysql
			sucesso = contatoRepo.atualizar(item);
		}

		if(sucesso){

			txtNome.setText("");
			txtTelefone.setText("");
			btnInserir.setText("Inserir");
			modoEdit=false;
			itemselecionado = null;
			PreencherListView();
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
		itemselecionado = lstView.getSelectionModel().getSelectedItem();

		if(itemselecionado == null) return;

		txtNome.setText(itemselecionado.getName());
		txtTelefone.setText(itemselecionado.getPhone());

		btnInserir.setText("Salvar");
		modoEdit = true;
	}

	private void PreencherListView() {

		btnDelete.setDisable(true);
		btnEditar.setDisable(true);

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

}
