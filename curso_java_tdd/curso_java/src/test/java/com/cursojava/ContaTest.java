package com.cursojava;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import com.cursojava.entities.Conta;
import com.cursojava.exceptions.SaldoInsuficienteException;

public class ContaTest {

    private Conta conta; 

    // Método usado para instanciar variáveis estáticas; Executa apenas 1 vez; 
    @BeforeAll
    public static void setUpClass(){
    
    }

    @AfterAll
    public static void tearDownClass(){
    
    }

    // Método executado antes de cada teste 
    @BeforeEach
    public void setUp(){
        conta = new Conta(); 
    }

    // Método executado depois de cada teste 
    @AfterEach
    public void tearDown(){
        // liberação de recursos, close(), etc. 
    }
    
    @Test
    public void deve_depositar_valor_em_uma_conta_e_saldo_deve_ser_atualizado(){
        
        conta.depositar(100); 
        
        assertEquals(100, conta.getSaldo(), 0.1); 
    }
    
    @ParameterizedTest
    @ValueSource(doubles = {10, 30, 100.10, 50.54})
    public void deve_atualizar_saldo_quando_existir_multiplos_depositos(double valor){
        
        conta.depositar(valor); 
        assertEquals(valor, conta.getSaldo(), 0.1);
    }

    @DisplayName("Deposito com valores negativos")
    @Test
    public void nao_deve_aceitar_deposito_com_valores_negativos(){

        assertThrows(IllegalArgumentException.class, () -> conta.depositar(-300.0)); 
        
        assertEquals(0, conta.getSaldo()); 

    }
    
    @Test
    public void nao_deve_aceitar_deposito_com_valor_zero(){ 
        
        assertThrows(IllegalArgumentException.class, () -> conta.depositar(0));
        
        assertEquals(0, conta.getSaldo());           
    }

    @Test
    public void deve_sacar_valor_de_uma_conta_quando_ela_possuir_saldo (){
        conta.depositar(500);

        conta.sacar(100);

        assertEquals(400, conta.getSaldo()); 
    }

    @Test
    public void nao_deve_sacar_valor_de_uma_conta_quando_ela_nao_possuir_saldo (){
        conta.depositar(100);

        SaldoInsuficienteException e = assertThrows(SaldoInsuficienteException.class, () -> conta.sacar(500));
        
        assertEquals("Saldo insuficiente", e.getMessage()); 
    }

    @Test
    public void nao_deve_aceitar_saque_com_valores_invalidos(){ 
        
        assertThrows(IllegalArgumentException.class, () -> conta.sacar(0));

        assertThrows(IllegalArgumentException.class, () -> conta.sacar(-100));
    }
}
