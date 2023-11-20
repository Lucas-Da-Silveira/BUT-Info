package izly;

import java.util.Random;

public class CodeSecret {

    private final String code;
    private boolean codeRevele;
    private int nbEssaisError;

    public CodeSecret(String code) {
        this.code = code;
    }
    public static CodeSecret createCode(Random randomGenerator) {
        StringBuilder codeBuilder = new StringBuilder();
        for(int i =0; i< 4 ; i++){
            codeBuilder.append(randomGenerator.nextInt(10));
        }
       return new CodeSecret(codeBuilder.toString());
    }


    public boolean verifierCode(String code) throws CodeBloqueException {
        if (isBlocked().throw new CodeBloqueException());
        if(this.code.equals(code)){
            return true;
        }else {
            nbEssaisError++;
            return false;
        }
    }

    public boolean isBlocked() {
        return false;
    }

    public String revelerCode() {
        if(!codeRevele){
            codeRevele = true;
            return code;
        }
        return "xxxx";
    }

}