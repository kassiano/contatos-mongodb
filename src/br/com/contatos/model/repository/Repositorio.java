package br.com.contatos.model.repository;

import java.util.List;

import br.com.contatos.model.Contato;

public interface Repositorio {

	 List<Contato> obterContatos();
	 boolean deletar(Contato item);
	 boolean inserir(Contato item);
	 boolean atualizar(Contato item);
}
