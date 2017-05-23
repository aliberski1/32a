public class KlasaStawek{

    private double stawka;
    private double stawka_poprzednia;
    private int godziny_pozostale_do_meczu;//pobrac aktualna godzine
    private int ilosc_obstawien_na_mecz;

    public int getMatchTime(string match){
        int time;
        return time;
    }
    public int getMatchBets(string match){
        int bets;
        return bets;
    }
    public double Calculate(string match){

        godziny_pozostale_do_meczu=getMatchTime(string match);
        ilosc_obstawien_na_mecz=getMatchBets(string match);
        stawka=stawka_poprzednia + godziny_pozostale_do_meczu * 0.1 - ilosc_obstawien_na_mecz * 0.1;
        stawka_poprzednia=stawka;

        return stawka;
    }
    //Stawka meczu bedzie uzalezniona od ilosci godzin pozostalych do meczu i ilosci obstawien danego meczu. Im mniej czasu pozostanie do meczu tym mniejsza
    //będzie stawka, a im więcej będzie obstawień danego meczu tym mniejsza będzie stawka.
}