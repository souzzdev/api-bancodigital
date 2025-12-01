package br.com.cdb.bancodigital.service;

public class CPFValidationService {

    public static boolean validarCPF(String cpf) {
        if (cpf == null) {
            return false;
        }

        // Remove pontos e traço, ou qualquer coisa que não seja dígito
        String cpfLimpo = cpf.replaceAll("\\D", "");

        // Verifica se tem 11 dígitos
        if (cpfLimpo.length() != 11) {
            return false;
        }

        // Verifica se todos os dígitos são iguais (ex: "11111111111")
        if (cpfLimpo.matches("(\\d)\\1{10}")) {
            return false;
        }

        // Calcula o primeiro dígito verificador
        int soma = 0;
        for (int i = 0; i < 9; i++) {
            int num = Character.getNumericValue(cpfLimpo.charAt(i));
            soma += num * (10 - i);
        }
        int resto = soma % 11;
        int primeiroDigito = (resto < 2) ? 0 : (11 - resto);

        // Calcula o segundo dígito verificador
        soma = 0;
        for (int i = 0; i < 10; i++) {
            int num = Character.getNumericValue(cpfLimpo.charAt(i));
            soma += num * (11 - i);
        }
        resto = soma % 11;
        int segundoDigito = (resto < 2) ? 0 : (11 - resto);

        // Verifica se os dígitos calculados conferem com os dígitos informados
        int digitoInformado1 = Character.getNumericValue(cpfLimpo.charAt(9));
        int digitoInformado2 = Character.getNumericValue(cpfLimpo.charAt(10));

        return (primeiroDigito == digitoInformado1 && segundoDigito == digitoInformado2);
    }
}
