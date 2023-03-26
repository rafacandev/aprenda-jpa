package aprenda.jpa.qrcode;

import static aprenda.jpa.qrcode.ConstantesDeQrCode.QR_CODE_CODIGO;

public class FabricaDeQrCode {
    public static QrCode novoQrCode() {
        var qrCode = new QrCode();
        qrCode.setCodigo(QR_CODE_CODIGO);
        return qrCode;
    }
}
