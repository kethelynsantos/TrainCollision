import java.util.Scanner;

public class ColisaoTrens {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean executarNovamente = true;

        while (executarNovamente) {
            double posicaoA, posicaoB, velocidadeA, velocidadeB;

            // Entrada das posições dos trens A e B
            posicaoA = obterNumero("Digite a posição do trem A (entre 0 e 10000): ", 0, 10000);
            posicaoB = obterNumero("Digite a posição do trem B (entre 0 e 10000): ", 0, 10000);

            // Entrada das velocidades dos trens A e B
            velocidadeA = obterNumero("Digite a velocidade do trem A (positiva, até 300 km/h): ", 0, 300);
            velocidadeB = obterNumero("Digite a velocidade do trem B (negativa, até 300 km/h): ", -300, 0);

            // Cálculo da colisão
            double tempoColisao = calcularTempoColisao(posicaoA, posicaoB, velocidadeA, velocidadeB);
            double posicaoColisao = posicaoA + (velocidadeA * tempoColisao / 3600); // Convertendo para quilômetros

            if (tempoColisao >= 0) {
                int horas = 17 + (int) (tempoColisao / 3600);
                int minutos = (int) ((tempoColisao % 3600) / 60);
                int segundos = (int) (tempoColisao % 60);

                System.out.printf("A colisão dos trens acontecerá no km %.1f e ocorrerá após %.1f segundos, às %02d:%02d:%02d\n", posicaoColisao, tempoColisao, horas, minutos, segundos);
            } else {
                System.out.println("Os trens não irão colidir.");
            }

            System.out.print("Deseja executar novamente? (S/N): ");
            String resposta = scanner.next().trim().toUpperCase();

            if (!resposta.equals("S")) {
                executarNovamente = false;
            }
        }

        System.out.println("Fim do programa.");
    }

    public static double obterNumero(String mensagem, double min, double max) {
        Scanner scanner = new Scanner(System.in);
        double numero;

        do {
            System.out.print(mensagem);
            while (!scanner.hasNextDouble()) {
                System.out.println("Você digitou um caractere inválido.");
                System.out.print("Por favor, digite novamente: ");
                scanner.next();
            }
            numero = scanner.nextDouble();

            if (numero < min || numero > max) {
                System.out.println("O número deve ser maior ou igual a zero e menor que 10000.");
                System.out.print("Por favor, digite novamente: ");
            }
        } while (numero < min || numero > max);

        return numero;
    }

    public static double calcularTempoColisao(double posicaoA, double posicaoB, double velocidadeA, double velocidadeB) {
        if (velocidadeA == velocidadeB) {
            return -1; // Os trens nunca colidirão
        }

        double tempoColisao = (posicaoB - posicaoA) / (velocidadeA + Math.abs(velocidadeB));
        return tempoColisao * 3600;
    }

}
