public class Main {
    public static int countTiles(double totalWidth, double tileWidth){
        if((totalWidth/tileWidth)%2!=0){
            return (int)((totalWidth/tileWidth)-1);
        }else{
            return (int) (totalWidth/tileWidth);
        }
    }

    public static double computeGap(double totalWidth, double tileWidth, int tilesNumber){
        return totalWidth-(tilesNumber*tileWidth);
    }
    public static void main(String[] args) {
        double totalWidth=250;
        double tileWidth=12.5;

        System.out.println(countTiles(totalWidth, tileWidth));
    }
}
