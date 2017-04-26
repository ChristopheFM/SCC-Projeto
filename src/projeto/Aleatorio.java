// Classe para geração de números aleatórios segundos várias distribuições
// Apenas a distribuição exponencial negativa está definida

package projeto;

public class Aleatorio {

	// Gera um número segundo uma distribuição exponencial negativa de média m
    static double exponencial (double m){
		return (-m*Math.log(Math.random()));
	}

}