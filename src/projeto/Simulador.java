package projeto;

public class Simulador {

 	// Rel�gio de simula��o - vari�vel que cont�m o valor do tempo em cada instante
    private double instante;
    // M�dias das distribui��es de chegadas e de atendimento no servi�o
	private double media_cheg, media_serv;
	// N�mero de clientes que v�o ser atendidos
	private int n_clientes;
	// Servi�o - pode haver mais do que um num simulador
    private Servico servico;
    // Lista de eventos - onde ficam registados todos os eventos que v�o ocorrer na simula��o
    // Cada simulador s� tem uma
	private ListaEventos lista;

    // Construtor
    public Simulador(double chegada, double temp_servico, int caixas) {
		// Inicializa��o de par�metros do simulador
        media_cheg = chegada;
		media_serv = 1.5;
		n_clientes = 100;
		// Inicializa��o do rel�gio de simula��o
		instante = 0;
		// Cria��o do servi�o
		servico = new Servico (this, temp_servico, caixas);
		// Cria��o da lista de eventos
		lista = new ListaEventos(this);
		// Agendamento da primeira chegada
        // Se n�o for feito, o simulador n�o tem eventos para simular
		insereEvento (new Chegada(instante, this));
    }

        // programa principal
        public static void main(String[] args) {
            // Cria um simulador e
            Simulador s = new Simulador(1,1.5,1);
            // p�e-o em marcha
            s.executa();
        }

    // M�todo que insere o evento e1 na lista de eventos
	void insereEvento (Evento e1){
		lista.insereEvento (e1);
	}

    // M�todo que actualiza os valores estat�sticos do simulador
	private void act_stats(){
		servico.act_stats();
	}

    // M�todo que apresenta os resultados de simula��o finais
	private void relat (){
    	System.out.println();
    	System.out.println("------- Resultados finais -------");
    	System.out.println();
		servico.relat();
	}

    // M�todo executivo do simulador
	public void executa (){
		Evento e1;
		// Enquanto n�o atender todos os clientes
		while (servico.getAtendidos() < n_clientes){
    	//	lista.print();  // Mostra lista de eventos - desnecess�rio; � apenas informativo
			e1 = (Evento)(lista.removeFirst());  // Retira primeiro evento (� o mais iminente) da lista de eventos
			instante = e1.getInstante();         // Actualiza rel�gio de simula��o
			act_stats();                         // Actualiza valores estat�sticos
			e1.executa(servico);                 // Executa evento
		};
		relat();  // Apresenta resultados de simula��o finais
	}

    // M�todo que devolve o instante de simula��o corrente
    public double getInstante() {
        return instante;
    }

    // M�todo que devolve a m�dia dos intervalos de chegada
    public double getMedia_cheg() {
        return media_cheg;
    }

    // M�todo que devolve a m�dia dos tempos de servi�o
    public double getMedia_serv() {
        return media_serv;
    }

}

// TESTE AGORA EM CAPS