package challenge;

public class CriptografiaCesariana implements Criptografia {
    int nCriptSteps = 3;

    public void validateParameter(String param) {
        if(param == null) {
            throw new NullPointerException("Texto informado não pode ser nulo.");
        }

        if(param.isEmpty())
            throw new IllegalArgumentException("Necessário informado um texto.");
    }

    @Override
    public String criptografar(String texto) {
        String sCript = "";
        texto = texto.toLowerCase();

        this.validateParameter(texto);

        for(int i = 0; i < texto.length(); i++) {
            int num_ascii = (int)texto.charAt(i);

            if (num_ascii < 97 || num_ascii > 122)
                sCript += (char)num_ascii;
            else if ((num_ascii + this.nCriptSteps) <= 122)
                sCript += (char)(num_ascii + this.nCriptSteps);
            else {
                int aux = num_ascii + this.nCriptSteps;
                int restart = ((aux - 122) + 97) - 1;
                sCript += (char)restart;
            }
        }

        return  sCript;
    }

    @Override
    public String descriptografar(String texto) {
        String sDecript = "";
        texto = texto.toLowerCase();

        this.validateParameter(texto);

        for(int i = 0; i < texto.length(); i++){
            int num_ascii = (int)texto.charAt(i);

            if(num_ascii < 97 || num_ascii > 122)
                sDecript += (char)num_ascii;
            else if( (num_ascii - this.nCriptSteps) >= 97)
                sDecript += (char)(num_ascii - this.nCriptSteps);
            else {
                int aux = num_ascii - this.nCriptSteps;
                int restart = (122 - (97 - aux)) - 1;
                sDecript += (char)restart;
            }
        }

        return sDecript;
    }
}
