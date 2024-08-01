package steps;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import org.junit.Assert;
import entidades.Filme;
import entidades.NotaAluguel;
import entidades.TipoAluguel;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import servicos.AluguelService;
import utils.DateUtils;

public class AlugarFilmesSteps {
	private Filme filme;
	private AluguelService aluguel = new AluguelService();
	private NotaAluguel nota;
	private String erro;
	private TipoAluguel tipoAluguel = TipoAluguel.COMUM;

	@Dado("um filme com estoque de {int} unidades")
	public void umFilmeComEstoqueDeUnidades(int int1) throws Throwable {
		filme = new Filme();
		filme.setEstoque(int1);
	}

	@Dado("que o preço do aluguel seja de R$ {int}")
	public void queOPreçoDoAluguelSejaDeR$(int int1) throws Throwable {
		filme.setAluguel(int1);
	}
	
	@Dado("um filme")
	public void um_filme(DataTable table) throws Throwable {
		Map<String, String> map = table.asMap(String.class, String.class);
		filme = new Filme();
		filme.setEstoque(Integer.parseInt(map.get("estoque")));
		filme.setAluguel(Integer.parseInt(map.get("preco")));
	}

	@Quando("alugar")
	public void alugar() throws Throwable {
		try {
			nota = aluguel.alugar(filme, tipoAluguel);
		} catch (RuntimeException e) {
			erro = e.getMessage();
		}
	}

	@Então("o preço do aluguél será R$ {int}")
	public void oPreçoDoAluguélSeráR$(int int1) throws Throwable {
		Assert.assertEquals(int1, nota.getPreco());
	}

	@Então("a data de entrega será no dia seguinte")
	public void aDataDeEntregaSeráNoDiaSeguinte() throws Throwable {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, 1);

		Date dataRetorno = nota.getDataEntrega();
		Calendar calRetorno = Calendar.getInstance();
		calRetorno.setTime(dataRetorno);

		Assert.assertEquals(cal.get((Calendar.DAY_OF_MONTH)), calRetorno.get(Calendar.DAY_OF_MONTH));
		Assert.assertEquals(cal.get((Calendar.MONTH)), calRetorno.get(Calendar.MONTH));
		Assert.assertEquals(cal.get((Calendar.YEAR)), calRetorno.get(Calendar.YEAR));
	}

	@Então("o estoque do filme será {int} unidade")
	public void oEstoqueDoFilmeSeráUnidade(int int1) throws Throwable {
		Assert.assertEquals(int1, filme.getEstoque());
	}

	@Então("não será possivel por falta de estoque")
	public void não_será_possivel_por_falta_de_estoque() throws Throwable {
		Assert.assertEquals("Filme sem estoque", erro);
	}

	@Dado("o tipo do alugel seja (.*)")
	public void o_tipo_do_alugel_seja_extendido(String tipo) throws Throwable {
		tipoAluguel = tipo.equals("semanal")? TipoAluguel.SEMANAL: tipo.equals("extendido")? TipoAluguel.EXTENDIDO: TipoAluguel.COMUM;
	}

	@Então("a data de entrega será em {int} dias")
	public void a_data_de_entrega_será_em_dias(int int1) throws Throwable {
		Date dataEsperada = DateUtils.obterDatadiferencaDias(int1);
		Date dataReal = nota.getDataEntrega();

		DateFormat format = new SimpleDateFormat("dd/MM/yyyy");

		Assert.assertEquals(format.format(dataEsperada), format.format(dataReal));
	}

	@Então("a pontuação recebida será {int} pontos")
	public void a_pontuação_recebida_será_pontos(int int1) throws Throwable {
		Assert.assertEquals(int1, nota.getPontuacao());
	}

}
