package services;

import java.util.List;

import model.entity.Pessoa;
import model.repository.PessoaRepository;

public class PessoaService {

	private PessoaRepository pessoaRepository = new PessoaRepository();
	
	public Pessoa salvar(Pessoa novaPessoa) {
		//TODO validar se o CPF já consta no banco
		//Caso sim -> devolver exceção
		
		//Caso não -> salva no banco a novaPessoa
		
		salvar(novaPessoa);
		
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
	private void validarPessoa(Pessoa pessoa) throws ControleDeVacinaException{
		if(pessoa.getNome() == null || pessoa.getNome() == "") {
			throw new ControleDeVacinaException("O campo 'Nome' deve ser preenchido.");
		}
		if(pessoa.getDtnascimento() == null) {
			throw new ControleDeVacinaException("O campo 'Data de nascimento' deve ser preenchido.");
		}
		if(pessoa.getSexo() == null || pessoa.getSexo() == "") {
			throw new ControleDeVacinaException("O campo 'Sexo' deve ser preenchido.");
		}
		if(pessoa.getCpf() == null || pessoa.getCpf() == "") {
			throw new ControleDeVacinaException("O campo 'CPF' deve ser preenchido.");
		} 
		if(pessoa.getTipo() == null || pessoa.getTipo() == "") {
			throw new ControleDeVacinaException("O campo 'Tipo' deve ser preenchido.");
		}
	}
}
