package id.ac.polinema.relasiclass.percobaan4;
public class MainPertanyaan5 { public static void main(String[] args) { Penumpang p=new Penumpang("12345","Mr. Krab");Penumpang budi=new Penumpang("67890","Budi");GerbongGuard g=new GerbongGuard("A",10);g.setPenumpang(p,1);g.setPenumpang(budi,1);System.out.println(g.info()); } }
