package models;

public class Ticket {
   
        private String movieType;
        private String cineplexCode;
        private String ageCat;
        private String dayType;
        private boolean afterSix;
        private int price;

        public String getMovieType() {
            return movieType;
        }
        public void setMovieType(String movieType) {
            this.movieType = movieType;
        }
        public String getCineplexCode() {
            return cineplexCode;
        }
        public void setCineplexCode(String cineplexCode) {
            this.cineplexCode = cineplexCode;
        }
        public String getAgeCat() {
            return ageCat;
        }
        public void setAgeCat(String ageCat) {
            this.ageCat = ageCat;
        }
        public String getDayType() {
            return dayType;
        }
        public void setTime(String dayType) {
            this.dayType = dayType;
        }
        public int getPrice() {
            return price;
        }
        public void setPrice(int price) {
            this.price = price;
        }
        
        public boolean getAfterSix(){
            return afterSix;
        }

        public void setAfterSix(boolean afterSix){
            this.afterSix=afterSix;
        }

        private static final String CSV_SEPARATOR =",";
        
        public String toString() {
            StringBuffer oneLine = new StringBuffer();
            oneLine.append(this.getMovieType());
            oneLine.append(CSV_SEPARATOR);
            oneLine.append(this.getCineplexCode());
            oneLine.append(CSV_SEPARATOR);
            oneLine.append(this.getAgeCat());
            oneLine.append(CSV_SEPARATOR);
            oneLine.append(this.getDayType());
            oneLine.append(CSV_SEPARATOR);
            oneLine.append(this.getAfterSix())
            oneLine.append(CSV_SEPARATOR);
            oneLine.append(this.getPrice());
            return oneLine.toString();
            
        }
        
}
