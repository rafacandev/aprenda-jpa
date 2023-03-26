package aprenda.jpa.qrcode;

import static aprenda.jpa.qrcode.ConstantesDeQrCode.QR_CODE_CODIGO;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class VerificadorDeQrCode {
    public static void verificarQrCode(QrCode qrCode) {
        assertEquals(QR_CODE_CODIGO, qrCode.getCodigo());
    }
}
