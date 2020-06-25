package challenge;

import java.util.ArrayList;
import java.util.List;

public class Estacionamento {
    public final int MAX_VAGAS = 10;
    public final int IDADE_MINIMA_DIRIGIR = 18;
    public final int MAX_PONTOS_CARTEIRA = 20;

    private final List<Carro> carrosEstacionados = new ArrayList<>();

    public void estacionar(Carro carro) {
        if(carro.getMotorista() == null)
            throw new EstacionamentoException("Carros autônomos não são permitidos.");

        if(carro.getMotorista().getIdade() < this.IDADE_MINIMA_DIRIGIR)
            throw new EstacionamentoException("Motorista não tem idade mínima para dirigir.");

        if(carro.getMotorista().getPontos() > this.MAX_PONTOS_CARTEIRA)
            throw new EstacionamentoException("Motorista possui habilitação suspensa.");

        if(this.carrosEstacionados() == this.MAX_VAGAS) {
            Carro carroARemover = null;
            for (Carro c : this.carrosEstacionados) {
                if(c.getMotorista().getIdade() <= 55) {
                    carroARemover = c;
                    break;
                }
            }

            if(carroARemover == null)
                throw new EstacionamentoException("Não há vagas disponíveis.");
            else {
                int posicaoSubstituir = this.carrosEstacionados.indexOf(carroARemover);

                this.carrosEstacionados.set(posicaoSubstituir, carro);
            }

        } else {
            carrosEstacionados.add(carro);
        }

    }

    public int carrosEstacionados() {
        return this.carrosEstacionados.size();
    }

    public boolean carroEstacionado(Carro carro) {
        return this.carrosEstacionados.stream().filter(c -> c.equals(carro)).count() > 0;
    }
}
