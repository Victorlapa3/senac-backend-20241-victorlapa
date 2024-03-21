package service;

import java.util.List;

import model.entity.Pessoa;
import model.repository.PessoaRepository;

public class PessoaService {

	private PessoaRepository pessoaRepository = new PessoaRepository();
	
	public Pessoa salvar(Pessoa novaPessoa) {
		//TODO validar se o CPF já consta no banco
		//Caso sim -> devolver exceção
		
		//Caso não -> salva no banco a novaPessoa
		
		return pessoaRepository.salvar(novaPessoa);
	}
	
	public boolean excluir(int id) {
		
		return this.pessoaRepository.excluir(id);
	}
	public Pessoa consultarPorId(int id) {
		return this.pessoaRepository.consultarPorId(id);
	}

	public List<Pessoa> consultarTodas() {
		return this.pessoaRepository.consultarTodos();
	}
}
