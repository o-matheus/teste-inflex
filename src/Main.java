import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private static final BigDecimal SALARIO_MINIMO = new BigDecimal("1212.00");

    static void main() {
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

        System.out.print("\nPrint do total de funcionários");
        imprimirFuncionarios(funcionarios);

        funcionarios.removeIf(
                funcionario -> funcionario.getNome().equals("João")
        );

        System.out.print("\nApós retirada do João");
        imprimirFuncionarios(funcionarios);

        aumentoSalario(funcionarios);

        System.out.print("\nDepois do aumento de salário");
        imprimirFuncionarios(funcionarios);


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
}