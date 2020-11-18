public class CoinFactory {

    public Coin getCoin(String coinType){
        if(coinType == null){
            return null;
        }
        if(coinType.equalsIgnoreCase("ILS")) {
            return new ILS();

        } else if(coinType.equalsIgnoreCase("USD")){
            return new USD();
        }

//        switch(coinType){
//            case coinType.equalsIgnoreCase("ILS"):
//                 return new ILS();
//            case coinType.equalsIgnoreCase("USD"):
//                return new USD();
//            default:
//                return null;
//        }
        return null;
    }
}