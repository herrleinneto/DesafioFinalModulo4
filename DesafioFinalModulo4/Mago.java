// Mago é outra especialização da classe Personagem.
public class Mago extends Personagem {
    public Mago(String nome) {
        // Mago tem ataque alto, mas menos vida e defesa.
        super(nome, 85, 25, 5);
    }

    // Sobrescreve o ataque para lançar uma bola de fogo.
    @Override
    public void atacar(Personagem alvo) {
        System.out.println(getNome() + " lancou uma bola de fogo!");
        alvo.tomarDano(getAtaque() + 7);
    }
}