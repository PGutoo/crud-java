package com.crud.java.domain;

import org.springframework.stereotype.Component;

@Component
public class CpfCnpjUtils {

    public static boolean isCpfOrCnpjValid(String documento) {
        if (documento == null) {
            return false;
        }

        // Remove caracteres não numéricos
        documento = documento.replaceAll("[^0-9]", "");

        if (documento.length() == 11) {
            return isValidCpf(documento);
        } else if (documento.length() == 14) {
            return isValidCnpj(documento);
        }

        return false; // Não é CPF nem CNPJ
    }

    // Validação de CPF
    private static boolean isValidCpf(String cpf) {
        if (cpf.matches("(\\d)\\1{10}")) {
            return false; // CPF com todos os dígitos iguais é inválido
        }

        int soma = 0;
        for (int i = 0; i < 9; i++) {
            soma += Character.getNumericValue(cpf.charAt(i)) * (10 - i);
        }
        int digito1 = 11 - (soma % 11);
        digito1 = (digito1 > 9) ? 0 : digito1;

        soma = 0;
        for (int i = 0; i < 10; i++) {
            soma += Character.getNumericValue(cpf.charAt(i)) * (11 - i);
        }
        int digito2 = 11 - (soma % 11);
        digito2 = (digito2 > 9) ? 0 : digito2;

        return cpf.endsWith(String.valueOf(digito1) + String.valueOf(digito2));
    }

    // Validação de CNPJ
    private static boolean isValidCnpj(String cnpj) {
        if (cnpj.matches("(\\d)\\1{13}")) {
            return false; // CNPJ com todos os dígitos iguais é inválido
        }

        int[] pesosPrimeiroDigito = {5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
        int[] pesosSegundoDigito = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};

        int soma = 0;
        for (int i = 0; i < 12; i++) {
            soma += Character.getNumericValue(cnpj.charAt(i)) * pesosPrimeiroDigito[i];
        }
        int digito1 = 11 - (soma % 11);
        digito1 = (digito1 > 9) ? 0 : digito1;

        soma = 0;
        for (int i = 0; i < 13; i++) {
            soma += Character.getNumericValue(cnpj.charAt(i)) * pesosSegundoDigito[i];
        }
        int digito2 = 11 - (soma % 11);
        digito2 = (digito2 > 9) ? 0 : digito2;

        return cnpj.endsWith(String.valueOf(digito1) + String.valueOf(digito2));
    }

}
