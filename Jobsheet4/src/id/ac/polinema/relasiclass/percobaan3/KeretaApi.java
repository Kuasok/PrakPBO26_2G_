package id.ac.polinema.relasiclass.percobaan3;
public class KeretaApi {
 private String nama,kelas; private Pegawai masinis,asisten;
 public KeretaApi(String nama,String kelas,Pegawai masinis){this.nama=nama;this.kelas=kelas;this.masinis=masinis;}
 public KeretaApi(String nama,String kelas,Pegawai masinis,Pegawai asisten){this.nama=nama;this.kelas=kelas;this.masinis=masinis;this.asisten=asisten;}
 public void setMasinis(Pegawai masinis){this.masinis=masinis;} public Pegawai getMasinis(){return masinis;}
 public void setAsisten(Pegawai asisten){this.asisten=asisten;} public Pegawai getAsisten(){return asisten;}
 public String info(){String info="";info+="Nama: "+this.nama+"\n";info+="Kelas: "+this.kelas+"\n";info+="Masinis: "+this.masinis.info()+"\n";if(this.asisten!=null)info+="Asisten: "+this.asisten.info()+"\n";return info;}
}
