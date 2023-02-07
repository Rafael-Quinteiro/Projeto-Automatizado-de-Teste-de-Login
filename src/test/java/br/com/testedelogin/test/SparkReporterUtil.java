package br.com.testedelogin.test;

import java.io.File;

import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import br.com.testedelogin.util.MetodoUtil;

public class SparkReporterUtil {

    public WebDriver driver;
    
    public static ExtentSparkReporter spark;
    
    public String nomeRelatorioHtml = "reports/Reports-Automation.html";

    /**
     * Construtor da classe.
     * @param driver : Driver utilizado pelo sistema.
     */
    public SparkReporterUtil(WebDriver driver) {
        this.driver = driver;
    }

    public void gerarLogSucesso(ExtentTest teste) {
        String rotularTesteSucesso = "Teste executado com sucesso!";

        teste.log(Status.PASS, rotularTesteSucesso);
    }

    public void gerarLogFalha(ExtentTest teste, Throwable t) {
        teste.log(Status.FAIL, t); 
    }

    public void gerarLogErro(ExtentTest teste, Throwable t) {
        teste.log(Status.FAIL, t);
        
    }  
    
    public void gerarLogFalhaInformacaoQtArquivos(ExtentTest teste, String mensagemFalhaQtArquivos) {
        teste.log(Status.FAIL, mensagemFalhaQtArquivos);
    }

    public void gerarLogSucessoInformacaoQtArquivos(ExtentTest teste, String mensagemSucessoQtArquivos) {
        teste.log(Status.INFO, mensagemSucessoQtArquivos);
    }

    public void gerarLogIgnorado(ExtentTest teste) {
        String logIgnorado = "Teste Ignorado.";

        teste.log(Status.SKIP, logIgnorado);
    }
    
    public void rotularTesteSucesso(ExtentTest teste) {
        String rotularTesteSucesso = "Teste executado com sucesso!";

        teste.log(Status.PASS, MarkupHelper.createLabel(rotularTesteSucesso, ExtentColor.GREEN));
    }

    public void rotularTesteFalha(ExtentTest teste) {
        String rotularTesteFalha = "Ocorreu uma falha ao executar o teste!";

        teste.log(Status.FAIL, MarkupHelper.createLabel(rotularTesteFalha, ExtentColor.RED));
    }   
    
    public void criarPastaScreenshot() {
        File file = new File("src/screenshots/");  

        if (!file.exists()) {
        file.mkdirs();
        }
    }

    public void encerrarTesteFalha(ExtentTest teste, String descricaoTeste, Throwable t) {
        MetodoUtil metodoUtil = new MetodoUtil(driver);
        String file = "src/screenshots/";

        metodoUtil.salvarCapturaDeTela(file, descricaoTeste);

        rotularTesteFalha(teste);

        gerarLogFalha(teste, t);

        teste.fail(MediaEntityBuilder.createScreenCaptureFromPath(metodoUtil.salvarCapturaDeTela(file, descricaoTeste)).build());
    }
}
