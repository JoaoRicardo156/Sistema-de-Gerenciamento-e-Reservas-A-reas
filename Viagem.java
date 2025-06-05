import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

class Passageiro {
    private String nome;
    private String CPF;
    private static int proximoId = 1;
    private int id;

    Passageiro(String nome, String CPF) {
        this.nome = nome;
        this.CPF = CPF;
        this.id = proximoId++;
    }

    public String getNome() {
        return nome;
    }

    public String getCPF() {
        return CPF;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Passageiro{" +
                "nome='" + nome + '\'' +
                ", CPF='" + CPF + '\'' +
                ", id=" + id +
                '}';
    }
}

class Voo {
    int numero;
    String origem;
    String destino;
    private LocalDate data;
    private static int lotacaoMaxima = 150;
    private int lotacaoAtual = 0;
    private List<Passageiro> passageiros = new ArrayList<>();

    Voo(int numero, String origem, String destino, LocalDate data) {
        this.numero = numero;
        this.origem = origem;
        this.destino = destino;
        this.data = data;
    }

    Voo(int numero, String origem, String destino) {
        this.numero = numero;
        this.origem = origem;
        this.destino = destino;
        this.data = null;
    }

    public boolean reservar(Passageiro passageiro) {
        if (lotacaoAtual < lotacaoMaxima) {
            passageiros.add(passageiro);
            lotacaoAtual++;
            return true;
        }
        return false;
    }

    public boolean cancelarReserva(Passageiro passageiro) {
        if (passageiros.remove(passageiro)) {
            lotacaoAtual--;
            return true;
        }
        return false;
    }

    public void listarPassageiros() {
        System.out.println("Passageiros do voo " + numero + ":");
        for (Passageiro passageiro : passageiros) {
            System.out.println(passageiro);
        }
    }

    public int getVagasDisponiveis() {
        return lotacaoMaxima - lotacaoAtual;
    }

    public static int getLotacaoMaxima() {
        return lotacaoMaxima;
    }

    public LocalDate getData() {
        return this.data;
    }

    @Override
    public String toString() {
        return "Voo{" +
                "numero=" + numero +
                ", origem='" + origem + '\'' +
                ", destino='" + destino + '\'' +
                ", data=" + data +
                ", vagas disponíveis=" + getVagasDisponiveis() +
                '}';
    }
}

public class Viagem {
    public static void main(String[] args) {
        Passageiro passageiro1 = new Passageiro("João", "123456789");
        Passageiro passageiro2 = new Passageiro("Maria", "987654321");

        Voo voo1 = new Voo(1, "São Paulo", "Rio de Janeiro", LocalDate.of(2024, 5, 25));
        Voo voo2 = new Voo(2, "Rio de Janeiro", "São Paulo");

        System.out.println("Reserva de " + passageiro1.getNome() + " no voo " + voo1.numero + ": " + voo1.reservar(passageiro1));
        System.out.println("Reserva de " + passageiro2.getNome() + " no voo " + voo1.numero + ": " + voo1.reservar(passageiro2));

        voo1.listarPassageiros();
        System.out.println("Vagas disponíveis no voo " + voo1.numero + ": " + voo1.getVagasDisponiveis());

        System.out.println("Cancelamento de reserva de " + passageiro1.getNome() + " no voo " + voo1.numero + ": " + voo1.cancelarReserva(passageiro1));

        voo1.listarPassageiros();

        System.out.println("Vagas disponíveis no voo " + voo1.numero + ": " + voo1.getVagasDisponiveis());
    }
}