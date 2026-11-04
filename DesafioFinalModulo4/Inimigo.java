// Inimigo também é um tipo de Personagem.
public class Inimigo extends Personagem {
    public Inimigo(String nome, int vida, int ataque, int defesa) {
        super(nome, vida, ataque, defesa);
    }

    // IA simples: o inimigo escolhe entre defender e atacar.
    public void realizarTurno(Personagem jogador) {
        // 20% de chance de defender e 80% de chance de atacar.
        if (Math.random() < 0.2) {
            defender();
        } else {
            atacar(jogador);
        }
    }
}