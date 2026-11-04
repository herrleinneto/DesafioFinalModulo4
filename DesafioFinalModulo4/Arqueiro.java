// Arqueiro herda as características básicas de Personagem.
public class Arqueiro extends Personagem {
    public Arqueiro(String nome) {
        super(nome, 100, 20, 8);
    }

    // O Arqueiro tem 25% de chance de causar dano crítico.
    @Override
    public void atacar(Personagem alvo) {
        int dano = getAtaque();

        // Math.random() gera um valor entre 0.0 e 1.0.
        if (Math.random() < 0.25) {
            dano += 15;
            System.out.println(getNome() + " acertou um ataque critico!");
        } else {
            System.out.println(getNome() + " disparou uma flecha!");
        }

        alvo.tomarDano(dano);
    }
}