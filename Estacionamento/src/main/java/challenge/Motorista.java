package challenge;


import java.util.Objects;

public class Motorista {

    private final String nome;

    private final int idade;

    private final int pontos;

    private final String habilitacao;

    private Motorista(String nome, int idade, int pontos, String habilitacao) {
        this.nome = nome;
        this.idade = idade;
        this.pontos = pontos;
        this.habilitacao = habilitacao;
    }

    public String getNome() {
        return nome;
    }

    public int getIdade() {
        return idade;
    }

    public int getPontos() {
        return pontos;
    }

    public String getHabilitacao() {
        return habilitacao;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Motorista motorista = (Motorista) o;
        return Objects.equals(habilitacao, motorista.habilitacao);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(habilitacao);
    }

    @Override
    public String toString() {
        return "Motorista{" +
                "nome='" + nome + '\'' +
                ", idade=" + idade +
                ", pontos=" + pontos +
                ", habilitacao='" + habilitacao + '\'' +
                '}';
    }

    public static MotoristaBuilder builder() {
        return new MotoristaBuilder();
    }


    public static class MotoristaBuilder {

        private String nome;

        private int idade;

        private int pontos;

        private String habilitacao;

        private MotoristaBuilder() {
        }

        public MotoristaBuilder withNome(String nome) {
            if(nome == null || nome.isEmpty())
                throw new IllegalArgumentException("Nome do Motorista não informado.");

            this.nome = nome;
            return this;
        }

        public MotoristaBuilder withIdade(int idade) {
            if(idade < 0)
                throw new IllegalArgumentException("Idade do Motorista não pode ser negativa.");

            this.idade = idade;
            return this;
        }

        public MotoristaBuilder withPontos(int pontos) {
            if(pontos < 0)
                throw new IllegalArgumentException("Número de pontos do Motorista não pode ser negativo.");

            this.pontos = pontos;
            return this;
        }

        public MotoristaBuilder withHabilitacao(String habilitacao) {
            if(habilitacao == null || habilitacao.isEmpty())
                throw new IllegalArgumentException("Habilitação do Motorista não informada.");

            this.habilitacao = habilitacao;
            return this;
        }


        public Motorista build() {
            if(this.nome == null || this.nome.isEmpty())
                throw new NullPointerException("Nome do Motorista não informado.");

            if(this.idade < 0)
                throw new IllegalArgumentException("Idade do Motorista não pode ser negativa.");

            if(this.pontos < 0)
                throw new IllegalArgumentException("Número de pontos do Motorista não pode ser negativo.");

            if(this.habilitacao == null || this.habilitacao.isEmpty())
                throw new NullPointerException("Habilitação do Motorista não informada.");

            return new Motorista(nome, idade, pontos, habilitacao);
        }
    }
}