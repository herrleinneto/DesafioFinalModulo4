import java.util.Random;
import java.util.Scanner;

// Classe principal: organiza o jogo e controla as batalhas.
public class ArenaCampeoes {
    static Scanner entrada = new Scanner(System.in);
    static Random aleatorio = new Random();

    public static void main(String[] args) {
        iniciarJogo();
    }

    // Pede o nome, permite escolher a classe e inicia a partida.
    public static void iniciarJogo() {
        System.out.println("=== ARENA DOS CAMPEOES ===");
        System.out.print("Digite o nome do seu heroi: ");
        String nome = entrada.nextLine();

        // Não aceita um nome vazio.
        while (nome.trim().isEmpty()) {
            System.out.print("Digite um nome valido: ");
            nome = entrada.nextLine();
        }

        Personagem jogador = escolherClasse(nome);
        jogador.adicionarPocoes(3); // O jogador começa com três poções.

        System.out.println("\\nVoce escolheu: " + jogador.getClass().getSimpleName());
        int vitorias = 0;

        // Enfrenta três inimigos, desde que o jogador continue vivo.
        for (int i = 1; i <= 3 && jogador.estaVivo(); i++) {
            Inimigo inimigo = gerarInimigo(i);
            System.out.println("\\n=== BATALHA " + i + " ===");
            System.out.println("Inimigo encontrado: " + inimigo.getNome());

            if (iniciarBatalha(jogador, inimigo)) {
                vitorias++;
                System.out.println("\\nVoce venceu a batalha!");
            }
        }
        fimDeJogo(jogador, vitorias);
    }

    // Mostra as classes e cria o objeto escolhido pelo jogador.
    public static Personagem escolherClasse(String nome) {
        System.out.println("\\nEscolha sua classe:");
        System.out.println("1 - Guerreiro");
        System.out.println("2 - Mago");
        System.out.println("3 - Arqueiro");

        int opcao;
        // Repete até receber uma opção válida.
        do {
            System.out.print("Opcao: ");
            opcao = lerNumero();
            if (opcao < 1 || opcao > 3) {
                System.out.println("Escolha uma opcao de 1 a 3.");
            }
        } while (opcao < 1 || opcao > 3);

        if (opcao == 1) {
            return new Guerreiro(nome);
        } else if (opcao == 2) {
            return new Mago(nome);
        } else {
            return new Arqueiro(nome);
        }
    }

    // Continua os turnos até um dos personagens ser derrotado.
    public static boolean iniciarBatalha(Personagem jogador, Inimigo inimigo) {
        int turno = 1;
        while (jogador.estaVivo() && inimigo.estaVivo()) {
            System.out.println("\\n--- TURNO " + turno + " ---");
            exibirStatus(jogador, inimigo);
            turnoDoJogador(jogador, inimigo);

            // O inimigo só joga se ainda estiver vivo.
            if (inimigo.estaVivo()) {
                System.out.println("\\nTurno do inimigo:");
                turnoDoInimigo(jogador, inimigo);
            }
            turno++;
        }
        return jogador.estaVivo();
    }

    // Exibe vida dos personagens e quantidade de poções.
    public static void exibirStatus(Personagem jogador, Inimigo inimigo) {
        System.out.println("\\n" + jogador.getNome() + ": "
                + jogador.getVida() + "/" + jogador.getVidaMaxima() + " de vida");
        System.out.println(inimigo.getNome() + ": "
                + inimigo.getVida() + "/" + inimigo.getVidaMaxima() + " de vida");
        System.out.println("Pocoes: " + jogador.getPocoes());
    }

    // Lê a ação do jogador e chama o método correspondente.
    public static void turnoDoJogador(Personagem jogador, Inimigo inimigo) {
        System.out.println("\\n1 - Atacar");
        System.out.println("2 - Defender");
        System.out.println("3 - Usar pocao");

        int opcao;
        // Aceita somente as opções de 1 a 3.
        do {
            System.out.print("Escolha: ");
            opcao = lerNumero();
            if (opcao < 1 || opcao > 3) {
                System.out.println("Opcao invalida!");
            }
        } while (opcao < 1 || opcao > 3);

        if (opcao == 1) {
            jogador.atacar(inimigo);
        } else if (opcao == 2) {
            jogador.defender();
        } else {
            jogador.usarPocao();
        }
    }

    // Executa o turno da inteligência artificial do inimigo.
    public static void turnoDoInimigo(Personagem jogador, Inimigo inimigo) {
        inimigo.realizarTurno(jogador);
    }

    // Sorteia nome e atributos do inimigo.
    public static Inimigo gerarInimigo(int numeroBatalha) {
        // Nomes que podem ser sorteados.
        String[] nomes = {
            "Zargor, o Orc", "Morgath, o Sombrio",
            "Dragor, o Destruidor", "Vark, o Guerreiro",
            "Nexar, o Temido"
        };

        // Escolhe uma posição aleatória do vetor.
        String nome = nomes[aleatorio.nextInt(nomes.length)];

        // Os atributos aumentam conforme o número da batalha.
        int vida = 65 + numeroBatalha * 10 + aleatorio.nextInt(16);
        int ataque = 12 + numeroBatalha * 2 + aleatorio.nextInt(5);
        int defesa = 4 + aleatorio.nextInt(5);
        return new Inimigo(nome, vida, ataque, defesa);
    }

    // Converte a entrada em número e trata quando o usuário digita texto.
    public static int lerNumero() {
        while (true) {
            try {
                return Integer.parseInt(entrada.nextLine());
            } catch (NumberFormatException erro) {
                System.out.print("Digite apenas numeros: ");
            }
        }
    }

    // Apresenta o resultado da partida.
    public static void fimDeJogo(Personagem jogador, int vitorias) {
        System.out.println("\\n=== FIM DE JOGO ===");
        System.out.println("Batalhas vencidas: " + vitorias + "/3");
        System.out.println("Vida final: " + jogador.getVida());

        if (vitorias == 3 && jogador.estaVivo()) {
            System.out.println("PARABENS! Voce conquistou a Arena dos Campeoes!");
        } else {
            System.out.println("Voce foi derrotado! Tente novamente.");
        }
    }
}