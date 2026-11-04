// Classe base de todos os personagens do jogo.
public class Personagem {
    // Atributos privados: são acessados por métodos da própria classe.
    private String nome;
    private int vida, vidaMaxima, ataque, defesa, pocoes;
    private boolean defendendo;

    // Define os atributos iniciais do personagem.
    public Personagem(String nome, int vida, int ataque, int defesa) {
        this.nome = nome;
        this.vida = vida;
        this.vidaMaxima = vida;
        this.ataque = ataque;
        this.defesa = defesa;
        this.pocoes = 0;
        this.defendendo = false;
    }

    // Causa dano ao personagem escolhido como alvo.
    public void atacar(Personagem alvo) {
        System.out.println(nome + " atacou!");
        alvo.tomarDano(ataque);
    }

    // Prepara o personagem para receber menos dano no próximo ataque.
    public void defender() {
        defendendo = true;
        System.out.println(nome + " esta se defendendo!");
    }

    // Calcula o dano recebido e atualiza a vida.
    public void tomarDano(int dano) {
        int danoFinal = dano - defesa / 3;

        if (defendendo) {
            danoFinal = danoFinal / 2;
            defendendo = false;
        }

        // Garante que o dano mínimo seja 1.
        if (danoFinal < 1) {
            danoFinal = 1;
        }

        vida -= danoFinal;

        // A vida não pode ficar negativa.
        if (vida < 0) {
            vida = 0;
        }

        System.out.println(nome + " recebeu " + danoFinal + " de dano!");
    }

    // Informa se o personagem ainda está vivo.
    public boolean estaVivo() {
        return vida > 0;
    }

    // Adiciona poções ao inventário (contagem simples).
    public void adicionarPocoes(int quantidade) {
        pocoes += quantidade;
    }

    // Usa uma poção e recupera até 20 pontos de vida.
    public boolean usarPocao() {
        if (pocoes > 0 && vida < vidaMaxima) {
            pocoes--;
            vida += 20;

            // Não permite ultrapassar a vida máxima.
            if (vida > vidaMaxima) {
                vida = vidaMaxima;
            }

            System.out.println("Voce recuperou 20 pontos de vida!");
            return true;
        }

        if (pocoes == 0) {
            System.out.println("Voce nao possui pocoes!");
        } else {
            System.out.println("Sua vida ja esta cheia!");
        }
        return false;
    }

    // Getters: permitem consultar os atributos privados.
    public String getNome() { return nome; }
    public int getVida() { return vida; }
    public int getVidaMaxima() { return vidaMaxima; }
    public int getAtaque() { return ataque; }
    public int getPocoes() { return pocoes; }
}