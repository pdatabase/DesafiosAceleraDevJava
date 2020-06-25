package br.com.codenation;

import br.com.codenation.exceptions.CapitaoNaoInformadoException;
import br.com.codenation.exceptions.IdentificadorUtilizadoException;
import br.com.codenation.exceptions.JogadorNaoEncontradoException;
import br.com.codenation.exceptions.TimeNaoEncontradoException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


public class DesafioMeuTimeApplication implements MeuTimeInterface {
	private final List<Time> times = new ArrayList<>();

	private List<Time> getTimes() {
		return this.times;
	}

	private Time buscarTimePorId(long idTime) throws TimeNaoEncontradoException {
		for (Time t : this.getTimes()) {
			if(t.getId().equals(idTime))
				return t;
		}

		throw new TimeNaoEncontradoException("Time Id " + idTime + " não encontrado.");
	}

	private boolean existeTime(Time time){
		for (Time t : this.getTimes()) {
			if(t.getId().equals(time.getId()))
				return true;
		}

		return false;
	}

	public void incluirTime(Long id, String nome, LocalDate dataCriacao, String corUniformePrincipal, String corUniformeSecundario)
			throws IdentificadorUtilizadoException {
		Time time = new Time();
		time.setId(id);
		time.setNome(nome);
		time.setDataCriacao(dataCriacao);
		time.setCorUniformePrincipal(corUniformePrincipal);
		time.setCorUniformeSecundario(corUniformeSecundario);

		if(this.existeTime(time))
			throw new IdentificadorUtilizadoException("Time Id " + time.getId() + " já existe.");
		else
			this.times.add(time);
	}

	public void incluirJogador(Long id, Long idTime, String nome, LocalDate dataNascimento, Integer nivelHabilidade, BigDecimal salario)
			throws IdentificadorUtilizadoException, TimeNaoEncontradoException {
		Time time = buscarTimePorId(idTime);

		Jogador jogador = new Jogador();
		jogador.setTime(time);
		jogador.setId(id);
		jogador.setIdTime(idTime);
		jogador.setNome(nome);
		jogador.setDataNascimento(dataNascimento);
		jogador.setNivelHabilidade(nivelHabilidade);
		jogador.setSalario(salario);

		time.adicionaJogador(jogador);
	}

	private Jogador buscarJogadorEmTodosTimesPorIdJogador(long idJogador)
				throws JogadorNaoEncontradoException {

		try {
			Time time = this.buscarTimeEmTodosTimesPorIdJogador(idJogador);

			return time.buscarJogadorPorId(idJogador);
		} catch (TimeNaoEncontradoException e) {
			throw new JogadorNaoEncontradoException("Jogador Id " + idJogador + " não encontrado.");
		}
	}

	private Time buscarTimeEmTodosTimesPorIdJogador(long idJogador)
			throws TimeNaoEncontradoException {

		for (Time time : this.getTimes())
			if(time.existeJogador(idJogador))
				return time;

		throw new TimeNaoEncontradoException("Nenhum time encontrado para o Jogador Id " + idJogador + ".");
	}

	public void definirCapitao(Long idJogador) throws JogadorNaoEncontradoException {

		Jogador jogador = this.buscarJogadorEmTodosTimesPorIdJogador(idJogador);
		Time time = jogador.getTime();

		time.setCapitaoTime(jogador);
	}

	public Long buscarCapitaoDoTime(Long idTime) throws TimeNaoEncontradoException, CapitaoNaoInformadoException{
		Time time = this.buscarTimePorId(idTime);

		Jogador jogadorCapitao = time.getCapitaoTime();

		if(jogadorCapitao == null)
			throw new CapitaoNaoInformadoException("Jogador capitão não encontrado para o Time id " + idTime);

		return jogadorCapitao.getId();
	}

	public String buscarNomeJogador(Long idJogador) throws JogadorNaoEncontradoException {
		return this.buscarJogadorEmTodosTimesPorIdJogador(idJogador).getNome();
	}

	public String buscarNomeTime(Long idTime) throws TimeNaoEncontradoException{
		return this.buscarTimePorId(idTime).getNome();
	}

	public List<Long> buscarJogadoresDoTime(Long idTime) throws TimeNaoEncontradoException {
		Time time = this.buscarTimePorId(idTime);

		List<Jogador> jogadores = time.getJogadores();
		List<Long> idList = new ArrayList<>();

		// Sorting
		Collections.sort(jogadores, new Comparator<Jogador>() {
			@Override
			public int compare(Jogador j1, Jogador j2)
			{
				return j1.getId().compareTo(j2.getId());
			}
		});

		for (Jogador j : jogadores)
			idList.add(j.getId());

		return idList;
	}

	public Long buscarMelhorJogadorDoTime(Long idTime) throws TimeNaoEncontradoException {
		Time time = this.buscarTimePorId(idTime);

		List<Jogador> jogadores = time.getJogadores();

		// Sorting
		Collections.sort(jogadores, new Comparator<Jogador>() {
			@Override
			public int compare(Jogador j1, Jogador j2)
			{
				return j2.getNivelHabilidade().compareTo(j1.getNivelHabilidade());
			}
		});

		return jogadores.get(0).getId();
	}

	public Long buscarJogadorMaisVelho(Long idTime) throws TimeNaoEncontradoException {
		Time time = this.buscarTimePorId(idTime);
		List<Jogador> jogadores = time.getJogadores();

		if(jogadores.size() > 0) {
			List<Jogador> sortedUsers = jogadores.stream()
					.sorted(Comparator.comparing(Jogador::getDataNascimento))
					.collect(Collectors.toList());

			return sortedUsers.get(0).getId();
		}
		
		return 0l;
	}

	public List<Long> buscarTimes() {
		List<Long> timesList = new ArrayList<>();

		// Sorting
		Collections.sort(this.getTimes(), new Comparator<Time>() {
			@Override
			public int compare(Time t1, Time t2)
			{
				return t1.getId().compareTo(t2.getId());
			}
		});

		for (Time time : this.getTimes()) {
			timesList.add(time.getId());
		}

		return timesList;
	}

	public Long buscarJogadorMaiorSalario(Long idTime) throws  TimeNaoEncontradoException {
		Time time = this.buscarTimePorId(idTime);

		List<Jogador> sortedUsers = time.getJogadores().stream()
				.sorted(Comparator.comparing(Jogador::getSalario).reversed())
				.collect(Collectors.toList());

		return sortedUsers.get(0).getId();
	}

	public BigDecimal buscarSalarioDoJogador(Long idJogador) throws JogadorNaoEncontradoException {
		return this.buscarJogadorEmTodosTimesPorIdJogador(idJogador).getSalario();
	}

	public List<Long> buscarTopJogadores(Integer top) {
		List<Jogador> jogadores = new ArrayList<>();
		List<Long> topJogadores = new ArrayList<>();

		for (Time time : this.getTimes()) {
			for (Jogador jogador : time.getJogadores()) {
				jogadores.add(jogador);
			}
		}

		if(jogadores.size() > 0) {
			Comparator<Jogador> comparePorMaiorHabilidadeMenorId = Comparator
					.comparing(Jogador::getNivelHabilidade).reversed()
					.thenComparing(Jogador::getId);

			List<Jogador> jogadoresOrdenados = jogadores.stream()
					.sorted(comparePorMaiorHabilidadeMenorId)
					.collect(Collectors.toList());

			for (int i = 0; i < top; i++) {
				topJogadores.add(jogadoresOrdenados.get(i).getId());
			}
		}

		return topJogadores;
	}
}
