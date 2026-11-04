// Guerreiro herda os atributos e métodos de Personagem.
public class Guerreiro extends Personagem {
    public Guerreiro(String nome) {
        // Guerreiro começa com mais vida e defesa.
        super(nome, 120, 18, 12);
    }

    // Sobrescreve o ataque comum para causar dano extra.
    @Override
    public void atacar(Personagem alvo) {
        System.out.println(getNome() + " atacou com sua espada!");
        alvo.tomarDano(getAtaque() + 4);
    }
}