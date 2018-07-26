package com.vitea.util;
/**
 * 
 * @author liushahe
 *
 */
public class ConstantsUtil {
	/**
     * 性别
     */
    public static enum sex{
        
        MAN("1","男"),FEMAN("2","女");
        
        private sex(String value,String name){
            this.value = value;
            this.name = name;
        }
        private final String value;
        private final String name;
        
        public String getValue() {
            return value;
        }

        public String getName() {
            return name;
        }
        
    }

}
