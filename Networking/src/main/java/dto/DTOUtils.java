package dto;

import domain.Jucator;

public class DTOUtils {

        public static Jucator getFromDTO( JucatorDTO dto){
            String alias = dto.getAlias();
            Integer id = dto.getId();
            Jucator jucator = new Jucator(alias);
            jucator.setId(id);
            return jucator;
        }

    public static JucatorDTO getDTO(Jucator user){
        String alias=user.getAlias();
        Integer id=user.getId();
        return new JucatorDTO(alias,id);
    }
}
