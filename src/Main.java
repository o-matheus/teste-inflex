import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

public class Main {
    private static final BigDecimal SALARIO_MINIMO = new BigDecimal("1212.00");

    static void main() {

        //3.1 -> Adicionar funcionários
        List<Funcionario> funcionarios = new ArrayList<>();

        funcionarios.add(new Funcionario("Maria", LocalDate.of(2000, 10, 18), new BigDecimal("2009.44"), "Operador"));
        funcionarios.add(new Funcionario("João", LocalDate.of(1990, 5, 12), new BigDecimal("2284.38"), "Operador"));
        funcionarios.add(new Funcionario("Caio", LocalDate.of(1961, 5 , 2), new BigDecimal("9836.14"), "Coordenador"));
        funcionarios.add(new Funcionario("Miguel", LocalDate.of(1988, 10 , 14), new BigDecimal("19119.88"), "Diretor"));
        funcionarios.add(new Funcionario("Alice", LocalDate.of(1995, 1 , 5), new BigDecimal("2234.68"), "Recepcionista"));
        funcionarios.add(new Funcionario("Heitor", LocalDate.of(1999, 11 , 19), new BigDecimal("1582.72"), "Operador"));
        funcionarios.add(new Funcionario("Arthur", LocalDate.of(1993, 3 , 31), new BigDecimal("4071.84"), "Contador"));
        funcionarios.add(new Funcionario("Laura", LocalDate.of(1994, 7 , 8), new BigDecimal("3017.45"), "Gerente"));
        funcionarios.add(new Funcionario("Heloísa", LocalDate.of(2003, 5 , 24), new BigDecimal("1606.85"), "Eletricista"));
        funcionarios.add(new Funcionario("Helena", LocalDate.of(1996, 9 , 2), new BigDecimal("2799.93"), "Gerente"));

        //3.3 -> Imprimir todos os funcionários
        System.out.print("\nPrint do total de funcionários");
        imprimirFuncionarios(funcionarios);


        //3.2 -> Remover funcionário João.
        funcionarios.removeIf(
                funcionario -> funcionario.getNome().equals("João")
        );
        System.out.print("\nApós retirada do João");
        imprimirFuncionarios(funcionarios);

        //3.4 -> Aumento de salário em 10%
        aumentoSalario(funcionarios);
        System.out.printf("%nDepois do aumento de salário:%n");
        for(Funcionario funcionario : funcionarios) {
            System.out.printf("Nome: %s | Salário: %s%n",funcionario.getNome(), funcionario.getSalario());
        }


        //3.10 -> Imprimir lista de funcionários em ordem alfabética.
        ordenarFuncionariosPorNome(funcionarios);
        System.out.printf("%nLista de funcionários em ordem alfabética:%n");
        for(Funcionario funcionario : funcionarios) {
            System.out.println(funcionario.getNome());
        }

        //3.11 -> Total de salários recebidos pelos funcionários.
        System.out.printf("%nTotal de salário recebido pelos funcionários: ");
        calcularTotalSalario(funcionarios);

        //3.12 -> Quantidade de salários minimos
        System.out.printf("%nFuncionário - Quantidade de Salários Minimos.%n");
        quantidadeSalariosMinimos(funcionarios);

    }

    public static void imprimirFuncionarios(List<Funcionario> funcionarios) {
        System.out.println("\nLista de funcionários: ");
        for(Funcionario funcionario : funcionarios) {
            System.out.println(funcionario);
        }
    }

    public static void aumentoSalario(List<Funcionario> funcionarios) {
        BigDecimal valorAumento = new BigDecimal("1.10");

        for(Funcionario funcionario : funcionarios) {
            BigDecimal novoSalario = funcionario.getSalario().multiply(valorAumento);
            funcionario.setSalario(novoSalario);
        }
    }

    public static void quantidadeSalariosMinimos(List<Funcionario> funcionarios) {
        for (Funcionario funcionario : funcionarios) {
            BigDecimal qtdSalarios = funcionario.getSalario().divide(SALARIO_MINIMO, 2, RoundingMode.HALF_UP);
            System.out.printf("%s ganha %s salários minimos.%n", funcionario.getNome(), qtdSalarios);
        }
    }

    public static void calcularTotalSalario(List<Funcionario> funcionarios) {
        BigDecimal totalSalarios = BigDecimal.ZERO;

        for (Funcionario funcionario : funcionarios) {
            totalSalarios = totalSalarios.add(funcionario.getSalario());
        }

        NumberFormat formatadorMoeda = NumberFormat.getCurrencyInstance(Locale.forLanguageTag("pt-BR"));
        System.out.println(formatadorMoeda.format(totalSalarios));
    }

    public static void ordenarFuncionariosPorNome(List<Funcionario> funcionarios) {
        funcionarios.sort(Comparator.comparing(Funcionario::getNome));
    }
}