package aprenda.jpa.qrcode;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class QrCodeRepositoryTest {
    private static final String QR_CODE_CODIGO = "Meu codigo";

    @Autowired
    private QrCodeRepository qrCodeRepository;

    @Test
    void save_NovoQrCode_Entao_VerificarNoBanco() {
        var qrCode = new QrCode();
        qrCode.setCodigo(QR_CODE_CODIGO);

        qrCodeRepository.save(qrCode);

        qrCodeRepository
                .findById(qrCode.getId())
                .ifPresentOrElse(QrCodeRepositoryTest::verificarQrCode, Assertions::fail);
    }

    private static void verificarQrCode(QrCode qrCodeNoBanco) {
        assertEquals(QR_CODE_CODIGO, qrCodeNoBanco.getCodigo());
    }
}
