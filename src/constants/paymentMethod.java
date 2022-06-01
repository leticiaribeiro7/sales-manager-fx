/*******************************************************************************
Autor: Leticia Teixeira Ribeiro dos Santos
Componente Curricular: MI Programação
Concluido em: 11/04/2022
Declaro que este código foi elaborado por mim de forma individual e não contém nenhum
trecho de código de outro colega ou de outro autor, tais como provindos de livros e
apostilas, e páginas ou documentos eletrônicos da Internet. Qualquer trecho de código
de outra autoria que não a minha está destacado com uma citação para o autor e a fonte
do código, e estou ciente que estes trechos não serão considerados para fins de avaliação.
******************************************************************************************/

package constants;
/**
 *
 * Enumerador com constantes que servem para definir método de pagamento.
 *
 */
	public enum paymentMethod {
		A_VISTA(0),
		CREDITO(1),
		DEBITO(2),
		PIX(3);
		
		
		private int index;
		
		paymentMethod(int i) {
			index = i;
		}

		
		public int getIndex() {
			return this.index;
		}
		
	}
