package projeto;

public class Simulador {

 	// Relógio de simulação - variável que contém o valor do tempo em cada instante
    private double instante;
    // Médias das distribuições de chegadas e de atendimento no serviço
	private double media_cheg, media_serv;
	// Número de clientes que vão ser atendidos
	private int n_clientes;
	// Serviço - pode haver mais do que um num simulador
    private Servico servico;
    // Lista de eventos - onde ficam registados todos os eventos que vão ocorrer na simulação
    // Cada simulador só tem uma
	private ListaEventos lista;

    // Construtor
    public Simulador(double chegada, double temp_servico, int caixas) {
		// Inicialização de parâmetros do simulador
        media_cheg = chegada;
		media_serv = 1.5;
		n_clientes = 100;
		// Inicialização do relógio de simulação
		instante = 0;
		// Criação do serviço
		servico = new Servico (this, temp_servico, caixas);
		// Criação da lista de eventos
		lista = new ListaEventos(this);
		// Agendamento da primeira chegada
        // Se não for feito, o simulador não tem eventos para simular
		insereEvento (new Chegada(instante, this));
    }

        // programa principal
        public static void main(String[] args) {
            // Cria um simulador e
            Simulador s = new Simulador(1,1.5,1);
            // põe-o em marcha
            s.executa();
        }

    // Método que insere o evento e1 na lista de eventos
	void insereEvento (Evento e1){
		lista.insereEvento (e1);
	}

    // Método que actualiza os valores estatísticos do simulador
	private void act_stats(){
		servico.act_stats();
	}

    // Método que apresenta os resultados de simulação finais
	private void relat (){
    	System.out.println();
    	System.out.println("------- Resultados finais -------");
    	System.out.println();
		servico.relat();
	}

    // Método executivo do simulador
	public void executa (){
		Evento e1;
		// Enquanto não atender todos os clientes
		while (servico.getAtendidos() < n_clientes){
    	//	lista.print();  // Mostra lista de eventos - desnecessário; é apenas informativo
			e1 = (Evento)(lista.removeFirst());  // Retira primeiro evento (é o mais iminente) da lista de eventos
			instante = e1.getInstante();         // Actualiza relógio de simulação
			act_stats();                         // Actualiza valores estatísticos
			e1.executa(servico);                 // Executa evento
		};
		relat();  // Apresenta resultados de simulação finais
	}

    // Método que devolve o instante de simulação corrente
    public double getInstante() {
        return instante;
    }

    // Método que devolve a média dos intervalos de chegada
    public double getMedia_cheg() {
        return media_cheg;
    }

    // Método que devolve a média dos tempos de serviço
    public double getMedia_serv() {
        return media_serv;
    }

}