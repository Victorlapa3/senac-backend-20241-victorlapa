package service;

import java.util.List;

import exception.VacinacaoException;
import model.entity.Pessoa;
import model.entity.Vacinacao;
import model.repository.PessoaRepository;
import model.repository.VacinacaoRepository;

public class PessoaService {

	private PessoaRepository repository = new PessoaRepository();
	
	public Pessoa salvar(Pessoa novaPessoa) throws VacinacaoException {
		validarCamposObrigatorios(novaPessoa);
		
		validarCpf(novaPessoa);
		
		return repository.salvar(novaPessoa);
	}
	
	public boolean atualizar(Pessoa pessoaEditada) throws VacinacaoException {
		validarCamposObrigatorios(pessoaEditada);
		
		//TODO porque não valido o CPF? Veremos em sala
		
		return repository.alterar(pessoaEditada);
	}

	public boolean excluir(int id) throws VacinacaoException {
		VacinacaoRepository vacinacaoRepository = new VacinacaoRepository();
		
	    List<Vacinacao> vacinacoes = vacinacaoRepository.consultarPorIdPessoa(id);
	    if (vacinacoes.isEmpty()) {
	        return repository.excluir(id);			
	    } else {
	        throw new VacinacaoException("A pessoa de ID " + id + " não pode ser excluída pois possui "
	            + vacinacoes.size() + " vacinações cadastradas.");
	    }
	}

	public Pessoa consultarPorId(int id) {
		return repository.consultarPorId(id);
	}

	public List<Pessoa> consultarTodas() {
		return repository.consultarTodos();
	}
	
	private void validarCpf(Pessoa novaPessoa) throws VacinacaoException {
		if(repository.cpfJaCadastrado(novaPessoa.getCpf())) {
			throw new VacinacaoException("CPF " + novaPessoa.getCpf() + " já cadastrado "); 
		}
	}

	private void validarCamposObrigatorios(Pessoa p) throws VacinacaoException{
		String mensagemValidacao = "";
		if(p.getNome() == null || p.getNome().isEmpty()) {
			mensagemValidacao += " - informe o nome \n";
		}
		if(p.getDataNascimento() == null) {
			mensagemValidacao += " - informe a data de nascimento \n";
		}
		if(p.getCpf() == null || p.getCpf().isEmpty() || p.getCpf().length() != 11) {
			mensagemValidacao += " - informe o CPF";
		}
		if(p.getSexo() == ' ') {
			mensagemValidacao += " - informe o sexo";
		}
		if(p.getTipo() < 1 || p.getTipo() > 3) {
			mensagemValidacao += " - informe o tipo (entre 1 e 3)";
		}
		if(p.getPaisOrigem() == null) {
			mensagemValidacao += " - informe o país de origem";
		}
		
		if(!mensagemValidacao.isEmpty()) {
			throw new VacinacaoException("Preencha o(s) seguinte(s) campo(s) \n " + mensagemValidacao);
		}
	}
}
