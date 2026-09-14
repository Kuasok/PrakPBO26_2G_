package id.ac.polinema.relasiclass.percobaan4;
public class GerbongGuard {
 private String kode; private Kursi[] arrayKursi; public GerbongGuard(String kode,int jumlah){this.kode=kode;arrayKursi=new Kursi[jumlah];for(int i=0;i<jumlah;i++)arrayKursi[i]=new Kursi(String.valueOf(i+1));}
 public void setPenumpang(Penumpang p,int nomor){if(arrayKursi[nomor-1].getPenumpang()!=null){System.out.println("Maaf, kursi "+nomor+" sudah terisi.");return;}arrayKursi[nomor-1].setPenumpang(p);}
 public String info(){String s="Kode: "+kode+"\n";for(Kursi k:arrayKursi)s+=k.info();return s;}
}
