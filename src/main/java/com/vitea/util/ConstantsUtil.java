package com.vitea.util;
/**
 * 
 * @author liushahe
 *
 */
public class ConstantsUtil {
	
    public static enum sex{
        //男
        MAN("1","男"),
        //女
        FEMAN("2","女");
        /**
         * 性别
         * @param value
         * @param name
         */
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
