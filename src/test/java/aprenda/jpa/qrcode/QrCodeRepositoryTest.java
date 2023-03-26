package aprenda.jpa.qrcode;

import aprenda.jpa.util.ApagadorDeRepositorios;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static aprenda.jpa.qrcode.FabricaDeQrCode.novoQrCode;

@SpringBootTest
public class QrCodeRepositoryTest {
    @Autowired
    private QrCodeRepository qrCodeRepository;
    @Autowired
    private ApagadorDeRepositorios apagadorDeRepositorios;

    @BeforeEach
    void antesDeCadaTest() {
        apagadorDeRepositorios.apagarTudo();
    }

    @Test
    void save_NovoQrCode_Entao_VerificarNoBanco() {
        var qrCode = novoQrCode();

        qrCodeRepository.save(qrCode);

        qrCodeRepository
                .findById(qrCode.getId())
                .ifPresentOrElse(VerificadorDeQrCode::verificarQrCode, Assertions::fail);
    }
}
